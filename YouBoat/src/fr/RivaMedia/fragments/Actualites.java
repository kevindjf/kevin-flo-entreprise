package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.adapter.ActualiteListAdapter;
import fr.RivaMedia.factory.BateauFactory;
import fr.RivaMedia.factory.NewsFactory;

public class Actualites extends Fragment{

	View _view;
	ListView _liste = null;
	ActualiteListAdapter _adapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.actualites, container, false);
		
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}
	
	protected void charger(){
		_liste = (ListView)_view.findViewById(R.id.actualites_liste_listview);		
	}
	protected void remplir(){
		_adapter = new ActualiteListAdapter(getActivity(), NewsFactory.getListeNews());
		_liste.setAdapter(_adapter);
	}
	protected void ajouterListeners(){
	}

}
