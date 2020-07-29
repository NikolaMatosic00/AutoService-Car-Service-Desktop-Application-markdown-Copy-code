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

import moduli.Administrator;
import moduli.Musterija;
import moduli.Servis;
import moduli.Serviser;
import rad_sa_listama.SveListe;

public class ATabelaServisera extends JFrame {

	private String slika_add_putanja = new String(SlikePutanje.slika_add);
	private String slika_edit_putanja = new String(SlikePutanje.slika_edit);
	private String slika_delete_putanja = new String(SlikePutanje.slika_delete);
	
	private JToolBar main_tool_bar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel table_model;
	private JTable serviseri_tabela;
	
	private SveListe sv;
	
	public ATabelaServisera(SveListe sv) {
		this.sv = sv;
		setTitle("Pregled servisera");
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
		String[] zaglavlje = new String[] {"Ime", "Prezime", "Korisnicko ime" ,"Adresa", "Broj Telefona", "Plata", "Specijalizacija"};
		Object[][] sadrzaj = new Object[sv.getServiseri().size()][zaglavlje.length];
		
		for(int i = 0; i < sv.getServiseri().size(); i ++) {
			Serviser ser = sv.getServiseri().get(i);
			sadrzaj[i][0] = ser.getIme();
			sadrzaj[i][1] = ser.getPrezime();
			sadrzaj[i][2] = ser.getKorisnicko_ime();
			sadrzaj[i][3] = ser.getAdresa();
			sadrzaj[i][4] = ser.getBroj_telefona();
			sadrzaj[i][5] = ser.getPlata();
			sadrzaj[i][6] = ser.getSpecijalizacija();

		
		
		}
		
		table_model = new DefaultTableModel(sadrzaj, zaglavlje);
		serviseri_tabela = new JTable(table_model);
		
		serviseri_tabela.setRowSelectionAllowed(true);
		serviseri_tabela.setColumnSelectionAllowed(false);
		serviseri_tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		serviseri_tabela.setDefaultEditor(Object.class, null);
		serviseri_tabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jsp = new JScrollPane(serviseri_tabela);
		add(jsp, BorderLayout.CENTER);
	
	}
	
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int red = serviseri_tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite servisera kog zelite da obrisete.", "Greska!", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnicko_ime = table_model.getValueAt(red,  2).toString();
					Serviser ser = sv.NadjiServisera(korisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, "Obrisi korisnika: " + ser.getIme() + ser.getPrezime() + "?", "Neophodna potvrda.",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						for(Servis s : ser.getServisi_na_koje_je_rasporedjen()) {
							sv.getServisi().remove(s);
						}
						sv.getServiseri().remove(ser);
						sv.upisiSve();
						table_model.removeRow(red);
						JOptionPane.showMessageDialog(null, "Korisnik " + korisnicko_ime + " obrisan!");
					}
					
					
							
				}
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				AFormaServiser fds = new AFormaServiser(sv, null);
				fds.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = serviseri_tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Odaberite red za izmenu.", "jGreska!", JOptionPane.WARNING_MESSAGE);
					
				}else {
					String korisnicko_ime = table_model.getValueAt(red, 2).toString();
					Serviser serv = sv.NadjiServisera(korisnicko_ime);
					AFormaServiser fds = new AFormaServiser(sv, serv);
					fds.setVisible(true);		
					}
			}
		});
		

		}
}

