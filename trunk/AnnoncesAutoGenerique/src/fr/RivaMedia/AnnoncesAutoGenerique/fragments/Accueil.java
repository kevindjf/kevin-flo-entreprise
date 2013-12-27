package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

public class Accueil extends FragmentNormal implements View.OnClickListener, OnTouchListener{

	View _view;
	ImageView  _logo;
	ImageView  _imageEntreprise;
	TextView _text_entreprise;
	
	ImageView _fond;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.accueil,container, false);

		ImageLoaderCache.load(getActivity());
		
		charger();	
		remplir();
		ajouterListeners();

		return _view;
	}	

	@Override
	public void charger() {
		_logo = (ImageView)_view.findViewById(R.id.logo_entreprise);
		_imageEntreprise = (ImageView) _view.findViewById(R.id.image_entreprise);
		_text_entreprise = (TextView) _view.findViewById(R.id.text_entreprise);
		
	}

	@Override
	public void remplir() {

		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(), _logo);
		ImageLoaderCache.charger(Donnees.parametres.getImageAccueil(), _imageEntreprise);
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		_text_entreprise.setText(Donnees.parametres.getTexteIntro());
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

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.accueil));
	}
	
	

}
