
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/** Stellt eine Ziffer graphisch in einem Feld dar. Das Zifferfeld kann in 
  drei verschiedenen Farben aufleuchten. Das Ziffernfeld reagiert auf 
  Mausklicks.*/
@SuppressWarnings("serial")
public class Ziffer extends JLabel
{   
   /** Groesse des Feldes, in der die Ziffer dargestellt wird [Pixel]*/
   public static final int groesse=50;
   private static int anzahl=0;
   private int ziffer;
   private Spielkonsole spielkonsole; // Spielkonsole, zu der das Ziffernfeld gehoert.
   
  /** Erzeugt ein Feld, das eine Ziffer darstellt. Das erste Feld stellt
      die Ziffer 1 dar, das zweite, das erzeugt wird, die Ziffer 2 usw.
      Wird das Ziffernfeld mit der linken Maustaste angeklickt, wird die
      Spielkonsole, zu der das Ziffernfeld gehoert, dar�ber informiert.
      @param spielkonsole Spielkonsole zu der das Ziffernfeld gehoert.*/
  public Ziffer(Spielkonsole spielkonsole)
  { anzahl++;
    ziffer=anzahl;
    this.spielkonsole=spielkonsole;
    //Graphik-Eigenschaften setzen
    this.setHorizontalAlignment(SwingConstants.CENTER);
    this.setFont(new Font("Arial", Font.PLAIN, 40));
    this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    this.setSize(groesse,groesse);   
    this.setText(Integer.toString(ziffer));
    this.setOpaque(true);
    this.setBackground(SystemColor.controlHighlight);
    this.addMouseListener(new MouseAdapter() 
    { @Override
	  public void mouseClicked(MouseEvent e) 
      { Ziffer ziffer=(Ziffer)e.getSource();
        ziffer.spielkonsole.Ziffer_ausgewaehlt(ziffer);
      }
    });
  } 
  
  /** Laesst das Feld, das die Ziffer enthaelt, rot aufleuchten. */
  public void leuchte_rot_auf()
  { Thread t=new AufleuchtenThread(Color.RED);
    t.start();
  }

  /** Laesst das Feld, das die Ziffer enthaelt, gruen aufleuchten. */
  public void leuchte_gruen_auf()
  { Thread t=new AufleuchtenThread(Color.GREEN);
    t.start();    
  }

  /** Laesst das Feld, das die Ziffer enthaelt, blau aufleuchten. */  
  public void leuchte_blau_auf()
  { Thread t=new AufleuchtenThread(Color.BLUE);
    t.start();    
  }
  
  private class AufleuchtenThread extends Thread
  { private Color farbe;
  
    private AufleuchtenThread(Color farbe)
    { this.farbe=farbe;
    }
  
    @Override
	public void run()
    { setBackground(farbe);
      try{Thread.sleep(1000);}
      catch(InterruptedException e){}
      setBackground(SystemColor.controlHighlight);
    }
  }

  /** Gibt den Wert der dargestellten Ziffer zur�ck .
      @return Wert der dargestellten Ziffer.*/
  public int wert(){return ziffer;}
  
}
