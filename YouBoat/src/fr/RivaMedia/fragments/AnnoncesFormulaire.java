package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import fr.RivaMedia.fragments.selector.MarqueSelector;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.fragments.selector.ValeurSelector;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.core.Net;

/**
 * Si 
 *   - BATEAU : [Type,Categorie,Prix,Longueur,Chantier/Modele,Etat,Localisation]
 *   - MOTEUR : [{Type=Moteurs},Categorie,Prix,Puissance,Marque,Etat,Localisation]
 *   - ACCESSOIRES : [{Type=Accessoires}, Categorie, Prix, Localisation]
 */

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	private String typeAnnonces;

	public static int TYPE = 0;
	public static int CATEGORIE = 1;

	public static int CHANTIER_MODELE = 2;
	public static int MARQUE = 3;

	public static int ETAT = 4;
	public static int LOCALISATION = 5;

	View _view;
	View _rechercher;

	View _type;
	View _categorie;
	View _prix;
	View _longueur;
	View _chantierModele;
	View _etat;
	View _localisation;

	//MOTEUR
	View _puissance;
	View _marque;

	View[] _views;
	View[] _vuesBateau;
	View[] _vuesMoteur;
	View[] _vuesAccessoire;


	String recherche_type = null;
	String recherche_categorie_id = null;
	String recherche_marque_id = null;

	String recherche_chantier_id = null;
	String recherche_modele_id = null;

	String recherche_prix_min = null;
	String recherche_prix_max = null;

	String recherche_longueur_min = null;
	String recherche_longueur_max = null;

	String recherche_puissance_min = null;
	String recherche_puissance_max = null;

	List<Marque> _marques = null;

	public AnnoncesFormulaire(String type){
		this.typeAnnonces = type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_rechercher = _view.findViewById(R.id.annonces_formulaire_bouton_rechercher);	

		_type = _view.findViewById(R.id.annonces_formulaire_type);
		_categorie = _view.findViewById(R.id.annonces_formulaire_categorie);
		_prix = _view.findViewById(R.id.annonces_formulaire_prix);
		_longueur = _view.findViewById(R.id.annonces_formulaire_longueur);
		_chantierModele = _view.findViewById(R.id.annonces_formulaire_chantier_modele);
		_etat = _view.findViewById(R.id.annonces_formulaire_etat);
		_localisation = _view.findViewById(R.id.annonces_formulaire_localisation);

		//MOTEUR
		_puissance = _view.findViewById(R.id.annonces_formulaire_puissance);
		_marque = _view.findViewById(R.id.annonces_formulaire_marque);

		_vuesBateau = new View[]{
				_type,
				_categorie,
				_prix,
				_longueur,
				_chantierModele,
				_etat,
				_localisation
		};

		_vuesMoteur = new View[]{
				_type,
				_categorie,
				_prix,
				_puissance,
				_marque,
				_etat,
				_localisation
		};

		_vuesAccessoire = new View[]{
				_type,
				_categorie, 
				_prix, 
				_localisation
		};

		_views = new View[]{
				_type,
				_categorie,
				_prix,
				_longueur,
				_chantierModele,
				_etat,
				_localisation,
				_puissance,
				_marque
		};
	}

	public void remplir(){
		if(typeAnnonces.equals(Constantes.BATEAUX))
			afficherFormulaireBateau();
		else if(typeAnnonces.equals(Constantes.MOTEURS))
			afficherFormulaireMoteur();
		else if(typeAnnonces.equals(Constantes.ACCESSOIRES))
			afficherFormulaireAccessoire();
	}

	public void ajouterListeners(){
		_rechercher.setOnClickListener(this);

		if(typeAnnonces.equals(Constantes.BATEAUX))
			_type.setOnClickListener(this);

		_categorie.setOnClickListener(this);
		_prix.setOnClickListener(this);
		_longueur.setOnClickListener(this);
		_chantierModele.setOnClickListener(this);
		_etat.setOnClickListener(this);
		_localisation.setOnClickListener(this);
		_puissance.setOnClickListener(this);
		_marque.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_formulaire_bouton_rechercher:
			rechercher();
			break;

		case R.id.annonces_formulaire_type:
			demanderType();
			break;
		case R.id.annonces_formulaire_categorie:
			demanderCategorie();
			break;
		case R.id.annonces_formulaire_prix:
			demanderPrix();
			break;
		case R.id.annonces_formulaire_longueur:
			demanderLongueur();
			break;
		case R.id.annonces_formulaire_chantier_modele:
			demanderChantierModele();
			break;
		case R.id.annonces_formulaire_etat:
			demanderEtat();
			break;
		case R.id.annonces_formulaire_localisation:
			demanderLocalisation();
			break;
		case R.id.annonces_formulaire_puissance:
			demanderPuissance();
			break;
		case R.id.annonces_formulaire_marque:
			demanderMarque();
			break;

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
		if(recherche_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			String type = typeAnnonces;
			if(typeAnnonces.equals(Constantes.BATEAUX))
				type = recherche_type;

			List<Categorie> categories = Donnees.getCategories(type);
			if(categories != null){
				Map<String,String> donneesValeurs = new HashMap<String,String>();
				for(Categorie categorie : categories){
					donneesValeurs.put(categorie.getLibelle(), categorie.getId());
				}

				ajouterFragment(new DonneeValeurSelector(this,CATEGORIE,donneesValeurs));
			}
		}
	}

	protected void demanderPrix(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.prix),
				this,
				recherche_prix_min,recherche_prix_max,
				50000
				).show();
	}
	protected void demanderLongueur(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.longueur),
				this,
				recherche_longueur_min,recherche_longueur_max,
				18
				).show();
	}
	protected void demanderChantierModele(){
		if(recherche_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else if(typeAnnonces.equals(Constantes.BATEAUX))
			ajouterFragment(new MarqueSelector(this,CHANTIER_MODELE,recherche_type));
	}
	protected void demanderEtat(){
		ajouterFragment(new ValeurSelector(this, ETAT,  getResources().getStringArray(R.array.etat)));
	}
	protected void demanderLocalisation(){
		ajouterFragment(new ValeurSelector(this, LOCALISATION,  getResources().getStringArray(R.array.localisation)));
	}
	protected void demanderPuissance(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.puissance),
				this,
				recherche_puissance_min,recherche_puissance_max,
				500
				).show();
	}
	protected void demanderMarque(){
		if(recherche_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			if(typeAnnonces.equals(Constantes.MOTEURS)){
				List<Marque> marques = Donnees.getMarques(""+typeAnnonces);
				Map<String,String> donneesValeurs = new HashMap<String, String>();
				for(Marque m : marques){
					donneesValeurs.put(m.getLibelle(), m.getId());
				}
				ajouterFragment(new DonneeValeurSelector(this, MARQUE, donneesValeurs));
			}else{
				ajouterFragment(new MarqueSelector(this,MARQUE,""+typeAnnonces));
			}
		}
	}

	public void afficherAnnoncesListe(String url, List<NameValuePair> donneesFormulaire, String type){
		ajouterFragment(new AnnoncesListe(url,donneesFormulaire,type));
	}

	public void reset(){
		for(View v : _views){
			v.setOnClickListener(null);
			v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText("");
			else if(o instanceof EditText)
				((EditText)o).setText("");
			//spinners, etc
		}

		recherche_type = null;

		recherche_chantier_id = null;
		recherche_modele_id = null;
		recherche_marque_id = null;
	}

	protected void afficherFormulaireBateau(){
		reset();

		for(View v : _vuesBateau)
			v.setVisibility(View.VISIBLE);

		_type.setOnClickListener(this);
	}
	protected void afficherFormulaireMoteur(){
		reset();

		for(View v : _vuesMoteur)
			v.setVisibility(View.VISIBLE);
		_type.findViewById(R.id.indicator).setVisibility(View.GONE);

		recherche_type = getResources().getString(R.string.moteurs);
		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.moteurs));
		_type.setOnClickListener(null);
		_type.setClickable(false);
	}
	protected void afficherFormulaireAccessoire(){
		reset();

		for(View v : _vuesAccessoire)
			v.setVisibility(View.VISIBLE);

		_type.findViewById(R.id.indicator).setVisibility(View.GONE);

		recherche_type = getResources().getString(R.string.accessoires);
		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.accessoires));
		_type.setOnClickListener(null);
		_type.setClickable(false);
	}

	@Override
	public void itemSelected(Object from,int idRetour, String item, String value) {	

		Log.e("ItemSelected", item+" | "+value);

		if(idRetour == TYPE){
			recherche_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			recherche_categorie_id = null;
			((TextView)_categorie.findViewById(R.id.text)).setText("");

			_marques = Donnees.getMarques(item);

		}else if(idRetour == CATEGORIE){
			recherche_categorie_id = item;
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}

		else if(idRetour == CHANTIER_MODELE){
			String[] ids = item.split(";");
			recherche_chantier_id = ids[0];
			recherche_modele_id = ids[1];
			Log.e("CHANTIER",recherche_chantier_id);
			((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == MARQUE){
			recherche_marque_id = item;
			recherche_modele_id = item;

			Log.e("MODELE",recherche_marque_id);

			((TextView)_marque.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == ETAT){
			((TextView)_etat.findViewById(R.id.text)).setText(item);
		}
		else if(idRetour == LOCALISATION){
			((TextView)_localisation.findViewById(R.id.text)).setText(item);
		}
	}


	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.prix))){
			recherche_prix_min = min;
			recherche_prix_max = max;

			((TextView)_prix.findViewById(R.id.text)).setText("de "+min+" € à "+max+" €");
		}
		if(titre.equals(getActivity().getResources().getString(R.string.longueur))){
			recherche_longueur_min = min;
			recherche_longueur_max = max;

			((TextView)_longueur.findViewById(R.id.text)).setText("de "+min+" m à "+max+" m");
		}
		if(titre.equals(getActivity().getResources().getString(R.string.puissance))){
			recherche_puissance_min = min;
			recherche_puissance_max = max;

			((TextView)_puissance.findViewById(R.id.text)).setText("de "+min+" ch à "+max+" ch");
		}

	}

	public void rechercher(){

		if(this.recherche_type == null)
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		else{
			afficherAnnoncesListe(recupererUrl(),recupererDonnees(),recupererType());
		}
	}

	protected String recupererUrl(){
		String url = "";

		if(typeAnnonces.equals(Constantes.BATEAUX)){
			if(recherche_type.equals(Constantes.BATEAU_A_MOTEUR))
				url = Constantes.URL_ANNONCES_BATEAUX_A_MOTEURS;
			else if(recherche_type.equals(Constantes.VOILIER))
				url = Constantes.URL_ANNONCES_BATEAUX_VOILIERS;
			else if(recherche_type.equals(Constantes.PNEU))
				url = Constantes.URL_ANNONCES_BATEAUX_PNEUMATIQUES;
		}
		else if(typeAnnonces.equals(Constantes.MOTEURS)){
			url = Constantes.URL_ANNONCES_MOTEURS;
		}
		else if(typeAnnonces.equals(Constantes.ACCESSOIRES)){
			url = Constantes.URL_ANNONCES_ACCESSOIRES;  
		}

		return url;
	}

	protected String recupererType(){

		if(typeAnnonces.equals(Constantes.BATEAUX))
			return recherche_type;
		else 
			return typeAnnonces;  
	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(this.recherche_categorie_id != null)
			Net.add(donnees, Constantes.ANNONCES_CATEGORIE_ID,recherche_categorie_id);

		String localisation = ((TextView)_localisation.findViewById(R.id.text)).getText().toString();
		if(localisation.length()>0)
			Net.add(donnees, Constantes.ANNONCES_REGION_ID,localisation);

		if(this.recherche_longueur_min != null && this.recherche_longueur_max != null){
			if(!this.recherche_longueur_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_TAILLE,recherche_longueur_max);
			//if(!this.recherche_longueur_min.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_TAILLE,recherche_longueur_min);
		}

		if(this.recherche_puissance_min != null && this.recherche_puissance_max != null){
			if(!this.recherche_puissance_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_PUISS,recherche_puissance_max);
			//if(!this.recherche_puissance_min.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_PUISS,recherche_puissance_min);
		}

		if(this.recherche_prix_min != null && this.recherche_prix_max != null){
			if(!this.recherche_prix_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_PRIX,recherche_prix_max);
			//if(!this.recherche_prix_max.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_PRIX,recherche_prix_min);
		}

		String etat = ((TextView)_etat.findViewById(R.id.text)).getText().toString();
		if(etat != null && etat.length()>0){
			if(etat.equals(getActivity().getResources().getString(R.string.occasion)))
				Net.add(donnees, Constantes.ANNONCES_ETAT,Constantes.ETAT_OCCASION);
			else if(etat.equals(getActivity().getResources().getString(R.string.neuf)))
				Net.add(donnees, Constantes.ANNONCES_ETAT,Constantes.ETAT_NEUF);
			//ne pas ajouter indifferent
		}

		String marque = ((TextView)_etat.findViewById(R.id.text)).getText().toString();
		if(marque != null && etat.length()>0){
			if(recherche_modele_id != null)
				Net.add(donnees,Constantes.ANNONCES_MODELE_ID,recherche_modele_id);
			if(typeAnnonces.equals(Constantes.BATEAUX) && recherche_chantier_id != null)
				Net.add(donnees, Constantes.ANNONCES_MARQUE_ID,recherche_chantier_id);
			else if(typeAnnonces.equals(Constantes.MOTEURS) && recherche_marque_id != null)
				Net.add(donnees, Constantes.ANNONCES_MARQUE_ID,recherche_marque_id);
		}

		return donnees;
	}

	@Override
	public void effacer() {
		reset();
		remplir();
		ajouterListeners();
	}

}