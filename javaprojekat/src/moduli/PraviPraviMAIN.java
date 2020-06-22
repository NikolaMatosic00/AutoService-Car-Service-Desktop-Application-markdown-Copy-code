package moduli;

import crud.UcitajIzFajla;

public class PraviPraviMAIN {
	
	private static String putanjaAdmini = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\administratori.txt";
	private static String putanjaMusterije = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\musterije.txt";
	private static String putanjaAutomobili = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\automobili.txt";
	private static String putanjaServisneKnjizice = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\servisneknjizice.txt";
	private static String putanjaServisi = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\servisi.txt";
	private static String putanjaServiseri = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\serviseri.txt";
	private static String putanjaDelovi = "C:\\Users\\Dell\\git\\UOP\\javaprojekat\\src\\tekstualni_fajlovi\\delovi.txt";

	public static void main(String[] args) {


		UcitajIzFajla uf = new UcitajIzFajla();
		uf.UcitajAdministratore(putanjaAdmini);
		uf.ucitajMusterije(putanjaMusterije);
		uf.ucitajAutomobile(putanjaAutomobili);
		uf.ucitajServisnaKnjizice(putanjaServisneKnjizice);
		uf.ucitajServise(putanjaServisi);
		uf.ucitajServisere(putanjaServiseri);
		uf.ucitajDelove(putanjaDelovi);
		
		
		LoginProzor lp = new LoginProzor(uf);
		lp.setVisible(true);
	}

}
