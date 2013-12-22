package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.model.AutoPromo;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Modele;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.AutoPromoXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.CategorieXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.EnergieXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.MarqueXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.ModeleXmlParser;

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
