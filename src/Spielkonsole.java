
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

 /** Spielkonsole fuer das Spiel "Ziffernfolge". 
     Es besteht aus einem Feld von neun Ziffernfeldern, die in verschiedenen Farben
     aufleuchten k�nnen. Der Spieler kan die Zifferfelder mit der Maus
     anklicken, um die memorierte Ziffernreihe einzugeben.*/
 @SuppressWarnings("serial")
public class Spielkonsole extends JPanel
{ private Steuerung steuerung;  
  private Ziffer[][] ziffern=new Ziffer[3][3];
  private Zufallszahl zufallszahlen=new Zufallszahlen(1,9); 
  private Liste ziffernfolge=new VerketteteListe(); 
  private Liste.Iterator iterator=ziffernfolge.erzeuge_Iterator(); 
  
  private static final int abstand=5;
  
  /** Hoehe der Spielkonsole, wird automatisch so eingestellt, dass
     drei Ziffernfelder nebeneinander Platz haben.*/
  public static final int hoehe =abstand*4+Ziffer.groesse*3;
 
  /** Breite der Spielkonsole, wird automatisch so eingestellt, dass
     drei Ziffernfelder �bereinander Platz haben.*/
  static final int breite =abstand*4+Ziffer.groesse*3;  //=170, Ziffer.groesse=50
 
  private int startZeit=0;		
  private int stoppZeit=0;
  
   /** Erzeugt eine neue Spielkonsole.
       Die Abmessungen werden automatisch so eingestellt, dass
       die neun Ziffernfelder genug Platz haben. Die Spielkonsole erzeugt
       eine Steuerung, die erlaubt, das Spiel zu kontrollieren. 
       Ist die Spielkonsole erzeugt, wird der Steuerung der Eintritt des 
       Ereignisses "Spiel gestartet" �bermittelt.*/
  public Spielkonsole()
  { this.setBorder(new LineBorder(new Color(0, 0, 0)));
    this.setBackground(SystemColor.controlHighlight);
    this.setSize(breite,hoehe); 
    this.setLayout(null);
    for(int i=0;i<3;i++)
    { for(int j=0;j<3;j++)
      { ziffern[i][j]=new Ziffer(this);
        int xpos=abstand+j*(abstand+Ziffer.groesse);
        int ypos=abstand+i*(abstand+Ziffer.groesse);
        ziffern[i][j].setLocation(xpos,ypos);
        this.add(ziffern[i][j]);
      } 
    }
  }
  
   /** Der Spielkonsole wird die Steuerung bekannt gemacht, 
       mit der sie zusammenarbeiten soll.
       @param steuerung  Steuerung  */
  public void melde_an(Steuerung steuerung){this.steuerung=steuerung;}    
  
   /**Die Spielkonsole sichtbar oder unsichtbar schalten.*/
  public void sichtbar(boolean sichtbar)
  { //this.setEnabled(); //this.setOpaque()
	this.setVisible(sichtbar); 
  }
  
   /** Die aktuelle Ziffernreihe wird um eine Ziffer verlaengert und 
       ihre Praesentation gestartet.
       Dies bedeutet, dass die zugehoerigen Ziffernfelder 
       nacheinander  blau aufleuchten. 
       Ist die Praesentation beendet, wird der
       Steuerung mitgeteilt, dass dieses Ereignis eingetreten ist.*/
  public void starte_Praesentation_Ziffernfolge()
  { if(iterator.nach_ende()) startZeit=(int)System.currentTimeMillis(); 
	int ziffer=zufallszahlen.naechste();
    ziffernfolge.setze_an_Ende(ziffer);
    iterator.anfang();
    Thread t=new PraesentationsThread(this); 
    t.start();
  } 
  
  private class PraesentationsThread extends Thread
  { private Spielkonsole spielkonsole;
    private PraesentationsThread(Spielkonsole spielkonsole)
    { this.spielkonsole=spielkonsole; }
     @Override
	public void run()
    { for(Liste.Iterator k=spielkonsole.ziffernfolge.erzeuge_Iterator();
          !k.nach_ende();k.weiter())
      { int ziffer=(Integer)k.element();
        int i=(ziffer-1)/3;
        int j=((ziffer)-1)%3;  
        try{Thread.sleep(1000);} catch(InterruptedException e){} 
        this.spielkonsole.ziffern[i][j].leuchte_blau_auf();
      }
      spielkonsole.iterator.anfang();
      steuerung.praesentation_Ziffernfolge_beendet();
    }
  }
  
   /** Teilt der Spielkonsole mit, dass der Spieler ein Ziffernfeld 
       mit der Maus angeklickt hat. Die Spielkonsole leitet dieses 
       Ereignis an seine Steuerung weiter.
       @param ziffer Verweis auf das ausgew�hlte Ziffer-Objekt. */
  public void Ziffer_ausgewaehlt(Ziffer ziffer)
  { steuerung.ziffer_ausgewaehlt(ziffer); } 

   /** Es wird gepr�ft, ob die ausgewaehlte Ziffer korrekt ist.
       Die ausgewaehlte Ziffer wird mit der aktuellen Ziffer der 
       Ziffernreihe verglichen. 
       @param ziffer Verweis auf das ausgewaehlte Ziffer-Objekt 
       @return true, wenn die Ziffer mit der aktuellen uebereinstimmt. */
  public boolean ausgewaehlte_Ziffer_korrekt(Ziffer ziffer)
  { int ziffer_ist=ziffer.wert();
    int ziffer_soll=(Integer)iterator.element();
    boolean richtig_memoriert=(ziffer_ist==ziffer_soll);
    if(!richtig_memoriert) stoppZeit=(int)System.currentTimeMillis();
    return richtig_memoriert;
  }  
  
   /** Die n�chste Sollziffer wird f�r die n�chste 
       �berpr�fung bereitgestellt. */
  public void naechste_Sollziffer()
  { if(!iterator.nach_ende()) iterator.weiter(); } 
  
   /** Prueft, ob die gesamte Ziffernreihe richtig memoriert worden ist.
       @return true, wenn die gesamte Ziffernreihe 
       richtig memoriert worden ist. */
  public boolean alle_Ziffern_memoriert()
  { iterator.weiter();
    boolean alle_memoriert=iterator.nach_ende();
    iterator.zurueck();
    return alle_memoriert;
  }
    
   /** Die aktuelle Ziffernfolge wird geloescht und durch eine neue, noch 
       leere ersetzt. */
  public void beginne_neue_Ziffernfolge()
  { ziffernfolge=new VerketteteListe();
    iterator=ziffernfolge.erzeuge_Iterator();
  }
  
   /** Erfassung der Spielzeit.
       @return die Spielzeit in ms als Integer. */
  public int spielzeit()
  { return (int)(stoppZeit-startZeit); }
  
   /** Anzahl der korrekt memorierten Zahlen.
       @return Laenge der korrekt memorierten Ziffernfolge */
  public int laenge_Ziffernfolge()
  { int laenge_Ziffernfolge=0;
    for(iterator.anfang(); !iterator.nach_ende(); iterator.weiter()) 
    { laenge_Ziffernfolge++; 
    }
    iterator.anfang();
    return (laenge_Ziffernfolge-1);
  }
}