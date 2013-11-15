package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;
import fr.RivaMedia.model.Categorie;

public class CategorieFactory {

	public static Categorie getCategorie(){
		Categorie categorie = new Categorie();

		categorie.setIdCategory("1");
		categorie.setLibelle("Premiere categorie");
		//private Map<Object,Object> contener;

		return categorie;
	}
	
	public static List<Categorie> getListeCategorie(){
		List<Categorie> categories = new ArrayList<Categorie>();
		for(int i=0;i<10;++i)
			categories.add(getCategorie());
		return categories;
	}

}
