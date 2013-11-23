package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class Annonces extends FragmentNormal implements View.OnClickListener{

	//TODO recuperer le nombre d'annonces du jour
	//private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.annonces,container, false);
		
		//_ajourdhuiAnnonces = (TextView)view.findViewById(R.id.annonces_aujourd_hui);
		_boutonBateauxVoiliers = view.findViewById(R.id.annonces_bouton_bateaux_et_voiliers);
		_boutonMoteurs = view.findViewById(R.id.annonces_bouton_moteurs);
		_boutonAccessoiresDivers = view.findViewById(R.id.annonces_bouton_accessoires_et_divers);

		ajouterListeners();

		return view;
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
