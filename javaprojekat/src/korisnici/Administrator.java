package korisnici;

public class Administrator extends Korisnik {
	
	private String plata;
	
	public Administrator() {
		super();
		this.plata = "";
	}

	public Administrator(String plata) {
		super();
		this.plata = plata;
	}

	public Administrator(String ime, String prezime, String JMBG, String pol, String adresa, String broj_telefona,
			String korisnicko_ime, String sifra, String plata) {
	}

	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return "Administrator [plata=" + plata + ", ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", pol="
				+ pol + ", adresa=" + adresa + ", broj_telefona=" + broj_telefona + ", korisnicko_ime=" + korisnicko_ime
				+ ", sifra=" + sifra + "]";
	}

}