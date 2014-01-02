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
	
	
}
