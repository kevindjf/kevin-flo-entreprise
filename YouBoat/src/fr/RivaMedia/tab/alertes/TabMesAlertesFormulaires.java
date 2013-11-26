
package fr.RivaMedia.tab.alertes;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.tab.core.Tab;


@SuppressLint("ValidFragment")
public class TabMesAlertesFormulaires extends Tab {

	View  _view;
	Activity _activity;
	FragmentNormal _fragment;

	AsyncTask<Void, Void, Void> task = null;

	public TabMesAlertesFormulaires(String titre, Activity activity, FragmentNormal fragment){
		super(titre,activity.getResources().getDrawable(R.drawable.logo_vendre_blanc));
		this._activity = activity;
		this._fragment = fragment;
		
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_annonces, container, false);

		return _view;
	}

}