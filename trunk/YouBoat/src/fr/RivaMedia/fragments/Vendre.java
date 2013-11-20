package fr.RivaMedia.fragments;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.fragments.core.Effaceable;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.*;
import fr.RivaMedia.image.ImageResizer;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Lien;
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
public class Vendre extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, Effaceable{

	public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1111;
	public static final int IMAGE_REQUEST = 2222;

	public static final int TYPE = 0;
	public static final int CATEGORIE = 1;
	public static final int CHANTIER_MODELE = 2;
	public static final int MARQUE_MOTEUR = 3;
	public static final int ENERGIE = 4;
	public static final int NOMBRE_MOTEUR = 5;



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


		_layoutPhotos = _view.findViewById(R.id.vendre_photos);
		_page = (ViewPager) _view.findViewById(R.id.vendre_photos_pager);
		_indicator = (CirclePageIndicator)_view.findViewById(R.id.vendre_photos_pager_indicator);

		_pagesAdapter = new ImagePagesAdapter();
		_page.setAdapter(_pagesAdapter);
		_indicator.setViewPager(_page);

		if(Donnees.uneSeulePhoto)
			_indicator.setVisibility(View.GONE);
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
		ajouterFragment( new DonneeValeurSelector(this,
				TYPE,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				));

	}

	private void demanderChantierModele() {
		ajouterFragment( new MarqueSelector(this,CHANTIER_MODELE,vendre_type));
	}

	private void demanderCategorie() {
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			String type = ""+typeVente;
			if(typeVente == Annonces.BATEAUX)
				type = vendre_type;

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

	private void demanderEnergie() {
		ajouterFragment(new ValeurSelector(this, ENERGIE,  getResources().getStringArray(R.array.energie)));
	}

	private void demanderMarqueModele() {
		if(vendre_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			ajouterFragment( new MarqueSelector(this,MARQUE_MOTEUR,""+typeVente));
		}
	}


	private void demanderNombreMoteur() {
		ajouterFragment(new ValeurSelector(this, NOMBRE_MOTEUR,  getResources().getStringArray(R.array.nombre)));
	}

	private void demanderMarqueMoteur() {
		ajouterFragment( new MarqueSelector(this,MARQUE_MOTEUR,""+Annonces.MOTEURS));
	}

	private void ajouterPhoto() {
		afficherPopupSelectrionPhoto();
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
		if(typeVente == Annonces.BATEAUX)
			vendreBateaux();
		else if(typeVente == Annonces.MOTEURS)
			vendreMoteurs();
		else if(typeVente == Annonces.DIVERS)
			vendreDivers();
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
	public void itemSelected(Object from, int idRetour, String item, String value) {
		Log.e("ItemSelected", item+" | "+value);

		if(idRetour == TYPE){
			vendre_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			vendre_categorie = null;
			((TextView)_categorie.findViewById(R.id.text)).setText(getString(R.string.requis));
		}

		else if(idRetour == CATEGORIE){
			vendre_categorie = item;			
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == ENERGIE){
			vendre_energie = item;
			((TextView)_energie.findViewById(R.id.text)).setText(vendre_energie);
		}
		else if(idRetour == NOMBRE_MOTEUR){
			vendre_nombre_moteur = item;
			((TextView)_nombreMoteur.findViewById(R.id.text)).setText(vendre_nombre_moteur);
		}
		else if(idRetour == CHANTIER_MODELE){
			vendre_marque = item;
			((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == MARQUE_MOTEUR){
			vendre_marque_moteur = item;
			((TextView)_marqueMoteur.findViewById(R.id.text)).setText(value);
		}
		else if(typeVente == Annonces.MOTEURS){
			vendre_marque = item;
			((TextView)_marqueModele.findViewById(R.id.text)).setText(value);
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
					"",vendre_prix
					);

			if(((EditText)_annee.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_annee.findViewById(R.id.text)).getText());

			if(((EditText)_longueur.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_longueur.findViewById(R.id.text)).getText());

			if(vendre_nombre_moteur != null)
				Net.add(donnees,"",vendre_nombre_moteur);

			if(((TextView)_marqueMoteur.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((TextView)_marqueMoteur.findViewById(R.id.text)).getText());

			if(((EditText)_anneeMoteur.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_anneeMoteur.findViewById(R.id.text)).getText());

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

			if(((EditText)_annee.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_annee.findViewById(R.id.text)).getText());

			if(((EditText)_puissance.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_puissance.findViewById(R.id.text)).getText());

		}else if(typeVente == Annonces.DIVERS){

			if(vendre_type == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
				return null;
			}
			if(vendre_categorie == null){
				Toast.makeText(getActivity(), getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
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
					"",vendre_prix
					);

			if(((EditText)_intitule.findViewById(R.id.text)).getText().length() > 0)
				Net.add(donnees,"",((EditText)_intitule.findViewById(R.id.text)).getText());


		}

		if(((EditText)_description.findViewById(R.id.text)).getText().length() > 0)
			Net.add(donnees,"",((EditText)_description.findViewById(R.id.text)).getText());

		return donnees;
	}

	private String recupererUrl(){
		return "";
	}
	
	private void etapeSuivante() {
		List<NameValuePair> donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		ajouterFragment(new VendeurFormulaire(recupererUrl(),donneesVente));
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
		_photoCamera = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(_photoCamera));
		startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
	}

	public void getPhotoFromAlbum(){		
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, IMAGE_REQUEST);
	}

	public void afficherPopupSelectrionPhoto(){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		String[] sources = { "Appareil photo", "Album" };

		builder.setTitle(R.string.choisir_une_photo)
		.setItems(sources, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int position) {
				switch(position){
				case 0:
					getPhotoFromCamera();
					break;
				case 1:
					getPhotoFromAlbum();
				}
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	protected void ajouterPhotoExtras(Bitmap photo){
		if(_photos.size()>0)
			_page.setCurrentItem(0);
		if(Donnees.uneSeulePhoto)
			_photos.clear();
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
}
