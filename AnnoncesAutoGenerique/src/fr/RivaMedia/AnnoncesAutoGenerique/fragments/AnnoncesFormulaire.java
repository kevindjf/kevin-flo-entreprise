package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.MinMaxDialog;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.OnMinMaxListener;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentFormulaire;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.MarqueSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.ValeurSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Departement;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAlerte;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesAutoGenerique.utils.JetonManager;

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	public static int CARROSSERIE = 1;
	public static int MARQUE_MODELE = 2;
	public static int MARQUE = 3;

	public static int ETAT = 4;
	public static int DEPARTEMENT = 5;

	public static int ENERGIE = 6;
	public static int BOITE_VITESSE = 7;

	View _view;
	View _rechercher;
	TextView _nombreAnnonces;

	View _carrosserie;
	View _marqueModele;
	View _energie;
	View _boite_de_vitesse;
	View _nb_portes;
	View _annee;
	View _km_max;
	View _prix;
	View _departement;

	View[] _views;



	List<NameValuePair> donnees = new ArrayList<NameValuePair>();


	String recherche_carrosserie_id = null;
	String recherche_marque_id = null;
	String recherche_modele_id = null;

	String recherche_energie_id = null;

	String recherche_annee_min = null;
	String recherche_annee_max = null;

	String recherche_prix_min = null;
	String recherche_prix_max = null;

	String recherche_longueur_min = null;
	String recherche_longueur_max = null;

	String recherche_puissance_min = null;
	String recherche_puissance_max = null;

	String recherche_departement_id = null;
	String recherche_etat_id = null;

	List<Marque> _marques = null;

	public AnnoncesFormulaire(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_formulaire,container, false);
		charger();
		ajouterListeners();
		chargerCouleurs();

		rechercherNombre();

		return _view;
	}

	public void charger(){
		_rechercher = _view.findViewById(R.id.annonces_formulaire_bouton_rechercher);	
		_nombreAnnonces = (TextView)_view.findViewById(R.id.annonces_formulaire_nombre_annonces);	

		_carrosserie = _view.findViewById(R.id.annonces_formulaire_carrosserie);
		_marqueModele = _view.findViewById(R.id.annonces_formulaire_marque_modele);
		_energie = _view.findViewById(R.id.annonces_formulaire_energie);
		_boite_de_vitesse = _view.findViewById(R.id.annonces_formulaire_boite_vitesse);
		_nb_portes  = _view.findViewById(R.id.annonces_formulaire_nb_porte);
		_annee = _view.findViewById(R.id.annonces_formulaires_annee);
		_km_max = _view.findViewById(R.id.annonces_formulaires_km_max);
		_prix = _view.findViewById(R.id.annonces_formulaires_prix);
		_departement = _view.findViewById(R.id.annonces_formulaires_departement);

		_views = new View[]{
				_carrosserie,
				_marqueModele,
				_energie,
				_boite_de_vitesse,
				_nb_portes,
				_annee,
				_km_max,
				_prix,
				_departement,
		};
		
		
	}

	

	public void chargerCouleurs(){
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		
		afficherCouleurTouch(_rechercher);
		selector(_rechercher,false);
		
		afficherCouleurNormal(_view.findViewById(R.id.annonces_formulaire_separator_1),
				_view.findViewById(R.id.annonces_formulaire_separator_2),
				_view.findViewById(R.id.annonces_formulaire_separator_3));
		
		afficherTexteCouleurTitre(_view.findViewById(R.id.annonces_formulaire_separator_1),
				_view.findViewById(R.id.annonces_formulaire_separator_2),
				_view.findViewById(R.id.annonces_formulaire_separator_3));
		
		afficherTexteCouleurTexte(_views);
	}

	public void remplir(){
		charger();
		for(View v : _views)
			v.setVisibility(View.VISIBLE);	
	}

	public void ajouterListeners(){
		_rechercher.setOnClickListener(this);
		_carrosserie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_energie.setOnClickListener(this);
		_boite_de_vitesse.setOnClickListener(this);
		_annee.setOnClickListener(this);
		_prix.setOnClickListener(this);
		_departement.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.annonces_formulaire_bouton_rechercher) {
			rechercher();
		} else if (id == R.id.annonces_formulaire_carrosserie) {
			demanderCarrosserie();
		} else if (id == R.id.annonces_formulaire_marque_modele) {
			demanderMarqueModele();
		} else if (id == R.id.annonces_formulaire_energie) {
			demanderEnergie();
		} else if (id == R.id.annonces_formulaire_boite_vitesse) {
			demanderBoiteVitesse();
		} else if (id == R.id.annonces_formulaires_annee) {
			demanderAnnee();
		} else if (id == R.id.annonces_formulaires_prix) {
			demanderPrix();
		} else if (id == R.id.annonces_formulaires_departement) {
			demanderDepartement();
		}
	}

	private void demanderMarqueModele() { 
		ajouterFragment(new MarqueSelector(this, MARQUE_MODELE, true, true));
	}

	@SuppressWarnings("deprecation")
	private void demanderAnnee() {
		int anneeMin = 1900;
		int anneeMax = new Date().getYear();

		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.annee),
				this,
				recherche_annee_min,recherche_annee_max,
				anneeMin,
				anneeMax
				).show();	
	}

	private void demanderBoiteVitesse() {
		ajouterFragment(new ValeurSelector(this, BOITE_VITESSE, Donnees.transmission));
	}

	private void demanderEnergie() {
		List<Energie> energies = Donnees.energies;
		if(energies != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Energie energie : energies){
				donneesValeurs.put(energie.getNom(), energie.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,ENERGIE,donneesValeurs));
		}
	}

	private void demanderDepartement() {
		List<Departement> departements = Donnees.departements;

		if(departements != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Departement departement : departements){
				Log.e("Departement","je passe");
				donneesValeurs.put(departement.getNom(), departement.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,DEPARTEMENT,donneesValeurs));
		}
	}

	@SuppressWarnings("unused")
	private void ajouterAlerte() {
		if(this.recherche_carrosserie_id == null)
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_choisir_une_carrosserie), Toast.LENGTH_SHORT).show();
		else{
			task = new AjouterAlerteTask();
			task.execute();
		}

	}

	protected void demanderCarrosserie(){
		List<Categorie> categories = Donnees.categories;
		if(categories != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Categorie categorie : categories){
				donneesValeurs.put(categorie.getNom(), categorie.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,CARROSSERIE,donneesValeurs));
		}
	}

	protected void demanderPrix(){

		int prix = 300000;

		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.prix),
				this,
				recherche_prix_min,recherche_prix_max,
				prix
				).show();
	}

	public void afficherAnnoncesListe(List<NameValuePair> donneesFormulaire){
		ajouterFragment(new AnnoncesListe(donneesFormulaire));
	}

	public void reset(){
		for(View v : _views){
			v.setOnClickListener(null);
			v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof EditText)
				((EditText)o).setText("");
			else if(o instanceof TextView)
				((TextView)o).setText("facultatif");
			
		}

		recherche_carrosserie_id = null;

		recherche_energie_id = null;

		recherche_modele_id = null;
		recherche_marque_id = null;

		recherche_departement_id = null;
		recherche_etat_id = null;

		recherche_prix_min = null;
		recherche_prix_max = null;

		recherche_annee_min = null;
		recherche_annee_max = null;

		recherche_longueur_min = null;
		recherche_longueur_max = null;

		recherche_puissance_min = null;
		recherche_puissance_max = null;

		_nombreAnnonces.setText("");
		donnees.clear();
	}


	@Override
	public void itemSelected(Object from,int idRetour, String item, String value) {	

		Log.e("ItemSelected", item+" | "+value);
		if(idRetour == ENERGIE){
			if(!item.equals("-1"))
			recherche_energie_id = item;
			
			((TextView)_energie.findViewById(R.id.text)).setText(value);
		}else if(idRetour == DEPARTEMENT){
			
			if(!item.equals("-1"))
			recherche_departement_id = item;
			
			((TextView)_departement.findViewById(R.id.text)).setText(value);
		}else if(idRetour == BOITE_VITESSE){
			((TextView)_boite_de_vitesse.findViewById(R.id.text)).setText(value);
		}else if(idRetour == MARQUE_MODELE){
			if(item.equals("-1")){
				recherche_marque_id = null;
				recherche_modele_id = null;
			}else{
				String[] ids = item.split(";");
				recherche_marque_id = ids[0];
				recherche_modele_id = ids[1];
			}
			((TextView)_marqueModele.findViewById(R.id.text)).setText(value);
		}else if(idRetour == CARROSSERIE){

			if(!item.equals("-1"))
			recherche_carrosserie_id = item;
			
			((TextView)_carrosserie.findViewById(R.id.text)).setText(value);
		}
		
		//TODO recupérer les retours des champs
		rechercherNombre();
	}


	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.prix))){
			recherche_prix_min = min;
			recherche_prix_max = max;

			((TextView)_prix.findViewById(R.id.text)).setText("de "+min+" € à "+max+" €");
		}
		else if(titre.equals(getActivity().getResources().getString(R.string.annee))){
			recherche_annee_min = min;
			recherche_annee_max = max;

			((TextView)_annee.findViewById(R.id.text)).setText("de "+min+" à "+max);
		}

		rechercherNombre();

	}

	public void rechercher(){
		afficherAnnoncesListe(recupererDonnees());
		afficherCouleurNormal(_rechercher);
	}

	public void rechercherNombre(){
		task = new RechercherNombreAnnoncesTask();
		task.execute();
	}


	protected List<NameValuePair> recupererDonnees(){
		List<NameValuePair> donnees = Net.construireDonnes();

		if(recherche_carrosserie_id != null)
			Net.add(donnees,Constantes.ANNONCES_CATEGORIE_ID,recherche_carrosserie_id);

		if(recherche_modele_id != null)
			Net.add(donnees,Constantes.ANNONCES_SERIE_ID,recherche_modele_id);

		if(recherche_energie_id != null)
			Net.add(donnees,Constantes.ANNONCES_ENERGIE,recherche_energie_id);

		String boiteVitesse = ((TextView) _boite_de_vitesse.findViewById(R.id.text)).getText().toString();
		if(boiteVitesse.length()>0 && !boiteVitesse.equals("facultatif"))
			Net.add(donnees,Constantes.ANNONCES_TRANSMISSION,boiteVitesse);

		String nbPortes = ((TextView) _nb_portes.findViewById(R.id.text)).getText().toString();
		if(nbPortes.length()>0 && !nbPortes.equals("facultatif"))
			Net.add(donnees,Constantes.ANNONCES_NB_PORTES,nbPortes);

		if(recherche_prix_min != null && recherche_prix_max != null)
			Net.add(donnees,
					Constantes.ANNONCES_PRIX_MIN,recherche_prix_min,
					Constantes.ANNONCES_PRIX_MAX,recherche_prix_max
					);

		if(recherche_annee_min != null && recherche_annee_max != null)
			Net.add(donnees,
					Constantes.ANNONCES_ANNEE_MIN,recherche_annee_min,
					Constantes.ANNONCES_ANNEE_MAX,recherche_annee_max
					);

		String km = ((TextView) _km_max.findViewById(R.id.text)).getText().toString();
		if(km.length() > 0 && !km.equals("facultatif")){
			Net.add(donnees,
					Constantes.ANNONCES_KM,km
					);
		}

		if(recherche_departement_id != null)
			Net.add(donnees,Constantes.ANNONCES_DEPARTEMENT_ID,recherche_departement_id);


		return donnees;
	}

	@Override
	public void effacer() {
		reset();
		remplir();
		ajouterListeners();
		rechercherNombre();
	}


	public void afficherNombreAnnonces(final String nombre){
		getActivity().runOnUiThread(new Runnable(){

			@Override
			public void run() {
				System.err.println(nombre);
				_nombreAnnonces.setText(nombre);
				_nombreAnnonces.invalidate();
				System.err.println(_nombreAnnonces.getText());
			}

		});

	}

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.annonces));
	    Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-46725109-2");	    
	    tracker.set(Fields.SCREEN_NAME, "Annonces formulaire");
	    tracker.send(MapBuilder.createAppView().build());	
	    }

	/* --------------------------------------------------------------------------- */

	class RechercherNombreAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...d) {
			synchronized (donnees) {
				donnees.clear();
				donnees.addAll(recupererDonnees());
				String nombre = NetAnnonce.nombreAnnonces(donnees);
				afficherNombreAnnonces(nombre);
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}

	/* --------------------ALERTE-----------------------*/

	protected List<NameValuePair> recupererDonneesFormulaireAlerte(){
		List<NameValuePair> donnees = Net.construireDonnes();

		JetonManager jm = new JetonManager(this.getActivity());
		@SuppressWarnings("unused")
		String jeton = jm.getJeton();

		return donnees;
	}

	public void alerteAjoutee(){
		Toast.makeText(getActivity(), R.string.alerte_ajoutee, Toast.LENGTH_LONG).show();
		getFragmentManager().popBackStack();
	}

	public void erreurAlerte(){
		Toast.makeText(getActivity(), R.string.erreur_alerte, Toast.LENGTH_LONG).show();
	}

	/* --------------------------------------------------------------------------- */

	class AjouterAlerteTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			final String reponse = NetAlerte.creerAlerte(recupererDonneesFormulaireAlerte());

			getActivity().runOnUiThread(new Runnable(){

				@SuppressLint("DefaultLocale")
				@Override
				public void run() {
					if(!reponse.toLowerCase().trim().equals("false")){
						JetonManager jm = new JetonManager(getActivity());
						jm.setJeton(reponse);
						alerteAjoutee();
					}
					else
						erreurAlerte();
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}
	

}