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
	
	//URL
	
	public static final String URL_BASE = "http://www.youboat.fr/xml/";
	
	public static final String URL_PUB_MAGASINE_NORMAL = "http://www.youboat.fr/xml/promo/splash_promo_normal.png";
	public static final String URL_PUB_MAGASINE_HD = "http://www.youboat.fr/xml/promo/splash_promo_retina.png";
	
	public static final String URL_TYPES = "xml-categorie.php?";
	public static final String TYPES_TYPE_ID = "idtype";
	
	public static final String URL_SERVICES = "xml-services.php?";
	public static final String URL_MARQUES = "xml-marque-bateau-rech.php";
	public static final String URL_MARQUES_MOTEUR = "xml-marque-moteur-rech.php?";
	public static final String MARQUES_ID_TYPE = "idtype";
	
	public static final String URL_MODELES = "xml-modele-bateau-rech.php?";
	public static final String MODELES_ID_TYPE = "idtype";
	public static final String MODELES_ID_MARQUE = "idm";
	
	public static final String URL_ACTUALITES = "xml-actu-bateau.php?";
	public static final String URL_ACTUALITE_DETAIL = "xml-actu-detail.php?";
	public static final String ACTUALITE_DETAIL_ID_ACTUALITE = "idredac";

	public static final String URL_VENDEURS = "xml-client-region2.php?";

	public static final String URL_INFORMATIONS_VENDEUR = "xml-client-info.php?";
	public static final String VENDEUR_INFORMATIONS_ID_CLIENT = "idcli";
	
	public static final String URL_ANNONCES_BATEAUX_A_MOTEURS = "xml-resultat-bateau.php?";
	public static final String URL_ANNONCES_BATEAUX_VOILIERS = "xml-resultat-voilier.php?";
	public static final String URL_ANNONCES_BATEAUX_PNEUMATIQUES = "xml-resultat-pneuma.php?";
	public static final String URL_ANNONCES_MOTEURS = "xml-resultat-moteur.php?";
	public static final String URL_ANNONCES_ACCESSOIRES = "xml-resultat-accessoire.php?";
	
	public static final String ANNONCES_CATEGORIE_ID = "idcat";
	public static final String ANNONCES_REGION_ID = "idregion";
	public static final String ANNONCES_MAX_TAILLE = "maxtaille";
	public static final String ANNONCES_MIN_TAILLE = "mintaille";
	public static final String ANNONCES_MAX_PUISS = "maxpuiss";
	public static final String ANNONCES_MIN_PUISS = "minpuiss";
	public static final String ANNONCES_MAX_PRIX = "maxprix";
	public static final String ANNONCES_MIN_PRIX = "minprix";
	public static final String ANNONCES_ETAT = "etat";
	public static final String ANNONCES_ETAT_OCCASION = "1";
	public static final String ANNONCES_ETAT_NEUF = "2";
	
	public static final String ANNONCES_MODELE_ID = "listModele";
	public static final String ANNONCES_MARQUE_ID = "listMarque";
	
	public static final String VENDEUR_NOM = "nom";
	public static final String VENDEUR_PRENOM = "prenom";
	public static final String VENDEUR_EMAIL = "email";
	public static final String VENDEUR_TEL_1 = "tel1";
	public static final String VENDEUR_CODE_POSTAL = "cp";
	public static final String VENDEUR_PAYS = "pays";
	
	public static final String URL_ANNONCES_BATEAUX_DE = "xml-client-bateau.php?";
	public static final String URL_ANNONCES_MOTEURS_DE = "xml-client-moteur.php?";
	public static final String URL_ANNONCES_ACCESSOIRES_DE = "xml-client-accessoire.php?";
	public static final String ANNONCES_ID_CLIENT = "idcli";
	
	public static final String URL_ON_DEMAND = "bod.php?";

}
