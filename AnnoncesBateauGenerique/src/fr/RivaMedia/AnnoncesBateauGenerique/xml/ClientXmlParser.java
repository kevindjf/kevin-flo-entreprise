package fr.RivaMedia.AnnoncesBateauGenerique.xml;


import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import fr.RivaMedia.AnnoncesBateauGenerique.model.Client;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;
import android.util.Log;


public class ClientXmlParser extends XmlParser {

	public ClientXmlParser(String xml) {
		super(xml);	
	}

	public ClientXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	public List<Client> getClients() {
		List<Client> listeClients = new ArrayList<Client>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("client")){
					listeClients.add(getClient());
				}
			}
			eventType = XMLgetSuivant();
		}
		Log.e("XML",listeClients.size()+" clients chargees");

		return listeClients;
	}

	public Client getClient(){
		Client client = new Client();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML CLIENT",tag);
				if(tag.equals("id"))
					client.setId(getString());
				else if(tag.equals("contrat"))
					client.setContrat(getString());
				else if(tag.equals("nom"))
					client.setNom(getString());
				else if(tag.equals("adresse"))
					client.setAdresse(getString());
				else if(tag.equals("cp"))
					client.setCp(getString());
				else if(tag.equals("ville"))
					client.setVille(getString());
				else if(tag.equals("tel1"))
					client.setTel1(getString());
				else if(tag.equals("tel2"))
					client.setTel2(getString());
				else if(tag.equals("fax"))
					client.setFax(getString());
				else if(tag.equals("email"))
					client.setEmail(getString());
				else if(tag.equals("contact"))
					client.setContact(getString());
				else if(tag.equals("departement"))
					client.setDepartement(getString());
				else if(tag.equals("departement_num"))
					client.setDepartementNum(getString());
				else if(tag.equals("image"))
					client.setImage(getString());

				else if(tag.equals("horaires"))
					client.setHoraires(getString());
				else if(tag.equals("services"))
					client.setServices(getServices());
				else if(tag.equals("distributeur"))
					client.setDistributeur(getString());
				
				else if(tag.equals("gpslatitude")){
					String lat = getString();
					client.setLat(lat);
					Log.e("LAT",lat);
				}
				else if(tag.equals("gpslongitude")){
					String lng = getString();
					client.setLng(lng);
					Log.e("LNG",lng);
				}
				
				else if(tag.equals("siteweb"))
					client.setSiteWeb(getString());
				else if(tag.equals("description"))
					client.setDescription(getString());
				
				else if(tag.equals("logo")){
					client.setLogo(getXpp().getAttributeValue(null, "url"));
				}
				
				else if(tag.equals("nb_bateau"))
					client.setNbBateau(getInteger());
				else if(tag.equals("nb_moteur"))
					client.setNbMoteur(getInteger());
				else if(tag.equals("nb_accessoires"))
					client.setNbAccessoires(getInteger());
				
				else if(tag.equals("nom"))
					client.setNom(getString());
				
				else
					Log.e("XML INCONNU",tag);
			}
			eventType = XMLgetSuivant();
		}

		return client;
	}

	protected List<String> getServices(){
		List<String> services = new ArrayList<String>();
		
		int eventType = XMLgetEventType(); 
		do{
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML", tag);
				if(tag.equals("service")){
					services.add(getString());
				}
			}
			eventType = XMLgetSuivant();
		}while (eventType != XmlPullParser.END_TAG);
		
		return services;
	}

}
