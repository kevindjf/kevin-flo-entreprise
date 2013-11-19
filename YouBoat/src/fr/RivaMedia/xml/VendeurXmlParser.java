package fr.RivaMedia.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.model.Moteur;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.xml.core.XmlParser;

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
				if(tag.equals("client")){
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
				if(tag.equals("numero")){
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

			}
			eventType = XMLgetSuivant();
		}

		return vendeur;
	}
}
