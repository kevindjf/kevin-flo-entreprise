package fr.RivaMedia.net;

import java.util.List;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.News;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.NewsXmlParser;

public class NetNews extends Net{

	public static List<News> chargerListeNews(){
		String xml = Net.requeteGet(Constantes.URL_ACTUALITES,null);

		return new NewsXmlParser(xml).getListeNews();
	}
	
	public static News getNews(String id){

		String xml = Net.requeteGet(Constantes.URL_ACTUALITE_DETAIL, Net.construireDonnes(
				Constantes.ACTUALITE_DETAIL_ID_ACTUALITE));

		return new NewsXmlParser(xml).getNews();
	}

}
