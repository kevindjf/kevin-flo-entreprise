package fr.RivaMedia.AnnoncesAutoGenerique.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.alertes.GcmInitializer;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetChargement;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 0;

	GcmInitializer gcmInitializer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		//Net.enableHttpResponseCache(this);
		
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
				if(Donnees.autoPromo != null && Donnees.autoPromo.getImage320_568() != null)
					ImageLoaderCache.charger(Donnees.autoPromo.getImage320_568(), new ImageView(SplashScreenActivity.this));
			}

		});
	}

	/* --------------------------------------------------------------------------- */

	class ChargementsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//TODO charger les donnees 
			//afficher le magasine AutoPromo
			
			Donnees.transmission.add("Inconnu");
			Donnees.transmission.add("Mecanique");
			Donnees.transmission.add("Automatique");
			
			Donnees.parametres = NetChargement.chargerClientParametres();
			Donnees.autoPromo = NetChargement.chargerAutoPromo();
			chargerImage();
			
			Donnees.categories = NetChargement.charcherCategories();
			Donnees.marques = NetChargement.charcherMarques();
			Donnees.marquesPubliees = NetChargement.charcherMarquesPubliees();
			Donnees.energies = NetChargement.chargerEnergies();
			
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
