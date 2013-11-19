package fr.RivaMedia.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.navdrawer.SimpleSideDrawer;

import fr.RivaMedia.R;
import fr.RivaMedia.fragments.*;
import fr.RivaMedia.fragments.core.Effaceable;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

	SimpleSideDrawer _slider;
	View _header_menu;
	View _header_effacer;

	View _slider_annonces;
	View _slider_vendre;
	View _slider_boat_on_demand;
	View _slider_actualites;
	View _slider_annuaire;
	View _slider_mes_annonces;
	View _slider_mes_alertes;
	View _slider_informations;
	View _slider_contact_pro;

	View[] _slider_elements;

	View _progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ajouterVues();
		charger();
		ajouterListeners();
	}
	public void afficherProgress(boolean afficher){
		if(afficher)
			_progress.setVisibility(View.VISIBLE);
		else
			_progress.setVisibility(View.GONE);
	}

	protected void ajouterVues(){
		_slider = new SimpleSideDrawer( this );
		_slider.setLeftBehindContentView( R.layout.slider);
		_header_menu = findViewById(R.id.header_menu);
		_header_effacer = findViewById(R.id.header_effacer);

		_slider_annonces = findViewById(R.id.slider_annonces);
		_slider_vendre = findViewById(R.id.slider_vendre);
		_slider_boat_on_demand = findViewById(R.id.slider_boat_on_demand);
		_slider_actualites = findViewById(R.id.slider_actualites);
		_slider_annuaire = findViewById(R.id.slider_annuaire);
		_slider_mes_annonces = findViewById(R.id.slider_mes_annonces);
		_slider_mes_alertes = findViewById(R.id.slider_mes_alertes);
		_slider_informations = findViewById(R.id.slider_informations);
		_slider_contact_pro = findViewById(R.id.slider_contact_pro);

		_slider_elements = new View[]{
				_slider_annonces,
				_slider_vendre,
				_slider_boat_on_demand,
				_slider_actualites,
				_slider_annuaire,
				_slider_mes_annonces,
				_slider_mes_alertes,
				_slider_informations,
				_slider_contact_pro
		};

		_progress = findViewById(R.id.header_progress);
	}

	protected void charger(){
		afficherAnnonces();
	}

	protected void ajouterListeners(){
		_header_menu.setOnClickListener(this);
	}

	public void ouvrirSlider(){
		for(View v : this._slider_elements)
			v.setOnClickListener(this);
		_slider.toggleLeftDrawer();	
	}
	public void fermerSlider(){
		for(View v : this._slider_elements)
			v.setOnClickListener(null);
		_slider.toggleLeftDrawer();	
	}

	@Override
	public void onClick(View v) {

		Log.d("ID",""+v.getId());

		switch(v.getId()){
		case R.id.header_menu:
			ouvrirSlider();
			break;

		case R.id.slider_annonces:
			fermerSlider();	
			afficherAnnonces();
			break;
		case R.id.slider_vendre:
			fermerSlider();	
			afficherVendre();
			break;
		case R.id.slider_boat_on_demand:
			fermerSlider();		
			afficherBoatOnDemand();
			break;
		case R.id.slider_actualites:
			fermerSlider();		
			afficherActualites();
			break;
		case R.id.slider_annuaire:
			fermerSlider();		
			afficherAnnuaire();
			break;
		case R.id.slider_mes_annonces:
			fermerSlider();	
			afficherMesAnnonces();
			break;
		case R.id.slider_mes_alertes:
			fermerSlider();		
			afficherMesAlertes();
			break;
		case R.id.slider_informations:
			fermerSlider();	
			afficherInformations();
			break;
		case R.id.slider_contact_pro:
			fermerSlider();	
			afficherContactPro();
			break;
		}

	}



	public void afficherAnnonces(){
		ajouterFragment(new Annonces());
	}
	public void afficherVendre(){
		ajouterFragment(new Vendre());
	}
	public void afficherBoatOnDemand(){
		ajouterFragment(new OnDemand());
	}
	public void afficherActualites(){
		ajouterFragment(new Actualites());
	}
	public void afficherAnnuaire(){
		ajouterFragment(new Annuaire());
	}
	public void afficherMesAnnonces(){
		ajouterFragment(new MesAnnonces());
	}

	public void afficherMesAlertes(){
		ajouterFragment(new MesAlertes());
	}

	public void afficherInformations(){
		ajouterFragment(new Informations());
	}

	public void afficherContactPro(){
		ajouterFragment(new ContactPro());
	}
	
	public void afficherEffacer(final Effaceable effaceable){
		_header_effacer.setVisibility(View.VISIBLE);
		_header_effacer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				effaceable.effacer();
			}
		});
	}
	public void cacherEffacer(){
		_header_effacer.setVisibility(View.GONE);
		_header_effacer.setOnClickListener(null);
	}
	
	public void ajouterFragment(Fragment fragment){
		ajouterFragment(fragment,true);
	}
	public void ajouterFragment(Fragment fragment, boolean back){
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		//transaction.remove(getSupportFragmentManager().findFragmentById(R.id.main_fragment));
		transaction.add(R.id.main_fragment, fragment);

		if(back)
			transaction.addToBackStack(null);
		
		transaction.commit();
	}
}
