package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;

public class NetReprise {
	
	public static String reprise(List<NameValuePair> donnees){
		return Net.requete(Constantes.URL_REPRISE, donnees);
	}
	
}
