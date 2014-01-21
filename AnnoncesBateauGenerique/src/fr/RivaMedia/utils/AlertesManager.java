package fr.RivaMedia.utils;

import android.content.Context;

public class AlertesManager extends AnnoncesManager{

	public static final String PREFS_ALERTES = "PREFS_ALERTES";
	public static final String ALERTES = "ALERTES";
	
	public AlertesManager(Context context){
		super(context, PREFS_ALERTES, ALERTES);
	}
}
