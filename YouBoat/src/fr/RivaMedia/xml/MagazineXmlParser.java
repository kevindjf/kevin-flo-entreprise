package fr.RivaMedia.xml;

import org.xmlpull.v1.XmlPullParser;

import fr.RivaMedia.model.Magazine;
import fr.RivaMedia.xml.core.XmlParser;

public class MagazineXmlParser extends XmlParser {

	public MagazineXmlParser(String xml) {
		super(xml);
	}

	public Magazine getMagazine() {
		Magazine magazine = new Magazine();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("numero"))
					magazine.setNumero(getString());
				else if(tag.equals("periode"))
					magazine.setPeriode(getString());
				else if(tag.equals("image"))
					magazine.setImage(getString());
				
			}
			eventType = XMLgetSuivant();
		}
		
		return magazine;
	}

}
