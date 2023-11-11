

public class VerketteteListe implements Liste
{
  private Knoten kopf=new Knoten();
  private Knoten ende=new Knoten();

  VerketteteListe()
  { kopf.nachfolger=ende;
    kopf.vorgaenger=kopf;
    ende.nachfolger=ende;
    ende.vorgaenger=kopf;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Comparable loesche_nach(Liste.Iterator i)
  { Knoten knoten=((VerketteteListe.Iterator)i).knoten;
    if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
    if(knoten.nachfolger==ende) throw new ende_Ausnahme();
    Comparable zu_loeschendes_element=knoten.nachfolger.element;
    loesche_nach(knoten);
    return zu_loeschendes_element;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Comparable loesche(Liste.Iterator i)
  { Knoten knoten=((VerketteteListe.Iterator)i).knoten;
    if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten==ende) throw new ende_Ausnahme();
    if(knoten==kopf) throw new anfang_Ausnahme();
    Comparable zu_loeschendes_element=knoten.element;
    i.weiter();
    loesche_nach(knoten.vorgaenger);
    return zu_loeschendes_element;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Comparable loesche_vor(Liste.Iterator i)
  { Knoten knoten=((VerketteteListe.Iterator)i).knoten;
    if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten.vorgaenger==kopf) throw new anfang_Ausnahme();
    Comparable zu_loeschendes_element=knoten.vorgaenger.element;
    loesche_nach(knoten.vorgaenger.vorgaenger);
    return zu_loeschendes_element;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Comparable loesche_erstes_Element()
  { if(kopf.nachfolger==ende) throw new leer_Ausnahme();
    Comparable zu_loeschendes_element=kopf.nachfolger.element;
    loesche_nach(kopf);
    return zu_loeschendes_element;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public Comparable loesche_letztes_Element()
  { if(ende.vorgaenger==kopf) throw new leer_Ausnahme();
    Comparable zu_loeschendes_element=ende.vorgaenger.element;
    loesche_nach(ende.vorgaenger.vorgaenger);
    return zu_loeschendes_element;
  }

  private void loesche_nach(Knoten knoten)
  { Knoten zu_loeschender_knoten=knoten.nachfolger;
    zu_loeschender_knoten.nachfolger.vorgaenger=zu_loeschender_knoten.vorgaenger;
    zu_loeschender_knoten.vorgaenger.nachfolger=zu_loeschender_knoten.nachfolger;
    zu_loeschender_knoten.nachfolger=null;
    zu_loeschender_knoten.vorgaenger=null;
  }

  @Override
  @SuppressWarnings("rawtypes")
  public void setze_an_Anfang(Comparable element)
  { fuege_ein_nach(element,kopf);
  }
  
  @Override
  @SuppressWarnings("rawtypes")
  public void setze_an_Ende(Comparable element)
  { fuege_ein_nach(element,ende.vorgaenger);
  }


  @Override
  @SuppressWarnings("rawtypes")
  public void fuege_ein_nach(Comparable element,Liste.Iterator i)
  { Knoten knoten=((VerketteteListe.Iterator)i).knoten;
    if(knoten==ende) throw new ende_Ausnahme();
    if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
    fuege_ein_nach(element,knoten);
  }

  @Override
  @SuppressWarnings("rawtypes")
  public void fuege_ein_vor(Comparable element,Liste.Iterator i)
  { Knoten knoten=((VerketteteListe.Iterator)i).knoten;
    if(knoten==kopf) throw new anfang_Ausnahme();
    if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
    fuege_ein_nach(element,knoten.vorgaenger);
  }

  @SuppressWarnings("rawtypes")
  private void fuege_ein_nach(Comparable element,Knoten knoten)
  { Knoten neuer_knoten=new Knoten();
    neuer_knoten.element=element;
    neuer_knoten.nachfolger=knoten.nachfolger;
    neuer_knoten.vorgaenger=knoten;
    knoten.nachfolger.vorgaenger=neuer_knoten;
    knoten.nachfolger=neuer_knoten;
  }

  @Override
  public void vertausche(Liste.Iterator i1,Liste.Iterator i2) 
  { Knoten knoten_1=((VerketteteListe.Iterator)i1).knoten;
    if(knoten_1.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten_1==kopf) throw new anfang_Ausnahme();  
    if(knoten_1==ende) throw new ende_Ausnahme();
    Knoten knoten_2=((VerketteteListe.Iterator)i2).knoten;
    if(knoten_2.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten_2==kopf) throw new anfang_Ausnahme();  
    if(knoten_2==ende) throw new ende_Ausnahme();
    vertausche(knoten_1,knoten_2);
  }

  @Override
  public void vertausche_mit_nachfolger(Liste.Iterator iterator)
  { Knoten knoten_1=((VerketteteListe.Iterator)iterator).knoten;
    if(knoten_1.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten_1==kopf) throw new anfang_Ausnahme();  
    if(knoten_1==ende) throw new ende_Ausnahme();
    Knoten knoten_2=knoten_1.nachfolger;
    if(knoten_2==ende) throw new ende_Ausnahme();
    vertausche(knoten_1,knoten_2);
  }

  @Override
  public void vertausche_mit_vorgaenger(Liste.Iterator iterator)
  { Knoten knoten_1=((VerketteteListe.Iterator)iterator).knoten;
    if(knoten_1.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(knoten_1==kopf) throw new anfang_Ausnahme();  
    if(knoten_1==ende) throw new ende_Ausnahme();
    Knoten knoten_2=knoten_1.vorgaenger;
    if(knoten_2==kopf) throw new anfang_Ausnahme();
    vertausche(knoten_1,knoten_2);
  }

  @SuppressWarnings("rawtypes")
  private void vertausche(Knoten knoten_1,Knoten knoten_2)
  { Comparable element_1=knoten_1.element;
    knoten_1.element=knoten_2.element;
    knoten_2.element=element_1;
  }
      
  @Override
  public void verschiebe_an_Anfang(Liste.Iterator iterator)
  { Knoten zu_verschieben=((VerketteteListe.Iterator)iterator).knoten;
    if(zu_verschieben.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(zu_verschieben==kopf) throw new anfang_Ausnahme();  
    if(zu_verschieben==ende) throw new ende_Ausnahme();  
    Knoten ziel=kopf; 
    iterator.weiter();
    if(ziel.nachfolger==zu_verschieben) return; 
    verschiebe_nach(zu_verschieben,ziel);
  }

  @Override
  public void verschiebe_an_Ende(Liste.Iterator iterator)
  { Knoten zu_verschieben=((VerketteteListe.Iterator)iterator).knoten;
    if(zu_verschieben.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(zu_verschieben==kopf) throw new anfang_Ausnahme();  
    if(zu_verschieben==ende) throw new ende_Ausnahme();  
    Knoten ziel=ende.vorgaenger; 
    iterator.weiter();
    if(ziel.nachfolger==zu_verschieben) return; 
    verschiebe_nach(zu_verschieben,ziel);
  } 

  @Override
  public void verschiebe_nach(Liste.Iterator von,Liste.Iterator nach)
  { Knoten zu_verschieben=((VerketteteListe.Iterator)von).knoten;
    if(zu_verschieben.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(zu_verschieben==kopf) throw new anfang_Ausnahme();  
    if(zu_verschieben==ende) throw new ende_Ausnahme();
    Knoten ziel=((VerketteteListe.Iterator)nach).knoten;
    if(zu_verschieben==ziel) throw new ungueltiger_Iterator_Ausnahme(); 
    if(ziel.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(ziel==ende) throw new ende_Ausnahme();  
    von.weiter();
    if(ziel.nachfolger==zu_verschieben) return; 
    verschiebe_nach(zu_verschieben,ziel);
  }

  @Override
  public void verschiebe_vor(Liste.Iterator von,Liste.Iterator nach)
  { Knoten zu_verschieben=((VerketteteListe.Iterator)von).knoten;
    if(zu_verschieben.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme(); 
    if(zu_verschieben==kopf) throw new anfang_Ausnahme();  
    if(zu_verschieben==ende) throw new ende_Ausnahme();
    Knoten ziel=((VerketteteListe.Iterator)nach).knoten.vorgaenger;
    if(zu_verschieben==ziel) throw new ungueltiger_Iterator_Ausnahme(); 
    if(ziel==ende) throw new ende_Ausnahme();  
    von.weiter();
    if(ziel.nachfolger==zu_verschieben) return; 
    verschiebe_nach(zu_verschieben,ziel);
  }

  private void verschiebe_nach(Knoten zu_verschieben,Knoten ziel)
  { if(ziel==zu_verschieben) return;
    Knoten ziel_nachfolger=ziel.nachfolger; 
    if(ziel_nachfolger==zu_verschieben) return;
    Knoten zu_verschieben_nachfolger=zu_verschieben.nachfolger;
    Knoten zu_verschieben_vorgaenger=zu_verschieben.vorgaenger;
    // Ausketten
    zu_verschieben_nachfolger.vorgaenger=zu_verschieben_vorgaenger;
    zu_verschieben_vorgaenger.nachfolger=zu_verschieben_nachfolger; 
    // Einketten
    ziel_nachfolger.vorgaenger=zu_verschieben;
    ziel.nachfolger=zu_verschieben;
    zu_verschieben.vorgaenger=ziel;
    zu_verschieben.nachfolger=ziel_nachfolger;  
  }

  @Override
  public Liste.Iterator erzeuge_Iterator()
  { Iterator iterator=new Iterator();
    iterator.knoten=kopf.nachfolger;
    iterator.liste=this;
    return iterator;
  } 
  
  @Override
  public Iterator erzeuge_Iterator(Liste.Iterator iterator)
  { VerketteteListe.Iterator neuer_iterator=new Iterator();
    VerketteteListe.Iterator vorhandener_iterator;
    vorhandener_iterator=(VerketteteListe.Iterator)iterator;  
    if(this!=vorhandener_iterator.liste) throw new ungueltiger_Iterator_Ausnahme();
    neuer_iterator.knoten=vorhandener_iterator.knoten;
    neuer_iterator.liste=this;
    return neuer_iterator;
  }

  
  private class Knoten
  { @SuppressWarnings("rawtypes")
    public Comparable element;
    Knoten nachfolger;
    Knoten vorgaenger;
  };  
 

  public class Iterator implements Liste.Iterator
  {
    private Knoten knoten;
    private VerketteteListe liste;
 
    @Override
	public void anfang()
    { knoten=liste.kopf.nachfolger;
    }  
 
    @Override
	public void ende()
    { knoten=liste.ende.vorgaenger;
    }
 
    @Override
	public void weiter()
    { if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
      knoten=knoten.nachfolger;
    }
 
    @Override
	public void zurueck()
    { if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
      knoten=knoten.vorgaenger;
    }

    @Override
	public boolean vor_anfang()
    { return knoten==liste.kopf;
    }

    @Override
	public boolean nach_ende()
    { return knoten==liste.ende;
    }

    @Override
	public boolean gleich(Liste.Iterator iterator)
    { VerketteteListe.Iterator vergleichs_iterator;
      vergleichs_iterator=(VerketteteListe.Iterator)iterator;
      if(this.liste!=vergleichs_iterator.liste) throw new ungueltiger_Iterator_Ausnahme();
      return knoten==vergleichs_iterator.knoten;
    }

    @Override
	@SuppressWarnings("rawtypes")
    public Comparable element()
    { if(knoten.nachfolger==null) throw new ungueltiger_Iterator_Ausnahme();
      if(knoten==liste.kopf) throw new anfang_Ausnahme();
      if(knoten==liste.ende) throw new ende_Ausnahme();
      return knoten.element;
    }  
  }
}