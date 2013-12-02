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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.Gallery;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.model.Lien;
import fr.RivaMedia.net.NetAnnonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.utils.FavorisManager;

@SuppressLint("ValidFragment")
public class AnnonceDetail extends FragmentNormal implements View.OnClickListener{

	View _view;
	Annonce _annonce;
	String _id;
	String _type;
	View screen;

	View titre;
	View sousTitre;
	View prixEntete;
	View type;
	View longeur;
	View largeur;

	View cabines;
	View couchettes;
	View salleDeBain;
	View annee;
	View etat;
	View moteur;
	View puissance;
	View propulsion;
	View nbHeures;
	View prix;
	View description;
	View nomVendeur;
	View adresseVendeur;
	View postaleVendeur;
	View telephonePrincipal;
	View vendeur;
	View email;

	View apartirDe;
	View apartirDeEntete;

	View _favoris;
	FavorisManager _favorisManager;

	PagerAdapter _pagesAdapter;
	ViewPager _page;	
	CirclePageIndicator _indicator;

	LayoutInflater _inflater;

	boolean afficherProgress = true;

	public AnnonceDetail(String id, String type){
		this._id = id;
		this._type = type;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonce_detail,container, false);

		ImageLoaderCache.load(getActivity());
		_favorisManager = new FavorisManager(getActivity());

		task = new ChargerAnnonceTask();
		task.execute();

		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		afficherLogoFavoris();
	}


	public void charger(){
		screen = _view.findViewById(R.id.screen);
		titre = _view.findViewById(R.id.annonce_detail_titre);	
		sousTitre = _view.findViewById(R.id.annonce_detail_sous_titre);
		prixEntete = _view.findViewById(R.id.annonce_detail_prix_entete);
		type =  _view.findViewById(R.id.annonce_detail_type);
		longeur = _view.findViewById(R.id.annonce_detail_longueur);
		largeur =  _view.findViewById(R.id.annonce_detail_largeur);
		cabines =  _view.findViewById(R.id.annonce_detail_cabines);
		couchettes =  _view.findViewById(R.id.annonce_detail_couchettes);
		salleDeBain = _view.findViewById(R.id.annonce_detail_salle_de_bain);
		annee =  _view.findViewById(R.id.annonce_detail_annee);
		etat =  _view.findViewById(R.id.annonce_detail_etat);
		moteur =  _view.findViewById(R.id.annonce_detail_moteur);
		puissance =  _view.findViewById(R.id.annonce_detail_puissance);
		propulsion =  _view.findViewById(R.id.annonce_detail_propulsion);
		nbHeures =  _view.findViewById(R.id.annonce_detail_nombre_heures);
		prix =  _view.findViewById(R.id.annonce_detail_prix);
		description = _view.findViewById(R.id.annonce_detail_description);
		nomVendeur = _view.findViewById(R.id.annonce_detail_nom_vendeur);
		adresseVendeur =  _view.findViewById(R.id.annonce_detail_adresse_vendeur);
		postaleVendeur =  _view.findViewById(R.id.annonce_detail_code_postale_vendeur);
		telephonePrincipal =  _view.findViewById(R.id.annonce_detail_telephone_principal);
		vendeur = _view.findViewById(R.id.annonce_detail_vendeur);
		email = _view.findViewById(R.id.annonce_detail_email);
		apartirDe = _view.findViewById(R.id.annonce_detail_prix_a_partir_de);
		apartirDeEntete = _view.findViewById(R.id.annonce_detail_prix_entete_a_partir_de);

		_page = (ViewPager) getView().findViewById(R.id.annonce_detail_image_pager);

		_indicator = (CirclePageIndicator)getView().findViewById(R.id.annonce_detail_image_pager_indicator);


	}
	public void remplir(){
		if(_annonce != null){

			if(_annonce.getTitle() != null)
				((TextView)titre).setText(_annonce.getTitle());
			else
				titre.setVisibility(View.GONE);

			if(_annonce.getMoteur() != null && _annonce.getMoteur().getInfoMoteur() != null)
				((TextView)sousTitre).setText(_annonce.getMoteur().getInfoMoteur());
			else
				sousTitre.setVisibility(View.GONE);

			if(_annonce.getType() != null && _annonce.getCategorie() != null)
				((TextView)type).setText(_annonce.getCategorie());
			else
				type.setVisibility(View.GONE);

			if(_annonce.getLongueur() != null)
				((TextView)longeur.findViewById(R.id.text)).setText(_annonce.getLongueur());
			else
				longeur.setVisibility(View.GONE);

			if(_annonce.getLargeur() != null)
				((TextView)largeur.findViewById(R.id.text)).setText(_annonce.getLargeur());
			else
				largeur.setVisibility(View.GONE);

			if(_annonce.getNbCabines() != null)
				((TextView)cabines.findViewById(R.id.text)).setText(_annonce.getNbCabines());
			else
				cabines.setVisibility(View.GONE);

			if(_annonce.getNbCouchettes() != null && !_annonce.getNbCouchettes().equals("0"))
				((TextView)couchettes.findViewById(R.id.text)).setText(_annonce.getNbCouchettes());
			else
				couchettes.setVisibility(View.GONE);

			if(_annonce.getNbSallesDeBain() != null)
				((TextView)salleDeBain.findViewById(R.id.text)).setText(_annonce.getNbSallesDeBain());
			else
				salleDeBain.setVisibility(View.GONE);

			if(_annonce.getAnnee() != null)
				((TextView)annee.findViewById(R.id.text)).setText(_annonce.getAnnee());
			else
				annee.setVisibility(View.GONE);

			if(_annonce.getEtat() != null)
				((TextView)etat.findViewById(R.id.text)).setText(_annonce.getEtat());
			else
				etat.setVisibility(View.GONE);

			if(_annonce.getMoteur() != null){ 
				if(_annonce.getMoteur().getMarqueMoteur() != null)
					((TextView)moteur.findViewById(R.id.text)).setText(_annonce.getMoteur().getMarqueMoteur());
				else
					moteur.setVisibility(View.GONE);

				if(_annonce.getMoteur().getPuissanceMoteur() != null)
					((TextView)puissance.findViewById(R.id.text)).setText(_annonce.getMoteur().getPuissanceMoteur());
				else
					puissance.setVisibility(View.GONE);

				if(_annonce.getMoteur().getPropulsion() != null)
					((TextView)propulsion.findViewById(R.id.text)).setText(_annonce.getMoteur().getPropulsion());
				else
					propulsion.setVisibility(View.GONE);

				if(_annonce.getMoteur().getHeureMoteur() != null)
					((TextView)nbHeures.findViewById(R.id.text)).setText(_annonce.getMoteur().getHeureMoteur());
				else
					nbHeures.setVisibility(View.GONE);
			}else{
				moteur.setVisibility(View.GONE);
				puissance.setVisibility(View.GONE);
				propulsion.setVisibility(View.GONE);
				nbHeures.setVisibility(View.GONE);
			}

			if(_annonce.getPrix() != null && _annonce.getTaxePrix() != null){
				String p = String.format("%,8d", Integer.parseInt(_annonce.getPrix())).trim();
				((TextView)prix.findViewById(R.id.text)).setText(p + " € " + _annonce.getTaxePrix());
				((TextView)prixEntete.findViewById(R.id.text)).setText(p + " € " + _annonce.getTaxePrix());

				if(_annonce.getApartirDe() != null && _annonce.getApartirDe().trim().equals("1")){
					apartirDe.setVisibility(View.VISIBLE);
					apartirDeEntete.setVisibility(View.VISIBLE);
				}
			}
			else
				prix.setVisibility(View.GONE);


			if(_annonce.getCommentaire() != null)
				((TextView)description).setText(_annonce.getCommentaire());
			else
				description.setVisibility(View.GONE);

			if(_annonce.getVendeur() != null){
				if(_annonce.getVendeur().getNom() != null)
					((TextView)nomVendeur).setText(_annonce.getVendeur().getNom());
				else
					nomVendeur.setVisibility(View.GONE);

				if(_annonce.getVendeur().getAdresse() != null)
					((TextView)adresseVendeur).setText(_annonce.getVendeur().getAdresse());
				else
					adresseVendeur.setVisibility(View.GONE);

				if(_annonce.getVendeur().getCodePostal() != null && _annonce.getVendeur().getVille() != null)
					((TextView)postaleVendeur).setText(_annonce.getVendeur().getCodePostal() + " " + _annonce.getVendeur().getVille());
				else
					postaleVendeur.setVisibility(View.GONE);

				if(_annonce.getVendeur().getTel1() != null)
					((TextView)telephonePrincipal.findViewById(R.id.text)).setText(_annonce.getVendeur().getTel1());
				else
					telephonePrincipal.setVisibility(View.GONE);

				if(_annonce.getVendeur() != null && _annonce.getVendeur().getNom() != null){
					//((TextView)vendeur.findViewById(R.id.text)).setText(_annonce.getVendeur().getNom());
					if(_annonce.getVendeur().getLogo() != null)
						ImageLoaderCache.charger(_annonce.getVendeur().getLogo(), ((ImageView)vendeur.findViewById(R.id.image)));
				}
				else
					vendeur.setVisibility(View.GONE);

				if(_annonce.getVendeur().getEmail() != null){
					//((TextView)email.findViewById(R.id.text)).setText(_annonce.getVendeur().getEmail());
				}
				else
					email.setVisibility(View.GONE);

			}

			afficherLogoFavoris();
		}
		screen.setVisibility(View.VISIBLE);

		_pagesAdapter = new ImagePagesAdapter();
		_page.setAdapter(_pagesAdapter);
		_indicator.setViewPager(_page);
		//_page.getAdapter().notifyDataSetChanged();

	}
	public void ajouterListeners(){
		telephonePrincipal.setOnClickListener(this);
		vendeur.setOnClickListener(this);
		email.setOnClickListener(this);


		if(_favoris != null)
			_favoris.setOnClickListener(this);
	}

	public void afficherLogoFavoris(){
		if(_annonce != null){
			_favoris = super.afficherFavoris();
			_favoris.setOnClickListener(this);
			if(_annonce.getNumero() != null && !_annonce.getNumero().equals("")){
				_favoris.setSelected(_favorisManager.contient(_annonce.getNumero()));
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonce_detail_telephone_principal:
			super.appeller(((TextView)telephonePrincipal.findViewById(R.id.text)).getText().toString());
			break;
		case R.id.annonce_detail_vendeur:
			afficherVendeur();
			break;
		case R.id.annonce_detail_email:
			super.envoyerEmailAnnonce(((TextView)email.findViewById(R.id.text)).getText().toString(),this._annonce);
			break;
		case R.id.header_favoris:
			switchFavoris();
			break;
		}
	}

	protected void afficherVendeur(){
		if(_annonce != null && _annonce.getIdClient() != null)
			ajouterFragment(new VendeurDetail(_annonce.getIdClient()));
	}

	protected void switchFavoris(){
		if(_annonce.getNumero() != null && !_annonce.getNumero().equals("")){
			if(_favorisManager.contient(_annonce.getNumero())){
				_favorisManager.retirer(_annonce.getNumero(),_type);
				Toast.makeText(getActivity(), R.string.favoris_retiree, Toast.LENGTH_SHORT).show();
			}
			else{
				_favorisManager.ajouter(_annonce.getNumero(),_type);
				Toast.makeText(getActivity(), R.string.favoris_ajoutee, Toast.LENGTH_SHORT).show();
			}
			getActivity().runOnUiThread(new Runnable(){
				public void run(){
					_favoris.setSelected(_favorisManager.contient(_annonce.getNumero()));
				}
			});
		}
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
					System.err.println(_urlImage);
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


	protected void chargerAnnonce(){
		charger();
		remplir();
		ajouterListeners();
	}



	protected void chargerDetailAnnonce(){
		_annonce = NetAnnonce.rechercherAnnonce(_type, Net.construireDonnes(Constantes.ANNONCES_DETAIL_ID_ANNONCE,_id));
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
					afficherProgress = false;
					afficherProgress(afficherProgress);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}


	@Override
	public void onPause() {
		super.onPause();
		if(_favoris != null)
			super.cacherFavoris();
	}

}
