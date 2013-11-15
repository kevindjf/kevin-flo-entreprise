package fr.RivaMedia.view;

import android.content.Context;
import android.renderscript.Element;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.ActualiteDetail;
import fr.RivaMedia.fragments.AnnonceDetail;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Bateau;
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
	
	public AnnonceView(Object element, Context context, View view, int position, String type) {
		super(context, view);
		ImageLoaderCache.load(getContext());
		
		this._element = element;
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
		if(_element instanceof Bateau){
			Bateau bateau = (Bateau)_element;
			
			if(bateau.getLien() != null)
				ImageLoaderCache.charger(bateau.getLien().getUrl(),_image);
			_titre.setText(bateau.getTitle());
			_sousTitre.setText(bateau.getNomMoteur());
			_taille.setText(bateau.getLongueur()+" m");
			_annee.setText(bateau.getAnnee());
			_prix.setText(bateau.getPrix()+" €");
			
		}else if(_element instanceof Moteur){
			Moteur moteur = (Moteur)_element;
			
			_image.setVisibility(View.GONE);
			_titre.setText(moteur.getMarqueMoteur());
			_sousTitre.setText(moteur.getInfoMoteur());
			_taille.setText(moteur.getPuissanceMoteur()+" ch");
			_annee.setVisibility(View.GONE);
			_prix.setText(moteur.getPrix()+" €");
		}
		/*else if(_element instanceof Divers){
			
		}*/
		
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
				lancerAnnonceDetail(_element);
		}
	}
	
	private void afficherNormal(){
		if(_position%2==0){
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.blanc));
		}else{
			getView().setBackgroundColor(getContext().getResources().getColor(R.color.bleu_claire));
		}
	}
	private void afficherTouch(){
		getView().setBackgroundColor(getContext().getResources().getColor(R.color.blue));
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
	
	public void lancerAnnonceDetail(Object element){
		String id = "";
		if(_element instanceof Bateau){
			Bateau bateau = (Bateau)_element;
			id = bateau.getNumero();
		}else if(_element instanceof Moteur){
			Moteur moteur = (Moteur)_element;
			//TODO trouver l'id d'un moteur
		}
		
		FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new AnnonceDetail(id,_type));
		transaction.addToBackStack(null);
		transaction.commit();
	}

}
