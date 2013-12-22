package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import org.xmlpull.v1.XmlPullParser;

import fr.RivaMedia.AnnoncesAutoGenerique.model.AutoPromo;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

public class AutoPromoXmlParser extends XmlParser {

	public AutoPromoXmlParser(String xml) {
		super(xml);
	}

	public AutoPromo getAutoPromo() {
		AutoPromo autoPromo = new AutoPromo();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("image_320x568"))
					autoPromo.setImage320_568(getString());
				else if(tag.equals("image_320x480"))
					autoPromo.setImage320_480(getString());
				else if(tag.equals("lien"))
					autoPromo.setLien(getString());
				
			}
			eventType = XMLgetSuivant();
		}
		
		return autoPromo;
	}

}
