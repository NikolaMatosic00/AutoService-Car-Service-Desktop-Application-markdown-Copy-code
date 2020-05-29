package automobil;

import java.util.ArrayList;
import korisnici.Serviser;

public class Servis {
	
	private Automobil auto_za_servis;
	private Serviser serviser;
	private String termin;
	private String opis;
	private ArrayList<Deo> delovi;
	private String status_servisa;
	
	public Servis() {
		
	}

	public Servis(Automobil auto_za_servis, Serviser serviser, String termin, String opis, ArrayList<Deo> delovi,
			String status_servisa) {
		super();
		this.auto_za_servis = auto_za_servis;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.delovi = delovi;
		this.status_servisa = status_servisa;
	}

	public Automobil getAuto_za_servis() {
		return auto_za_servis;
	}

	public void setAuto_za_servis(Automobil auto_za_servis) {
		this.auto_za_servis = auto_za_servis;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Deo> getDelovi() {
		return delovi;
	}

	public void setDelovi(ArrayList<Deo> delovi) {
		this.delovi = delovi;
	}

	public String getStatus_servisa() {
		return status_servisa;
	}

	public void setStatus_servisa(String status_servisa) {
		this.status_servisa = status_servisa;
	}
}