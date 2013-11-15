package fr.RivaMedia.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.*;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.*;

public class NetNews extends Net{

	public static List<News> chargerListeNews(){
		String xml = Net.requeteGet(Constantes.NEWS_ADRESS_COMPLEMENT,null);

		return new NewsXmlParser(xml).getListeNews();
	}
	
	public static News getNews(String id){

		String xml = Net.requeteGet(Constantes.RECHERCHE_ACTU_DETAIL_ADRESS+id, null);

		return new NewsXmlParser(xml).getNews();
	}

}
