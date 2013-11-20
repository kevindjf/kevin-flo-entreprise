package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Annonce;

public class MesAnnonces extends FragmentNormal implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_annonces,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_liste = (ListView)_view.findViewById(R.id.annonces_liste_listview);		
	}
	public void remplir(){
		_adapter = new AnnonceListAdapter(getActivity(), _annonces,null);
		_liste.setAdapter(_adapter);
	}
	public void ajouterListeners(){
	}


	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

}
