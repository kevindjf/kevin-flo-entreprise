package fr.RivaMedia.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import fr.RivaMedia.R;

/**
 * Si 
 *   - BATEAU : [Type,Categorie,Prix,Longueur,Chantier/Modele,Etat,Localisation]
 *   - MOTEUR : [{Type=Moteurs},Categorie,Prix,Puissance,Marque,Etat,Localisation]
 *   - ACCESSOIRES : [{Type=Accessoires}, Catégorie, Prix, Localisation]
 */

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends Fragment implements View.OnClickListener{

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
	}

	protected void afficherFormulaireMoteur(){
		reset();

		for(View v : _vuesMoteur)
			v.setVisibility(View.VISIBLE);

		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.moteurs));
	}

	protected void afficherFormulaireAccessoire(){
		reset();

		for(View v : _vuesAccessoire)
			v.setVisibility(View.VISIBLE);

		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.accessoires));
	}


}
