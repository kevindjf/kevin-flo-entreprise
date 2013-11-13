package fr.RivaMedia.model;

import java.util.List;

public class Bateau {
	private String numero;
	private String title;
	private String moteur;
	private String longueur;
	private String annee;
	private String categorie;
	private String gpsLatitude;
	private String gpsLongtitude;
	private String typeClient;
	private String photos;
	private String prix;
    private String taxePrix;
    private String pubDate;
    List<RubriqueDescription> rubriquesDecscription;
    private String type;
    // utilisé dans le tris des annonces
    private int prixNumber;
    private double tailleNumber;
    
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMoteur() {
		return moteur;
	}
	public void setMoteur(String moteur) {
		this.moteur = moteur;
	}
	public String getLongueur() {
		return longueur;
	}
	public void setLongueur(String longueur) {
		this.longueur = longueur;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(String gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public String getGpsLongtitude() {
		return gpsLongtitude;
	}
	public void setGpsLongtitude(String gpsLongtitude) {
		this.gpsLongtitude = gpsLongtitude;
	}
	public String getTypeClient() {
		return typeClient;
	}
	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public String getTaxePrix() {
		return taxePrix;
	}
	public void setTaxePrix(String taxePrix) {
		this.taxePrix = taxePrix;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public List<RubriqueDescription> getRubriquesDecscription() {
		return rubriquesDecscription;
	}
	public void setRubriquesDecscription(
			List<RubriqueDescription> rubriquesDecscription) {
		this.rubriquesDecscription = rubriquesDecscription;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrixNumber() {
		return prixNumber;
	}
	public void setPrixNumber(int prixNumber) {
		this.prixNumber = prixNumber;
	}
	public double getTailleNumber() {
		return tailleNumber;
	}
	public void setTailleNumber(double tailleNumber) {
		this.tailleNumber = tailleNumber;
	}
}
