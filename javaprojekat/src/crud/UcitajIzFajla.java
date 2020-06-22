package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import enumi.Marka;
import enumi.ModelAutomobila;
import enumi.Pol;
import enumi.Specijalizacija;
import enumi.StatusServisa;
import enumi.VrstaGoriva;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;

public class UcitajIzFajla {
	
	
	private ArrayList<Administrator> admini;
	private ArrayList<Musterija> musterije;
	private ArrayList<Automobil> automobili;
	private ArrayList<ServisnaKnjizica> servisne_knjizice;
	private ArrayList<Servis> servisi;
	private ArrayList<Serviser> serviseri;
	private ArrayList<Deo> delovi;


	public UcitajIzFajla() {
		this.admini = new ArrayList<Administrator>();
		this.musterije = new ArrayList<Musterija>();
		this.automobili = new ArrayList<Automobil>();
		this.servisne_knjizice = new ArrayList<ServisnaKnjizica>();
		this.serviseri = new ArrayList<Serviser>();
		this.servisi = new ArrayList<Servis>();
		this.delovi = new ArrayList<Deo>();

	}
	
	
	public ArrayList<Administrator> getAdmini() {
		return admini;
	}


	public void setAdmini(ArrayList<Administrator> admini) {
		this.admini = admini;
	}


	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}


	public void setMusterije(ArrayList<Musterija> musterije) {
		this.musterije = musterije;
	}


	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}


	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}


	public ArrayList<ServisnaKnjizica> getServisne_knjizice() {
		return servisne_knjizice;
	}


	public void setServisne_knjizice(ArrayList<ServisnaKnjizica> servisne_knjizice) {
		this.servisne_knjizice = servisne_knjizice;
	}


	public ArrayList<Servis> getServisi() {
		return servisi;
	}


	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}


	public ArrayList<Serviser> getServiseri() {
		return serviseri;
	}


	public void setServiseri(ArrayList<Serviser> serviseri) {
		this.serviseri = serviseri;
	}


	public ArrayList<Deo> getDelovi() {
		return delovi;
	}


	public void setDelovi(ArrayList<Deo> delovi) {
		this.delovi = delovi;
	}


	public void UcitajAdministratore(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				Pol pol = Pol.valueOf(sp[4]);
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				String plata = sp[9];
			Administrator adm = new Administrator(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, plata);
			this.admini.add(adm);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	public void ucitajMusterije(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				Pol pol = Pol.valueOf(sp[4]);
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				int br_bod = Integer.parseInt(sp[9]);
				ArrayList<Automobil> lista_auta = new ArrayList<Automobil>();
				
				

			Musterija mus = new Musterija(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, br_bod, lista_auta);
			this.musterije.add(mus);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	public void ucitajAutomobile(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				Musterija vlasnik = new Musterija();
				Marka marka = Marka.valueOf(sp[2]);
				ModelAutomobila model = ModelAutomobila.valueOf(sp[3]);
				String godina_pr = sp[4];
				int zapremina = Integer.parseInt(sp[5]);
				int snaga = Integer.parseInt(sp[6]);
				VrstaGoriva vrsta_goriva = VrstaGoriva.valueOf(sp[7]);
				ServisnaKnjizica ser_knj = new ServisnaKnjizica();
				ser_knj.SetId(Integer.parseInt(sp[8]));
				for(Musterija m : musterije) {
					if(sp[1].equalsIgnoreCase(m.getKorisnicko_ime())) {
						vlasnik = m;
						Automobil autic = new Automobil(id, vlasnik, marka, model, godina_pr, zapremina, snaga, vrsta_goriva, ser_knj);
						m.getAutomobili().add(autic);
						
					}
						
				}
				for(ServisnaKnjizica sss : this.servisne_knjizice) {
					if(sss.getId() == ser_knj.getId()) {
						sss.setAutomobil(new Automobil(id, vlasnik, marka, model, godina_pr, zapremina, snaga,
					vrsta_goriva, ser_knj));
					}
				}


			Automobil auto = new Automobil(id, vlasnik, marka, model, godina_pr, zapremina, snaga,
					vrsta_goriva, ser_knj);
			this.automobili.add(auto);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	public void ucitajServisnaKnjizice(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				ArrayList<Servis> servisiii = new ArrayList<Servis>();
				if(sp[2].length() > 1) {
					
					for(String s : sp[2].trim().split(",")) {
						Servis ser = new Servis();
						ser.SetId(Integer.parseInt(s));
						servisiii.add(ser);
					}
				}else {
					try {
					Servis ser = new Servis();
					ser.SetId(Integer.parseInt(sp[2]));
					servisiii.add(ser);
					}catch(NumberFormatException e)
					{
						
					}
				}
				Automobil auto = new Automobil();
				for(Automobil a : automobili) {
					if(a.getServisna_knjizica().getId() == id) {
						auto = a;
						a.setServisna_knjizica(new ServisnaKnjizica(id, auto, servisiii));
					}
				}
			ServisnaKnjizica ser_knj = new ServisnaKnjizica(id, auto, servisiii);
			this.servisne_knjizice.add(ser_knj);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	
	public void ucitajServise(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				Serviser servisr = new Serviser();
				servisr.setId(Integer.parseInt(sp[2]));
				String termin = sp[3];
				String opis = sp[4];
				ArrayList<Deo> deloviii = new ArrayList<Deo>();
				StatusServisa status_servisa = StatusServisa.valueOf(sp[6]);
				Automobil auto = new Automobil();
				for(Automobil a : automobili) {
					if(a.getId() == Integer.parseInt(sp[1])) {
						auto = a;
					}
				}
				if(sp[5].length() > 1) {
					for(String s : sp[2].split(",")) {
						Deo d = new Deo();
						d.SetId(Integer.parseInt(s));
						deloviii.add(d);
					}
				}else {
					Deo d = new Deo();
					d.SetId(Integer.parseInt(sp[2]));
					deloviii.add(d);
				}
				for(ServisnaKnjizica sk : servisne_knjizice) {
					for(Servis s : sk.getObavljeni_servisi()) {
						if(s.getId() == id) {
							s.setAuto_za_servis(auto);
							s.setOpis(opis);
							s.setTermin(termin);
							s.setServiser(servisr);
							s.setUpotrebljeni_delovi(deloviii);
							s.setStatus_servisa(status_servisa);
						}
					}
				}
				for(Musterija mmm : musterije) {
					for(Automobil aa : mmm.getAutomobili()) {
						if(aa.getId() == auto.getId()) {
							aa.getServisna_knjizica().getObavljeni_servisi().add(new Servis(id, auto, servisr, termin, opis, deloviii, status_servisa));
						}
					}
				}
//delovima se dodaje id.
						
				
			Servis servis = new Servis(id, auto, servisr, termin, opis, deloviii, status_servisa);
			this.servisi.add(servis);
			}
			br.close();
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	
	
	public void ucitajServisere(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				Pol pol = Pol.valueOf(sp[4]);
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				String plata = sp[9];
				Specijalizacija spec = Specijalizacija.valueOf(sp[10]);
				ArrayList<Servis> servisi_na_koje_je_rasp = new ArrayList<Servis>();
				
			for(Servis s : servisi) {
				if(s.getServiser().getId() == id) {
					s.setServiser(new Serviser(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, plata, spec, servisi_na_koje_je_rasp));
					servisi_na_koje_je_rasp.add(s);
				}
			}
			Serviser ser = new Serviser(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, plata, spec, servisi_na_koje_je_rasp);
			this.serviseri.add(ser);
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	public void ucitajDelove(String putanja) {
		File fajl_in = new File(putanja);
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				Marka marka = Marka.valueOf(sp[1]);
				ModelAutomobila model = ModelAutomobila.valueOf(sp[2]);
				String naziv = sp[3];
				int cena = Integer.parseInt(sp[4]);
			Deo delic = new Deo(id, marka, model, naziv, cena);
			this.delovi.add(delic);
			for(Servis s : servisi) {
				for(Deo d : s.getUpotrebljeni_delovi()) {
					if(d.getId() == id) {
						d.setCena(cena);
						d.setMarka(marka);
						d.setModel(model);
						d.setNaziv(naziv);
					}
				}
			}
			}
			br.close();
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}
	
	public Administrator NadjiAdministratora(int id) {
		for(Administrator a : this.admini) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Musterija NadjiMusteriju(int id) {
		for(Musterija a : this.musterije) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Serviser NadjiServisera(int id) {
		for(Serviser a : this.serviseri) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Automobil NadjiAutomobil(int id) {
		for(Automobil a : this.automobili) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Deo NadjiDeo(int id) {
		for(Deo a : this.delovi) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Servis NadjiServis(int id) {
		for(Servis a : this.servisi) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	
	public ServisnaKnjizica NadjiServisnuKnjizicu(int id) {
		for(ServisnaKnjizica a : this.servisne_knjizice) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Administrator nadjiAdministratora(String korisnicko_ime) {
		for(Administrator a : this.admini) {
			if (a.getKorisnicko_ime() == korisnicko_ime) {
				return a;
			}
		}
		return null;
	}
	
	public Musterija NadjiMusteriju(String korisnicko_ime) {
		for(Musterija a : this.musterije) {
			if (a.getKorisnicko_ime() == korisnicko_ime) {
				return a;
			}
		}
		return null;
	}
	
	public Serviser NadjiServisera(String korisnicko_ime) {
		for(Serviser a : this.serviseri) {
			if (a.getKorisnicko_ime() == korisnicko_ime) {
				return a;
			}
		}return null;
	}
	
	public Serviser NadjiServiseraPoPrezimenu(String prezime) {
		for(Serviser a : this.serviseri) {
			if (a.getPrezime() == prezime) {
				return a;
			}
		}return null;
	}
	
	
	public void upisiAdministratore() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\administratori.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Administrator administrator : this.admini) {
			String linija_za_upis = String.valueOf(administrator.getId()) + "|" + administrator.getIme() + "|" + administrator.getPrezime() + "|" + administrator.getJmbg() + "|" +
					administrator.getPol() + "|" + administrator.getAdresa() + "|" + administrator.getBroj_telefona() + "|" + administrator.getKorisnicko_ime() + "|" + 
					administrator.getSifra() + "|" + administrator.getPlata() + "\n" ;
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}
	
	public void upisiMusterije() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\musterije.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Musterija musterijaa : this.musterije) {
			String musterijini_automobili_id = " ";
			if (musterijaa.getAutomobili() != null) {
			for(Automobil a : musterijaa.getAutomobili()) {
				musterijini_automobili_id += String.valueOf(a.getId()) + ",";
			}
			} 
			
			String linija_za_upis =String.valueOf(musterijaa.getId()) + "|"  + musterijaa.getIme() + "|" + musterijaa.getPrezime() + "|" + musterijaa.getJmbg() + "|" +
					musterijaa.getPol() + "|" + musterijaa.getAdresa() + "|" + musterijaa.getBroj_telefona() + "|" + musterijaa.getKorisnicko_ime() + "|" + 
					musterijaa.getSifra() + "|" +  String.valueOf(musterijaa.getBroj_nagradnoh_bodova()) + "|" + musterijini_automobili_id.substring(0, musterijini_automobili_id.length() - 1) + "\n" ;

			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}
	
	public void upisiServisere() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\serviseri.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Serviser serviser : this.serviseri) {
				String idijevi_servisa = " ";
				if(serviser.getServisi_na_koje_je_rasporedjen() != null) {
					for(Servis s : serviser.getServisi_na_koje_je_rasporedjen()) {
						idijevi_servisa += String.valueOf(s.getId()) + ",";
					}
				}
			String linija_za_upis =String.valueOf(serviser.getId()) + "|" + serviser.getIme() + "|" + serviser.getPrezime() + "|" + serviser.getJmbg() + "|" +
					serviser.getPol() + "|" + serviser.getAdresa() + "|" + serviser.getBroj_telefona() + "|" + serviser.getKorisnicko_ime() + "|" + 
					serviser.getSifra() + "|" + serviser.getPlata() + "|" + serviser.getSpecijalizacija() + "|" + idijevi_servisa.substring(0, idijevi_servisa.length() - 1).trim() + "\n" ;
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}

	public void upisiAutomobile( ) {
	
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\automobili.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Automobil auto : this.automobili) {
			String linija_za_upis = String.valueOf(auto.getId())+ "|" + auto.getVlasnik().getKorisnicko_ime() + "|" + auto.getMarka() + "|" + auto.getModel() + "|" + auto.getGodina_proizvodnje() + "|" +
					String.valueOf(auto.getZapremina_motora()) + "|"
					+ String.valueOf(auto.getSnaga_motora()) + "|" 
					+ auto.getVrsta_goriva() + "|" 
					+ String.valueOf(auto.getServisna_knjizica().getId()) + "\n";
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}
	
	public void upisiDelove() {
			
			
			File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\delovi.txt");
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
				for(Deo deo : this.delovi) {
				String linija_za_upis = String.valueOf(deo.getId()) + "|" + deo.getMarka() + "|" + deo.getModel() + "|" + deo.getNaziv() + "|" + String.valueOf(deo.getCena()) + "\n";
				
				bw.write(linija_za_upis);
				}
				bw.close();
				
			} catch (IOException e) {
				
				System.out.println("Nesto nije u redu sa fajlom.");
			}
			
			}
	
	public void upisiServise() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\servisi.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Servis servis : this.servisi) {
			String upotrebljeni_del = " ";
			if(servis.getUpotrebljeni_delovi() != null) {
			for(Deo a : servis.getUpotrebljeni_delovi()) {
				upotrebljeni_del += String.valueOf(a.getId()) + ",";
			}
			}
			
			String linija_za_upis = String.valueOf(servis.getId()) + "|" + 
			String.valueOf(servis.getAuto_za_servis().getId()) + "|" + 
			String.valueOf(servis.getServiser().getId()) + "|" 
			+ servis.getTermin() + "|" 
			+ servis.getOpis() + "|" + upotrebljeni_del.substring(0, upotrebljeni_del.length() - 1).trim() + "|" + 
			servis.getStatus_servisa() +"\n";
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}

	public void upisiServisneKnjizice() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\tekstualni_fajlovi\\servisneknjizice.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(ServisnaKnjizica servisna_k : this.servisne_knjizice) {
			String uradjeno = " ";
			for(Servis s : servisna_k.getObavljeni_servisi()) {
				uradjeno += s.getId() + ",";
			}
			String temp = "";
			String temp2 = "";
			String neki = "";
			for(String cao : uradjeno.trim().split(",")) {
				temp2 = temp;
				temp = cao;
				if(!temp.equals(temp2)) {
					neki += temp + ",";
				}
			}
			
			String linija_za_upis = String.valueOf(servisna_k.getId()) + "|" + String.valueOf(servisna_k.getAutomobil().getId()) + "|"  +neki.substring(0, neki.length() - 1) +"\n";
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
}

	public void upisiSve() {
		upisiAdministratore();
		upisiAutomobile();
		upisiMusterije();
		upisiServise();
		upisiServisere();
		upisiServisneKnjizice();
		upisiDelove();
	}
}
