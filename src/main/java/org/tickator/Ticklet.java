package org.tickator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.tickator.meta.MetaObjectDescriber;
import org.tickator.meta.PortDefinition;
import org.tickator.meta.TickletMetadata;
import org.tickator.utils.TickatorUtils;

public abstract class Ticklet {
	private static final Map<String, Object> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<>());
	
	private Map<String, Port<?>> portsByUuid = new HashMap<>();
	private Tickator tickator;
	private TickletMetadata metadata;
	private final Map<String, Object> properties;

	public Ticklet(Tickator tickator, Map<String, Object> properties) {
		Validate.notNull(tickator);
		
		this.tickator = tickator;
		
		this.properties = properties!=null ? Collections.unmodifiableMap(properties) : EMPTY_MAP;
	}
	
	protected abstract void execute() throws Exception;

	public Tickator getTickator() {
		return tickator;
	}
	
	public TickletMetadata getMetadata() {
		if (metadata==null) {
			metadata = tickator.getTickletsRegistry().lookup(getClass().getName());
		}
		
		return metadata;
	}
	
	void registerPort(Port<?> port) {
		if (portsByUuid.containsKey(port.getDefinition().getUuid())) {
			TickatorUtils.throwRuntime("Already registered port %s", MetaObjectDescriber.describe(getMetadata(), port.getDefinition()));
		}
		
		if (!getMetadata().hasPort(port.getDefinition())) {
			TickatorUtils.throwRuntime("Registering unknown port %s", MetaObjectDescriber.describe(getMetadata(), port.getDefinition()));
		}
		
		portsByUuid.put(port.getDefinition().getUuid(), port);
	}
	
	Port<?> getPort(PortDefinition<?> definition) {
		if (!portsByUuid.containsKey(definition.getUuid())) {
			TickatorUtils.throwRuntime("Not registered port %s", MetaObjectDescriber.describe(getMetadata(), definition));
		}
		
		return portsByUuid.get(definition.getUuid());
	}
	
	void validate() {
		Validate.validState(portsByUuid.size()==metadata.getPorts().size(), "Not all ports are defined for %s!", MetaObjectDescriber.describe(getMetadata()));
	}
	
	protected void scheduleAsync() {
		tickator.scheduleAsync(this);
	}
	
	public Map<String, Object> getProperties() {
		Validate.notNull(properties);
		return properties;
	}
}
