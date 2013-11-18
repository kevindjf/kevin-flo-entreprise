package fr.RivaMedia.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

public class Annonces extends FragmentNormal implements View.OnClickListener{

	//TODO recuperer le nombre d'annonces du jour
	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	public static final int BATEAUX = 1;
	public static final int MOTEURS = 4; //Constantes.MOTEURS
	public static final int DIVERS = 5; //Constantes.ACCESSOIRES

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.annonces,container, false);

		_ajourdhuiAnnonces = (TextView)view.findViewById(R.id.annonces_aujourd_hui);
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
	
	public void afficherFormulaireBateaux(int item){
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new AnnoncesFormulaire(item));
		transaction.addToBackStack(null);
		transaction.commit();
	}

	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_bouton_bateaux_et_voiliers:
			//Toast.makeText(getActivity(), "BATEAUX", Toast.LENGTH_SHORT).show();
			afficherFormulaireBateaux(BATEAUX);
			break;
		case R.id.annonces_bouton_moteurs:
			//Toast.makeText(getActivity(), "MOTEURS", Toast.LENGTH_SHORT).show();
			afficherFormulaireBateaux(MOTEURS);
			break;
		case R.id.annonces_bouton_accessoires_et_divers:
			//Toast.makeText(getActivity(), "DIVERS", Toast.LENGTH_SHORT).show();
			afficherFormulaireBateaux(DIVERS);
			break;
		}
	}

}
