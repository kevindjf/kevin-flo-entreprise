package fr.RivaMedia.AnnoncesBateauGenerique.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.AnnonceDetail;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.view.core.YouBoatView;

public class AnnonceView extends YouBoatView implements View.OnTouchListener{

	Object _element;
	int _position;
	String _type;
	boolean _swipable;

	ImageView _image;
	TextView _titre;
	TextView _sousTitre;
	TextView _taille;
	TextView _annee;
	TextView _prix;

	Annonce _annonce;

	View _derriere;
	View _devant;
	
	View _apartirDe;

	public AnnonceView(Annonce annonce, Context context, View view, int position, String type, boolean swipable) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._annonce = annonce;
		this._position = position;
		this._type = type;
		this._swipable = swipable;
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
		_apartirDe = (TextView)getView().findViewById(R.id.annonce_element_liste_a_partir_de);

		if(_swipable){
			_devant = getView().findViewById(R.id.devant);
			_derriere = getView().findViewById(R.id.derriere);
		}
		else
			_devant = getView();
	}

	@Override
	public void remplir() {
		if(_annonce != null){

			if(!_swipable){
				if(_annonce.getLien() != null && _annonce.getLien().getUrl() != null){
					ImageLoaderCache.charger(_annonce.getLien().getUrl(),_image);
					Log.e("IMAGE",_annonce.getLien().getUrl());
				}
			}else{
				if(_annonce.getPhotos() != null && _annonce.getPhotos().size()>0 
						&& _annonce.getPhotos().get(0) != null && _annonce.getPhotos().get(0).getUrl() != null){
					ImageLoaderCache.charger(_annonce.getPhotos().get(0).getUrl(),_image);
				}
			}

			if(_annonce.getTitle() != null)
				_titre.setText(_annonce.getTitle());
			else
				if(_annonce.getMoteur() != null && _annonce.getMoteur().getMarqueMoteur() != null)
					_titre.setText(_annonce.getMoteur().getMarqueMoteur());

			if(!_swipable)
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

			if(_annonce.getPrix() != null){
				String p = String.format("%,8d", Integer.parseInt(_annonce.getPrix())).trim()+" € ";
				if(_annonce.getTaxePrix() != null)
					p += _annonce.getTaxePrix();
				_prix.setText(p);
			}
			
			if(_annonce.getApartirDe() != null && _annonce.getApartirDe().trim().equals("1"))
				_apartirDe.setVisibility(View.VISIBLE);

		}

		afficherNormal();
	}

	@Override
	public void ajouterListeners() {
		_devant.setOnClickListener(this);
		_devant.setOnTouchListener(this);
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
			_devant.setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_paire));
		}else{
			_devant.setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_impaire));
		}
	}
	private void afficherTouch(){
		_devant.setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_touch));
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
		if(_annonce != null && _annonce.getNumero() != null){
			afficherNormal();
			String id = _annonce.getNumero();
			String type = _annonce.getType();
			if(type == null)
				type = _type;
			
			System.err.println("ID :"+id);
			System.err.println("TYPE :"+type);

			FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.fragment_container, new AnnonceDetail(id,type));
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	public void onClickFrontView() {
		afficherNormal();
		lancerAnnonceDetail();
	}

}
