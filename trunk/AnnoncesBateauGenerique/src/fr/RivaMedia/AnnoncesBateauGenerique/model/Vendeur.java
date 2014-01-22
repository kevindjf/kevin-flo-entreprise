package fr.RivaMedia.AnnoncesBateauGenerique.model;

import java.util.List;

public class Vendeur {
	private String numero;
    private String nom;
    private String email;
    private String adresse;
    private String codePostal;
    private String ville;
    private String tel1;
    private String tel2;
    private String siteWeb;
    private String type;
    
    private String vendeurLatitude;
    private String vendeurLongitude;
    
    private String logo;
    private String gpsLatitude;
    private String gpsLongitude;
    private String nombreAnnonce;
    
    private String fax;
    private String contact;
    private String horaires;
    private String description;
    private String nbBateau;
    private String nbMoteur;
    private String nbAccessoire;
    
    private List<String> services;
    
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getSiteWeb() {
		return siteWeb;
	}
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVendeurLatitude() {
		return vendeurLatitude;
	}
	public void setVendeurLatitude(String vendeurLatitude) {
		this.vendeurLatitude = vendeurLatitude;
	}
	public String getVendeurLongitude() {
		return vendeurLongitude;
	}
	public void setVendeurLongitude(String vendeurLongitude) {
		this.vendeurLongitude = vendeurLongitude;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(String gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public String getGpsLongitude() {
		return gpsLongitude;
	}
	public void setGpsLongitude(String gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	public String getNombreAnnonce() {
		return nombreAnnonce;
	}
	public void setNombreAnnonce(String nombreAnnonce) {
		this.nombreAnnonce = nombreAnnonce;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getHoraires() {
		return horaires;
	}
	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNbBateau() {
		return nbBateau;
	}
	public void setNbBateau(String nbBateau) {
		this.nbBateau = nbBateau;
	}
	public String getNbMoteur() {
		return nbMoteur;
	}
	public void setNbMoteur(String nbMoteur) {
		this.nbMoteur = nbMoteur;
	}
	public String getNbAccessoire() {
		return nbAccessoire;
	}
	public void setNbAccessoire(String nbAccessoire) {
		this.nbAccessoire = nbAccessoire;
	}
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
    
    
}
