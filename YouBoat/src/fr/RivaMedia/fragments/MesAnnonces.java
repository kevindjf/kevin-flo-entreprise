package fr.RivaMedia.fragments;

import java.util.List;

import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.factory.*;

public class MesAnnonces extends Fragment implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_liste,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	protected void charger(){
		_liste = (ListView)_view.findViewById(R.id.annonces_liste_listview);		
	}
	protected void remplir(){
		_adapter = new AnnonceListAdapter(getActivity(), BateauFactory.getListBateaux());
		_liste.setAdapter(_adapter);
	}
	protected void ajouterListeners(){
	}


	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

}
