package fr.RivaMedia.AnnoncesBateauGenerique.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.util.Log;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Departement;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Energie;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Etat;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Lieu;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Magazine;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Modele;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Region;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Service;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeCategories;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Parametres;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.CategorieXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.DepartementXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.EnergieXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.EtatXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.LieuxXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.MagazineXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.MarqueXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.ModeleXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.NombreXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.ParametresXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.RegionXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.ServiceXmlParser;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.TypeAnnonceXmlParser;

public class NetChargement extends Net{

	//BATEAU_A_MOTEUR = "1";
	//VOILIER = "2";
	//PNEU = "3";
	//MOTEURS = "4";
	//ACCESSOIRES = "5";
	//PLACE_DE_PORT="6";
	public static List<TypeCategories> chargerTypesCategories(boolean WA){
		List<TypeCategories> types = new ArrayList<TypeCategories>();
		for(int i=1;i<=6;++i){

			TypeCategories type = new TypeCategories();
			type.setId(Integer.toString(i));

			List<NameValuePair> donnees = Net.construireDonnes(
					Constantes.CATEGORIES_TYPE_ID, Integer.valueOf(i)
					);
			if(WA)
				Net.add(donnees,Constantes.MARQUES_POUR, "1");
			
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

	public static List<Marque> chargerMarquesBateauType(String idType, String idClient, boolean WA){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(WA)
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

	public static List<Marque> chargerMarquesMoteurs(String idClient, boolean WA){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(idClient != null)
			Net.add(donnees, Constantes.MARQUES_POUR_CLIENT, idClient
					);
		
		if(WA)
			Net.add(donnees, Constantes.MARQUES_POUR, "1");
		
		Log.e("MARQUE_MOTEUR",""+WA);

		String xml = Net.requeteGet(Constantes.URL_MARQUES_MOTEUR, donnees);		

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesDistribuees() {
		String xml = Net.requeteGet(Constantes.URL_MARQUES_DISTRIBUEES, null);
		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Modele> chargerModeles(String idMarque, String idType, String idClient, boolean WA) {
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MODELES_ID_MARQUE, idMarque
				);

		if(WA)
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

		return new NombreXmlParser(xml).getNombres();
	}

	public static Magazine chargerAutoPromo() {
		String xml = Net.requeteGet(Constantes.URL_AUTO_PROMO, null);

		return new MagazineXmlParser(xml).getMagazine();
	}

	public static Magazine chargerMagazineEnCours() {
		String xml = Net.requeteGet(Constantes.URL_MAGAZINE, null);

		return new MagazineXmlParser(xml).getMagazine();
	}

	public static List<Lieu> chargerLieux() {
		String xml = Net.requeteGet(Constantes.URL_LIEUX, null);
		return new LieuxXmlParser(xml).getListe();
	}

	public static Parametres chargerParametres() {
		String xml = Net.requeteGet(Constantes.URL_PARAMETRES, null);

		return new ParametresXmlParser(xml).getParametres();
	}


}