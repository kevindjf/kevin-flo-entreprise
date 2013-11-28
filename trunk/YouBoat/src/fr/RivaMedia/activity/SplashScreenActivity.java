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
import android.widget.ImageView;
import android.widget.Toast;
import fr.RivaMedia.R;
import fr.RivaMedia.alertes.GcmInitializer;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;
import fr.RivaMedia.net.core.Net;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 2;

	GcmInitializer gcmInitializer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Net.enableHttpResponseCache(this);

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

			Donnees.magazine = NetChargement.chargerMagazine();
			chargerImage();

			Donnees.typesAnnonces = NetChargement.chargerTypesAnnonces();

			Donnees.typeCategories = NetChargement.chargerTypesCategories();
			Donnees.regions = NetChargement.chargerRegions();
			Donnees.etats = NetChargement.chargerEtats();
			Donnees.departements = NetChargement.chargerDepartements();


			Donnees.energies = NetChargement.chargerEnergies();
			
			final List<Service> services = NetChargement.chargerServices();
			Donnees.services = services;			

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
