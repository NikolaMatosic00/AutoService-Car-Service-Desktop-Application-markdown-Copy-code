package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumi.StatusServisa;
import moduli.Automobil;
import moduli.Deo;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class SFormaServis extends JFrame {

private JLabel label_auto = new JLabel("Auto za servis: ");
private JLabel label_termin = new JLabel("Termin: ");
private JLabel label_opis = new JLabel("Opis: ");
private JLabel label_status = new JLabel("Status servisa: ");
private JComboBox<String> auto_jcb = new JComboBox<String>();
private JTextField termin = new JTextField(20);
private JTextField opis = new JTextField(20);
private JComboBox<StatusServisa> status_servisa_jcb = new JComboBox<StatusServisa>(StatusServisa.values());
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private SveListe sv;
private Servis servis;
private Serviser serviser;

public SFormaServis(SveListe sv, Servis ser, Serviser serviser) {
	this.sv = sv;
	this.servis = ser;
	this.serviser = serviser;
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][][][]");
	setLayout(layout);
	
	for(Automobil a : sv.getAutomobili()) {
		auto_jcb.addItem(String.valueOf(a.getId()) + ":" + a.getMarka() + "-" + a.getModel());
	}
	
	if(servis != null) {
		popuniPolja();
	}
	
	add(label_auto);
	add(auto_jcb);
	add(label_termin);
	add(termin);
	add(label_opis);
	add(opis);
	if(servis != null) {
	add(label_status);
	add(status_servisa_jcb);
	}
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			SFormaServis.this.dispose();
			SFormaServis.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = sv.getServisi().get(sv.getServisi().size() - 1).getId() + 1;
				
				String autoo = auto_jcb.getSelectedItem().toString();
				int idid = Integer.parseInt(autoo.split(":")[0]);
				Automobil autic = sv.NadjiAutomobil(idid);
				
				String terminn = termin.getText().trim();
				String opiss = opis.getText().trim();
				StatusServisa statuss = StatusServisa.valueOf(status_servisa_jcb.getSelectedItem().toString());
				
				if(servis == null) {
					if(sv.NadjiServis(idd) == null) {
					
						Servis servviss = new Servis(idd, autic, serviser, terminn, opiss, new ArrayList<Deo>(), statuss);
						for(ServisnaKnjizica sk : sv.getServisne_knjizice()) {
							if(sk.getAutomobil().getId() == autic.getId()) {
								sk.getObavljeni_servisi().add(servviss);
								sv.upisiSve();
							}
						}
						sv.getServisi().add(servviss);
						for(Serviser s : sv.getServiseri()) {
							if(s.getKorisnicko_ime().equals(serviser.getKorisnicko_ime())) {
								s.getServisi_na_koje_je_rasporedjen().add(servviss);
							}
						}
						for(Automobil a : sv.getAutomobili()) {
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
					servis.setServiser(serviser);
					servis.setTermin(terminn);
					servis.setOpis(opiss);
					servis.setStatus_servisa(statuss);
					
				}
				sv.upisiSve();
				SFormaServis.this.dispose();
				SFormaServis.this.setVisible(false);
			}
			
		}
	});
}

private void popuniPolja() {
	auto_jcb.setSelectedItem(String.valueOf(servis.getAuto_za_servis().getId())+ ":" + servis.getAuto_za_servis().getMarka() + "-" + servis.getAuto_za_servis().getModel());
	termin.setText(servis.getTermin());
	opis.setText(servis.getOpis());
	status_servisa_jcb.setSelectedItem(servis.getStatus_servisa());


}

private boolean validacija() {
	boolean ok = true;
	String poruka = "Molimo ispravite sledece greske u unosu: \n";
		
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




