package fr.RivaMedia.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.Type;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.CategorieXmlParser;
import fr.RivaMedia.xml.MarqueXmlParser;
import fr.RivaMedia.xml.ModeleXmlParser;
import fr.RivaMedia.xml.ServiceXmlParser;

public class NetChargement extends Net{

	//BATEAU_A_MOTEUR = "1";
	//VOILIER = "2";
	//PNEU = "3";
	//MOTEURS = "4";
	//ACCESSOIRES = "5";
	//PLACE_DE_PORT="6";
	public static List<Type> chargerTypes(){
		List<Type> types = new ArrayList<Type>();
		for(int i=1;i<=6;++i){

			Type type = new Type();
			type.setId(Integer.toString(i));

			List<NameValuePair> donnees = Net.construireDonnes(
					Constantes.TYPES_TYPE_ID, Integer.valueOf(i)
					);
			String xml = Net.requeteGet(Constantes.URL_TYPES, donnees);
			type.setCategories(new CategorieXmlParser(xml).getCategoriesBateau());

			types.add(type);
		}


		return types;
	}

	public static List<Service> chargerServices(){
		String xml = Net.requeteGet(Constantes.URL_SERVICES, null);
		return new ServiceXmlParser(xml).getServices();
	}

	public static List<Marque> chargerMarquesBateaux(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES.replace("?", ""), null);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesBateauAMoteur(){

		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MARQUES_ID_TYPE, Constantes.BATEAU_A_MOTEUR
				);

		String xml = Net.requeteGet(Constantes.URL_MARQUES, donnees);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesVoiliers(){

		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MARQUES_ID_TYPE, Constantes.VOILIER
				);

		String xml = Net.requeteGet(Constantes.URL_MARQUES, donnees);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesPneu(){

		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MARQUES_ID_TYPE, Constantes.PNEU
				);

		String xml = Net.requeteGet(Constantes.URL_MARQUES, donnees);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesMoteurs(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES_MOTEUR, null);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Modele> chargerModeles(String idType, String idMarque) {
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.MODELES_ID_TYPE, idType,
				Constantes.MODELES_ID_MARQUE, idMarque
				);

		String xml = Net.requeteGet(Constantes.URL_MODELES, donnees);

		return new ModeleXmlParser(xml).getModeles();
	}

}
