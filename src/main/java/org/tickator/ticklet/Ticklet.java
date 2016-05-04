package org.tickator.ticklet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.Validate;
import org.tickator.definition.ports.PortDefinition;
import org.tickator.facade.TickatorExecutorFacadeForTicklet;
import org.tickator.registry.TickletMetadata;
import org.tickator.utils.MetaObjectDescriber;
import org.tickator.utils.TickatorUtils;

public abstract class Ticklet {
	private static final Map<String, Object> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<>());
	
	private Map<String, Port<?>> portsByUuid = new HashMap<>();

	private final TickletMetadata metadata;
	private final Map<String, Object> properties;
	private final TickatorExecutorFacadeForTicklet tickatorExecutorFacade;

	public Ticklet(Function<Ticklet, TickatorExecutorFacadeForTicklet> tickatorExecutorFacadeProvider, 
			TickletMetadata metadata, Map<String, Object> properties) {
		Validate.notNull(tickatorExecutorFacadeProvider);
		Validate.notNull(metadata);
		
		this.metadata = metadata;
		
		this.properties = properties!=null ? Collections.unmodifiableMap(properties) : EMPTY_MAP;

		this.tickatorExecutorFacade = tickatorExecutorFacadeProvider.apply(this);
	}
	
	public void executeWithCheck() throws Exception {
		Validate.validState(tickatorExecutorFacade.isExecuted());
		execute();
	}
	
	protected abstract void execute() throws Exception;
	
	public TickletMetadata getMetadata() {
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
	
	protected TickatorExecutorFacadeForTicklet getTickatorExecutorFacade() {
		return tickatorExecutorFacade;
	}
	
	protected void scheduleAsync() {
		tickatorExecutorFacade.scheduleAsync();
	}
	
	public Map<String, Object> getProperties() {
		Validate.notNull(properties);
		return properties;
	}
}
