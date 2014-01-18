package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.Gallery;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesAutoGenerique.utils.FavorisManager;

@SuppressLint({ "ValidFragment", "DefaultLocale" })
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
	View _modele;
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
	
	View[] _views;

	
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
		_modele = _view.findViewById(R.id.annonce_detail_modele);

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
		telephonePrincipal =  _view.findViewById(R.id.annonce_detail_telephone_principal);
		email = _view.findViewById(R.id.annonce_detail_email);
		apartirDe = _view.findViewById(R.id.annonce_detail_prix_a_partir_de);

		_page = getView().findViewById(R.id.annonce_detail_image_pager);
		_image1 = (ImageView)getView().findViewById(R.id.annonce_detail_image_1);
		_image2 = (ImageView)getView().findViewById(R.id.annonce_detail_image_2);
		_image3 = (ImageView)getView().findViewById(R.id.annonce_detail_image_3);

		contact_rond = _view.findViewById(R.id.annonce_detail_rond);
		
		_views = new View[]{
			_categorie,
			_modele,
			_energie,
			_miseCirculation,
			_kilometrage,
			_puissanceFiscal,
			_puissanceDin,
			_emmission,
			_nombrePorte,
			_boiteVitesse,
			_couleurExterieure,
			_couleurInterieure,
			_garantie,
			_departement,
			description};

	}

	@SuppressLint("DefaultLocale")
	public void remplir(){


		if(_annonce != null){



			if(_annonce.getMarque() != null && _annonce.getSerie() != null){
				((TextView)titre).setText(_annonce.getMarque()+" / "+_annonce.getSerie());
			}else{
				titre.setVisibility(View.GONE);
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

			}else{
				sousTitre.setVisibility(View.GONE);
			}

			if(_annonce.getNbPortes() != null){
				((TextView)_nombrePorte.findViewById(R.id.text)).setText(_annonce.getNbPortes());

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

			if(_annonce.getDescriptif() != null){
				String inter = _annonce.getDescriptif().replace("&lt;", "<");
				String interp = inter.replace("&gt;", ">");
				interp = interp.replace("\n", "<br/>");
				interp = inter.replace("\r", "<br/>");
				((TextView)description).setText(Html.fromHtml(interp));
			}
			else
				((TextView)description).setText(getResources().getString(R.string.description_pas_renseigne));

			afficherLogoFavoris();



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
		int id = v.getId();
		if (id == R.id.annonce_detail_telephone_principal) {
			afficherCouleurTouch(v);
			if(Donnees.client.getTel1() != null)
			super.appeller(Donnees.client.getTel1());
		} else if (id == R.id.annonce_detail_email) {
			afficherCouleurTouch(v);
			if(Donnees.client.getEmail() != null)
			super.envoyerEmailAnnonce(Donnees.client.getEmail(),this._annonce);
		} else if (id == R.id.annonce_detail_rond) {
			super.ajouterContactPro();
		} else if (id == R.id.header_favoris) {
			switchFavoris();
		} else if (id == R.id.annonce_detail_image_pager) {
			Intent intent = new Intent(AnnonceDetail.this.getActivity(),Gallery.class);
			if(_annonce.getFinition() != null)
				intent.putExtra(Gallery.TEXTE, _annonce.getFinition());
			intent.putStringArrayListExtra(Gallery.IMAGES, (ArrayList<String>)_annonce.getPhotos());
			getActivity().startActivity(intent);
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

	@SuppressWarnings("deprecation")
	protected void changerCouleur(){
		afficherCouleurTouch(email);
		afficherCouleurTouch(telephonePrincipal);

		selector(email,false);
		selector(telephonePrincipal,false);

		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		
		afficherCouleurNormal(_view.findViewById(R.id.annonce_detail_separator_1),
		_view.findViewById(R.id.annonce_detail_separator_2));
		
		afficherTexteCouleurTitre(
				_view.findViewById(R.id.annonce_detail_titre),
				_view.findViewById(R.id.annonce_detail_sous_titre),
				_view.findViewById(R.id.annonce_detail_separator_1),
				_view.findViewById(R.id.annonce_detail_separator_2)
				);
		
		afficherTexteCouleurTexte(_views);
		afficherTexteCouleurTexte(_categorie.findViewById(R.id.annonce_detail_type));
		
		GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.circle);
		drawable.setColor(Donnees.parametres.getCouleurPrincipale());
		drawable.setStroke(4 , Color.WHITE);
		drawable.setCornerRadius(270);
		contact_rond.setBackgroundDrawable(drawable);
		
		((TextView)prix.findViewById(R.id.text)).setTextColor(Donnees.parametres.getCouleurSecondaire());
		((TextView)prixEntete.findViewById(R.id.text)).setTextColor(Donnees.parametres.getCouleurSecondaire());

	}


	protected void chargerDetailAnnonce(){
		_annonce = NetAnnonce.getAnnonce(_id);
	}


	/* --------------------------------------------------------------------------- */

	class ChargerAnnonceTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			
			try{

			chargerDetailAnnonce();

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonce();
					afficherProgress = false;
					afficherProgress(afficherProgress);
				}

			});
			
			}catch(Exception e){
				e.printStackTrace();
			}

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
