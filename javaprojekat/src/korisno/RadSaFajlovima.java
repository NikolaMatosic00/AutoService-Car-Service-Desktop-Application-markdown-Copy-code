package korisno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import korisnici.Administrator;
import korisnici.Musterija;
import korisnici.Serviser;

public class RadSaFajlovima {
	
	public static ArrayList<Administrator> ucitajAdministratore() {
		ArrayList<Administrator> administratori = new ArrayList<Administrator>();
		try {
			File fajl = new File("src/txt/administratori.txt");
			BufferedReader rider = new BufferedReader(new FileReader(fajl));
			String linija;
			while((linija = rider.readLine()) != null) {
				String[] splitic = linija.split("//|");
				String ime = splitic[0];
				String prezime = splitic[1];
				String JMBG = splitic[2];
				String pol = splitic[3];
				String adresa = splitic[4];
				String broj_telefona = splitic[5];
				String korisnicko_ime = splitic[6];
				String sifra = splitic[7];
				String plata = splitic[8];
				Administrator administrator = new Administrator(ime, prezime, JMBG, pol, adresa, broj_telefona, korisnicko_ime, sifra, plata);
				administratori.add(administrator);
				
			}
			rider.close();
			
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa citanjem iz fajla.");
		}

				
		return administratori;
	}
	
	public static ArrayList<Musterija> ucitajMusterije() {
		ArrayList<Musterija> musterije = new ArrayList<Musterija>();
		try {
			File fajl = new File("src/txt/musterije.txt");
			BufferedReader rider = new BufferedReader(new FileReader(fajl));
			String linija;
			while((linija = rider.readLine()) != null) {
				String[] splitic = linija.split("//|");
				String ime = splitic[0];
				String prezime = splitic[1];
				String JMBG = splitic[2];
				String pol = splitic[3];
				String adresa = splitic[4];
				String broj_telefona = splitic[5];
				String korisnicko_ime = splitic[6];
				String sifra = splitic[7];
				String broj_sakupljenih_nagradnih_bodova = splitic[8];
				Musterija musterija = new Musterija(ime, prezime, JMBG, pol, adresa, broj_telefona, korisnicko_ime, sifra, broj_sakupljenih_nagradnih_bodova);
				musterije.add(musterija);
				
			}
			rider.close();
			
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa citanjem iz fajla.");
		}

				
		return musterije;
	}
	

	public static ArrayList<Serviser> ucitajServisere() {
		ArrayList<Serviser> serviseri = new ArrayList<Serviser>();
		try {
			File fajl = new File("src/txt/serviseri.txt");
			BufferedReader rider = new BufferedReader(new FileReader(fajl));
			String linija;
			while((linija = rider.readLine()) != null) {
				String[] splitic = linija.split("//|");
				String ime = splitic[0];
				String prezime = splitic[1];
				String JMBG = splitic[2];
				String pol = splitic[3];
				String adresa = splitic[4];
				String broj_telefona = splitic[5];
				String korisnicko_ime = splitic[6];
				String sifra = splitic[7];
				String plata = splitic[8];
				String specijalizacija = splitic[9];
				Serviser serviser = new Serviser(ime, prezime, JMBG, pol, adresa, broj_telefona, korisnicko_ime, sifra, plata, specijalizacija);
				serviseri.add(serviser);
				
			}
			rider.close();
			
		} catch (IOException e) {
			System.out.println("Nesto nije u redu sa citanjem iz fajla.");
		}

				
		return serviseri;
	}
	
}
