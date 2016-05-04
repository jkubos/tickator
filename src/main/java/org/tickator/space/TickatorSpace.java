package org.tickator.space;

import java.util.ArrayList;
import java.util.List;

import org.tickator.change.TickatorChange;

@Deprecated
public class TickatorSpace {
	
	private List<TickatorChange> currentChanges = new ArrayList<>();

	public TickatorSpace() {
	}

	public void applyChanges() {
		// TODO Auto-generated method stub
		
	}

}


/*


	private void applyChanges() {
		currentChanges.forEach(change->{
			
		});
		
		
//		TickatorUtils.withRuntimeException(()->{
//			for (AddTickletAction addTickletAction : scope.getAddTickletActions()) {
//				TickletMetadata tickletMetadata = tickletsRegistry.lookup(addTickletAction.getKlassName());
//				Ticklet ticklet = tickletMetadata.getKlass().getConstructor(new Class<?>[]{getClass(), Map.class}).newInstance(new Object[]{this, addTickletAction.getProperties()});
//
//				ticklet.validate();
//				ticklets.put(addTickletAction.getUuid(), ticklet);
//				
//				if (addTickletAction.isAutostart()) {
//					executor.schedule(ticklet);
//				}
//			}
//			
//			for (ChangeScope addScope : scope.getAddScopes()) {
//				applyChange(addScope, tickletsToExecute, ticklets);
//			}
//			
//			for (ConnectAction connectAction : scope.getConnectActions()) {
//				Ticklet a = ticklets.get(connectAction.getA().getTickletUuid());
//				Validate.notNull(a);
//				
//				Ticklet b = ticklets.get(connectAction.getB().getTickletUuid());
//				Validate.notNull(b);
//				
//				int todo;
////				connect(a, connectAction.getA().getPort(), b, connectAction.getB().getPort());
//			}
//		});		
	}

//	private void connect(Ticklet a, String portNameA, Ticklet b, String portNameB) {
//		Port<?> portA = a.getPort(portNameA);
//		Port<?> portB = b.getPort(portNameB);
//		
//		OutputPort<?> output;
//		
//		Connectable<?> input;
//		
//		if (portA instanceof Connectable<?> && portB instanceof OutputPort<?>) {
//			output = (OutputPort<?>) portB;
//			input = (Connectable<?>) portA;
//		} else if (portA instanceof OutputPort<?> && portB instanceof Connectable<?>) {
//			output = (OutputPort<?>) portA;
//			input = (Connectable<?>) portB;
//		} else {
//			throw new RuntimeException(String.format("Cannot connect %s:'%s':%s to %s:'%s':%s", 
//					a.getClass().getName(), portNameA, portA.getClass().getSimpleName(), 
//					b.getClass().getName(), portNameB, portB.getClass().getSimpleName()));
//		}
//	
//		input.connect(output);
//	}


 */