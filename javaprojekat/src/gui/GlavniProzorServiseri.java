package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import moduli.Serviser;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class GlavniProzorServiseri extends JFrame {

	private String slika_sei_putanja = new String(SlikePutanje.slika_servis);

	private JButton btn_servisi = new JButton("Moji servisi");
	private JButton logout = new JButton("Odjavi me");
	private SveListe sv;
	private Serviser serviser;
	
	
	public GlavniProzorServiseri(SveListe sv, Serviser serviser) {
		this.sv = sv;
		this.serviser = serviser;
		setSize(380, 150);
		setResizable(true);
		setTitle("Prijavljeni ste kao: " + serviser.getIme() + " " + serviser.getPrezime());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(380, 150));
		GUIMenu();
		initActions();
		
	}
	

	private void GUIMenu() {

		MigLayout ml = new MigLayout();
		setLayout(ml);
		
		ImageIcon slika6 = new ImageIcon(slika_sei_putanja);
		btn_servisi.setIcon(slika6);
		
		btn_servisi.setBorderPainted(false);

		btn_servisi.setFocusPainted(false);

		add(btn_servisi, "grow, push, wrap");
		add(logout, "grow, push");

		
	}
	
	private void initActions() {

		btn_servisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				STabelaServis sts = new STabelaServis(sv, serviser);
				sts.setVisible(true);
				
			}
		});
		
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				GlavniProzorServiseri.this.setVisible(false);
				GlavniProzorServiseri.this.dispose();
				LoginProzor lpr = new LoginProzor(sv);
				lpr.setVisible(true);
				
			}
		});
		
	}

		
}

