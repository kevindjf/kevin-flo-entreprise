package fr.RivaMedia.AnnoncesBateauGenerique.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.core.XmlParser;

public class VendeurXmlParser extends XmlParser {

	public VendeurXmlParser(String xml) {
		super(xml);	
	}

	public VendeurXmlParser(XmlPullParser xpp) {
		super(xpp);
	}

	/*
	<client>
	<numero><![CDATA[2403]]></numero>
	<nom><![CDATA[A.3.C]]></nom>
	<ville><![CDATA[Le Marin (97)]]></ville>
	<tel1><![CDATA[0613096071]]></tel1>
	<tel2><![CDATA[0696112240]]></tel2>
	<logo url="http://www.youboat.fr/images/identite/2403.gif"/>
	<gpslatitude><![CDATA[42.3914848]]></gpslatitude>
	<gpslongitude><![CDATA[-8.7020824]]></gpslongitude>
	<nombre_annonce><![CDATA[18]]></nombre_annonce>
	</client>
	 */

	public List<Vendeur> getListe() {
		List<Vendeur> vendeurs = new ArrayList<Vendeur>();
		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_DOCUMENT) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				if(tag.equals("client") || tag.equals("info_client")){
					Vendeur vendeur = getVendeur();
					vendeurs.add(vendeur);
				}
			}
			eventType = XMLgetSuivant();
		}

		Log.e("XML",vendeurs.size()+" vendeurs chargees");

		return vendeurs;
	}

	public Vendeur getVendeur(){
		Vendeur vendeur = new Vendeur();

		int eventType = XMLgetEventType(); 
		while (eventType != XmlPullParser.END_TAG) { 
			if (eventType == XmlPullParser.START_TAG) {
				String tag = getXpp().getName();
				//Log.e("XML",tag);
				if(tag.equals("id")){
					vendeur.setNumero(getString());
				}else if(tag.equals("numero")){
						vendeur.setNumero(getString());
				}else if(tag.equals("nom")){
					vendeur.setNom(getString());
				}else if(tag.equals("ville")){
					vendeur.setVille(getString());
				}else if(tag.equals("tel1")){
					vendeur.setTel1(getString());
				}else if(tag.equals("tel2")){
					vendeur.setTel2(getString());
				}else if(tag.equals("logo")){
					vendeur.setLogo(getXpp().getAttributeValue(null, "url"));
					getString();
				}else if(tag.equals("gpslatitude")){
					vendeur.setGpsLatitude(getString());
				}else if(tag.equals("gpslongitude")){
					vendeur.setGpsLatitude(getString());
				}else if(tag.equals("nombre_annonce")){
					vendeur.setNombreAnnonce(getString());
				}
				
				
				else if(tag.equals("email")){
					vendeur.setEmail(getString());
				}else if(tag.equals("adresse")){
					vendeur.setAdresse(getString());
				}else if(tag.equals("cp")){
					vendeur.setCodePostal(getString());
				}else if(tag.equals("ville")){
					vendeur.setVille(getString());
				}else if(tag.equals("fax")){
					vendeur.setFax(getString());
				}else if(tag.equals("siteweb")){
					vendeur.setSiteWeb(getString());
				}else if(tag.equals("contact")){
					vendeur.setContact(getString());
				}else if(tag.equals("horaires")){
					vendeur.setHoraires(getString());
				}else if(tag.equals("services")){
					vendeur.setServices(getServices());
				}else if(tag.equals("description")){
					vendeur.setDescription(getString());
				}else if(tag.equals("nb_bateau")){
					vendeur.setNbBateau(getString());
				}else if(tag.equals("nb_moteur")){
					vendeur.setNbMoteur(getString());
				}else if(tag.equals("nb_accessoire")){
					vendeur.setNbAccessoire(getString());
				}

			}
			eventType = XMLgetSuivant();
		}

		return vendeur;
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
	
	
	/*
	 * youboat>
	<info_client>
	<numero><![CDATA[2403]]></numero>
	<nom><![CDATA[A.3.C]]></nom>
	<email><![CDATA[contact@a-3-c.com]]></email>
	<adresse><![CDATA[Boulevard All?gre, Port De Plaisance]]></adresse>
	<cp><![CDATA[97290]]></cp>
	<ville><![CDATA[Le Marin]]></ville>
	<tel1><![CDATA[0613096071]]></tel1>
	<tel2><![CDATA[0696112240]]></tel2>
	<fax><![CDATA[]]></fax>
	<siteweb><![CDATA[]]></siteweb>
	<contact><![CDATA[Pierre Lecler]]></contact>
	<horaires><![CDATA[Du lundi au samedi
	8h15 - 18h00]]></horaires>
	<services>   
	<service><![CDATA[Entretien]]></service>
	<service><![CDATA[Gardiennage]]></service>
	<service><![CDATA[Gestion location]]></service>
	<service><![CDATA[Hivernage]]></service>
	<service><![CDATA[Maintenance]]></service>
	<service><![CDATA[M?canique]]></service>
	<service><![CDATA[Pose accessoires]]></service>
	<service><![CDATA[R?paration]]></service>
	</services>
	
	<description><![CDATA[Vente de bateaux neufs et occasions aux Antilles.
	Agent Nautitech Catamarans - R?gion Cara?bes
	
	Profitez de notre exp?rience et de notre ?quipe technique en France et aux Antilles, pour l'achat, l'?quipement, et la pr?paration de votre bateau. A.3.C vous accompagne tout au long de votre projet !]]></description>
	<logo url="http://www.youboat.fr/images/identite/2403.gif"/>
	<gpslatitude><![CDATA[42.3914848]]></gpslatitude>
	<gpslongitude><![CDATA[-8.7020824]]></gpslongitude>
	<nb_bateau><![CDATA[18]]></nb_bateau>
	<nb_moteur><![CDATA[0]]></nb_moteur>
	<nb_accessoire><![CDATA[0]]></nb_accessoire>
	</info_client>
	</youboat>
	*/
}
