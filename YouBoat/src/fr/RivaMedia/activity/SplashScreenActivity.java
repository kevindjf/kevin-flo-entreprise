package fr.RivaMedia.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.alertes.GcmInitializer;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;
import fr.RivaMedia.net.core.Net;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 0;

	GcmInitializer gcmInitializer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Net.enableHttpResponseCache(this);
		
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

		Intent i = new Intent(this,MagasineActivity.class);
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
			final List<Marque> TOUTESmarquesPneu = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null,false);
			final List<Marque> TOUTESmarquesMoteur = NetChargement.chargerMarquesMoteurs(null,false);
			
			Donnees.TOUTESmarques.put(Constantes.BATEAU_A_MOTEUR, TOUTESmarquesBateauxAMoteur);
			Donnees.TOUTESmarques.put(Constantes.VOILIER, TOUTESmarquesVoilier);
			Donnees.TOUTESmarques.put(Constantes.PNEU, TOUTESmarquesPneu);
			Donnees.TOUTESmarques.put(Constantes.MOTEURS, TOUTESmarquesMoteur);

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
		HttpResponseCache cache = HttpResponseCache.getInstalled();
		if (cache != null) {
			cache.flush();
		}
	}
}
