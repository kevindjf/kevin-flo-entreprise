package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.Gallery;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.net.NetRecherche;
import fr.RivaMedia.net.core.Net;

@SuppressLint("ValidFragment")
public class AnnonceDetail extends FragmentNormal implements View.OnClickListener{

	View _view;
	Annonce _annonce;
	String _id;
	String _type;
	View screen;

	TextView titre_type;
	TextView subtitre_type;
	TextView type_bateau;
	TextView longeur_text;
	TextView largeur_text;
	TextView cabines;
	TextView couchettes;
	TextView salle_de_bain;
	TextView annee;
	TextView etat_text;
	TextView moteur_text;
	TextView puissance_text;
	TextView propulsion_text;
	TextView nb_heures;
	TextView prix;
	TextView description_text;
	TextView nomVendeur;
	TextView rueVendeur;
	TextView postaleVendeur;
	TextView tel_principal;
	TextView tel_secondaire;
	TextView email;

	/*
	ImageView imageprincipale;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	 */
	View relative_tel_secondaire;
	View relative_email;


	PagerAdapter _pagesAdapter;
	ViewPager _page;	
	CirclePageIndicator _indicator;

	LayoutInflater _inflater;

	public AnnonceDetail(String id, String type){
		this._id = id;
		this._type = type;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonce_detail,container, false);

		ImageLoaderCache.load(getActivity());

		afficherProgress(true);
		task = new ChargerAnnonceTask();
		task.execute();

		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		return _view;
	}



	public void charger(){
		screen = _view.findViewById(R.id.screen);
		relative_tel_secondaire = _view.findViewById(R.id.relative_tel_2);
		relative_email = _view.findViewById(R.id.relative_email);
		titre_type = (TextView) _view.findViewById(R.id.type_title);	
		subtitre_type = (TextView) _view.findViewById(R.id.sous_type_title);
		type_bateau = (TextView) _view.findViewById(R.id.bateau_type);
		longeur_text = (TextView) _view.findViewById(R.id.longueur_text);
		largeur_text = (TextView) _view.findViewById(R.id.largeur_text);
		cabines = (TextView) _view.findViewById(R.id.cabines_text);
		couchettes = (TextView) _view.findViewById(R.id.couchettes_text);
		salle_de_bain = (TextView) _view.findViewById(R.id.salle_de_bain_text);
		annee = (TextView) _view.findViewById(R.id.annee_text);
		etat_text = (TextView) _view.findViewById(R.id.etat_text);
		moteur_text = (TextView) _view.findViewById(R.id.moteur_text);
		puissance_text = (TextView) _view.findViewById(R.id.puissance_text);
		propulsion_text = (TextView) _view.findViewById(R.id.propulsion);
		nb_heures = (TextView) _view.findViewById(R.id.nb_heure_text);
		prix = (TextView) _view.findViewById(R.id.prix);
		description_text = (TextView) _view.findViewById(R.id.description_annonce);
		nomVendeur = (TextView) _view.findViewById(R.id.nom_vendeur);
		rueVendeur = (TextView) _view.findViewById(R.id.rue_vendeur);
		postaleVendeur = (TextView) _view.findViewById(R.id.postale_vendeur);
		tel_principal = (TextView) _view.findViewById(R.id.telephone_principal);
		tel_secondaire = (TextView) _view.findViewById(R.id.telephone_secondaire);
		email = (TextView) _view.findViewById(R.id.email);
		/*
		imageprincipale = (ImageView) _view.findViewById(R.id.principal_picture);
		image1 = (ImageView) _view.findViewById(R.id.other_picture_1);
		image2 = (ImageView) _view.findViewById(R.id.other_picture_2);
		image3 = (ImageView) _view.findViewById(R.id.other_picture_3);
		image4 = (ImageView) _view.findViewById(R.id.other_picture_4);
		 */

		_page = (ViewPager) getView().findViewById(R.id.annonce_detail_image_pager);

		_indicator = (CirclePageIndicator)getView().findViewById(R.id.annonce_detail_image_pager_indicator);


	}
	public void remplir(){
		if(_annonce != null){

			if(_annonce.getTitle() != null)
				titre_type.setText(_annonce.getTitle());
			if(_annonce.getMoteur() != null && _annonce.getMoteur().getInfoMoteur() != null)
				subtitre_type.setText(_annonce.getMoteur().getInfoMoteur());

			if(_annonce.getType() != null && _annonce.getCategorie() != null)
				type_bateau.setText(_annonce.getType() + " > " +_annonce.getCategorie());
			if(_annonce.getLongueur() != null)
				longeur_text.setText(_annonce.getLongueur());
			if(_annonce.getLargeur() != null)
				largeur_text.setText(_annonce.getLargeur());
			if(_annonce.getNbCabines() != null)
				cabines.setText(_annonce.getNbCabines());
			if(_annonce.getNbCouchettes() != null)
				couchettes.setText(_annonce.getNbCouchettes());
			if(_annonce.getNbSallesDeBain() != null)
				salle_de_bain.setText(_annonce.getNbSallesDeBain());
			if(_annonce.getAnnee() != null)
				annee.setText(_annonce.getAnnee());
			if(_annonce.getEtat() != null)
				etat_text.setText(_annonce.getEtat());
			if(_annonce.getMoteur() != null){ 
				if(_annonce.getMoteur().getMarqueMoteur() != null)
					moteur_text.setText(_annonce.getMoteur().getMarqueMoteur());
				if(_annonce.getMoteur().getPuissanceMoteur() != null)
					puissance_text.setText(_annonce.getMoteur().getPuissanceMoteur());
				if(_annonce.getMoteur().getPropulsion() != null)
					propulsion_text.setText(_annonce.getMoteur().getPropulsion());
				if(_annonce.getMoteur().getHeureMoteur() != null)
					nb_heures.setText(_annonce.getMoteur().getHeureMoteur());
			}
			if(_annonce.getPrix() != null && _annonce.getTaxePrix() != null)
				prix.setText(_annonce.getPrix() + " € " + _annonce.getTaxePrix());
			if(_annonce.getCommentaire() != null)
				description_text.setText(_annonce.getCommentaire());

			//if(_annonce.getLien() != null && _annonce.getLien().getUrl()!= null)
			//	ImageLoaderCache.charger(_annonce.getLien().getUrl(), imageprincipale);

			/*
				if(_annonce.getPhotos() != null){
					if(_annonce.getPhotos().size() > 1){
						imageprincipale.setVisibility(View.VISIBLE);
						ImageLoaderCache.charger(_annonce.getPhotos().get(0).getUrl(), imageprincipale);

					}
					if(_annonce.getPhotos().size() > 2){
						image1.setVisibility(View.VISIBLE);
						ImageLoaderCache.charger(_annonce.getPhotos().get(1).getUrl(), image1);

					}
					if(_annonce.getPhotos().size() > 3){
						image2.setVisibility(View.VISIBLE);
						ImageLoaderCache.charger(_annonce.getPhotos().get(2).getUrl(), image2);

					}
					if(_annonce.getPhotos().size() > 4){
						image3.setVisibility(View.VISIBLE);
						ImageLoaderCache.charger(_annonce.getPhotos().get(3).getUrl(), image3);

					}
					if(_annonce.getPhotos().size() > 5){
						image4.setVisibility(View.VISIBLE);
						ImageLoaderCache.charger(_annonce.getPhotos().get(3).getUrl(), image4);

					}

				}
			 */
			if(_annonce.getVendeur() != null){
				if(_annonce.getVendeur().getNom() != null)
					nomVendeur.setText(_annonce.getVendeur().getNom());
				if(_annonce.getVendeur().getAdresse() != null)
					rueVendeur.setText(_annonce.getVendeur().getAdresse());
				if(_annonce.getVendeur().getCodePostal() != null && _annonce.getVendeur().getVille() != null)
					postaleVendeur.setText(_annonce.getVendeur().getCodePostal() + " " + _annonce.getVendeur().getVille());
				if(_annonce.getVendeur().getTel1() != null)
					tel_principal.setText(_annonce.getVendeur().getTel1());
				if(_annonce.getVendeur().getTel2() != null){
					relative_tel_secondaire.setVisibility(View.VISIBLE);
					tel_secondaire.setText(_annonce.getVendeur().getTel2());
				}
				if(_annonce.getVendeur().getEmail() != null){
					relative_email.setVisibility(View.VISIBLE);
					email.setText(_annonce.getVendeur().getEmail());
				}
			}
		}
		screen.setVisibility(View.VISIBLE);

		_pagesAdapter = new ImagePagesAdapter();
		_page.setAdapter(_pagesAdapter);
		_indicator.setViewPager(_page);
		//_page.getAdapter().notifyDataSetChanged();
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
		public Object instantiateItem(ViewGroup container, int position) {

			if(_annonce != null){

				String _urlImage = _annonce.getPhotos().get(position).getUrl();

				View	_layout = _inflater.inflate(R.layout.pager_image, container, false);
				ImageView	_imageView = (ImageView)_layout.findViewById(R.id.image);

				_imageView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(AnnonceDetail.this.getActivity(),Gallery.class);
						if(_annonce.getTitle() != null)
							intent.putExtra(Gallery.TEXTE, _annonce.getTitle());
						intent.putStringArrayListExtra(Gallery.IMAGES, getListeUrls(_annonce.getPhotos()));
						getActivity().startActivity(intent);
					}
				});

				ImageLoaderCache.load(getActivity());
				try{
					ImageLoaderCache.charger(_urlImage, _imageView);
				}catch(Exception e){
					e.printStackTrace();
				}

				((ViewPager) container).addView(_layout,position);
				return _layout;

			}

			return null;
		}

		@Override
		public int getCount() {
			if(_annonce != null){
				if(_annonce.getPhotos() != null)
					return _annonce.getPhotos().size();

			}
			return 0;
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


	public void ajouterListeners(){
	}




	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}


	protected void chargerAnnonce(){
		charger();
		remplir();
		ajouterListeners();
	}

	protected String recupererUrl(){
		String url = null;

		if(_type.equals(Constantes.BATEAU_A_MOTEUR))
			url = "xml-detail-bateau.php?";
		else if(_type.equals(Constantes.VOILIER))
			url = "xml-detail-voilier.php?";
		else if(_type.equals(Constantes.PNEU))
			url = "xml-detail-pneuma.php?";
		else if(_type.equals(Constantes.MOTEURS))
			url = "xml-detail-moteur.php?";
		else if(_type.equals(Constantes.ACCESSOIRES))
			url = "xml-detail-accessoire.php?";

		return url;
	}

	protected void chargerDetailAnnonce(){
		Log.e("AnnonceDetail",recupererUrl());
		_annonce = NetRecherche.rechercherAnnonce(recupererUrl(), Net.construireDonnes("idad",_id));
	}


	/* --------------------------------------------------------------------------- */

	class ChargerAnnonceTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests

			chargerDetailAnnonce();

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonce();
					afficherProgress(false);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
