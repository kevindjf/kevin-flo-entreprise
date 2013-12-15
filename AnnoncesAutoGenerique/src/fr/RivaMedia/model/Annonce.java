package fr.RivaMedia.model;

import java.util.List;

public class Annonce {
    
	String id;
	String idClient;
	String categorie;
	String marque;
	String serie;
	String finition;
	String energie;
	String annee;
	String km;
	String prix;
	String departement;
	String departementNum;
	String photo;
	
	String transmission;
	String puissanceDin;
	String puissanceFisc;
	String co2;
	String nbPortes;
	String couleurExt;
	String couleurInt;
	String garantie;
	String reference;
	String descriptif;
	Client client;
	List<String> photos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFinition() {
		return finition;
	}
	public void setFinition(String finition) {
		this.finition = finition;
	}
	public String getEnergie() {
		return energie;
	}
	public void setEnergie(String energie) {
		this.energie = energie;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getPuissanceDin() {
		return puissanceDin;
	}
	public void setPuissanceDin(String puissanceDin) {
		this.puissanceDin = puissanceDin;
	}
	public String getPuissanceFisc() {
		return puissanceFisc;
	}
	public void setPuissanceFisc(String puissanceFisc) {
		this.puissanceFisc = puissanceFisc;
	}
	public String getCo2() {
		return co2;
	}
	public void setCo2(String co2) {
		this.co2 = co2;
	}
	public String getNbPortes() {
		return nbPortes;
	}
	public void setNbPortes(String nbPortes) {
		this.nbPortes = nbPortes;
	}
	public String getCouleurExt() {
		return couleurExt;
	}
	public void setCouleurExt(String couleurExt) {
		this.couleurExt = couleurExt;
	}
	public String getCouleurInt() {
		return couleurInt;
	}
	public void setCouleurInt(String couleurInt) {
		this.couleurInt = couleurInt;
	}
	public String getGarantie() {
		return garantie;
	}
	public void setGarantie(String garantie) {
		this.garantie = garantie;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
    
}
