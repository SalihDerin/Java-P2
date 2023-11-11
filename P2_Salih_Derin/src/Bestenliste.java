import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 * Klasse zum Anzeigen der Bestenliste.
 * @author SalihDerin (525884)
 *
 */

public class Bestenliste extends JPanel

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BestenlisteZeile lies = new BestenlisteZeile();
	private String puffer = "Bestenliste\n\n";
	private Ergebnis ergebnis = new Ergebnis();
	private Ergebnisliste ergebnisliste = new Ergebnisliste();
	private Steuerung steuerung;
	
	private JPanel contentPane;
	private JLabel aufforderung;
	private JTextField namensFeld;
	private JButton knopfNameEingeben, knopfNeuesSpiel;
	private JTextPane listeZeilen;
	
	public Bestenliste()
	  {
		// Hauptfenster-Spezifikationen
	    //setTitle("Bestenliste");
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(500,50,500,500);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    //setContentPane(contentPane);
	    contentPane.setLayout(null);
	    
	    // Knopf-Spezifikationen
	    knopfNeuesSpiel = new JButton("Neues Spiel");
	    knopfNeuesSpiel.setBounds(150, 415, 200, 20);
	    knopfNeuesSpiel.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				neues_Spiel(e);
			}
		});
	    
	    knopfNameEingeben = new JButton("Ergebnis speichern");
	    knopfNameEingeben.setBounds(150, 360, 200, 20);
	    knopfNameEingeben.addMouseListener(new MouseAdapter()
	    {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Name_eingegeben(e);
			}
		});
	    
	    contentPane.add(knopfNeuesSpiel);
	    contentPane.add(knopfNameEingeben);
	    
	    //JLabel-Spezifikationen
	    aufforderung = new JLabel();
	    aufforderung.setBounds(160, 300, 200, 25);
	    aufforderung.setText("Bitte geben Sie Ihren Namen ein:");
	    contentPane.add(aufforderung);
	    
	    // Textfeld-Spezifikationen
	    namensFeld = new JTextField();
	    namensFeld.setBounds(150, 325, 200, 25);
	    contentPane.add(namensFeld);
	    
	    //TextPane-Spezifikationen
	    listeZeilen = new JTextPane();
	    listeZeilen.setBounds(50, 0, 400, 200);
	    listeZeilen.setEditable(false);
	    listeZeilen.setText(puffer);
	    contentPane.add(listeZeilen);
	  }
	
	/**
	 * Mit dieser Methode erfolgt der Sprung vom Zustand "Name eingegeben" 
	 * in den Zustand "Anzeige Bestenliste". 
	 * @param Objekt der Klasse Event
	 */
	public void Name_eingegeben(MouseEvent event)
	{
		ergebnis.name = namensFeld.getText();
		System.out.println(ergebnis.name);
		ergebnisliste.speichere(ergebnis);
		
		steuerung.name_eingegeben();
	}
	
	/**
	 * Mit dieser Methode erfolgt der Sprung vom Zustand "Name eingegeben" 
	 * in den Zustand "Präsentation Ziffernfolge". 
	 * @param event: Objekt der Klasse Event
	 */
	public void neues_Spiel(MouseEvent event)
	{
		namensFeld.setVisible(false);
		knopfNameEingeben.setVisible(false);
		knopfNeuesSpiel.setVisible(false);
		
		steuerung.neues_Spiel();
		puffer = "";
	}
	
	/**
	 * Eine Methode zur Speicherung einer neuen Ergebnisses in der Ergebnisliste.
	 * @param folgenlänge: int-Variable mit der erreichten Punktzahl
	 * @param spielzeit int-Variable mit der benötigten Zeit
	 */
	public void neues_Ergebnis(int folgenlänge, int spielzeit)
	{
		ergebnis.ziffernzahl = folgenlänge;
		ergebnis.zeit = spielzeit;
	}
	
	/**
	 * Aktiviert die Namenseingabe
	 */
	public void aktiviere_Namenseingabe()
	{
		namensFeld.setVisible(true);
		knopfNameEingeben.setVisible(true);
		knopfNeuesSpiel.setVisible(true);
	}
	
	/**
	 * Macht die Bestenliste sichtbar
	 */
	
	// TODO: Ändern, so dass Bestenliste (das obere weiße Panel in das die 10 Zeilen reinkommen) sichtbar gemacht wird; davor unsichtbar.
	public void zeige_Liste_an()
	{
		EventQueue.invokeLater(new Runnable()
	    {
	      @Override
		public void run()
	      {
	        try
	        {
	          sichtbar(true);
	        } catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    });
	}
	
	/**
	 * Mit dieser Methode wird die Sichtbarkeit der Liste geändert.
	 * @param wert: true -> sichtbar; false -> unsichtbar
	 */
	public void sichtbar(boolean wert)
	{
		this.setVisible(wert);
	}
	
	public void melde_Steuerung_an(Steuerung steuerung)
	{
		this.steuerung = steuerung;
	}
	
	/**
	 * Klasse, die die Bestenliste neu schreibt.
	 * @author SalihDerin (525884)
	 *
	 */
	private class BestenlisteZeile
	{
		/**
		 * Zieht einzelne Ergebnisse aus der Ergebnisliste.
		 * @param ergebnis: Objekt der Klasse Ergebnis
		 */
		public void zeige_an (Ergebnis ergebnis)
		{
			puffer += "Name: "+ergebnis.name+"\tZeit: "+ergebnis.zeit+"\tPunkte: "+ergebnis.ziffernzahl+"\n";
			listeZeilen.setText(puffer);
		}
	}
	
	
	/* TODO: - Aufruf der Bestenliste aus Benutzeroberfläche
	 * 		 - Zustandsänderungen implementieren und damit verbundene Methodenaufrufe (aktiviere Namenseingabe, zeige_Liste_an etc.)
	 * 		 - Commit & Push 
	*/ 
	
	
	
	public static void main (String[] args)
	{
		Bestenliste test = new Bestenliste();
		test.zeige_Liste_an();
		test.sichtbar(true);
		Ergebnis ergebnis = new Ergebnis();
		ergebnis.name("Salih");
		ergebnis.zeit(30);
		ergebnis.ziffernzahl(14);
		for (int i=0; i<10; i++)
			test.lies.zeige_an(ergebnis);
	}
	
}
