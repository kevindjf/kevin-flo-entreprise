package fr.RivaMedia.net;

import java.util.List;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.model.Actualite;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.xml.ActualiteXmlParser;

public class NetActualite extends Net{

	public static List<Actualite> chargerListeActualites(){
		String xml = Net.requeteGet(Constantes.URL_ACTUALITES_CLIENT,null);
		
		return new ActualiteXmlParser(xml).getListeActualites();
	}
	
	public static List<Actualite> chargerListeActualites(Integer from, Integer nombre){
		
		String xml = Net.requeteGet(Constantes.URL_ACTUALITES_CLIENT,
				Net.construireDonnes(
						Constantes.ACTUALITES_CLIENT_LIMIT_FROM, from,
						Constantes.ACTUALITES_CLIENT_LIMIT, nombre
						)
				);

		return new ActualiteXmlParser(xml).getListeActualites();
	}
	
	public static Actualite getActualite(String id){

		String xml = Net.requeteGet(Constantes.URL_ACTUALITE_DETAIL, Net.construireDonnes(
				Constantes.ACTUALITE_DETAIL_ID, id));

		return new ActualiteXmlParser(xml).getActualite();
	}

}
