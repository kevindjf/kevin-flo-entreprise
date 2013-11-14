package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.factory.BateauFactory;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends Fragment implements View.OnClickListener{

	private OnItemSelectedListener listener;
	private int typeAnnonces;
	
	View _view;
	View _rechercher;
	
	public AnnoncesFormulaire(int type){
		this.typeAnnonces = type;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}

	protected void charger(){
		_rechercher = _view.findViewById(R.id.annonces_formulaire_bouton_rechercher);		
	}
	protected void remplir(){
	}
	protected void ajouterListeners(){
		_rechercher.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_formulaire_bouton_rechercher:
			afficherAnnoncesListe();
			break;
		}
	}
	
	public void afficherAnnoncesListe(){
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new AnnoncesListe());
		transaction.addToBackStack(null);
		transaction.commit();
	}



}
