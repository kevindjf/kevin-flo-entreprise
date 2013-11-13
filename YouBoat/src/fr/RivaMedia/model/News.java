package fr.RivaMedia.model;

import java.util.List;

public class News {
    private String title;
    private String link;
    private String imageAdress;
    private String pubDate;
    private String description;
    private List<RubriqueDescription> contener;
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
	public List<RubriqueDescription> getContener() {
		return contener;
	}
	public void setContener(List<RubriqueDescription> contener) {
		this.contener = contener;
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
