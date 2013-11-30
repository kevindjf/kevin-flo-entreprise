package fr.RivaMedia.model;

import java.util.ArrayList;
import java.util.List;

public class Annonce {
	private String typeAnnonce;
	
	
	private String numero;
	private String idClient;
	private String title;
	private String nomMoteur;
	private String longueur;
	private String annee;
	private String categorie;
	private String gpsLatitude;
	private String gpsLongtitude;
	private String typeClient;
	private List<Lien> photos; //le nombre de photos
	private String prix;
    private String taxePrix;
    private String pubDate;
    private Lien lien;
    private String type;
    // utilise dans le tris des annonces
    private int prixNumber;
    private double tailleNumber;
    
    
    private String lienAnnonce;
    private String numeroVendeur;
    private Moteur moteur;
    private String etat;
    private String largeur;
    private String nbCabines;
    private String nbCouchettes;
    private String nbSallesDeBain;
    private String garantie;
    private String commentaire;
    private String placeDePort;
    private String taxe;
    private String nbPhotos;
    private Vendeur vendeur;
    private List<String> equipement = new ArrayList<String>();
    private List<String> electroniques = new ArrayList<String>();
    
    private String apartirDe;
    
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
	public String getNomMoteur() {
		return nomMoteur;
	}
	public void setNomMoteur(String nomMoteur) {
		this.nomMoteur = nomMoteur;
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
	public List<Lien> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Lien> photos) {
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
	public Lien getLien() {
		return lien;
	}
	public void setLien(Lien lien) {
		this.lien = lien;
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
	public String getLienAnnonce() {
		return lienAnnonce;
	}
	public void setLienAnnonce(String lienAnnonce) {
		this.lienAnnonce = lienAnnonce;
	}
	public String getNumeroVendeur() {
		return numeroVendeur;
	}
	public void setNumeroVendeur(String numeroVendeur) {
		this.numeroVendeur = numeroVendeur;
	}
	public Moteur getMoteur() {
		return moteur;
	}
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getLargeur() {
		return largeur;
	}
	public void setLargeur(String largeur) {
		this.largeur = largeur;
	}
	public String getNbCabines() {
		return nbCabines;
	}
	public void setNbCabines(String nbCabines) {
		this.nbCabines = nbCabines;
	}
	public String getNbCouchettes() {
		return nbCouchettes;
	}
	public void setNbCouchettes(String nbCouchettes) {
		this.nbCouchettes = nbCouchettes;
	}
	public String getNbSallesDeBain() {
		return nbSallesDeBain;
	}
	public void setNbSallesDeBain(String nbSallesDeBain) {
		this.nbSallesDeBain = nbSallesDeBain;
	}
	public String getGarantie() {
		return garantie;
	}
	public void setGarantie(String garantie) {
		this.garantie = garantie;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getPlaceDePort() {
		return placeDePort;
	}
	public void setPlaceDePort(String placeDePort) {
		this.placeDePort = placeDePort;
	}
	public String getTaxe() {
		return taxe;
	}
	public void setTaxe(String taxe) {
		this.taxe = taxe;
	}
	public String getNbPhotos() {
		return nbPhotos;
	}
	public void setNbPhotos(String nbPhotos) {
		this.nbPhotos = nbPhotos;
	}
	public Vendeur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}
	public List<String> getEquipement() {
		return equipement;
	}
	public void getEquipement(List<String> equipement) {
		this.equipement = equipement;
	}
	public String getTypeAnnonce() {
		return typeAnnonce;
	}
	public void setTypeAnnonce(String typeAnnonce) {
		this.typeAnnonce = typeAnnonce;
	}
	public void setEquipement(List<String> equipement) {
		this.equipement = equipement;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public List<String> getElectroniques() {
		return electroniques;
	}
	public void setElectroniques(List<String> electroniques) {
		this.electroniques = electroniques;
	}
	public String getApartirDe() {
		return apartirDe;
	}
	public void setApartirDe(String apartirDe) {
		this.apartirDe = apartirDe;
	}
    
    
}
