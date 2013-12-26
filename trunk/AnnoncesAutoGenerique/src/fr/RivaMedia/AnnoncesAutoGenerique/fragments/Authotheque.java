package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.MinMaxDialog;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.OnMinMaxListener;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentFormulaire;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.MarqueSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.ValeurSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Energie;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

@SuppressLint("ValidFragment")
public class Authotheque extends FragmentFormulaire implements ItemSelectedListener , OnMinMaxListener{

	private static final int CARROSSERIE = 1;
	private static final int MARQUE_MODELE = 2;
	private static final int ENERGIE = 3;
	private static final int BOITE_VITESSE = 4;

	View _view;

	View[] _views;
	String[] _texteInitial;

	View _carrosserie;
	View _marqueModele;
	View _finition;
	View _energie;
	View _annee;
	View _kilometrage;
	View _commentaire;
	View _budget;
	View _couleurExterieur;
	View _couleurInterieur;
	View _boiteDeVitesse;

	View _boat_on_demand_etape_suivante;

	List<Marque> _marques = null;
	List<Marque> _marques_posseder = null;

	String energie_id;
	String marque_id;
	String modele_id;
	String carrosserie_id;
	String budget_requis;
	String taille_requis;
	
	String annee_min;
	String annee_max;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.authotheque, container, false);

		charger();
		remplir();
		ajouterListeners();
		setTitre(getString(R.string.autotheque));
		return _view;
	}

	public void charger(){
		_carrosserie = _view.findViewById(R.id.autotheque_carrosserie);
		_marqueModele = _view.findViewById(R.id.autotheque_marque_modele);
		_finition = _view.findViewById(R.id.autotheque_finition);
		_energie = _view.findViewById(R.id.autotheque_energie);
		_annee = _view.findViewById(R.id.autotheque_annee);
		_kilometrage = _view.findViewById(R.id.autotheque_kilometrage);
		_commentaire = _view.findViewById(R.id.autotheque_commentaire);
		_budget = _view.findViewById(R.id.autotheque_budget);
		_couleurExterieur = _view.findViewById(R.id.autotheque_couleur_exterieur);
		_couleurInterieur = _view.findViewById(R.id.autotheque_couleur_interieur);
		_boiteDeVitesse = _view.findViewById(R.id.autotheque_boite_de_vitesse);
		_boat_on_demand_etape_suivante = _view.findViewById(R.id.boat_on_demand_etape_suivante);

		_views = new View[]{
				_carrosserie,
				_marqueModele,
				_finition,
				_energie,
				_annee,
				_kilometrage,
				_commentaire,
				_budget,
				_couleurExterieur,
				_couleurInterieur,
				_boiteDeVitesse,
		};

		_texteInitial = new String[_views.length];
		for(int i=0;i<_views.length;++i){
			String texte = null;
			Object o = _views[i].findViewById(R.id.text);
			if(o instanceof TextView)
				texte = ((TextView)o).getText().toString();
			else if(o instanceof EditText)
				texte = ((EditText)o).getHint().toString();
			_texteInitial[i] = texte;
		}
	}

	public void remplir(){

	}

	public void ajouterListeners(){
		_carrosserie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_energie.setOnClickListener(this);
		_annee.setOnClickListener(this);
		_couleurExterieur.setOnClickListener(this);
		_couleurInterieur.setOnClickListener(this);
		_boiteDeVitesse.setOnClickListener(this);
		_boat_on_demand_etape_suivante.setOnClickListener(this);
		_budget.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText()+" €");
				}else{
					if(
							((EditText)(v.findViewById(R.id.text))).getText().toString().trim().equals("0")){
						((EditText)(v.findViewById(R.id.text))).setText("");
					}else{
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" €", ""));
					}
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.autotheque_carrosserie:
			demanderCarrosserie();
			break;
		case R.id.autotheque_marque_modele:
			demanderMarqueModele();
			break;
		case R.id.autotheque_energie:
			demanderEnergie();
			break;
		case R.id.autotheque_annee:
			demanderAnneeMinMax();
			break;
		case R.id.autotheque_boite_de_vitesse:
			demanderBoiteDeVitesse();
			break;
		}
	}

	private void demanderBoiteDeVitesse() {
		ajouterFragment(new ValeurSelector(this, BOITE_VITESSE, Donnees.transmission));
	}

	@SuppressWarnings("deprecation")
	private void demanderAnneeMinMax() {
		int anneeMin = 1900;
		int anneeMax = new Date().getYear();

		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.annee),
				this,
				annee_min,annee_max,
				anneeMin,
				anneeMax
				).show();	
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

	private void demanderMarqueModele() {
		ajouterFragment(new MarqueSelector(this, MARQUE_MODELE, false));
	}

	private void demanderCarrosserie() {
		List<Categorie> categories = Donnees.categories;
		if(categories != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Categorie categorie : categories){
				donneesValeurs.put(categorie.getNom(), categorie.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,CARROSSERIE,donneesValeurs));
		}	}

	private void demanderEtapeSuivante() {
		List<NameValuePair> donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		//TODO Envoyer vers le formulaire vendeur
		//ajouterFragment(new VendeurFormulaire(VendeurFormulaire.ON_DEMAND,null,donneesVente,null));
	}

	private List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = new ArrayList<NameValuePair>();
		return donnees;
	}




	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {
		if(idRetour == BOITE_VITESSE)
			((TextView)_boiteDeVitesse.findViewById(R.id.text)).setText(value);
		else if(idRetour == ENERGIE){
			energie_id = item;
			((TextView)_energie.findViewById(R.id.text)).setText(value);
		}else if(idRetour == MARQUE_MODELE){
			if(item.equals("-1")){
				marque_id = null;
				modele_id = null;
			}else{
				String[] ids = item.split(";");
				marque_id = ids[0];
				modele_id = ids[1];
			}
			((TextView)_marqueModele.findViewById(R.id.text)).setText(value);
		}else if(idRetour == CARROSSERIE){
			carrosserie_id = item;
			((TextView)_carrosserie.findViewById(R.id.text)).setText(value);
		}
	}

	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.annee))){
			annee_min = min;
			annee_max = max;

			((TextView)_annee.findViewById(R.id.text)).setText("de "+min+" à "+max);
		}
	}

	@Override
	public void effacer() {
		reset();
		remplir();
	}

	@Override
	public void reset() {

		energie_id = null;
		marque_id = null;
		modele_id = null;
		carrosserie_id = null;

		int i=0;
		for(View v : _views){
			//v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(_texteInitial[i]);
			else if(o instanceof EditText){
				((EditText)o).setText("");
				((EditText)o).setHint(_texteInitial[i]);
			}
			//spinners, etc
			++i;
		}
	}
}
