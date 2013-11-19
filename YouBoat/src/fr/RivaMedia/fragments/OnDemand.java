package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;

@SuppressLint("ValidFragment")
public class OnDemand extends FragmentNormal{

	View _view;
	
	View _type;
	View _categorie;
	View _chantierModele;
	View _etat;
	
	View _taille;
	View _lieu;
	View _budget;
	View _commentaire;
	
	View _typePosseder;
	View _categoriePosseder;
	View _chantierModelePosseder;
	View _prixCessionPosseder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.boat_on_demand, container, false);
		
		charger();
		remplir();
		ajouterListeners();
		
		return _view;
	}
	
	public void charger(){
		_type = _view.findViewById(R.id.boat_on_demand_type);
		_categorie = _view.findViewById(R.id.boat_on_demand_categorie);
		_chantierModele = _view.findViewById(R.id.boat_on_demand_chantier_modele);
		_etat = _view.findViewById(R.id.boat_on_demand_etat);
		_taille = _view.findViewById(R.id.boat_on_demand_taille);
		_lieu = _view.findViewById(R.id.boat_on_demand_lieu);
		_budget = _view.findViewById(R.id.boat_on_demand_budget);
		_commentaire = _view.findViewById(R.id.boat_on_demand_commentaire);
		_typePosseder = _view.findViewById(R.id.boat_on_demand_posseder_type);
		_categoriePosseder = _view.findViewById(R.id.boat_on_demand_posseder_categorie);
		_chantierModelePosseder = _view.findViewById(R.id.boat_on_demand_posseder_chantier_modele);
		_prixCessionPosseder = _view.findViewById(R.id.boat_on_demand_posseder_prix_cession);
	}
	
	public void remplir(){
		
	}
	
	public void ajouterListeners(){
		
	}

}
