package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.AnnuaireLocaliteSelector;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.Ville;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.core.Net;

public class Annuaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener{

	public static final int MARQUE = 0;
	public static final int SERVICE = 1;
	public static final int LOCALITE = 2;

	View _view;
	View _rechercher;

	View _distributeur_marque;
	View _services;
	View _localite;

	View[] views;

	String marque_id = null;
	String service_id = null;
	Ville ville = null;
	String rayon = null;
	String longitude = null;
	String latitude = null;

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
			for(Marque m : Donnees.marquesDistribuees)
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
		_rechercher = _view.findViewById(R.id.annuaire_rechercher);

		views = new View[]{
				_distributeur_marque,
				_services,
				_localite				
		};
	}

	@Override
	public void remplir() {
	}

	@Override
	public void ajouterListeners() {
		_distributeur_marque.setOnClickListener(this);
		_services.setOnClickListener(this);
		_localite.setOnClickListener(this);
		_rechercher.setOnClickListener(this);
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
		ville = null;
		rayon = null;
		longitude = null;
		latitude = null;
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
		case R.id.annuaire_rechercher:
			rechercher();
			break;
		}

	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(marque_id != null && !marque_id.equals("-1"))
			Net.add(donnees, Constantes.VENDEURS_MARQUE_ID,marque_id);
		if(service_id != null && !service_id.equals("-1"))
			Net.add(donnees,Constantes.VENDEURS_SERVICE_ID,service_id);
		if(rayon != null)
			Net.add(donnees,Constantes.VENDEURS_RAYON,rayon);
		if(ville != null)
			Net.add(donnees,Constantes.VENDEURS_VILLE_ID,ville.getId());
		if(latitude != null)
			Net.add(donnees,Constantes.VENDEURS_LATITUDE,latitude);
		if(longitude != null)
			Net.add(donnees,Constantes.VENDEURS_LONGITUDE,longitude);

		return donnees;
	}

	protected void rechercher(){
		ajouterFragment(new VendeursListe(recupererDonnees()));
	}

	protected void demanderDistributeurDeLaMarque(){
		ajouterFragment( new DonneeValeurSelector(
				this,
				MARQUE,
				false,
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
			marque_id = item;
			((TextView)_distributeur_marque.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == SERVICE){
			service_id = item;
			((TextView)_services.findViewById(R.id.text)).setText(value);
		}
	}

	public void localiteSelected(Ville ville, String nomVille, String rayon, String longitude, String latitude){
		this.ville = ville;
		this.rayon = rayon;
		this.longitude = longitude;
		this.latitude = latitude;
		
		try{
			Double f = Double.parseDouble(longitude);
			Double f2 = Double.parseDouble(latitude);
			double longitudeF = f.doubleValue();
			double latitudeF = f2.doubleValue();
			
			double longitudeRadian = longitudeF * Math.PI / 180;
			double latitudeRadian = latitudeF * Math.PI / 180;
			
			this.longitude = ""+longitudeRadian;
			this.latitude = ""+latitudeRadian;
			
		}catch(Exception e){}
		
		Log.e("LOCALITE",ville+" | "+rayon+" | "+longitude+" | "+latitude);
		
		String text = this.rayon+" "+getString(R.string.km_autour_de)+" "+nomVille;

		((TextView)_localite.findViewById(R.id.text)).setText(text);
	}
}
