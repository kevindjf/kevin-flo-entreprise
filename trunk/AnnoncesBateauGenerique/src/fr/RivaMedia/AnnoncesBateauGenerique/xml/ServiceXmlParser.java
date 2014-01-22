package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Service;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class ServiceXmlParser extends XmlParser {

	public ServiceXmlParser(String xml) {
		super(xml);	
	}

	public ServiceXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Service> getServices() {
		List<Service> services = new ArrayList<Service>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("service")){
					Service service = new Service();
					service.setId(getXpp().getAttributeValue(null, "id"));
					service.setLibelle(getString());
					services.add(service);
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",services.size()+" services chargees");

		return services;
	}

}
