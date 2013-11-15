package fr.RivaMedia.activity;

import java.util.List;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.Type;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.*;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new ChargementsTask().execute();
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
}
