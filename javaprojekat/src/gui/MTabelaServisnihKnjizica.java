package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import moduli.Automobil;
import moduli.Musterija;
import moduli.Servis;
import moduli.ServisnaKnjizica;
import rad_sa_listama.SveListe;

public class MTabelaServisnihKnjizica extends JFrame {


	private DefaultTableModel table_model;
	private JTable servisne_knjizice_tabela;
	
	private SveListe sv;
	private Musterija musterija;
	
	public MTabelaServisnihKnjizica(SveListe sv, Musterija m) {
		this.sv = sv;
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
			int id = musterija.getAutomobili().get(i).getServisna_knjizica().getId();
			ServisnaKnjizica sk = sv.NadjiServisnuKnjizicu(id);
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

