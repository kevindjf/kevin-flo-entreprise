package fr.RivaMedia.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Lieu;
import fr.RivaMedia.model.Magazine;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.model.Region;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.TypeAnnonce;
import fr.RivaMedia.model.TypeCategories;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.CategorieXmlParser;
import fr.RivaMedia.xml.DepartementXmlParser;
import fr.RivaMedia.xml.EnergieXmlParser;
import fr.RivaMedia.xml.EtatXmlParser;
import fr.RivaMedia.xml.LieuxXmlParser;
import fr.RivaMedia.xml.MagazineXmlParser;
import fr.RivaMedia.xml.MarqueXmlParser;
import fr.RivaMedia.xml.ModeleXmlParser;
import fr.RivaMedia.xml.NombreXmlParser;
import fr.RivaMedia.xml.RegionXmlParser;
import fr.RivaMedia.xml.ServiceXmlParser;
import fr.RivaMedia.xml.TypeAnnonceXmlParser;

public class NetChargement extends Net{	
	
	public static List<Marque> charcherMarques(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES, null);
		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Marque> charcherMarquesPubliees(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES, 
				Net.construireDonnes(
						Constantes.MARQUES_TOUTES, Constantes.MARQUES_TOUTES_VALUE
						));
		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Modele> charcherModele(String marqueId){
		String xml = Net.requeteGet(Constantes.URL_MODELES, 
				Net.construireDonnes(
						Constantes.MODELES_MARQUE_ID, marqueId
						));
		return new ModeleXmlParser(xml).getModeles();
	}
	
	public static List<Categorie> charcherCategories(){
		String xml = Net.requeteGet(Constantes.URL_CATEGORIES, null);
		return new CategorieXmlParser(xml).getCategories();
	}
	
	public static List<Energie> chargerEnergies(){
		String xml = Net.requeteGet(Constantes.URL_ENERGIES, null);

		return new EnergieXmlParser(xml).getEnergies();
	}
}
