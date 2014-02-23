package fr.RivaMedia.AnnoncesBateauGenerique.model;

import java.util.List;

public class Client {

	String id;
	String contrat;
	String nom;
	String adresse;
	String cp;
	String ville;
	String tel1;
	String tel2;
	String email;
	String contact;
	String departement;
	String departementNum;
	String image;
	
	String horaires;
	List<String> services;
	String distributeur;
	
	String lng;
	String lat;
	
	String fax;
	String siteWeb;
	String description;
	String logo;
	
	int nbBateau;
	int nbMoteur;
	int nbAccessoires;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getDepartementNum() {
		return departementNum;
	}
	public void setDepartementNum(String departementNum) {
		this.departementNum = departementNum;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHoraires() {
		return horaires;
	}
	public void setHoraires(String horaires) {
		this.horaires = horaires;
	}
	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
	public String getDistributeur() {
		return distributeur;
	}
	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}
	public String getContrat() {
		return contrat;
	}
	public void setContrat(String contrat) {
		this.contrat = contrat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getNbBateau() {
		return nbBateau;
	}
	public void setNbBateau(int nbBateau) {
		this.nbBateau = nbBateau;
	}
	public int getNbMoteur() {
		return nbMoteur;
	}
	public void setNbMoteur(int nbMoteur) {
		this.nbMoteur = nbMoteur;
	}
	public int getNbAccessoires() {
		return nbAccessoires;
	}
	public void setNbAccessoires(int nbAccessoires) {
		this.nbAccessoires = nbAccessoires;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", contrat=" + contrat + ", nom=" + nom
				+ ", adresse=" + adresse + ", cp=" + cp + ", ville=" + ville
				+ ", tel1=" + tel1 + ", tel2=" + tel2 + ", email=" + email
				+ ", contact=" + contact + ", departement=" + departement
				+ ", departementNum=" + departementNum + ", image=" + image
				+ ", horaires=" + horaires + ", services=" + services
				+ ", distributeur=" + distributeur + ", lng=" + lng + ", lat="
				+ lat + ", fax=" + fax + ", siteWeb=" + siteWeb
				+ ", description=" + description + ", logo=" + logo
				+ ", nbBateau=" + nbBateau + ", nbMoteur=" + nbMoteur
				+ ", nbAccessoires=" + nbAccessoires + "]";
	}
	
}
