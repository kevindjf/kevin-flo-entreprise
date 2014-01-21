package fr.RivaMedia.model;

import java.util.List;

public class TypeCategories {
	
	private String id;
	private List<Categorie> categories;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
}
