package glavni_prozori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import administratorove_tabele.TabelaAdministratora;
import administratorove_tabele.TabelaAutomobila;
import administratorove_tabele.TabelaDelova;
import administratorove_tabele.TabelaMusterija;
import administratorove_tabele.TabelaServisa;
import administratorove_tabele.TabelaServisera;
import administratorove_tabele.TabelaServisnihKnjizica;
import crud.UcitajIzFajla;
import moduli.Administrator;

public class GlavniProzorAdmini extends JFrame {

	private JMenuBar mainMenu = new JMenuBar();
	private JMenu korisnici_menu = new JMenu("Korisnici");
	private JMenuItem administratori = new JMenuItem("Administratori");
	private JMenuItem musterije = new JMenuItem("Musterije");
	private JMenuItem serviseri = new JMenuItem("Serviseri");
	
	private JMenuItem servisi = new JMenuItem("Servisi");
	private JMenuItem automobili = new JMenuItem("Automobili");
	private JMenuItem delovi = new JMenuItem("Delovi");
	private JMenuItem servisne_knjizice = new JMenuItem("Servisne Knjizice");
	
	private UcitajIzFajla uf;
	private Administrator admin;
	
	
	public GlavniProzorAdmini(UcitajIzFajla uf, Administrator admin) {
		this.uf = uf;
		this.admin = admin;
		setSize(500, 200);
		setResizable(true);
		setTitle("Prijavljeni ste kao: " + admin.getKorisnicko_ime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	public void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(korisnici_menu);
		korisnici_menu.add(administratori);
		korisnici_menu.add(musterije);
		korisnici_menu.add(serviseri);
		mainMenu.add(servisi);
		mainMenu.add(automobili);
		mainMenu.add(delovi);
		mainMenu.add(servisne_knjizice);




	}

	public void initActions() {
		administratori.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaAdministratora tabAdm = new TabelaAdministratora(uf);
				tabAdm.setVisible(true);
				
			}
		});
		
		musterije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
 
				TabelaMusterija tabMus = new TabelaMusterija(uf);
				tabMus.setVisible(true);
			}
		});
		
		serviseri.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaServisera tabSer = new TabelaServisera(uf);
				tabSer.setVisible(true);
				
			}
		});
		
		servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaServisa tabServis = new TabelaServisa(uf);
				tabServis.setVisible(true);
				
			}
		});
		
		automobili.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaAutomobila tabAut = new TabelaAutomobila(uf);
				tabAut.setVisible(true);
				
			}
		});
		
		delovi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TabelaDelova tabDel = new TabelaDelova(uf);
				tabDel.setVisible(true);
				
			}
		});
		
		servisne_knjizice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				TabelaServisnihKnjizica tabSerKnj = new TabelaServisnihKnjizica(uf);
				tabSerKnj.setVisible(true);
				
			}
		});
	}
}
