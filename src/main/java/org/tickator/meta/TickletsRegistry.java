package org.tickator.meta;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tickator.Ticklet;
import org.tickator.utils.TickatorUtils;

public class TickletsRegistry {
	private static Logger logger = LoggerFactory.getLogger(TickletsRegistry.class);
			
	public static final String REGISTRY_FILE = "ticklets.txt";

	private static final String SETUP_FIELD = "SETUP";

	public static final String PORT_FIELD_PREFIX = "DEF_";
	
	public static final String PROPERTY_FIELD_PREFIX = "PROP_";
	
	private final Map<Class<? extends Ticklet>, TickletMetadata> tickletsMetadata = new HashMap<>();
	private final Collection<TickletMetadata> tickletsMetadataReadOnly = Collections.unmodifiableCollection(tickletsMetadata.values());
	
	public TickletsRegistry() {
		
	}

	public void addOwnClassloader() {
		add(getClass().getClassLoader());
	}
	
	public Collection<TickletMetadata> getTickletsMetadata() {
		return tickletsMetadataReadOnly;
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
			
			TickletMetadata metadata = addSetup(klass);
			addPorts(klass, metadata);
			addProperties(klass, metadata);
			
			if (tickletsMetadata.containsKey(klass)) {
				logger.warn("Overriding class {}", klass.getName());
			}
			
			tickletsMetadata.put(klass, metadata);
			
			logger.info("Ticklet [{}] successfully initialized", klass.getName());
		});
	}

	private TickletMetadata addSetup(Class<? extends Ticklet> klass) throws Exception {
		logger.debug("Searching for setup");
		
		MutableObject<TickletSetup> setup = new MutableObject<>();
		
		TickatorUtils.forEachStaticField(klass, SETUP_FIELD, field->{
			logger.debug("Found setup field");
			
			setup.setValue((TickletSetup)field.get(null));
		});
		
		if (setup.getValue()==null) {
			logger.debug("Setup not found, creating default instance");
			setup.setValue(new TickletSetup());
		}
		
		return new TickletMetadata(klass.getName(), setup.getValue());
	}

	private void addPorts(Class<? extends Ticklet> klass, TickletMetadata metadata) throws Exception {
		logger.debug("Searching for ports");
		
		TickatorUtils.forEachStaticField(klass, "^"+PORT_FIELD_PREFIX +".*$", field->{
			logger.debug("Adding port {}", field.getName());
			
			metadata.addPort((PortDefinition<?>)field.get(null));
		});
	}

	private void addProperties(Class<? extends Ticklet> klass, TickletMetadata metadata) throws Exception {
		logger.debug("Searching for properties");
		
		TickatorUtils.forEachStaticField(klass, "^"+PROPERTY_FIELD_PREFIX +".*$", field->{
			logger.debug("Adding property {}", field.getName());
			metadata.addProperty((PropertyDefinition<?>)field.get(null));
		});
	}	
}
