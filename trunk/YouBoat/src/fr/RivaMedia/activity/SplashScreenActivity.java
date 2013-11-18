package fr.RivaMedia.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;
import fr.RivaMedia.net.NetNews;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		if(isOnline())
			new ChargementsTask().execute();
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
	}

	protected void lancerDecompte(){
		new Thread(new Runnable(){

			@Override
			public void run() {

				try {
					Thread.sleep(tempsAttenteSecondes*1000);

					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							etapeSuivante();

						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}).start();
	}

	/* --------------------------------------------------------------------------- */

	class ChargementsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			Donnees.types = NetChargement.chargerTypes();

			final List<Marque> marquesBateaux = NetChargement.chargerMarquesBateaux();
			final List<Marque> marquesBateauxAMoteur = NetChargement.chargerMarquesBateaux();
			final List<Marque> marquesVoilier = NetChargement.chargerMarquesVoiliers();
			final List<Marque> marquesPneu = NetChargement.chargerMarquesPneu();
			final List<Marque> marquesMoteur = NetChargement.chargerMarquesMoteurs();

			Donnees.marques.put("0", marquesBateaux);
			Donnees.marques.put(Constantes.BATEAU_A_MOTEUR, marquesBateaux);
			Donnees.marques.put(Constantes.VOILIER, marquesBateaux);
			Donnees.marques.put(Constantes.PNEU, marquesBateaux);
			Donnees.marques.put(Constantes.MOTEURS, marquesMoteur);

			final List<Service> services = NetChargement.chargerServices();

			//tests
			NetNews.chargerListeNews();


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


}
