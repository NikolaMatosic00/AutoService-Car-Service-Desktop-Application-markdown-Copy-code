package forme_za_dodavanje_i_izmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import crud.UcitajIzFajla;
import enumi.Marka;
import enumi.ModelAutomobila;
import enumi.Pol;
import enumi.StatusServisa;
import enumi.VrstaGoriva;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;

public class FormaDodajIzmeniAutomobil extends JFrame {

private JLabel label_id = new JLabel("ID: ");
private JLabel label_vlasnik = new JLabel("Vlasnik: ");
private JLabel label_marka = new JLabel("Marka: ");
private JLabel label_model = new JLabel("Model: ");
private JLabel label_godiste = new JLabel("Godina proizvodnje: ");
private JLabel label_zapremina = new JLabel("Zapremina u ccm: ");
private JLabel label_snaga = new JLabel("Snaga u ks: ");
private JLabel label_vrsta_goriva = new JLabel("Vrsta goriva: ");
private JTextField id = new JTextField(20);
private JComboBox<String> vlasnik = new JComboBox<String>();
private JComboBox<Marka> marka = new JComboBox<Marka>(Marka.values());
private JComboBox<ModelAutomobila> model = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
private JTextField godiste = new JTextField(20);
private JTextField snaga = new JTextField(20);
private JTextField zapremina = new JTextField(20);
private JComboBox<VrstaGoriva> vrsta_goriva = new JComboBox<VrstaGoriva>(VrstaGoriva.values());
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private UcitajIzFajla uf;
private Automobil automobil;

public FormaDodajIzmeniAutomobil(UcitajIzFajla uf, Automobil au) {
	this.uf = uf;
	this.automobil = au;
	if(automobil == null) {
		setTitle("Novi Automobil");
	}else {
		setTitle("Izmenjivanje Automobila: " + automobil.getId()+ ": " + automobil.getMarka() + "-" + automobil.getModel());
	}
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(false);
	setSize(400, 600);
	InitGUI();
	InitActions();
	pack();
}

private void InitGUI() {
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][][][]25[]");
	setLayout(layout);
	
	for(Musterija m : uf.getMusterije()) {
		vlasnik.addItem(String.valueOf(m.getId()) + ":" + m.getKorisnicko_ime());
		
	}
	
	if(automobil != null) {
		popuniPolja();
	}
	
	add(label_id);
	add(id);
	add(label_vlasnik);
	add(vlasnik);
	add(label_marka);
	add(marka);
	add(label_model);
	add(model);
	add(label_godiste);
	add(godiste);
	add(label_zapremina);
	add(zapremina);
	add(label_snaga);
	add(snaga);
	add(label_vrsta_goriva);
	add(vrsta_goriva);
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			FormaDodajIzmeniAutomobil.this.dispose();
			FormaDodajIzmeniAutomobil.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = Integer.parseInt(id.getText().trim());
				Musterija vlasnikk = uf.NadjiMusteriju(Integer.parseInt(vlasnik.getSelectedItem().toString().split(":")[0]));
				Marka markaa = Marka.valueOf(marka.getSelectedItem().toString());
				ModelAutomobila modell = ModelAutomobila.valueOf(model.getSelectedItem().toString());
				String godd = godiste.getText().toString();
				int snagaa = Integer.parseInt(snaga.getText().toString());
				int zapreminaa = Integer.parseInt(zapremina.getText().toString());
				VrstaGoriva vg = VrstaGoriva.valueOf(vrsta_goriva.getSelectedItem().toString());
				
				if(automobil == null) {
					if(uf.NadjiAutomobil(idd) == null) {
						
						Automobil auticcc = new Automobil(idd, vlasnikk, markaa, modell, godd, zapreminaa, snagaa, vg, new ServisnaKnjizica());
						for(Musterija m : uf.getMusterije()) {
							if(m.getKorisnicko_ime().equalsIgnoreCase(vlasnikk.getKorisnicko_ime())) {
								m.getAutomobili().add(auticcc);
							}
						}
						
						uf.upisiMusterije();
						uf.getAutomobili().add(auticcc);
						uf.upisiAutomobile();
						
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
							
					automobil.setId(idd);
					automobil.setVlasnik(vlasnikk);
					automobil.setMarka(markaa);
					automobil.setModel(modell);
					automobil.setGodina_proizvodnje(godd);
					automobil.setZapremina_motora(zapreminaa);
					automobil.setSnaga_motora(snagaa);
					automobil.setVrsta_goriva(vg);
				
					
					
					for(Musterija m : uf.getMusterije()) {
						if(m.getKorisnicko_ime().equalsIgnoreCase(vlasnikk.getKorisnicko_ime())) {
							m.getAutomobili().add(new Automobil(idd, vlasnikk, markaa, modell, godd, zapreminaa, snagaa, vg, new ServisnaKnjizica()));
						}
					}
				}
				
				uf.upisiSve();
				FormaDodajIzmeniAutomobil.this.dispose();
				FormaDodajIzmeniAutomobil.this.setVisible(false);
			
			
		}
		}});
}

private void popuniPolja() {
	id.setText(String.valueOf(automobil.getId()));
	vlasnik.setSelectedItem(String.valueOf(automobil.getVlasnik().getId()));
	marka.setSelectedItem(automobil.getMarka());
	model.setSelectedItem(automobil.getModel());
	godiste.setText(automobil.getGodina_proizvodnje());
	zapremina.setText(String.valueOf(automobil.getZapremina_motora()));
	snaga.setText(String.valueOf(automobil.getSnaga_motora()));
	vrsta_goriva.setSelectedItem(automobil.getVrsta_goriva());

}

private boolean validacija() {
	boolean ok = true;
	String poruka = "Molimo ispravite sledece greske u unosu: \n";
	try {
		Integer.parseInt(id.getText().trim());
		Integer.parseInt(zapremina.getText().trim());
		Integer.parseInt(snaga.getText().trim());
	}catch(NumberFormatException e) {
		poruka += "ID, zapremina i snaga moraju biti broj\n";
		ok = false;
	}
		
	if(godiste.getText().trim().equals("")) {
		poruka += "-Godiste\n";
		ok = false;
	}


		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}

