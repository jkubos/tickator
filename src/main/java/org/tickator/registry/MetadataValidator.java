package org.tickator.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tickator.utils.MetaObjectDescriber;
import org.tickator.utils.ProblemReporter;
import org.tickator.utils.ProblemSeverity;

public class MetadataValidator {	
	public static void validate(TickletsRegistry registry, ProblemReporter problemReporter) {
		validateTickletUniqueness(registry, problemReporter);
		validateUuidUniqueness(registry, problemReporter);
		validatePortNameUniqueness(registry, problemReporter);
		validatePropertyNameUniqueness(registry, problemReporter);
	}

	private static void validateTickletUniqueness(TickletsRegistry registry, ProblemReporter problemReporter) {
		final Map<String, List<String>> tickletMapping = new HashMap<>();
		
		registry.getTickletsMetadata().forEach(md->{
			tickletMapping.computeIfAbsent(md.getName(), k->new ArrayList<>()).add(MetaObjectDescriber.describe(md));
		});
		
		tickletMapping.entrySet().stream().filter(e->e.getValue().size()>1).forEach(e->{
			problemReporter.report(ProblemSeverity.error, String.format("Duplicit ticklet %s in objects %s", e.getKey(), String.join(" / ", e.getValue())));
		});
	}

	private static void validatePropertyNameUniqueness(TickletsRegistry registry, ProblemReporter problemReporter) {
		registry.getTickletsMetadata().forEach(md->{
			final Map<String, List<String>> nameMapping = new HashMap<>();
			
			md.getPorts().forEach(port->{
				nameMapping.computeIfAbsent(port.getName(), k->new ArrayList<>()).add(MetaObjectDescriber.describe(md, port));
			});
			
			nameMapping.entrySet().stream().filter(e->e.getValue().size()>1).forEach(e->{
				problemReporter.report(ProblemSeverity.error, String.format("Duplicit port name %s of %s", e.getKey(), String.join(" / ", e.getValue())));
			});
		});
	}

	private static void validatePortNameUniqueness(TickletsRegistry registry, ProblemReporter problemReporter) {
		registry.getTickletsMetadata().forEach(md->{
			final Map<String, List<String>> nameMapping = new HashMap<>();
			
			md.getProperties().forEach(property->{
				nameMapping.computeIfAbsent(property.getName(), k->new ArrayList<>()).add(MetaObjectDescriber.describe(md, property));
			});
			
			nameMapping.entrySet().stream().filter(e->e.getValue().size()>1).forEach(e->{
				problemReporter.report(ProblemSeverity.error, String.format("Duplicit property name %s of %s", e.getKey(), String.join(" / ", e.getValue())));
			});
		});
	}

	private static void validateUuidUniqueness(TickletsRegistry registry, ProblemReporter problemReporter) {
		final Map<String, List<String>> uuidMapping = new HashMap<>();
		
		registry.getTickletsMetadata().forEach(md->{
			md.getPorts().forEach(port->{
				uuidMapping.computeIfAbsent(port.getUuid(), k->new ArrayList<>()).add(MetaObjectDescriber.describe(md, port));
			});
			
			md.getProperties().forEach(property->{
				uuidMapping.computeIfAbsent(property.getUuid(), k->new ArrayList<>()).add(MetaObjectDescriber.describe(md, property));
			});
		});
		
		uuidMapping.entrySet().stream().filter(e->e.getValue().size()>1).forEach(e->{
			problemReporter.report(ProblemSeverity.error, String.format("Duplicit UUID %s in objects %s", e.getKey(), String.join(" / ", e.getValue())));
		});
	}
}
