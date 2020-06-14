package moduli;

import java.util.ArrayList;

public class Musterija extends Korisnik {

	private int broj_nagradnoh_bodova;
	private ArrayList<Automobil> automobili;
	
	public Musterija() {
		this.broj_nagradnoh_bodova = 0;
		this.automobili = new ArrayList<Automobil>();

	}

	public Musterija(int id, String ime, String prezime, String jmbg, String pol, String adresa, String broj_telefona,
			String korisnicko_ime, String sifra, int broj_nagradnoh_bodova, ArrayList<Automobil> automobili) {
		super(id, ime, prezime, jmbg, pol, adresa, broj_telefona, korisnicko_ime, sifra);
		this.broj_nagradnoh_bodova = broj_nagradnoh_bodova;
		this.automobili = automobili;
	}

	public int getBroj_nagradnoh_bodova() {
		return broj_nagradnoh_bodova;
	}

	public void setBroj_nagradnoh_bodova(int broj_nagradnoh_bodova) {
		this.broj_nagradnoh_bodova = broj_nagradnoh_bodova;
	}

	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}
	
	public void dodajNaListuAutomobila(Automobil autic) {
		this.automobili.add(autic);
		
	}

	@Override
	public String toString() {
		return "Musterija [broj_nagradnoh_bodova=" + broj_nagradnoh_bodova + ", automobili=" + automobili + ", id=" + id
				+ ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol + ", adresa=" + adresa
				+ ", broj_telefona=" + broj_telefona + ", korisnicko_ime=" + korisnicko_ime + ", sifra=" + sifra + "]";
	}
	
	
	
}