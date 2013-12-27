package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.Gallery;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.MainActivity;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesAutoGenerique.utils.FavorisManager;

@SuppressLint("ValidFragment")
public class AnnonceDetail extends FragmentNormal implements View.OnClickListener{

	View _view;
	Annonce _annonce;
	String _id;
	View screen;
	View layout_haut;

	View titre;
	View sousTitre;
	View prixEntete;
	View type;

	View prix;	


	View _categorie;
	View _marque;
	View _modele;
	View _finition;
	View _energie;
	View _miseCirculation;
	View _kilometrage;
	View _puissanceFiscal;
	View _puissanceDin;
	View _emmission;
	View _nombrePorte;
	View _boiteVitesse;
	View _couleurExterieure;
	View _couleurInterieure;
	View _garantie;
	View _departement;

	View description;

	View nomVendeur;
	View adresseVendeur;
	View postaleVendeur;
	View telephonePrincipal;
	View email;

	View apartirDe;
	
	View contact_rond;

	View _favoris;
	FavorisManager _favorisManager;

	View _page;
	ImageView _image1;
	ImageView _image2;
	ImageView _image3;

	LayoutInflater _inflater;

	boolean afficherProgress = true;

	public AnnonceDetail(String id){
		this._id = id;
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
		setTitre(getString(R.string.annonce_details));
	}	



	public void charger(){

		screen = _view.findViewById(R.id.screen);
		layout_haut = _view.findViewById(R.id.annonce_detail_layout_haut);
		titre = _view.findViewById(R.id.annonce_detail_titre);	
		sousTitre = _view.findViewById(R.id.annonce_detail_sous_titre);
		prixEntete = _view.findViewById(R.id.annonce_detail_prix_entete);
		type =  _view.findViewById(R.id.annonce_detail_type);
		_categorie = _view.findViewById(R.id.annonce_details_categorie);
		_marque = _view.findViewById(R.id.annonce_detail_marque);
		_modele = _view.findViewById(R.id.annonce_detail_modele);

		_finition = _view.findViewById(R.id.annonce_detail_finition);
		_energie = _view.findViewById(R.id.annonce_detail_energie);
		_miseCirculation = _view.findViewById(R.id.annonce_detail_annee);
		_kilometrage = _view.findViewById(R.id.annonce_detail_kilometrage);
		_puissanceFiscal = _view.findViewById(R.id.annonce_detail_puissance_fiscal);
		_puissanceDin = _view.findViewById(R.id.annonce_detail_puissance_din);
		_emmission = _view.findViewById(R.id.annonce_detail_emission);
		_nombrePorte = _view.findViewById(R.id.annonce_detail_nb_porte);
		_boiteVitesse = _view.findViewById(R.id.annonce_detail_boite_vitesse);
		_couleurExterieure = _view.findViewById(R.id.annonce_detail_couleur_exterieure);
		_couleurInterieure  = _view.findViewById(R.id.annonce_detail_couleur_interieure);
		_garantie = _view.findViewById(R.id.annonce_detail_garantie);
		_departement = _view.findViewById(R.id.annonce_detail_departement);

		prix =  _view.findViewById(R.id.annonce_detail_prix);
		description = _view.findViewById(R.id.annonce_detail_description);
		nomVendeur = _view.findViewById(R.id.annonce_detail_nom_vendeur);
		adresseVendeur =  _view.findViewById(R.id.annonce_detail_adresse_vendeur);
		postaleVendeur =  _view.findViewById(R.id.annonce_detail_code_postale_vendeur);
		telephonePrincipal =  _view.findViewById(R.id.annonce_detail_telephone_principal);
		email = _view.findViewById(R.id.annonce_detail_email);
		apartirDe = _view.findViewById(R.id.annonce_detail_prix_a_partir_de);

		_page = getView().findViewById(R.id.annonce_detail_image_pager);
		_image1 = (ImageView)getView().findViewById(R.id.annonce_detail_image_1);
		_image2 = (ImageView)getView().findViewById(R.id.annonce_detail_image_2);
		_image3 = (ImageView)getView().findViewById(R.id.annonce_detail_image_3);

		contact_rond = _view.findViewById(R.id.annonce_detail_rond);

	}

	public void remplir(){


		if(_annonce != null){



			if(_annonce.getMarque() != null && _annonce.getSerie() != null){
				((TextView)titre).setText(_annonce.getMarque()+" / "+_annonce.getSerie());
				((TextView)_marque.findViewById(R.id.text)).setText(_annonce.getMarque());
			}else{
				titre.setVisibility(View.GONE);
				_marque.setVisibility(View.GONE);
			}

			/*
			if(_annonce.getModele() != null){
				((TextView)_marque.findViewById(R.id.text)).setText(_annonce..getModele());
			}else{
				_modele.setVisibility(View.GONE);
			}

			 */
			_modele.setVisibility(View.GONE);

			if(_annonce.getEnergie() != null){
				((TextView)_energie.findViewById(R.id.text)).setText(_annonce.getEnergie());
			}else{
				_energie.setVisibility(View.GONE);
			}

			if(_annonce.getKm() != null){
				((TextView)_kilometrage.findViewById(R.id.text)).setText(_annonce.getKm());
			}else{
				_kilometrage.setVisibility(View.GONE);
			}

			if(_annonce.getPuissanceFisc() != null){
				((TextView)_puissanceFiscal.findViewById(R.id.text)).setText(_annonce.getPuissanceFisc());
			}else{
				_puissanceFiscal.setVisibility(View.GONE);
			}

			if(_annonce.getPuissanceDin() != null){
				((TextView)_puissanceDin.findViewById(R.id.text)).setText(_annonce.getPuissanceDin());
			}else{
				_puissanceDin.setVisibility(View.GONE);
			}

			if(_annonce.getDepartement() != null){
				((TextView)_departement.findViewById(R.id.text)).setText(_annonce.getDepartement());
			}else{
				_departement.setVisibility(View.GONE);
			}

			if(_annonce.getCo2() != null){
				((TextView)_emmission.findViewById(R.id.text)).setText(_annonce.getCo2());
			}else{
				_emmission.setVisibility(View.GONE);
			}
			if(_annonce.getFinition() != null){
				((TextView)sousTitre).setText(_annonce.getFinition());
				((TextView)_finition.findViewById(R.id.text)).setText(_annonce.getFinition());

			}else{
				sousTitre.setVisibility(View.GONE);
				_finition.setVisibility(View.GONE);
			}

			if(_annonce.getNbPortes() != null){
				((TextView)_nombrePorte.findViewById(R.id.text)).setText(_annonce.getFinition());

			}else{
				_nombrePorte.setVisibility(View.GONE);
			}

			if(_annonce.getTransmission() != null){
				((TextView)_boiteVitesse.findViewById(R.id.text)).setText(_annonce.getTransmission());

			}else{
				_boiteVitesse.setVisibility(View.GONE);
			}

			if(_annonce.getCouleurExt() != null){
				((TextView)_couleurExterieure.findViewById(R.id.text)).setText(_annonce.getCouleurExt());

			}else{
				_couleurExterieure.setVisibility(View.GONE);
			}

			if(_annonce.getCouleurInt() != null){
				((TextView)_couleurInterieure.findViewById(R.id.text)).setText(_annonce.getCouleurInt());

			}else{
				_couleurInterieure.setVisibility(View.GONE);
			}

			if(_annonce.getGarantie() != null && _annonce.getGarantie().length() != 0){
				((TextView)_garantie.findViewById(R.id.text)).setText(_annonce.getGarantie());

			}else{
				_garantie.setVisibility(View.GONE);
			}

			if(_annonce.getAnnee() != null)
				((TextView)_miseCirculation.findViewById(R.id.text)).setText(_annonce.getAnnee());
			else
				_miseCirculation.setVisibility(View.GONE);

			if(_annonce.getPrix() != null){
				String p = String.format("%,8d", Integer.parseInt(_annonce.getPrix())).trim();
				((TextView)prix.findViewById(R.id.text)).setText(p + " € ");
				((TextView)prixEntete.findViewById(R.id.text)).setText(p + " € ");
			}
			else
				prix.setVisibility(View.GONE);

			if(_annonce.getDescriptif() != null)
				((TextView)description).setText(_annonce.getDescriptif());
			else
				((TextView)description).setText(getResources().getString(R.string.description_pas_renseigne));

			afficherLogoFavoris();


			if(_annonce.getClient() != null){
				if(_annonce.getClient().getNom() != null)
					((TextView)nomVendeur).setText(_annonce.getClient().getNom());
				else
					nomVendeur.setVisibility(View.GONE);

				if(_annonce.getClient().getAdresse() != null)
					((TextView)adresseVendeur).setText(_annonce.getClient().getAdresse());
				else
					adresseVendeur.setVisibility(View.GONE);

				if(_annonce.getClient().getDepartement() != null)
					((TextView)postaleVendeur).setText(_annonce.getClient().getDepartementNum());
				else
					postaleVendeur.setVisibility(View.GONE);

				if(_annonce.getClient().getVille() != null)
					((TextView)postaleVendeur).setText(((TextView)postaleVendeur).getText().toString() + " " + _annonce.getClient().getVille());
			}

			if(_annonce.getCategorie() != null){
				((TextView)sousTitre).setText(((TextView)sousTitre).getText() + " - " +_annonce.getCategorie());
				((TextView)type).setText(_annonce.getCategorie());
			}else{
				sousTitre.setVisibility(View.GONE);
				_categorie.setVisibility(View.GONE);
			}
			
			if(_annonce.getPhotos() != null){
				if(_annonce.getPhotos().size()>0){
					_image1.setVisibility(View.VISIBLE);
					ImageLoaderCache.charger(_annonce.getPhotos().get(0), _image1);
				}
				if(_annonce.getPhotos().size()>1){
					_image2.setVisibility(View.VISIBLE);
					ImageLoaderCache.charger(_annonce.getPhotos().get(1), _image2);
				}
				if(_annonce.getPhotos().size()>2){
					_image3.setVisibility(View.VISIBLE);
					ImageLoaderCache.charger(_annonce.getPhotos().get(2), _image3);
				}
			}
		}


		screen.setVisibility(View.VISIBLE);

		System.out.println(_annonce.getPhotos());

	}
	public void ajouterListeners(){
		telephonePrincipal.setOnClickListener(this);
		email.setOnClickListener(this);
		contact_rond.setOnClickListener(this);
		_page.setOnClickListener(this);


		if(_favoris != null)
			_favoris.setOnClickListener(this);
	}

	public void afficherLogoFavoris(){
		if(_annonce != null){
			_favoris = super.afficherFavoris();
			_favoris.setOnClickListener(this);
			if(_annonce.getId() != null && !_annonce.getId().equals("")){
				_favoris.setSelected(_favorisManager.contient(_annonce.getId()));
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonce_detail_telephone_principal:
			afficherCouleurNormal(v);
			super.appeller(((TextView)telephonePrincipal.findViewById(R.id.text)).getText().toString());
			break;
		case R.id.annonce_detail_email:
			afficherCouleurNormal(v);
			super.envoyerEmailAnnonce(((TextView)email.findViewById(R.id.text)).getText().toString(),this._annonce);
			break;
		case R.id.annonce_detail_rond:
			((MainActivity) getActivity()).ajouterContactPro();
			break;
		case R.id.header_favoris:
			switchFavoris();
			break;
		case R.id.annonce_detail_image_pager:
			Intent intent = new Intent(AnnonceDetail.this.getActivity(),Gallery.class);
			if(_annonce.getFinition() != null)
				intent.putExtra(Gallery.TEXTE, _annonce.getFinition());
			intent.putStringArrayListExtra(Gallery.IMAGES, (ArrayList<String>)_annonce.getPhotos());
			getActivity().startActivity(intent);
			break;
		}
	}

	protected void switchFavoris(){
		if(_annonce.getId() != null && !_annonce.getId().equals("")){
			if(_favorisManager.contient(_annonce.getId())){
				_favorisManager.retirer(_annonce.getId());
				Toast.makeText(getActivity(), R.string.favoris_retiree, Toast.LENGTH_SHORT).show();
			}
			else{
				_favorisManager.ajouter(_annonce.getId());
				Toast.makeText(getActivity(), R.string.favoris_ajoutee, Toast.LENGTH_SHORT).show();
			}
			getActivity().runOnUiThread(new Runnable(){
				public void run(){
					_favoris.setSelected(_favorisManager.contient(_annonce.getId()));
				}
			});
		}
	}

	protected void chargerAnnonce(){
		charger();
		changerCouleur();
		remplir();
		ajouterListeners();
	}

	protected void changerCouleur(){
		afficherCouleurNormal(_page);
		afficherCouleurNormal(email);
		afficherCouleurNormal(telephonePrincipal);
		afficherCouleurNormal(layout_haut);

		selector(email);
		selector(telephonePrincipal);

		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		afficherCouleurNormal(_view.findViewById(R.id.annonce_detail_separator_1));
		afficherCouleurNormal(_view.findViewById(R.id.annonce_detail_separator_2));
		afficherCouleurNormal(_view.findViewById(R.id.annonce_detail_separator_3));
		
		GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.circle);
		drawable.setColor(Donnees.parametres.getCouleurSecondaire());
		drawable.setStroke(4 , Color.WHITE);
		drawable.setCornerRadius(270);
		contact_rond.setBackgroundDrawable(drawable);

	}


	protected void chargerDetailAnnonce(){
		_annonce = NetAnnonce.getAnnonce(_id);
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
