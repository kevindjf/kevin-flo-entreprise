package fr.RivaMedia.model;

public class TypeAnnonce {

	private String id;
	private String nom;
	
	public TypeAnnonce(String id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	public TypeAnnonce() {
	}

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
	
}
