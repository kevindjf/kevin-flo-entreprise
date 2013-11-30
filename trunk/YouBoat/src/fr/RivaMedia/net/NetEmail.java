package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.net.core.Net;

public class NetEmail extends Net{

	public static Boolean envoyerEmailAnnonce(String email, String message, String annonceId, String idType){
		return envoyerEmailAnnonce(email, message, annonceId, idType, null, null, null);
	}
	
	public static Boolean envoyerEmailAnnonce(String email, String message, String annonceId, String idType, String nom, String tel1, String departementId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ENVOIE_EMAIL_EMAIL, email,
				Constantes.ENVOIE_EMAIL_MESSAGE, message,
				Constantes.ENVOIE_EMAIL_ANNONCE_ID, annonceId,
				Constantes.ENVOIE_EMAIL_TYPE_ID, idType
				);
		
		if(nom != null)
			Net.add(donnees, 
					Constantes.ENVOIE_EMAIL_NOM, nom
					);
		if(tel1 != null)
			Net.add(donnees, 
					Constantes.ENVOIE_EMAIL_TEL_1, tel1
					);
		if(departementId != null)
			Net.add(donnees, 
					Constantes.ENVOIE_EMAIL_DEPARTEMENT_ID, departementId
					);
		
		return Boolean.parseBoolean(requete(Constantes.URL_ENVOIE_EMAIL, donnees));
	}
	
	public static Boolean envoyerEmailClientDirect(String email, String message, String nom, String clientDirectId){
		return envoyerEmailClientDirect(email, message, nom, clientDirectId, null, null);
	}
	
	public static Boolean envoyerEmailClientDirect(String email, String message, String nom, String clientDirectId, String tel1, String departementId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ENVOIE_EMAIL_EMAIL, email,
				Constantes.ENVOIE_EMAIL_MESSAGE, message,
				Constantes.ENVOIE_EMAIL_NOM, nom,
				Constantes.ENVOIE_EMAIL_CLIENT_DIRECT_ID, clientDirectId
				);
		
		if(tel1 != null)
			Net.add(donnees, 
					Constantes.ENVOIE_EMAIL_TEL_1, tel1
					);
		if(departementId != null)
			Net.add(donnees, 
					Constantes.ENVOIE_EMAIL_DEPARTEMENT_ID, departementId
					);
		
		return Boolean.parseBoolean(requete(Constantes.URL_ENVOIE_EMAIL, donnees));
	}
	
	

}
