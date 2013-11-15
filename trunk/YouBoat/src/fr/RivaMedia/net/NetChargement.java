package fr.RivaMedia.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.*;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.*;

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
					Constantes.TYPE_ID, Integer.valueOf(i)
					);
			String xml = Net.requeteGet(Constantes.CATEGORY_ADRESS_COMPLEMENT, donnees);
			type.setCategories(new CategorieBateauXmlParser(xml).getCategoriesBateau());
			
			types.add(type);
		}


		return types;
	}
	
	public static String servicesUrl = "xml-services.php";
	
	public static List<Service> chargerServices(){
		String xml = Net.requeteGet(servicesUrl, null);
		return new ServiceXmlParser(xml).getServices();
	}

	public static String marquesUrl = "xml-marque-bateau-rech.php";
	public static String marquesIdType = "idtype";

	public static List<Marque> chargerMarquesBateaux(){
		String xml = Net.requeteGet(marquesUrl, null);

		return new MarqueXmlParser(xml).getMarques();
	}

	public static List<Marque> chargerMarquesBateauAMoteur(){

		List<NameValuePair> donnees = Net.construireDonnes(
				marquesIdType, Constantes.BATEAU_A_MOTEUR
				);

		String xml = Net.requeteGet(marquesUrl+"?", donnees);

		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Marque> chargerMarquesVoiliers(){

		List<NameValuePair> donnees = Net.construireDonnes(
				marquesIdType, Constantes.VOILIER
				);

		String xml = Net.requeteGet(marquesUrl+"?", donnees);

		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Marque> chargerMarquesPneu(){

		List<NameValuePair> donnees = Net.construireDonnes(
				marquesIdType, Constantes.PNEU
				);

		String xml = Net.requeteGet(marquesUrl+"?", donnees);

		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Marque> chargerMarquesMoteurs(){
		String xml = Net.requeteGet(Constantes.MARQUE_MOTEUR_ADRESS_COMPLEMENT, null);

		return new MarqueXmlParser(xml).getMarques();
	}

}
