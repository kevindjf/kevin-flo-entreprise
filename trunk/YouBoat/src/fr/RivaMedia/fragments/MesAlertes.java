package fr.RivaMedia.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.comparator.AnnoncePrixParLongueurComparator;
import fr.RivaMedia.fragments.core.FragmentListe;
import fr.RivaMedia.tab.alertes.TabMesAlertesAnnonces;
import fr.RivaMedia.tab.alertes.TabMesAlertesFormulaires;
import fr.RivaMedia.tab.core.PagesAdapter;
import fr.RivaMedia.tab.core.Tab;

public class MesAlertes extends FragmentListe implements View.OnClickListener{

	View _view;
	TabMesAlertesAnnonces _tabAnnonces = null;
	TabMesAlertesFormulaires _tabFormulaires = null;
	
	PagerAdapter _pagesAdapter;
	ViewPager _page;	
	List<Tab> _pages = new ArrayList<Tab>();
	
	Button _boutonAnnonces;
	Button _boutonFragments;
	
	View _boutonRechercher;
	View _boutonAjouterAlerte;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.mes_alertes,container, false);
		
		charger();
		remplir();
		ajouterListeners();

		return _view;
	}
	

	@Override
	public void charger() {
		_page = (ViewPager) _view.findViewById(R.id.mes_alertes_pager);
		_boutonAnnonces = (Button)_view.findViewById(R.id.mes_alertes_bouton_annonces);
		_boutonFragments = (Button)_view.findViewById(R.id.mes_alertes_bouton_formulaires);
		
		_boutonRechercher = _view.findViewById(R.id.mes_alertes_bouton_rechercher);
		_boutonAjouterAlerte = _view.findViewById(R.id.mes_alertes_bouton_alertes);
	}

	@Override
	public void remplir() {
		chargerTabs();		
	}

	@Override
	public void ajouterListeners() {
		_boutonAnnonces.setOnClickListener(this);
		_boutonFragments.setOnClickListener(this);
		
		_boutonRechercher.setOnClickListener(this);
		_boutonAjouterAlerte.setOnClickListener(this);
	}
	
	protected void chargerTabs(){
		_pages.clear();

		_tabAnnonces = new TabMesAlertesAnnonces("Annonces", getActivity(), this);
		_tabFormulaires = new TabMesAlertesFormulaires("Alertes", getActivity(), this);

		_pages.add(_tabAnnonces);
		_pages.add(_tabFormulaires);
		
		if(_pages.size()>0){
			_pagesAdapter = new PagesAdapter(getChildFragmentManager(),_pages);
			_page.setAdapter(_pagesAdapter);
		}else
			_page.setVisibility(View.GONE);
		
		afficherProgress = false;
	}
	

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.mes_alertes_bouton_annonces:
			inverserTabs();
			_page.setCurrentItem(0);
			break;
		case R.id.mes_alertes_bouton_formulaires:
			inverserTabs();
			_page.setCurrentItem(1);
			break;
		case R.id.mes_alertes_bouton_rechercher:
			rechercher();
			break;
		case R.id.mes_alertes_bouton_alertes:
			ajouterAlerte();
			break;
		}
	}
	
	private void ajouterAlerte() {
		ajouterFragment(new AjouterAlerteFormulaire());
	}


	private void rechercher() {
		ajouterFragment(new AnnoncesFormulaire(Constantes.BATEAUX));
	}


	@SuppressWarnings("deprecation")
	protected void inverserTabs(){
		Drawable couleur1 = _boutonAnnonces.getBackground();
		int couleurTexte1 = _boutonAnnonces.getTextColors().getDefaultColor();
		Drawable couleur2 = _boutonFragments.getBackground();
		int couleurTexte2 = _boutonFragments.getTextColors().getDefaultColor();
		
		_boutonAnnonces.setBackgroundDrawable(couleur2);
		_boutonFragments.setBackgroundDrawable(couleur1);
		
		_boutonAnnonces.setTextColor(couleurTexte2);
		_boutonFragments.setTextColor(couleurTexte1);	
	}
	protected void clickBoutonFormulaires(){
		
	}
	
	@Override
	public void effacer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherPrixCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherPrixCroissant();
		}
	}


	@Override
	public void afficherPrixDeCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherPrixDeCroissant();
		}
	}


	@Override
	public void afficherDateCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherDateCroissant();
		}
	}


	@Override
	public void afficherDateDeCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherDateDeCroissant();
		}
	}
	
	@Override
	public void afficherLongueurCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherLongueurCroissant();
		}
	}


	@Override
	public void afficherLongueurDeCroissant() {
		if(_tabAnnonces != null){
			_tabAnnonces.afficherLongueurDeCroissant();
		}
	}

	

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		if(_tabFormulaires != null)
			_tabFormulaires.onResume();
	}

	




}