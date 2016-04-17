package org.tickator.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tickator.Ticklet;
import org.tickator.utils.TickatorUtils;

public class TickletsRegistry {
	private static Logger logger = LoggerFactory.getLogger(TickletsRegistry.class);
			
	public static final String REGISTRY_FILE = "ticklets.txt";
	
	public String PORT_PREFIX = "DEF_";
	
	private final Map<Class<? extends Ticklet>, TickletMetadata> metadata = new HashMap<>();

	public TickletsRegistry() {
		add(getClass().getClassLoader());
	}

	public void add(ClassLoader classLoader) {
		logger.debug("Adding Ticklets from classloader "+classLoader);
		
		TickatorUtils.withRuntimeException(()->{
			Enumeration<URL> list = classLoader.getResources(REGISTRY_FILE);
			
			while (list.hasMoreElements()) {
				add(classLoader, list.nextElement());
			}
		});
	}

	private void add(ClassLoader classLoader, URL tickletsRegistryFile) {
		logger.debug("Applying Ticklets from configuration '{}'", tickletsRegistryFile);
		
		TickatorUtils.withRuntimeException(()->{
			String content = IOUtils.toString(tickletsRegistryFile.toURI());
			
			Arrays.asList(content.split("\\R")).forEach(line->{				
				String cleanLine = line.trim();
				if (!StringUtils.isBlank(cleanLine) && !cleanLine.startsWith("#")) {
					logger.debug("Found line with ticklet '{}'", cleanLine);
					
					addTicklet(classLoader, cleanLine);
				}
			});
		});
	}

	private void addTicklet(ClassLoader classLoader, String klassName) {
		TickatorUtils.withRuntimeException(()->{
			Class<?> rawKlass = classLoader.loadClass(klassName);
			
			if (rawKlass.getSuperclass()!=Ticklet.class) {
				throw new RuntimeException("Ticklet "+klassName+" must be directly inherited from "+Ticklet.class.getName()+" class.");
			}
			
			@SuppressWarnings("unchecked")
			Class<? extends Ticklet> klass = (Class<? extends Ticklet>) rawKlass;
			
			logger.debug("Class found, trying to search for ports");
			
			for (Field field : klass.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers())) {
					if (field.getName().matches("^"+PORT_PREFIX +".*$")) {
						logger.debug("Registering port {}", field.getName());
						registerPort(klass, (PortDefinition<?>)field.get(null));
					}
				}
			}
			
			logger.info("Ticklet [{}] successfully initialized", klass.getName());
		});
	}

	private void registerPort(Class<? extends Ticklet> klass, PortDefinition<?> portDefinition) {
		metadata.computeIfAbsent(klass, k->new TickletMetadata());
	}
}
