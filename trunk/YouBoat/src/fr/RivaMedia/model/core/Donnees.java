package fr.RivaMedia.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Departement;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Etat;
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
	public static List<Categorie> getCategories(String type){
		for(TypeCategories t : typeCategories){
			if(t.getId().equals(type))
				return t.getCategories();
		}
		return null;
	}
	
	public static List<Marque> toutesMarques = new ArrayList<Marque>();
	public static Map<String,List<Marque>> marques = new HashMap<String,List<Marque>>();
	public static List<Service> services;
	public static List<Marque> getMarques(String type){
		List<Marque> mqs = marques.get(type);
		
		return mqs;
	}
	
	public static boolean uneSeulePhoto = true;
	
	public static List<Region> regions = new ArrayList<Region>();
	public static List<Etat> etats = new ArrayList<Etat>();
	public static List<Departement> departements = new ArrayList<Departement>();
	public static List<Energie> energies = new ArrayList<Energie>();
	
}
