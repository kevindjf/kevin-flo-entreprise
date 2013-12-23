package fr.RivaMedia.AnnoncesAutoGenerique.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Alerte;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.view.core.YouBoatView;

public class AlerteView extends YouBoatView implements View.OnTouchListener{

	Alerte _alerte;
	int _position;
	boolean _swipable;

	TextView _titre;
	TextView _sousTitre;
	TextView _prixMin;
	TextView _prixMax;
	TextView _longueurMin;
	TextView _longueurMax;
	View _derriere;
	View _devant;

	public AlerteView(Alerte alerte, Context context, View view, int position, boolean swipable) {
		super(context, view);

		this._alerte = alerte;
		this._position = position;
		this._swipable = swipable;
		charger();
		remplir();
		ajouterListeners();
		
		afficherNormal();
	}

	@Override
	public void charger() {
		_titre = (TextView)getView().findViewById(R.id.alerte_element_liste_titre);
		_sousTitre = (TextView)getView().findViewById(R.id.alerte_element_liste_sous_titre);
		_prixMin = (TextView)getView().findViewById(R.id.prix_min);
		_prixMax = (TextView)getView().findViewById(R.id.prix_max);
		_longueurMin = (TextView)getView().findViewById(R.id.longueur_min);
		_longueurMax = (TextView)getView().findViewById(R.id.longueur_max);
		
		if(_swipable){
			_devant = getView().findViewById(R.id.devant);
			_derriere = getView().findViewById(R.id.derriere);
		}
		else
			_devant = getView();
	}

	@Override
	public void remplir() {
		//TODO
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

}
