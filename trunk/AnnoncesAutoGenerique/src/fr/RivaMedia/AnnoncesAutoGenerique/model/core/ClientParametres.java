package fr.RivaMedia.AnnoncesAutoGenerique.model.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

public class ClientParametres {

	String id = "";
	String texteIntro = "";
	int couleurPrincipale = Color.parseColor("#FFFFFF");
	int couleurSecondaire = Color.parseColor("#000000");
	String imageFond = "";
	String imageAccueil = "";
	String imageLogo = "";
	List<String> imageSlider = new ArrayList<String>();
	String imageStart640x1136;
	String imageStart640x960;
	
	String majInterval;
	int couleurTitre;
	int couleurTexte;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTexteIntro() {
		return texteIntro;
	}
	public void setTexteIntro(String texteIntro) {
		this.texteIntro = texteIntro;
	}
	public int getCouleurPrincipale() {
		return couleurPrincipale;
	}
	public void setCouleurPrincipale(int couleurPrincipale) {
		this.couleurPrincipale = couleurPrincipale;
	}
	public int getCouleurSecondaire() {
		return couleurSecondaire;
	}
	public void setCouleurSecondaire(int couleurSecondaire) {
		this.couleurSecondaire = couleurSecondaire;
	}
	public String getImageFond() {
		return imageFond;
	}
	public void setImageFond(String imageFond) {
		this.imageFond = imageFond;
	}
	public String getImageAccueil() {
		return imageAccueil;
	}
	public void setImageAccueil(String imageAccueil) {
		this.imageAccueil = imageAccueil;
	}
	public String getImageLogo() {
		return imageLogo;
	}
	public void setImageLogo(String imageLogo) {
		this.imageLogo = imageLogo;
	}
	public List<String> getImageSlider() {
		return imageSlider;
	}
	public void setImageSlider(List<String> imageSlider) {
		this.imageSlider = imageSlider;
	}
	public String getImageStart640x1136() {
		return imageStart640x1136;
	}
	public void setImageStart640x1136(String imageStart640x1136) {
		this.imageStart640x1136 = imageStart640x1136;
	}
	public String getImageStart640x960() {
		return imageStart640x960;
	}
	public void setImageStart640x960(String imageStart640x960) {
		this.imageStart640x960 = imageStart640x960;
	}
	public String getMajInterval() {
		return majInterval;
	}
	public void setMajInterval(String majInterval) {
		this.majInterval = majInterval;
	}
	public int getCouleurTitre() {
		return couleurTitre;
	}
	public void setCouleurTitre(int couleurTitre) {
		this.couleurTitre = couleurTitre;
	}
	public int getCouleurTexte() {
		return couleurTexte;
	}
	public void setCouleurTexte(int couleurTexte) {
		this.couleurTexte = couleurTexte;
	}
	
	
}
