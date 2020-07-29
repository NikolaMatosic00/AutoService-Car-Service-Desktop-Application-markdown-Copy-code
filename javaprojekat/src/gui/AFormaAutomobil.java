package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumi.Marka;
import enumi.ModelAutomobila;
import enumi.VrstaGoriva;
import moduli.Automobil;
import moduli.Musterija;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class AFormaAutomobil extends JFrame {

private JLabel label_vlasnik = new JLabel("Vlasnik: ");
private JLabel label_marka = new JLabel("Marka: ");
private JLabel label_model = new JLabel("Model: ");
private JLabel label_godiste = new JLabel("Godina proizvodnje: ");
private JLabel label_zapremina = new JLabel("Zapremina u ccm: ");
private JLabel label_snaga = new JLabel("Snaga u ks: ");
private JLabel label_vrsta_goriva = new JLabel("Vrsta goriva: ");
private JButton nova_musterija = new JButton("Nova musterija");
private JComboBox<String> vlasnik = new JComboBox<String>();
private JComboBox<Marka> marka = new JComboBox<Marka>(Marka.values());
private JComboBox<ModelAutomobila> model = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
private JTextField godiste = new JTextField(20);
private JTextField snaga = new JTextField(20);
private JTextField zapremina = new JTextField(20);
private JComboBox<VrstaGoriva> vrsta_goriva = new JComboBox<VrstaGoriva>(VrstaGoriva.values());
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private SveListe sv;
private Automobil automobil;

public AFormaAutomobil(SveListe sv, Automobil au) {
	this.sv = sv;
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][][]25[]");
	setLayout(layout);
	
	for(Musterija m : sv.getMusterije()) {
		vlasnik.addItem(String.valueOf(m.getId()) + ":" + m.getKorisnicko_ime());
		
	}
	
	if(automobil != null) {
		popuniPolja();
	}
	
	
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
	
	
	nova_musterija.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AFormaMusterija af = new AFormaMusterija(sv, null);
			af.setVisible(true);

		}
	});
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AFormaAutomobil.this.dispose();
			AFormaAutomobil.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = sv.getAutomobili().get(sv.getAutomobili().size() - 1).getId();
				Musterija vlasnikk = sv.NadjiMusteriju(Integer.parseInt(vlasnik.getSelectedItem().toString().split(":")[0]));
				Marka markaa = Marka.valueOf(marka.getSelectedItem().toString());
				ModelAutomobila modell = ModelAutomobila.valueOf(model.getSelectedItem().toString());
				String godd = godiste.getText().toString();
				int snagaa = Integer.parseInt(snaga.getText().toString());
				int zapreminaa = Integer.parseInt(zapremina.getText().toString());
				VrstaGoriva vg = VrstaGoriva.valueOf(vrsta_goriva.getSelectedItem().toString());
				
				if(automobil == null) {
					if(sv.NadjiAutomobil(idd) == null) {
						
						Automobil auticcc = new Automobil(idd, vlasnikk, markaa, modell, godd, zapreminaa, snagaa, vg, new ServisnaKnjizica());
						for(Musterija m : sv.getMusterije()) {
							if(m.getKorisnicko_ime().equalsIgnoreCase(vlasnikk.getKorisnicko_ime())) {
								m.getAutomobili().add(auticcc);
							}
						}
						
						sv.upisiMusterije();
						sv.getAutomobili().add(auticcc);
						sv.upisiAutomobile();
						
						
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
				
					
					
					for(Musterija m : sv.getMusterije()) {
						if(m.getKorisnicko_ime().equalsIgnoreCase(vlasnikk.getKorisnicko_ime())) {
							m.getAutomobili().add(new Automobil(idd, vlasnikk, markaa, modell, godd, zapreminaa, snagaa, vg, new ServisnaKnjizica()));
						}
					}
				}
				
				sv.upisiSve();
				AFormaAutomobil.this.dispose();
				AFormaAutomobil.this.setVisible(false);
			
			
		}
		}});
}

private void popuniPolja() {
	vlasnik.setSelectedItem(String.valueOf(automobil.getVlasnik().getId() + ":" + automobil.getVlasnik().getKorisnicko_ime()));
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
		Integer.parseInt(zapremina.getText().trim());
		Integer.parseInt(snaga.getText().trim());
	}catch(NumberFormatException e) {
		poruka += "Zapremina i snaga moraju biti broj\n";
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


