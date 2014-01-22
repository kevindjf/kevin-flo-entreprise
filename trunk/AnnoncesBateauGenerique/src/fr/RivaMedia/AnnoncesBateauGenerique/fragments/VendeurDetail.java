package fr.RivaMedia.AnnoncesBateauGenerique.fragments;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetVendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.PagesAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.Tab;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.vendeur.TabVendeurAnnonces;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.vendeur.TabVendeurDescription;

@SuppressLint("ValidFragment")
public class VendeurDetail extends FragmentNormal implements View.OnClickListener, OnPageChangeListener{

	View _view;
	View _layout;
	Vendeur _vendeur;
	String _id;

	ImageView _logo;
	TextView _nom;
	TextView _adresse;
	TextView _codePostalVille;

	View _telephonePrincipal;
	View _telephoneSecondaire;
	View _email;

	PagerAdapter _pagesAdapter;
	PagerTabStrip _pageTitleStrip;
	ViewPager _page;	
	List<Tab> _pages = new ArrayList<Tab>();

	LayoutInflater _inflater;

	boolean afficherProgress = true;

	public VendeurDetail(String id){
		this._id = id;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.vendeur_detail,container, false);

		ImageLoaderCache.load(getActivity());

		afficherProgress(true);
		task = new ChargerVendeurTask();
		task.execute();

		this._inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}

	public void charger(){
		_layout = _view.findViewById(R.id.vendeur_detail_layout);

		_logo = (ImageView)_view.findViewById(R.id.vendeur_detail_logo);
		_nom = (TextView)_view.findViewById(R.id.vendeur_detail_nom);
		_adresse = (TextView)_view.findViewById(R.id.vendeur_detail_adresse);
		_codePostalVille = (TextView)_view.findViewById(R.id.vendeur_detail_code_postal_ville);

		_telephonePrincipal = _view.findViewById(R.id.vendeur_detail_telephone_principal);
		_telephoneSecondaire = _view.findViewById(R.id.vendeur_detail_telephone_secondaire);
		_email = _view.findViewById(R.id.vendeur_detail_email);


		_page = (ViewPager) _view.findViewById(R.id.vendeur_detail_pager);
		_pageTitleStrip = (PagerTabStrip) _view.findViewById(R.id.pager_title_strip);
	}
	public void remplir(){
		if(_vendeur != null){
			if(_vendeur.getLogo() != null)
				ImageLoaderCache.charger(_vendeur.getLogo(), _logo);
			else
				_logo.setVisibility(View.GONE);

			if(_vendeur.getNom() != null)
				_nom.setText(_vendeur.getNom());
			else
				_nom.setVisibility(View.GONE);

			if(_vendeur.getAdresse() != null)
				_adresse.setText(_vendeur.getAdresse());
			else
				_adresse.setVisibility(View.GONE);

			if(_vendeur.getCodePostal() != null && _vendeur.getVille() != null)
				_codePostalVille.setText(_vendeur.getCodePostal()+", "+_vendeur.getVille());
			else
				_codePostalVille.setVisibility(View.GONE);

			if(_vendeur.getTel1() != null)
				((TextView)_telephonePrincipal.findViewById(R.id.text)).setText(_vendeur.getTel1());
			else
				_telephonePrincipal.setVisibility(View.GONE);

			if(_vendeur.getTel2() != null)
				((TextView)_telephoneSecondaire.findViewById(R.id.text)).setText(_vendeur.getTel2());
			else
				_telephoneSecondaire.setVisibility(View.GONE);

			if(_vendeur.getEmail() != null)
				((TextView)_email.findViewById(R.id.text)).setText(_vendeur.getEmail());
			else
				_email.setVisibility(View.GONE);


			chargerTabs();
		}
	}

	public void ajouterListeners(){
		_telephonePrincipal.setOnClickListener(this);
		_telephoneSecondaire.setOnClickListener(this);
		_email.setOnClickListener(this);

		if(_pages.size()>0){
			_page.setOnPageChangeListener(this);
		}
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendeur_detail_telephone_principal:
			appeller(_vendeur.getTel1());
			break;
		case R.id.vendeur_detail_telephone_secondaire:
			appeller(_vendeur.getTel2());
			break;
		case R.id.vendeur_detail_email:
			envoyerEmailVendeur(_vendeur.getEmail(), _vendeur);
			break;
		}
	}

	protected void chargerVendeur(){
		charger();
		remplir();
		ajouterListeners();
		_layout.setVisibility(View.VISIBLE);
	}

	protected void chargerDetailVendeur(){
		_vendeur = NetVendeur.getVendeur(_id);
	}

	protected void chargerTabs(){
		_pages.clear();

		_pages.add(new TabVendeurDescription("Vendeur",_vendeur,getActivity()));

		if(_vendeur.getNbBateau() != null && !_vendeur.getNbBateau().equals("0"))
			_pages.add(new TabVendeurAnnonces(getString(R.string.bateaux),_vendeur,Constantes.BATEAUX,getActivity()));
		if(_vendeur.getNbMoteur() != null && !_vendeur.getNbMoteur().equals("0"))
			_pages.add(new TabVendeurAnnonces(getString(R.string.moteurs),_vendeur,Constantes.MOTEURS,getActivity()));
		if(_vendeur.getNbAccessoire() != null && !_vendeur.getNbAccessoire().equals("0"))
			_pages.add(new TabVendeurAnnonces(getString(R.string.accessoires),_vendeur,Constantes.ACCESSOIRES,getActivity()));

		if(_pages.size()>0){
			_pagesAdapter = new PagesAdapter(getChildFragmentManager(),_pages);
			_page.setAdapter(_pagesAdapter);
		}else
			_page.setVisibility(View.GONE);
	}



	/* --------------------------------------------------------------------------- */

	class ChargerVendeurTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests

			chargerDetailVendeur();

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerVendeur();
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
	public void onPageScrollStateChanged(int p) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if((position-1)>0)
			_pages.get(position-1).onResume();
		if(position+1<this._pages.size())
			_pages.get(position+1).onResume();		
	}


	@Override
	public void onPageSelected(int i) {
	}

}
