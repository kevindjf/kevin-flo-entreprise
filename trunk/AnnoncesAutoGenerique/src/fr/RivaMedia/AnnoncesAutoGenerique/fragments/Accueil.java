package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.Gallery;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

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


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.accueil,container, false);

		ImageLoaderCache.load(getActivity());

		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		charger();	
		remplir();
		ajouterListeners();

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

	}

	@Override
	public void remplir() {

		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(), _logo);
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
		_text_entreprise.setText(Donnees.parametres.getTexteIntro());

		chargerSlider();
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
				if(position == Donnees.parametres.getImageSlider().size()-1)
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

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.accueil));
		trackerEcran("Ecran Accueil Android");
	}

	public class ImagePagesAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			String _urlImage = Donnees.parametres.getImageSlider().get(position);

			View	_layout = _inflater.inflate(R.layout.pager_image, container, false);
			ImageView	_imageView = (ImageView)_layout.findViewById(R.id.image);

			_imageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(Accueil.this.getActivity(),Gallery.class);
					intent.putStringArrayListExtra(Gallery.IMAGES, (ArrayList<String>)Donnees.parametres.getImageSlider());
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

		@Override
		public int getCount() {
			return Donnees.parametres.getImageSlider().size();
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





}
