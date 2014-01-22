package fr.RivaMedia.AnnoncesBateauGenerique.net;

import java.util.List;

import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.ActualiteXmlParser;

public class NetActualite extends Net{

	public static List<Actualite> chargerListeNews(Integer page){
		String xml = Net.requeteGet(Constantes.URL_ACTUALITES,Net.
				construireDonnes(Constantes.PAGE,page));

		return new ActualiteXmlParser(xml).getListeActualites();
	}
	
	public static Actualite getActualite(String id){

		String xml = Net.requeteGet(Constantes.URL_ACTUALITE_DETAIL, Net.construireDonnes(
				Constantes.ACTUALITE_DETAIL_ID_ACTUALITE, id));

		return new ActualiteXmlParser(xml).getNews();
	}

}
