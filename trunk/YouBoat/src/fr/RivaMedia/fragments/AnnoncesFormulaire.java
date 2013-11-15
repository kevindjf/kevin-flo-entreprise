package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import fr.RivaMedia.fragments.core.*;
import fr.RivaMedia.fragments.selector.*;

/**
 * Si 
 *   - BATEAU : [Type,Categorie,Prix,Longueur,Chantier/Modele,Etat,Localisation]
 *   - MOTEUR : [{Type=Moteurs},Categorie,Prix,Puissance,Marque,Etat,Localisation]
 *   - ACCESSOIRES : [{Type=Accessoires}, Categorie, Prix, Localisation]
 */

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends Fragment implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	private int typeAnnonces;

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


	String recherche_type;
	String recherche_categorie;

	String recherche_prix_min = null;
	String recherche_prix_max = null;

	String recherche_longueur_min = null;
	String recherche_longueur_max = null;

	String recherche_marque = null;
	String recherche_etat = null;
	String recherche_localisation = null;

	String recherche_puissance_min = null;
	String recherche_puissance_max = null;

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

	protected void charger(){
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

	protected void remplir(){
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
	protected void ajouterListeners(){
		_rechercher.setOnClickListener(this);

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
			afficherAnnoncesListe();
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
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new TypeSelector(this));
		transaction.addToBackStack(null);
		transaction.commit();
	}
	protected void demanderCategorie(){
		if(_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			if(typeAnnonces == Annonces.BATEAUX){
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
				transaction.add(R.id.main_fragment, new CategorieSelector(recherche_type,this));
				transaction.addToBackStack(null);
				transaction.commit();
			}
			else{
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
				transaction.add(R.id.main_fragment, new CategorieSelector(""+typeAnnonces,this));
				transaction.addToBackStack(null);
				transaction.commit();
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
		if(_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else if(typeAnnonces == Annonces.BATEAUX){
			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new CategorieSelector(recherche_type,this));
			transaction.addToBackStack(null);
			transaction.commit();
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
				recherche_etat = items[which];
				((TextView)_etat.findViewById(R.id.text)).setText(recherche_etat);
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
				recherche_localisation = items[which];
				((TextView)_localisation.findViewById(R.id.text)).setText(recherche_localisation);
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
		if(_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new MarqueSelector(""+typeAnnonces,this));
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}


	public void afficherAnnoncesListe(){
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new AnnoncesListe());
		transaction.addToBackStack(null);
		transaction.commit();
	}

	protected void reset(){
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

		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.moteurs));
		_type.setOnClickListener(null);
		_type.setClickable(false);
	}

	protected void afficherFormulaireAccessoire(){
		reset();

		for(View v : _vuesAccessoire)
			v.setVisibility(View.VISIBLE);

		_type.findViewById(R.id.indicator).setVisibility(View.GONE);

		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.accessoires));
		_type.setOnClickListener(null);
		_type.setClickable(false);
	}


	@Override
	public void itemSelected(Object from, String item, String value) {	

		Log.e("ItemSelected", item+" | "+value);

		if(from instanceof TypeSelector){
			recherche_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);

		}
		else if(from instanceof CategorieSelector){
			recherche_categorie = item;			
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(from instanceof MarqueSelector){
			recherche_marque = item;
			switch(typeAnnonces){
			case Annonces.BATEAUX:
				((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
				break;
			case Annonces.MOTEURS:
				((TextView)_marque.findViewById(R.id.text)).setText(value);
				break;
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


}
