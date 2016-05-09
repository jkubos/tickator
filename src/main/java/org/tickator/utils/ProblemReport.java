package org.tickator.utils;

import org.apache.commons.lang3.Validate;

public class ProblemReport {
	
	private ProblemSeverity severity;
	private String message;

	public ProblemReport(ProblemSeverity severity, String message, Object...params) {
		Validate.notNull(severity);
		Validate.notBlank(message);
		
		this.severity = severity;
		this.message = String.format(message, params);
	}
	
	public ProblemSeverity getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}	
}
