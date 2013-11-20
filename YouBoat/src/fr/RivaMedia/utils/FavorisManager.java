package fr.RivaMedia.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class FavorisManager {

	Context _context;
	SharedPreferences _settings;
	
	public static final String PREFS_FAVORIS = "PREFS_FAVORIS";
	
	public static final String FAVORIS = "FAVORIS";
	
	public FavorisManager(Context context){
		_context = context;
		_settings = _context.getSharedPreferences(PREFS_FAVORIS, 0);
	}
	
	public void setSessionCode(String code){
		SharedPreferences.Editor editor = _settings.edit();
	    editor.putString(FAVORIS, code);
	    editor.commit();
	}
	
	public List<String> getFavois(){
		List<String > listeFavoris = new ArrayList<String>();
		String lesIds = _settings.getString(FAVORIS, null);
		
		String[] ids = lesIds.split(" ");
		for(String id : ids)
			listeFavoris.add(id.replace("[","").replace("]",""));
		
		return listeFavoris;
	}
	
	public boolean contientFavoris(String id){
		String lesIds = _settings.getString(FAVORIS, "");
		String idRecherche = "["+id+"]";
		
		Log.e("FAVORIS", "contient :"+idRecherche+" in "+lesIds);
		
		boolean contient = (lesIds!= null && lesIds.contains(idRecherche));
		
		Log.e("FAVORIS", ""+contient);
		
		return contient;
	}
	
	public void ajouterFavoris(String id){
		SharedPreferences.Editor editor = _settings.edit();
		
		String lesIds = _settings.getString(FAVORIS, "");
		String idAjout = "["+id+"]";
		
		String nouvelIds = lesIds+" "+idAjout;
		editor.putString(FAVORIS, nouvelIds);
		
		Log.e("FAVORIS", "ajouter :"+nouvelIds);
		
		editor.commit();
	}

	public void retirerFavoris(String id) {
		SharedPreferences.Editor editor = _settings.edit();
		
		String lesIds = _settings.getString(FAVORIS, "");
		String idSuppr = "["+id+"]";
		
		String nouvelIds = lesIds.replace(idSuppr,"");
		editor.putString(FAVORIS, nouvelIds);
		
		Log.e("FAVORIS", "retirer :"+nouvelIds);
		editor.commit();
	}
	
}
