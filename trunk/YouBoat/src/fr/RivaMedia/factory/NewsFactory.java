package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.News;

public class NewsFactory {

	public static News getNews(){
		News news = new News();
		
		news.setTitle("Le nouveau magasine est sorti !");
		news.setLink("http://www.youboat.fr/");
		news.setImageAdress("http://www.aboriva.com/img/couv/8/nm_youboat-num6-juilletaout-2012_99.jpg");
		news.setPubDate("10/10/2010 10/10");
		news.setDescription("Le nouveau magasine est sorti ! courrez tout de suite l'acheter !");
		//private List<RubriqueDescription> contener;
		news.setDateFormattee("10 octobre 2010");
		news.setHeureFormattee("10 heureus 10");
		
		return news;
	}
	
	public static List<Object> getListeNews(){
		List<Object> news = new ArrayList<Object>();
		for(int i=0;i<10;++i)
			news.add(getNews());
		return news;
	}

}
