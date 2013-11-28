package fr.RivaMedia.model;

import java.util.List;

public class Actualite {
    private String title;
    private String link;
    private String imageAdress;
    private String pubDate;
    private String description;
    private List<Lien> liens;
    private String dateFormattee;
    private String heureFormattee;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageAdress() {
		return imageAdress;
	}
	public void setImageAdress(String imageAdress) {
		this.imageAdress = imageAdress;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Lien> getContener() {
		return liens;
	}
	public void setContener(List<Lien> liens) {
		this.liens = liens;
	}
	public String getDateFormattee() {
		return dateFormattee;
	}
	public void setDateFormattee(String dateFormattee) {
		this.dateFormattee = dateFormattee;
	}
	public String getHeureFormattee() {
		return heureFormattee;
	}
	public void setHeureFormattee(String heureFormattee) {
		this.heureFormattee = heureFormattee;
	}
}
