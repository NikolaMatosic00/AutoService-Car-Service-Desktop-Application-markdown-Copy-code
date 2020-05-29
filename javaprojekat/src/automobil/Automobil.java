package automobil;

import korisnici.Musterija;
import enumi.Marka;
import enumi.Modeli_auta;

public class Automobil {
	private Musterija vlasnik;
	private Marka marka;
	private Modeli_auta model;
	private String godina_proizvodnje;
	private float zapremina_motora;
	private int snaga_motora;
	private String vrsta_goriva;
	private String id;
	private  ServisnaKnjizica servisna_knjizica;

	
	public Automobil () {
		this.vlasnik = null;
		this.marka = null;
		this.model = null;
		this.godina_proizvodnje = "";
		this.zapremina_motora = 0;
		this.snaga_motora = 0;
		this.vrsta_goriva = "";
		this.id = "";
		this.servisna_knjizica = null;
		
	}


	public Automobil(Musterija vlasnik, Marka marka, Modeli_auta model, String godina_proizvodnje,
			float zapremina_motora, int snaga_motora, String vrsta_goriva, String id,
			ServisnaKnjizica servisna_knjizica) {
		super();
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godina_proizvodnje = godina_proizvodnje;
		this.zapremina_motora = zapremina_motora;
		this.snaga_motora = snaga_motora;
		this.vrsta_goriva = vrsta_goriva;
		this.id = id;
		this.servisna_knjizica = servisna_knjizica;
	}


	public Musterija getVlasnik() {
		return vlasnik;
	}


	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
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


	public String getGodina_proizvodnje() {
		return godina_proizvodnje;
	}


	public void setGodina_proizvodnje(String godina_proizvodnje) {
		this.godina_proizvodnje = godina_proizvodnje;
	}


	public float getZapremina_motora() {
		return zapremina_motora;
	}


	public void setZapremina_motora(float zapremina_motora) {
		this.zapremina_motora = zapremina_motora;
	}


	public int getSnaga_motora() {
		return snaga_motora;
	}


	public void setSnaga_motora(int snaga_motora) {
		this.snaga_motora = snaga_motora;
	}


	public String getVrsta_goriva() {
		return vrsta_goriva;
	}


	public void setVrsta_goriva(String vrsta_goriva) {
		this.vrsta_goriva = vrsta_goriva;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ServisnaKnjizica getServisna_knjizica() {
		return servisna_knjizica;
	}


	public void setServisna_knjizica(ServisnaKnjizica servisna_knjizica) {
		this.servisna_knjizica = servisna_knjizica;
	}


	@Override
	public String toString() {
		return "Automobil [vlasnik=" + vlasnik + ", marka=" + marka + ", model=" + model + ", godina_proizvodnje="
				+ godina_proizvodnje + ", zapremina_motora=" + zapremina_motora + ", snaga_motora=" + snaga_motora
				+ ", vrsta_goriva=" + vrsta_goriva + ", id=" + id + ", servisna_knjizica=" + servisna_knjizica + "]";
	}
	
	
	
}
