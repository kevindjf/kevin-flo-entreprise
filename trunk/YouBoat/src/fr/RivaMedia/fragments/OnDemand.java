package fr.RivaMedia.fragments;

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
import android.widget.Toast;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.dialog.MinMaxDialog;
import fr.RivaMedia.dialog.OnMinMaxListener;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.fragments.selector.MarqueSelector;
import fr.RivaMedia.fragments.selector.ValeurSelector;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.core.Net;

@SuppressLint("ValidFragment")
public class OnDemand extends FragmentFormulaire implements ItemSelectedListener , OnMinMaxListener{

	public static final int TYPE = 0;
	public static final int CATEGORIE = 1;
	public static final int CHANTIER_MODELE = 2 ;
	public static final int ETAT = 3 ;
	public static final int LOCALISATION = 4;
	public static final int TYPE_POSSEDER = 5 ;
	public static final int CATEGORIE_POSSEDER = 6;
	public static final int CHANTIER_MODELE_POSSEDER = 7;

	View _view;

	View[] _views;
	String[] _texteInitial;

	View _type;
	View _categorie;
	View _chantierModele;
	View _etat;

	View _taille;
	View _lieu;
	View _budget;
	View _commentaire;

	View _typePosseder;
	View _categoriePosseder;
	View _chantierModelePosseder;
	View _prixCessionPosseder;

	View _boat_on_demand_etape_suivante;


	String demand_type = null ;
	String demand_categorie_id = null;
	String demand_chantier_id = null;
	String demand_modele_id = null;
	String demand_taille_min = null;
	String demand_taille_max = null;

	String demand_type_posseder = null ;
	String demand_categorie_posseder_id = null;
	String demand_chantier_posseder_id = null;
	String demand_modele_posseder_id = null;


	List<Marque> _marques = null;
	List<Marque> _marques_posseder = null;

	String budget_requis;
	String taille_requis;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.boat_on_demand, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_type = _view.findViewById(R.id.boat_on_demand_type);
		_categorie = _view.findViewById(R.id.boat_on_demand_categorie);
		_chantierModele = _view.findViewById(R.id.boat_on_demand_chantier_modele);
		_etat = _view.findViewById(R.id.boat_on_demand_etat);
		_taille = _view.findViewById(R.id.boat_on_demand_taille);
		_lieu = _view.findViewById(R.id.boat_on_demand_lieu);
		_budget = _view.findViewById(R.id.boat_on_demand_budget);
		_commentaire = _view.findViewById(R.id.boat_on_demand_commentaire);
		_typePosseder = _view.findViewById(R.id.boat_on_demand_posseder_type);
		_categoriePosseder = _view.findViewById(R.id.boat_on_demand_posseder_categorie);
		_chantierModelePosseder = _view.findViewById(R.id.boat_on_demand_posseder_chantier_modele);
		_prixCessionPosseder = _view.findViewById(R.id.boat_on_demand_posseder_prix_cession);
		_boat_on_demand_etape_suivante = _view.findViewById(R.id.boat_on_demand_etape_suivante);

		_views = new View[]{
				_type,
				_categorie,
				_chantierModele,
				_etat,
				_taille,
				_lieu,
				_budget,
				_commentaire,
				_typePosseder,
				_categoriePosseder,
				_chantierModelePosseder,
				_prixCessionPosseder
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
		_type.setOnClickListener(this);
		_categorie.setOnClickListener(this);
		_chantierModele.setOnClickListener(this);
		_etat.setOnClickListener(this);
		_taille.setOnClickListener(this);
		_lieu.setOnClickListener(this);
		_typePosseder.setOnClickListener(this);
		_categoriePosseder.setOnClickListener(this);
		_chantierModelePosseder.setOnClickListener(this);
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
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" ���", ""));
					}
				}

			}
		});

		_prixCessionPosseder.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText()+" ���");
				}else{
					if(
							((EditText)(v.findViewById(R.id.text))).getText().toString().trim().equals("0")){
						((EditText)(v.findViewById(R.id.text))).setText("");
					}else{
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" ���", ""));
					}
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.boat_on_demand_type:
			demanderType();
			break;

		case R.id.boat_on_demand_categorie:
			demanderCategorie();
			break;

		case R.id.boat_on_demand_chantier_modele:
			demanderChantierModele();
			break;

		case R.id.boat_on_demand_etat:
			demanderEtat();
			break;

		case R.id.boat_on_demand_taille:
			demanderTaille();
			break;

		case R.id.boat_on_demand_lieu:
			demanderLieu();
			break;

		case R.id.boat_on_demand_posseder_type:
			demanderTypePosseder();
			break;

		case R.id.boat_on_demand_posseder_categorie:
			demanderCategoriePosseder();
			break;

		case R.id.boat_on_demand_posseder_chantier_modele:
			demanderChantierModelePosseder();
			break;

		case R.id.boat_on_demand_etape_suivante:
			demanderEtapeSuivante();
			break;

		}
	}

	private void demanderEtapeSuivante() {
		List<NameValuePair> donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		ajouterFragment(new VendeurFormulaire(recupererUrl(),donneesVente,null));
	}

	private String recupererUrl(){
		return Constantes.URL_ON_DEMAND;
	}

	private List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(demand_type == null){
			Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
			return null;
		}

		if(demand_categorie_id == null){
			Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
			return null;	
		}

		taille_requis = ((TextView)_taille.findViewById(R.id.text)).getText().toString().trim();
		if(taille_requis.length() == 0){
			Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_taille), Toast.LENGTH_SHORT).show();
			return null;
		}

		budget_requis = ((EditText)_budget.findViewById(R.id.text)).getText().toString().trim().replace(" €", "");
		if(budget_requis.length() == 0){
			Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_budget), Toast.LENGTH_SHORT).show();	
			return null;
		}

		//les requis
		Net.add(donnees, 
				Constantes.ON_DEMAND_TYPE,demand_type,
				Constantes.ON_DEMAND_CATEGORIE,demand_categorie_id,
				Constantes.ON_DEMAND_TAILLE,taille_requis,
				Constantes.ON_DEMAND_BUDGET,budget_requis
				);

		if(((TextView)_chantierModele.findViewById(R.id.text)).getText().length() > 0 
				&& demand_modele_id != null && demand_chantier_id != null){
			Net.add(donnees,Constantes.ON_DEMAND_MODELE,demand_modele_id);
			Net.add(donnees,Constantes.ON_DEMAND_MARQUE,demand_chantier_id);
		}

		if(((TextView)_etat.findViewById(R.id.text)).getText().length() > 0){
			String etat = ((TextView)_etat.findViewById(R.id.text)).getText().toString();
			if(etat.equals(getActivity().getResources().getString(R.string.occasion)))
				Net.add(donnees, Constantes.ON_DEMAND_ETAT,Constantes.ETAT_OCCASION);
			else if(etat.equals(getActivity().getResources().getString(R.string.neuf)))
				Net.add(donnees, Constantes.ON_DEMAND_ETAT,Constantes.ETAT_NEUF);
		}

		if(((TextView)_taille.findViewById(R.id.text)).getText().length() > 0
				&& demand_taille_min != null && demand_taille_max != null){
			Net.add(donnees, Constantes.ON_DEMAND_TAILLE_MIN, demand_taille_min);
			Net.add(donnees, Constantes.ON_DEMAND_TAILLE_MAX, demand_taille_max);
		}

		if(((TextView)_lieu.findViewById(R.id.text)).getText().length() > 0)
			Net.add(donnees,Constantes.ON_DEMAND_LIEU,((TextView)_lieu.findViewById(R.id.text)).getText());

		if(((EditText)_commentaire.findViewById(R.id.text)).getText().length() > 0)
			Net.add(donnees,Constantes.ON_DEMAND_COMMENTAIRE,((TextView)_commentaire.findViewById(R.id.text)).getText());

		if(((TextView)_typePosseder.findViewById(R.id.text)).getText().length() > 0 && demand_type_posseder != null)
			Net.add(donnees,Constantes.ON_DEMAND_TYPE_POSSEDE,demand_type_posseder);

		if(((TextView)_categoriePosseder.findViewById(R.id.text)).getText().length() > 0 && demand_categorie_posseder_id != null)
			Net.add(donnees,Constantes.ON_DEMAND_CATEGORIE_POSSEDE,demand_categorie_posseder_id);

		if(((TextView)_chantierModelePosseder.findViewById(R.id.text)).getText().length() > 0 
				&& demand_modele_posseder_id != null && demand_chantier_posseder_id != null){
			Net.add(donnees,Constantes.ON_DEMAND_MODELE_POSSEDE,demand_modele_posseder_id);
			Net.add(donnees,Constantes.ON_DEMAND_MARQUE_POSSEDE,demand_chantier_posseder_id);
		}

		if(((EditText)_prixCessionPosseder.findViewById(R.id.text)).getText().length() > 0)
			Net.add(donnees,Constantes.ON_DEMAND_PRIX_CESSION,((TextView)_prixCessionPosseder.findViewById(R.id.text)).getText().toString().trim().replace(" €", ""));
		return donnees;
	}

	private void demanderChantierModelePosseder() {
		if(demand_type_posseder == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else {
			ajouterFragment(new MarqueSelector(this,CHANTIER_MODELE_POSSEDER,demand_type_posseder));
		}
	}

	private void demanderCategoriePosseder() {
		if(demand_type_posseder == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			List<Categorie> categories = Donnees.getCategories(demand_type_posseder);
			if(categories != null){
				Map<String,String> donneesValeurs = new HashMap<String,String>();
				for(Categorie categorie : categories){
					donneesValeurs.put(categorie.getLibelle(), categorie.getId());
				}

				ajouterFragment(new DonneeValeurSelector(this,CATEGORIE_POSSEDER,donneesValeurs));
			}
		}
	}

	private void demanderTypePosseder() {
		ajouterFragment(new DonneeValeurSelector(
				this,
				TYPE_POSSEDER,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				));
	}

	private void demanderLieu() {
		ajouterFragment(new ValeurSelector(this, LOCALISATION,  getResources().getStringArray(R.array.localisation)));

	}

	private void demanderTaille() {
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.taille),
				this,
				demand_taille_min,demand_taille_max,
				18
				).show();
	}

	private void demanderEtat() {
		ajouterFragment(new ValeurSelector(this, ETAT,  getResources().getStringArray(R.array.etat)));

	}

	private void demanderChantierModele() {
		if(demand_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else {
			ajouterFragment(new MarqueSelector(this,CHANTIER_MODELE,demand_type));
		}
	}

	protected void demanderType(){
		ajouterFragment(new DonneeValeurSelector(
				this,
				TYPE,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				));

	}

	protected void demanderCategorie(){
		if(demand_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			List<Categorie> categories = Donnees.getCategories(demand_type);
			if(categories != null){
				Map<String,String> donneesValeurs = new HashMap<String,String>();
				for(Categorie categorie : categories){
					donneesValeurs.put(categorie.getLibelle(), categorie.getId());
				}

				ajouterFragment(new DonneeValeurSelector(this,CATEGORIE,donneesValeurs));
			}
		}
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item,
			String value) {
		if(idRetour == TYPE){
			demand_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			demand_categorie_id = null;
			((TextView)_categorie.findViewById(R.id.text)).setText(getResources().getString(R.string.requis));
			_marques = Donnees.getMarques(item);

		} else if(idRetour == CATEGORIE){
			demand_categorie_id = item;
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == CHANTIER_MODELE){
			String[] ids = item.split(";");
			demand_chantier_id = ids[0];
			demand_modele_id = ids[1];
			((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
		}else if(idRetour == ETAT){
			((TextView)_etat.findViewById(R.id.text)).setText(item);
		}else if(idRetour == LOCALISATION){
			((TextView)_lieu.findViewById(R.id.text)).setText(item);
		} else if(idRetour == TYPE_POSSEDER){
			demand_type_posseder = item;
			((TextView)_typePosseder.findViewById(R.id.text)).setText(value);
			demand_categorie_posseder_id = null;
			((TextView)_categoriePosseder.findViewById(R.id.text)).setText(getResources().getString(R.string.facultatif));
			_marques_posseder = Donnees.getMarques(item);
		} else if(idRetour == CATEGORIE_POSSEDER){
			demand_categorie_posseder_id = item;
			((TextView)_categoriePosseder.findViewById(R.id.text)).setText(value);
		}else if(idRetour == CHANTIER_MODELE_POSSEDER){
			String[] ids = item.split(";");
			demand_chantier_posseder_id = ids[0];
			demand_modele_posseder_id = ids[1];
			((TextView)_chantierModelePosseder.findViewById(R.id.text)).setText(value);
		}

	}

	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.taille))){
			demand_taille_min = min;
			demand_taille_max = max;
			((TextView)_taille.findViewById(R.id.text)).setText("de "+min+" à "+max+" m");
		}

	}

	@Override
	public void effacer() {
		reset();
		remplir();
	}

	@Override
	public void reset() {
		int i=0;
		for(View v : _views){
			//v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(_texteInitial[i]);
			else if(o instanceof EditText)
				((EditText)o).setHint(_texteInitial[i]);
			//spinners, etc
			++i;
		}

		demand_type = null;
		demand_chantier_id = null;
		demand_modele_id = null;
		demand_type_posseder = null;
		demand_chantier_posseder_id = null;
		demand_modele_posseder_id = null;
	}
}
