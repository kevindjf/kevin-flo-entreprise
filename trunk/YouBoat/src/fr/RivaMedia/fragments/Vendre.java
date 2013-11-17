package fr.RivaMedia.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
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
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.CategorieSelector;
import fr.RivaMedia.fragments.selector.ChantierModeleSelector;
import fr.RivaMedia.fragments.selector.MarqueSelector;
import fr.RivaMedia.fragments.selector.TypeSelector;
import fr.RivaMedia.net.core.Net;

/** 
 * Etoile = requis
 * Si: 
 *  - BATEAUX : 
 *         Generalites=[Type*,Categorie*,"Chantier"/Modele*]
 *         Caracteristiques=[Annee,Longueur,Prix*]
 *         Motorisation=[NombreMoteur,PuisanceCH,MarqueMoteur,AnneeMoteur]
 *         Photo&Description=[Description,AjouterPhoto]
 *  - MOTEURS :
 *  	   Generalites=[{Type="Moteurs"},Categorie*,"Marque"/Modele*]
 *         Caracteristiques=[Annee,Energie*,Puissance,Prix*]
 *         Photo&Description=[Description,AjouterPhoto]
 *  - DIVERS :
 *  	   Generalites=[{Type="Accessoires"},Categorie*]
 *         Caracteristiques=[Intitule,Prix]
 *         Photo&Description=[Description,AjouterPhoto]
 */
public class Vendre extends Fragment implements View.OnClickListener, ItemSelectedListener{

	View _view;

	private int typeVente;

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
	
	String[] _texteInitial;
	
	
	int demanderMarque = 0;
	private static int DEMANDER_MARQUE_MODELE = 1;
	private static int DEMANDER_MARQUE_MOTEUR = 2;
	
	String vendre_type = null;
	String vendre_categorie = null;
	String vendre_marque = null; //aussi chantier/modele
	String vendre_localisation = null;
	String vendre_prix = null;
	String vendre_nombre_moteur = null;
	String vendre_puissance = null;
	String vendre_marque_moteur = null;
	String vendre_annee_moteur = null;
	String vendre_description = null;
	
	String vendre_energie = null;
	
	String vendre_intitule = null;
	
	String vendre_annee = null;
	
	String[] vendre_valeurs;
	
	//TODO: ajouter photos
	List<Bitmap> photos = new ArrayList<Bitmap>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.vendre,container, false);

		charger();
		ajouterListeners();
		remplir();
		
		vendreBateaux();

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
		
		vendre_valeurs = new String[]{
				vendre_type,
				vendre_categorie,
				vendre_marque,
				vendre_annee,
				vendre_localisation,
				vendre_prix,
				vendre_nombre_moteur,
				vendre_puissance,
				vendre_marque_moteur,
				vendre_annee_moteur,
				vendre_description,
				vendre_energie,
				vendre_intitule
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

		case R.id.vendre_ajouter_photo:
			ajouterPhoto();
			break;
		case R.id.vendre_etape_suivante:
			etapeSuivante();
			break;
		case R.id.vendre_type:
			demanderType();
			break;
		case R.id.vendre_categorie:
			demanderCategorie();
			break;
		case R.id.vendre_chantier_modele:
			demanderChantierModele();
			break;
		case R.id.vendre_nombre_moteur:
			demanderNombreMoteur();
			break;
		case R.id.vendre_marque_moteur:
			demanderMarqueMoteur();
			break;

		case R.id.vendre_marque_modele:
			demanderMarqueModele();
			break;
		case R.id.vendre_energie:
			demanderEnergie();
			break;
		}
	}
	
	private void demanderType() {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new TypeSelector(this));
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	private void demanderChantierModele() {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new ChantierModeleSelector(vendre_type,this));
		transaction.addToBackStack(null);
		transaction.commit();
		demanderMarque = DEMANDER_MARQUE_MODELE;
	}

	private void demanderCategorie() {
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			if(typeVente == Annonces.BATEAUX){
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
				transaction.add(R.id.main_fragment, new CategorieSelector(vendre_type,this));
				transaction.addToBackStack(null);
				transaction.commit();
			}
			else{
				FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
				transaction.add(R.id.main_fragment, new CategorieSelector(""+typeVente,this));
				transaction.addToBackStack(null);
				transaction.commit();
			}
		}
	}

	private void demanderEnergie() {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		alertBuilder.setTitle(getActivity().getResources().getString(R.string.energie));

		final String[] items = new String[]{
				getActivity().getResources().getString(R.string.diesel),
				getActivity().getResources().getString(R.string.essence),
				getActivity().getResources().getString(R.string.moins)
		};

		alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				vendre_energie = items[which];
				((TextView)_energie.findViewById(R.id.text)).setText(vendre_energie);
			}
		});

		alertBuilder.create().show();
	}

	private void demanderMarqueModele() {
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
			transaction.add(R.id.main_fragment, new MarqueSelector(""+typeVente,this));
			transaction.addToBackStack(null);
			transaction.commit();
			demanderMarque = DEMANDER_MARQUE_MODELE;
		}
	}


	private void demanderNombreMoteur() {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
		alertBuilder.setTitle(getActivity().getResources().getString(R.string.nombre_moteur));

		final String[] items = new String[]{
				"1",
				"2",
				"3"
		};

		alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				vendre_nombre_moteur = items[which];
				((TextView)_nombreMoteur.findViewById(R.id.text)).setText(vendre_nombre_moteur);
			}
		});

		alertBuilder.create().show();
	}

	private void demanderMarqueMoteur() {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new MarqueSelector(""+Annonces.MOTEURS,this));
		transaction.addToBackStack(null);
		transaction.commit();
		demanderMarque = DEMANDER_MARQUE_MOTEUR;
	}

	private void ajouterPhoto() {
		// TODO Auto-generated method stub
		
	}

	public void reset(){
		int i=0;
		for(View v : _views){
			v.setOnClickListener(null);
			v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(_texteInitial[i]);
			else if(o instanceof EditText)
				((EditText)o).setHint(_texteInitial[i]);
			//spinners, etc
			++i;
		}
		for(int j=0;j<vendre_valeurs.length;++j)
			vendre_valeurs[j] = "";
	}

	public void vendreBateaux(){
		typeVente = Annonces.BATEAUX;
		
		_boutonBateaux.setSelected(true);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(false);

		reset();

		for(View v : _vuesBateaux)
			v.setVisibility(View.VISIBLE);
		
		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.requis));
		_type.findViewById(R.id.indicator).setVisibility(View.VISIBLE);

		_type.setOnClickListener(this);
		_categorie.setOnClickListener(this);
		_chantierModele.setOnClickListener(this);

		_nombreMoteur.setOnClickListener(this);
		_marqueMoteur.setOnClickListener(this);
	}
	public void vendreMoteurs(){
		typeVente = Annonces.MOTEURS;
		vendre_type = ""+Annonces.MOTEURS;
		
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(true);

		reset();

		for(View v : _vuesMoteurs)
			v.setVisibility(View.VISIBLE);
		
		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.type));
		_type.findViewById(R.id.indicator).setVisibility(View.GONE);

		_categorie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_energie.setOnClickListener(this);
	}
	public void vendreDivers(){
		typeVente = Annonces.DIVERS;
		vendre_type = ""+Annonces.DIVERS;
		
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(true);
		_boutonMoteurs.setSelected(false);

		reset();
		
		((TextView)_type.findViewById(R.id.text)).setText(getString(R.string.accessoires));
		_type.findViewById(R.id.indicator).setVisibility(View.GONE);
		


		for(View v : _vuesDivers)
			v.setVisibility(View.VISIBLE);
		
		_categorie.setOnClickListener(this);

	}

	@Override
	public void itemSelected(Object from, String item, String value) {
		Log.e("ItemSelected", item+" | "+value);

		if(from instanceof TypeSelector){
			vendre_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			vendre_categorie = null;
			((TextView)_categorie.findViewById(R.id.text)).setText(getString(R.string.requis));

		}
		else if(from instanceof CategorieSelector){
			vendre_categorie = item;			
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(from instanceof MarqueSelector || from instanceof ChantierModeleSelector){
			if(typeVente == Annonces.BATEAUX){
				if(demanderMarque == DEMANDER_MARQUE_MODELE){
					vendre_marque = item;
					((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
				}else if(demanderMarque == DEMANDER_MARQUE_MOTEUR){
					vendre_marque_moteur = item;
					((TextView)_marqueMoteur.findViewById(R.id.text)).setText(value);
				}
			}
			if(typeVente == Annonces.MOTEURS){
				vendre_marque = item;
				((TextView)_marqueModele.findViewById(R.id.text)).setText(value);
			}
		}
	}

	private List<NameValuePair> recupererDonnees(){
		List<NameValuePair> donnees = Net.construireDonnes();
		
		if(typeVente == Annonces.BATEAUX){
			
			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_marque == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_modele), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_prix == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_prix), Toast.LENGTH_SHORT).show();
				return null;
			}
			
			//les requis
			Net.add(donnees, 
					"",vendre_type,
					"",vendre_categorie,
					"",vendre_marque,
					"",vendre_prix
					);
			
			if(((EditText)_annee).getText().length() > 0)
				Net.add(donnees,"",((EditText)_annee).getText());
			
			if(((EditText)_longueur).getText().length() > 0)
				Net.add(donnees,"",((EditText)_longueur).getText());
			
			if(vendre_nombre_moteur != null)
				Net.add(donnees,"",vendre_nombre_moteur);
			
			if(((EditText)_marqueMoteur).getText().length() > 0)
				Net.add(donnees,"",((EditText)_marqueMoteur).getText());
			
			if(((EditText)_anneeMoteur).getText().length() > 0)
				Net.add(donnees,"",((EditText)_anneeMoteur).getText());
			
		}else if(typeVente == Annonces.MOTEURS){
			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_marque == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_marque), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_energie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type_d_energie), Toast.LENGTH_SHORT).show();
				return null;
			}
			vendre_prix = ((TextView)_prix.findViewById(R.id.text)).getText().toString();
			if(vendre_prix.length()==0){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_prix), Toast.LENGTH_SHORT).show();
				return null;
			}
			
			//les requis
			Net.add(donnees, 
					"",vendre_type,
					"",vendre_categorie,
					"",vendre_marque,
					"",vendre_energie,
					"",vendre_prix
					);
			
			if(((EditText)_annee).getText().length() > 0)
				Net.add(donnees,"",((EditText)_annee).getText());
			
			if(((EditText)_puissance).getText().length() > 0)
				Net.add(donnees,"",((EditText)_puissance).getText());
			
		}else if(typeVente == Annonces.DIVERS){
			
			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
				return null;
			}
			
			//les requis
			Net.add(donnees, 
					"",vendre_type,
					"",vendre_categorie,
					"",vendre_prix
					);
			
			if(((EditText)_intitule).getText().length() > 0)
				Net.add(donnees,"",((EditText)_intitule).getText());
			
			
		}
		
		if(((EditText)_description).getText().length() > 0)
			Net.add(donnees,"",((EditText)_description).getText());
		
		return donnees;
	}
	
	private void etapeSuivante() {
		List<NameValuePair> donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.main_fragment, new VendeurFormulaire(typeVente,donneesVente));
		transaction.addToBackStack(null);
		transaction.commit();
	}


	
}
