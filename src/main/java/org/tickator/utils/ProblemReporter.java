package org.tickator.utils;

public interface ProblemReporter {

	void report(ProblemReport problemReport);
	
	default void report(ProblemSeverity severity, String message, Object...params) {
		report(new ProblemReport(severity, message, params));
	}
}
