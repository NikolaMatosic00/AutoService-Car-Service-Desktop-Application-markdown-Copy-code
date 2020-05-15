package korisnici;

import enumi.Specijalizacija;

public class Serviser extends Korisnik {

	private String plata;
	private Specijalizacija specijalizacija;
	
	public Serviser() {
		super();
		this.plata = "";
		this.specijalizacija = Specijalizacija.AUTO_ELEKTRICAR;
	}

	public Serviser(String plata, Specijalizacija specijalizacija) {
		super();
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public Serviser(String ime, String prezime, String jMBG, String pol, String adresa, String broj_telefona,
			String korisnicko_ime, String sifra, String plata, String specijalizacija) {
		// TODO Auto-generated constructor stub
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
		return "Serviser [plata=" + plata + ", specijalizacija=" + specijalizacija + ", ime=" + ime + ", prezime="
				+ prezime + ", JMBG=" + JMBG + ", pol=" + pol + ", adresa=" + adresa + ", broj_telefona="
				+ broj_telefona + ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + "]";
	}

}