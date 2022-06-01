package module;

public class Article {
	
	int idArticle, 
		prixHT, 
		TVA	, 
		quantiteStock;
	
	String libelle;
	
	
	
	public Article(int id, int prixHT, int tVA, int quantiteStock, String libelle) {
		super();
		this.idArticle = id;
		this.prixHT = prixHT;
		TVA = tVA;
		this.quantiteStock = quantiteStock;
		this.libelle = libelle;
	}
	
	public int getId() {
		return idArticle;
	}
	public void setId(int id) {
		this.idArticle = id;
	}
	public int getPrixHT() {
		return prixHT;
	}
	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}
	public int getTVA() {
		return TVA;
	}
	public void setTVA(int tVA) {
		TVA = tVA;
	}
	public int getQuantiteStock() {
		return quantiteStock;
	}
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
