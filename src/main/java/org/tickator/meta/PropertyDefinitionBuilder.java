package org.tickator.meta;

public class PropertyDefinitionBuilder<T> {
	private PropertyDefinition<T> obj;
	
	public PropertyDefinitionBuilder(Class<? extends T> klass, String uuid, String name) {
		obj = new PropertyDefinition<>(klass, uuid, name);
	}
	
	public PropertyDefinitionBuilder<T> withMandatory(boolean mandatory) {
		obj.setMandatory(mandatory);
		
		return this;
	}
	
	public PropertyDefinition<T> build() {
		PropertyDefinition<T> res = obj;
		obj = null;
		return res;
	}
}
