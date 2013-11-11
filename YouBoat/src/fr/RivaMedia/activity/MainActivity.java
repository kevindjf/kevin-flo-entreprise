package fr.RivaMedia.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.navdrawer.SimpleSideDrawer;

import fr.RivaMedia.R;
import fr.RivaMedia.fragments.*;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

	SimpleSideDrawer _slider;
	View _header_menu;

	View _slider_annonces;
	View _slider_vendre;
	View _slider_boat_on_demand;
	View _slider_actualites;
	View _slider_annuaire;
	View _slider_mes_annonces;
	View _slider_mes_alertes;
	View _slider_informations;
	View _slider_contact_pro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ajouterVues();
		charger();
		ajouterListeners();
	}

	protected void ajouterVues(){
		_slider = new SimpleSideDrawer( this );
		_slider.setLeftBehindContentView( R.layout.slider);
		_header_menu = findViewById(R.id.header_menu);

		_slider_annonces = findViewById(R.id.slider_annonces);
		_slider_vendre = findViewById(R.id.slider_vendre);
		_slider_boat_on_demand = findViewById(R.id.slider_boat_on_demand);
		_slider_actualites = findViewById(R.id.slider_actualites);
		_slider_annuaire = findViewById(R.id.slider_annuaire);
		_slider_mes_annonces = findViewById(R.id.slider_mes_annonces);
		_slider_mes_alertes = findViewById(R.id.slider_mes_alertes);
		_slider_informations = findViewById(R.id.slider_informations);
		_slider_contact_pro = findViewById(R.id.slider_contact_pro);
	}

	protected void charger(){
	}

	protected void ajouterListeners(){
		_header_menu.setOnClickListener(this);

		_slider_annonces.setOnClickListener(this);
		_slider_vendre.setOnClickListener(this);
		_slider_boat_on_demand.setOnClickListener(this);
		_slider_actualites.setOnClickListener(this);
		_slider_annuaire.setOnClickListener(this);
		_slider_mes_annonces.setOnClickListener(this);
		_slider_mes_alertes.setOnClickListener(this);
		_slider_informations.setOnClickListener(this);
		_slider_contact_pro.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.header_menu:
			_slider.toggleLeftDrawer();	
			break;

		case R.id.slider_annonces:
			_slider.toggleLeftDrawer();	
			afficherAnnonces();
			break;
		case R.id.slider_vendre:
			_slider.toggleLeftDrawer();	
			afficherVendre();
			break;
		case R.id.slider_boat_on_demand:
			_slider.toggleLeftDrawer();	
			afficherBoatOnDemand();
			break;
		case R.id.slider_actualites:
			_slider.toggleLeftDrawer();	
			afficherActualites();
			break;
		case R.id.slider_annuaire:
			_slider.toggleLeftDrawer();	
			afficherAnnuaire();
			break;
		case R.id.slider_mes_annonces:
			_slider.toggleLeftDrawer();	
			afficherMesAnnonces();
			break;
		case R.id.slider_mes_alertes:
			_slider.toggleLeftDrawer();	
			afficherMesAlertes();
			break;
		case R.id.slider_informations:
			_slider.toggleLeftDrawer();	
			afficherInformations();
			break;
		case R.id.slider_contact_pro:
			_slider.toggleLeftDrawer();	
			afficherContactPro();
			break;
		}

	}

	public void afficherAnnonces(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new Annonces());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void afficherVendre(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new Vendre());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void afficherBoatOnDemand(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new BoatOnDemand());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void afficherActualites(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new Actualites());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void afficherAnnuaire(){}
	public void afficherMesAnnonces(){}
	
	public void afficherMesAlertes(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new MesAlertes());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void afficherInformations(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new Informations());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void afficherContactPro(){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.main_fragment, new ContactPro());
		transaction.addToBackStack(null);
		transaction.commit();
	}



}
