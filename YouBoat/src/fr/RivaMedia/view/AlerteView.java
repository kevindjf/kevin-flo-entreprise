package fr.RivaMedia.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Alerte;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.utils.DateFrancais;
import fr.RivaMedia.view.core.YouBoatView;

public class AlerteView extends YouBoatView implements View.OnTouchListener{

	Alerte _alerte;
	int _position;
	boolean _swipable;

	TextView _titre;
	TextView _sousTitre;

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
	}

	@Override
	public void charger() {
		_titre = (TextView)getView().findViewById(R.id.alerte_element_liste_titre);
		_sousTitre = (TextView)getView().findViewById(R.id.alerte_element_liste_sous_titre);

		if(_swipable){
			_devant = getView().findViewById(R.id.devant);
			_derriere = getView().findViewById(R.id.derriere);
		}
		else
			_devant = getView();
	}

	@Override
	public void remplir() {
		Categorie c = Donnees.getCategorie(_alerte.getCategorie(),false);
		if(c != null && c.getLibelle() != null)
			_titre.setText(c.getLibelle());

		//TODO prix+longueur
		if(_alerte.getDate() != null )
			_sousTitre.setText(_alerte.getDate());

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

}
