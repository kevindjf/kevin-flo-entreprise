package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import fr.RivaMedia.fragments.selector.ModeleSelector;
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

	private int typeAnnonces;

	public static int TYPE = 0;
	public static int CATEGORIE = 1;

	public static int CHANTIER_MODELE = 2;
	public static int MARQUE = 3;

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

	public AnnoncesFormulaire(int type){
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
		switch(typeAnnonces){
		case Annonces.BATEAUX:
			afficherFormulaireBateau();
			break;
		case Annonces.MOTEURS:
			afficherFormulaireMoteur();
			break;
		case Annonces.DIVERS:
			afficherFormulaireAccessoire();
			break;
		}
	}
	public void ajouterListeners(){
		_rechercher.setOnClickListener(this);

		if(typeAnnonces == Annonces.BATEAUX)
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
			String type = ""+typeAnnonces;
			if(typeAnnonces == Annonces.BATEAUX)
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
		else if(typeAnnonces == Annonces.BATEAUX){
			ajouterFragment(new MarqueSelector(this,CHANTIER_MODELE,recherche_type));
		}
	}
	protected void demanderEtat(){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		alertBuilder.setTitle(getActivity().getResources().getString(R.string.etat));

		final String[] items = new String[]{
				getActivity().getResources().getString(R.string.indifferent),
				getActivity().getResources().getString(R.string.occasion),
				getActivity().getResources().getString(R.string.neuf)
		};

		alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((TextView)_etat.findViewById(R.id.text)).setText(items[which]);
			}
		});

		alertBuilder.create().show();
	}
	protected void demanderLocalisation(){
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		alertBuilder.setTitle(getActivity().getResources().getString(R.string.etat));

		final String[] items = new String[]{
				getActivity().getResources().getString(R.string.indifferent),
				getActivity().getResources().getString(R.string.manche_bretagne),
				getActivity().getResources().getString(R.string.atlantique),
				getActivity().getResources().getString(R.string.paca),
				getActivity().getResources().getString(R.string.languedoc_roussillon),
				getActivity().getResources().getString(R.string.dom_tom),
				getActivity().getResources().getString(R.string.france_autre_region),
		};

		alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				((TextView)_localisation.findViewById(R.id.text)).setText(items[which]);
			}
		});

		alertBuilder.create().show();
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
			ajouterFragment(new MarqueSelector(this,MARQUE,""+typeAnnonces));
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

		if(from instanceof DonneeValeurSelector){
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

		}
		else if(from instanceof ModeleSelector){
			recherche_marque_id = item;
			if(idRetour == CHANTIER_MODELE){
				String[] ids = item.split(";");
				recherche_chantier_id = ids[0];
				recherche_modele_id = ids[1];
				((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
			}
			if(idRetour == MARQUE){
				String[] ids = item.split(";");
				recherche_marque_id = ids[0];
				recherche_modele_id = ids[1];
				((TextView)_marque.findViewById(R.id.text)).setText(value);
			}
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

		if(typeAnnonces == Annonces.BATEAUX){
			if(recherche_type.equals(Constantes.BATEAU_A_MOTEUR))
				url = Constantes.RECHERCHE_BATEAU_ADRESSE;
			else if(recherche_type.equals(Constantes.VOILIER))
				url = Constantes.RECHERCHE_VOILIER_ADRESSE;
			else if(recherche_type.equals(Constantes.PNEU))
				url = Constantes.RECHERCHE_PNEUMA_ADRESSE;
		}
		else if(typeAnnonces == Annonces.MOTEURS){
			url = Constantes.RECHERCHE_MOTEUR_ADRESSE;
		}
		else if(typeAnnonces == Annonces.DIVERS){
			url = Constantes.RECHERCHE_ACCESSOIRE_ADRESSE;  
		}

		return url;
	}

	protected String recupererType(){

		if(typeAnnonces == Annonces.BATEAUX){
			return recherche_type;
		}
		else 
			return ""+typeAnnonces;  
	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		if(this.recherche_categorie_id != null)
			Net.add(donnees, "idcat",recherche_categorie_id);

		String localisation = ((TextView)_localisation.findViewById(R.id.text)).getText().toString();
		if(localisation.length()>0)
			Net.add(donnees, "idregion",localisation);

		if(this.recherche_longueur_min != null && this.recherche_longueur_max != null){
			if(!this.recherche_longueur_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, "maxtaille",recherche_longueur_max);
			//if(!this.recherche_longueur_min.equals("0"))
			Net.add(donnees, "mintaille",recherche_longueur_min);
		}

		if(this.recherche_puissance_min != null && this.recherche_puissance_max != null){
			if(!this.recherche_puissance_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, "maxpuiss",recherche_puissance_max);
			//if(!this.recherche_puissance_min.equals("0"))
			Net.add(donnees, "minpuiss",recherche_puissance_min);
		}

		if(this.recherche_prix_min != null && this.recherche_prix_max != null){
			if(!this.recherche_prix_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, "maxprix",recherche_prix_max);
			//if(!this.recherche_prix_max.equals("0"))
			Net.add(donnees, "minprix",recherche_prix_min);
		}

		String etat = ((TextView)_etat.findViewById(R.id.text)).getText().toString();
		if(etat != null && etat.length()>0){
			if(etat.equals(getActivity().getResources().getString(R.string.occasion)))
				Net.add(donnees, "etat","1");
			else if(etat.equals(getActivity().getResources().getString(R.string.neuf)))
				Net.add(donnees, "etat","2");
			//ne pas ajouter indifferent
		}

		String marque = ((TextView)_etat.findViewById(R.id.text)).getText().toString();
		if(marque != null && etat.length()>0){
			Net.add(donnees,"listModele",recherche_modele_id);
			if(typeAnnonces == Annonces.BATEAUX)
				Net.add(donnees, "listMarque",recherche_chantier_id);
			else if(typeAnnonces == Annonces.MOTEURS)
				Net.add(donnees, "listMarque",recherche_marque_id);
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
