package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Color;

import fr.RivaMedia.AnnoncesAutoGenerique.model.core.ClientParametres;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

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
					clientParametres.setCouleurPrincipale(Color.parseColor(getString()));
				else if(tag.equals("couleur_secondaire"))
					clientParametres.setCouleurSecondaire(Color.parseColor(getString()));
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
