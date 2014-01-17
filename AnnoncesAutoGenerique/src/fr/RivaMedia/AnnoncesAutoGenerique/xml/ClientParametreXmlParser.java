package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Color;
import android.util.Log;
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
				Log.e("XML",tag)
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
				else if(tag.contains("image_slide")){
					String image = getString();
					if(clientParametres.getImageSlider().size()==0)
						clientParametres.setImageAccueil(image);
					clientParametres.getImageSlider().add(image);
				}
				
				else if(tag.equals("maj_interval"))
					clientParametres.setMajInterval(getString());
				
				else if(tag.equals("couleur_titre"))
					clientParametres.setCouleurTitre(Color.parseColor(getString()));
				else if(tag.equals("couleur_texte"))
					clientParametres.setCouleurTexte(Color.parseColor(getString()));
				
				else if(tag.equals("image_logo"))
					clientParametres.setImageLogo(getString());
				else if(tag.equals("image_start_640x1136"))
					clientParametres.setImageStart640x1136(getString());
				else if(tag.equals("image_start_640x960"))
					clientParametres.setImageStart640x960(getString());
				else if(tag.equals("image_start_320x480"))
					getString();
			}
			eventType = XMLgetSuivant();
		}

		return clientParametres;
	}


}
