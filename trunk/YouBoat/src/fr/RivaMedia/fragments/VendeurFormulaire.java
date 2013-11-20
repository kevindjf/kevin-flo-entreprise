package fr.RivaMedia.fragments;

import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.ValeurSelector;
import fr.RivaMedia.net.core.Net;

@SuppressLint("ValidFragment")
public class VendeurFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener{

	public static final int PAYS = 0;

	View _view;
	View _valider;

	View _nom;
	View _prenom;
	View _email;
	View _telephone;
	View _codePostal;
	View _pays;

	String _url;
	List<NameValuePair> _donnees;

	View[] views;
	String[] valeurs;

	public VendeurFormulaire(String url, List<NameValuePair> donneesVente) {
		_url = url;
		_donnees = donneesVente;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.vendeur_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_valider = _view.findViewById(R.id.vendeur_formulaire_valider);	
		_nom = _view.findViewById(R.id.vendeur_formulaire_nom);	
		_prenom = _view.findViewById(R.id.vendeur_formulaire_prenom);	
		_email = _view.findViewById(R.id.vendeur_formulaire_email);	
		_telephone = _view.findViewById(R.id.vendeur_formulaire_telephone);	
		_codePostal = _view.findViewById(R.id.vendeur_formulaire_code_postal);	
		_pays = _view.findViewById(R.id.vendeur_formulaire_pays);	

		views = new View[]{
				_nom,
				_prenom,
				_email,
				_telephone,
				_codePostal,
				_pays
		};

		recupererValeursInitiales();

	}

	protected void recupererValeursInitiales(){
		valeurs = new String[views.length];
		int i=0;
		for(View v : views){
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				valeurs[i] = ((TextView)o).getText().toString();
			else if(o instanceof EditText)
				valeurs[i] = ((EditText)o).getHint().toString();
			//spinners, etc
			i++;
		}
	}

	public void remplir(){

	}

	public void reset(){
		int i=0;
		for(View v : views){
			v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(valeurs[i]);
			else if(o instanceof EditText)
				((EditText)o).setHint(valeurs[i]);
			//spinners, etc
			i++;
		}
	}

	public void ajouterListeners(){
		_valider.setOnClickListener(this);

		_pays.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendeur_formulaire_valider:
			valider();
			break;

		case R.id.vendeur_formulaire_pays:
			demanderPays();
			break;

		}
	}

	protected void demanderPays(){
		ajouterFragment(new ValeurSelector(this,PAYS,getResources().getStringArray(R.array.pays)));
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {	
		if(idRetour == PAYS){
			Log.e("ItemSelected", item+" | "+value);

			if(from instanceof ValeurSelector){
				((TextView)_pays.findViewById(R.id.text)).setText(value);
			}
		}
	}

	public void valider(){

		if(((EditText)_nom.findViewById(R.id.text)).getText().toString().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_nom), Toast.LENGTH_SHORT).show();
		else if(((EditText)_email.findViewById(R.id.text)).getText().toString().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_email), Toast.LENGTH_SHORT).show();
		else if(((TextView)_pays.findViewById(R.id.text)).getText().toString().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_pays), Toast.LENGTH_SHORT).show();
		else{
			validerVendeur(recupererDonnees());
		}
	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		Net.add(donnees, "nom",((EditText)_nom.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, "prenom",((EditText)_prenom.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, "email",((EditText)_email.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, "tel1",((EditText)_telephone.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, "cp",((EditText)_codePostal.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, "pays",((TextView)_pays.findViewById(R.id.text)).getText().toString());

		return donnees;
	}

	private void validerVendeur(List<NameValuePair> recupererDonnees) {
		// TODO Auto-generated method stub

	}

	@Override
	public void effacer() {
		reset();
		ajouterListeners();
	}

}
