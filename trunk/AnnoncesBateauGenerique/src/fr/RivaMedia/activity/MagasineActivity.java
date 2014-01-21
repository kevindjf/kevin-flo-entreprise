package fr.RivaMedia.activity;


import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;

public class MagasineActivity extends Activity{

	ImageView _image;
	public static final int tempsAttenteSecondes = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magasine);

		ImageLoaderCache.load(this);

		_image = (ImageView)findViewById(R.id.magasine_image);
		if(Donnees.autoPromo != null && Donnees.autoPromo.getImage() != null){
			ImageLoaderCache.charger(Donnees.autoPromo.getImage(), _image);
			if(Donnees.autoPromo.getUrl() != null){
				_image.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						String url = Donnees.autoPromo.getUrl();
						Intent i = new Intent(Intent.ACTION_VIEW);
						i.setData(Uri.parse(url));
						startActivity(i);
					}
				});
			}
				
		}

		new ChargementsTask().execute();
	}


	protected void etapeSuivante(){
		
		new Thread(new Runnable(){

			@Override
			public void run() {

				try {
					Thread.sleep(tempsAttenteSecondes*1000);

					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							Intent i = new Intent(MagasineActivity.this,MainActivity.class);
							startActivity(i);
							finish();
							overridePendingTransition(R.anim.entrer, R.anim.sortir); 
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
