package fr.RivaMedia;

public class Constantes {	

	// adresses des services
	public static final int CC_MD5_DIGEST_LENGTH = 16;
	// utilise par we kiss pour le md(#define APPLICATION_KEY "a#i4$5n_DaPs"

	public static final String kFBApplicationID = "222700434429506";
	public static final String KFBAPISecretKey = "0e6b3af83b47dd755b115613629c486c";
	public static final String kFBApiKey = "5173bf4938dc54424d5e4a43bc67e123";

	public static final String CONFIDENTIAL = "confidential";
	public static final String CONFIDENTIAL_PUBLIC = "0";
	public static final String CONFIDENTIAL_PRIVATE = "1";

	public static final int    SIZE_MIN = 400;
	public static final String UPLOAD_PHOTO_WEB_SERVICE = "http://www.youboat.fr/xml/postPhoto.php";
	public static final String FINALIZE_PAYMENT_WEBSERVICE = "http://www.youboat.fr/xml/validPA.php";

	public static final String EcranNormal = "http://www.youboat.fr/xml/promo/splash_promo_normal.png";
	public static final String EcranRetina = "http://www.youboat.fr/xml/promo/splash_promo_retina.png";

	/*
	public static final String BASE_ADRESS = "http://www.youboat.fr/xml/";

	public static final String CATEGORY_ADRESS_COMPLEMENT = "xml-categorie.php?";
	public static final String MARQUE_ADRESS_COMPLEMENT = "xml-marque-bateau.php?";
	public static final String MODELE_ADRESS_COMPLEMENT = "xml-modele-bateau.php?";
	public static final String MODELE_MOTEUR_ADRESS_COMPLEMENT = "xml-modele-moteur.php?";


	public static final String BOD_ADRESS_COMPLEMENT = "bod.php?";
	
	public static final String NEWS_ADRESS_COMPLEMENT = "xml-actu-bateau.php?";
	//public static final String MARQUE_MOTEUR_ADRESS_COMPLEMENT = "xml-marque-moteur.php?";
	public static final String MARQUE_MOTEUR_ADRESS_COMPLEMENT = "xml-marque-moteur-rech.php?";
	//public static final String RECHERCHE_BATEAU_ADRESSE = "xml-resultat-bateau.php?q=1&idcat=8 c ";
	public static final String RECHERCHE_BATEAU_ADRESSE = "xml-resultat-bateau.php?";
	//public static final String RECHERCHE_VOILIER_ADRESSE = "xml-resultat-voilier.php?q=";
	public static final String RECHERCHE_VOILIER_ADRESSE = "xml-resultat-voilier.php?";
	public static final String RECHERCHE_PNEUMA_ADRESSE = "xml-resultat-pneuma.php?";
	public static final String RECHERCHE_MOTEUR_ADRESSE = "xml-resultat-moteur.php?";
	public static final String RECHERCHE_ACCESSOIRE_ADRESSE = "xml-resultat-accessoire.php?";
	public static final String RECHERCHE_ACTU_DETAIL_ADRESS = "xml-actu-detail.php?idredac=";
	*/
	
	public static final String POST_ANNONCE = "getAnnonce.php";
	public static final String REQUEST_PUSH = "alerte.php?";
	public static final String ALERTE = "alerte";

	public static final String TITRE_MAIL = "A Voir ! % %";
	public static final String MESSAGE_MAIL_HTML = "";



	public static final String URL_APPLI = "urlutils";
	public static final String TYPE_ID = "idtype";
	public static final String CATEGORIE_BATEAU="categorie_bateau";
	public static final String IDM = "idm";
	public static final String MARQUE = "marque";
	public static final String MODELE = "modele";
	public static final String MODELE_MOTEUR = "modele_moteur";
	public static final String NEWS = "item";
	public static final String NEWS_DETAIL = "actu_detail";
	public static final String ID = "id";
	public static final String BOATDETAIL = "detail_bateau";
	public static final String MOTEURDETAIL = "detail_moteur";
	public static final String DIVERSDETAIL = "detail_divers";
	public static final String CLIENT = "client";
	public static final String INFO_CLIENT = "info_client";

	// constantes pour le In App purchase

	public static final String ANNONCE_IDENTIFIER="RVYB01ADDEPOT";

	public static final int TVINFO = 0;
	public static final int TVINFPLUS = 1;
	public static final int EQUIPE = 2;
	public static final int ELECTRO = 3;


	public static final String ID_CATEGORIE = "idcat_bateau";
	public static final String TAILLE_MIN = "taille_min";
	public static final String TAILLE_MAX = "taille_max";
	public static final String BUDGET_MIN = "budget_min";
	public static final String BUDGET_MAX = "budget_max";
	public static final String DATE = "date";


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
	
	public static final String URL_PUB_MAGASINE_NORMAL = "http://www.youboat.fr/xml/promo/splash_promo_normal.png";
	public static final String URL_PUB_MAGASINE_HD = "http://www.youboat.fr/xml/promo/splash_promo_retina.png";
	
	public static final String URL_TYPES_ANNONCES = "type.php"; //F
	public static final String URL_REGIONS = "region.php"; //F
	public static final String URL_ETATS = "etat.php"; //F
	public static final String URL_ENERGIES = "nrj.php"; //F
	
	public static final String URL_DEPARTEMENTS = "departement.php"; //F
	
	public static final String URL_TYPES_CATEGORIES = "categorie.php";  //F
	public static final String CATEGORIES_TYPE_ID = "idt"; //F
	
	public static final String URL_SERVICES = "xml-services.php?";
	public static final String URL_MARQUES = "marque-bateau.php"; //F
	public static final String MARQUES_POUR = "wa"; //F
	public static final String MARQUES_POUR_CLIENT = "idcli"; //F
	public static final String URL_MARQUES_MOTEUR = "marque-moteur.php"; //F
	public static final String MARQUES_ID_TYPE = "idt"; //F
	
	public static final String URL_MODELES = "modele-bateau.php"; //F
	public static final String MODELES_POUR = "wa"; //F
	public static final String MODELES_ID_TYPE = "idt"; //F
	public static final String MODELES_ID_CLIENT = "idcli"; //F
	public static final String MODELES_ID_MARQUE = "idm"; //F
	
	public static final String URL_ACTUALITES = "actu.php"; //F
	public static final String URL_ACTUALITE_DETAIL = "actu-detail.php"; //F
	public static final String ACTUALITE_DETAIL_ID_ACTUALITE = "idactu"; //F

	public static final String URL_VENDEURS = "xml-client-region2.php?";

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
	public static final String VENDRE_PUISSANCE_MOTEUR = "puissmot"; //F
	public static final String VENDRE_MARQUE_MOTEUR_ID = "listMarqueMoteur"; //F
	public static final String VENDRE_ANNEE_MOTEUR = "anneemot"; //F
	public static final String VENDRE_DESCRIPTION = "description"; //F
	public static final String VENDRE_PRIX = "prix"; //F
	
	public static final String VENDRE_MODELE_MOTEUR = "modelemot"; //F -> champ texte a remplir
	public static final String VENDRE_ENERGIE_ID = "idnrj"; //F
	
	public static final String VENDRE_INTITULE_DIVERS = "intituledivers"; //F
	
	
	public static final String URL_ANNONCES_BATEAUX_DE = "xml-client-bateau.php?";
	public static final String URL_ANNONCES_MOTEURS_DE = "xml-client-moteur.php?";
	public static final String URL_ANNONCES_ACCESSOIRES_DE = "xml-client-accessoire.php?";
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

	
	
	public static final String URL_UPLOAD_PHOTO = "postPhoto.php";
	public static final String UPLOAD_PHOTO = "Filedata";
	public static final String URL_ENVOIE_ANNONCE = "getAnnonce.php";
	
	public static final String URL_NB_ANNONCES = "xml-nbannonce.php";
	public static final String NB_ANNONCES_BATEAUX = "bateau";
	public static final String NB_ANNONCES_BATEAUX_MOTEURS = "bateaumot";
	public static final String NB_ANNONCES_VOILIERS = "voile";
	public static final String NB_ANNONCES_PNEUMATIQUES = "pneuma";
	public static final String NB_ANNONCES_MOTEURS = "moteur";
	public static final String NB_ANNONCES_DIVERS = "divers";
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

	
	
}
