package fr.RivaMedia.AnnoncesAutoGenerique.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.AnnonceDetail;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.view.core.YouBoatView;

public class AnnonceView extends YouBoatView implements View.OnTouchListener{

	Object _element;
	int _position;
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

	public AnnonceView(Annonce annonce, Context context, View view, int position, boolean swipable) {
		super(context, view);
		ImageLoaderCache.load(getContext());

		this._annonce = annonce;
		this._position = position;
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

			if(_annonce.getPhoto() != null){
				ImageLoaderCache.charger(_annonce.getPhoto(),_image);
			}

			if(_annonce.getFinition() != null)
				_titre.setText(_annonce.getFinition());

			if(_annonce.getAnnee() != null)
				_annee.setText(_annonce.getAnnee());
			else
				_annee.setVisibility(View.GONE);

			if(_annonce.getPrix() != null){
				String p = String.format("%,8d", Integer.parseInt(_annonce.getPrix())).trim()+" € ";
				_prix.setText(p);
			}

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
		_devant.setBackgroundColor(Donnees.parametres.getCouleurPrincipale());
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
		if(_annonce != null && _annonce.getId() != null){
			afficherNormal();
			String id = _annonce.getId();

			System.err.println("ID :"+id);

			FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new AnnonceDetail(id));
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	public void onClickFrontView() {
		afficherNormal();
		lancerAnnonceDetail();
	}

}
