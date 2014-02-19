package fr.RivaMedia.AnnoncesBateauGenerique.model.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

public class Parametres {

	private String texteIntro;
	private int backgroundColorUn;
	private int backgroundColorDeux;
	private int fontColorUn;
	private int fontColorDeux;
	private String majInterval;
	private String imageStart728x1280;
	private String imageStart640x1136;
	private String imageStart640x960;
	private String imageStart640x480;
	private String imageFond;
	private List<String> imagesSlide = new ArrayList<String>();
	
	public List<String> getImagesSlide() {
		return imagesSlide;
	}
	public void setImagesSlide(List<String> imagesSlide) {
		this.imagesSlide = imagesSlide;
	}
	private String imageLogo;

	public String getTexteIntro() {
		return texteIntro;
	}
	public void setTexteIntro(String texteIntro) {
		this.texteIntro = texteIntro;
	}
	public int getBackgroundColorUn() {
		return backgroundColorUn;
	}
	public void setBackgroundColorUn(int backgroundColorUn) {
		this.backgroundColorUn = backgroundColorUn;
	}
	public int getBackgroundColorDeux() {
		return backgroundColorDeux;
	}
	public void setBackgroundColorDeux(int backgroundColorDeux) {
		this.backgroundColorDeux = backgroundColorDeux;
	}
	public int getFontColorUn() {
		return fontColorUn;
	}
	public void setFontColorUn(int fontColorUn) {
		this.fontColorUn = fontColorUn;
	}
	public int getFontColorDeux() {
		return fontColorDeux;
	}
	public void setFontColorDeux(int fontColorDeux) {
		//this.fontColorDeux = fontColorDeux;
	}
	public String getMajInterval() {
		return majInterval;
	}
	public void setMajInterval(String majInterval) {
		this.majInterval = majInterval;
	}
	public String getImageStart728x1280() {
		return imageStart728x1280;
	}
	public void setImageStart728x1280(String imageStart728x1280) {
		this.imageStart728x1280 = imageStart728x1280;
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
	public String getImageStart640x480() {
		return imageStart640x480;
	}
	public void setImageStart640x480(String imageStart640x480) {
		this.imageStart640x480 = imageStart640x480;
	}
	public String getImageFond() {
		return imageFond;
	}
	public void setImageFond(String imageFond) {
		this.imageFond = imageFond;
	}
	
	public String getImageLogo() {
		return imageLogo;
	}
	public void setImageLogo(String imageLogo) {
		this.imageLogo = imageLogo;
	}

}
