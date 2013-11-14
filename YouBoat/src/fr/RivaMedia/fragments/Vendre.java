package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/** 
 * Etoile = requis
 * Si: 
 *  - BATEAUX : 
 *         Generalites=[Type*,Catégorie*,"Chantier"/Modele*]
 *         Caracteristiques=[Annee,Longueur,Prix*]
 *         Motorisation=[NombreMoteur,PuisanceCH,MarqueMoteur,AnneeMoteur]
 *         Photo&Description=[Description,AjouterPhoto]
 *  - MOTEURS :
 *  	   Generalites=[{Type="Moteurs"},Catégorie*,"Marque"/Modele*]
 *         Caracteristiques=[Annee,Energie*,Puissance,Prix*]
 *         Photo&Description=[Description,AjouterPhoto]
 *  - DIVERS :
 *  	   Generalites=[{Type="Accessoires"},Catégorie*]
 *         Caracteristiques=[Intitule,Prix]
 *         Photo&Description=[Description,AjouterPhoto]
 */
public class Vendre extends Fragment implements View.OnClickListener{

	View _view;

	public static final int VENDRE_BATEAUX = 0;
	public static final int VENDRE_MOTEURS = 0;
	public static final int VENDRE_DIVERS = 0;

	private View _boutonBateaux;
	private View _boutonMoteurs;
	private View _boutonDivers;

	//Bateau
	View _type;
	View _categorie;
	View _chantierModele;
	View _annee;
	View _longueur;
	View _prix;
	View _motorisation;
	View _nombreMoteur;
	View _puissanceCH;
	View _marqueMoteur;
	View _anneeMoteur;

	View _description;
	View _ajouterPhoto;

	View _etapeSuivante;

	//Moteur
	View _marqueModele;
	View _energie;
	View _puissance;

	//Divers
	View _intitule;

	View[] _views;

	View[] _vuesBateaux;
	View[] _vuesMoteurs;
	View[] _vuesDivers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.vendre,container, false);

		charger();
		ajouterListeners();
		remplir();

		return _view;
	}

	public void charger(){
		_boutonBateaux = _view.findViewById(R.id.vendre_bateaux);
		_boutonMoteurs = _view.findViewById(R.id.vendre_moteurs);
		_boutonDivers  = _view.findViewById(R.id.vendre_divers);
		_ajouterPhoto = _view.findViewById(R.id.vendre_ajouter_photo);
		_etapeSuivante = _view.findViewById(R.id.vendre_etape_suivante);


		_type = _view.findViewById(R.id.vendre_type);
		_categorie = _view.findViewById(R.id.vendre_categorie);
		_chantierModele = _view.findViewById(R.id.vendre_chantier_modele);
		_annee = _view.findViewById(R.id.vendre_annee);
		_longueur = _view.findViewById(R.id.vendre_longueur);
		_prix = _view.findViewById(R.id.vendre_prix);
		_motorisation = _view.findViewById(R.id.vendre_motorisation);
		_nombreMoteur = _view.findViewById(R.id.vendre_nombre_moteur);
		_puissanceCH = _view.findViewById(R.id.vendre_puissance_ch);
		_marqueMoteur = _view.findViewById(R.id.vendre_marque_moteur);
		_anneeMoteur = _view.findViewById(R.id.vendre_annee_moteur);

		_description = _view.findViewById(R.id.vendre_description);

		//Moteur
		_marqueModele = _view.findViewById(R.id.vendre_marque_modele);
		_energie = _view.findViewById(R.id.vendre_energie);
		_puissance = _view.findViewById(R.id.vendre_puissance);

		//Divers
		_intitule = _view.findViewById(R.id.vendre_intitule);

		_views = new View[]{
				_type,
				_categorie,
				_chantierModele,
				_annee,
				_longueur,
				_prix,
				_motorisation,
				_nombreMoteur,
				_puissanceCH,
				_marqueMoteur,
				_anneeMoteur,
				_description,
				_ajouterPhoto,
				_etapeSuivante,
				_marqueModele,
				_energie,
				_puissance,
				_intitule
		};

		_vuesBateaux = new View[]{
				_type,
				_categorie,
				_chantierModele,
				_annee,
				_longueur,
				_prix,
				_motorisation,
				_nombreMoteur,
				_puissanceCH,
				_marqueMoteur,
				_anneeMoteur,
				_description
		};

		_vuesMoteurs = new View[]{
				_type,
				_categorie,
				_marqueModele,
				_annee,
				_energie,
				_puissance,
				_prix,
				_description	
		};

		_vuesDivers = new View[]{
				_type,
				_categorie,
				_intitule,
				_prix,
				_description
		};
	}

	public void ajouterListeners(){
		_boutonBateaux.setOnClickListener(this);
		_boutonMoteurs.setOnClickListener(this);
		_boutonDivers.setOnClickListener(this);	
		_ajouterPhoto.setOnClickListener(this);
		_etapeSuivante.setOnClickListener(this);
	}

	public void remplir(){
		_boutonBateaux.setSelected(true);
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendre_bateaux:
			vendreBateaux();
			break;
		case R.id.vendre_moteurs:
			vendreMoteurs();
			break;
		case R.id.vendre_divers:
			vendreDivers();
			break;
		}
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
	}

	public void vendreBateaux(){
		_boutonBateaux.setSelected(true);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(false);

		reset();

		for(View v : _vuesBateaux)
			v.setVisibility(View.VISIBLE);

		_type.setOnClickListener(this);
		_categorie.setOnClickListener(this);
		_chantierModele.setOnClickListener(this);
		
		_nombreMoteur.setOnClickListener(this);
		_marqueMoteur.setOnClickListener(this);
	}
	public void vendreMoteurs(){
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(true);

		reset();
		
		for(View v : _vuesMoteurs)
			v.setVisibility(View.VISIBLE);
		
		_categorie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_energie.setOnClickListener(this);
	}
	public void vendreDivers(){
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(true);
		_boutonMoteurs.setSelected(false);

		reset();
		
		for(View v : _vuesDivers)
			v.setVisibility(View.VISIBLE);

	}

}
