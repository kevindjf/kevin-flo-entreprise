package fr.RivaMedia.AnnoncesBateauGenerique.activity;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.NetClient;
import fr.RivaMedia.AnnoncesBateauGenerique.ConstantesClient;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.alertes.GcmInitializer;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetChargement;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;

public class SplashScreenActivity extends Activity{

	public static final String CLIENT = "000";
	
	public static final int tempsAttenteSecondes = 0;

	GcmInitializer gcmInitializer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		//Net.enableHttpResponseCache(this);
		
		ConstantesClient.ID_CLIENT = getIntent().getExtras().getString(CLIENT);
		
		findViewById(R.id.splash_progress).setVisibility(View.VISIBLE);
		findViewById(R.id.splash_progress).setEnabled(true);

		if(isOnline()){
			gcmInitializer = new GcmInitializer(this);
			gcmInitializer.enregistrerCloudMessaginClient();

			ImageLoaderCache.load(this);

			new ChargementsTask().execute();
			
		}
		else{
			Toast.makeText(this, getString(R.string.erreur_connexion), Toast.LENGTH_LONG).show();
			new Thread(new Runnable(){

				@Override
				public void run() {

					try {
						Thread.sleep(tempsAttenteSecondes*1000);

						runOnUiThread(new Runnable(){

							@Override
							public void run() {
								finish();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}).start();


		}
	}

	public boolean isOnline() {
		ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	protected void etapeSuivante(){

		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
		finish();

		overridePendingTransition(R.anim.entrer, R.anim.sortir); 

	}

	protected void lancerDecompte(){
		//n'attend plus
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				etapeSuivante();

			}
		});
	}

	public void chargerImage(){
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if(Donnees.magazine != null && Donnees.magazine.getImage() != null)
					ImageLoaderCache.charger(Donnees.magazine.getImage(), new ImageView(SplashScreenActivity.this));
			}

		});
	}

	/* --------------------------------------------------------------------------- */

	class ChargementsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			/*
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					Donnees.getVilles(getApplicationContext());
				}
			});
			th.start();
			*/
			
			Donnees.parametres = NetChargement.chargerParametres();

			Donnees.autoPromo = NetChargement.chargerAutoPromo();
			Donnees.magazine = NetChargement.chargerMagazineEnCours();
			chargerImage();

			Donnees.typesAnnonces = NetChargement.chargerTypesAnnonces();

			Donnees.typeCategories = NetChargement.chargerTypesCategories(true);
			Donnees.typeCategoriesTOUTES = NetChargement.chargerTypesCategories(false);
			Donnees.regions = NetChargement.chargerRegions();
			Donnees.etats = NetChargement.chargerEtats();
			Donnees.departements = NetChargement.chargerDepartements();


			Donnees.energies = NetChargement.chargerEnergies();
			
			Donnees.services = NetChargement.chargerServices();
			
			final List<Marque> TOUTESmarquesBateauxAMoteur = NetChargement.chargerMarquesBateauType(Constantes.BATEAU_A_MOTEUR,null,false);
			final List<Marque> TOUTESmarquesVoilier = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null,false);	
			final List<Marque> TOUTESmarquesPneu = NetChargement.chargerMarquesBateauType(Constantes.PNEU,null,false);
			final List<Marque> TOUTESmarquesMoteur = NetChargement.chargerMarquesMoteurs(null,false);
			
			Donnees.TOUTESmarques.put(Constantes.BATEAU_A_MOTEUR, TOUTESmarquesBateauxAMoteur);
			Donnees.TOUTESmarques.put(Constantes.VOILIER, TOUTESmarquesVoilier);
			Donnees.TOUTESmarques.put(Constantes.PNEU, TOUTESmarquesPneu);
			Donnees.TOUTESmarques.put(Constantes.MOTEURS, TOUTESmarquesMoteur);
			Donnees.client = NetClient.getClient(Constantes.ID_CLIENT);

			final List<Marque> toutesMarques = NetChargement.chargerMarquesBateauType(null, null,true);
			final List<Marque> marquesBateauxAMoteur = NetChargement.chargerMarquesBateauType(Constantes.BATEAU_A_MOTEUR,null,true);
			final List<Marque> marquesVoilier = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null,true);	
			final List<Marque> marquesPneu = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null,true);
			final List<Marque> marquesMoteur = NetChargement.chargerMarquesMoteurs(null,true);
			Donnees.marquesDistribuees = NetChargement.chargerMarquesDistribuees();
			Donnees.lieux = NetChargement.chargerLieux();

			final Map<String,Integer> nbAnnonces = NetChargement.chargerNbAnnonces();

			Donnees.toutesMarques = toutesMarques;
			Donnees.marques.put("0", toutesMarques);
			Donnees.TOUTESmarques.put("0", toutesMarques);
			Donnees.marques.put(Constantes.BATEAU_A_MOTEUR, marquesBateauxAMoteur);
			Donnees.marques.put(Constantes.VOILIER, marquesVoilier);
			Donnees.marques.put(Constantes.PNEU, marquesPneu);
			Donnees.marques.put(Constantes.MOTEURS, marquesMoteur);
			Log.e("marques moteur",""+marquesMoteur.size());
			Donnees.nbAnnonces = nbAnnonces;
			Log.e("marques moteur",""+TOUTESmarquesMoteur.size());

			/*
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
			
			lancerDecompte();

			return null;
		}

		protected void onPostExecute(){
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@SuppressLint("NewApi")
	protected void onStop() {
		super.onStop();
		/*HttpResponseCache cache = HttpResponseCache.getInstalled();
		if (cache != null) {
			cache.flush();
		}
		*/
	}
}
