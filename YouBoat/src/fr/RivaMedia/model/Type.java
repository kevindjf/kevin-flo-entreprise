package fr.RivaMedia.model;

import java.util.Map;

public class Type {
	private String idType;
    //private List<Categorie> categories;
	private Map<Object,Object> categoriesById;
	
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public Map<Object, Object> getCategoriesById() {
		return categoriesById;
	}
	public void setCategoriesById(Map<Object, Object> categoriesById) {
		this.categoriesById = categoriesById;
	}
}
