package fr.RivaMedia.AnnoncesBateauGenerique.model;

//pareil que news...
public class NewsDescription {
	private String title;
	private String category;
	private String imageAdress;
	private String pubDate;
	private String description;
	private String dateFormattee;
	private String heureFormattee;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
