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

import moduli.Automobil;
import moduli.Servis;
import moduli.ServisnaKnjizica;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class AFormaServisnaKnjizica extends JFrame {

private JLabel label_automobil = new JLabel("Automobil: ");
private JComboBox<String> automobil = new JComboBox<String>();
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private SveListe sv;
private ServisnaKnjizica servisna_knjizica;
private int brojac_za_izlaz = 0;

public AFormaServisnaKnjizica(SveListe sv, ServisnaKnjizica sk) {
	this.sv = sv;
	this.servisna_knjizica = sk;
	if(servisna_knjizica == null) {
		setTitle("Nova servisna knjizica");
	}else {
		setTitle("Izmenjivanje servisne knjizice: " + servisna_knjizica.getId());
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[]25[]");
	setLayout(layout);
	
	for(Automobil a : sv.getAutomobili()) {
		if(a.getServisna_knjizica().getId() == 0) {
			automobil.addItem(String.valueOf(a.getId()) + "-" + a.getModel());
			brojac_za_izlaz ++;
		}
	}
	
	
	if(brojac_za_izlaz != 0) {

	add(label_automobil);
	add(automobil);
	add(btnOK, "split 2");
	add(btnCancel);
	}else {

			JOptionPane.showMessageDialog(null, "Svi automobili vec imaju servisne knjizice.");
			
		}
	}


private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AFormaServisnaKnjizica.this.dispose();
			AFormaServisnaKnjizica.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = sv.getServisne_knjizice().get(sv.getServisne_knjizice().size() - 1).getId() + 1;
				Automobil auto = sv.NadjiAutomobil(Integer.parseInt(automobil.getSelectedItem().toString().split("-")[0]));
				
				if(servisna_knjizica == null) {
					if(sv.NadjiServisnuKnjizicu(idd) == null) {
						
						ServisnaKnjizica serv_knj = new ServisnaKnjizica(idd, auto, new ArrayList<Servis>());
						sv.getServisne_knjizice().add(serv_knj);
						for(Automobil au : sv.getAutomobili()) {
							if(au.getId() == auto.getId()) {
								au.setServisna_knjizica(serv_knj);
							}
						}
						sv.upisiSve();
						
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
							
					servisna_knjizica.SetId(idd);
					servisna_knjizica.setAutomobil(auto);
					
					
					for(Automobil au : sv.getAutomobili()) {
						if(au.getId() == auto.getId()) {
							au.setServisna_knjizica(servisna_knjizica);
						}
					}
				}
			
				sv.upisiSve();
				AFormaServisnaKnjizica.this.dispose();
				AFormaServisnaKnjizica.this.setVisible(false);
			
			
		}
		}});
}


private boolean validacija() {
	boolean ok = true;
	String poruka = "Molimo ispravite sledece greske u unosu: \n";
		

		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}


