package fr.RivaMedia.utils;

import android.content.Context;

public class FavorisManager extends AnnoncesManager{

	public static final String PREFS_FAVORIS = "PREFS_FAVORIS";
	public static final String FAVORIS = "FAVORIS";
	
	public FavorisManager(Context context){
		super(context, PREFS_FAVORIS, FAVORIS);
	}	
}
