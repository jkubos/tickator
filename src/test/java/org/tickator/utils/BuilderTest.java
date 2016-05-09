package org.tickator.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BuilderTest {

	@Test
	public void testBasicWorkflow() {
		String obj = createBuilder().build();
		assertEquals("Test", obj);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testValidationFail() {
		String obj = createInvalidBuilder().build();
		assertEquals("Test", obj);
	}

	private Builder<String> createBuilder() {
		return new Builder<String>() {
			
			@Override
			protected void validate() {
				//
			}
			
			@Override
			protected String create() {
				return "Test";
			}
		};
	}

	private Builder<String> createInvalidBuilder() {
		return new Builder<String>() {
			
			@Override
			protected void validate() {
				throw new IllegalStateException();
			}
			
			@Override
			protected String create() {
				return "Test";
			}
		};
	}
}
