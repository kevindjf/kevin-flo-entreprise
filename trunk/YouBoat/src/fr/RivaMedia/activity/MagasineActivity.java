package fr.RivaMedia.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import fr.RivaMedia.R;
import fr.RivaMedia.image.ImageLoaderCache;

public class MagasineActivity extends Activity{

	ImageView _image;
	public static final String urlMagasine = "http://www.youboat.fr/xml/promo/splash_promo_retina.png";
	public static final int tempsAttenteSecondes = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.magasine);

		ImageLoaderCache.load(this);

		_image = (ImageView)findViewById(R.id.magasine_image);
		ImageLoaderCache.charger(urlMagasine, _image);

		lancerDecompte();
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

	protected void etapeSuivante(){
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
		finish();
	}

}
