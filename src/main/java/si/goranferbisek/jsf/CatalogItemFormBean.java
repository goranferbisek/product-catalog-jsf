package si.goranferbisek.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import si.goranferbisek.CatalogItem;
import si.goranferbisek.CatalogLocal;

@RequestScoped
@Named
public class CatalogItemFormBean implements Serializable {
	
	@Inject
	private CatalogLocal catalogBean;

	@Inject
	private InventoryService inventoryService;
	
	private CatalogItem item = new CatalogItem();
	private List<CatalogItem> items = new ArrayList<>();
	private String searchText;
	
	public void searchByName() {
		this.items = this.catalogBean.searchByName(this.searchText);
	}
	
	public String addItem() {
//		long itemId = this.catalogBean.getItems().size() + 1;
		
		this.catalogBean.addItem(new CatalogItem(this.item.getName(), this.item.getManufacturer(),
				this.item.getDescription(), this.item.getAvailableDate()));
		
		this.inventoryService.createItem(this.item.getItemId(), this.item.getName());
		
		return "list?faces-redirect=true";
	}
	
	public void init() {
		this.items = this.catalogBean.getItems();
	}
	
	public CatalogItem getItem() {
		return item;
	}
	public void setItem(CatalogItem item) {
		this.item = item;
	}
	public List<CatalogItem> getItems() {
		return items;
	}
	public void setItems(List<CatalogItem> items) {
		this.items = items;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}
