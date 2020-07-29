package moduli;


import java.util.ArrayList;

import enumi.Pol;
import enumi.Specijalizacija;
import moduli.Korisnik;


public class Serviser extends Korisnik {

	private String plata;
	private Specijalizacija specijalizacija;
	private ArrayList<Servis> servisi_na_koje_je_rasporedjen;
	
	public Serviser() {
		super();
		this.plata = "";
		this.specijalizacija = Specijalizacija.AUTO_ELEKTRICAR;
		this.servisi_na_koje_je_rasporedjen = new ArrayList<Servis>();
	}

	



	public Serviser(int id, String ime, String prezime, String jmbg, Pol pol, String adresa, String broj_telefona,
			String korisnicko_ime, String sifra, String plata, Specijalizacija specijalizacija,
			ArrayList<Servis> servisi_na_koje_je_rasporedjen) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_telefona, korisnicko_ime, sifra);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
		this.servisi_na_koje_je_rasporedjen = servisi_na_koje_je_rasporedjen;
	}




	public ArrayList<Servis> getServisi_na_koje_je_rasporedjen() {
		return servisi_na_koje_je_rasporedjen;
	}





	public void setServisi_na_koje_je_rasporedjen(ArrayList<Servis> servisi_na_koje_je_rasporedjen) {
		this.servisi_na_koje_je_rasporedjen = servisi_na_koje_je_rasporedjen;
	}



	
	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}





	@Override
	public String toString() {
		return "Serviser [plata=" + plata + ", specijalizacija=" + specijalizacija + ", servisi_na_koje_je_rasporedjen="
				+ servisi_na_koje_je_rasporedjen + ", id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg="
				+ jmbg + ", pol=" + pol + ", adresa=" + adresa + ", broj_telefona=" + broj_telefona
				+ ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + "]";
	}





	



	


	

	
}
