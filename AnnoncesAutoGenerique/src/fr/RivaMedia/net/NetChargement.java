package fr.RivaMedia.net;

import java.util.List;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.AutoPromo;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Modele;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.AutoPromoXmlParser;
import fr.RivaMedia.xml.CategorieXmlParser;
import fr.RivaMedia.xml.EnergieXmlParser;
import fr.RivaMedia.xml.MarqueXmlParser;
import fr.RivaMedia.xml.ModeleXmlParser;

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
	
	public static AutoPromo chargerAutoPromo(){
		String xml = Net.requeteGet(Constantes.URL_AUTO_PROMO, null);

		return new AutoPromoXmlParser(xml).getAutoPromo();
	}
}
