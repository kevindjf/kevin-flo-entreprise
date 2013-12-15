package fr.RivaMedia.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.dialog.MinMaxDialog;
import fr.RivaMedia.dialog.OnMinMaxListener;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.fragments.selector.MarqueSelector;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Lieu;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.core.Net;

@SuppressLint("ValidFragment")
public class Authotheque extends FragmentFormulaire implements ItemSelectedListener , OnMinMaxListener{

	View _view;

	View[] _views;
	String[] _texteInitial;

	View _carrosserie;
	View _marqueModele;
	View _finition;
	View _energie;
	View _annee;
	View _kilometrage;
	View _commentaire;
	View _budget;
	View _couleurExterieur;
	View _couleurInterieur;
	View _boiteDeVitesse;

	View _boat_on_demand_etape_suivante;

	List<Marque> _marques = null;
	List<Marque> _marques_posseder = null;

	String budget_requis;
	String taille_requis;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.authotheque, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_carrosserie = _view.findViewById(R.id.autotheque_carrosserie);
		_marqueModele = _view.findViewById(R.id.autotheque_marque_modele);
		_finition = _view.findViewById(R.id.autotheque_finition);
		_energie = _view.findViewById(R.id.autotheque_energie);
		_annee = _view.findViewById(R.id.autotheque_annee);
		_kilometrage = _view.findViewById(R.id.autotheque_kilometrage);
		_commentaire = _view.findViewById(R.id.autotheque_commentaire);
		_budget = _view.findViewById(R.id.autotheque_budget);
		_couleurExterieur = _view.findViewById(R.id.autotheque_couleur_exterieur);
		_couleurInterieur = _view.findViewById(R.id.autotheque_couleur_interieur);
		_boiteDeVitesse = _view.findViewById(R.id.autotheque_boite_de_vitesse);
		_boat_on_demand_etape_suivante = _view.findViewById(R.id.boat_on_demand_etape_suivante);

		_views = new View[]{
				_carrosserie,
				_marqueModele,
				_finition,
				_energie,
				_annee,
				_kilometrage,
				_commentaire,
				_budget,
				_couleurExterieur,
				_couleurInterieur,
				_boiteDeVitesse,
		};

		_texteInitial = new String[_views.length];
		for(int i=0;i<_views.length;++i){
			String texte = null;
			Object o = _views[i].findViewById(R.id.text);
			if(o instanceof TextView)
				texte = ((TextView)o).getText().toString();
			else if(o instanceof EditText)
				texte = ((EditText)o).getHint().toString();
			_texteInitial[i] = texte;
		}
	}

	public void remplir(){

	}

	public void ajouterListeners(){
		_carrosserie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_finition.setOnClickListener(this);
		_energie.setOnClickListener(this);
		_annee.setOnClickListener(this);
		_kilometrage.setOnClickListener(this);
		_couleurExterieur.setOnClickListener(this);
		_couleurInterieur.setOnClickListener(this);
		_boiteDeVitesse.setOnClickListener(this);
		_boat_on_demand_etape_suivante.setOnClickListener(this);
		_budget.findViewById(R.id.text).setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText()+" €");
				}else{
					if(
							((EditText)(v.findViewById(R.id.text))).getText().toString().trim().equals("0")){
						((EditText)(v.findViewById(R.id.text))).setText("");
					}else{
						((EditText)(v.findViewById(R.id.text))).setText(((EditText)(v.findViewById(R.id.text))).getText().toString().replace(" ���", ""));
					}
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.autotheque_carrosserie:
			demanderCarrosserie();
			break;
		case R.id.autotheque_marque_modele:
			demanderMarqueModele();
			break;
		case R.id.autotheque_finition:
			demanderFinition();
			break;
		case R.id.autotheque_energie:
			demanderEnergie();
			break;
		case R.id.autotheque_annee:
			demanderAnneeMinMax();
			break;
		case R.id.autotheque_couleur_exterieur:
			demanderCouleurExterieur();
			break;
		case R.id.autotheque_couleur_interieur:
			demanderCouleurInterieur();
			break;
		}
	}

	private void demanderCouleurInterieur() {
		// TODO Auto-generated method stub
		
	}

	private void demanderCouleurExterieur() {
		// TODO Auto-generated method stub
		
	}

	private void demanderAnneeMinMax() {
		// TODO Auto-generated method stub
		
	}

	private void demanderEnergie() {
		// TODO Auto-generated method stub
		
	}

	private void demanderFinition() {
		// TODO Auto-generated method stub
		
	}

	private void demanderMarqueModele() {
		// TODO Auto-generated method stub
		
	}

	private void demanderCarrosserie() {
		// TODO Auto-generated method stub
		
	}

	private void demanderEtapeSuivante() {
		List<NameValuePair> donneesVente = recupererDonnees();
		if(donneesVente == null)
			return;

		//TODO Envoyer vers le formulaire vendeur
		//ajouterFragment(new VendeurFormulaire(VendeurFormulaire.ON_DEMAND,null,donneesVente,null));
	}

	private List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = new ArrayList<NameValuePair>();
		return donnees;
	}




	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {
		
//TODO TEST SUR LES ID DE RETOUR

	}

	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
//TODO Année for example
	}

	@Override
	public void effacer() {
		reset();
		remplir();
	}

	@Override
	public void reset() {
		int i=0;
		for(View v : _views){
			//v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(_texteInitial[i]);
			else if(o instanceof EditText)
				((EditText)o).setHint(_texteInitial[i]);
			//spinners, etc
			++i;
		}
	}
}
