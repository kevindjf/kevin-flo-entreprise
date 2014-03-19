package fr.RivaMedia.AnnoncesBateauGenerique.fragments;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;

import fr.RivaMedia.AnnoncesBateauGenerique.ConstantesClient;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.Gallery;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.view.ActualiteView;

public class Accueil extends FragmentNormal implements View.OnClickListener, OnTouchListener{

	View _view;
	ImageView  _logo;
	TextView _text_entreprise;

	ImageView _fond;

	PagerAdapter _pagesAdapter;
	ViewPager _page;	
	CirclePageIndicator _indicator;
	LayoutInflater _inflater;

	Timer timer = new Timer();
	AccueilImagesMoverTask task;

	
	View _layoutEntrepriseConsulterOffres;
	TextView _textEntrepriseConsulterOffres;
	ViewGroup _layoutEntrepriseOffres;
	
	View _layoutEntrepriseDerniereActualite;
	TextView _textEntrepriseDerniereActualite;
	ViewGroup _layoutEntrepriseDerniereActualiteLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.accueil,container, false);

		ImageLoaderCache.load(getActivity());

		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		charger();	
		remplir();
		ajouterListeners();
		chargerCouleurs();

		task = new AccueilImagesMoverTask();
		timer.scheduleAtFixedRate(task, 3000, 3000);
		return _view;
	}	



	@Override
	public void charger() {
		_logo = (ImageView)_view.findViewById(R.id.logo_entreprise);
		_text_entreprise = (TextView) _view.findViewById(R.id.text_entreprise);

		_page = (ViewPager) _view.findViewById(R.id.image_entreprise_pager);

		_indicator = (CirclePageIndicator)_view.findViewById(R.id.image_entreprise_pager_indicator);
		
		
		_layoutEntrepriseConsulterOffres = _view.findViewById(R.id.layout_entreprise_consulter_offres);
		_textEntrepriseConsulterOffres = (TextView)_view.findViewById(R.id.text_entreprise_consulter_offres);
		_layoutEntrepriseOffres = (ViewGroup)_view.findViewById(R.id.layout_entreprise_offres);
		
		_layoutEntrepriseDerniereActualite = _view.findViewById(R.id.layout_entreprise_derniere_actualite);
		_textEntrepriseDerniereActualite = (TextView)_view.findViewById(R.id.text_entreprise_derniere_actualite);
		_layoutEntrepriseDerniereActualiteLayout = (ViewGroup)_view.findViewById(R.id.layout_entreprise_derniere_actualite_layout);

	}

	@Override
	public void remplir() {

		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(), _logo);
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		_text_entreprise.setText(Donnees.parametres.getTexteIntro());
		
		_textEntrepriseConsulterOffres.setText(getString(R.string.consulter_les_offres_de)+" "+ConstantesClient.APPLICATION_NAME.toUpperCase());
		_textEntrepriseDerniereActualite.setText(getString(R.string.derniere_actualite_de)+" "+ConstantesClient.APPLICATION_NAME.toUpperCase());
		
		chargerSlider();
		
		chargerTypesAnnonces();
		chargerDerniereActualite();
	}

	private void chargerCouleurs(){
		super.afficherTexteCouleurTexte(_text_entreprise);
		
		afficherCouleurTouch(_layoutEntrepriseConsulterOffres);
		super.afficherTexteCouleurTexte(_textEntrepriseConsulterOffres);
		
		afficherCouleurTouch(_layoutEntrepriseDerniereActualite);
		super.afficherTexteCouleurTexte(_textEntrepriseDerniereActualite);
	}
	
	@SuppressWarnings("deprecation")
	private void chargerTypesAnnonces(){
		
		int taille = Donnees.typesAnnonces.size();
		
		for(int i=0;i<taille;++i){
			final TypeAnnonce type = Donnees.typesAnnonces.get(i);
			
			View layoutType = _inflater.inflate(R.layout.accueil_menu, null);
			layoutType.setBackgroundColor(Donnees.parametres.getBackgroundColorUn());
			
			TextView titreType = (TextView)layoutType.findViewById(R.id.accueil_annonces_text);
			titreType.setText(type.getIntitule());
			titreType.setTextColor(Donnees.parametres.getFontColorUn());
			
			TextView nombreAnnonce = (TextView)layoutType.findViewById(R.id.accueil_annonces_nombre_annonces);
			nombreAnnonce.setText(type.getNb());
			
			GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.circle);
			drawable.setColor(Donnees.parametres.getBackgroundColorDeux());
			drawable.setStroke(4 , Color.WHITE);
			drawable.setCornerRadius(270);
			layoutType.findViewById(R.id.accueil_annonces_layout_rond).setBackgroundDrawable(drawable);
			
			_layoutEntrepriseOffres.addView(layoutType);
			
			layoutType.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ajouterFragment(new AnnoncesListe(type));
				}
			});
		}
	}
	
	private void chargerDerniereActualite(){
		if(Donnees.news.size()>0){
			
			Actualite actu = Donnees.news.get(0);
			View view = _inflater.inflate(R.layout.actualite_element_liste, null);

			ActualiteView av = new ActualiteView(actu,getActivity(),view,0);
			_layoutEntrepriseDerniereActualiteLayout.addView(view);
			
		}
		else{
			_layoutEntrepriseDerniereActualite.setVisibility(View.GONE);
			_layoutEntrepriseDerniereActualiteLayout.setVisibility(View.GONE);
		}

	}

	private void chargerSlider(){

		_pagesAdapter = new ImagePagesAdapter();
		_page.setAdapter(_pagesAdapter);
		_indicator.setViewPager(_page);
	}

	public void avancerSlider(){
		try{
			if(_page != null){
				int position = _page.getCurrentItem();
				int newPosition = position + 1;
				if(position == Donnees.parametres.getImagesSlide().size()-1)
					newPosition = 0;

				_page.setCurrentItem(newPosition,true);
			}
		}catch(Exception e){}
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void ajouterListeners() {
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

	public class ImagePagesAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			String _urlImage = Donnees.parametres.getImagesSlide().get(position);

			View	_layout = _inflater.inflate(R.layout.pager_image, container, false);
			ImageView	_imageView = (ImageView)_layout.findViewById(R.id.image);

			_imageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Accueil.this.getActivity(),Gallery.class);
					intent.putStringArrayListExtra(Gallery.IMAGES, (ArrayList<String>)Donnees.parametres.getImagesSlide());
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

			try{
			((ViewPager) container).addView(_layout,position);
			}catch(Exception e){
				e.printStackTrace();
			}
			return _layout;

		}

		@Override
		public int getCount() {
			return Donnees.parametres.getImagesSlide().size();
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

	/* --------------------------------------------------------------------------- */

	class AccueilImagesMoverTask extends TimerTask {

		@Override
		public void run() {
			try{
				getActivity().runOnUiThread(new Runnable(){

					@Override
					public void run() {
						avancerSlider();
					}

				});
			}catch(Exception e){}
		}

	}


	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.accueil));			
	}

}
