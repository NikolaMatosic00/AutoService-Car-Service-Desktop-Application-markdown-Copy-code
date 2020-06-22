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
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniAdmina;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniServis;
import moduli.Administrator;
import moduli.Servis;
import moduli.Serviser;

public class TabelaServisaMusterijina extends JFrame {

	private String slika_add_putanja = new String("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\slike\\zaPredmete\\plus.png");
	private String slika_edit_putanja = new String("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\slike\\zaPredmete\\edit.png");
	private String slika_delete_putanja = new String("C:\\Users\\Dell\\Desktop\\Java_projekat_dva\\src\\slike\\zaPredmete\\minus.png");

	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable servisi_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaServisaMusterijina(UcitajIzFajla ufs) {
		this.uf = ufs;
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
		ImageIcon delete_icon = new ImageIcon(slika_delete_putanja);
		btnDelete.setIcon(delete_icon);
		
		main_tool_bar.add(btnAdd);
		main_tool_bar.add(btnEdit);
		main_tool_bar.add(btnDelete);

		add(main_tool_bar, BorderLayout.SOUTH);
		String[] zaglavlje = new String[] {"ID", "Auto za servis", "Serviser", "Termin" ,"Opis", "Status servisa"};
		Object[][] sadrzaj = new Object[uf.getServisi().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getServisi().size(); i ++) {
			Servis ser = uf.getServisi().get(i);
			sadrzaj[i][0] = ser.getId();
			sadrzaj[i][1] = ser.getAuto_za_servis().getMarka() + "-" + ser.getAuto_za_servis().getModel();
			sadrzaj[i][2] = ser.getServiser().getPrezime();
			sadrzaj[i][3] = ser.getTermin();
			sadrzaj[i][4] = ser.getOpis();
			sadrzaj[i][5] = ser.getStatus_servisa();
			

		
		
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
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = servisi_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite servis koji zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					Servis ser = uf.NadjiServis(id);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi servis: " + id + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getServisi().remove(ser);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Servis " + id + " obrisan!");
						uf.upisiServise();
					}
					
					
							
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniServis fda = new FormaDodajIzmeniServis(uf, null);
				fda.setVisible(true);
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
					Servis s = uf.NadjiServis(Integer.parseInt(idd));
					FormaDodajIzmeniServis fda = new FormaDodajIzmeniServis(uf, s);
					fda.setVisible(true);
				}
				
			}
		});

	}
}
