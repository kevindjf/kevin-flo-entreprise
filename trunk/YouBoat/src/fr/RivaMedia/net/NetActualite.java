package fr.RivaMedia.net;

import java.util.List;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Actualite;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.NewsXmlParser;

public class NetActualite extends Net{

	public static List<Actualite> chargerListeNews(Integer page){
		String xml = Net.requeteGet(Constantes.URL_ACTUALITES,Net.
				construireDonnes(Constantes.PAGE,page));

		return new NewsXmlParser(xml).getListeNews();
	}
	
	public static Actualite getNews(String id){

		String xml = Net.requeteGet(Constantes.URL_ACTUALITE_DETAIL, Net.construireDonnes(
				Constantes.ACTUALITE_DETAIL_ID_ACTUALITE, id));

		return new NewsXmlParser(xml).getNews();
	}

}
