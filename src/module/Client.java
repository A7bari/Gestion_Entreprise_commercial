package module;

public class Client {
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", encoure=" + encoure + ", chiffreAffaire=" + chiffreAffaire + ", tel=" + tel
				+ ", nom=" + nom + ", adresseLiv=" + adresseLiv + ", estEntreprise=" + estEntreprise + "]";
	}


	int id,	
		encoure	,
		chiffreAffaire,
		tel;
	
	String 	nom, 
			adresseLiv;
	
	boolean estEntreprise;
	
	
	public Client(int id, String nom,boolean estEntreprise, int encoure, int chiffreAffaire, String adresseLiv ,int tel
			) {
		super();
		this.id = id;
		this.encoure = encoure;
		this.chiffreAffaire = chiffreAffaire;
		this.tel = tel;
		this.nom = nom;
		this.adresseLiv = adresseLiv;
		this.estEntreprise = estEntreprise;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setEncoure(int encoure) {
		this.encoure = encoure;
	}


	public void setChiffreAffaire(int chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}


	public void setTel(int tel) {
		this.tel = tel;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setAdresseLiv(String adresseLiv) {
		this.adresseLiv = adresseLiv;
	}


	public void setEstEntreprise(boolean estEntreprise) {
		this.estEntreprise = estEntreprise;
	}


	public int getId() {
		return id;
	}


	public int getEncoure() {
		return encoure;
	}


	public int getChiffreAffaire() {
		return chiffreAffaire;
	}


	public int getTel() {
		return tel;
	}


	public String getNom() {
		return nom;
	}


	public String getAdresseLiv() {
		return adresseLiv;
	}


	public boolean isEstEntreprise() {
		return estEntreprise;
	}

	
	
	
	
	
}
