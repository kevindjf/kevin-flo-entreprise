package fr.RivaMedia.AnnoncesAutoGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Client;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.core.XmlParser;

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
					client.setServices(getString());
				else if(tag.equals("distributeur"))
					client.setDistributeur(getString());
				
				else if(tag.equals("lat"))
					client.setLat(getString());
				else if(tag.equals("long"))
					client.setLng(getString());
				
				else
					Log.e("XML INCONNU",tag);
			}
			eventType = XMLgetSuivant();
		}

		return client;
	}


}
