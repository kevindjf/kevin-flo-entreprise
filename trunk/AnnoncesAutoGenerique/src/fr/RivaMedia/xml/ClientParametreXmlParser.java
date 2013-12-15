package fr.RivaMedia.xml;

import org.xmlpull.v1.XmlPullParser;

import fr.RivaMedia.model.ClientParametres;
import fr.RivaMedia.xml.core.XmlParser;

public class ClientParametreXmlParser extends XmlParser {

	public ClientParametreXmlParser(String xml) {
		super(xml);	
	}

	public ClientParametreXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public ClientParametres getClientParametres(){
		ClientParametres clientParametres = new ClientParametres();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag)
				;
				if(tag.equals("id"))
					clientParametres.setId(getString());
				else if(tag.equals("texte_intro"))
					clientParametres.setTexteIntro(getString());
				else if(tag.equals("couleur_principale"))
					clientParametres.setCouleurPrincipale(getString());
				else if(tag.equals("couleur_secondaire"))
					clientParametres.setCouleurSecondaire(getString());
				else if(tag.equals("image_fond"))
					clientParametres.setImageFond(getString());
				else if(tag.equals("image_accueil"))
					clientParametres.setImageAccueil(getString());
				else if(tag.equals("image_logo"))
					clientParametres.setImageLogo(getString());
			}
			eventType = XMLgetSuivant();
		}

		return clientParametres;
	}


}
