package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;

@SuppressLint("ValidFragment")
public class BoatOnDemand extends Fragment{

	private TextView boat_on_demand_type_text;
	private TextView boat_on_demand_categorie_text;
	private TextView boat_on_demand_chantier_modele_text;
	private TextView boat_on_demand_etat_text;
	private TextView boat_on_demand_taille_text;
	private TextView boat_on_demand_lieu_text;
	private TextView boat_on_demand_budget_text;
	private TextView boat_on_demand_commentaire_text;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.boat_on_demand, container, false);
		
		boat_on_demand_type_text = (TextView) v.findViewById(R.id.boat_on_demand_type_text);
		boat_on_demand_categorie_text = (TextView) v.findViewById(R.id.boat_on_demand_categorie_text);
		boat_on_demand_chantier_modele_text = (TextView) v.findViewById(R.id.boat_on_demand_chantier_modele_text);
		boat_on_demand_etat_text = (TextView) v.findViewById(R.id.boat_on_demand_etat_text);
		boat_on_demand_taille_text = (TextView) v.findViewById(R.id.boat_on_demand_taille_text);
		boat_on_demand_lieu_text = (TextView) v.findViewById(R.id.boat_on_demand_lieu_text);
		boat_on_demand_budget_text = (TextView) v.findViewById(R.id.boat_on_demand_budget_text);
		boat_on_demand_commentaire_text = (TextView) v.findViewById(R.id.boat_on_demand_commentaire_text);
		
		return v;
	}

}
