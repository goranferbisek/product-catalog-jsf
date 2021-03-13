package si.goranferbisek.jsf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("remoteInventoryService")
public class RemoteInventoryService implements InventoryService {

private Map<Long, InventoryItem> items = new HashMap<>();
	
	@Override
	public void createItem(Long catalogItemId, String name) {
		long inventoryItemId = items.size() + 1;
		this.items.put(inventoryItemId, new InventoryItem(inventoryItemId, catalogItemId, name, 0L));
		this.printInventory();
	}

	private void printInventory() {
		System.out.println("Remote inventory contains: ");
		
		for (Entry<Long, InventoryItem> entry: this.items.entrySet()) {
			System.out.println(entry.getValue().getName());
		}
	}

	@Override
	public long getQuantity(Long catalogItemId) {
		return 0L;
	}
	
}
