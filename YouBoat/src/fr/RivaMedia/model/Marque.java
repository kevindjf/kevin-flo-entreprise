package fr.RivaMedia.model;

import java.util.Map;

public class Marque {

    private String idMarque;
    private String libelle;
    private Map<Object,Object> modelesById;
    //ASIFormDataRequest* requete;
    private String idType;
    
	public String getIdMarque() {
		return idMarque;
	}
	public void setIdMarque(String idMarque) {
		this.idMarque = idMarque;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Map<Object, Object> getModelesById() {
		return modelesById;
	}
	public void setModelesById(Map<Object, Object> modelesById) {
		this.modelesById = modelesById;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
}
