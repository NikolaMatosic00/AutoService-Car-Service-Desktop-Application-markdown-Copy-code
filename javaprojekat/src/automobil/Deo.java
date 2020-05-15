package automobil;

import java.util.ArrayList;

import enumi.Marka;
import enumi.Modeli_auta;

public class Deo {

	private Marka marka;
	private Modeli_auta model;
	private String naziv;
	private float cena;
	private ArrayList<Servis> servisi;
	
	public Deo() {
		this.marka = null;
		this.model = null;
		this.naziv = "";
		this.cena = 0;
		this.servisi = new ArrayList<Servis>();
	}
	
	public Deo(Marka marka, Modeli_auta model, String naziv, float cena) {
		super();
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
		this.servisi = new ArrayList<Servis>();
	}

	
	public ArrayList<Servis> getServisi() {
		return servisi;
	}


	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}


	public Marka getMarka() {
		return marka;
	}
	public void setMarka(Marka marka) {
		this.marka = marka;
	}
	public Modeli_auta getModel() {
		return model;
	}
	public void setModel(Modeli_auta model) {
		this.model = model;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	@Override
	public String toString() {
		return "Deo [marka=" + marka + ", model=" + model + ", naziv=" + naziv + ", cena=" + cena + "]";
	}
	
	
	
}