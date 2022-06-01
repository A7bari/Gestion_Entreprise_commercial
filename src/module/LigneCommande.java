package module;

public class LigneCommande {
	
	int id,	
		quantite,
		article;
	
	public LigneCommande(int id, int quantite, int article) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.article = article;
	}

	public int getId() {
		return id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setArticle(int article) {
		this.article = article;
	}

	public int getArticle() {
		return article;
	}
	
	
}
