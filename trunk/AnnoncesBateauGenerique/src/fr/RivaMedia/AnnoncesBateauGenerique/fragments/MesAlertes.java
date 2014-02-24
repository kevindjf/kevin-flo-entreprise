package fr.RivaMedia.AnnoncesBateauGenerique.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.AnnoncesFormulaire.AnnoncesFormulaireDelegate;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentListe;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.alertes.TabMesAlertesAnnonces;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.alertes.TabMesAlertesFormulaires;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.PagesAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.Tab;

public class MesAlertes extends FragmentListe implements View.OnClickListener, AnnoncesFormulaireDelegate{

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

	View _progressAnnonces;
	View _progressFormulaires;

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

		_progressAnnonces = _view.findViewById(R.id.mes_alertes_bouton_annonces_progress);
		_progressFormulaires = _view.findViewById(R.id.mes_alertes_bouton_formulaires_progress);
	}

	@Override
	public void remplir() {
		chargerTabs();		
	}

	public void afficherProgressAnnonces(boolean afficher){
		if(afficher)
			_progressAnnonces.setVisibility(View.VISIBLE);
		else
			_progressAnnonces.setVisibility(View.GONE);
	}

	public void afficherProgressFormulaires(boolean afficher){
		if(afficher)
			_progressFormulaires.setVisibility(View.VISIBLE);
		else
			_progressFormulaires.setVisibility(View.GONE);
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

		_boutonAnnonces.setSelected(true);
	}


	@Override
	public void onClick(View v) {
		super.onClick(v);
		int id = v.getId();
		if (id == R.id.mes_alertes_bouton_annonces) {
			if(!_boutonAnnonces.isSelected()){
				_boutonAnnonces.setSelected(true);
				_boutonFragments.setSelected(false);
				inverserTabs();
				_page.setCurrentItem(0);
			}
		} else if (id == R.id.mes_alertes_bouton_formulaires) {
			if(!_boutonFragments.isSelected()){
				_boutonFragments.setSelected(true);
				_boutonAnnonces.setSelected(false);
				inverserTabs();
				_page.setCurrentItem(1);
			}
		} else if (id == R.id.mes_alertes_bouton_rechercher) {
			rechercher();
		} else if (id == R.id.mes_alertes_bouton_alertes) {
			ajouterAlerte();
		}
	}

	private void ajouterAlerte() {
		ajouterFragment(new AjouterAlerteFormulaire());
	}


	private void rechercher() {
		ajouterFragment(new AnnoncesFormulaire(Constantes.BATEAUX,this,null));
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


	@Override
	public void rechercher(List<NameValuePair> donneesFormulaire, String type) {
		// TODO Auto-generated method stub
		
	}






}