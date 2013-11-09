//
//  Constantes.h
//  YouBoat
//
//  Created by qingyan guo on 13/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

// adresses des services
#define CC_MD5_DIGEST_LENGTH	16
// utilisé par we kiss pour le md(#define APPLICATION_KEY @"a#i4$5n_DaPs"

#define kFBApplicationID @"222700434429506"
#define KFBAPISecretKey @"0e6b3af83b47dd755b115613629c486c"
#define kFBApiKey @"5173bf4938dc54424d5e4a43bc67e123"
#define CONFIDENTIAL @"confidential"
#define CONFIDENTIAL_PUBLIC @"0"
#define CONFIDENTIAL_PRIVATE @"1"
#define SIZE_MIN 400
#define UPLOAD_PHOTO_WEB_SERVICE @"http://www.youboat.fr/xml/postPhoto.php"
#define FINALIZE_PAYMENT_WEBSERVICE @"http://www.youboat.fr/xml/validPA.php"
#define ADRESSE_TEST_YOUBOAT @"http://www.google.fr"

static NSString * const EcranNormal = @"http://www.youboat.fr/xml/promo/splash_promo_normal.png";
static NSString * const EcranRetina = @"http://www.youboat.fr/xml/promo/splash_promo_retina.png";

static NSString * const BASE_ADRESS = @"http://www.youboat.fr/xml/";
static NSString * const CATEGORY_ADRESS_COMPLEMENT = @"xml-categorie.php?";
static NSString * const MARQUE_ADRESS_COMPLEMENT = @"xml-marque-bateau.php?";
static NSString * const MODELE_ADRESS_COMPLEMENT = @"xml-modele-bateau.php?";
static NSString * const MODELE_MOTEUR_ADRESS_COMPLEMENT = @"xml-modele-moteur.php?";

static NSString * const BOD_ADRESS_COMPLEMENT = @"bod.php?";
static NSString * const NEWS_ADRESS_COMPLEMENT = @"xml-actu-bateau.php?";
//static NSString * const MARQUE_MOTEUR_ADRESS_COMPLEMENT = @"xml-marque-moteur.php?";
static NSString * const MARQUE_MOTEUR_ADRESS_COMPLEMENT = @"xml-marque-moteur-rech.php?";
//static NSString * const RECHERCHE_BATEAU_ADRESSE = @"xml-resultat-bateau.php?q=1&idcat=8 c ";
static NSString * const RECHERCHE_BATEAU_ADRESSE = @"xml-resultat-bateau.php?";
//static NSString * const RECHERCHE_VOILIER_ADRESSE = @"xml-resultat-voilier.php?q=";
static NSString * const RECHERCHE_VOILIER_ADRESSE = @"xml-resultat-voilier.php?";
static NSString * const RECHERCHE_PNEUMA_ADRESSE = @"xml-resultat-pneuma.php?";
static NSString * const RECHERCHE_MOTEUR_ADRESSE = @"xml-resultat-moteur.php?";
static NSString * const RECHERCHE_ACCESSOIRE_ADRESSE = @"xml-resultat-accessoire.php?";
static NSString * const RECHERCHE_ACTU_DETAIL_ADRESS = @"xml-actu-detail.php?idredac=";
static NSString* const POST_ANNONCE = @"getAnnonce.php";
static NSString* const REQUEST_PUSH = @"alerte.php?";
static NSString* const ALERTE = @"alerte";

static NSString * const TITRE_MAIL = @"A Voir ! %@ %@";
static NSString * const MESSAGE_MAIL_HTML = @"";

static NSString * const BATEAU_A_MOTEUR = @"1";
static NSString * const VOILIER = @"2";
static NSString * const PNEU = @"3";
static NSString * const MOTEURS = @"4";
static NSString * const ACCESSOIRES = @"5";
static NSString * const PLACE_DE_PORT=@"6";

static NSString * const URL_APPLI = @"urlutils";
static NSString * const TYPE_ID = @"idtype";
static NSString * const CATEGORIE_BATEAU=@"categorie_bateau";
static NSString * const IDM = @"idm";
static NSString * const MARQUE = @"marque";
static NSString * const MODELE = @"modele";
static NSString * const MODELE_MOTEUR = @"modele_moteur";
static NSString * const NEWS = @"item";
static NSString * const NEWS_DETAIL = @"actu_detail";
static NSString * const ID = @"id";
static NSString * const BOATDETAIL = @"detail_bateau";
static NSString * const MOTEURDETAIL = @"detail_moteur";
static NSString * const DIVERSDETAIL = @"detail_divers";
static NSString * const CLIENT = @"client";
static NSString * const INFO_CLIENT = @"info_client";

static NSString * const TITLENEWS = @"title";
static NSString * const LINK = @"link";
static NSString * const IMAGE = @"image";
static NSString * const VENDEUR = @"vendeur";
static NSString * const INFO_MOTEUR=@"info_moteur";
static NSString * const PROPULSION=@"propulsion";
static NSString * const HEURE_MOTEUR=@"heure_moteur";
static NSString * const PUISSANCE_MOTEUR= @"puissance_moteur";
static NSString * const CATEGORIE = @"categorie";

static NSString * const ENCLOSURE = @"enclosure";
static NSString * const URL= @"url";


static NSString * const DESCRIPTION = @"description";

static NSString * const NUMERO_VENDEUR = @"numero_vendeur";
static NSString * const NUMERO = @"numero";
static NSString * const TITLE = @"title";
static NSString * const MOTEUR = @"moteur";
static NSString * const LONGUEUR = @"longueur";
static NSString * const ANNEE = @"annee";
static NSString * const GPSLATITUDE = @"gpslatitude";
static NSString * const GPSLONGITUDE = @"gpslongitude";
static NSString * const TYPECLIENT = @"typeclient";
static NSString * const PHOTOS = @"photos";
static NSString * const PRIX = @"prix";
static NSString * const TAXEPRIX = @"taxeprix";
static NSString * const PUBDATE = @"pubDate";
static NSString * const PHOTOARRAY = @"enclosure";
static NSString * const BOATAD = @"item";
static NSString * const LIEN_ANNONCE=@"lien_annonce";
static NSString * const ETAT=@"etat";
static NSString * const TAXE = @"taxe";
static NSString * const COMMENTAIRE=@"commentaire";
static NSString * const NB_CABINE=@"nb_cabine";
static NSString * const NB_COUCH=@"nb_couch";
static NSString * const NB_SDB=@"nb_sdb";
static NSString * const GARANTIE= @"garantie";
static NSString * const NB_PHOTOS=@"nb_photos";
static NSString * const PLACEPORT= @"place_de_port";
static NSString * const LARGEUR= @"largeur";
static NSString * const EQUIPEMENT = @"equipement";
static NSString * const ELECTRONIQUE = @"electronique";
static NSString * const NB_YOUBOAT = @"nbann_youboat";

static NSString * const ADRESSE=@"adresse";
static NSString * const CP=@"cp";
static NSString * const TEL1=@"tel1";
static NSString * const TEL2=@"tel2";
static NSString * const SITEWEB=@"siteweb";
static NSString * const TYPE=@"type";
static NSString * const NOMBRE_ANNONCE = @"nombre_annonce";
static NSString * const LOGO = @"logo";
static NSString * const FAX = @"fax";
static NSString * const SERVICES = @"services";
static NSString * const SERVICE = @"service";
static NSString * const HORAIRES = @"horaires";
static NSString * const CONTACT = @"contact";
static NSString * const NB_BATEAU =@"nb_bateau";
static NSString * const NB_MOTEUR = @"nb_moteur";
static NSString * const NB_ACCESSOIRE = @"nb_accessoire";

static NSString * const REGION = @"region";
static NSString * const MINPRIX = @"minPrix";
static NSString * const MAXPRIX = @"maxPrix";
static NSString * const MINTAILLE = @"minTaille";
static NSString * const MAXTAILLE = @"maxTaille";
static NSString * const MINPUISS = @"minPuiss";
static NSString * const MAXPUISS = @"maxPuiss";
static NSString * const CATEGORIE_LIB = @"Categorie";
static NSString * const REGION_LIB = @"Region";
static NSString * const MINPRIX_LIB = @"Prix minimum";
static NSString * const MAXPRIX_LIB = @"Prix maximum";
static NSString * const MINTAILLE_LIB = @"Taille minimum";
static NSString * const MAXTAILLE_LIB = @"Taille maximum";
static NSString * const MINPUISS_LIB = @"Puissance minimum";
static NSString * const MAXPUISS_LIB = @"Puissance maximum";
static NSString * const PAYS = @"pays";

static NSString * const MANCHE_OU_BRETAGNE=@"Manche/Bretagne";
static NSString * const ATLANTIQUE=@"Atlantique";
static NSString * const PACA=@"Paca";
static NSString * const LANGUEDOC_OU_ROUSSILLON=@"Languedoc/Roussillon";
static NSString * const DOM_TOM=@"Dom-Tom";
static NSString * const FRANCE_AUTRE_REGIONS=@"France/Autre region";

static int const CRITERIA_CATEGORY=1;
static int const CRITERIA_REGION=2;
static int const CRITERIA_MINTAILLE=3;
static int const CRITERIA_MAXTAILLE=4;
static int const CRITERIA_MINPRIX=5;
static int const CRITERIA_MAXPRIX=6;
static int const CRITERIA_MINPUISS=7;
static int const CRITERIA_MAXPUISS=8;
static int const CRITERIA_MARQUEMODELE=47;

static const int LIGNE_CATEGORY = 0;
static const int LIGNE_REGION = 1;
static const int LIGNE_MINTAILLE = 2;
static const int LIGNE_MAXTAILLE = 3;
static const int LIGNE_MINPRIX = 4;
static const int LIGNE_MAXPRIX = 5;
static const int LIGNE_MINPUISS = 6;
static const int LIGNE_MAXPUISS = 7;
    

static NSString * const MINTAILLE_ONE=@"0";
static NSString * const MINTAILLE_TWO=@"5m/16.4 pieds";
static NSString * const MINTAILLE_THREE=@"7m/23 pieds";
static NSString * const MINTAILLE_FOUR=@"9m/29.5 pieds";
static NSString * const MINTAILLE_FIVE=@"11m/36 pieds";
static NSString * const MINTAILLE_SIX=@"13m/42.6 pieds";

static NSString * const MAXTAILLE_ONE=@"5m/16.4 pieds";
static NSString * const MAXTAILLE_TWO=@"7m/23 pieds";
static NSString * const MAXTAILLE_THREE=@"9m/29.5 pieds";
static NSString * const MAXTAILLE_FOUR=@"11m/36 pieds";
static NSString * const MAXTAILLE_FIVE=@"13m/42.6 pieds";
static NSString * const MAXTAILLE_SIX=@"+";


static NSString * const MINPUISS1=@"10";
static NSString * const MINPUISS2=@"20";
static NSString * const MINPUISS3=@"30";
static NSString * const MINPUISS4=@"40";
static NSString * const MINPUISS5=@"50";
static NSString * const MINPUISS6=@"60";
static NSString * const MINPUISS7=@"70";
static NSString * const MINPUISS8=@"80";
static NSString * const MINPUISS9=@"90";
static NSString * const MINPUISS10=@"100";
static NSString * const MINPUISS11=@"120";
static NSString * const MINPUISS12=@"140";
static NSString * const MINPUISS13=@"160";
static NSString * const MINPUISS14=@"180";
static NSString * const MINPUISS15=@"200";
static NSString * const MINPUISS16=@"220";
static NSString * const MINPUISS17=@"240";
static NSString * const MINPUISS18=@"260";
static NSString * const MINPUISS19=@"280";
static NSString * const MINPUISS20=@"300";
static NSString * const MINPUISS21=@"400";
static NSString * const MINPUISS22=@"500";
static NSString * const MINPUISS23=@"600";
static NSString * const MINPUISS24=@"700";
static NSString * const MINPUISS25=@"800";
static NSString * const MINPUISS26=@"900";
static NSString * const MINPUISS27=@"+900";

static NSString * const MAXPUISS1=@"10";
static NSString * const MAXPUISS2=@"20";
static NSString * const MAXPUISS3=@"30";
static NSString * const MAXPUISS4=@"40";
static NSString * const MAXPUISS5=@"50";
static NSString * const MAXPUISS6=@"60";
static NSString * const MAXPUISS7=@"70";
static NSString * const MAXPUISS8=@"80";
static NSString * const MAXPUISS9=@"90";
static NSString * const MAXPUISS10=@"100";
static NSString * const MAXPUISS11=@"120";
static NSString * const MAXPUISS12=@"140";
static NSString * const MAXPUISS13=@"160";
static NSString * const MAXPUISS14=@"180";
static NSString * const MAXPUISS15=@"200";
static NSString * const MAXPUISS16=@"220";
static NSString * const MAXPUISS17=@"240";
static NSString * const MAXPUISS18=@"260";
static NSString * const MAXPUISS19=@"280";
static NSString * const MAXPUISS20=@"300";
static NSString * const MAXPUISS21=@"400";
static NSString * const MAXPUISS22=@"500";
static NSString * const MAXPUISS23=@"600";
static NSString * const MAXPUISS24=@"700";
static NSString * const MAXPUISS25=@"800";
static NSString * const MAXPUISS26=@"900";
static NSString * const MAXPUISS27=@"+900";


static NSString * const NOM = @"nom";
static NSString * const PRENOM = @"prenom";
static NSString * const EMAIL = @"email";
static NSString * const TEL = @"tel";
static NSString * const CODE_POSTAL = @"Code postal";
static NSString * const VILLE = @"ville";

static NSString * const ETAT_VOULU = @"Etat";
static NSString * const TYPE_VOULU = @"Type";
static NSString * const CATEGORIE_VOULU = @"Categorie";
static NSString * const MARQUE_VOULU = @"Marque";
static NSString * const MODELE_VOULU = @"Modèle";
static NSString * const MINTAILLE_VOULU = @"Taille minimum";
static NSString * const MAXTAILLE_VOULU = @"Taille maximum";
static NSString * const LIEU_VOULU = @"Lieu";
static NSString * const MARQUE_MOTEUR = @"marque_moteur";
static NSString * const BUDGET = @"Budget";
static NSString * const PRIX_SOUHAITE = @"Prix souhaité";
static NSString * const CARBURANT = @"carburant";
static NSString * const ENERGIE = @"Energie";
static NSString * const FORMATLONG = @"Format longueur";

static NSString * const TYPE_POSSEDE = @"Type possede";
static NSString * const CATEGORIE_POSSEDE = @"Categorie possede";
static NSString * const MARQUE_POSSEDE = @"Marque possede";
static NSString * const MODELE_POSSEDE = @"Modele possede";

static NSString* const TYPE_UPPERCASE_FIRST = @"Type";


static NSString * const OCCASION = @"occasion";
static NSString * const NEUF = @"neuf";

static NSString * const BATEAUAMOTEUR = @"Bateau à Moteur";
static NSString * const VOILIERS = @"Voilier";
static NSString * const PNEU_SEMI_RIGIDES = @"Pneumatique/Semi-Rigides";
/*
static int const CRITERIA_PRENOM=1;
static int const CRITERIA_EMAIL=2;
static int const CRITERIA_TEL=3;
static int const CRITERIA_CODE_POSTAL=4;
static int const CRITERIA_VILLE=5;
static int const CRITERIA_ETAT_VOULU=6;
static int const CRITERIA_TYPE_VOULU=7;
static int const CRITERIA_CATEGORIE_VOULU=8;
static int const CRITERIA_MARQUE_VOULU=9;
static int const CRITERIA_MODELE_VOULU=10;
static int const CRITERIA_MINTAILLE_VOULU=11;
static int const CRITERIA_MAXTAILLE_VOULU=12;
static int const CRITERIA_LIEU_VOULU=13;
static int const CRITERIA_BUDGET=14;
static int const CRITERIA_PRIX_SOUHAITE=15;
static int const CRITERIA_TYPE_POSSEDE=16;
static int const CRITERIA_CATEGORIE_POSSEDE=17;
static int const CRITERIA_MARQUE_POSSEDE=18;
static int const CRITERIA_MODELE_POSSEDE=19;*/

static int const CRITERIA_PRENOM=9;
static int const CRITERIA_EMAIL=10;
static int const CRITERIA_TEL=11;
static int const CRITERIA_CODE_POSTAL=12;
static int const CRITERIA_VILLE=13;
static int const CRITERIA_ETAT_VOULU=14;
static int const CRITERIA_TYPE_VOULU=15;
static int const CRITERIA_CATEGORIE_VOULU=16;
static int const CRITERIA_MARQUE_VOULU=17;
static int const CRITERIA_MODELE_VOULU=18;
static int const CRITERIA_MINTAILLE_VOULU=19;
static int const CRITERIA_MAXTAILLE_VOULU=20;
static int const CRITERIA_LIEU_VOULU=21;
static int const CRITERIA_BUDGET=22;
static int const CRITERIA_PRIX_SOUHAITE=23;
static int const CRITERIA_TYPE_POSSEDE=24;
static int const CRITERIA_CATEGORIE_POSSEDE=25;
static int const CRITERIA_MARQUE_POSSEDE=26;
static int const CRITERIA_MODELE_POSSEDE=27;
static int const CRITERIA_NOM=28;

static int const CRITERIA_TYPEGET = 29;
static int const CRITERIA_ALEANUM = 30;

static int const CRITERIA_ANNEE = 32;
static int const CRITERIA_LONG = 33;
static int const CRITERIA_FORMATLONG =34;
static int const CRITERIA_NBMOTEUR = 31;
static int const CRITERIA_PUISSMOTEUR = 35;
static int const CRITERIA_ANNEEMOTEUR = 36;
static int const CRITERIA_DESCRIPTION = 37;
static int const CRITERIA_MARQUE_MOTEUR = 38;
static int const CRITERIA_CARBURANT = 39;
static int const CRITERIA_ENERGIE = 40;
static int const CRITERIA_INTITULE = 41;


static int const CRITERIA_TYPE_BATEAU = 42;
static int const CRITERIA_CATEGORIE = 43;
static int const CRITERIA_MARQUE = 44;
static int const CRITERIA_MODELE = 45;
static int const CRITERIA_MODELE_MOTEUR = 46;
static int const CRITERIA_PAYS = 47;
static int const CRITERIA_ETAT = 49;
static int const CRITERIA_PRIX = 48;
static int const CRITERIA_LOGUEUR = 50;

// indice des lignes dans BoatOnDemand

static const int LIGNE_NOM = 0;
static const int LIGNE_PRENOM = 1;
static const int LIGNE_EMAIL = 2;
static const int LIGNE_TEL = 3;
static const int LIGNE_CODE_POSTAL = 4;
static const int LIGNE_VILLE = 5;
static const int LIGNE_ETAT_VOULU = 6;
static const int LIGNE_TYPE_VOULU = 7;
static const int LIGNE_CATEGORIE_VOULU = 8;
static const int LIGNE_MARQUE_VOULU = 9;
static const int LIGNE_MODELE_VOULU = 10;
static const int LIGNE_MINTAILLE_VOULU = 11;
static const int LIGNE_MAXTAILLE_VOULU = 12;
static const int LIGNE_LIEU_VOULU = 13;
static const int LIGNE_BUDGET = 14;
static const int LIGNE_PRIX_SOUHAITE = 15;
static const int LIGNE_TYPE_POSSEDE = 16;
static const int LIGNE_CATEGORIE_POSSEDE = 17;
static const int LIGNE_MARQUE_POSSEDE = 18;
static const int LIGNE_MODELE_POSSEDE = 19;
static const int LIGNE_COMMENTAIRE = 20;

// constantes pour le In App purchase

static NSString * const ANNONCE_IDENTIFIER=@"RVYB01ADDEPOT";

static const int TVINFO = 0;
static const int TVINFPLUS = 1;
static const int EQUIPE = 2;
static const int ELECTRO = 3;


static NSString * const ID_CATEGORIE = @"idcat_bateau";
static NSString * const TAILLE_MIN = @"taille_min";
static NSString * const TAILLE_MAX = @"taille_max";
static NSString * const BUDGET_MIN = @"budget_min";
static NSString * const BUDGET_MAX = @"budget_max";
static NSString * const DATE = @"date";



