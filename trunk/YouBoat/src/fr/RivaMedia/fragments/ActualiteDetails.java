package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;

//TODO
public class ActualiteDetails extends Fragment{

	View _view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.actualite_details, container, false);
		
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}
	
	protected void charger(){
	}
	protected void remplir(){
	}
	protected void ajouterListeners(){
	}

}
