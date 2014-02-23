package fr.RivaMedia.AnnoncesBateauGenerique.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Client;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Departement;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Energie;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Etat;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Lieu;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Magazine;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Region;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Service;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeCategories;

public class Donnees {

	public static Parametres parametres;
	public static Client client;
	public static Map<String,Integer> nbAnnonces;
	public static List<Actualite> news;
	public static List<TypeAnnonce> typesAnnonces = new ArrayList<TypeAnnonce>();

	public static List<TypeCategories> typeCategories = new ArrayList<TypeCategories>();
	public static List<TypeCategories> typeCategoriesTOUTES = new ArrayList<TypeCategories>();
	public static List<Categorie> getCategories(String type, boolean WA){
		List<TypeCategories> cs = typeCategories;
		if(WA)
			cs = typeCategoriesTOUTES;
		for(TypeCategories t : cs){
			if(t.getId().equals(type))
				return t.getCategories();
		}
		return null;
	}

	public static Categorie getCategorie(String id, boolean WA){
		List<Categorie> categories = getCategories(Constantes.BATEAUX, WA);
		for(Categorie c : categories){
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}

	public static Categorie getCategorieToutes(String id,boolean WA){
		for(int i=0;i<=typeCategoriesTOUTES.size();++i){
			try{
				List<Categorie> categories = getCategories(""+i, WA);
				for(Categorie c : categories){
					if(c.getId().equals(id))
						return c;
				}
			}catch(Exception e){}
		}
		return null;
	}

	public static List<Marque> toutesMarques = new ArrayList<Marque>();
	public static Map<String,List<Marque>> marques = new HashMap<String,List<Marque>>();
	public static Map<String,List<Marque>> TOUTESmarques = new HashMap<String,List<Marque>>();
	public static List<Service> services;
	public static List<Marque> getMarques(String type, boolean WA){
		List<Marque> mqs;
		if(WA)
			mqs = marques.get(type);
		else
			mqs = TOUTESmarques.get(type);

		return mqs;
	}

	public static boolean uneSeulePhoto = true;

	public static List<Region> regions = new ArrayList<Region>();
	public static List<Etat> etats = new ArrayList<Etat>();
	public static List<Departement> departements = new ArrayList<Departement>();
	public static List<Energie> energies = new ArrayList<Energie>();
	public static Magazine magazine;
	public static Magazine autoPromo;

	public static String jeton = "";
	public static List<Marque> marquesDistribuees;

	/*
	private static List<Ville> villes = null;

	public static List<Ville> getVilles(Context context){
		if(villes == null)
			villes = VillesChargeur.chargerVilles(context, R.raw.villes);
		return villes;
	}
	 */

	public static List<Lieu> lieux;

	public static int nb_result = 0;
	
}
