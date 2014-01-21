package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Region;
import fr.RivaMedia.xml.core.XmlParser;

public class RegionXmlParser extends XmlParser {

	public RegionXmlParser(String xml) {
		super(xml);	
	}

	public RegionXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Region> getRegions() {
		List<Region> regions = new ArrayList<Region>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("region")){
					Region region = new Region();
					region.setId(getXpp().getAttributeValue(null, "id"));
					region.setNom(getString());
					regions.add(region);
				}
			}
			eventType = XMLgetSuivant();
		}
		
		Log.e("XML",regions.size()+" regions chargees");

		return regions;
	}

}
