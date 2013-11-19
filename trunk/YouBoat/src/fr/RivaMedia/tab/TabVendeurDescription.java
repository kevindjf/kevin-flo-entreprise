package fr.RivaMedia.tab;


import fr.RivaMedia.R;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.tab.core.Tab;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment") 
public class TabVendeurDescription extends Tab {

	Vendeur _vendeur;
	View _descriptionLabel;
	TextView _description;
	View _servicesLabel;
	ViewGroup _services;

	public TabVendeurDescription(String titre, Vendeur vendeur){
		super(titre);
		this._vendeur = vendeur;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.vendeur_description, container, false);

		if(_vendeur != null){
			_descriptionLabel = v.findViewById(R.id.vendeur_detail_description_label);
			_description = (TextView)v.findViewById(R.id.vendeur_detail_description);
			if(_vendeur.getDescription() != null)
				_description.setText(_vendeur.getDescription());
			else{
				_descriptionLabel.setVisibility(View.GONE);
				_description.setVisibility(View.GONE);
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
			}
			else{
				_servicesLabel.setVisibility(View.GONE);
				_services.setVisibility(View.GONE);
			}
		}


		return v;
	}

}