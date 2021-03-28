package si.goranferbisek.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.goranferbisek.CatalogItem;
import si.goranferbisek.CatalogLocal;
import si.goranferbisek.ItemManager;

@Named
@ConversationScoped
public class CatalogItemDetailBean implements Serializable {
	
	private long itemId;

	private CatalogItem item;
	
	private Long quantity;
	
	@Inject
	@RemoteService
	private InventoryService inventoryService;

	@Inject
	private Conversation conversation;
	
	@Inject
	private CatalogLocal catalogBean;
	
	private ItemManager manager = new ItemManager();

	public void fetchItem() {
		this.item = this.catalogBean.findItem(this.itemId);
		this.quantity = this.inventoryService.getQuantity(this.itemId);
	}
	
	public void addManager() {
		this.manager.getCatalogItems().add(this.item);
		this.item.getItemManagers().add(this.manager);
		
		this.catalogBean.saveItem(item);
		this.item = this.catalogBean.findItem(itemId);
	}
	
	@PostConstruct
	public void init() {
		this.conversation.begin();
	}
	
	@PreDestroy
	public void destroy() {
		conversation.end();
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public CatalogItem getItem() {
		return item;
	}

	public void setItem(CatalogItem item) {
		this.item = item;
	}

	public ItemManager getManager() {
		return manager;
	}

	public void setManager(ItemManager manager) {
		this.manager = manager;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public InventoryService getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
}

