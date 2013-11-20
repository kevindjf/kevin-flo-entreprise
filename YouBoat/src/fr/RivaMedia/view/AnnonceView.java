package fr.RivaMedia.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.R.color;
import fr.RivaMedia.fragments.AnnonceDetail;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Moteur;
import fr.RivaMedia.view.core.YouBoatView;

public class AnnonceView extends YouBoatView implements View.OnTouchListener{

	Object _element;
	int _position;
	String _type;

	ImageView _image;
	TextView _titre;
	TextView _sousTitre;
	TextView _taille;
	TextView _annee;
	TextView _prix;

	Annonce _annonce;

	public AnnonceView(Annonce annonce, Context context, View view, int position, String type) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._annonce = annonce;
		this._position = position;
		this._type = type;
		charger();
		remplir();
		ajouterListeners();
	}

	@Override
	public void charger() {
		_image = (ImageView)getView().findViewById(R.id.annonce_element_liste_image);
		_titre = (TextView)getView().findViewById(R.id.annonce_element_liste_titre);
		_sousTitre = (TextView)getView().findViewById(R.id.annonce_element_liste_sous_titre);
		_taille = (TextView)getView().findViewById(R.id.annonce_element_liste_taille);
		_annee = (TextView)getView().findViewById(R.id.annonce_element_liste_annee);
		_prix = (TextView)getView().findViewById(R.id.annonce_element_liste_prix);
	}

	@Override
	public void remplir() {
		if(_annonce != null){

			Log.e("IMAGE","vide");
			if(_annonce.getLien() != null && _annonce.getLien().getUrl() != null){
				ImageLoaderCache.charger(_annonce.getLien().getUrl(),_image);
				Log.e("IMAGE",_annonce.getLien().getUrl());
			}

			if(_annonce.getTitle() != null)
				_titre.setText(_annonce.getTitle());
			else
				if(_annonce.getMoteur() != null && _annonce.getMoteur().getMarqueMoteur() != null)
					_titre.setText(_annonce.getMoteur().getMarqueMoteur());

			if(_annonce.getNomMoteur() != null)
				_sousTitre.setText(_annonce.getNomMoteur());
			else
				if(_annonce.getCommentaire() != null)
					_sousTitre.setText(_annonce.getCommentaire());

			if(_annonce.getLongueur() != null)
				_taille.setText(_annonce.getLongueur()+" m");
			if(_annonce.getMoteur() != null && _annonce.getMoteur().getPuissanceMoteur() != null)
				_taille.setText(_annonce.getMoteur().getPuissanceMoteur()+" ch");

			if(_annonce.getAnnee() != null)
				_annee.setText(_annonce.getAnnee());
			else
				_annee.setVisibility(View.GONE);

			if(_annonce.getPrix() != null)
				_prix.setText(_annonce.getPrix()+" €");

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
			lancerAnnonceDetail();
		}
	}

	private void afficherNormal(){
		if(_position%2==0){
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_paire));
		}else{
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_impaire));
		}
	}
	private void afficherTouch(){
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

	public void lancerAnnonceDetail(){
		if(_annonce != null){
			afficherNormal();
			String id = _annonce.getNumero();

			FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new AnnonceDetail(id,_type));
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

}
