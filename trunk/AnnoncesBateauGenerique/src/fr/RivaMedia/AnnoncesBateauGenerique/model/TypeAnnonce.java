package fr.RivaMedia.AnnoncesBateauGenerique.model;

public class TypeAnnonce {

	private String id;
	private String nom;
	private String intitule;
	private String nb;
	private String url;
	
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

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getNb() {
		return nb;
	}

	public void setNb(String nb) {
		this.nb = nb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
