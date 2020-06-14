package crud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import enumi.Marka;
import enumi.ModelAutomobila;
import enumi.Specijalizacija;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;

public class UcitajIzFajla {
	
	
	public static ArrayList<Administrator> admini = UcitajAdministratore();
	public static ArrayList<Musterija> musterije = ucitajMusterije();
	public static ArrayList<Automobil> automobili = ucitajAutomobile();
	public static ArrayList<ServisnaKnjizica> servisne_knjizice = ucitajServisnaKnjizice();
	public static ArrayList<Servis> servisi = ucitajServise();
	public static ArrayList<Serviser> serviseri = ucitajServisere();
	public static ArrayList<Deo> delovi = ucitajDelove();

	
	
	public static ArrayList<Administrator> UcitajAdministratore() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\administratori.txt");
		ArrayList<Administrator> lista_admina = new ArrayList<Administrator>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				String pol = sp[4];
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				String plata = sp[9];
			Administrator adm = new Administrator(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, plata);
			lista_admina.add(adm);
			}
			br.close();
		return lista_admina;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_admina;
	}
	
	public static ArrayList<Musterija> ucitajMusterije() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\musterije.txt");
		ArrayList<Musterija> lista_musterija = new ArrayList<Musterija>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				String pol = sp[4];
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				int br_bod = Integer.parseInt(sp[9]);
				ArrayList<Automobil> lista_auta = new ArrayList<Automobil>();
				
				

			Musterija mus = new Musterija(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, br_bod, lista_auta);
			lista_musterija.add(mus);
			}
			br.close();
		return lista_musterija;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_musterija;
	}
	
	public static ArrayList<Automobil> ucitajAutomobile() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\automobili.txt");
		ArrayList<Automobil> lista_automobila = new ArrayList<Automobil>();
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
				String vrsta_goriva = sp[7];
				ServisnaKnjizica ser_knj = new ServisnaKnjizica();
				for(Musterija m : musterije) {
					if(sp[1].equalsIgnoreCase(m.getKorisnicko_ime())) {
						vlasnik = m;
						Automobil autic = new Automobil(id, vlasnik, marka, model, godina_pr, zapremina, snaga, vrsta_goriva, ser_knj);
						m.dodajNaListuAutomobila(autic);
						
					}
						
				}


			Automobil auto = new Automobil(id, vlasnik, marka, model, godina_pr, zapremina, snaga,
					vrsta_goriva, ser_knj);
			
			lista_automobila.add(auto);
			}
			br.close();
		return lista_automobila;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_automobila;
	}
	
	public static ArrayList<ServisnaKnjizica> ucitajServisnaKnjizice() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\servisneknjizice.txt");
		ArrayList<ServisnaKnjizica> lista_servisa = new ArrayList<ServisnaKnjizica>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				ArrayList<Servis> servisi = new ArrayList<Servis>();
				Automobil auto = new Automobil();
				for(Automobil a : automobili) {
					if(a.getId() == id) {
						auto = a;
						a.setServisna_knjizica(new ServisnaKnjizica(id, auto, servisi));
					}
				}
// KASNIJE OBAVEZNO STAVITI AUTOMOBILOVOJ SERVISNOJ KNJIZICI SERVISE
			ServisnaKnjizica ser_knj = new ServisnaKnjizica(id, auto, servisi);
			lista_servisa.add(ser_knj);
			}
			br.close();
		return lista_servisa;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_servisa;
	}
	
	
	public static ArrayList<Servis> ucitajServise() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\servisi.txt");
		ArrayList<Servis> lista_servisa = new ArrayList<Servis>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				Serviser servisr = new Serviser();
				for(Serviser serviser : ucitajServisere()) {
					if(serviser.getId() == Integer.parseInt(sp[2]))
						servisr = serviser;
				}
				String termin = sp[3];
				String opis = sp[4];
				ArrayList<Deo> delovi = new ArrayList<Deo>();
				for(Deo d : ucitajDelove()) {
					for(String svkai : sp[5].strip().split(",")) {
						if (d.getId() == Integer.parseInt(svkai)) {
							delovi.add(d);
						}
					}
				}
				String status_servisa = sp[6];
				Automobil auto = new Automobil();
				for(ServisnaKnjizica m : servisne_knjizice) {
					if(m.getAutomobil().getId() == Integer.parseInt(sp[1])) {
						auto = m.getAutomobil();
						m.dodajNaListuServisa(new Servis(id, auto, servisr, termin, opis, delovi, status_servisa));
					for(Musterija v : musterije) {
						for(Automobil ab : v.getAutomobili()) {
							if(ab.getId() == m.getAutomobil().getId()) {
								ab.setServisna_knjizica(m);
							}
						}
					}
					}
						
				}
			Servis servis = new Servis(id, auto, servisr, termin, opis, delovi, status_servisa);
			lista_servisa.add(servis);
			}
			br.close();
		return lista_servisa;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_servisa;
	}
	
	public static ArrayList<Serviser> ucitajServisere() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\serviseri.txt");
		ArrayList<Serviser> lista_servisera = new ArrayList<Serviser>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fajl_in));
			String linija;
			while((linija = br.readLine()) != null) {
				String[] sp = linija.split("\\|");
				int id = Integer.parseInt(sp[0]);
				String ime = sp[1];
				String prezime = sp[2];
				String jmbg = sp[3];
				String pol = sp[4];
				String adresa = sp[5];
				String broj = sp[6];
				String korisnicko = sp[7];
				String sifra = sp[8];
				String plata = sp[9];
				Specijalizacija spec = Specijalizacija.valueOf(sp[10]);
			Serviser ser = new Serviser(id, ime, prezime, jmbg, pol, adresa, broj, korisnicko, sifra, plata, spec);
			lista_servisera.add(ser);
			}
			br.close();
		return lista_servisera;

		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_servisera;
	}
	
	public static ArrayList<Deo> ucitajDelove() {
		File fajl_in = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\delovi.txt");
		ArrayList<Deo> lista_delova = new ArrayList<Deo>();
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
			lista_delova.add(delic);
			}
			br.close();
		return lista_delova;
		
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa fajlom.");
		}
		return lista_delova;
	}
	
}
