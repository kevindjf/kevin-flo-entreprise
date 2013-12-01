package fr.RivaMedia;

public class Constantes {	



	/////////////////////////////////////////////////////////////////////////

	//TYPES
	public static final String BATEAU_A_MOTEUR = "1";
	public static final String BATEAUX = BATEAU_A_MOTEUR;
	public static final String VOILIER = "2";
	public static final String PNEU = "3";

	public static final String MOTEURS = "4";
	public static final String ACCESSOIRES = "5";
	public static final String PLACE_DE_PORT="6";

	/////////////////////////////////////////////////////////////////////////
	
	public static final String ANCIEN_URL_BASE = "http://www.youboat.fr/xml/";
	
	//URL
	public static final String URL_BASE = "http://www.youboat.fr/xml/android/"; //F
	
	public static final String URL_MAGAZINE = "magazine.php"; //F
	public static final String URL_AUTO_PROMO = "autopromo.php"; //F
	
	public static final String URL_TYPES_ANNONCES = "type.php"; //F
	public static final String URL_REGIONS = "region.php"; //F
	public static final String URL_ETATS = "etat.php"; //F
	public static final String URL_ENERGIES = "nrj.php"; //F
	
	public static final String URL_DEPARTEMENTS = "departement.php"; //F
	
	public static final String URL_TYPES_CATEGORIES = "categorie.php";  //F
	public static final String CATEGORIES_TYPE_ID = "idt"; //F
	
	public static final String URL_SERVICES = "services.php"; //F
	public static final String URL_MARQUES = "marque-bateau.php"; //F
	public static final String MARQUES_POUR = "wa"; //F
	public static final String MARQUES_POUR_CLIENT = "idcli"; //F
	public static final String URL_MARQUES_MOTEUR = "marque-moteur.php"; //F
	public static final String MARQUES_ID_TYPE = "idt"; //F
	public static final String URL_MARQUES_DISTRIBUEES = "marque-distrib.php"; //F
	public static final String URL_LIEUX = "lieu.php"; //F
	
	public static final String URL_MODELES = "modele-bateau.php"; //F
	public static final String MODELES_POUR = "wa"; //F
	public static final String MODELES_ID_TYPE = "idt"; //F
	public static final String MODELES_ID_CLIENT = "idcli"; //F
	public static final String MODELES_ID_MARQUE = "idm"; //F
	
	public static final String URL_ACTUALITES = "actu.php"; //F
	public static final String URL_ACTUALITE_DETAIL = "actu-detail.php"; //F
	public static final String ACTUALITE_DETAIL_ID_ACTUALITE = "idactu"; //F

	public static final String URL_VENDEURS = "resultat-client.php"; //F
	public static final String VENDEURS_REGION_ID = "idr"; //F
	public static final String VENDEURS_MARQUE_ID = "idm"; //F
	public static final String VENDEURS_SERVICE_ID = "ids"; //F
	public static final String VENDEURS_LONGITUDE = "maLong"; //F
	public static final String VENDEURS_LATITUDE = "maLat"; //F
	public static final String VENDEURS_VILLE_ID = "idVille"; //F
	public static final String VENDEURS_RAYON = "rayon"; //F
	

	public static final String URL_INFORMATIONS_VENDEUR = "client.php"; //F
	public static final String VENDEUR_INFORMATIONS_ID_CLIENT = "idcli"; //F
	
	public static final String URL_ANNONCES_BATEAUX = "resultat-bateau.php"; //F
	public static final String URL_ANNONCES_MOTEURS = "resultat-moteur.php"; //F
	public static final String URL_ANNONCES_DIVERS = "resultat-divers.php"; //F
	
	
	public static final String URL_ANNONCE_DETAIL_BATEAU = "detail-bateau.php"; //F
	public static final String URL_ANNONCE_DETAIL_MOTEUR = "detail-moteur.php"; //F
	public static final String URL_ANNONCE_DETAIL_DIVER = "detail-divers.php"; //F
	public static final String ANNONCES_DETAIL_ID_ANNONCE = "idad"; //F
	
	public static final String ANNONCES_TYPE_ID = "idt"; //F
	public static final String ANNONCES_CATEGORIE_ID = "idc"; //F
	public static final String ANNONCES_REGION_ID = "idr"; //F
	public static final String ANNONCES_MAX_TAILLE = "mxt"; //F
	public static final String ANNONCES_MIN_TAILLE = "mit"; //F
	public static final String ANNONCES_MAX_PUISS = "mxp"; //F
	public static final String ANNONCES_MIN_PUISS = "mip"; //F
	public static final String ANNONCES_MAX_PRIX = "mxp"; //F
	public static final String ANNONCES_MIN_PRIX = "mip"; //F
	public static final String ANNONCES_ETAT = "et"; //F
	
	public static final String ANNONCES_MODELE_ID = "idmo"; //F
	public static final String ANNONCES_MARQUE_ID = "idm"; //F
	
	
	
	
	public static final String URL_VENDRE = "getAnnonce.php"; //F
	
	public static final String VENDRE_TYPE = "type"; //F   -> "bateau","moteur","divers"
	public static final String VENDRE_ALEA_NUMBER = "aleaNumber"; //F
	public static final String VENDRE_NOM = "nom"; //F
	public static final String VENDRE_PRENOM = "prenom"; //F
	public static final String VENDRE_EMAIL = "email"; //F
	public static final String VENDRE_TEL_1 = "tel1"; //F
	public static final String VENDRE_CODE_POSTAL = "cp"; //F
	public static final String VENDRE_PAYS = "pays"; //F
	public static final String VENDRE_HASH = "hash"; //F
	
	public static final String VENDRE_IMAGE = "Filedata"; //F
	
	//VENTE BATEAU
	public static final String VENDRE_CATEGORIE = "listSsCat"; //F
	public static final String VENDRE_MODELE = "listModele"; //F
	public static final String VENDRE_LONGUEUR_AVANT_VIRGULE = "longueur_unit"; //F
	public static final String VENDRE_LONGUEUR_APRES_VIRGULE = "longueur_deci"; //F
	public static final String VENDRE_LONGUEUR_UNITE = "unites_long"; //F   -> 'm', 'ft' 
	public static final String VENDRE_ANNEE = "annee"; //F
	public static final String VENDRE_NOMBRE_MOTEUR = "nbmot"; //F
	public static final String VENDRE_NOMBRE_CABINE = "nbcab";
	public static final String VENDRE_NOMBRE_COUCHETTE = "nbcouch";
	public static final String VENDRE_NOMBRE_SALLE_DE_BAIN = "nbsdb";
	public static final String VENDRE_PUISSANCE_MOTEUR = "puissmot"; //F
	public static final String VENDRE_MARQUE_MOTEUR_ID = "listMarqueMoteur"; //F
	public static final String VENDRE_ANNEE_MOTEUR = "anneemot"; //F
	public static final String VENDRE_DESCRIPTION = "description"; //F
	public static final String VENDRE_PRIX = "prix"; //F
	
	public static final String VENDRE_MODELE_MOTEUR = "modelemot"; //F -> champ texte a remplir
	public static final String VENDRE_ENERGIE_ID = "idnrj"; //F
	
	public static final String VENDRE_INTITULE_DIVERS = "intituledivers"; //F
	
	
	//public static final String URL_ANNONCES_BATEAUX_DE = "xml-client-bateau.php?";
	//public static final String URL_ANNONCES_MOTEURS_DE = "xml-client-moteur.php?";
	//public static final String URL_ANNONCES_ACCESSOIRES_DE = "xml-client-accessoire.php?";
	public static final String ANNONCES_ID_CLIENT = "idcli"; //F
	
	public static final String ANNURAIRE_MARQUE = "listMarque";
	public static final String ANNURAIRE_SERVICE = "service";
	
	public static final String URL_ON_DEMAND = "bod.php"; //F
	public static final String VENDEUR_NOM = "nom"; //F
	public static final String VENDEUR_PRENOM = "prenom"; //F
	public static final String VENDEUR_EMAIL = "email"; //F
	public static final String VENDEUR_TEL_1 = "tel1"; //F
	public static final String VENDEUR_CODE_POSTAL = "cp"; //F
	public static final String VENDEUR_VILLE = "ville"; //F
	public static final String VENDEUR_PAYS = "pays"; //F
	
	public static final String ON_DEMAND_ORIGINE_VALUE = "BOD"; //F
	
	public static final String ON_DEMAND_ORIGINE = "origine"; //F
	public static final String ON_DEMAND_ETAT = "idtypads"; //F
	public static final String ON_DEMAND_TYPE = "listProduit"; //F
	public static final String ON_DEMAND_CATEGORIE = "listSsCat"; //F
	public static final String ON_DEMAND_MODELE = "listModele"; //F
	public static final String ON_DEMAND_TAILLE_MIN = "minlong"; //F
	public static final String ON_DEMAND_TAILLE_MAX = "maxlong"; //F
	public static final String ON_DEMAND_LIEU_ID = "listNav"; //F
	public static final String ON_DEMAND_COMMENTAIRE = "infosuprech"; //F
	public static final String ON_DEMAND_BUDGET = "budget"; //F
	
	public static final String ON_DEMAND_CATEGORIE_POSSEDE = "listSsCat2"; //F
	public static final String ON_DEMAND_TYPE_POSSEDE = "listProduit2"; //F
	public static final String ON_DEMAND_MARQUE_POSSEDE = "listMarque2"; //F
	public static final String ON_DEMAND_MODELE_POSSEDE = "listModele2"; //F
	public static final String ON_DEMAND_PRIX_CESSION = "budget2"; //F
	
	public static final String URL_NB_ANNONCES = "nbannonce.php"; //F
	public static final String URL_NB_ANNONCES_BATEAUX = "nbbateau.php"; //F
	public static final String URL_NB_ANNONCES_MOTEURS = "nbmoteur.php"; //F
	public static final String URL_NB_ANNONCES_DIVERS = "nbdivers.php"; //F

	
	
	public static final String URL_UPLOAD_PHOTO = "postPhoto.php";
	public static final String UPLOAD_PHOTO = "Filedata";
	public static final String URL_ENVOIE_ANNONCE = "getAnnonce.php";
	
	//elements du xml nbannonces
	public static final String NB_ANNONCES_BATEAUX = "bateau"; //F
	public static final String NB_ANNONCES_BATEAUX_MOTEURS = "bateaumot"; //F
	public static final String NB_ANNONCES_VOILIERS = "voile"; //F
	public static final String NB_ANNONCES_PNEUMATIQUES = "pneuma"; //F
	public static final String NB_ANNONCES_MOTEURS = "moteur"; //F
	public static final String NB_ANNONCES_DIVERS = "divers"; //F
	
	public static final String NB_ANNONCES_LOCATION = "location";
	
	public static final String URL_CREER_ALERTE = "alerte-action.php"; //F
	public static final String ALERTE_ID_SMARTPHONE = "idiphone"; //F (oui, idiphone ^^)
	public static final String ALERTE_ID_TYPE = "id";
	public static final String ALERTE_ID_CATEGORIE = "listSsCat"; //F
	public static final String ALERTE_MIN_LONG = "minlong"; //F
	public static final String ALERTE_MAX_LONG = "maxlong"; //F
	public static final String ALERTE_MIN_PRIX = "prixmin"; //F
	public static final String ALERTE_MAX_PRIX = "prixmax"; //F
	
	public static final String URL_SUPPRIMER_ALERTE = "alerte-action.php"; //F
	public static final String SUPPRIMER_ALERTE_ID = "id"; //F
	public static final String SUPPRIMER_ALERTE_DELETE = "delete"; //F
	

	public static final String DATE_MD5 = "kh"; //F
	public static final String PAGE = "pg"; //F
	
	public static final String ENVOIE_EMAIL_EMAIL = "email"; //F
	public static final String ENVOIE_EMAIL_MESSAGE = "mesg"; //F
	public static final String ENVOIE_EMAIL_ANNONCE_ID = "idad"; //F 
	public static final String ENVOIE_EMAIL_TYPE_ID = "type"; //F
	public static final String ENVOIE_EMAIL_NOM = "nom"; //F
	public static final String ENVOIE_EMAIL_TEL_1 = "tel1"; //F
	public static final String ENVOIE_EMAIL_DEPARTEMENT_ID = "iddept"; //F
	public static final String ENVOIE_EMAIL_CLIENT_DIRECT_ID = "clidirect"; //F
	
	public static final String URL_ENVOIE_EMAIL = "envoi-email.php"; //F

	public static final String URL_ALERTES = "alerte-listing.php"; //F
	public static final String ALERTE_JETON = "jeton"; //F
	
	public static final String SERVER_GOOGLE_CLOUD_ID = "AIzaSyB7IT3ndRmf9j_J94Y79Znyl1OptMp3e_s";
	public static final Object VENDRE_NOMBRE_HEURE = "nbhrmot";
	
}
