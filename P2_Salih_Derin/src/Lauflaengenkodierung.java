public class Lauflaengenkodierung implements Komprimierung
{
	private String komprimiert="";
	private String expandiert="";
	
	private int pruefeHaeufigkeit (String text, int stelle)
	{
		int haeufigkeit = 1;
		int zaehler = stelle;
		while (zaehler+1 < text.length())
		{
			if(text.charAt(zaehler) == text.charAt(zaehler+1))
				haeufigkeit++;
			else break;			
			zaehler++;
		}
		if (haeufigkeit < 4
			&& text.charAt(stelle)!='Q')
			for (int i=0; i<haeufigkeit; i++)
				this.komprimiert += text.charAt(stelle);
		if (haeufigkeit >= 4 
			&& text.charAt(stelle)!='Q')
				this.komprimiert += "Q" + (char) (haeufigkeit+64) + text.charAt(stelle);
		if (text.charAt(stelle) == 'Q')
				this.komprimiert += "Q " + (char) (haeufigkeit+64);
		return haeufigkeit;
	}
	
	private int pruefeKodierung (String text, int stelle)
	{
		int sprung = 0;
		if(text.charAt(stelle) != 'Q')
			this.expandiert += text.charAt(stelle);				
			
		else if(text.charAt(stelle) == 'Q'					
			&& text.charAt(stelle + 1) != ' ')
		{		
			for(int i=0; i<((int) text.charAt(stelle + 1) - 64); i++)
				this.expandiert += text.charAt(stelle + 2);
			sprung+=2;
		}
		else if(text.charAt(stelle) == 'Q'					
				&& text.charAt(stelle + 1) == ' ')
		{
			for(int i=0; i<((int) text.charAt(stelle + 2) - 64); i++)
				this.expandiert += 'Q';
			sprung+=2;
		}
//		if(stelle + sprung >= text.length())
//			sprung++;
		return sprung;
	}
	
	public String komprimieren (String Text)
	{
		for(int i=0; i<Text.length(); i++)
		{
			i = i + pruefeHaeufigkeit(Text, i) - 1;
		}
		return komprimiert;
	}
	
	public String expandieren (String Text)
	{
		for(int i=0; i<Text.length(); i++)
		{
			i = i + pruefeKodierung(Text, i);
		}
		return expandiert;
	}
}
