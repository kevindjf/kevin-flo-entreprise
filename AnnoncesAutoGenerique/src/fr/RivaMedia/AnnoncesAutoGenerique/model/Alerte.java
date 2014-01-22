package fr.RivaMedia.AnnoncesAutoGenerique.model;

public class Alerte {

	private String id;
	private String categorie;
	private String longueurMin;
	private String longueurMax;
	private String prixMin;
	private String prixMax;
	private String etat;
	private String date;

	private String jeton;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getLongueurMin() {
		return longueurMin;
	}
	public void setLongueurMin(String longueurMin) {
		this.longueurMin = longueurMin;
	}
	public String getLongueurMax() {
		return longueurMax;
	}
	public void setLongueurMax(String longueurMax) {
		this.longueurMax = longueurMax;
	}
	public String getPrixMin() {
		return prixMin;
	}
	public void setPrixMin(String prixMin) {
		this.prixMin = prixMin;
	}
	public String getPrixMax() {
		return prixMax;
	}
	public void setPrixMax(String prixMax) {
		this.prixMax = prixMax;
	}
	public String getJeton() {
		return jeton;
	}
	public void setJeton(String jeton) {
		this.jeton = jeton;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	

}