package fr.RivaMedia.AnnoncesAutoGenerique.fragments.core;

import com.navdrawer.SimpleSideDrawer;

import android.view.View;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.MainActivity;

//TODO: n'arriche pas "effacer" en cas de popStack

public abstract class FragmentListe extends FragmentNormal implements Effaceable{
	
	SimpleSideDrawer _slider;
	protected boolean afficherProgress = true;
	
	public void cacherTrier(){
		((MainActivity)getActivity()).cacherTrier();
	}
	
	public void afficherTrier(){
		 _slider = ((MainActivity)getActivity()).getSliderDroite();
		((MainActivity)getActivity()).afficherTrier().setOnClickListener(this);
	}
	
	public void afficherSliderDroite(){	
		((MainActivity)getActivity()).afficherSliderDroite();
		getActivity().findViewById(R.id.slider_droite_date_croissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_date_decroissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_prix_croissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_prix_decroissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_annee_croissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_annee_decroissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_kilometrage_croissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_kilometrage_decroissant).setOnClickListener(this);
	}
	
	public void fermerSliderDroite(){
		((MainActivity)getActivity()).fermerSliderDroite();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		afficherTrier();
	}
	
	public abstract void afficherPrixCroissant();
	public abstract void afficherPrixDeCroissant();
	public abstract void afficherDateCroissant();
	public abstract void afficherDateDeCroissant();
	public abstract void afficherKilometrageCroissant();
	public abstract void afficherKilometrageDeCroissant();
	public abstract void afficherAnneeCroissant();
	public abstract void afficherAnneeDeCroissant();
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.header_tri:
			if(_slider.isClosed())
				afficherSliderDroite();
			else
				fermerSliderDroite();
			break;
		case R.id.slider_droite_date_croissant:
			afficherDateCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_date_decroissant:
			afficherDateDeCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_prix_croissant:
			afficherPrixCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_prix_decroissant:
			afficherPrixDeCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_annee_croissant:
			afficherAnneeCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_annee_decroissant:
			afficherAnneeDeCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_kilometrage_croissant:
			afficherKilometrageCroissant();
			fermerSliderDroite();
			break;
		case R.id.slider_droite_kilometrage_decroissant:
			afficherKilometrageDeCroissant();
			fermerSliderDroite();
			break;
		}
	}
	
}
