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
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniAutomobil;
import forme_za_dodavanje_i_izmenu.FormaDodajIzmeniDeo;
import moduli.Automobil;
import moduli.Deo;

public class TabelaDelova extends JFrame {

	private String slika_add_putanja = new String(PutanjeZaSlike.slika_plus_putanja);
	private String slika_edit_putanja = new String(PutanjeZaSlike.slika_edit_putanja);
	private String slika_delete_putanja = new String(PutanjeZaSlike.slika_minus_putanja);

	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable delovi_tabela;
	
	private UcitajIzFajla uf;
	
	public TabelaDelova(UcitajIzFajla ufs) {
		this.uf = ufs;
		setTitle("Pregled delova");
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
		String[] zaglavlje = new String[] {"ID","Marka", "Model" ,"Naziv", "Cena"};
		Object[][] sadrzaj = new Object[uf.getDelovi().size()][zaglavlje.length];
		
		for(int i = 0; i < uf.getDelovi().size(); i ++) {
			Deo deo = uf.getDelovi().get(i);
			sadrzaj[i][0] = deo.getId();
			sadrzaj[i][1] = deo.getMarka();
			sadrzaj[i][2] = deo.getModel();
			sadrzaj[i][3] = deo.getNaziv();
			sadrzaj[i][4] = deo.getCena();
	
		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		delovi_tabela = new JTable(table_model);
		
		delovi_tabela.setRowSelectionAllowed(true);
		delovi_tabela.setColumnSelectionAllowed(false);
		delovi_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		delovi_tabela.setDefaultEditor(Object.class, null);
		delovi_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(delovi_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = delovi_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite deo koji zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					Deo deo = uf.NadjiDeo(id);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi deo: " + deo.getNaziv() + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						uf.getDelovi().remove(deo);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Deo sa ID: " + id + " obrisan!");
						uf.upisiDelove();
					}
					
					
							
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				FormaDodajIzmeniDeo fda = new FormaDodajIzmeniDeo(uf, null);
				fda.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = delovi_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String idd = table_model.getValueAt(red, 0).toString();
					Deo s = uf.NadjiDeo(Integer.parseInt(idd));
					FormaDodajIzmeniDeo fda = new FormaDodajIzmeniDeo(uf, s);
					fda.setVisible(true);
				}
				
			}
		});

	}
}
