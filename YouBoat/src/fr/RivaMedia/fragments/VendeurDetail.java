package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.net.NetVendeur;
import fr.RivaMedia.tab.TabVendeurDescription;
import fr.RivaMedia.tab.core.PagesAdapter;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class VendeurDetail extends FragmentNormal implements View.OnClickListener{

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
				((TextView)_email.findViewById(R.id.text)).setText(_vendeur.getTel1());
			else
				_email.setVisibility(View.GONE);
			
			
			chargerTabs();
		}
	}

	public void ajouterListeners(){
		_telephonePrincipal.setOnClickListener(this);
		_telephoneSecondaire.setOnClickListener(this);
		_email.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendeur_detail_telephone_principal:
			break;
		case R.id.vendeur_detail_telephone_secondaire:
			break;
		case R.id.vendeur_detail_email:
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

		_pages.add(new TabVendeurDescription("Vendeur",_vendeur));
		_pages.add(new TabVendeurDescription("Vendeur",_vendeur));
		_pages.add(new TabVendeurDescription("Vendeur",_vendeur));

		_pagesAdapter = new PagesAdapter(getChildFragmentManager(),_pages);
		_page.setAdapter(_pagesAdapter);
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

}
