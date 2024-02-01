//Test Ingo0815

public class Steuerung
{ private Spielkonsole spielkonsole;
  private Bestenliste bestenliste;
  
  // Zustaende
  private Start start=new Start();
  private Praesentation_Ziffernfolge praesentation_ziffernfolge=new Praesentation_Ziffernfolge();
  private Memorieren_Ziffernfolge memorieren_ziffernfolge=new Memorieren_Ziffernfolge();
  private Eingabe_Name eingabe_name=new Eingabe_Name();
  private Anzeige_Bestenliste anzeige_bestenliste=new Anzeige_Bestenliste();
  
  // aktueller Zustand der Spielkonsole.
  private Zustand zustand=start; 
  
   /** Erzeugt eine Steuerung fuer eine Spielkonsole.
       @param spielkonsole Spielkonsole, welche gesteuert werden soll.
       @param bestenliste Bestenliste auf welche zugegriffen wird. */
  public Steuerung(Spielkonsole spielkonsole, Bestenliste bestenliste)
  { this.spielkonsole=spielkonsole;
    this.bestenliste=bestenliste; }
  
  //Zustands�berg�nge
  
   /** Ereignis. Teilt der Steuerung mit, 
       dass das Spiel gestartet worden ist.*/
  public void spiel_gestartet()
  { zustand.spiel_gestartet(); }

   /** Ereignis. Teilt der Steuerung mit, dass die Praesentation der 
       Ziffernfolge beendet worden ist.*/
  public void praesentation_Ziffernfolge_beendet()
  { zustand.praesentation_Ziffernfolge_beendet(); }

   /** Ereignis. Teilt der Steuerung mit, dass der Spieler eine
       Ziffer ausgewaehlt hat*/
  public void ziffer_ausgewaehlt(Ziffer ziffer)
  { zustand.ziffer_ausgewaehlt(ziffer); }
  
   /** Ereignis. Teilt der Steuerung mit, dass der Name eingegeben wurde*/
  public void name_eingegeben()
  { zustand.name_eingegeben(); }   

   /** Ereignis. Teilt der Steuerung mit, ein neues Spiel zu beginnen*/
  public void neues_Spiel()
  { zustand.neues_Spiel(); }
  
  // abstrakte Zustandsklasse mit Standardverhalten
  private abstract class Zustand
  { public void spiel_gestartet(){};
    public void praesentation_Ziffernfolge_beendet(){};
    public void ziffer_ausgewaehlt(Ziffer ziffer){};
    public void name_eingegeben(){};
    public void neues_Spiel(){};
    public void entry(){};
    public void exit(){};   
    // Umschalten auf neuen Zustand
    public void naechster_Zustand(Zustand neuer_zustand)
    { exit();  
      zustand=neuer_zustand;  
      neuer_zustand.entry(); 
    }
  }

   // Start Zustand 
  private class Start extends Zustand
  { @Override
	public void spiel_gestartet(){naechster_Zustand(praesentation_ziffernfolge);} 
  }  
   
   // Praesentation_Ziffernfolge-Zustand 
  private class Praesentation_Ziffernfolge extends Zustand
  { @Override
	public void entry(){spielkonsole.starte_Praesentation_Ziffernfolge();}
    
     @Override
	public void praesentation_Ziffernfolge_beendet()
    { naechster_Zustand(memorieren_ziffernfolge); }  
  }
  
  // Memorieren_Ziffernfolge Zustand 
  private class Memorieren_Ziffernfolge extends Zustand
  { @Override
	public void ziffer_ausgewaehlt(Ziffer ziffer)
    { if(spielkonsole.ausgewaehlte_Ziffer_korrekt(ziffer)
         && !spielkonsole.alle_Ziffern_memoriert())
      { ziffer.leuchte_gruen_auf();
        spielkonsole.naechste_Sollziffer();
        naechster_Zustand(memorieren_ziffernfolge);
        return;
      }
      if(spielkonsole.ausgewaehlte_Ziffer_korrekt(ziffer)
         && spielkonsole.alle_Ziffern_memoriert())
      { ziffer.leuchte_gruen_auf();      
        naechster_Zustand(praesentation_ziffernfolge);
        return;
      }
      if(!spielkonsole.ausgewaehlte_Ziffer_korrekt(ziffer))
      { // Fehler
    	ziffer.leuchte_rot_auf();
        naechster_Zustand(eingabe_name);
        return;
      }
    }
  }
  
  // Eingabe_Name Zustand 
  private class Eingabe_Name extends Zustand
  { @Override
	public void entry() 
    { spielkonsole.sichtbar(false);
	  bestenliste.sichtbar(true);	// bestenliste.repaint();
      bestenliste.aktiviere_Namenseingabe();	//JPanel.setEnabled()
      int laenge_Ziffernfolge=spielkonsole.laenge_Ziffernfolge();
      int spielzeit=spielkonsole.spielzeit();      
      bestenliste.neues_Ergebnis(laenge_Ziffernfolge, spielzeit);
    }
  
	@Override
	public void name_eingegeben()
    { naechster_Zustand(anzeige_bestenliste); }
  
    @Override
	public void neues_Spiel()
    { bestenliste.sichtbar(false);
      spielkonsole.sichtbar(true);
      spielkonsole.beginne_neue_Ziffernfolge();
      naechster_Zustand(praesentation_ziffernfolge); }
  }  
  
  // Anzeige_Bestenliste Zustand 
  private class Anzeige_Bestenliste extends Zustand
  { @Override
	public void entry() {bestenliste.zeige_Liste_an();}
  
	@Override
	public void neues_Spiel()
    { naechster_Zustand(praesentation_ziffernfolge); }

    @Override
	public void exit() 
    { spielkonsole.beginne_neue_Ziffernfolge(); 
      spielkonsole.sichtbar(true);
      bestenliste.sichtbar(false);
    }
  }
}