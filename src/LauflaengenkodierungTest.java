import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LauflaengenkodierungTest
{

	@Test
	public void standardKonstruktor()
	{
		Lauflaengenkodierung test = null;
		test = new Lauflaengenkodierung();
		assertNotNull("Objekt wurde nicht erzeugt", test);
	}
	
	@Test
	public void komprimiereNichtQHaeufigkeitKleiner4()
	{
		String testVariable = "oo";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String kompr = test.komprimieren(testVariable);
		assertEquals(testVariable, kompr);
	}
	
	@Test
	public void komprimiereNichtQHaeufigkeitGroesser3()
	{
		String testVariable = "OOOO";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String kompr = test.komprimieren(testVariable);
		assertEquals("QDO", kompr);
	}
	
	@Test
	public void komprimiereQHaeufigkeitKleiner4()
	{
		String testVariable = "QQ";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String kompr = test.komprimieren(testVariable);
		assertEquals("Q B", kompr);
	}
	
	@Test
	public void komprimiereQHaeufigkeitGroesser3()
	{
		String testVariable = "QQQQQ";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String kompr = test.komprimieren(testVariable);
		assertEquals("Q E", kompr);
	}
	
	@Test
	public void expandiereNichtQHaeufigkeitKleiner4()
	{
		String testVariable = "oo";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String exp = test.expandieren(testVariable);
		assertEquals(testVariable, exp);
	}
	
	@Test
	public void expandiereNichtQHaeufigkeitGroesser3()
	{
		String testVariable = "QDO";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String exp = test.expandieren(testVariable);
		assertEquals("OOOO", exp);
	}
	
	@Test
	public void expandiereQHaeufigkeitKleiner4()
	{
		String testVariable = "Q B";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String exp = test.expandieren(testVariable);
		assertEquals("QQ", exp);
	}
	
	@Test
	public void expandiereQHaeufigkeitGroesser3()
	{
		String testVariable = "Q E";
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String exp = test.expandieren(testVariable);
		assertEquals("QQQQQ", exp);
	}
	
	@Test
	public void test1()
	{
		//String testVariable = "QQQQQQQQQ aaaaaaaaaaaaaaaaaa &&&& \n QQQQ";
		String testVariable = "aaaaa\naaaa";
		System.out.println(testVariable);
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String komprimiert=test.komprimieren(testVariable);
		System.out.println(komprimiert);
		assertTrue(testVariable.length()>=komprimiert.length(),"Komprimierte Zeichenkette ist länger als das Original");
		String expandiert = test.expandieren(komprimiert);
		System.out.println(expandiert);
		assertEquals(testVariable, expandiert);
	}
	
	@Test
	public void test2()
	{
		String testVariable = "QQQQQQQQQ aaaaaaaaaaaaaaaaaa &&&& \n QQQQ";
		System.out.println(testVariable);
		Lauflaengenkodierung test = new Lauflaengenkodierung();
		String komprimiert=test.komprimieren(testVariable);
		System.out.println(komprimiert);
		assertTrue(testVariable.length()>=komprimiert.length(),"Komprimierte Zeichenkette ist länger als das Original");
		String expandiert = test.expandieren(komprimiert);
		System.out.println(expandiert);
		assertEquals(testVariable, expandiert);
	}
}
