package fr.RivaMedia.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.ValeurSelector;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;
import fr.RivaMedia.net.NetNews;
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
	List<NameValuePair> _donneesPrecedentes;

	List<NameValuePair> _donneesEnvoyees;

	View[] views;
	String[] valeurs;

	public VendeurFormulaire(String url, List<NameValuePair> donneesPrecedentes) {
		_url = url;
		_donneesPrecedentes = donneesPrecedentes;
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
		else if(((EditText)_email.findViewById(R.id.text)).getText().toString().trim().equals(""))
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_email), Toast.LENGTH_SHORT).show();
		else
			if(((TextView)_pays.findViewById(R.id.text)).getText().toString().trim().equals(getString(R.string.requis)))
				Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_pays), Toast.LENGTH_SHORT).show();

			else if(((EditText)_codePostal.findViewById(R.id.text)).getText().toString().trim().equals(""))
				Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_indiquer_votre_code_postale), Toast.LENGTH_SHORT).show();
			else
				validerVendeur(recupererDonnees());	

	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();

		Net.add(donnees, Constantes.VENDEUR_NOM,((EditText)_nom.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, Constantes.VENDEUR_PRENOM,((EditText)_prenom.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, Constantes.VENDEUR_EMAIL,((EditText)_email.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, Constantes.VENDEUR_TEL_1,((EditText)_telephone.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, Constantes.VENDEUR_CODE_POSTAL,((EditText)_codePostal.findViewById(R.id.text)).getText().toString());
		Net.add(donnees, Constantes.VENDEUR_PAYS,((TextView)_pays.findViewById(R.id.text)).getText().toString());

		return donnees;
	}

	private void validerVendeur(List<NameValuePair> donneesVendeur) {

		_donneesEnvoyees = new ArrayList<NameValuePair>();
		_donneesEnvoyees.addAll(_donneesPrecedentes);
		_donneesEnvoyees.addAll(donneesVendeur);

		if(_url.equals(Constantes.URL_ON_DEMAND)){
			task = new EnvoyerOnDemandTask();
			task.execute();
		}else{ //vendre
			task = new EnvoyerVendreTask();
			task.execute();
		}


	}

	@Override
	public void effacer() {
		reset();
		ajouterListeners();
	}


	/* --------------------------------------------------------------------------- */

	class EnvoyerOnDemandTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			synchronized (_donneesEnvoyees) {
				Net.requete(_url, _donneesEnvoyees);
			}

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
					alert.setTitle(R.string.votre_recherche_est_bien_enregistree);
					alert.setMessage(R.string.gerer_votre_recherche_directement_sur);
					alert.setPositiveButton(R.string.ok, new Dialog.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int pos) {
							dialog.dismiss();
							((MainActivity)getActivity()).ajouterFragment(new Annonces(),false);
						}

					});
					alert.show();
				}

			});
			return null;
		}

		protected void onPostExecute(){
		}
	}

	class EnvoyerVendreTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			synchronized (_donneesEnvoyees) {
				Net.requete(_url, _donneesEnvoyees);
			}

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
					alert.setTitle("?"); //TODO
					alert.setMessage("?"); //TODO
					alert.setPositiveButton(R.string.ok, new Dialog.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int pos) {
							dialog.dismiss();
							((MainActivity)getActivity()).ajouterFragment(new Annonces(),false);
						}

					});
					alert.show();
				}

			});
			return null;
		}

		protected void onPostExecute(){
		}
	}

}
