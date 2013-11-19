package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.AnnuaireLocaliteSelector;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.core.Donnees;

public class Annuaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener{

	public static final int MARQUE = 0;
	public static final int SERVICE = 1;
	public static final int LOCALITE = 2;

	View _view;

	View _distributeur_marque;
	View _services;
	View _localite;

	View[] views;

	String marque_id = null;
	String service_id = null;
	String localite = null;
	String rayon = null;

	static Map<String,String> marques_id = null;
	static Map<String,String> services_id = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.annuaire, container, false);
		charger();
		remplir();
		ajouterListeners();

		if(marques_id == null){
			marques_id = new HashMap<String,String>();
			for(Marque m : Donnees.toutesMarques)
				marques_id.put(m.getLibelle(),m.getId());
		}
		if(services_id == null){
			services_id = new HashMap<String,String>();
			for(Service s : Donnees.services)
				services_id.put(s.getLibelle(),s.getId());
		}

		return _view;
	}

	@Override
	public void charger() {
		_distributeur_marque = _view.findViewById(R.id.annuaire_distributeur_de_la_marque);
		_services = _view.findViewById(R.id.annuaire_services);
		_localite = _view.findViewById(R.id.annuaire_localite);
	}

	@Override
	public void remplir() {
	}

	@Override
	public void ajouterListeners() {
		_distributeur_marque.setOnClickListener(this);
		_services.setOnClickListener(this);
		_localite.setOnClickListener(this);
	}

	@Override
	public void effacer() {
		reset();	
	}

	@Override
	public void reset() {
		for(View v : views){
			((TextView)v.findViewById(R.id.text)).setText("");
		}
		marque_id = null;
		service_id = null;
		localite = null;
		rayon = null;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annuaire_distributeur_de_la_marque:
			demanderDistributeurDeLaMarque();
			break;
		case R.id.annuaire_services:
			demanderServices();
			break;
		case R.id.annuaire_localite:
			demanderLocalite();
			break;
		}

	}

	protected void demanderDistributeurDeLaMarque(){
		ajouterFragment( new DonneeValeurSelector(
				this,
				MARQUE,
				marques_id));
	}

	protected void demanderServices(){
		ajouterFragment( new DonneeValeurSelector(
				this,
				SERVICE,
				services_id));

	}

	protected void demanderLocalite(){
		ajouterFragment( new AnnuaireLocaliteSelector(
				this,
				LOCALITE));
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item,String value) {
		if(idRetour == MARQUE){
			marque_id = value;
			((TextView)_distributeur_marque.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == SERVICE){
			service_id = value;
			((TextView)_services.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == LOCALITE){
			localite = item;
			rayon = value;
			String[] rp = rayon.split(";");
			if(rp.length == 2){
				String r = rp[0];
				String p = rp[1];
				
				String text = r+" "+getString(R.string.km_autour_de)+" "+p;

				((TextView)_localite.findViewById(R.id.text)).setText(text);
			}
		}

	}
}
