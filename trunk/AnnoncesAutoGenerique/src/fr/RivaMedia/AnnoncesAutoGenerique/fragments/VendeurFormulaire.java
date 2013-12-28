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
import fr.RivaMedia.AnnoncesAutoGenerique.R;


@SuppressLint("ValidFragment")
public class VendeurFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener{

	public static int AUTHOTHEQUE = 1;


	public static final int PAYS = 0;

	View _view;
	View _valider;

	View _nom;
	View _prenom;
	View _email;
	View _telephone;
	View _codePostal;

	View _pays;
	View _ville;

	String idPays;

	MultipartEntity _donneesPost;
	List<NameValuePair> _donneesGet;

	List<Bitmap> _photos;

	View[] views;
	String[] valeurs;

	public VendeurFormulaire(List<NameValuePair> donneesGet) {
		_donneesGet = donneesGet;
		if(_donneesGet == null)
			_donneesGet = Net.construireDonnes();
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

		views = new View[]{
				_nom,
				_prenom,
				_email,
				_telephone,
				_codePostal,
				_pays,
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

		idPays = null;
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
		}
	}

	protected void demanderPays(){
		List<Departement> departements = Donnees.departements;
		if(departements != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Departement departement : departements){
				donneesValeurs.put(departement.getNom(), departement.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,PAYS,false,donneesValeurs));
		}
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {	
		if(idRetour == PAYS){
			Log.e("ItemSelected", item+" | "+value);

			((TextView)_pays.findViewById(R.id.text)).setText(value);
			idPays = item;
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
		//TODO Recuperer les donnees
		/*
		String nom = ((EditText)_nom.findViewById(R.id.text)).getText().toString().trim().replace(" ", "%20");
		Net.add(_donneesGet, Constantes.VENDEUR_NOM,nom);

		String prenom = ((EditText)_prenom.findViewById(R.id.text)).getText().toString().trim().replace(" ", "%20");
		if(prenom.length() > 0)
		Net.add(_donneesGet, Constantes.VENDEUR_PRENOM,prenom);

		Net.add(_donneesGet, Constantes.VENDEUR_EMAIL,((EditText)_email.findViewById(R.id.text)).getText().toString());
		Net.add(_donneesGet, Constantes.VENDEUR_TEL_1,((EditText)_telephone.findViewById(R.id.text)).getText().toString().trim().replace(".", "").replace(",",""));
		Net.add(_donneesGet, Constantes.VENDEUR_CODE_POSTAL,((EditText)_codePostal.findViewById(R.id.text)).getText().toString());



		String ville = ((TextView)_ville.findViewById(R.id.text)).getText().toString().trim().replace(" ", "%20");
		if(ville.length() > 0){
			Net.add(_donneesGet, Constantes.VENDEUR_VILLE,ville);
			System.out.println("ajout ville : "+ville);
		}
		
		*/

	}

	private void validerVendeur() {
		recupererDonnees();
		if(task == null){
				afficherProgress(true);
				task = new EnvoyerOnDemandTask();
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
		Toast.makeText(getActivity(), R.string.demande_postee, Toast.LENGTH_LONG).show();
		afficherProgress(false);
		ajouterFragment(new AnnoncesFormulaire(), false);
	}

	class EnvoyerOnDemandTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			synchronized (_donneesGet) {

				//TODO Reponse
				//String reponse = NetAnnonce.onDemand(_donneesGet);
				//System.out.println(reponse);
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

	public void vendu(){
		Toast.makeText(getActivity(), R.string.annonce_postee, Toast.LENGTH_LONG).show();
		afficherProgress(false);
		ajouterFragment(new Accueil(), false);
	}

	class EnvoyerVendreTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			synchronized (_donneesPost) {

				//TODO Reponse
				//String reponse = NetVendre.vendre(_donneesPost, _photos);
				//System.out.println(reponse);
			}

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					afficherProgress(false);
					vendu();
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
