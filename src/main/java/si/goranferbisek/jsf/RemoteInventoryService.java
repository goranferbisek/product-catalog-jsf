package si.goranferbisek.jsf;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ApplicationScoped
@RemoteService
public class RemoteInventoryService implements InventoryService {
	
	private String apiUrl = "http://localhost:8080/product-catalog-jax/catalog/api/";
	
	@Override
	public void createItem(Long catalogItemId, String name) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(apiUrl)
			.path("inventoryitems")
			.request()
			.post(Entity.json(new InventoryItem(null, catalogItemId, name, (long) new Random().nextInt(10))));
		
		System.out.println(response.getStatus());
		System.out.println(response.getLocation().getPath());
	}

	@Override
	public long getQuantity(Long catalogItemId) {
		Client client = ClientBuilder.newClient();
		InventoryItem item = client.target(apiUrl).path("inventoryitems").path("catalog")
				.queryParam("catalogItemId", catalogItemId.toString())
				.request()
				.get(InventoryItem.class);

		return item.getQuantity();
	}
	
}
