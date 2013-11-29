package fr.RivaMedia.activity;


import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;

public class MagasineActivity extends Activity{

	ImageView _image;
	public static final int tempsAttenteSecondes = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magasine);

		ImageLoaderCache.load(this);

		_image = (ImageView)findViewById(R.id.magasine_image);
		if(Donnees.magazine != null && Donnees.magazine.getImage() != null)
			ImageLoaderCache.charger(Donnees.magazine.getImage(), _image);

		new ChargementsTask().execute();
	}


	protected void etapeSuivante(){

		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				Intent i = new Intent(MagasineActivity.this,MainActivity.class);
				startActivity(i);
				finish();

				overridePendingTransition(R.anim.entrer, R.anim.sortir); 
			}
		});


	}

	/* --------------------------------------------------------------------------- */

	class ChargementsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			final List<Marque> toutesMarques = NetChargement.chargerMarquesBateauType(null, null);
			final List<Marque> marquesBateauxAMoteur = NetChargement.chargerMarquesBateauType(Constantes.BATEAU_A_MOTEUR,null);
			final List<Marque> marquesVoilier = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null);	
			final List<Marque> marquesPneu = NetChargement.chargerMarquesBateauType(Constantes.VOILIER,null);
			final List<Marque> marquesMoteur = NetChargement.chargerMarquesMoteurs(null);
			Donnees.marquesDistribuees = NetChargement.chargerMarquesDistribuees();
			Donnees.lieux = NetChargement.chargerLieux();

			final Map<String,Integer> nbAnnonces = NetChargement.chargerNbAnnonces();

			Donnees.toutesMarques = toutesMarques;
			Donnees.marques.put("0", toutesMarques);
			Donnees.marques.put(Constantes.BATEAU_A_MOTEUR, marquesBateauxAMoteur);
			Donnees.marques.put(Constantes.VOILIER, marquesVoilier);
			Donnees.marques.put(Constantes.PNEU, marquesPneu);
			Donnees.marques.put(Constantes.MOTEURS, marquesMoteur);
			Donnees.nbAnnonces = nbAnnonces;

			etapeSuivante();
			
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
