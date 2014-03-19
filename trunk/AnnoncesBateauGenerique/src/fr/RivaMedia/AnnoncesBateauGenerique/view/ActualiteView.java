package fr.RivaMedia.AnnoncesBateauGenerique.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.core.BateauFragmentActivity;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.ActualiteDetail;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.AnnonceDetail;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.view.core.YouBoatView;

public class ActualiteView extends YouBoatView implements View.OnTouchListener{

	Object _element;
	int _position;

	ImageView _image;
	TextView _titre;
	TextView _sousTitre;

	public ActualiteView(Object element, Context context, View view, int position) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._element = element;
		this._position = position;
		charger();
		remplir();
		ajouterListeners();
	}

	@Override
	public void charger() {
		_image = (ImageView)getView().findViewById(R.id.actualite_element_liste_image);
		_titre = (TextView)getView().findViewById(R.id.actualite_element_liste_titre);
		_sousTitre = (TextView)getView().findViewById(R.id.actualite_element_liste_sous_titre);
	}

	@Override
	public void remplir() {
		if(_element instanceof Actualite){
			Actualite news = (Actualite)_element;

			ImageLoaderCache.charger(news.getImageAdress(),_image, getView().findViewById(android.R.id.progress));
			_titre.setText(news.getTitle());
			_sousTitre.setText(news.getDescription());
		}

		afficherNormal();
	}

	@Override
	public void ajouterListeners() {
		this.getView().setOnClickListener(this);
		this.getView().setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		default:
			lancerActualiteDetail(_element);
		}
	}

	private void afficherNormal(){
		_titre.setTextColor(Donnees.parametres.getFontColorUn());
		_sousTitre.setTextColor(Donnees.parametres.getFontColorUn());

		getView().setBackgroundColor(Donnees.parametres.getBackgroundColorUn());
		
		/*if(_position%2==0){
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_paire));
		}else{
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_impaire));
		}*/
	}
	private void afficherTouch(){
		_titre.setTextColor(getContext().getResources().getColor(R.color.couleur_texte_blanc));
		_sousTitre.setTextColor(getContext().getResources().getColor(R.color.couleur_texte_blanc));
		getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_touch));
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			afficherTouch();
		}
		if(event.getAction() == MotionEvent.ACTION_CANCEL){
			afficherNormal();
		}
		return false;
	}

	public void lancerActualiteDetail(Object element){
		if(_element instanceof Actualite){
			Actualite news = (Actualite)_element;
			afficherNormal();

			((BateauFragmentActivity)getContext()).ajouterFragment(new ActualiteDetail(news.getId()));
			
		}
	}

}
