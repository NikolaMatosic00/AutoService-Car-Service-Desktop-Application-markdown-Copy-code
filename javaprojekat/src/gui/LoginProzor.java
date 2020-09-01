package gui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

import moduli.Administrator;
import moduli.Musterija;
import moduli.Serviser;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class LoginProzor extends JFrame {

	private JButton username = new JButton();
	private JButton password = new JButton();
	private JTextField username_tf = new JTextField(20);
	private JTextField password_tf = new JTextField(20);
	private JButton btn_ok = new JButton();
	private SveListe sv;
	
	private String slika_korisicko_putanja = new String(SlikePutanje.slika_korisnicko);
	private String slika_sifra_putanja = new String(SlikePutanje.slika_sifra);
//	private String slika_korekt_putanja = new String(SlikePutanje.slika_korekt);

	
	
	public LoginProzor(SveListe sv) {
		this.sv = sv;
		setTitle("Prijava");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		getRootPane().setDefaultButton(btn_ok);
	}
	
	private void initGUI(){
		ImageIcon ki = new ImageIcon(slika_korisicko_putanja);
		username.setIcon(ki);
		ImageIcon sif = new ImageIcon(slika_sifra_putanja);
		password.setIcon(sif);
//		ImageIcon korekt = new ImageIcon(slika_korekt_putanja);
	//	btn_ok.setIcon(korekt);
		
		btn_ok.setBorderPainted(false);
		username.setBorderPainted(false);
		password.setBorderPainted(false);
		username.setEnabled(false);
		password.setEnabled(false);
		btn_ok.setOpaque(false);
		btn_ok.setContentAreaFilled(false);
		btn_ok.setBorderPainted(false);
		MigLayout ml = new MigLayout();
		setLayout(ml);
		add(username);
		add(username_tf, "wrap");
		add(password);
		add(password_tf, "wrap");
		add(btn_ok, "skip");

	}
	
	private void initActions() {
				
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String uneto_korisnicko = username_tf.getText().trim();
				String uneta_sifra = password_tf.getText().trim();
				int rez = 0;
				for(Administrator a : sv.getAdmini()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko)) {
						rez += 1;
					}
				}for(Musterija a : sv.getMusterije()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko)) {
						rez += 1;
					}
				}for(Serviser a : sv.getServiseri()) {
					if(a.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko)) {
						rez += 1;
					}
				}

				if(rez == 0) {
					JOptionPane.showMessageDialog(null, "Uneseni podaci se ne podudaraju ni sa jednim korisnikom.", "Greska prilikom unsa!", JOptionPane.WARNING_MESSAGE);
				}
				if(uneto_korisnicko.equals("") || uneta_sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Ostavili ste prazno polje", "Greska prilikom unosa!", JOptionPane.WARNING_MESSAGE);

					
				}else {
					for(Administrator a : sv.getAdmini()) {
						if(a.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko) && a.getSifra().equals(uneta_sifra)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorAdmini gpa = new GlavniProzorAdmini(sv, a);
							gpa.setVisible(true);
						}
					}for(Musterija m : sv.getMusterije()) {
						if(m.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko) && m.getSifra().equals(uneta_sifra)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorMusterije gpm = new GlavniProzorMusterije(sv, m);
							gpm.setVisible(true);
						}
					}for(Serviser s : sv.getServiseri()) {
						if(s.getKorisnicko_ime().equalsIgnoreCase(uneto_korisnicko) && s.getSifra().equals(uneta_sifra)) {
							LoginProzor.this.dispose();
							LoginProzor.this.setVisible(false);
							GlavniProzorServiseri gms = new GlavniProzorServiseri(sv, s);
							gms.setVisible(true);
							
						}
					}
				}
				
				
			}

		});
	}
}
