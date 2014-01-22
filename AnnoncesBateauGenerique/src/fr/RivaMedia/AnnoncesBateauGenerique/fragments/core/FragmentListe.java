package fr.RivaMedia.AnnoncesBateauGenerique.fragments.core;

import com.navdrawer.SimpleSideDrawer;

import android.view.View;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.MainActivity;

//TODO: n'arriche pas "effacer" en cas de popStack

public abstract class FragmentListe extends FragmentNormal implements Effaceable{
	
	SimpleSideDrawer _slider;
	protected boolean afficherProgress = true;
	
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
		getActivity().findViewById(R.id.slider_droite_longueur_croissant).setOnClickListener(this);
		getActivity().findViewById(R.id.slider_droite_longueur_decroissant).setOnClickListener(this);
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
	
	public void afficherTriLongueur(boolean b){
		if(b)
			getActivity().findViewById(R.id.slider_droite_tri_longueur).setVisibility(View.VISIBLE);
		else
			getActivity().findViewById(R.id.slider_droite_tri_longueur).setVisibility(View.GONE);
	}
	
	public abstract void afficherPrixCroissant();
	public abstract void afficherPrixDeCroissant();
	public abstract void afficherDateCroissant();
	public abstract void afficherDateDeCroissant();
	public abstract void afficherLongueurCroissant();
	public abstract void afficherLongueurDeCroissant();
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.header_tri) {
			if(_slider.isClosed())
				afficherSliderDroite();
			else
				fermerSliderDroite();
		} else if (id == R.id.slider_droite_date_croissant) {
			afficherDateCroissant();
			fermerSliderDroite();
		} else if (id == R.id.slider_droite_date_decroissant) {
			afficherDateDeCroissant();
			fermerSliderDroite();
		} else if (id == R.id.slider_droite_prix_croissant) {
			afficherPrixCroissant();
			fermerSliderDroite();
		} else if (id == R.id.slider_droite_prix_decroissant) {
			afficherPrixDeCroissant();
			fermerSliderDroite();
		} else if (id == R.id.slider_droite_longueur_croissant) {
			afficherLongueurCroissant();
			fermerSliderDroite();
		} else if (id == R.id.slider_droite_longueur_decroissant) {
			afficherLongueurDeCroissant();
			fermerSliderDroite();
		}
	}
	
}
