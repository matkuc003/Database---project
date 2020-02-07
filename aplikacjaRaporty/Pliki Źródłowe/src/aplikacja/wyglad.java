package aplikacja;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class wyglad {

	private JFrame frame;
	private JTextField imie;
	private JTextField nazwisko;
	private JTextField nazwa;
	private JTextField nosnik;
	private JTextField cena;
	private JTextField kara;
	private JTextField data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wyglad window = new wyglad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wyglad() {
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		String url = "jdbc:firebirdsql://localhost:3050/C:/Users/Mati/Desktop/BAZY PROJEKT/db/DB.FDB";
		String user = "SYSDBA";
		String pass = "masterkey";

		frame = new JFrame();
		frame.setBounds(100, 100, 659, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Domyslna", "Sortuj alfabetycznie filmy", "Sortuj po ocenie rosn\u0105co"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setBounds(31, 26, 236, 33);
		frame.getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("Generuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.firebirdsql.jdbc.FBDriver"); //sterowniki do jdbc (odpowiednie dla firebird)
					Connection con = DriverManager.getConnection(url, user, pass); //po³¹czenie z baz¹
					JasperDesign jd = JRXmlLoader
							.load("C:/Users/Mati/Desktop/BAZY PROJEKT/aplikacja/Leaf_Grey_1.jrxml"); //³adowanie raportu
					JasperReport jr = JasperCompileManager.compileReport(jd); //skompilowanie raportu
					HashMap p = new HashMap();
					if (comboBox.getSelectedItem().toString()
							.equals("Sortuj alfabetycznie filmy")) //je¿eli z listy rozwijanej zosta³o wybrane "Sortuj alfabetycznie filmy"
						p.put("SORT", "nazwa"); //dodanie do HashMapy 'nazwa' z kluczem 'SORT'
					else if (comboBox.getSelectedItem().toString()
							.equals("Sortuj po ocenie rosn¹co")) //je¿eli z listy rozwijanej zosta³o wybrane "Sortuj po ocenie rosn¹co"
						p.put("SORT", "ocena"); //dodanie do HashMapy 'ocena' z kluczem 'SORT'
					else
						p.put("SORT", "dlugosc"); //domyœlnie sortuje po d³ugoœci filmu
					JasperPrint jp = JasperFillManager.fillReport(jr, p, con); //wype³nienie raportu parametrami
					JasperViewer viewer = new JasperViewer(jp, false); //wyœwietlenie raportu w programie JasperViewer
					viewer.setTitle("Wypo¿yczalnia - filmy");
					viewer.setVisible(true);
				} catch (SQLException ex) {
					if (ex.getErrorCode() == 1045)
						System.out.println("b³¹d SQL - " + "B³¹d logowania");
					else
						System.out.println("b³¹d SQL - " + ex);
				} catch (JRException ex) {
					System.out.println(ex);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(297, 26, 107, 33);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblRaportOcenaFilmw = new JLabel("Raport Ocena Film\u00F3w");
		lblRaportOcenaFilmw.setBounds(31, 11, 179, 14);
		frame.getContentPane().add(lblRaportOcenaFilmw);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Domyslna"}));
		comboBox_1.setBounds(31, 90, 236, 33);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Domyslna"}));
		comboBox_2.setBounds(31, 156, 236, 33);
		frame.getContentPane().add(comboBox_2);
		
		JButton button = new JButton("Generuj");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("org.firebirdsql.jdbc.FBDriver");
						Connection con = DriverManager.getConnection(url, user, pass);
						JasperDesign jd = JRXmlLoader
								.load("C:/Users/Mati/Desktop/BAZY PROJEKT/aplikacja/Cherry_Landscape_1.jrxml");
						JasperReport jr = JasperCompileManager.compileReport(jd);
						HashMap p = new HashMap();
						JasperPrint jp = JasperFillManager.fillReport(jr, p, con);
						JasperViewer viewer = new JasperViewer(jp, false);
						viewer.setTitle("Wypo¿yczalnia - filmy");
						viewer.setVisible(true);
					} catch (SQLException ex) {
						if (ex.getErrorCode() == 1045)
							System.out.println("b³¹d SQL - " + "B³¹d logowania");
						else
							System.out.println("b³¹d SQL - " + ex);
					} catch (JRException ex) {
						System.out.println(ex);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}

			}
		});
		button.setBounds(297, 95, 107, 33);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Generuj");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.firebirdsql.jdbc.FBDriver");
					Connection con = DriverManager.getConnection(url, user, pass);
					JasperDesign jd = JRXmlLoader
							.load("C:/Users/Mati/Desktop/BAZY PROJEKT/aplikacja/Leaf_Violet.jrxml");
					JasperReport jr = JasperCompileManager.compileReport(jd);
					HashMap p = new HashMap();
					JasperPrint jp = JasperFillManager.fillReport(jr, p, con);
					JasperViewer viewer = new JasperViewer(jp, false);
					viewer.setTitle("Wypo¿yczalnia - filmy");
					viewer.setVisible(true);
				} catch (SQLException ex) {
					if (ex.getErrorCode() == 1045)
						System.out.println("b³¹d SQL - " + "B³¹d logowania");
					else
						System.out.println("b³¹d SQL - " + ex);
				} catch (JRException ex) {
					System.out.println(ex);
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(297, 156, 107, 33);
		frame.getContentPane().add(button_1);
		
		JLabel lblRaportWypozyczenia = new JLabel("Raport Wypozyczenia");
		lblRaportWypozyczenia.setBounds(31, 70, 179, 14);
		frame.getContentPane().add(lblRaportWypozyczenia);
		
		JLabel lblRaportPracownicy = new JLabel("Raport Pracownicy");
		lblRaportPracownicy.setBounds(31, 131, 179, 14);
		frame.getContentPane().add(lblRaportPracownicy);
		
		JLabel lblFormularz = new JLabel("Formularz wypo\u017Cyczenia");
		lblFormularz.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblFormularz.setBounds(148, 214, 315, 33);
		frame.getContentPane().add(lblFormularz);
		
		JLabel lblImieINazwisko = new JLabel("Imie i Nazwisko");
		lblImieINazwisko.setBounds(10, 302, 107, 14);
		frame.getContentPane().add(lblImieINazwisko);
		
		imie = new JTextField();
		imie.setBounds(148, 299, 86, 20);
		frame.getContentPane().add(imie);
		imie.setColumns(10);
		
		nazwisko = new JTextField();
		nazwisko.setBounds(247, 299, 86, 20);
		frame.getContentPane().add(nazwisko);
		nazwisko.setColumns(10);
		
		JLabel lblNazwaFilmu = new JLabel("Nazwa Filmu");
		lblNazwaFilmu.setBounds(10, 327, 107, 14);
		frame.getContentPane().add(lblNazwaFilmu);
		
		JLabel lblNosnik = new JLabel("Nosnik");
		lblNosnik.setBounds(10, 352, 107, 14);
		frame.getContentPane().add(lblNosnik);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(10, 377, 107, 14);
		frame.getContentPane().add(lblCena);
		
		JLabel lblKaraZaPrzetrzymanie = new JLabel("Kara za przetrzymanie");
		lblKaraZaPrzetrzymanie.setBounds(10, 402, 140, 14);
		frame.getContentPane().add(lblKaraZaPrzetrzymanie);
		
		JLabel lblDataWypozyczenia = new JLabel("Data Wypozyczenia");
		lblDataWypozyczenia.setBounds(10, 427, 122, 14);
		frame.getContentPane().add(lblDataWypozyczenia);
		
		nazwa = new JTextField();
		nazwa.setColumns(10);
		nazwa.setBounds(148, 324, 86, 20);
		frame.getContentPane().add(nazwa);
		
		nosnik = new JTextField();
		nosnik.setColumns(10);
		nosnik.setBounds(148, 349, 86, 20);
		frame.getContentPane().add(nosnik);
		
		cena = new JTextField();
		cena.setColumns(10);
		cena.setBounds(148, 374, 86, 20);
		frame.getContentPane().add(cena);
		
		kara = new JTextField();
		kara.setColumns(10);
		kara.setBounds(148, 399, 86, 20);
		frame.getContentPane().add(kara);
		
		data = new JTextField();
		data.setColumns(10);
		data.setBounds(148, 424, 86, 20);
		frame.getContentPane().add(data);
		
		JButton button_2 = new JButton("Generuj");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.firebirdsql.jdbc.FBDriver");
					Connection con = DriverManager.getConnection(url, user, pass);
					JasperDesign jd = JRXmlLoader
							.load("C:/Users/Mati/Desktop/BAZY PROJEKT/aplikacja/Blank_A4.jrxml");
					JasperReport jr = JasperCompileManager.compileReport(jd);
					HashMap p = new HashMap();
					p.put("imie", imie.getText());
					p.put("nazwisko", nazwisko.getText());
					p.put("nazwa", nazwa.getText());
					p.put("nosnik", nosnik.getText());
					p.put("cena", cena.getText());
					p.put("kara", kara.getText());
					p.put("data", data.getText());
					JasperPrint jp = JasperFillManager.fillReport(jr, p, con);
					JasperViewer viewer = new JasperViewer(jp, false);
					viewer.setTitle("Wypo¿yczalnia - filmy");
					viewer.setVisible(true);
				} catch (SQLException ex) {
					if (ex.getErrorCode() == 1045)
						System.out.println("b³¹d SQL - " + "B³¹d logowania");
					else
						System.out.println("b³¹d SQL - " + ex);
				} catch (JRException ex) {
					System.out.println(ex);
				} catch (ClassNotFoundException e1) {
			
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(344, 352, 107, 33);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("Mateusz Kucaj GRUPA 4 110953");
		lblNewLabel.setBounds(421, 430, 212, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
