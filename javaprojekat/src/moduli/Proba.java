package moduli;

import java.util.ArrayList;

import crud.DodajListuUFajl;
import crud.UcitajIzFajla;
import enumi.ModelAutomobila;
import enumi.Specijalizacija;

public class Proba {

	public static void main(String[] args) {

		
		ArrayList<Administrator> admini = UcitajIzFajla.admini;
		ArrayList<Musterija> musterije = UcitajIzFajla.musterije;
		ArrayList<Automobil> automobili = UcitajIzFajla.automobili;
		ArrayList<Servis> servisi = UcitajIzFajla.servisi;
		ArrayList<ServisnaKnjizica> servisne_knjizice = UcitajIzFajla.servisne_knjizice;
		ArrayList<Serviser> serviseri = UcitajIzFajla.serviseri;
		ArrayList<Deo> delovi = UcitajIzFajla.delovi;

		System.out.println(servisi);
		servisi.add(new Servis(1, null, null, "21/6/2019(10:40)", "Popravka branika.", null, "U toku."));
		DodajListuUFajl.dodajListuServisa(servisi);
		
//		System.out.println(admini);
//		System.out.println(musterije);
//		System.out.println(automobili);
//		System.out.println(servisi);
//		System.out.println(serviseri);
//		System.out.println(servisne_knjizice);
//		System.out.println(delovi);
//		serviseri.add(new Serviser(1, "Jovan", "Simic", "3487953794539",
//		"muski", "PBD4", "063285791", "neshibacispida", "Landofaeu", "30000",
//		Specijalizacija.AUTO_ELEKTRICAR));
//		DodajListuUFajl.dodajListuServisera(serviseri);
//		System.out.println(serviseri);
//		musterije.add(new Musterija(16, "NijeMiljan", "NijeMijovic", "243223443390842", "muski",
//				"DIR72", "0647352920", "Skdler", "kanalizacija123", 123420, null));
//		DodajListuUFajl.dodajListuMusterija(musterije);
//		System.out.println(musterije);

//		admini.add(new Administrator(7, "NijeNikola", "NijeMatosic", "2432423324", "muski",
//				"NF73a", "0646477555", "aster255", "Nikolica.12", "2432423"));
//		System.out.println(admini);
//
//		DodajListuUFajl.dodajListuAdministratora(admini);
//		
//		System.out.println(admini);

//		System.out.println(UcitajIzFajla.UcitajAdministratore());
//		System.out.println(UcitajIzFajla.ucitajAutomobile());
//		System.out.println(UcitajIzFajla.ucitajDelove());
//		System.out.println(UcitajIzFajla.ucitajMusterije());
//		System.out.println(UcitajIzFajla.ucitajServise());
//		System.out.println(UcitajIzFajla.ucitajServisere());
//		System.out.println(UcitajIzFajla.ucitajServisnaKnjizice());
//
//
//		ModelAutomobila model = ModelAutomobila.valueOf("C1");
//		System.out.println(model);
//		
//		Administrator admin1 = new Administrator(1, "Nikola", "Matosic", "2432423324", "muski",
//				"NF73a", "0646477555", "aster255", "Nikolica.12", "70000");
//		
//
//		Administrator admin2 = new Administrator(2, "Marko", "Matosic", "90910232310", "muski",
//				"BO14", "0646477550", "markomat123", "Nikoletina", "90000");
//		

//		DodavanjeUFajl.dodajAdministratora(admin1);
//		DodavanjeUFajl.dodajAdministratora(admin2);
//		
//		IzmenjivanjeFajla.promeniAdministratora(1, new Administrator(5, "Nikolas", "Matosic", "2432423324", "muski",
//						"NF73a", "0646477555", "aster255", "Nikolica.12", "70000"));
		
//		BrisanjeIzFajla.obrisiAdministratora(2);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		Musterija musterija1 = new Musterija(1, "Miljan", "Mijovic", "243243390842", "muski",
//				"DIR72", "0647352920", "Skdler", "kanalizacija123", 120, null);
//
//		Musterija musterija2 = new Musterija(2, "Uros", "Mijovic", "234980110231", "muski",
//				"DIR72", "064735291", "1dler", "ukidid", 20, null);
//		
//		DodavanjeUFajl.dodajMusteriju(musterija1);		
//		DodavanjeUFajl.dodajMusteriju(musterija2);		
//				
//		IzmenjivanjeFajla.promeniMusteriju(1, new Musterija(5, "Miljke", "Mijovic", "243243390842", "muski",
//						"DIR72", "0647352920", "Skdler", "kanalizacija123", 120, null));
//		
//		BrisanjeIzFajla.obrisiMusteriju(2);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//		
//		Serviser serviser1 = new Serviser(1, "Jovan", "Simic", "3487953794539",
//		"muski", "PBD4", "063285791", "neshibacispida", "Landofaeu", "30000",
//		Specijalizacija.AUTO_ELEKTRICAR); 
//		Serviser serviser2 = new Serviser(2,
//		"Jovan", "Radakovic", "234897101120", "muski", "BO32", "0628754321",
//		"rade14", "RadakJvn", "50000", Specijalizacija.VULKANIZER);
//		
//		DodavanjeUFajl.dodajServisera(serviser1);
//		DodavanjeUFajl.dodajServisera(serviser2);
//		
//		IzmenjivanjeFajla.promeniServisera(1, new Serviser(5, "Jovan", "Simic", "3487953794539", "muski",
//						"PBD4", "063285791", "neshibacispida", "Landofaeu", "30000", Specijalizacija.AUTO_ELEKTRICAR));
//				
//		BrisanjeIzFajla.obrisiServisera(2);		

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//		
//		Automobil auto1 = new Automobil(1, musterija1, enumi.Marka.CITROEN,enumi.ModelAutomobila.C1, "2001", 1200, 100, "Benzin", null);
//		
//		Automobil auto2 = new Automobil(2, musterija2, enumi.Marka.MERCEDES_BENZ,enumi.ModelAutomobila.CLA, "2006", 1999, 150, "Dizel", null);

//		DodavanjeUFajl.dodajAutomobil(auto1);
//		DodavanjeUFajl.dodajAutomobil(auto2);
//
//		IzmenjivanjeFajla.promeniAutomobil(1, new Automobil(5, musterija1, enumi.Marka.CITROEN,enumi.ModelAutomobila.C1, "2005", 1200, 100, "Benzin", null));
//
//		BrisanjeIzFajla.obrisiAutomobil(2);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//		Deo deo1 = new Deo(1, enumi.Marka.CITROEN, enumi.ModelAutomobila.C1, "Branik", 15000);
//		
//		Deo deo2 = new Deo(2, enumi.Marka.MERCEDES_BENZ, enumi.ModelAutomobila.CLA, "Prednji levi far", 5000);

//		DodavanjeUFajl.dodajDeo(deo1);
//		DodavanjeUFajl.dodajDeo(deo2);
//		
//		IzmenjivanjeFajla.promeniDeo(1, new Deo(5, enumi.Marka.CITROEN, enumi.ModelAutomobila.C1, "Branik", 14000));
//		
//		BrisanjeIzFajla.obrisiDeo(2);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//		Servis servis1 = new Servis(1, auto1, serviser1, "21/6/2019(10:40)", "Popravka branika.", null, "U toku.");
//		
//		Servis servis2 = new Servis(2, auto2, serviser2, "22/6/2019(11:30)", "Zamena prednjeg levog fara", null, "Ceka se.");

//		DodavanjeUFajl.dodajServis(servis1);
//		DodavanjeUFajl.dodajServis(servis2);
//		
//		IzmenjivanjeFajla.promeniServis(1, new Servis(5, auto1, serviser1, "21/6/2019(10:40)", "Popravka branika.", null, "U toku."));
//		
//		BrisanjeIzFajla.obrisiServis(2);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//		ServisnaKnjizica ser_knj1 = new ServisnaKnjizica(1, auto1, null);
//
//		ServisnaKnjizica ser_knj2 = new ServisnaKnjizica(2, auto2, null);

//		DodavanjeUFajl.dodajServisnuKnjizicu(ser_knj1);
//		DodavanjeUFajl.dodajServisnuKnjizicu(ser_knj2);
//		
//		IzmenjivanjeFajla.promeniServisnuKnjizicu(1, new ServisnaKnjizica(5, auto1, null));
//		
//		BrisanjeIzFajla.obrisiServisnuKnjizicu(2);
//				

	}

	
}
