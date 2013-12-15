package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class Accueil extends FragmentNormal implements View.OnClickListener, OnTouchListener{

	View _view;
	View  _logo;
	View  _imageEntreprise;
	TextView _text_entreprise;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.accueil,container, false);

		_logo = _view.findViewById(R.id.annonces_logo);
		_imageEntreprise = _view.findViewById(R.id.image_entreprise);
		_text_entreprise = (TextView) _view.findViewById(R.id.text_entreprise);
		
		ajouterListeners();
		charger();
	
		return _view;
	}	
	
	@Override
	public void charger() {
	}

	@Override
	public void remplir() {
	}

	@Override
	public void onClick(View v) {
	}
	
	@Override
	public void ajouterListeners() {
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

}
