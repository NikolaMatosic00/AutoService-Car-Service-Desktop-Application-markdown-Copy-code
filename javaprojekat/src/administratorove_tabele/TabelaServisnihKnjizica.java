package administratorove_tabele;


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
import moduli.Servis;
import moduli.ServisnaKnjizica;

public class TabelaServisnihKnjizica extends JFrame {

	private String slika_add_putanja = new String(PutanjeZaSlike.slika_plus_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_edit_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_minus_putanja);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable servisne_knjizice_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaServisnihKnjizica(UcitajIzFajla ufs) {
		this.uf = ufs;
		setTitle("Pregled Servisnih Knjizica");
		setSize(500, 300);
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
		String[] zaglavlje = new String[] {"ID", "Automobil", "Obavljeni servisi"};
		Object[][] sadrzaj = new Object[uf.getServisne_knjizice().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getServisne_knjizice().size(); i ++) {
			ServisnaKnjizica sk = uf.getServisne_knjizice().get(i);
			sadrzaj[i][0] = sk.getId();
			sadrzaj[i][1] = sk.getAutomobil().getMarka() + "-" + sk.getAutomobil().getModel();
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
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = servisne_knjizice_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite servisnu knjizicu koju zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					ServisnaKnjizica ser_knj = uf.NadjiServisnuKnjizicu(id);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi servisnu knjizicu?: " + id + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getServisne_knjizice().remove(ser_knj);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Servisna knjizica " + id + " obrisana!");
						uf.upisiServisneKnjizice();
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniServisnkuKnjizicu fda = new FormaDodajIzmeniServisnkuKnjizicu(uf, null);
				fda.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = servisne_knjizice_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					int idd = Integer.parseInt(table_model.getValueAt(red, 0).toString());
					ServisnaKnjizica s = uf.NadjiServisnuKnjizicu(idd);
					FormaDodajIzmeniServisnkuKnjizicu fda = new FormaDodajIzmeniServisnkuKnjizicu(uf, s);
					fda.setVisible(true);
				}
				
			}
		});

	}
}
