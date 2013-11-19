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
	public static final String ADRESSE_TEST_YOUBOAT = "http://www.google.fr";

	public static final String EcranNormal = "http://www.youboat.fr/xml/promo/splash_promo_normal.png";
	public static final String EcranRetina = "http://www.youboat.fr/xml/promo/splash_promo_retina.png";

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
	public static final String POST_ANNONCE = "getAnnonce.php";
	public static final String REQUEST_PUSH = "alerte.php?";
	public static final String ALERTE = "alerte";

	public static final String TITRE_MAIL = "A Voir ! % %";
	public static final String MESSAGE_MAIL_HTML = "";

	//TYPE
	public static final String BATEAU_A_MOTEUR = "1";
	public static final String VOILIER = "2";
	public static final String PNEU = "3";
	
	public static final String MOTEURS = "4";
	public static final String ACCESSOIRES = "5";
	public static final String PLACE_DE_PORT="6";

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

	public static final String TITLENEWS = "title";
	public static final String LINK = "link";
	public static final String IMAGE = "image";
	public static final String VENDEUR = "vendeur";
	public static final String INFO_MOTEUR="info_moteur";
	public static final String PROPULSION="propulsion";
	public static final String HEURE_MOTEUR="heure_moteur";
	public static final String PUISSANCE_MOTEUR= "puissance_moteur";
	public static final String CATEGORIE = "categorie";

	public static final String ENCLOSURE = "enclosure";
	public static final String URL= "url";


	public static final String DESCRIPTION = "description";

	public static final String NUMERO_VENDEUR = "numero_vendeur";
	public static final String NUMERO = "numero";
	public static final String TITLE = "title";
	public static final String MOTEUR = "moteur";
	public static final String LONGUEUR = "longueur";
	public static final String ANNEE = "annee";
	public static final String GPSLATITUDE = "gpslatitude";
	public static final String GPSLONGITUDE = "gpslongitude";
	public static final String TYPECLIENT = "typeclient";
	public static final String PHOTOS = "photos";
	public static final String PRIX = "prix";
	public static final String TAXEPRIX = "taxeprix";
	public static final String PUBDATE = "pubDate";
	public static final String PHOTOARRAY = "enclosure";
	public static final String BOATAD = "item";
	public static final String LIEN_ANNONCE="lien_annonce";
	public static final String ETAT="etat";
	public static final String TAXE = "taxe";
	public static final String COMMENTAIRE="commentaire";
	public static final String NB_CABINE="nb_cabine";
	public static final String NB_COUCH="nb_couch";
	public static final String NB_SDB="nb_sdb";
	public static final String GARANTIE= "garantie";
	public static final String NB_PHOTOS="nb_photos";
	public static final String PLACEPORT= "place_de_port";
	public static final String LARGEUR= "largeur";
	public static final String EQUIPEMENT = "equipement";
	public static final String ELECTRONIQUE = "electronique";
	public static final String NB_YOUBOAT = "nbann_youboat";

	public static final String ADRESSE="adresse";
	public static final String CP="cp";
	public static final String TEL1="tel1";
	public static final String TEL2="tel2";
	public static final String SITEWEB="siteweb";
	public static final String TYPE="type";
	public static final String NOMBRE_ANNONCE = "nombre_annonce";
	public static final String LOGO = "logo";
	public static final String FAX = "fax";
	public static final String SERVICES = "services";
	public static final String SERVICE = "service";
	public static final String HORAIRES = "horaires";
	public static final String CONTACT = "contact";
	public static final String NB_BATEAU ="nb_bateau";
	public static final String NB_MOTEUR = "nb_moteur";
	public static final String NB_ACCESSOIRE = "nb_accessoire";

	public static final String REGION = "region";
	public static final String MINPRIX = "minPrix";
	public static final String MAXPRIX = "maxPrix";
	public static final String MINTAILLE = "minTaille";
	public static final String MAXTAILLE = "maxTaille";
	public static final String MINPUISS = "minPuiss";
	public static final String MAXPUISS = "maxPuiss";
	public static final String CATEGORIE_LIB = "Categorie";
	public static final String REGION_LIB = "Region";
	public static final String MINPRIX_LIB = "Prix minimum";
	public static final String MAXPRIX_LIB = "Prix maximum";
	public static final String MINTAILLE_LIB = "Taille minimum";
	public static final String MAXTAILLE_LIB = "Taille maximum";
	public static final String MINPUISS_LIB = "Puissance minimum";
	public static final String MAXPUISS_LIB = "Puissance maximum";
	public static final String PAYS = "pays";

	public static final String MANCHE_OU_BRETAGNE="Manche/Bretagne";
	public static final String ATLANTIQUE="Atlantique";
	public static final String PACA="Paca";
	public static final String LANGUEDOC_OU_ROUSSILLON="Languedoc/Roussillon";
	public static final String DOM_TOM="Dom-Tom";
	public static final String FRANCE_AUTRE_REGIONS="France/Autre region";

	public static final int CRITERIA_CATEGORY=1;
	public static final int CRITERIA_REGION=2;
	public static final int CRITERIA_MINTAILLE=3;
	public static final int CRITERIA_MAXTAILLE=4;
	public static final int CRITERIA_MINPRIX=5;
	public static final int CRITERIA_MAXPRIX=6;
	public static final int CRITERIA_MINPUISS=7;
	public static final int CRITERIA_MAXPUISS=8;
	public static final int CRITERIA_MARQUEMODELE=47;

	public static final int LIGNE_CATEGORY = 0;
	public static final int LIGNE_REGION = 1;
	public static final int LIGNE_MINTAILLE = 2;
	public static final int LIGNE_MAXTAILLE = 3;
	public static final int LIGNE_MINPRIX = 4;
	public static final int LIGNE_MAXPRIX = 5;
	public static final int LIGNE_MINPUISS = 6;
	public static final int LIGNE_MAXPUISS = 7;
	    

	public static final String MINTAILLE_ONE="0";
	public static final String MINTAILLE_TWO="5m/16.4 pieds";
	public static final String MINTAILLE_THREE="7m/23 pieds";
	public static final String MINTAILLE_FOUR="9m/29.5 pieds";
	public static final String MINTAILLE_FIVE="11m/36 pieds";
	public static final String MINTAILLE_SIX="13m/42.6 pieds";

	public static final String MAXTAILLE_ONE="5m/16.4 pieds";
	public static final String MAXTAILLE_TWO="7m/23 pieds";
	public static final String MAXTAILLE_THREE="9m/29.5 pieds";
	public static final String MAXTAILLE_FOUR="11m/36 pieds";
	public static final String MAXTAILLE_FIVE="13m/42.6 pieds";
	public static final String MAXTAILLE_SIX="+";


	public static final String MINPUISS1="10";
	public static final String MINPUISS2="20";
	public static final String MINPUISS3="30";
	public static final String MINPUISS4="40";
	public static final String MINPUISS5="50";
	public static final String MINPUISS6="60";
	public static final String MINPUISS7="70";
	public static final String MINPUISS8="80";
	public static final String MINPUISS9="90";
	public static final String MINPUISS10="100";
	public static final String MINPUISS11="120";
	public static final String MINPUISS12="140";
	public static final String MINPUISS13="160";
	public static final String MINPUISS14="180";
	public static final String MINPUISS15="200";
	public static final String MINPUISS16="220";
	public static final String MINPUISS17="240";
	public static final String MINPUISS18="260";
	public static final String MINPUISS19="280";
	public static final String MINPUISS20="300";
	public static final String MINPUISS21="400";
	public static final String MINPUISS22="500";
	public static final String MINPUISS23="600";
	public static final String MINPUISS24="700";
	public static final String MINPUISS25="800";
	public static final String MINPUISS26="900";
	public static final String MINPUISS27="+900";

	public static final String MAXPUISS1="10";
	public static final String MAXPUISS2="20";
	public static final String MAXPUISS3="30";
	public static final String MAXPUISS4="40";
	public static final String MAXPUISS5="50";
	public static final String MAXPUISS6="60";
	public static final String MAXPUISS7="70";
	public static final String MAXPUISS8="80";
	public static final String MAXPUISS9="90";
	public static final String MAXPUISS10="100";
	public static final String MAXPUISS11="120";
	public static final String MAXPUISS12="140";
	public static final String MAXPUISS13="160";
	public static final String MAXPUISS14="180";
	public static final String MAXPUISS15="200";
	public static final String MAXPUISS16="220";
	public static final String MAXPUISS17="240";
	public static final String MAXPUISS18="260";
	public static final String MAXPUISS19="280";
	public static final String MAXPUISS20="300";
	public static final String MAXPUISS21="400";
	public static final String MAXPUISS22="500";
	public static final String MAXPUISS23="600";
	public static final String MAXPUISS24="700";
	public static final String MAXPUISS25="800";
	public static final String MAXPUISS26="900";
	public static final String MAXPUISS27="+900";


	public static final String NOM = "nom";
	public static final String PRENOM = "prenom";
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	public static final String CODE_POSTAL = "Code postal";
	public static final String VILLE = "ville";

	public static final String ETAT_VOULU = "Etat";
	public static final String TYPE_VOULU = "Type";
	public static final String CATEGORIE_VOULU = "Categorie";
	public static final String MARQUE_VOULU = "Marque";
	public static final String MODELE_VOULU = "Modele";
	public static final String MINTAILLE_VOULU = "Taille minimum";
	public static final String MAXTAILLE_VOULU = "Taille maximum";
	public static final String LIEU_VOULU = "Lieu";
	public static final String MARQUE_MOTEUR = "marque_moteur";
	public static final String BUDGET = "Budget";
	public static final String PRIX_SOUHAITE = "Prix souhaité";
	public static final String CARBURANT = "carburant";
	public static final String ENERGIE = "Energie";
	public static final String FORMATLONG = "Format longueur";

	public static final String TYPE_POSSEDE = "Type possede";
	public static final String CATEGORIE_POSSEDE = "Categorie possede";
	public static final String MARQUE_POSSEDE = "Marque possede";
	public static final String MODELE_POSSEDE = "Modele possede";

	public static final String TYPE_UPPERCASE_FIRST = "Type";


	public static final String OCCASION = "occasion";
	public static final String NEUF = "neuf";

	public static final String BATEAUAMOTEUR = "Bateau a Moteur";
	public static final String VOILIERS = "Voilier";
	public static final String PNEU_SEMI_RIGIDES = "Pneumatique/Semi-Rigides";
	/*
	public static final int CRITERIA_PRENOM=1;
	public static final int CRITERIA_EMAIL=2;
	public static final int CRITERIA_TEL=3;
	public static final int CRITERIA_CODE_POSTAL=4;
	public static final int CRITERIA_VILLE=5;
	public static final int CRITERIA_ETAT_VOULU=6;
	public static final int CRITERIA_TYPE_VOULU=7;
	public static final int CRITERIA_CATEGORIE_VOULU=8;
	public static final int CRITERIA_MARQUE_VOULU=9;
	public static final int CRITERIA_MODELE_VOULU=10;
	public static final int CRITERIA_MINTAILLE_VOULU=11;
	public static final int CRITERIA_MAXTAILLE_VOULU=12;
	public static final int CRITERIA_LIEU_VOULU=13;
	public static final int CRITERIA_BUDGET=14;
	public static final int CRITERIA_PRIX_SOUHAITE=15;
	public static final int CRITERIA_TYPE_POSSEDE=16;
	public static final int CRITERIA_CATEGORIE_POSSEDE=17;
	public static final int CRITERIA_MARQUE_POSSEDE=18;
	public static final int CRITERIA_MODELE_POSSEDE=19;
	*/

	public static final int CRITERIA_PRENOM=9;
	public static final int CRITERIA_EMAIL=10;
	public static final int CRITERIA_TEL=11;
	public static final int CRITERIA_CODE_POSTAL=12;
	public static final int CRITERIA_VILLE=13;
	public static final int CRITERIA_ETAT_VOULU=14;
	public static final int CRITERIA_TYPE_VOULU=15;
	public static final int CRITERIA_CATEGORIE_VOULU=16;
	public static final int CRITERIA_MARQUE_VOULU=17;
	public static final int CRITERIA_MODELE_VOULU=18;
	public static final int CRITERIA_MINTAILLE_VOULU=19;
	public static final int CRITERIA_MAXTAILLE_VOULU=20;
	public static final int CRITERIA_LIEU_VOULU=21;
	public static final int CRITERIA_BUDGET=22;
	public static final int CRITERIA_PRIX_SOUHAITE=23;
	public static final int CRITERIA_TYPE_POSSEDE=24;
	public static final int CRITERIA_CATEGORIE_POSSEDE=25;
	public static final int CRITERIA_MARQUE_POSSEDE=26;
	public static final int CRITERIA_MODELE_POSSEDE=27;
	public static final int CRITERIA_NOM=28;

	public static final int CRITERIA_TYPEGET = 29;
	public static final int CRITERIA_ALEANUM = 30;

	public static final int CRITERIA_ANNEE = 32;
	public static final int CRITERIA_LONG = 33;
	public static final int CRITERIA_FORMATLONG =34;
	public static final int CRITERIA_NBMOTEUR = 31;
	public static final int CRITERIA_PUISSMOTEUR = 35;
	public static final int CRITERIA_ANNEEMOTEUR = 36;
	public static final int CRITERIA_DESCRIPTION = 37;
	public static final int CRITERIA_MARQUE_MOTEUR = 38;
	public static final int CRITERIA_CARBURANT = 39;
	public static final int CRITERIA_ENERGIE = 40;
	public static final int CRITERIA_INTITULE = 41;


	public static final int CRITERIA_TYPE_BATEAU = 42;
	public static final int CRITERIA_CATEGORIE = 43;
	public static final int CRITERIA_MARQUE = 44;
	public static final int CRITERIA_MODELE = 45;
	public static final int CRITERIA_MODELE_MOTEUR = 46;
	public static final int CRITERIA_PAYS = 47;
	public static final int CRITERIA_ETAT = 49;
	public static final int CRITERIA_PRIX = 48;
	public static final int CRITERIA_LOGUEUR = 50;

	// indice des lignes dans BoatOnDemand

	public static final int LIGNE_NOM = 0;
	public static final int LIGNE_PRENOM = 1;
	public static final int LIGNE_EMAIL = 2;
	public static final int LIGNE_TEL = 3;
	public static final int LIGNE_CODE_POSTAL = 4;
	public static final int LIGNE_VILLE = 5;
	public static final int LIGNE_ETAT_VOULU = 6;
	public static final int LIGNE_TYPE_VOULU = 7;
	public static final int LIGNE_CATEGORIE_VOULU = 8;
	public static final int LIGNE_MARQUE_VOULU = 9;
	public static final int LIGNE_MODELE_VOULU = 10;
	public static final int LIGNE_MINTAILLE_VOULU = 11;
	public static final int LIGNE_MAXTAILLE_VOULU = 12;
	public static final int LIGNE_LIEU_VOULU = 13;
	public static final int LIGNE_BUDGET = 14;
	public static final int LIGNE_PRIX_SOUHAITE = 15;
	public static final int LIGNE_TYPE_POSSEDE = 16;
	public static final int LIGNE_CATEGORIE_POSSEDE = 17;
	public static final int LIGNE_MARQUE_POSSEDE = 18;
	public static final int LIGNE_MODELE_POSSEDE = 19;
	public static final int LIGNE_COMMENTAIRE = 20;

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
	
}
