package fr.RivaMedia.model;

import java.util.List;

public class InfoClient {
	private String numero;
    private String nom;
    private String ville;
    private String tel1;
    private String tel2;
   // private String logo;
    private String gpsLatitude;
    private String gpsLongtitude;
    private String email;
    private String adresse;
    private String cp;
    private String fax;
    private String siteWeb;
    private String contact;
    private String horaires;
    private String service;
    private String description;
    private String nbBateau;
    private String nbMoteur;
    private String nbAccessoire;
    //NSMutableArray* serviceArray;
    Logo curLogo;
    List<Logo> photos;
    //Services* adservice;
    List<Object> services;
    
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSiteWeb() {
		return siteWeb;
	}
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
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
	public Logo getCurLogo() {
		return curLogo;
	}
	public void setCurLogo(Logo curLogo) {
		this.curLogo = curLogo;
	}
	public List<Logo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Logo> photos) {
		this.photos = photos;
	}
	public List<Object> getServices() {
		return services;
	}
	public void setServices(List<Object> services) {
		this.services = services;
	}
    
}
