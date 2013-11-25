<?php

// Set POST variables
$url = 'https://android.googleapis.com/gcm/send';

$idAnnonces = array();
//On boucle ici sur les r�sultat de la requete Mysql. eg. foreach($MySQL_results as $K => $V)
	//1Ere iteration
	array_push($idAnnonces,"2544183");
	//2Eme iteration
	array_push($idAnnonces,"2544182");
	//3eme iteration
	array_push($idAnnonces,"2544179");
//Fin boucle

$ID_FLORENT="APA91bFEgdzAsY-U2RUY-UwwM3zcUpn-d14Hfefpczx9dfeHKBacHgwSxgUpnyv_6GrcXwzTBrl2Wo490xJQB_Dpxh0lgMCMI9xojLmParD1JhQAlz20m7uGMwHOVCsHSTtniMXeuaNAmHMPe6wH6r2CiSs2qOpnlA";


//L'identifiant du t�l�phone stock� en base, T�l�phone de Florent pour tester
$IDENTIFIANT_TELEPHONE = $ID_FLORENT;

//La clef de l'application que l'on vient de g�n�rer avec les identifiants sur la console Google
$API_KEY = 'AIzaSyD4qJA03LqSDGuBZuLBl_7Vmo_0DESuoRo';

$fields = array(

				'registration_ids'  => array( $IDENTIFIANT_TELEPHONE ),
			    'data'              => array( "message" => "Nouvelles Alertes !" , "annonces" => json_encode($idAnnonces) ),
                );

$headers = array( 
					'Authorization: key=' . $API_KEY,
                    'Content-Type: application/json'
                );

// Open connection
$ch = curl_init();

// Set the url, number of POST vars, POST data
curl_setopt( $ch, CURLOPT_URL, $url );

curl_setopt( $ch, CURLOPT_POST, true );
curl_setopt( $ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1 );

curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 2);    // 2 is the default so this is not required


curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode( $fields ) );

// Execute post
$result = curl_exec($ch);

// Close connection
curl_close($ch);

echo $result;



//TMP AREA
                //'registration_ids'  => explode(";", $_POST['registrationIDs']),


//FIN TMPAREA

?>