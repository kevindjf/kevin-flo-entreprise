
package fr.RivaMedia.ProjetBateauGenerique.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.SplashScreenActivity;
import fr.RivaMedia.ProjetBateauGenerique.Constantes;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent(this,SplashScreenActivity.class);
		intent.putExtra(SplashScreenActivity.CLIENT, Constantes.CLIENT_VALUE);
		intent.putExtra(SplashScreenActivity.APPLICATION_NAME, Constantes.APPLICATION_NAME);
		
		startActivity(intent);
		finish();
	}




}
