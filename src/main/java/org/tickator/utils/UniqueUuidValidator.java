package org.tickator.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

public class UniqueUuidValidator {
	private Map<String, Set<Object>> objectsPerUuid = new HashMap<>();
	
	public UniqueUuidValidator() {
	}

	public void add(String uuid, Object object) {
		Validate.notBlank(uuid);
		objectsPerUuid.computeIfAbsent(uuid, k->new HashSet<>()).add(object);
	}
	
	public void validate(Consumer<ProblemReport> consumer, ObjectDescriber objectDescriber) {
		objectsPerUuid.entrySet().stream().filter(e->e.getValue().size()>1).forEach(e->{
			String descs = e.getValue().stream().map(objectDescriber::describe).collect(Collectors.joining(", "));
			consumer.accept(new ProblemReport(ProblemSeverity.error, "Duplicit uuid '%s' found for %s", e.getKey(), descs));
		});
	}
}
