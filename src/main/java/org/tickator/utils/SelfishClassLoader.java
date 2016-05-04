package org.tickator.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tickator.registry.TickletsRegistry;

public class SelfishClassLoader extends URLClassLoader {
	private static Logger logger = LoggerFactory.getLogger(SelfishClassLoader.class);
	
	private static final Set<String> selfishFiles = new HashSet<>();

	private URL jarFile;

	public SelfishClassLoader(ClassLoader parentClassLoader, URL jarFile) {
		super(new URL[]{jarFile}, parentClassLoader);
		this.jarFile = jarFile;
		selfishFiles.add(TickletsRegistry.REGISTRY_FILE);
	}

	public void addSelfishFile(String importedTicklet) {
		selfishFiles.add(importedTicklet);
	}
	
	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		if (selfishFiles.contains(name)) {
			logger.error("Loading selfish resource "+name);
			
			return super.findResources(name);
		} else {
			return super.getResources(name);
		}
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (selfishFiles.contains(name)) {
			logger.error("Loading selfish class "+name);
			
			return super.findClass(name);
		} else {
			return super.loadClass(name);
		}
	}
	
	@Override
	public String toString() {
		return "SelfishClassLoader["+jarFile+"]";
	}
}
