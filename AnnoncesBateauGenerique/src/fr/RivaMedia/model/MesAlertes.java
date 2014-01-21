package fr.RivaMedia.model;

public class MesAlertes {

	private String numero;
	private String idCategorie;
	private Double tailleMin;
	private Double tailleMax;
	private Double budgetMin;
	private Double budgetMax;
	private String date;
	private String dateFormattee;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}
	public Double getTailleMin() {
		return tailleMin;
	}
	public void setTailleMin(Double tailleMin) {
		this.tailleMin = tailleMin;
	}
	public Double getTailleMax() {
		return tailleMax;
	}
	public void setTailleMax(Double tailleMax) {
		this.tailleMax = tailleMax;
	}
	public Double getBudgetMin() {
		return budgetMin;
	}
	public void setBudgetMin(Double budgetMin) {
		this.budgetMin = budgetMin;
	}
	public Double getBudgetMax() {
		return budgetMax;
	}
	public void setBudgetMax(Double budgetMax) {
		this.budgetMax = budgetMax;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateFormattee() {
		return dateFormattee;
	}
	public void setDateFormattee(String dateFormattee) {
		this.dateFormattee = dateFormattee;
	}
	
}
