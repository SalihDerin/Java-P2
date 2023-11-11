
/** abstrakter Datentyp: Liste, die vergleichbare Objekte enth�lt und die 
 * mit mehreren Iteratoren in beiden Richtungen traversiert werden kann.
 * @author Andreas Kurz 
 * @version 24.05.2017 */
public interface Liste
{ 
  /** Ein Element wird an den Anfang der Liste gesetzt.
      @param element das Element, das an den Anfang der Liste 
      gesetzt werden soll.*/
   @SuppressWarnings("rawtypes")
  public void setze_an_Anfang(Comparable element);

  /** Ein Element wird an das Ende der Liste gesetzt.
      @param element das Element, das an das Ende der Liste 
      gesetzt werden soll.*/
   @SuppressWarnings("rawtypes")
  public void setze_an_Ende(Comparable element);

  /** Einfuegen eines neuen Elements nach dem Element, auf das der Iterator 
      zeigt.
      @param element das Element, das in die Liste eingefuegt werden soll.
      @param iterator der Iterator, der auf das Element zeigt, hinter das 
      das neue Element eingefuegt werden soll.    
      @throw ende_Ausnahme wird ausgeloest, wenn der Iterator hinter 
      das Listenende zeigt.
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/    
  @SuppressWarnings("rawtypes")
  public void fuege_ein_nach(Comparable element,Iterator iterator);

  /** Einfuegen eines neuen Elements vor dem Element, auf das der Iterator 
      zeigt.
      @param element das Element, das in die Liste eingefuegt werden soll.
      @param iterator der Iterator, der auf das Element zeigt, vor dem 
      das neue Element eingefuegt werden soll.        
      @throw anfang_Ausnahme wird ausgeloest, wenn der Iterator vor
      den Listenanfang zeigt.
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/    
  @SuppressWarnings("rawtypes")
  public void fuege_ein_vor(Comparable element,Iterator iterator);

  /** Das erste Element wird geloescht.
      @return Das geloeschte Element.
      @throw leer_Ausnahme wird ausgeloest, wenn die Liste leer ist.*/
  @SuppressWarnings("rawtypes")
  public Comparable loesche_erstes_Element();

  /** Das letzte Element wird geloescht.
      @return Das geloeschte Element.
      @throw leer_Ausnahme wird ausgeloest, wenn die Liste leer ist.*/
   @SuppressWarnings("rawtypes")
  public Comparable loesche_letztes_Element();

  /** Das Element nach dem Element, auf das der Iterator zeigt, wird geloescht.
      @param iterator der Iterator, der auf das Element zeigt, dessen
      Nachfolger geloescht werden soll.
      @return Das geloeschte Element.       
      @throw ende_Ausnahme wird ausgeloest, wenn das Element,
      auf das der Iterator zeigt, das letzte Element 
      der Liste ist oder der Iterator hinter das Listenende zeigt.     
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, 
      bereits geloescht worden ist.*/
  @SuppressWarnings("rawtypes")
  public Comparable loesche_nach(Iterator iterator);

  /** Das Element, auf das der Iterator zeigt, wird geloescht.
      Nach der Loeschoperation zeigt der Iterator auf den Nachfolger des
      geloeschen Elements.
      @param iterator der Iterator, der auf das Element zeigt, das
      geloescht werden soll.
      @return Das geloeschte Element.              
      @throw ende_Ausnahme wird ausgeloest, wenn das Element,
      auf das der Iterator zeigt, das letzte Element der Liste ist oder
      wenn der Iterator hinter das Listenende zeigt.
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
  @SuppressWarnings("rawtypes")
  public Comparable loesche(Iterator iterator);

  /** Das Element vor dem Element, auf das der Iterator zeigt, wird geloescht.
      @param iterator der Iterator, der auf das Element zeigt, dessen
      Vorgaenger geloescht werden soll.
      @return Das geloeschte Element.      
      @throw anfang_Ausnahme wird ausgeloest, wenn das Element,
      auf das der Iterator zeigt, das erste Element 
      der Liste ist oder der Iterator vor den Listenanfang zeigt.       
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, 
      bereits geloescht worden ist.*/
  @SuppressWarnings("rawtypes")
  public Comparable loesche_vor(Iterator iterator);

  /** Das Element, auf das der Iterator zeigt, wird mit seinem 
      Nachfolger vertauscht.          
      @param iterator der Iterator, der auf das Element zeigt, das mit 
      seinem Nachfolger vertauscht werden soll.    
      @throw leer_Ausnahme wird ausgeloest, wenn die Liste leer ist.   
      @throw ende_Ausnahme wird ausgeloest, wenn das Element,
      auf das der Iterator zeigt, das letzte Element 
      der Liste ist.  
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
  public void vertausche_mit_nachfolger(Iterator iterator);

  /** Das Element, auf das der Iterator zeigt, wird mit seinem 
      Vorgaenger vertauscht.            
      @param iterator der Iterator, der auf das Element zeigt, das mit 
      seinem Vorgaeger vertauscht werden soll.     
      @throw leer_Ausnahme wird ausgeloest, wenn die Liste leer ist.   
      @throw anfang_Ausnahme wird ausgeloest, wenn das Element,
      auf das der Iterator zeigt, das erste Element der Liste ist oder
      wenn der Iterator vor den Listenanfang zeigt.
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
  public void vertausche_mit_vorgaenger(Iterator iterator);

  /** Das Element, auf das der erste Iterator zeigt, wird mit dem, auf das 
      der zweite Iterator zeigt, vertauscht.        
      @param iterator1 einer der Iteratoren, die auf die Elemente zeigen, 
      die vertauscht werden sollen.          
      @param iterator2 einer der Iteratoren, die auf die Elemente zeigen, 
      die vertauscht werden sollen.  
      @throw anfang_Ausnahme wird ausgeloest, wenn einer der 
      Iteratoren vor den Listenanfang zeigt.        
      @throw ende_Ausnahme wird ausgeloest, wenn einer der Iteratoren 
      hinter das Listenende zeigt.    
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn eines der Elemente, auf die die Iteratoren zeigen, geloescht 
      worden ist.*/
  public void vertausche(Iterator iterator1,Iterator iterator2);

  /** Das Element, auf das der Iterator zeigt wird an den Anfang der Liste
      verschoben. Der Iterator zeigt nach dieser Aktion auf den Nachfolger
      des verschobenen Elements.
      @param iterator der Iterator, der auf das Element zeigt, das 
      an den Anfang verschoben werden soll.
      @throw anfang_Ausnahme wird ausgeloest, wenn der Iterator 
      vor den Listenanfang zeigt.        
      @throw ende_Ausnahme wird ausgeloest, wenn der Iterator 
      hinter das Listenende zeigt.          
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn eines der Elemente, auf die die Iteratoren zeigen, geloescht 
      worden ist.*/
  public void verschiebe_an_Anfang(Iterator iterator);

  /** Das Element, auf das der Iterator zeigt wird an das Ende der Liste
      verschoben. Der Iterator zeigt nach dieser Aktion auf den Nachfolger
      des verschobenen Elements.          
      @param iterator der Iterator, der auf das Element zeigt, das 
      an das Ende verschoben werden soll.      
      @throw anfang_Ausnahme wird ausgeloest, wenn der Iterator 
      vor den Listenanfang zeigt.   
      @throw ende_Ausnahme wird ausgeloest, wenn der Iterator hinter das Listenende zeigt.          
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn eines der Elemente, auf die die Iteratoren zeigen, geloescht 
      worden ist.*/
  public void verschiebe_an_Ende(Iterator iterator);

  /** Das Element, auf das der erste Iterator zeigt, wird hinter
      das Element auf das der zweite Iterator zeigt, verschoben.
      Der erste Iterator zeigt nach dieser Aktion auf den Nachfolger
      des verschobenen Elements.          
      @param von der Iterator, der auf das Element zeigt, das 
      verschoben werden soll.            
      @param nach der Iterator, der auf das Element zeigt, hinter 
      das das zu verschiebende Element verschoben werden soll.   
      @throw anfang_Ausnahme wird ausgeloest, der erste Iterator 
      vor den Listenanfang zeigt.        
      @throw ende_Ausnahme wird ausgeloest, wenn einer der 
      Iteratoren hinter das Listenende zeigt.          
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn eines der Elemente, auf die die Iteratoren zeigen, geloescht 
      worden ist.*/
  public void verschiebe_nach(Iterator von,Iterator nach);

  /** Das Element, auf das der erste Iterator zeigt, wird vor
      das Element auf das der zweite Iterator zeigt, verschoben.
      Der erste Iterator zeigt nach dieser Aktion auf den Nachfolger
      des verschobenen Elements.          
      @param von der Iterator, der auf das Element zeigt, das 
      verschoben werden soll.            
      @param nach der Iterator, der auf das Element zeigt, vor 
      das das zu verschiebende Element verschoben werden soll.   
      @throw anfang_Ausnahme wird ausgeloest, wenn einer der
      Iteratoren vor den Listenanfang zeigen.        
      @throw ende_Ausnahme wird ausgeloest, der erste Iterator hinter das
      Listenende zeigt.          
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn eines der Elemente, auf die die Iteratoren zeigen, geloescht 
      worden ist.*/
  public void verschiebe_vor(Iterator von,Iterator nach);

  /** Erzeugt einen Iterator fuer die Navigation durch die Liste. Nach dem
      Erzeugen zeigt der Iterator auf das erste Element der Liste. Wenn die Liste
      leer ist zeigt der Iterator auf das Listenende.*/
  public Iterator erzeuge_Iterator();  

  /** Erzeugt einen Iterator fuer die Navigation durch die Liste, der
      auf dasselbe Element zeigt, wie ein anderer gegebender Iterator.
      @param iterator der Iterator, von dem die Position �bernommen wird.
      @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
      wenn der Iterator zu einer anderen Liste gehoert.*/
  public Iterator erzeuge_Iterator(Iterator iterator); 

 
  /** Iteratoren fuer die Navigation in der Liste.
      Kann mit "erzeuge_Iterator" erzeugt werden */
  public interface Iterator
  {
      /** Setzt den Iterator auf das erste Element der Liste.
          Ist die Liste leer, zeigt der Iterator nach dem Aufruf dieser 
          Funktion hinter das Listenende.*/
      public void anfang();

      /** Setzt den Iterator auf das letzte Element der Liste.
          Ist die Liste leer, zeigt der Iterator nach dem Aufruf dieser 
          Funktion vor den Listenanfang.*/
      public void ende();

      /** Setzt den Iterator auf das naechste Element der Liste.
          Zeigt der Iterator auf das Listenende, aendert sich nichts.
          @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
          wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
      public void weiter();

      /** Setzt den Iterator auf das vorhergehende Element der Liste.
          Zeigt der Iterator auf den Listenanfang, aendert sich nichts.
          @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
          wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
      public void zurueck();

      /** Gibt das Element, auf das der Iterator zeigt, zurueck.   
          @return Das Element, auf das der Iterator zeigt.   
          @throw anfang_Ausnahme wird ausgeloest, wenn der Iterator 
          vor den Listenanfang zeigt.        
          @throw ende_Ausnahme wird ausgeloest, wenn der Iterator 
          hinter das Listenende zeigt. 
          @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
          wenn das Element, auf das der Iterator zeigt, geloescht worden ist.*/
      @SuppressWarnings("rawtypes")
      public Comparable element();

      /** Prueft, ob der Iterator vor den Listenanfang zeigt.
          @return true: der Iterator zeigt vor den Listenanfang. */
      public boolean vor_anfang();

      /** Prueft, ob der Iterator hinter das Listenende zeigt.
          @return true: der Iterator zeigt hinter Listenende. */       
      public boolean nach_ende();        
      
      /** Prueft, ob der Iterator auf dasselbe Element zeigt wie
          ein zweiter Iterator.
          @param iterator Der zweite Iterator.
          @return true: Der Iterator und der zweite Iterator           
          @throw ungueltiger_Iterator_Ausnahme wird ausgeloest, 
          wenn der Iterator zu einer anderen Liste gehoert
          als der zweite Iterator.*/       
      public boolean gleich(Iterator iterator);
  }

  /** Ausnahme, wird ausgeloest, wenn versucht wird, Elemente in 
      einer leeren Liste anzusprechen.*/
  @SuppressWarnings("serial")
  public class leer_Ausnahme extends RuntimeException{};    

  /** Ausnahme, wird ausgeloest, wenn versucht wird, Elemente vor 
      dem ersten Element der Liste anzusprechen.*/
  @SuppressWarnings("serial")
  public class anfang_Ausnahme extends RuntimeException{};

  /** Ausnahme, wird ausgeloest, wenn versucht wird, Elemente nach 
      dem letzten Element der Liste anzusprechen.*/
  @SuppressWarnings("serial")
  public class ende_Ausnahme extends RuntimeException{};

  /** Ausnahme, wird ausgeloest, wenn versucht wird, mit einem 
      Iterator ein geloeschtes Element der Liste anzusprechen oder 
      wenn versucht wird, Iteratoren verschiedener Listenobjekte
      gemeinsam zu verwenden.*/
  @SuppressWarnings("serial")
  public class ungueltiger_Iterator_Ausnahme extends RuntimeException{};
}