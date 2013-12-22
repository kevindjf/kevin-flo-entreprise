package fr.RivaMedia.AnnoncesAutoGenerique.net;

import java.util.List;

import org.apache.http.NameValuePair;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;

public class NetEmail extends Net{
	
	public static Boolean envoyerEmailAnnonce(String nom, String telephone, String email, String departementId, String message, String annonceId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ENVOIE_EMAIL_NOM, nom,
				Constantes.ENVOIE_EMAIL_TELEPHONE, telephone,
				Constantes.ENVOIE_EMAIL_EMAIL, email,
				Constantes.ENVOIE_EMAIL_DEPARTEMENT_ID, departementId,
				Constantes.ENVOIE_EMAIL_MESSAGE, message,
				Constantes.ENVOIE_EMAIL_ANNONCE_ID, annonceId
				);
		
		return Boolean.parseBoolean(requete(Constantes.URL_ENVOIE_EMAIL, donnees));
	}
	
	public static Boolean envoyerEmailClient(String nom, String telephone, String email, String departementId, String message, String clientId){
		List<NameValuePair> donnees = Net.construireDonnes(
				Constantes.ENVOIE_EMAIL_NOM, nom,
				Constantes.ENVOIE_EMAIL_TELEPHONE, telephone,
				Constantes.ENVOIE_EMAIL_EMAIL, email,
				Constantes.ENVOIE_EMAIL_DEPARTEMENT_ID, departementId,
				Constantes.ENVOIE_EMAIL_MESSAGE, message,
				Constantes.ENVOIE_EMAIL_CLIENT_ID, clientId
				);
		
		return Boolean.parseBoolean(requete(Constantes.URL_ENVOIE_EMAIL_CLIENT, donnees));
	}

}
