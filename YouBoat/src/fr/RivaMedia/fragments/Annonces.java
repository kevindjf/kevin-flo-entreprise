package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class Annonces extends Fragment implements View.OnClickListener{

	private TextView _ajourdhuiAnnonces;
	private View _boutonBateauxVoiliers;
	private View _boutonMoteurs;
	private View _boutonAccessoiresDivers;

	private ItemSelectedListener listener;

	public static final int BATEAUX = 1;
	public static final int MOTEURS = 2;
	public static final int DIVERS = 3;

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

	protected void ajouterListeners(){
		if(_boutonBateauxVoiliers != null && _boutonMoteurs != null && _boutonAccessoiresDivers != null){
			_boutonBateauxVoiliers.setOnClickListener(this);
			_boutonMoteurs.setOnClickListener(this);
			_boutonAccessoiresDivers.setOnClickListener(this);
		}
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

	public void setListener(ItemSelectedListener listener) {
		this.listener = listener;
	}


}
