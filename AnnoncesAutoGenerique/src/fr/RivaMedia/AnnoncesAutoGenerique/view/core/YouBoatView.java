package fr.RivaMedia.AnnoncesAutoGenerique.view.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class YouBoatView implements IYouBoatView, OnClickListener, TextLinkClickListener{

	Context _context;
	View _view;
	
	public YouBoatView(Context context, View view){
		this._context = context;
		this._view = view;
	}

	public Context getContext() {
		return _context;
	}

	public void setContext(Context context) {
		this._context = context;
	}

	public View getView() {
		return _view;
	}

	public void setView(View view) {
		this._view = view;
	}
	
	public void reCharger(View view){
		setView(view);
		charger();
		remplir();
		ajouterListeners();
	}
	
	public void onTextLinkClick(View textView, String clickedString){
		Log.e("listOfLinks","clicked :"+clickedString.charAt(0));
		switch(clickedString.charAt(0)){
		case '#':
			break;
		case '@':
			break;
		default:
		}
	}
	
}
