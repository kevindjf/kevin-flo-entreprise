package fr.RivaMedia.AnnoncesBateauGenerique.tab.vendeur;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.Tab;


@SuppressLint("ValidFragment") 
public class TabVendeurDescription extends Tab {

	Activity _activity;

	Vendeur _vendeur;
	View _descriptionLabel;
	TextView _description;
	View _horairesLabel;
	TextView _horaires;
	View _servicesLabel;
	ViewGroup _services;

	public TabVendeurDescription(String titre, Vendeur vendeur, Activity activity){
		super(titre,activity.getResources().getDrawable(R.drawable.logo_vendeur_blanc));
		this._vendeur = vendeur;
		this._activity = activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.vendeur_description, container, false);

		

		boolean av = true;

		if(_vendeur != null){
			_descriptionLabel = v.findViewById(R.id.vendeur_detail_description_label);
			_description = (TextView)v.findViewById(R.id.vendeur_detail_description);
			if(_vendeur.getDescription() != null){
				_description.setText(_vendeur.getDescription());
				av = false;
			}
			else{
				_descriptionLabel.setVisibility(View.GONE);
				_description.setVisibility(View.GONE);
			}

			_horairesLabel = v.findViewById(R.id.vendeur_detail_horaires_label);
			_horaires = (TextView)v.findViewById(R.id.vendeur_detail_horaires);
			if(_vendeur.getHoraires() != null){
				_horaires.setText(_vendeur.getHoraires());
				av = false;
			}
			else{
				_horairesLabel.setVisibility(View.GONE);
				_horaires.setVisibility(View.GONE);
			}

			_servicesLabel = v.findViewById(R.id.vendeur_detail_services_label);
			_services = (ViewGroup)v.findViewById(R.id.vendeur_detail_services);
			if(_vendeur.getServices() != null && _vendeur.getServices().size()>0){
				for(String service : _vendeur.getServices() ){
					View layout_service = inflater.inflate(R.layout.vendeur_description_service, container, false);
					TextView service_text = (TextView)layout_service.findViewById(R.id.text);
					service_text.setText(service);
					_services.addView(layout_service);
				}
				av = false;
			}
			else{
				_servicesLabel.setVisibility(View.GONE);
				_services.setVisibility(View.GONE);
			}
			
		}
		
		if(av){
			v.findViewById(R.id.vide).setVisibility(View.VISIBLE);
			((TextView)v.findViewById(R.id.vide_text)).setText(getString(R.string.vendeur_aucune_description));
		}


		return v;
	}

}