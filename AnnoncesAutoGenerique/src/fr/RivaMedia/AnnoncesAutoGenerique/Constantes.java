package fr.RivaMedia.AnnoncesAutoGenerique;

public class Constantes {	
	
	public static final String CLIENT_VALUE= "639";

	/*				ANNONCES AUTO 				*/

	public static final String URL_BASE = "http://www.annonces-automobile.com/script/xml/";
	public static final String KEY = "key";
	public static final String KEY_VALUE = "aA$Xm1";
	public static final String POST_KEY = "key";
	public static final String POST_KEY_VALUE = "aA$!n5ert";
	public static final String KEY_CLIENT= "client";

	//utile pour les PUSH
	public static final String SERVER_GOOGLE_CLOUD_ID = "AIzaSyB7IT3ndRmf9j_J94Y79Znyl1OptMp3e_s";

	public static final String URL_MARQUES = "marques.php";
	public static final String MARQUES_TOUTES = "all";
	public static final String MARQUES_TOUTES_VALUE = "true";

	public static final String URL_CATEGORIES = "categories.php";
	public static final String URL_ENERGIES = "energies.php";
	public static final String URL_DEPARTEMENTS = "departements.php";

	public static final String URL_MODELES = "series.php";
	public static final String MODELES_MARQUE_ID = "marque";

	public static final String URL_ANNONCES = "annonces.php";
	public static final String URL_DERNIERES_ANNONCES = "dernieres-annonces";
	public static final String URL_ANNONCE_DETAIL = "annonce.php";
	public static final String ANNONCE_DETAIL_ID = "annonce";
	
	public static final String ANNONCES_ORDER = "order";
	public static final String ANNONCES_ORDER_OPTION = "order_option";
	public static final String ANNONCES_ORDER_OPTION_PRIX = "prix";
	public static final String ANNONCES_ORDER_OPTION_KM = "km";
	public static final String ANNONCES_ORDER_OPTION_ANNEE = "annee";
	public static final String ANNONCES_ORDER_OPTION_CROISSANT = "asc";
	public static final String ANNONCES_ORDER_OPTION_DECROISSANT = "desc";

	public static final String ANNONCES_LIMIT_FROM = "limit_from";
	public static final String ANNONCES_LIMIT = "limit";
	
	public static final String ANNONCES_CATEGORIE_ID = "categorie";
	public static final String ANNONCES_SERIE_ID = "serie";
	public static final String ANNONCES_PRIX_MIN = "prix_min";
	public static final String ANNONCES_PRIX_MAX = "prix_max";
	public static final String ANNONCES_ENERGIE = "energie";
	public static final String ANNONCES_TRANSMISSION = "transmision"; //« Inconnu », « Mecanique » ou « Automatique »
	public static final String ANNONCES_TRANSMISSION_VALUE_INCONNU = "Inconnu"; 
	public static final String ANNONCES_TRANSMISSION_VALUE_MECANIQUE = "Mecanique"; 
	public static final String ANNONCES_TRANSMISSION_VALUE_AUTOMATIQUE = "Automatique"; 
	public static final String ANNONCES_NB_PORTES = "nb_portes";
	public static final String ANNONCES_ANNEE_MIN = "annee_min";
	public static final String ANNONCES_ANNEE_MAX = "annee_max";
	public static final String ANNONCES_KM = "km";
	public static final String ANNONCES_DEPARTEMENT_ID = "departement";
	public static final String ANNONCES_DEPARTEMENT_LIMITROPHE = "departement_limitrophe"; //true
	public static final String ANNONCES_DEPARTEMENT_LIMITROPHE_VALUE = "true"; 
	public static final String ANNONCES_FINITION = "finition"; 

	public static final String ANNONCES_MODE = "mode"; 
	public static final String ANNONCES_MODE_COUNT = "count"; 


	public static final String URL_ACTUALITES_CLIENT = "client_actualites.php";
	public static final String ACTUALITES_CLIENT_LIMIT_FROM = "limit_from";
	public static final String ACTUALITES_CLIENT_LIMIT = "limit";
	public static final String URL_ACTUALITE_DETAIL = "client_actualite.php";
	public static final String ACTUALITE_DETAIL_ID = "actualite";

	public static final String URL_CLIENTS_PRO = "clients.php";
	public static final String URL_CLIENT_DETAIL = "client.php";
	public static final String CLIENT_DETAIL_ID = "client";

	public static final String URL_AUTO_PROMO = "autopromo.php";



	public static final String URL_CLIENT_PARAMETRE = "client_parametre.php";


	public static final String URL_ENVOIE_EMAIL = "email_envoi.php";
	public static final String URL_ENVOIE_EMAIL_CLIENT = "email_envoi_client.php";
	public static final String ENVOIE_EMAIL_NOM = "nom";
	public static final String ENVOIE_EMAIL_TELEPHONE = "tel";
	public static final String ENVOIE_EMAIL_EMAIL = "email";
	public static final String ENVOIE_EMAIL_DEPARTEMENT_ID = "departement";
	public static final String ENVOIE_EMAIL_MESSAGE = "message";
	public static final String ENVOIE_EMAIL_ANNONCE_ID = "annonce";
	public static final String ENVOIE_EMAIL_CLIENT_ID = "annonce";

	//////////////////////////////POSTS//////////////////////////////

	public static final String URL_RECHERCHE = "client_recherche_insertion.php";
	public static final String URL_REPRISE= "client_reprise_insertion.php";
	//////////////////////////////OBLIGATOIRES//////////////////////////////

	public static final String RECHERCHE_CLIENT_ID = "client";
	public static final String RECHERCHE_MARQUE_ID = "marque";
	public static final String RECHERCHE_SERIE_ID = "serie";
	public static final String RECHERCHE_CATEGORIE_ID = "categorie";
	public static final String RECHERCHE_BUDGET = "budget";
	public static final String RECHERCHE_ENERGIE = "energie";

	public static final String RECHERCHE_DEPARTEMENT = "departement";
	public static final String RECHERCHE_NOM = "nom";
	public static final String RECHERCHE_EMAIL = "email";

	//////////////////////////////OPTIONNELS//////////////////////////////
	public static final String RECHERCHE_TELEPHONE = "tel";

	public static final String RECHERCHE_TRANSMISSION = "transmision";
	public static final String RECHERCHE_KM = "km";
	public static final String RECHERCHE_FINITION = "finition"; 
	public static final String RECHERCHE_COULEUR_EXT = "couleur_ext"; 
	public static final String RECHERCHE_COULEUR_INT = "couleur_int"; 
	public static final String RECHERCHE_NB_PORTES = "nb_portes";
	public static final String RECHERCHE_ANNEE_MIN = "annee_min";
	public static final String RECHERCHE_ANNEE_MAX = "annee_max";
	public static final String RECHERCHE_COMMENTAIRE = "commentaire";	

	public static final String URL_ABONNEMENT_ACTUALITES = "client_actualite_insertion.php";
	public static final String ABONNEMENT_ACTUALITES_JETON = "jeton";
	public static final String ABONNEMENT_ACTUALITES_TYPE = "type";
	public static final String ABONNEMENT_ACTUALITES_TYPE_VALUE = "android";


	//////////////////////////////RETOUR//////////////////////////////

	public static final String RETOUR_ERREUR = "0";
	public static final String RETOUR_OK = "1";

}
