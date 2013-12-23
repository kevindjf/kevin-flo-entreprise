package fr.RivaMedia.AnnoncesAutoGenerique.model.core;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.AnnoncesAutoGenerique.model.AutoPromo;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;

public class Donnees {
	public static ClientParametres parametres = new ClientParametres();

	public static String jeton = "";
	
	public static AutoPromo autoPromo;
	
	public static List<String> transmission = new ArrayList<String>();
	public static List<Marque> marques = new ArrayList<Marque>();
	public static List<Marque> marquesPubliees = new ArrayList<Marque>();
	public static List<Categorie> categories = new ArrayList<Categorie>();
	public static List<Energie> energies = new ArrayList<Energie>();
	
	
}
