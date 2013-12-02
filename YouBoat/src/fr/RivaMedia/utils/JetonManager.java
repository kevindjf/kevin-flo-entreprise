package fr.RivaMedia.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class JetonManager {

	Context _context;
	SharedPreferences _settings;

	protected String PREFS = "JETON_PREFS";
	protected String KEY = "JETON";

	public JetonManager(Context context){
		_context = context;
		_settings = _context.getSharedPreferences(PREFS, 0);
	}

	public String getJeton(){
		String jeton = _settings.getString(KEY, null);
		
		System.err.println("get jeton :"+jeton);
		
		return jeton;
	}

	public void setJeton(String jeton){
		SharedPreferences.Editor editor = _settings.edit();

		editor.putString(KEY, jeton);
		
		System.err.println("set jeton :"+jeton);

		editor.commit();
	}

	public void reset() {
		SharedPreferences.Editor editor = _settings.edit();

		String nouvelIds ="";
		editor.putString(KEY, nouvelIds);

		Log.e(KEY, "tout a ete supprimme");
		editor.commit();
	}
}
