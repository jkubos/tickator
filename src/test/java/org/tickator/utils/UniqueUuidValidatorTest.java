package org.tickator.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class UniqueUuidValidatorTest {
	@Test
	public void testSuccess() {
		UniqueUuidValidator validator = new UniqueUuidValidator();
		validator.add("a123", 1111);
		validator.add("b456", 2222);
		validator.add("c789", 3333);
		
		List<ProblemReport> problemReports = new ArrayList<>();
		
		validator.validate(problemReports::add, Object::toString);
		
		assertEquals(0, problemReports.size());
	}
	
	@Test
	public void testFail() {
		UniqueUuidValidator validator = new UniqueUuidValidator();
		validator.add("a123", 1111);
		validator.add("a123", 2222);
		validator.add("c789", 3333);
		
		List<ProblemReport> problemReports = new ArrayList<>();
		
		validator.validate(problemReports::add, Object::toString);
		
		assertEquals(1, problemReports.size());
		assertTrue(problemReports.get(0).getMessage().contains("a123"));
		assertTrue(problemReports.get(0).getMessage().contains("1111"));
		assertTrue(problemReports.get(0).getMessage().contains("2222"));
	}
}
