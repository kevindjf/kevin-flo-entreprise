package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Color;
import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Parametres;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class ParametresXmlParser extends XmlParser {

	public ParametresXmlParser(String xml) {
		super(xml);	
	}

	public ParametresXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public Parametres getParametres() {
		Parametres parametres = new Parametres(); 
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				
				if(tag.equals("texte_intro"))
					parametres.setTexteIntro(getString());
				else if(tag.equals("background_color_un"))
					parametres.setBackgroundColorUn(Color.parseColor(getString()));
				else if(tag.equals("background_color_deux"))
					parametres.setBackgroundColorDeux(Color.parseColor(getString()));
				else if(tag.equals("font_color_un"))
					parametres.setFontColorUn(Color.parseColor(getString()));
				else if(tag.equals("font_color_deux"))
					parametres.setFontColorDeux(Color.parseColor(getString()));
				else if(tag.equals("maj_interval"))
					parametres.setMajInterval(getString());
				else if(tag.equals("image_start_728x1280"))
					parametres.setImageStart728x1280(getString());
				else if(tag.equals("image_start_640x1136"))
					parametres.setImageStart640x1136(getString());
				else if(tag.equals("image_start_640x960"))
					parametres.setImageStart640x960(getString());
				else if(tag.equals("image_start_640x480"))
					parametres.setImageStart640x480(getString());
				else if(tag.equals("image_fond"))
					parametres.setImageFond(getString());
				else if(tag.startsWith("image_slide"))
					parametres.getImagesSlide().add(getString());
				else if(tag.equals("image_logo"))
					parametres.setImageLogo(getString());
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML","parametres charges");

		return parametres;
	}

}
