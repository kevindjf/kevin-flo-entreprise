package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntity;

import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentFormulaire;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Departement;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetRecherche;
import fr.RivaMedia.AnnoncesAutoGenerique.net.core.Net;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.AnnoncesAutoGenerique.Constantes;
import fr.RivaMedia.AnnoncesAutoGenerique.R;


@SuppressLint("ValidFragment")
public class VendeurFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener{

	public static int AUTHOTHEQUE = 1;

	public static final int DEPARTEMENT = 0;

	View _view;
	View _valider;

	View _nom;
	View _prenom;
	View _email;
	View _telephone;
	View _ville;
	View _codePostal;
	
	View _departement;

	String departementId;

	List<NameValuePair> _donnees;

	List<Bitmap> _photos;

	View[] views;
	String[] valeurs;

	public VendeurFormulaire(List<NameValuePair> donnees) {
		_donnees = donnees;
		if(_donnees == null)
			_donnees = Net.construireDonnes();
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
		_ville = _view.findViewById(R.id.vendeur_formulaire_ville);	
		
		_departement = _view.findViewById(R.id.vendeur_formulaire_departement);	

		views = new View[]{
				_nom,
				_prenom,
				_email,
				_telephone,
				_codePostal,
				_ville
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


	public void reset(){
		int i=0;
		for(View v : views){
			//v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText(valeurs[i]);
			else if(o instanceof EditText)
				((EditText)o).setHint(valeurs[i]);
			//spinners, etc
			i++;
		}

		departementId = null;
	}

	public void ajouterListeners(){
		_valider.setOnClickListener(this);

		_departement.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendeur_formulaire_valider:
			valider();
			break;
		case R.id.vendeur_formulaire_departement:
			demanderDepartement();
			break;
		}
	}

	protected void demanderDepartement(){
		List<Departement> departements = Donnees.departements;
		if(departements != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Departement departement : departements){
				donneesValeurs.put(departement.getNom(), departement.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,DEPARTEMENT,false,donneesValeurs));
		}
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {	
		if(idRetour == DEPARTEMENT){
			Log.e("ItemSelected", item+" | "+value);

			((TextView)_departement.findViewById(R.id.text)).setText(value);
			departementId = item;
		}
	}

	public void valider(){

		if(((EditText)_nom.findViewById(R.id.text)).getText().toString().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_nom), Toast.LENGTH_SHORT).show();
		else if(((EditText)_email.findViewById(R.id.text)).getText().toString().trim().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_email), Toast.LENGTH_SHORT).show();
		else if(((EditText)_codePostal.findViewById(R.id.text)).getText().toString().trim().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_code_postale), Toast.LENGTH_SHORT).show();
		else if(((EditText)_telephone.findViewById(R.id.text)).getText().toString().trim().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_numero_telephone), Toast.LENGTH_SHORT).show();
		else{
		
				validerVendeur();	
			
		}


	}

	protected void recupererDonnees(){
		
		String nom = ((EditText)_nom.findViewById(R.id.text)).getText().toString().trim();
		String email = ((EditText)_email.findViewById(R.id.text)).getText().toString().trim();
		if(nom.length() == 0)
			Toast.makeText(getActivity(), R.string.veuillez_indiquer_votre_nom, Toast.LENGTH_SHORT).show();
		else if(email.length() == 0)
			Toast.makeText(getActivity(), R.string.veuillez_indiquer_votre_email, Toast.LENGTH_SHORT).show();
		else{
			Net.add(_donnees, 
					Constantes.RECHERCHE_NOM,nom,
					Constantes.RECHERCHE_EMAIL,email
					);
		
		
			String telephone = ((EditText)_telephone.findViewById(R.id.text)).getText().toString().trim();
			if(telephone.length()>0)
				Net.add(_donnees, Constantes.RECHERCHE_TELEPHONE,telephone);
			
			if(departementId != null)
				Net.add(_donnees, Constantes.RECHERCHE_DEPARTEMENT,departementId);

		}

	}

	private void validerVendeur() {
		recupererDonnees();
		if(task == null){
				afficherProgress(true);
				task = new EnvoyerRechercheTask();
				task.execute();
		}
	}

	@Override
	public void effacer() {
		reset();
		ajouterListeners();
	}


	/* --------------------------------------------------------------------------- */

	public void demande_envoyee(){
		Toast.makeText(getActivity(), R.string.recherche_postee, Toast.LENGTH_LONG).show();
		afficherProgress(false);
		ajouterFragment(new AnnoncesFormulaire(), false);
	}

	class EnvoyerRechercheTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			synchronized (_donnees) {
				String reponse = NetRecherche.recherche(_donnees);
			}

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					demande_envoyee();
				}

			});
			return null;
		}

		protected void onPostExecute(){
		}
	}

	@Override
	public void remplir() {
		// TODO Auto-generated method stub
		
	}

}
