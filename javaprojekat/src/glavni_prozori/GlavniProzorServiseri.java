package glavni_prozori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import administratorove_tabele.TabelaServisa;
import crud.UcitajIzFajla;
import moduli.Serviser;
import serviserove_tabele.TabelaServisaServiserova;

public class GlavniProzorServiseri extends JFrame {

	private JMenuBar mainMenu = new JMenuBar();
	private JMenuItem servisi = new JMenuItem("Servisi");
	
	private UcitajIzFajla uf;
	private Serviser serviser;
	
	
	public GlavniProzorServiseri(UcitajIzFajla uf, Serviser serviser) {
		this.uf = uf;
		this.serviser = serviser;
		setSize(500, 200);
		setResizable(true);
		setTitle("Prijavljeni ste kao: " + serviser.getKorisnicko_ime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	public void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(servisi);




	}

	public void initActions() {
				servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TabelaServisaServiserova tabServis = new TabelaServisaServiserova(uf, serviser);
				tabServis.setVisible(true);
				
			}
		});
	}
}
