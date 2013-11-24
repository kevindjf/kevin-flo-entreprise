package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.core.Donnees;

public class Annonces extends FragmentNormal implements View.OnClickListener{

	View _view;
	
	//TODO recuperer le nombre d'annonces du jour
	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces,container, false);
		
		_ajourdhuiAnnonces = (TextView)_view.findViewById(R.id.annonces_aujourd_hui);
		_boutonBateauxVoiliers = _view.findViewById(R.id.annonces_bouton_bateaux_et_voiliers);
		_boutonMoteurs = _view.findViewById(R.id.annonces_bouton_moteurs);
		_boutonAccessoiresDivers = _view.findViewById(R.id.annonces_bouton_accessoires_et_divers);

		ajouterListeners();
		charger();

		return _view;
	}

	public void ajouterListeners(){
		if(_boutonBateauxVoiliers != null && _boutonMoteurs != null && _boutonAccessoiresDivers != null){
			_boutonBateauxVoiliers.setOnClickListener(this);
			_boutonMoteurs.setOnClickListener(this);
			_boutonAccessoiresDivers.setOnClickListener(this);
		}
	}

	@Override
	public void charger() {
		if(Donnees.nbAnnonces.size() > 0){
			
			
			Integer nbBateaux = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_BATEAUX);
			Integer nbMoteurs = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_MOTEURS);
			Integer nbDivers = Donnees.nbAnnonces.get(Constantes.NB_ANNONCES_DIVERS);
			
			Integer nbAnnonces = Integer.valueOf(nbBateaux.intValue()+nbMoteurs.intValue()+nbDivers.intValue());
			
			_ajourdhuiAnnonces.setText(getString(R.string.aujourd_hui)+", "+nbAnnonces+" "+getString(R.string.annonces_small));
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_bateaux)).setText(nbBateaux.toString());
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_moteurs)).setText(nbMoteurs.toString());
			((TextView)_view.findViewById(R.id.annonces_nb_annonces_accessoires)).setText(nbDivers.toString());
			
		}
	}

	@Override
	public void remplir() {
	}
	
	public void afficherFormulaireBateaux(String item){
		ajouterFragment(new AnnoncesFormulaire(item));
	}

	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_bouton_bateaux_et_voiliers:
			afficherFormulaireBateaux(Constantes.BATEAUX);
			break;
		case R.id.annonces_bouton_moteurs:
			afficherFormulaireBateaux(Constantes.MOTEURS);
			break;
		case R.id.annonces_bouton_accessoires_et_divers:
			afficherFormulaireBateaux(Constantes.ACCESSOIRES);
			break;
		}
	}

}
