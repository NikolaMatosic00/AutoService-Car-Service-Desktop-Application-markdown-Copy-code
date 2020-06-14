package moduli;

import enumi.Specijalizacija;
import moduli.Korisnik;


public class Serviser extends Korisnik {

	private String plata;
	private Specijalizacija specijalizacija;
	
	public Serviser() {
		super();
		this.plata = "";
		this.specijalizacija = Specijalizacija.AUTO_ELEKTRICAR;
	}

	public Serviser(int id, String ime, String prezime, String jmbg, String pol, String adresa, String broj_telefona,
			String korisnicko_ime, String sifra,String plata, Specijalizacija specijalizacija) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_telefona, korisnicko_ime, sifra);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
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
		return "Serviser [plata=" + plata + ", specijalizacija=" + specijalizacija + ", id=" + id + ", ime=" + ime
				+ ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa + ", broj_telefona="
				+ broj_telefona + ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + "]";
	}

	
}
