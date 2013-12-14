package fr.RivaMedia.model;

import java.util.List;

public class Marque {

    private String id;
    private String libelle;
    
    private List<Modele> modeles = null;
  
    
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Modele> getModeles() {
		return modeles;
	}
	public void setModeles(List<Modele> modeles) {
		this.modeles = modeles;
	}	
	
}

