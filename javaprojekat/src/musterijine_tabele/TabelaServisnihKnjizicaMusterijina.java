package musterijine_tabele;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import crud.UcitajIzFajla;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniServis;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniServisnkuKnjizicu;
import moduli.Musterija;
import moduli.Servis;
import moduli.ServisnaKnjizica;

public class TabelaServisnihKnjizicaMusterijina extends JFrame {


	private DefaultTableModel table_model;
	private JTable servisne_knjizice_tabela;
	
	private UcitajIzFajla uf;
	private Musterija musterija;
	
	public TabelaServisnihKnjizicaMusterijina(UcitajIzFajla ufs, Musterija m) {
		this.uf = ufs;
		this.musterija = m;
		setTitle("Pregled Servisnih Knjizica");
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
		
	}
	private void initGUI() {

		String[] zaglavlje = new String[] {"ID", "Automobil", "Obavljeni servisi"};
		Object[][] sadrzaj = new Object[musterija.getAutomobili().size()][zaglavlje.length];
		
		for(int i = 0; i < musterija.getAutomobili().size(); i ++) {
			ServisnaKnjizica sk = musterija.getAutomobili().get(i).getServisna_knjizica();
			sadrzaj[i][0] = sk.getId();
			sadrzaj[i][1] = musterija.getAutomobili().get(i).getMarka() + "-" + musterija.getAutomobili().get(i).getModel();
			String obavljeni = " ";
			for(Servis s : sk.getObavljeni_servisi()) {
				obavljeni += s.getOpis() + ",";
			}
			sadrzaj[i][2] = obavljeni.substring(0, obavljeni.length() - 1);	
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		servisne_knjizice_tabela = new JTable(table_model);
		
		servisne_knjizice_tabela.setRowSelectionAllowed(true);
		servisne_knjizice_tabela.setColumnSelectionAllowed(false);
		servisne_knjizice_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisne_knjizice_tabela.setDefaultEditor(Object.class, null);
		servisne_knjizice_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(servisne_knjizice_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
}
