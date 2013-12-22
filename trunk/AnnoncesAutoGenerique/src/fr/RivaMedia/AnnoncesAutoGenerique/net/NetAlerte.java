package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesAutoGenerique.model.Alerte;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;

public class NetAlerte extends Net{

	public static String creerAlerte(List<NameValuePair> donnees){
		return "";
	}

	public static String supprimerAlerte(String alerteId){
		return "";
	}
	
	public static List<Alerte> getAlertes(String jeton){		
		return null;
	}

}
