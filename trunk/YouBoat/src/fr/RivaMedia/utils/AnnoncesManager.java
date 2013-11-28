package fr.RivaMedia.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AnnoncesManager {

	Context _context;
	SharedPreferences _settings;

	protected String PREFS = "";
	protected String KEY = "";

	public AnnoncesManager(Context context, String prefs, String key){
		_context = context;
		this.PREFS = prefs;
		this.KEY = key;
		_settings = _context.getSharedPreferences(PREFS, 0);
	}

	public List<String> getAll(){
		List<String > liste = new ArrayList<String>();
		String lesIds = _settings.getString(KEY, null);
		if(lesIds != null){
			String[] ids = lesIds.split(" ");
			for(String id : ids){
				System.out.println(id);
				liste.add(id.replace("[","").replace("]",""));
			}
		}else{
			//Toast.makeText(_context, "Aucune "+KEY, Toast.LENGTH_SHORT).show();
		}
		return liste;
	}

	public boolean contient(String id){
		String lesIds = _settings.getString(KEY, "");
		String idRecherche = "["+id;

		Log.e(KEY, "contient :"+idRecherche+" in "+lesIds);

		boolean contient = (lesIds!= null && lesIds.contains(idRecherche));

		Log.e(KEY, ""+contient);

		return contient;
	}

	public void ajouter(String id, String type){
		SharedPreferences.Editor editor = _settings.edit();

		String lesIds = _settings.getString(KEY, "");
		String idAjout = "["+id+";"+type+"]";

		if(!lesIds.contains(idAjout)){

			String nouvelIds = lesIds+" "+idAjout;
			editor.putString(KEY, nouvelIds);

			Log.e(KEY, "ajouter :"+nouvelIds);
		}else
			Log.e(KEY, "ajouter : contient deja");

		editor.commit();
	}

	public void retirer(String id, String type) {
		SharedPreferences.Editor editor = _settings.edit();

		String lesIds = _settings.getString(KEY, "");
		String idSuppr = "["+id+";"+type+"]";

		String nouvelIds = lesIds.replace(idSuppr,"").trim().replace("  "," ");
		editor.putString(KEY, nouvelIds);

		Log.e(KEY, "retirer :"+nouvelIds);
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
