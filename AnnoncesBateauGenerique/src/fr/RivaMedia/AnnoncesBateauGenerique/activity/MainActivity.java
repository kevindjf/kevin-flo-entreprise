
package fr.RivaMedia.AnnoncesBateauGenerique.activity;

import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.navdrawer.SimpleSideDrawer;

import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Accueil;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Actualites;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Annonces;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Annuaire;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.ContactPro;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Credit;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.EmailFragment;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Informations;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.MesAlertes;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.MesAnnonces;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.OnDemand;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.Vendre;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.Effaceable;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnBackStackChangedListener{

	static LinkedList<Fragment> fragments = new LinkedList<Fragment>();
	
	SimpleSideDrawer _slider;
	View _header_menu;
	View _header_effacer;
	View _header_favoris;
	View _header_trier;
	View _header_plus;

	View _slider_accueil;
	View _slider_annonces;
	View _slider_vendre;
	View _slider_on_demand;
	View _slider_actualites;
	View _slider_annuaire;
	View _slider_mes_annonces;
	View _slider_mes_alertes;
	View _slider_informations;
	View _slider_contact_pro;
	View _slider_credits;

	View[] _slider_elements;

	View _progress;

	ImageView _slider_logo;
	TextView _header_titre;

	Annonce _annoncePourFavoris;

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
		_slider.setRightBehindContentView( R.layout.slider_right);
		_header_menu = findViewById(R.id.header_menu);
		_header_effacer = findViewById(R.id.header_effacer);
		_header_favoris = findViewById(R.id.header_favoris);
		_header_trier = findViewById(R.id.header_tri);
		_header_plus = findViewById(R.id.header_plus);
		_header_titre = (TextView)findViewById(R.id.header_titre);

		_slider_annonces = findViewById(R.id.slider_annonces);
		_slider_vendre = findViewById(R.id.slider_vendre);
		_slider_on_demand = findViewById(R.id.slider_on_demand);
		_slider_actualites = findViewById(R.id.slider_actualites);
		_slider_annuaire = findViewById(R.id.slider_annuaire);
		_slider_mes_annonces = findViewById(R.id.slider_mes_annonces);
		_slider_mes_alertes = findViewById(R.id.slider_mes_alertes);
		_slider_informations = findViewById(R.id.slider_informations);
		_slider_contact_pro = findViewById(R.id.slider_contact_pro);
		_slider_credits = findViewById(R.id.slider_credits);
		_slider_accueil = findViewById(R.id.slider_accueil);
		_slider_logo = (ImageView)findViewById(R.id.slider_image_entete);
		
		_slider_elements = new View[]{
				_slider_accueil,
				_slider_annonces,
				_slider_vendre,
				_slider_on_demand,
				_slider_actualites,
				_slider_annuaire,
				_slider_mes_annonces,
				_slider_mes_alertes,
				_slider_informations,
				_slider_contact_pro,
				_slider_credits
		};

		_progress = findViewById(R.id.header_progress);
	}

	protected void charger(){

		if (fragments.size() == 0)
			afficherAccueil();
		
		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(), _slider_logo);

	}

	protected void ajouterListeners(){
		_header_menu.setOnClickListener(this);
		_header_favoris.setOnClickListener(this);
		getSupportFragmentManager().addOnBackStackChangedListener(this);
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

		if(v.getId() == R.id.header_menu){
			ouvrirSlider();
		}
		else if(v.getId() ==  R.id.slider_accueil){
			fermerSlider();	
			afficherAccueil();
		}
		else if(v.getId() ==  R.id.slider_annonces){
			fermerSlider();	
			afficherAnnonces();
		}
		else if(v.getId() == R.id.slider_vendre){
			fermerSlider();	
			afficherVendre();
		}
		else if(v.getId() == R.id.slider_on_demand){
			fermerSlider();		
			afficherBoatOnDemand();
		}
		else if(v.getId() == R.id.slider_actualites){
			fermerSlider();		
			afficherActualites();
		}
		else if(v.getId() == R.id.slider_annuaire){
			fermerSlider();		
			afficherAnnuaire();
		}
		else if(v.getId() == R.id.slider_mes_annonces){
			fermerSlider();	
			afficherMesAnnonces();
		}
		else if(v.getId() == R.id.slider_mes_alertes){
			fermerSlider();		
			afficherMesAlertes();
		}
		else if(v.getId() == R.id.slider_informations){
			fermerSlider();	
			afficherInformations();
		}
		else if(v.getId() ==  R.id.slider_contact_pro){
			fermerSlider();	
			afficherContactPro();
		}
		else if(v.getId() ==  R.id.slider_credits){
			fermerSlider();	
			afficherCredits();
		}

	}
	
	public void setTitre(String titre){
		_header_titre.setText(titre);
	}

	public void afficherAccueil(){
		ajouterFragment(new Accueil(),false);
	}
	public void afficherAnnonces(){
		ajouterFragment(new Annonces(),false);
	}
	public void afficherVendre(){
		ajouterFragment(new Vendre(),false);
	}
	public void afficherBoatOnDemand(){
		ajouterFragment(new OnDemand(),false);
	}
	public void afficherActualites(){
		ajouterFragment(new Actualites(),false);
	}
	public void afficherAnnuaire(){
		ajouterFragment(new Annuaire(),false);
	}
	public void afficherMesAnnonces(){
		ajouterFragment(new MesAnnonces(),false);
	}

	public void afficherMesAlertes(){
		ajouterFragment(new MesAlertes(),false);
	}

	public void afficherInformations(){
		ajouterFragment(new Informations(),false);
	}

	public void afficherContactPro(){
		ajouterFragment(new ContactPro(),false);
	}

	public void afficherCredits(){
		ajouterFragment(new Credit(),false);
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

	public void cacherTrier(){
		_header_trier.setVisibility(View.GONE);
		_header_trier.setOnClickListener(null);
	}

	public View afficherPlus(){
		_header_plus.setVisibility(View.VISIBLE);
		return _header_plus;
	}

	public void cacherPlus(){
		_header_plus.setVisibility(View.GONE);
		_header_plus.setOnClickListener(null);
	}

	public void envoyerEmailVendeur(String email, Vendeur vendeur){
		envoyerEmail(email, EmailFragment.EMAIL_CLIENT, vendeur);
	}

	public void envoyerEmailAnnonce(String email,  Annonce annonce){
		envoyerEmail(email, EmailFragment.EMAIL_ANNNONCE, annonce);
	}


	private void envoyerEmail(String email, int type, Object objet){
		ajouterFragment(new EmailFragment(type, objet));
	}

	public void envoyerEmailDirect(String email){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("mailto:"+email));
		intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
		startActivity(intent);
	}

	public void appeller(String phone){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final String phone2 = phone.replace(".", "").replace(" ","").replace(",","");
		alert.setTitle(getString(R.string.appeller)+" "+phone2);
		alert.setCancelable(true);
		alert.setPositiveButton(R.string.oui, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int pos) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);

				callIntent.setData(Uri.parse("tel:"+phone2));
				startActivity(callIntent);
			}
		});

		alert.setNegativeButton(R.string.non, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}

		});

		alert.show();

	}

	public View afficherFavoris(){
		_header_favoris.setVisibility(View.VISIBLE);
		return _header_favoris;
	}

	public void cacherFavoris(){
		_header_favoris.setVisibility(View.GONE);
		_header_favoris.setOnClickListener(null);
		_annoncePourFavoris = null;
	}

	public View afficherTrier(){
		_header_trier.setVisibility(View.VISIBLE);
		return _header_trier;
	}

	public SimpleSideDrawer getSliderDroite(){
		return _slider;
	}

	public void afficherSliderDroite(){
		_slider.openRightSide();
	}
	public void fermerSliderDroite(){
		_slider.closeRightSide();
	}

	@SuppressLint("NewApi")
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onKeyDown(int keycode, KeyEvent event ) {
		if(keycode == KeyEvent.KEYCODE_MENU){
			if(_slider != null)
				ouvrirSlider();
		}
		return super.onKeyDown(keycode,event);  
	}
	
	
	public void ajouterFragment(Fragment fragment) {
		ajouterFragment(fragment, true);
	}

	public void retirerFragment() {
		try {
			fragments.removeLast().onPause();
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.fragment_container, fragments.getLast());
			transaction.commit();
		} catch (Exception e) {
		}
	}

	public void ajouterFragment(Fragment fragment, boolean back) {
		if (!back)
			fragments.clear();

		fragments.addLast(fragment);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_container, fragments.getLast());

		if (back)
			transaction.addToBackStack(null);

		transaction.commit();
	}

	public void onBackStackChanged() {
		FragmentManager manager = getSupportFragmentManager();

		if (manager != null) {
			Fragment currFrag = (Fragment) manager
					.findFragmentById(R.id.fragment_container);

			currFrag.onResume();
		}
	}

	@Override
	public void onBackPressed() {

		FragmentManager manager = getSupportFragmentManager();

		if (manager != null) {
			retirerFragment();

			if (fragments.size() == 0)
				this.finish();
		}
	}



}
