package fr.RivaMedia.model.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Type;

@SuppressLint("UseSparseArrays")
public class Donnees {

	public static List<Type> types = new ArrayList<Type>();
	public static List<Categorie> getCategories(String type){
		for(Type t : types){
			if(t.getId().equals(type))
				return t.getCategories();
		}
		return null;
	}
	
	public static Map<String,List<Marque>> marques = new HashMap<String,List<Marque>>();
	public static List<Marque> getMarques(String type){
		return marques.get(type);
	}
	
}
