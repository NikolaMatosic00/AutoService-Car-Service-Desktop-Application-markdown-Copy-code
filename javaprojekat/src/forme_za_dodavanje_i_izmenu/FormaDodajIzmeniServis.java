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
import enumi.Pol;
import enumi.StatusServisa;
import moduli.Administrator;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;

public class FormaDodajIzmeniServis extends JFrame {

private JLabel label_id = new JLabel("ID: ");
private JLabel label_auto = new JLabel("Auto za servis: ");
private JLabel label_serviser = new JLabel("Serviser: ");
private JLabel label_termin = new JLabel("Termin: ");
private JLabel label_opis = new JLabel("Opis: ");
private JLabel label_status = new JLabel("Status servisa: ");
private JTextField id = new JTextField(20);
private JComboBox<String> auto = new JComboBox<String>();
private JComboBox<String> serviser = new JComboBox<String>();
private JTextField termin = new JTextField(20);
private JTextField opis = new JTextField(20);
private JComboBox<StatusServisa> status = new JComboBox<StatusServisa>(StatusServisa.values());
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private UcitajIzFajla uf;
private Servis servis;

public FormaDodajIzmeniServis(UcitajIzFajla uf, Servis ser) {
	this.uf = uf;
	this.servis = ser;
	if(servis == null) {
		setTitle("Novi Servis");
	}else {
		setTitle("Izmenjivanje Servisa: " + servis.getId());
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][]25[]");
	setLayout(layout);
	
	for(Automobil a : uf.getAutomobili()) {
		auto.addItem(String.valueOf(a.getId()) + ": " + a.getMarka() + "-" + a.getModel());
	}
	for(Serviser s : uf.getServiseri()) {
		serviser.addItem(s.getPrezime());
	}
	
	if(servis != null) {
		popuniPolja();
	}
	
	add(label_id);
	add(id);
	add(label_auto);
	add(auto);
	add(label_serviser);
	add(serviser);
	add(label_termin);
	add(termin);
	add(label_opis);
	add(opis);
	add(label_status);
	add(status);
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			FormaDodajIzmeniServis.this.dispose();
			FormaDodajIzmeniServis.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = Integer.parseInt(id.getText().trim());
				
				String autoo = auto.getSelectedItem().toString();
				int idid = Integer.parseInt(autoo.split(":")[0]);
				Automobil autic = uf.NadjiAutomobil(idid);
				
				String serviserr = serviser.getSelectedItem().toString();
				
				Serviser servonja = uf.NadjiServiseraPoPrezimenu(serviserr);
				
				String terminn = termin.getText().trim();
				String opiss = opis.getText().trim();
				StatusServisa statuss = StatusServisa.valueOf(status.getSelectedItem().toString());
				
				if(servis == null) {
					if(uf.NadjiServis(idd) == null) {
					
						Servis servviss = new Servis(idd, autic, servonja, terminn, opiss, new ArrayList<Deo>(), statuss);
						for(ServisnaKnjizica sk : uf.getServisne_knjizice()) {
							if(sk.getAutomobil().getId() == autic.getId()) {
								sk.getObavljeni_servisi().add(servviss);
								uf.upisiSve();
							}
						}
						uf.getServisi().add(servviss);
						for(Serviser s : uf.getServiseri()) {
							if(s.getKorisnicko_ime() == servonja.getKorisnicko_ime()) {
								s.getServisi_na_koje_je_rasporedjen().add(servviss);
							}
						}
						for(Automobil a : uf.getAutomobili()) {
							if(a.getId() == idid) {
								a.getServisna_knjizica().getObavljeni_servisi().add(servviss);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
					servis.SetId(idd);
					servis.setAuto_za_servis(autic);
					servis.setServiser(servonja);
					servis.setTermin(terminn);;
					servis.setOpis(opiss);;
					
				}
				uf.upisiSve();
				FormaDodajIzmeniServis.this.dispose();
				FormaDodajIzmeniServis.this.setVisible(false);
			}
			
		}
	});
}

private void popuniPolja() {
	id.setText(String.valueOf(servis.getId()));
	auto.setSelectedItem(String.valueOf(servis.getAuto_za_servis().getId()) + servis.getAuto_za_servis().getMarka() + "-" + servis.getAuto_za_servis().getModel());
	serviser.setSelectedItem(servis.getServiser().getPrezime());
	termin.setText(servis.getTermin());
	opis.setText(servis.getOpis());
	status.setSelectedItem(servis.getStatus_servisa());


}

private boolean validacija() {
	boolean ok = true;
	String poruka = "Molimo ispravite sledece greske u unosu: \n";
	try {
		Integer.parseInt(id.getText().trim());
		
	}catch(NumberFormatException e) {
		poruka += "ID mora biti broj\n";
		ok = false;
	}
		
	if(termin.getText().trim().equals("")) {
		poruka += "-Termin\n";
		ok = false;
	}

	if(opis.getText().trim().equals("")) {
		poruka += "-Opis\n";
		ok = false;
	}

		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}

