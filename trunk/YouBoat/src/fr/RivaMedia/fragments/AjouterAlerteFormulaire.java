package fr.RivaMedia.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetAlerte;
import fr.RivaMedia.net.core.Net;

public class AjouterAlerteFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	public static int TYPE = 0;
	public static int CATEGORIE = 1;

	View _view;

	View _ajouterAlerte;
	View _type;
	View _categorie;
	View _prix;
	View _longueur;

	String recherche_type = null;
	String recherche_categorie_id = null;
	String recherche_marque_id = null;

	String recherche_prix_min = null;
	String recherche_prix_max = null;

	String recherche_longueur_min = null;
	String recherche_longueur_max = null;

	View[] views;

	public AjouterAlerteFormulaire(){

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.alerte_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.alerte_formulaire_type:
			demanderType();
			break;
		case R.id.alerte_formulaire_categorie:
			demanderCategorie();
			break;
		case R.id.alerte_formulaire_prix:
			demanderPrix();
			break;
		case R.id.alerte_formulaire_longueur:
			demanderLongueur();
			break;
		case R.id.alerte_formulaire_bouton_alertes:
			ajouterAlerte();
			break;
		}
	}

	private void ajouterAlerte() {
		if(this.recherche_type == null)
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		else if(this.recherche_categorie_id == null)
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_choisir_une_categorie), Toast.LENGTH_SHORT).show();
		else{
			task = new AjouterAlerteTask();
			task.execute();
		}
	}

	@Override
	public void charger() {
		_ajouterAlerte = _view.findViewById(R.id.alerte_formulaire_bouton_alertes);	

		_type = _view.findViewById(R.id.alerte_formulaire_type);
		_categorie = _view.findViewById(R.id.alerte_formulaire_categorie);
		_prix = _view.findViewById(R.id.alerte_formulaire_prix);
		_longueur = _view.findViewById(R.id.alerte_formulaire_longueur);


		views = new View[]{
				_type,
				_categorie,
				_prix,
				_longueur
		};
	}

	@Override
	public void remplir() {
	}

	@Override
	public void ajouterListeners() {
		_ajouterAlerte.setOnClickListener(this);
		_type.setOnClickListener(this);
		_categorie.setOnClickListener(this);
		_prix.setOnClickListener(this);
		_longueur.setOnClickListener(this);
	}

	@Override
	public void effacer() {
		reset();
	}

	@Override
	public void reset() {
		for(View v : views){
			//v.setOnClickListener(null);
			//v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText("");
			else if(o instanceof EditText)
				((EditText)o).setText("");
			//spinners, etc
		}
	}

	protected void demanderType(){
		ajouterFragment(new DonneeValeurSelector(
				this,
				TYPE,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				));

	}
	protected void demanderCategorie(){
		if(recherche_type == null){
			Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		}
		else{
			String type = recherche_type;

			List<Categorie> categories = Donnees.getCategories(type);
			if(categories != null){
				Map<String,String> donneesValeurs = new HashMap<String,String>();
				for(Categorie categorie : categories){
					donneesValeurs.put(categorie.getLibelle(), categorie.getId());
				}

				ajouterFragment(new DonneeValeurSelector(this,CATEGORIE,donneesValeurs));
			}
		}
	}

	protected void demanderPrix(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.prix),
				this,
				recherche_prix_min,recherche_prix_max,
				50000
				).show();
	}
	protected void demanderLongueur(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.longueur),
				this,
				recherche_longueur_min,recherche_longueur_max,
				18
				).show();
	}

	@Override
	public void itemSelected(Object from, int idRetour, String item, String value) {
		Log.e("ItemSelected", item+" | "+value);

		if(idRetour == TYPE){
			recherche_type = item;
			((TextView)_type.findViewById(R.id.text)).setText(value);
			recherche_categorie_id = null;
			((TextView)_categorie.findViewById(R.id.text)).setText("");

		}else if(idRetour == CATEGORIE){
			recherche_categorie_id = item;
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
	}

	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.prix))){
			recherche_prix_min = min;
			recherche_prix_max = max;

			((TextView)_prix.findViewById(R.id.text)).setText("de "+min+" € à "+max+" €");
		}
		if(titre.equals(getActivity().getResources().getString(R.string.longueur))){
			recherche_longueur_min = min;
			recherche_longueur_max = max;

			((TextView)_longueur.findViewById(R.id.text)).setText("de "+min+" m à "+max+" m");
		}
	}

	protected List<NameValuePair> recupererDonneesFormulaire(){
		List<NameValuePair> donnees = Net.construireDonnes();
		
		String android_id = Secure.getString(getActivity().getContentResolver(),Secure.ANDROID_ID);
		Net.add(donnees, Constantes.ALERTE_ID_SMARTPHONE,android_id);
		
		//TODO ajouter le md5 de la date

		if(this.recherche_type != null)
			Net.add(donnees, Constantes.ALERTE_ID_TYPE,recherche_type);

		if(this.recherche_categorie_id != null)
			Net.add(donnees, Constantes.ALERTE_ID_CATEGORIE,recherche_categorie_id);

		if(this.recherche_longueur_min != null && this.recherche_longueur_max != null){
			if(!this.recherche_longueur_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ALERTE_MAX_LONG,recherche_longueur_max);
			//if(!this.recherche_longueur_min.equals("0"))
			Net.add(donnees, Constantes.ALERTE_MIN_LONG,recherche_longueur_min);
		}

		if(this.recherche_prix_min != null && this.recherche_prix_max != null){
			if(!this.recherche_prix_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ALERTE_MAX_PRIX,recherche_prix_max);
			//if(!this.recherche_prix_max.equals("0"))
			Net.add(donnees, Constantes.ALERTE_MIN_PRIX,recherche_prix_min);
		}

		return donnees;
	}
	
	public void alerteAjoutee(){
		Toast.makeText(getActivity(), R.string.alerte_ajoutee, Toast.LENGTH_LONG).show();
		getFragmentManager().popBackStack();
	}
	
	public void erreurAlerte(){
		Toast.makeText(getActivity(), R.string.erreur_alerte, Toast.LENGTH_LONG).show();
	}
	
	/* --------------------------------------------------------------------------- */

	class AjouterAlerteTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			final String reponse = NetAlerte.creerAlerte(recupererDonneesFormulaire());

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					if(reponse.toLowerCase().trim().equals("true"))
						alerteAjoutee();
					else
						erreurAlerte();
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
