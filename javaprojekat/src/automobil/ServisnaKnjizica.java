package automobil;

import java.util.ArrayList;

public class ServisnaKnjizica {

	private Automobil automobil;
	private String identifikator;
	private ArrayList<Servis> servisi;
	
	public ServisnaKnjizica() {
		this.automobil = null;
		this.identifikator = "";
		this.servisi = new ArrayList<Servis>();
	}

	public ServisnaKnjizica(Automobil automobil, String identifikator, ArrayList<Servis> servisi) {
		super();
		this.automobil = automobil;
		this.identifikator = identifikator;
		this.servisi = servisi;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public String getIdentifikator() {
		return identifikator;
	}

	public void setIdentifikator(String identifikator) {
		this.identifikator = identifikator;
	}

	public ArrayList<Servis> getServisi() {
		return servisi;
	}

	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}

	@Override
	public String toString() {
		return "ServisnaKnjizica [automobil=" + automobil + ", identifikator=" + identifikator + ", servisi=" + servisi
				+ "]";
	}
}