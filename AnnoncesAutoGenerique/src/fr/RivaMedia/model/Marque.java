package fr.RivaMedia.model;

import java.util.List;

public class Marque {

    private String id;
    private String nom;
    
    private List<Modele> modeles = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Modele> getModeles() {
		return modeles;
	}

	public void setModeles(List<Modele> modeles) {
		this.modeles = modeles;
	}
  
	
}
