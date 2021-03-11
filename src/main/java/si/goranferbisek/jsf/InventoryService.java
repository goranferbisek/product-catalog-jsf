package si.goranferbisek.jsf;

import java.io.Serializable;

public interface InventoryService extends Serializable {

	public void createItem(Long catalogItemId, String name);
	
	public long getQuantity(Long catalogItemId);

}
