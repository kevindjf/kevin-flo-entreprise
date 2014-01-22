package fr.RivaMedia.AnnoncesBateauGenerique.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Alerte;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.utils.DateFrancais;
import fr.RivaMedia.AnnoncesBateauGenerique.view.core.YouBoatView;

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
		
		try{
		Categorie c = Donnees.getCategorieToutes(_alerte.getCategorie(),true);
		if(c != null && c.getLibelle() != null)
			_titre.setText(c.getLibelle());

		//TODO prix+longueur
		if(_alerte.getDate() != null ){
			
			String date = _alerte.getDate();
			_sousTitre.setText("Fait le " + DateFrancais.dateEnToFr(date.substring(0,10))+ " à " + date.substring(10));			
		}
		
		if(_alerte.getPrixMin() != null && _alerte.getPrixMin().trim().length() > 0){
			_prixMin.setText("Prix min : " + _alerte.getPrixMin());
		}
		
		if(_alerte.getPrixMax() != null && _alerte.getPrixMax().trim().length() > 0){
			_prixMax.setText("Prix max : " + _alerte.getPrixMax());
		}
		
		if(_alerte.getLongueurMin() != null && _alerte.getLongueurMin().trim().length() > 0){
			_longueurMin.setText("Longueur min : " + _alerte.getLongueurMin());
		}
		
		if(_alerte.getLongueurMax() != null && _alerte.getLongueurMax().trim().length() > 0){
			_longueurMax.setText("Longueur max : " + _alerte.getLongueurMax());
		}
		
		afficherNormal();
		}catch(Exception e){
			e.printStackTrace();
		}
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
