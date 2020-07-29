package rad_sa_listama;

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

public class SveListe {
	
	
	private ArrayList<Administrator> admini;
	private ArrayList<Musterija> musterije;
	private ArrayList<ServisnaKnjizica> servisne_knjizice;
	private ArrayList<Automobil> automobili;
	private ArrayList<Serviser> serviseri;
	private ArrayList<Servis> servisi;
	private ArrayList<Deo> delovi;


	public SveListe() {
		this.admini = new ArrayList<Administrator>();
		this.musterije = new ArrayList<Musterija>();
		this.servisne_knjizice = new ArrayList<ServisnaKnjizica>();
		this.automobili = new ArrayList<Automobil>();
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
				for(ServisnaKnjizica sss : servisne_knjizice) {
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
				int id = Integer.parseInt(linija.trim());
				ArrayList<Servis> servisiii = new ArrayList<Servis>();
				Automobil auto = new Automobil();
				
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
				StatusServisa status_servisa = StatusServisa.valueOf(sp[5]);
				Automobil auto = new Automobil();
				auto.setId(Integer.parseInt(sp[1]));
				Servis servis = new Servis(id, auto, servisr, termin, opis, deloviii, status_servisa);

					for(ServisnaKnjizica csk : servisne_knjizice) {
						if(servis.getAuto_za_servis().getId() == csk.getAutomobil().getId()) {
							csk.getObavljeni_servisi().add(servis);
							servis.setAuto_za_servis(csk.getAutomobil());
						}
				}
				for(Serviser ssss : serviseri) {
					if(ssss.getId() == servisr.getId()) {
						ssss.getServisi_na_koje_je_rasporedjen().add(servis);
						servis.setServiser(ssss);
					}
				}
				
				
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
				Servis namenjen = new Servis();
				Deo delic = new Deo(id, marka, model, naziv, cena, namenjen);

				for(Servis ssss : servisi) {
					if(ssss.getId() == Integer.parseInt(sp[5])){
						delic.setNamenjen_za_servis(ssss);;
						ssss.getUpotrebljeni_delovi().add(delic);
					}
				}
			this.delovi.add(delic);
			
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
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\administratori.txt");
		
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
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\musterije.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Musterija musterijaa : this.musterije) {
			
			String linija_za_upis =String.valueOf(musterijaa.getId()) + "|"  + musterijaa.getIme() + "|" + musterijaa.getPrezime() + "|" + musterijaa.getJmbg() + "|" +
					musterijaa.getPol() + "|" + musterijaa.getAdresa() + "|" + musterijaa.getBroj_telefona() + "|" + musterijaa.getKorisnicko_ime() + "|" + 
					musterijaa.getSifra() + "|" +  String.valueOf(musterijaa.getBroj_nagradnoh_bodova()) + "\n" ;

			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}
	
	public void upisiServisere() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\serviseri.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Serviser serviser : this.serviseri) {
				
			String linija_za_upis =String.valueOf(serviser.getId()) + "|" + serviser.getIme() + "|" + serviser.getPrezime() + "|" + serviser.getJmbg() + "|" +
					serviser.getPol() + "|" + serviser.getAdresa() + "|" + serviser.getBroj_telefona() + "|" + serviser.getKorisnicko_ime() + "|" + 
					serviser.getSifra() + "|" + serviser.getPlata() + "|" + serviser.getSpecijalizacija() + "\n" ;
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		
		}

	public void upisiAutomobile( ) {
	
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\automobili.txt");
		
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
			
			
			File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\delovi.txt");
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
				for(Deo deo : this.delovi) {
				String linija_za_upis = String.valueOf(deo.getId()) + "|" + deo.getMarka() + "|" + deo.getModel() + "|" + deo.getNaziv() + "|" + String.valueOf(deo.getCena())+ "|" + String.valueOf(deo.getNamenjen_za_servis().getId()) + "\n";
				
				bw.write(linija_za_upis);
				}
				bw.close();
				
			} catch (IOException e) {
				
				System.out.println("Nesto nije u redu sa fajlom.");
			}
			
			}
	
	public void upisiServise() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\servisi.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(Servis servis : this.servisi) {
			
			String linija_za_upis = String.valueOf(servis.getId()) + "|" + 
			String.valueOf(servis.getAuto_za_servis().getId()) + "|" + 
			String.valueOf(servis.getServiser().getId()) + "|" 
			+ servis.getTermin() + "|" 
			+ servis.getOpis() + "|" + 
			servis.getStatus_servisa() +"\n";
			
			bw.write(linija_za_upis);
			}
			bw.close();
			
		} catch (IOException e) {
			
			System.out.println("Nesto nije u redu sa fajlom.");
		}
	}

	public void upisiServisneKnjizice() {
		
		
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\3Projekat\\src\\tekstualni_fajlovi\\servisneknjizice.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
			for(ServisnaKnjizica servisna_k : this.servisne_knjizice) {
			
			String linija_za_upis = String.valueOf(servisna_k.getId()) +"\n";
			
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
