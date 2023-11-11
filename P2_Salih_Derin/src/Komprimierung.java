
/** abstrakter Datentyp: Komprimierer (und Dekomparimierer) für beliebige Zeichenketten. 
    @author Andreas Kurz 
    @version 02.09.2018 */
public interface Komprimierung
{ // Konstruktor für Implementierungen:
  /** Ein Komprimierer und ein Dekomp
   * rimierer werden bereitgestellt. */ 
  //public <Komprimierung_Implementierung>();

  /** Der Text wird komprimiert. Wenn fuer den Text 
      ein Null-Zeiger uebergeben wird, wird nicht 
      komprimiert.
      @param text der zu komprimierende Text.*/
  public String komprimieren(String text);

  /** Der Text wird expandiert. Wenn fuer den Text 
      ein Null-Zeiger uebergeben wird, wird nicht 
      expandiert.
      @param text der zu expandierende Text.*/
  public String expandieren(String text);
}