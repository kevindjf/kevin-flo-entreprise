package fr.RivaMedia.model;

import java.util.Map;

public class Categorie {
	 private Integer idCategory;
	 private String libelle;
	 private Map<Object,Object> contener;
	 
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Map<Object, Object> getContener() {
		return contener;
	}
	public void setContener(Map<Object, Object> contener) {
		this.contener = contener;
	}
}
