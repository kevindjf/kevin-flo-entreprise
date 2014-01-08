
package fr.RivaMedia.JPV.activity;

import fr.RivaMedia.AnnoncesAutoGenerique.activity.SplashScreenActivity;
import fr.RivaMedia.JPV.Constantes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent(this,SplashScreenActivity.class);
		intent.putExtra(SplashScreenActivity.CLIENT, Constantes.CLIENT_VALUE);
		
		startActivity(intent);
		finish();
	}




}
