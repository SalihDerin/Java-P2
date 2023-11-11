

/** Zufallszahlen nach der Methode der linearen Kongruenz 
    (Restmethode) von D. Lehmer vorgestellt im Jahre 1951, 
    mit Einstellregeln von D.E. Knuth.
    Mit �berlaufschutz f�r 32-Bit Systeme.
    Falls seed eine beliebige Zahl enth�lt, so erstellt 
    die folgende Anweisung ein Feld mit N Zufallszahlen:

    a[0]:=seed;

    for i:=1 to N do

      a[i]:=(a[i-1]*b+1) mod m;
      
	Das hei�t, um eine neue Zufallszahl zu erhalten, 
	nehme man die vorangegangene, multipliziere sie mit
	einer Konstanten b, addiere 1 und berechne den Rest,
	der sich bei der Division durch eine zweite Konstante
	m ergibt. Das Ergebnis ist stets eine ganze Zahl
	zwischen 0 und m - 1.
	Erstens sollte m gro� sein; es kann die Gr��e des 
	Computerwortes sein, mu� jedoch nicht ganz so gro� sein,
	wenn dies unzweckm��ig ist (siehe untenstehende Implementation).
	Normalerweise wird es zweckm��ig sein, m als eine Zweier- 
	oder Zehnerpotenz zu w�hlen. 
	Zweitens sollte b weder zu gro� noch zu klein sein; 
	eine g�nstige Wahl ist die Verwendung einer Zahl, 
	die eine Ziffer weniger hat als m. 
	Drittens sollte b eine beliebige Konstante ohne besonderes 
	Muster in ihren Ziffern sein, abgesehen davon, 
	da� sie auf ...x 21 enden sollte, wobei x gerade sein soll: 
	diese letzte Forderung ist sicherlich eigenartig, 
	doch sie verhindert das Auftreten bestimmter, 
	m�glicherweise problematischer F�lle, die durch die 
	mathematische Analyse gefunden wurden.

	@author Ingo Schneider
	@version 02.03.23
 * */
public class Zufallszahlen implements Zufallszahl
{ private int anfangswert=0;	// anfangswert ist kleiner als 10 Millionen = 10^7, siehe Konstruktor.
  private int zufallszahl_gross=0;	//Alle Zahlen von 0-99 999 999.
  private int zufallszahl_klein=0;	//Alle Zahlen von "this.von" bis "this.bis".
  private int b=48761421;	//31415821;	Konstante
  private int m=100000000;	// Zufallszahlen zwischen 0 und m-1. 100 Millionen = 10^8  Es interessieren nur Zahlen mit 8 Ziffern, nach Modulo. 
  private int ueberlaufschutz=10000;
  private int von=0;
  private int bis=0;
  private int anzahl_von_bis=0;
  
/*  a[0]:=seed;    a[i]:=(a[i-1]*b+1) mod m; 
  
    a=(a * b + 1) mod m    
 */
	// Konstruktor f�r Implementierungen:
  /** Ein Zufallszahlengenerator f�r Zahlen zwischen 0 und
      einschlie�lich 100 wird bereitgestellt. 
      Die Startzahl der Zufallszahlenreihe wird automatisch
      f�r diesen Generator individuell gew�hlt.*/
  public Zufallszahlen() 
  { long t_zufall=System.currentTimeMillis();
    //nach 32-Bit Bsp aus Sedgewick; anfangswert soll kleiner 10 Mio sein; 
    anfangswert=(int)(t_zufall%10000000); 
	zufallszahl_gross=anfangswert;
  	this.von=0;
  	this.bis=100;
  	anzahl_von_bis=this.bis-this.von;
  }
  
   //Konstruktor f�r Implementierungen:	
 /** Ein Zufallszahlengenerator wird bereitgestellt. 
     Die Startzahl der Zufallszahlenreihe wird automatisch
     f�r diesen Generator individuell gew�hlt.      
     @param von die kleinste moegliche Zahl. Wird eine negative
     Zahl fuer diesen Parameter angegeben, ist die kleinste 
     moegliche Zahl 0. Ist "von" groesser als "bis", ist die
     die kleinste moegliche Zahl "bis"; 
     @param bis die groesste m�gliche Zahl. Wird eine negative
     Zahl fuer diesen Parameter angegeben, ist die groesste 
     moegliche Zahl 0. Ist "bis" kleiner als "von", ist die
     die groesste moegliche Zahl "von" */
 public Zufallszahlen(int von, int bis) 
 { int von_tmp=0;
   int bis_tmp=0;
   
   if(von>0){von_tmp=von;}
   
   if(bis>0){bis_tmp=bis;}
   
   if(von_tmp>bis_tmp)
   { int tmp=von_tmp;
     von_tmp=bis_tmp;
     bis_tmp=tmp;
   }
   
   this.von=von_tmp;
   this.bis=bis_tmp;
   
   anzahl_von_bis=this.bis-this.von;
 }
  
  /** Die naechste ganzzahlige Zufallszahl wird ausgegeben. 
      Sie liegt im Bereich von einschliesslich 0 bis 
	  einschliesslich 100, wenn bei der Konstruktion 
	  des Generators nichts anderes definiert wurde. */
  public int naechste() 
  { zufallszahl_gross=mult(zufallszahl_gross,b);
    return reduktion(zufallszahl_gross);
  }   
	
  /** Die naechste ganzzahlige Zufallszahl wird ausgegeben. 
      Sie liegt im angegebenen Bereich.
      @param in_von die kleinste moegliche Zahl. Wird eine negative
      Zahl fuer diesen Parameter angegeben, ist die kleinste 
      moegliche Zahl 0. Ist "von" groesser als "bis", ist die
      die kleinste moegliche Zahl "bis"; 
      @param in_bis die groesste m�gliche Zahl. Wird eine negative
      Zahl fuer diesen Parameter angegeben, ist die groesste 
      moegliche Zahl 0. Ist "bis" kleiner als "von", ist die
      die groesste moegliche Zahl "von"*/
  public int naechste(int in_von, int in_bis) 
  { int von_alt=this.von;
  	int bis_alt=this.bis;
	if(in_von<0) this.von=0;
    else if(in_von>in_bis) this.von=in_bis;
	else this.von=in_von;
	if(in_bis<0) this.bis=0;
	else if(in_bis<in_von) this.bis=in_von;
	else this.bis=in_bis;
	anzahl_von_bis=this.bis-this.von;
	zufallszahl_gross=mult(zufallszahl_gross,b);
    zufallszahl_klein=reduktion(zufallszahl_gross);
    this.von=von_alt;
    this.bis=bis_alt;
    anzahl_von_bis=this.bis-this.von;
    return zufallszahl_klein;
  }
  
  /**Multiplikation von zwei Integers mit Ueberlaufschutz f�r 32-Bit-Systeme.
   * Der Trick besteht darin, den �berlauf zu vermeiden, indem die Multiplikation 
   * in Teile zerlegt wird. Um p mit q zu multiplizieren, schreiben wir 
   * p = 10^4 p1 + p0 und q = 10^4 q1 + q0, so da� das Produkt
   * pq	= (10^4 p1 + p0)(10^4 q1 + q0) 
   *    =  10^8 p1 q1  +  10^4 (p1 q0  +  p0 q1)  +  p0 q0 
   * Da wir nun f�r das Ergebnis nur acht Ziffern ben�tigen, k�nnen wir den ersten
   * Term ganz ignorieren. Und vom zweiten Term brauchen wir nur die letzten 4 Ziffern, 
   * vor der Multiplikation mit 10^4, also die einer bis tausender Stellen.   

   * @param p erster Faktor
   * @param q zweiter Faktor
   * @return produkt ohne Ueberlauf*/
  private int mult(int p,int q)
  { int p1,p0,q1,q0;
  	p1=p/ueberlaufschutz;
  	p0=p%ueberlaufschutz;
  	q1=q/ueberlaufschutz;
  	q0=q%ueberlaufschutz;
  	int mult=(((p0*q1+p1*q0)%ueberlaufschutz)*ueberlaufschutz+p0*q0)%m;
  	zufallszahl_gross=(mult+1)%m;
  	return zufallszahl_gross;
  }
  
  /** Reduktion von this.zufallszahl_gross 
      auf this.zufallszahl_klein.*/
  private int reduktion(int zufallszahl_gross) 
  { this.zufallszahl_klein=((zufallszahl_gross/ueberlaufschutz)*(anzahl_von_bis+1))
  							/ueberlaufschutz;
	return (this.zufallszahl_klein+this.von);  
  }
   
  
/* Erzeugung von Zufallszahlen, durch D. Lehmer im Jahre 1951 
 * ist die sogenannte Methode der linearen Kongruenz (Restmethode von Lehmer).
 * Falls seed eine beliebige Zahl enth�lt, so erstellt die
 * folgende Anweisung unter Benutzung dieser Methode ein Feld
 * mit N Zufallszahlen:
 * 
 *      a[0]:=seed;

    for i:=1 to N do

      a[i]:=(a[i-1]*b+1) mod m;
      
      
 * Das hei�t, um eine neue Zufallszahl zu erhalten, nehme man 
 * die vorangegangene, multipliziere sie mit einer Konstanten b,
 * addiere 1 und berechne den Rest, der sich bei der Division
 * durch eine zweite Konstante m ergibt. Das Ergebnis ist stets
 * eine ganze Zahl zwischen 0 und m - 1. 
 *   
 * Erstens sollte m gro� sein; es kann die Gr��e des Computerwortes sein, 
 * mu� jedoch nicht ganz so gro� sein, wenn dies unzweckm��ig ist
 * (siehe untenstehende Implementation).
 * Normalerweise wird es zweckm��ig sein, m als eine Zweier- 
 * oder Zehnerpotenz zu w�hlen.
 * 
 * Zweitens sollte b weder zu gro� noch zu klein sein; 
 * eine g�nstige Wahl ist die Verwendung einer Zahl, 
 * die eine Ziffer weniger hat als m.
 * 
 * Drittens sollte b eine beliebige Konstante ohne besonderes
 * Muster in ihren Ziffern sein, abgesehen davon, da� sie auf ...x 21
 * enden sollte, wobei x gerade sein soll: diese letzte Forderung
 * ist sicherlich eigenartig, doch sie verhindert das Auftreten bestimmter,
 * m�glicherweise problematischer F�lle, die durch die mathematische 
 * Analyse gefunden wurden.
 * 
 * 2�64=1,844674407*10�19
 * 
 * 2�32=4,294967296*10�9
 * */  

}
