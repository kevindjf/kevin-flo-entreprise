package fr.RivaMedia.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Lieu;
import fr.RivaMedia.model.Magazine;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Actualite;
import fr.RivaMedia.model.Region;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.TypeAnnonce;
import fr.RivaMedia.model.TypeCategories;

public class Donnees {

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

}
