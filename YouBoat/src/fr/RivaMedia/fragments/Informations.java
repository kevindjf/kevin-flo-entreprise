package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;

public class Informations extends FragmentNormal implements View.OnClickListener{

	View _view;
	ImageView _magasine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.informations,container, false);

		ImageLoaderCache.load(getActivity());
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

	@Override
	public void charger() {
		_magasine = (ImageView) _view.findViewById(R.id.couverture_magasine);
	}

	@Override
	public void remplir() {
		ImageLoaderCache.charger(Constantes.URL_PUB_MAGASINE_HD, _magasine);
	}

	@Override
	public void ajouterListeners() {
		// TODO Auto-generated method stub
		
	}

}