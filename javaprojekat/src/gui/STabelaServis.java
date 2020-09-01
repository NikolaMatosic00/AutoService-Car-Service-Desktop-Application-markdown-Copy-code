package gui;

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

import moduli.Servis;
import moduli.Serviser;
import rad_sa_listama.SveListe;

public class STabelaServis extends JFrame {

	private String slika_add_putanja = new String(SlikePutanje.slika_plus);
	private String slika_edit_putanja = new String(SlikePutanje.slika_editt);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	
	private DefaultTableModel table_model;
	private JTable servisi_tabela;
	
	private SveListe sv;
	private Serviser serviser;
	
	public STabelaServis(SveListe sv, Serviser s) {
		this.sv = sv;
		this.serviser = s;
		setTitle("Pregled servisa");
		setSize(850, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		
	}
	private void initGUI() {

		ImageIcon add_icon = new ImageIcon(slika_add_putanja);
		btnAdd.setIcon(add_icon);
		ImageIcon edit_icon = new ImageIcon(slika_edit_putanja);
		btnEdit.setIcon(edit_icon);
		
		main_tool_bar.add(btnAdd);
		main_tool_bar.add(btnEdit);

		add(main_tool_bar, BorderLayout.SOUTH);
		String[] zaglavlje = new String[] {"ID", "Auto za servis", "Termin" ,"Opis", "Status servisa"};
		Object[][] sadrzaj = new Object[serviser.getServisi_na_koje_je_rasporedjen().size()][zaglavlje.length];
		
		for(int i = 0; i < serviser.getServisi_na_koje_je_rasporedjen().size(); i ++) {
			Servis ser = serviser.getServisi_na_koje_je_rasporedjen().get(i);
			sadrzaj[i][0] = ser.getId();
			sadrzaj[i][1] = ser.getAuto_za_servis().getMarka() + "-" + ser.getAuto_za_servis().getModel();
			sadrzaj[i][2] = ser.getTermin();
			sadrzaj[i][3] = ser.getOpis();
			sadrzaj[i][4] = ser.getStatus_servisa();
			

		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		servisi_tabela = new JTable(table_model);
		
		servisi_tabela.setRowSelectionAllowed(true);
		servisi_tabela.setColumnSelectionAllowed(false);
		servisi_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		servisi_tabela.setDefaultEditor(Object.class, null);
		servisi_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(servisi_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				SFormaServis sfs = new SFormaServis(sv, null, serviser);
				sfs.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = servisi_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String idd = table_model.getValueAt(red, 0).toString();
					Servis s = sv.NadjiServis(Integer.parseInt(idd));
					SFormaServis sfs = new SFormaServis(sv, s, serviser);
					sfs.setVisible(true);
				}
				
			}
		});

		

}

}
