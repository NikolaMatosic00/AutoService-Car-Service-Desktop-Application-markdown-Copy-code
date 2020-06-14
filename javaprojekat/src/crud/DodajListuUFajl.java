package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import moduli.Administrator;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;


import moduli.Administrator;

public class DodajListuUFajl {

	public static void dodajListuAdministratora(ArrayList<Administrator> lista_admina) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\administratori.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		for(Administrator a : lista_admina) {
			String linija_za_upis = String.valueOf(a.getId()) + "|" + a.getIme() + "|" + a.getIme() + "|" + a.getPrezime() + "|" + a.getJmbg() + "|" +
					a.getPol() + "|" + a.getAdresa() + "|" + a.getBroj_telefona() + "|" + a.getKorisnicko_ime() + "|" + 
					a.getSifra() + "|" + a.getPlata() + "\n" ;
			ceo_tekst += linija_za_upis;
		}
		
		 
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
	}
	public static void dodajListuMusterija(ArrayList<Musterija> lista_musterija) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\musterije.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		for(Musterija musterija : lista_musterija) {
			
			String musterijini_automobili_id = "";
			if (musterija.getAutomobili() != null) {
			for(Automobil a : musterija.getAutomobili()) {
				musterijini_automobili_id += String.valueOf(a.getId()) + ",";
			}
			} else {
				musterijini_automobili_id = " ";
			}
			
			String linija_za_upis =String.valueOf(musterija.getId()) + "|" + musterija.getIme() + "|" + musterija.getIme() + "|" + musterija.getPrezime() + "|" + musterija.getJmbg() + "|" +
					musterija.getPol() + "|" + musterija.getAdresa() + "|" + musterija.getBroj_telefona() + "|" + musterija.getKorisnicko_ime() + "|" + 
					musterija.getSifra() + "|" +  String.valueOf(musterija.getBroj_nagradnoh_bodova()) + "|" + musterijini_automobili_id.substring(0, musterijini_automobili_id.length() - 1) + "\n" ;

			ceo_tekst += linija_za_upis;
			
		}
		
		 
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
	}
	
	public static void dodajListuServisera(ArrayList<Serviser> lista_servisera) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\serviseri.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		for(Serviser serviser : lista_servisera) {
			String linija_za_upis =String.valueOf(serviser.getId()) + "|" + serviser.getIme() + "|" + serviser.getPrezime() + "|" + serviser.getJmbg() + "|" +
					serviser.getPol() + "|" + serviser.getAdresa() + "|" + serviser.getBroj_telefona() + "|" + serviser.getKorisnicko_ime() + "|" + 
					serviser.getSifra() + "|" + serviser.getPlata() + "|" + serviser.getSpecijalizacija() + "\n" ;
			ceo_tekst += linija_za_upis;
		}
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
	}
	
	public static void dodajListuServisa(ArrayList<Servis> lista_servisa) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\servisi.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		String upotrebljeni_del = "";
		for(Servis servis : lista_servisa) {
			if(servis.getUpotrebljeni_delovi() != null) {
				for(Deo a : servis.getUpotrebljeni_delovi()) {
					upotrebljeni_del += String.valueOf(a.getId()) + ",";
				}
				} else {
					upotrebljeni_del = " ";
				}
				
				String linija_za_upis = String.valueOf(servis.getId()) + "|" + 
				String.valueOf(servis.getAuto_za_servis().getId()) + "|" +
						String.valueOf(servis.getServiser().getId())
				+ "|" + servis.getTermin() + "|" + servis.getOpis() + "|" +
						upotrebljeni_del.substring(0, upotrebljeni_del.length() - 1) + 
						"|" + servis.getStatus_servisa() +"\n";
				ceo_tekst += linija_za_upis;
		}
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
	}
	
	public static void dodajListuAutomobila(ArrayList<Automobil> lista_automobila) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\automobili.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		for(Automobil auto : lista_automobila) {
			String linija_za_upis = String.valueOf(auto.getId())+ "|" + auto.getVlasnik().getKorisnicko_ime() + "|" + auto.getMarka() + "|" + auto.getModel() + "|" + auto.getGodina_proizvodnje() + "|" +
					String.valueOf(auto.getZapremina_motora()) + "|"
					+ String.valueOf(auto.getSnaga_motora()) + "|" 
					+ auto.getVrsta_goriva() + "|" 
					+ String.valueOf(auto.getServisna_knjizica().getId()) + "\n";
			ceo_tekst += linija_za_upis;
		}
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
	}
	
	public static void dodajListuServisnihKnjizica(ArrayList<ServisnaKnjizica> lista_servisnih_knjizica) {
		try {
		File fajl_out = new File("C:\\Users\\Dell\\Desktop\\javaprojekat\\src\\tekstualni_fajlovi\\servisneknjizice.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fajl_out));
		
		String ceo_tekst = "";
		String uradjeno = "";
		for(ServisnaKnjizica servisna_k : lista_servisnih_knjizica) {
			for(Servis s : servisna_k.getObavljeni_servisi()) {
				uradjeno += s.getOpis() + ",";
			}
			
			String linija_za_upis = String.valueOf(servisna_k.getId()) + "|" + String.valueOf(servisna_k.getAutomobil().getId()) + "|" + "|" +uradjeno.substring(0, uradjeno.length() - 1) +"\n";
			ceo_tekst += linija_za_upis;
		}
		bw.write(ceo_tekst);
		bw.close();
			
		}catch (IOException e) {
			 System.out.println("Nesto nije u redu sa ucitavanjem iz fajla.");
			}
		
		
		
	}
	
	
}
	

