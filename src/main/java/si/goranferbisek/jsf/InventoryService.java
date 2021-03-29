package si.goranferbisek.jsf;

import java.io.Serializable;
import java.util.concurrent.Future;

public interface InventoryService extends Serializable {

	public void createItem(Long catalogItemId, String name);
	
	public long getQuantity(Long catalogItemId);
	
	public Future<InventoryItem> asyncGetQuantity(Long catalogItemId);

}
