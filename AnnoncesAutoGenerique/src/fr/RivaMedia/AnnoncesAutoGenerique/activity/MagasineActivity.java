package fr.RivaMedia.AnnoncesAutoGenerique.activity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

public class MagasineActivity extends Activity{

	ImageView _image;
	public static final int tempsAttenteSecondes = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magasine);

		ImageLoaderCache.load(this);

		_image = (ImageView)findViewById(R.id.magasine_image);
		
		if(Donnees.autoPromo != null && Donnees.autoPromo.getImage320_568() != null){
			ImageLoaderCache.charger(Donnees.autoPromo.getImage320_568(), _image);
			if(Donnees.autoPromo.getLien() != null){
				_image.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						String url = Donnees.autoPromo.getLien();
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

			//TODO charger les donnees 2
			
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
