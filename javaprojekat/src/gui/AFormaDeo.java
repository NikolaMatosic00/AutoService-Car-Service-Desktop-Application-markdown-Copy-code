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
import moduli.Deo;
import moduli.Servis;
import net.miginfocom.swing.MigLayout;
import rad_sa_listama.SveListe;

public class AFormaDeo extends JFrame {

private JLabel label_marka = new JLabel("Marka: ");
private JLabel label_model = new JLabel("Model: ");
private JLabel label_naziv = new JLabel("Naziv: ");
private JLabel label_cena = new JLabel("Cena: ");
private JLabel namenjen_za_servis = new JLabel("Namenjen za servis: ");
private JComboBox<String> namenjen_za_servis_box = new JComboBox<String>();
private JComboBox<Marka> marka = new JComboBox<Marka>(Marka.values());
private JComboBox<ModelAutomobila> model = new JComboBox<ModelAutomobila>(ModelAutomobila.values());
private JTextField naziv = new JTextField(20);
private JTextField cena = new JTextField(20);
private JButton btnOK = new JButton("OK");
private JButton btnCancel = new JButton("Cancel");

private SveListe sv;
private Deo deo;

public AFormaDeo(SveListe sv, Deo d) {
	this.sv = sv;
	this.deo = d;
	if(deo == null) {
		setTitle("Novi Deo");
	}else {
		setTitle("Izmenjivanje dela: " + deo.getId());
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
	MigLayout layout = new MigLayout("wrap 2", "[][]","[][][][]25[]");
	setLayout(layout);
	
	for(Servis s : sv.getServisi()) {
		namenjen_za_servis_box.addItem(String.valueOf(s.getId()) + "-" + s.getOpis());
	}
	
	if(deo != null) {
		popuniPolja();
	}
	
	add(label_marka);
	add(marka);
	add(label_model);
	add(model);
	add(label_naziv);
	add(naziv);
	add(label_cena);
	add(cena);
	add(namenjen_za_servis);
	add(namenjen_za_servis_box);
	add(btnOK, "split 2");
	add(btnCancel);

}

private void InitActions() {
	
	btnCancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			AFormaDeo.this.dispose();
			AFormaDeo.this.setVisible(false);
			
		}
	});
	
	btnOK.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(validacija()) {
				int idd = sv.getDelovi().get(sv.getDelovi().size() - 1).getId() + 1;
				Marka markaa = Marka.valueOf(marka.getSelectedItem().toString());
				ModelAutomobila modell = ModelAutomobila.valueOf(model.getSelectedItem().toString());
				String nazivv = naziv.getText().toString().trim();
				int cenaa = Integer.parseInt(cena.getText().toString().trim());
				Servis ss = sv.NadjiServis(Integer.parseInt(namenjen_za_servis_box.getSelectedItem().toString().split("-")[0]));
				if(deo == null) {
					if(sv.NadjiDeo(idd) == null) {
						
						Deo deooo = new Deo(idd, markaa, modell, nazivv, cenaa, ss);
						sv.getDelovi().add(deooo);
						sv.upisiSve();
						
					}else {
						JOptionPane.showMessageDialog(null, "Servis sa upisanim ID-jem vec postoji.");
					}
				}else {
							
					deo.setId(idd);
					deo.setMarka(Marka.valueOf(marka.getSelectedItem().toString()));
					deo.setModel(ModelAutomobila.valueOf(model.getSelectedItem().toString()));
					deo.setNaziv(naziv.getText().toString());
					deo.setCena(Integer.parseInt(cena.getText().toString()));
					deo.setNamenjen_za_servis(sv.NadjiServis(Integer.parseInt(namenjen_za_servis_box.getSelectedItem().toString().split("-")[0])));
					
				}
			
				sv.upisiSve();
				AFormaDeo.this.dispose();
				AFormaDeo.this.setVisible(false);
			
			
		}
		}});
}

private void popuniPolja() {
	marka.setSelectedItem(deo.getMarka());
	model.setSelectedItem(deo.getModel());
	naziv.setText(deo.getNaziv());
	cena.setText(String.valueOf(deo.getCena()));
	namenjen_za_servis_box.setSelectedItem(deo.getNamenjen_za_servis().getId()+"-"+deo.getNamenjen_za_servis().getOpis());
}

private boolean validacija() {
	boolean ok = true;
	String poruka = "Molimo ispravite sledece greske u unosu: \n";
	try {
		Integer.parseInt(cena.getText().trim());
	}catch(NumberFormatException e) {
		poruka += "cena mora biti broj\n";
		ok = false;
	}
	
	if(naziv.getText().trim().equals("")) {
		poruka += "-Naziv\n";
		ok = false;
	}
	if(cena.getText().toString().trim().equals("")) {
		poruka += "-Cena\n";
		ok = false;
	}
		

		if(ok == false) {
		JOptionPane.showMessageDialog(null, poruka, "Prazna polja", JOptionPane.WARNING_MESSAGE);
	}
	return ok;
}
}


