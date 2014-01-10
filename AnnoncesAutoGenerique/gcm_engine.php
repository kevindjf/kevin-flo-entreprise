<?php

// Set POST variables
$url = 'https://android.googleapis.com/gcm/send';

//////////////////////////////

$ID_FLORENT="APA91bH8030qtFA-nQs-T3gXV75LgU9j0tZFgy4uo5ng5xB45rKKNc3crj24d7SAK8Diwa1cIXmDwW1QcxdE5nvJjZVIhgkXO3NzruOFVZc34q5U7zjMfGV4hRcPxVEFy1OxG03rfjBb1m_aNa39RM8BhvKq9WQN2Q";


//L'identifiant du téléphone stocké en base, Téléphone de Florent pour tester
$IDENTIFIANT_TELEPHONE = $ID_FLORENT;

//La clef de l'application que l'on vient de générer avec les identifiants sur la console Google
$API_KEY = 'AIzaSyD4qJA03LqSDGuBZuLBl_7Vmo_0DESuoRo';

$datas = array( "titre" => "Nouveau Message !" , "message" => "La nouvelle laguna est sortie !");

$fields = array(

				'registration_ids'  => array( $IDENTIFIANT_TELEPHONE ),
			    'data'              => $datas
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