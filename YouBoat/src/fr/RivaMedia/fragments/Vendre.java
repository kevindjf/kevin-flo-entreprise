package fr.RivaMedia.fragments;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.mime.MultipartEntity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.Touch;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.dialog.PictureDialog;
import fr.RivaMedia.fragments.core.Effaceable;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.*;
import fr.RivaMedia.image.ImageResizer;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Energie;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
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
public class Vendre extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, Effaceable, OnFocusChangeListener{

	public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1111;
	public static final int IMAGE_REQUEST = 2222;

	public static final int TYPE = 0;
	public static final int CATEGORIE = 1;
	public static final int CHANTIER_MODELE = 2;
	public static final int MARQUE_MOTEUR = 3;
	public static final int ENERGIE = 4;
	public static final int NOMBRE_MOTEUR = 5;
	public static final int MARQUE_MODELE = 6;
	public static final int UNITE_LONGUEUR = 7;



	View _view;

	private String typeVente;

	private View _boutonBateaux;
	private View _boutonMoteurs;
	private View _boutonDivers;

	View _pub;
	View _scroll;

	//Bateau
	View _type;
	View _categorie;
	View _chantierModele;
	View _annee;
	View _longueur;
	View _longueurUnite;
	View _prix;
	View _nombreCabines;
	View _nombresCouchettes;
	View _nombreSalleDeBain;
	View _motorisation;
	View _nombreMoteur;
	View _puissanceCH;
	View _marqueMoteur;
	View _anneeMoteur;

	View _description;
	View _ajouterPhoto;

	View _etapeSuivante;

	//Moteur
	View _marque;
	View _modele;
	View _energie;
	View _puissance;
	View _nombreHeure;

	//Divers
	View _intitule;

	View[] _views;

	View[] _vuesBateaux;
	View[] _vuesMoteurs;
	View[] _vuesDivers;

	String[] _texteInitial;


	String vendre_type = null;
	String vendre_categorie = null;
	String vendre_marque = null; //aussi chantier/modele
	String vendre_localisation = null;
	String vendre_prix = null;
	String nombre_couchette = null;
	String nombre_cabine = null;
	String nombre_salle_de_bain = null;
	String vendre_nombre_moteur = null;
	String vendre_puissance = null;
	String vendre_marque_moteur_id = null;
	String vendre_annee_moteur = null;
	String vendre_description = null;

	String vendre_energie_id = null;

	String vendre_intitule = null;

	String vendre_annee = null;

	String[] vendre_valeurs;

	View _layoutPhotos;
	PagerAdapter _pagesAdapter;
	ViewPager _page;	
	CirclePageIndicator _indicator;
	List<Bitmap> _photos = new ArrayList<Bitmap>();
	File _photoCamera;

	LayoutInflater _inflater;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.vendre,container, false);
		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		charger();
		ajouterListeners();
		remplir();

		//vendreBateaux();


		return _view;
	}

	public void charger(){
		_boutonBateaux = _view.findViewById(R.id.vendre_bateaux);
		_boutonMoteurs = _view.findViewById(R.id.vendre_moteurs);
		_boutonDivers  = _view.findViewById(R.id.vendre_divers);
		_ajouterPhoto = _view.findViewById(R.id.vendre_ajouter_photo);
		_etapeSuivante = _view.findViewById(R.id.vendre_etape_suivante);

		_pub = _view.findViewById(R.id.vendre_pub);
		_scroll = _view.findViewById(R.id.vendre_scroll);

		_type = _view.findViewById(R.id.vendre_type);
		_categorie = _view.findViewById(R.id.vendre_categorie);
		_chantierModele = _view.findViewById(R.id.vendre_chantier_modele);
		_annee = _view.findViewById(R.id.vendre_annee);
		_longueur = _view.findViewById(R.id.vendre_longueur);
		_longueurUnite = _view.findViewById(R.id.vendre_unite_longueur);
		_prix = _view.findViewById(R.id.vendre_prix);
		_nombreCabines = _view.findViewById(R.id.nombre_cabine);
		_nombresCouchettes = _view.findViewById(R.id.nombre_couchage);
		_nombreSalleDeBain = _view.findViewById(R.id.nombre_salle_de_bain);
		_motorisation = _view.findViewById(R.id.vendre_motorisation);
		_nombreMoteur = _view.findViewById(R.id.vendre_nombre_moteur);
		_puissanceCH = _view.findViewById(R.id.vendre_puissance_ch);
		_marqueMoteur = _view.findViewById(R.id.vendre_marque_moteur);
		_anneeMoteur = _view.findViewById(R.id.vendre_annee_moteur);

		_description = _view.findViewById(R.id.vendre_description);

		//Moteur
		_marque = _view.findViewById(R.id.vendre_marque);
		_modele = _view.findViewById(R.id.vendre_modele);
		_energie = _view.findViewById(R.id.vendre_energie);
		_puissance = _view.findViewById(R.id.vendre_puissance);
		_nombreHeure = _view.findViewById(R.id.nombre_heure);

		//Divers
		_intitule = _view.findViewById(R.id.vendre_intitule);

		_views = new View[]{
				_type,
				_categorie,
				_chantierModele,
				_annee,
				_longueur,
				_longueurUnite,
				_prix,
				_nombreCabines,
				_nombresCouchettes,
				_nombreSalleDeBain,
				_nombreHeure,
				_motorisation,
				_nombreMoteur,
				_puissanceCH,
				_marqueMoteur,
				_anneeMoteur,
				_marque,
				_modele,
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
				_longueurUnite,
				_prix,
				_nombreCabines,
				_nombresCouchettes,
				_nombreSalleDeBain,
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
				_marque,
				_modele,
				_annee,
				_energie,
				_puissance,
				_prix,
				_description,
				_nombreHeure

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
				vendre_marque_moteur_id,
				vendre_annee_moteur,
				vendre_description,
				vendre_energie_id,
				vendre_intitule
		};


		_layoutPhotos = _view.findViewById(R.id.vendre_photos);
		_page = (ViewPager) _view.findViewById(R.id.vendre_photos_pager);
		_indicator = (CirclePageIndicator)_view.findViewById(R.id.vendre_photos_pager_indicator);

		_pagesAdapter = new ImagePagesAdapter();
		_page.setAdapter(_pagesAdapter);
		_indicator.setViewPager(_page);

		if(Donnees.uneSeulePhoto)
			_indicator.setVisibility(View.GONE);
	}

	public void afficherPub(){
		_pub.setVisibility(View.VISIBLE);
		_scroll.setVisibility(View.GONE);

		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(false);
	}
	public void cacherPub(){
		_pub.setVisibility(View.GONE);
		_scroll.setVisibility(View.VISIBLE);
	}

	public void ajouterListeners(){
		_boutonBateaux.setOnClickListener(this);
		_boutonMoteurs.setOnClickListener(this);
		_boutonDivers.setOnClickListener(this);	
		
		_ajouterPhoto.setOnClickListener(this);
		_etapeSuivante.setOnClickListener(this);
		_longueurUnite.setOnClickListener(this);

		_annee.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {


			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String a = ((EditText)v).getText().toString();

					try{
						int an = Integer.parseInt(a);
						Calendar c = Calendar.getInstance(); 
						int annee = c.get(Calendar.YEAR);
						System.out.println(an+" "+annee);
						if(an>=1900 && an<=annee+1)
							((EditText)v.findViewById(R.id.text)).setText(a);
						else{
							((EditText)v.findViewById(R.id.text)).setText("");
							Toast.makeText(getActivity(), R.string.vous_devez_entrer_une_annee_entre_1990_et_maintenant, Toast.LENGTH_SHORT).show();
						}
					}catch(Exception e){
						((EditText)v.findViewById(R.id.text)).setText("");
					}
				}
			}
		});

		_anneeMoteur.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String a = ((EditText)v).getText().toString();

					try{
						int an = Integer.parseInt(a);
						Calendar c = Calendar.getInstance(); 
						int annee = c.get(Calendar.YEAR);
						if(an>=1900 && an<=annee+1)
							((EditText)v.findViewById(R.id.text)).setText(a);
						else{
							((EditText)v.findViewById(R.id.text)).setText("");
							Toast.makeText(getActivity(), R.string.vous_devez_entrer_une_annee_entre_1990_et_maintenant, Toast.LENGTH_SHORT).show();
						}
					}catch(Exception e){
						((EditText)v.findViewById(R.id.text)).setText("");
					}
				}
			}
		});

		_puissance.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus)
					((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText()+" ch");
				else{
					if(((EditText)(v.findViewById(R.id.text))).getText().toString().trim().equals("0")){
						((EditText)(v.findViewById(R.id.text))).setText("");
					}else{
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" ch", ""));
					}
				}
			}
		});

		_prix.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					((EditText)(v.findViewById(R.id.text))).setText(((((EditText)v.findViewById(R.id.text))).getText().toString() +" €"));
				}
				else{
					if(((EditText)(v.findViewById(R.id.text))).getText().toString().trim().equals("0")){
						((EditText)(v.findViewById(R.id.text))).setText("");
					}else{
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" €", ""));
					}
				}
			}
		});
	}

	public void remplir(){
		//_boutonBateaux.setSelected(true);
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
		case R.id.vendre_unite_longueur:
			demanderUniteLongueur();
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

		case R.id.vendre_marque:
			demanderMarqueModele();
			break;
		case R.id.vendre_energie:
			demanderEnergie();
			break;
		}

	}

	private void demanderType() {
		ajouterFragment( new DonneeValeurSelector(this,
				TYPE,
				false,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				));

	}

	private void demanderChantierModele() {
		if(vendre_type != null)
			ajouterFragment( new MarqueSelector(this,CHANTIER_MODELE,false,vendre_type,true));
		else
			Toast.makeText(getActivity(), R.string.veuillez_choisir_un_type, Toast.LENGTH_LONG).show();
	}

	private void demanderUniteLongueur() {
		ajouterFragment( new ValeurSelector(this,UNITE_LONGUEUR,getResources().getStringArray(R.array.unite_longueur)));
	}

	private void demanderCategorie() {
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			String type = ""+typeVente;
			if(typeVente.equals(Constantes.BATEAUX))
				type = vendre_type;

			List<Categorie> categories = Donnees.getCategories(type);
			if(categories != null){
				Map<String,String> donneesValeurs = new HashMap<String,String>();
				for(Categorie categorie : categories){
					donneesValeurs.put(categorie.getLibelle(), categorie.getId());
				}

				ajouterFragment(new DonneeValeurSelector(this,CATEGORIE,false,donneesValeurs));
			}
		}
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
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			if(typeVente.equals(Constantes.BATEAUX))
				ajouterFragment( new MarqueSelector(this,MARQUE_MOTEUR,false,""+typeVente,false));
			else{
				List<Marque> marques = Donnees.getMarques(Constantes.MOTEURS,false);
				Map<String,String> donneesValeurs = new HashMap<String, String>();
				for(Marque m : marques){
					donneesValeurs.put(m.getLibelle(), m.getId());
				}
				ajouterFragment(new DonneeValeurSelector(this, MARQUE_MOTEUR,false, donneesValeurs));
			}

		}
	}


	private void demanderNombreMoteur() {
		ajouterFragment(new ValeurSelector(this, NOMBRE_MOTEUR,  getResources().getStringArray(R.array.nombre)));
	}

	private void demanderMarqueMoteur() {
		List<Marque> marques = Donnees.getMarques(Constantes.MOTEURS,false);
		Map<String,String> donneesValeurs = new HashMap<String, String>();
		for(Marque m : marques){
			donneesValeurs.put(m.getLibelle(), m.getId());
		}
		ajouterFragment(new DonneeValeurSelector(this, MARQUE_MOTEUR, donneesValeurs));
	}

	private void ajouterPhoto() {
		_photoCamera = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		new PictureDialog(this, getString(R.string.choisir_une_photo),_photoCamera).show();
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

		_photos.clear();
		_pagesAdapter.notifyDataSetChanged();
		System.gc();
		_layoutPhotos.setVisibility(View.GONE);
	}

	public void afficherTypeVente(){
		if(typeVente.equals(Constantes.BATEAUX))
			vendreBateaux();
		if(typeVente.equals(Constantes.MOTEURS))
			vendreMoteurs();
		if(typeVente.equals(Constantes.ACCESSOIRES))
			vendreDivers();
	}

	public void vendreBateaux(){
		if(_boutonBateaux.isSelected()){
			afficherPub();
			return;
		}
		cacherPub();

		typeVente = Constantes.BATEAUX;

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
		_longueurUnite.setOnClickListener(this);
	}
	public void vendreMoteurs(){
		if(_boutonMoteurs.isSelected()){
			afficherPub();
			return;
		}
		cacherPub();

		typeVente = Constantes.MOTEURS;
		vendre_type = Constantes.MOTEURS;
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(true);

		reset();

		for(View v : _vuesMoteurs)
			v.setVisibility(View.VISIBLE);

		((TextView)_type.findViewById(R.id.text)).setText(getResources().getString(R.string.moteurs));
		_type.findViewById(R.id.indicator).setVisibility(View.GONE);

		_categorie.setOnClickListener(this);
		_marque.setOnClickListener(this);
		_energie.setOnClickListener(this);
	}
	public void vendreDivers(){
		if(_boutonDivers.isSelected()){
			afficherPub();
			return;
		}
		cacherPub();

		typeVente = Constantes.ACCESSOIRES;
		vendre_type = Constantes.ACCESSOIRES;

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
	public void itemSelected(Object from, int idRetour, String item, String value) {
		Log.e("ItemSelected", item+" | "+value);
		Log.e("Id retour",idRetour+"");
		if(idRetour == TYPE){
			vendre_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			vendre_categorie = null;
			((TextView)_categorie.findViewById(R.id.text)).setText(getString(R.string.requis));
			vendre_marque = null;
			((TextView)_chantierModele.findViewById(R.id.text)).setText(getString(R.string.requis));
		}
		else if(idRetour == CATEGORIE){
			vendre_categorie = item;			
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == ENERGIE){
			vendre_energie_id = item;
			((TextView)_energie.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == UNITE_LONGUEUR){
			((TextView)_longueurUnite.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == NOMBRE_MOTEUR){
			vendre_nombre_moteur = item;
			((TextView)_nombreMoteur.findViewById(R.id.text)).setText(vendre_nombre_moteur);
		}
		else if(idRetour == CHANTIER_MODELE){
			vendre_marque = item;
			((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == MARQUE_MODELE){
			vendre_marque = item;
			((TextView)_marque.findViewById(R.id.text)).setText(value);
		}
		else if(typeVente.equals(Constantes.MOTEURS) && idRetour == MARQUE_MOTEUR){
			vendre_marque = item;
			((TextView)_marque.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == MARQUE_MOTEUR){
			vendre_marque_moteur_id = item;
			((TextView)_marqueMoteur.findViewById(R.id.text)).setText(value);
		}
	}

	private MultipartEntity recupererDonnees(){
		MultipartEntity donnees = Net.construireDonnesMultiPart();

		// Bateaux
		if(typeVente.equals(Constantes.BATEAUX)){

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

			vendre_prix = ((EditText)_prix.findViewById(R.id.text)).getText().toString().replace(" €", "");;
			if(vendre_prix.length()==0){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_prix), Toast.LENGTH_SHORT).show();
				return null;
			}

			//les requis
			Net.add(donnees, 
					Constantes.VENDRE_TYPE,vendre_type,
					Constantes.VENDRE_CATEGORIE,vendre_categorie,
					//Constantes.VENDRE_MARQUE,vendre_marque,
					Constantes.VENDRE_PRIX,vendre_prix
					);

			nombre_couchette = ((EditText)_nombreCabines.findViewById(R.id.text)).getText().toString();
			if(nombre_couchette != null && nombre_couchette.length()>0){
				Net.add(donnees,Constantes.VENDRE_NOMBRE_COUCHETTE,nombre_couchette);
			}

			nombre_cabine = ((EditText)_nombreCabines.findViewById(R.id.text)).getText().toString();
			if(nombre_cabine != null && nombre_cabine.length()>0){
				Net.add(donnees,Constantes.VENDRE_NOMBRE_CABINE,nombre_cabine);
			}

			nombre_salle_de_bain = ((EditText)_nombreSalleDeBain.findViewById(R.id.text)).getText().toString();
			if(nombre_salle_de_bain != null && nombre_salle_de_bain.length()>0){
				Net.add(donnees,Constantes.VENDRE_NOMBRE_SALLE_DE_BAIN,nombre_salle_de_bain);
			}

			if(((EditText)_annee.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_ANNEE,((EditText)_annee.findViewById(R.id.text)).getText());

			String longueur = ((EditText)_longueur.findViewById(R.id.text)).getText().toString().trim();
			if(longueur.length() > 0){
				String avant = longueur;
				String apres = "0";
				if(longueur.contains(".")){
					String [] s = longueur.split(".");
					if(s.length>1){
						avant = s[0];
						apres = s[1];
					}
				}
				else if(longueur.contains(",")){
					String [] s = longueur.split(",");
					if(s.length>1){
						avant = s[0];
						apres = s[1];
					}
				}
				Net.add(donnees,Constantes.VENDRE_LONGUEUR_AVANT_VIRGULE,avant);
				Net.add(donnees,Constantes.VENDRE_LONGUEUR_APRES_VIRGULE,apres);
			}

			if(((TextView)_longueurUnite.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_LONGUEUR_UNITE,((TextView)_longueurUnite.findViewById(R.id.text)).getText());


			if(vendre_nombre_moteur != null)
				Net.add(donnees,Constantes.VENDRE_NOMBRE_MOTEUR,vendre_nombre_moteur);

			if(((TextView)_puissance.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_PUISSANCE_MOTEUR,((TextView)_puissance.findViewById(R.id.text)).getText().toString().replace(" ch",""));

			if(vendre_marque_moteur_id != null)
				Net.add(donnees,Constantes.VENDRE_MARQUE_MOTEUR_ID,vendre_marque_moteur_id);

			if(((EditText)_anneeMoteur.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_ANNEE_MOTEUR,((EditText)_anneeMoteur.findViewById(R.id.text)).getText());

		}else if(typeVente.equals(Constantes.MOTEURS)){

			// MOTEUR
			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_marque== null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_marque), Toast.LENGTH_SHORT).show();
				return null;
			}

			if(((EditText)(_modele.findViewById(R.id.text))).getText().toString().trim().equals("")){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_modele), Toast.LENGTH_SHORT).show();
				return null;
			}

			if(((EditText)(_puissance.findViewById(R.id.text))).getText().toString().trim().length() <= 0){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_la_puissance), Toast.LENGTH_SHORT).show();
				return null;
			}

			vendre_prix = ((TextView)_prix.findViewById(R.id.text)).getText().toString().replace(" €", "");;
			if(vendre_prix.length()==0){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_prix), Toast.LENGTH_SHORT).show();
				return null;
			}


			String vendre_modele = ((EditText)_modele.findViewById(R.id.text)).getText().toString();

			//les requis
			Net.add(donnees, 
					Constantes.VENDRE_TYPE,vendre_type,
					Constantes.VENDRE_CATEGORIE,vendre_categorie,
					Constantes.VENDRE_MARQUE_MOTEUR_ID,vendre_marque+vendre_modele,
					Constantes.VENDRE_PUISSANCE_MOTEUR,((EditText)_puissance.findViewById(R.id.text)).getText().toString().trim(),
					Constantes.VENDRE_PRIX,vendre_prix
					);

			if(((EditText)_annee.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_ANNEE,((EditText)_annee.findViewById(R.id.text)).getText());

			String nombre_heure = ((EditText)_nombreHeure.findViewById(R.id.text)).getText().toString();
			if(nombre_heure.length() > 0){
				Net.add(donnees,Constantes.VENDRE_NOMBRE_HEURE,nombre_heure);
			}

			if(vendre_energie_id!= null)
				Net.add(donnees,Constantes.VENDRE_ENERGIE_ID,vendre_energie_id);

		}else if(typeVente.equals(Constantes.ACCESSOIRES)){

			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
				return null;
			}
			vendre_prix = ((TextView)_prix.findViewById(R.id.text)).getText().toString().replace(" €", "");
			if(vendre_prix.length()==0){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_prix), Toast.LENGTH_SHORT).show();
				return null;
			}

			//les requis
			Net.add(donnees, 
					Constantes.VENDRE_TYPE,vendre_type,
					Constantes.VENDRE_CATEGORIE,vendre_categorie,
					Constantes.VENDRE_PRIX,vendre_prix
					);

			if(((EditText)_intitule.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,Constantes.VENDRE_INTITULE_DIVERS,((EditText)_intitule.findViewById(R.id.text)).getText().toString().trim());


		}

		if(((EditText)_description.findViewById(R.id.text)).getText().length() > 0)
			Net.add(donnees,Constantes.VENDRE_DESCRIPTION,((EditText)_description.findViewById(R.id.text)).getText());

		return donnees;
	}

	private void etapeSuivante() {
		MultipartEntity donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		ajouterFragment(new VendeurFormulaire(VendeurFormulaire.VENDRE,donneesVente,null,_photos));
	}

	@Override
	public void effacer() {
		reset();
		afficherTypeVente();
		ajouterListeners();
	}

	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		super.onPause();
	}

	@Override
	public void onResume() {
		((MainActivity)getActivity()).afficherEffacer(this);
		super.onResume();
	}

	public void getPhotoFromCamera(){
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		_photoCamera = new File(Environment.getExternalStorageDirectory() + "image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoCamera));
		startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
	}

	public void getPhotoFromAlbum(){		
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, IMAGE_REQUEST);
	}



	protected void ajouterPhotoExtras(Bitmap photo){
		if(Donnees.uneSeulePhoto){
			_photos.clear();
			_pagesAdapter = new ImagePagesAdapter();
			_page.setAdapter(_pagesAdapter);
		}else{
			if(_photos.size()>0)
				_page.setCurrentItem(0);
		}
		_photos.add(photo);
		_pagesAdapter.notifyDataSetChanged();
		System.out.println("photo ajoutee");
	}

	@SuppressLint("NewApi")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) { 
		switch(requestCode){
		case CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE:
			if(resultCode == Activity.RESULT_OK) { 

				Bitmap photo = null;				

				BitmapFactory.Options options=new BitmapFactory.Options();
				options.inSampleSize = 6;
				FileInputStream fileInputStream;  
				try {
					Log.e("PHOTO",_photoCamera.getAbsolutePath());
					fileInputStream=new FileInputStream(_photoCamera);
					photo=BitmapFactory.decodeStream(fileInputStream,null,options);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}

				photo = ImageResizer.applyOrientation(photo,_photoCamera);

				if(photo != null)
					ajouterPhotoExtras(photo);
			}
			break;
		case IMAGE_REQUEST:
			if(resultCode == Activity.RESULT_OK){  

				Uri selectedImage = data.getData();

				Log.e("URI", selectedImage.toString());

				String filePath = selectedImage.getLastPathSegment();

				Bitmap b = null;
				try {
					b = android.provider.MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
					ajouterPhotoExtras(b);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(b == null){

					if(selectedImage != null && filePath != null){
						System.err.println(filePath);

						BitmapFactory.Options o = new BitmapFactory.Options();
						o.inJustDecodeBounds = true;

						BitmapFactory.decodeFile(filePath,o);

						//The new size we want to scale to
						final int REQUIRED_WIDTH=700;
						final int REQUIRED_HIGHT=700;
						//Find the correct scale value. It should be the power of 2.
						int scale=1;
						while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
							scale*=2;

						//Decode with inSampleSize
						BitmapFactory.Options o2 = new BitmapFactory.Options();
						o2.inSampleSize=scale;

						Bitmap photo = BitmapFactory.decodeFile(filePath,o2);

						photo = ImageResizer.applyOrientation(photo,new File(filePath));

						System.out.println("photo");
						ajouterPhotoExtras(photo);
					}
					break;
				}
			}
		}
	}

	public void supprimerImage( int position){
		this._photos.remove(position);
		_pagesAdapter.notifyDataSetChanged();
	}

	public void afficherPopupImages(final int position){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Image");
		builder.setMessage("Supprimer l'image");

		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				supprimerImage(position);
			}
		});
		builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public class ImagePagesAdapter extends PagerAdapter {


		public ArrayList<String> getListeUrls(List<Lien> ls){
			ArrayList<String> urls = new ArrayList<String>();
			for(Lien lien : ls){
				urls.add(lien.getUrl());
			}
			return urls;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {

			ImageView _imageView = (ImageView)_inflater.inflate(R.layout.photo, container, false);
			_imageView.setImageBitmap(_photos.get(position));

			_imageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					afficherPopupImages(position);
				}
			});

			((ViewPager) container).addView(_imageView,position);
			return _imageView;
		}

		@Override
		public int getCount() {
			int taille = _photos.size();
			if(taille == 0)
				_layoutPhotos.setVisibility(View.GONE);
			else
				_layoutPhotos.setVisibility(View.VISIBLE);
			return taille;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "";
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==((View)object);
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch(v.getId()){
		case R.id.vendre_prix:

			break;
		case R.id.vendre_annee:

			break;
		case R.id.vendre_puissance:

			break;
		}
	}

}
