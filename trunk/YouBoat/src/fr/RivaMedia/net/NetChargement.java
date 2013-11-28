package fr.RivaMedia.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.model.Region;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.TypeAnnonce;
import fr.RivaMedia.model.TypeCategories;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AnnonceXmlParser;
import fr.RivaMedia.xml.CategorieXmlParser;
import fr.RivaMedia.xml.DepartementXmlParser;
import fr.RivaMedia.xml.EnergieXmlParser;
import fr.RivaMedia.xml.EtatXmlParser;
import fr.RivaMedia.xml.MarqueXmlParser;
import fr.RivaMedia.xml.ModeleXmlParser;
import fr.RivaMedia.xml.RegionXmlParser;
import fr.RivaMedia.xml.ServiceXmlParser;
import fr.RivaMedia.xml.TypeAnnonceXmlParser;

public class NetChargement extends Net{

	//BATEAU_A_MOTEUR = "1";
	//VOILIER = "2";
	//PNEU = "3";
	//MOTEURS = "4";
	//ACCESSOIRES = "5";
	//PLACE_DE_PORT="6";
	public static List<TypeCategories> chargerTypesCategories(){
		List<TypeCategories> types = new ArrayList<TypeCategories>();
		for(int i=1;i<=6;++i){

			TypeCategories type = new TypeCategories();
			type.setId(Integer.toString(i));

			List<NameValuePair> donnees = Net.construireDonnes(
					Constantes.CATEGORIES_TYPE_ID, Integer.valueOf(i)
					);
			String xml = Net.requeteGet(Constantes.URL_TYPES_CATEGORIES, donnees);
			type.setCategories(new CategorieXmlParser(xml).getCategoriesBateau());

			types.add(type);
		}


		return types;
	}

	public static List<Service> chargerServices(){
		String xml = Net.requeteGet(Constantes.URL_SERVICES, null);
		return new ServiceXmlParser(xml).getServices();
	}
	
	public static List<Region> chargerRegions(){
		String xml = Net.requeteGet(Constantes.URL_REGIONS, null);
		return new RegionXmlParser(xml).getRegions();
	}
	
	public static List<Etat> chargerEtats(){
		String xml = Net.requeteGet(Constantes.URL_ETATS, null);
		return new EtatXmlParser(xml).getEtats();
	}
	
	public static List<Departement> chargerDepartements(){
		String xml = Net.requeteGet(Constantes.URL_DEPARTEMENTS, null);
		return new DepartementXmlParser(xml).getDepartements();
	}
	
	public static List<Energie> chargerEnergies(){
		String xml = Net.requeteGet(Constantes.URL_ENERGIES, null);
		return new EnergieXmlParser(xml).getEnergies();
	}
	
	public static List<TypeAnnonce> chargerTypesAnnonces(){
		String xml = Net.requeteGet(Constantes.URL_TYPES_ANNONCES, null);
		
		return new TypeAnnonceXmlParser(xml).getTypesAnnonces();
	}

	public static List<Marque> chargerMarquesBateauType(String idType, String idClient){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(idType != null || idClient != null)
			Net.add(donnees, 
					Constantes.MARQUES_POUR, "1"
					);

		if(idType != null)
			Net.add(donnees, 
					Constantes.MARQUES_ID_TYPE, idType
					);

		if(idClient != null)
			Net.add(donnees, 
					Constantes.MARQUES_POUR_CLIENT, idClient
					);

		String xml = Net.requeteGet(Constantes.URL_MARQUES, donnees);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesMoteurs(String idClient){

		List<NameValuePair> donnees = Net.construireDonnes();
		
		if(idClient != null)
			Net.add(donnees, 
					Constantes.MARQUES_POUR, "1",
					Constantes.MARQUES_POUR_CLIENT, idClient
					);
		
		String xml = Net.requeteGet(Constantes.URL_MARQUES_MOTEUR, null);		

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Modele> chargerModeles(String idMarque, String idType, String idClient) {
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MODELES_ID_MARQUE, idMarque
				);

		if(idType != null || idClient != null)
			Net.add(donnees, 
					Constantes.MODELES_POUR, "1"
			);
		
		if(idType != null)
			Net.add(donnees, 
				Constantes.MODELES_ID_TYPE, idType
			);
		
		if(idClient != null)
			Net.add(donnees, 
				Constantes.MODELES_ID_CLIENT, idClient
			);
		

		String xml = Net.requeteGet(Constantes.URL_MODELES, donnees);

		return new ModeleXmlParser(xml).getModeles();
	}

	public static Map<String,Integer> chargerNbAnnonces(){
		String xml = Net.requeteGet(Constantes.URL_NB_ANNONCES, null);

		return new AnnonceXmlParser(xml).getNbAnnonces();
	}

}
