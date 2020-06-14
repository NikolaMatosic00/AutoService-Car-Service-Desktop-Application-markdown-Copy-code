package moduli;


import enumi.Marka;
import enumi.ModelAutomobila;

public class Deo {

	private int id;
	private Marka marka;
	private ModelAutomobila model;
	private String naziv;
	private int cena;
	
	public Deo() {
		this.id = 0;
		this.marka = null;
		this.model = null;
		this.naziv = "";
		this.cena = 0;
	}

	public Deo(int id, Marka marka, ModelAutomobila model, String naziv, int cena) {
		super();
		this.id = id;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
	}
	
	public int getId() {
		return id;
	}

	public void SetId(int id) {
		this.id = id;
	}

	public Marka getMarka() {
		return marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}

	public ModelAutomobila getModel() {
		return model;
	}

	public void setModel(ModelAutomobila model) {
		this.model = model;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Deo [id=" + id + ", marka=" + marka + ", model=" + model + ", naziv=" + naziv + ", cena=" + cena + "]";
	}

}
