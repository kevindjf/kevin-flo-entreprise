package fr.RivaMedia.AnnoncesBateauGenerique.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.ConstantesClient;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.core.BateauFragmentActivity;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.AnnonceDetail;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
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
	TextView _taxe;

	Annonce _annonce;

	View _derriere;
	View _devant;

	TextView _apartirDe;

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
		_taxe = (TextView)getView().findViewById(R.id.annonce_element_liste_prix_taxe);
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
					ImageLoaderCache.charger(_annonce.getLien().getUrl(),_image, getView().findViewById(android.R.id.progress));
					Log.e("IMAGE",_annonce.getLien().getUrl());
				}
			}else{
				if(_annonce.getPhotos() != null && _annonce.getPhotos().size()>0 
						&& _annonce.getPhotos().get(0) != null && _annonce.getPhotos().get(0).getUrl() != null){
					ImageLoaderCache.charger(_annonce.getPhotos().get(0).getUrl(),_image, getView().findViewById(android.R.id.progress));
				}
			}

			if(_annonce.getTitle() != null)
				_titre.setText(_annonce.getTitle());
			else
				if(_annonce.getMoteur() != null && _annonce.getMoteur().getMarqueMoteur() != null)
					_titre.setText(_annonce.getMoteur().getMarqueMoteur());

			if(!_swipable){
				if(_annonce.getNomMoteur() != null)
					_sousTitre.setText(_annonce.getNomMoteur());
				else
					if(_annonce.getCommentaire() != null)
						_sousTitre.setText(_annonce.getCommentaire());
					else
						if(_annonce.getCategorie() != null)
							_sousTitre.setText(_annonce.getCategorie());
			}
			else if(_annonce.getMoteur() != null && _annonce.getMoteur().getInfoMoteur() != null){
				_sousTitre.setText(_annonce.getMoteur().getInfoMoteur());
			}
				

			_taille.setVisibility(View.GONE);
			_annee.setVisibility(View.GONE);

			String type = _annonce.getType();

			if(_annonce.getPrix() != null){
				String p = "";
				Log.e("Prix",_annonce.getPrix()+"" );
				if(_annonce.getPrix().trim().equalsIgnoreCase("0")){
					p = "Prix nous consulter";

				}else{
					p = String.format("%,8d", Integer.parseInt(_annonce.getPrix())).trim();

					if(type != null && type.equals(Constantes.LOCATION)){
						p +=" € / jour";
						_taxe.setVisibility(View.GONE);
					}

					else{
						p += " € ";
						if(_annonce.getTaxePrix() != null)
							_taxe.setText(_annonce.getTaxePrix());
					}				
				}
				_prix.setText(p);

			}

			if(_annonce.getPrix().trim().equalsIgnoreCase("0")){
				_apartirDe.setVisibility(View.GONE);
				_taxe.setVisibility(View.GONE);


			}else{

				if(type != null && type.equals(Constantes.LOCATION)){
					_apartirDe.setVisibility(View.VISIBLE);
				}

				if(_annonce.getApartirDe() != null && _annonce.getApartirDe().trim().equals("1"))
					_apartirDe.setVisibility(View.VISIBLE);
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
		
		
			//if(_position%2==0){
			changerCouleurs();
			_devant.setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_paire));
			_devant.setBackgroundColor(Donnees.parametres.getBackgroundColorUn());
			/*}else{

				changerCouleurs();
				_devant.setBackgroundColor(getContext().getResources().getColor(R.color.couleur_cellule_impaire));
			}*/
	}


	public void changerCouleursPaire(){		
		_titre.setTextColor(Color.BLACK);
		_sousTitre.setTextColor(Color.BLACK);
		_taille.setTextColor(Color.BLACK);
		_annee.setTextColor(Color.BLACK);
	}

	public void changerCouleurs(){		
		_titre.setTextColor(Donnees.parametres.getFontColorUn());
		_sousTitre.setTextColor(Donnees.parametres.getFontColorUn());
		_taille.setTextColor(Donnees.parametres.getFontColorUn());
		_annee.setTextColor(Donnees.parametres.getFontColorUn());
		
		_apartirDe.setTextColor(Donnees.parametres.getFontColorDeux());
		_prix.setTextColor(Donnees.parametres.getFontColorDeux());
		_taxe.setTextColor(Donnees.parametres.getFontColorDeux());
	}

	private void afficherTouch(){
		_devant.setBackgroundColor(Donnees.parametres.getBackgroundColorDeux());
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

			((BateauFragmentActivity)getContext()).ajouterFragment(new AnnonceDetail(id,type));
		}
	}

	public void onClickFrontView() {
		afficherNormal();
		lancerAnnonceDetail();
	}

}
