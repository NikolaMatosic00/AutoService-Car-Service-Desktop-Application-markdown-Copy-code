package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import moduli.Deo;
import rad_sa_listama.SveListe;

public class ATabelaDelova extends JFrame {

	private String slika_add_putanja = new String(SlikePutanje.slika_plus);
	private String slika_edit_putanja = new String(SlikePutanje.slika_editt);
	private String slika_delete_putanja = new String(SlikePutanje.slika_minus);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private JButton simetricni_deo = new JButton("Napravi simetrican");
	private DefaultTableModel table_model;
	private JTable delovi_tabela;
	
	private SveListe sv;
	
	public ATabelaDelova(SveListe svs) {
		this.sv = svs;
		setTitle("Pregled delova");
		setSize(650, 300);
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
		main_tool_bar.add(simetricni_deo);

		add(main_tool_bar, BorderLayout.SOUTH);
		String[] zaglavlje = new String[] {"ID","Marka", "Model" ,"Naziv", "Cena"};
		Object[][] sadrzaj = new Object[sv.getDelovi().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getDelovi().size(); i ++) {
			Deo deo = sv.getDelovi().get(i);
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
		
		simetricni_deo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = delovi_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati deo za dodavanje simetricnog.", "Greska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					Deo d = sv.NadjiDeo(id);
					String za_upis = "";
					String[] cao = d.getNaziv().split(" ");
					int brojacc = 0;
					for(String rec : cao) {
						if(rec.equalsIgnoreCase("levi")) {
							rec = "desni";
							brojacc ++;
						
						}else if(rec.equalsIgnoreCase("levo")) {
							rec = "desno";
							brojacc ++;

						
						}else if(rec.equalsIgnoreCase("leva")) {
							rec = "desna";
							brojacc ++;

						}else if(rec.equalsIgnoreCase("desni")) {
							rec = "levi";
							brojacc ++;

						
						}else if(rec.equalsIgnoreCase("desno")) {
							rec = "levo";
							brojacc ++;

						
						}else if(rec.equalsIgnoreCase("desna")) {
							rec = "leva";
							brojacc ++;

						}
						
						za_upis += rec + " ";
					}
					if(brojacc == 0) {
						JOptionPane.showMessageDialog(null, "Za izabrani deo ne postoji simentricni", "!", JOptionPane.WARNING_MESSAGE);
					}else {
					sv.getDelovi().add(new Deo(sv.getDelovi().get(sv.getDelovi().size() - 1).getId() + 1, d.getMarka(), d.getModel(), za_upis.substring(0, za_upis.length() - 1), d.getCena(), d.getNamenjen_za_servis()));
					sv.upisiDelove();
					JOptionPane.showMessageDialog(null, "Dodat simetricni deo", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = delovi_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite deo koji zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table_model.getValueAt(red,  0).toString());
					Deo deo = sv.NadjiDeo(id);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi deo: " + deo.getNaziv() + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						sv.getDelovi().remove(deo);
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Deo sa ID: " + id + " obrisan!");
						sv.upisiSve();
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaDeo fda = new AFormaDeo(sv, null);
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
					Deo s = sv.NadjiDeo(Integer.parseInt(idd));
					AFormaDeo fda = new AFormaDeo(sv, s);
					fda.setVisible(true);
				}
				
			}
		});
		
	}
}

