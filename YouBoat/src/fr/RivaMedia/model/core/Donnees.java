package fr.RivaMedia.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.Type;

public class Donnees {

	public static List<Type> types = new ArrayList<Type>();
	public static List<Categorie> getCategories(String type){
		for(Type t : types){
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
	
	//TODO envoyer tous les logs d'erreur par mail
}
