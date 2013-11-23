package fr.RivaMedia.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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
		if(lesIds != null){
		String[] ids = lesIds.split(" ");
		for(String id : ids){
			System.out.println(id);
			listeFavoris.add(id.replace("[","").replace("]",""));
		}
		}else{
			Toast.makeText(_context, "Aucun Favoris", Toast.LENGTH_SHORT).show();
		}
		return listeFavoris;
	}
	
	public boolean contientFavoris(String id){
		String lesIds = _settings.getString(FAVORIS, "");
		String idRecherche = "["+id;
		
		Log.e("FAVORIS", "contient :"+idRecherche+" in "+lesIds);
		
		boolean contient = (lesIds!= null && lesIds.contains(idRecherche));
		
		Log.e("FAVORIS", ""+contient);
		
		return contient;
	}
	
	public void ajouterFavoris(String id, String type){
		SharedPreferences.Editor editor = _settings.edit();
		
		String lesIds = _settings.getString(FAVORIS, "");
		String idAjout = "["+id+";"+type+"]";
		
		String nouvelIds = lesIds+" "+idAjout;
		editor.putString(FAVORIS, nouvelIds);
		
		Log.e("FAVORIS", "ajouter :"+nouvelIds);
		
		editor.commit();
	}

	public void retirerFavoris(String id, String type) {
		SharedPreferences.Editor editor = _settings.edit();
		
		String lesIds = _settings.getString(FAVORIS, "");
		String idSuppr = "["+id+";"+type+"]";
		
		String nouvelIds = lesIds.replace(idSuppr,"").trim().replace("  "," ");
		editor.putString(FAVORIS, nouvelIds);
		
		Log.e("FAVORIS", "retirer :"+nouvelIds);
		editor.commit();
	}
	
}
