package org.tickator.change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class ChangeScope {
	private List<AddTickletAction> addTickletActions = new ArrayList<>();
	private List<ChangeScope> addScopes = new ArrayList<>();
	private List<ConnectAction> connectActions = new ArrayList<>();
	private Map<String, Connector> connectors = new HashMap<>();

	public AddTickletAction createTicklet(String className) {
		AddTickletAction res = new AddTickletAction(className);
		addTickletActions.add(res);
		
		return res;
	}
	
	public void addScope(ChangeScope scope) {
		addScopes.add(scope);
	}

	public void connect(Connector a, Connector b) {		
		connectActions.add(new ConnectAction(a, b));
	}

	public void addConnector(String name, Connector conn) {
		Validate.validState(!connectors.containsKey(name), "Already defined connector %s!", name);
		
		connectors.put(name, conn);
	}

	public Connector connector(String name) {
		Validate.validState(connectors.containsKey(name), "Not defined connector %s!", name);
		
		return connectors.get(name);
	}
	
	public List<AddTickletAction> getAddTickletActions() {
		return addTickletActions;
	}
	
	public List<ChangeScope> getAddScopes() {
		return addScopes;
	}

	public List<ConnectAction> getConnectActions() {
		return connectActions;
	}
}
