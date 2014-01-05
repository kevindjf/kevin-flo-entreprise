package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.model.AutoPromo;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Departement;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Modele;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.ClientParametres;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.AutoPromoXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.CategorieXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.ClientParametreXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.DepartementXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.EnergieXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.MarqueXmlParser;
import fr.RivaMedia.AnnoncesAutoGenerique.xml.ModeleXmlParser;

public class NetChargement extends Net{	
	
	public static ClientParametres chargerClientParametres(){
		String xml = Net.requeteGet(Constantes.URL_CLIENT_PARAMETRE, null);
		return new ClientParametreXmlParser(xml).getClientParametres();
	}
	
	public static List<Marque> chargerMarques(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES, Net.construireDonnes(
				Constantes.MARQUES_TOUTES, Constantes.MARQUES_TOUTES_VALUE
				));
		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Marque> chargerMarquesPubliees(){
		String xml = Net.requeteGet(Constantes.URL_MARQUES, null);
				
		return new MarqueXmlParser(xml).getMarques();
	}
	
	public static List<Modele> chargerModeles(String marqueId, boolean tout){
		String xml = Net.requeteGet(Constantes.URL_MODELES, 
				Net.construireDonnes(
						Constantes.MODELES_MARQUE_ID, marqueId
						),tout);
		return new ModeleXmlParser(xml).getModeles();
	}
	
	public static List<Categorie> chargerCategories(){
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
	
	public static List<Departement> chargerDepartements(){
		String xml = Net.requeteGet(Constantes.URL_DEPARTEMENTS, null);
		return new DepartementXmlParser(xml).getDepartements();
	}
}
