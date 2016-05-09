package org.tickator.utils;

import org.apache.commons.lang3.Validate;

public abstract class Builder<T> {

	private boolean builded;
	
	public Builder() {
		
	}

	public final T build() {
		Validate.validState(!builded, "This builder was already used.");
		builded = true;
		
		validate();
		
		return create();
	}
	
	protected abstract T create();
	
	protected abstract void validate();
}
