package fr.RivaMedia.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
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
import fr.RivaMedia.dialog.MinMaxDialog;
import fr.RivaMedia.dialog.OnMinMaxListener;
import fr.RivaMedia.fragments.core.FragmentFormulaire;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.fragments.selector.MarqueSelector;
import fr.RivaMedia.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.Etat;
import fr.RivaMedia.model.Lieu;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetAlerte;
import fr.RivaMedia.net.NetAnnonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.utils.JetonManager;

/**
 * Si 
 *   - BATEAU : [Type,Categorie,Prix,Longueur,Chantier/Modele,Etat,Localisation]
 *   - MOTEUR : [{Type=Moteurs},Categorie,Prix,Puissance,Marque,Etat,Localisation]
 *   - ACCESSOIRES : [{Type=Accessoires}, Categorie, Prix, Localisation]
 */

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	public static int CATEGORIE = 1;
	public static int MARQUE_MODELE = 2;
	public static int MARQUE = 3;

	public static int ETAT = 4;
	public static int LOCALISATION = 5;

	View _view;
	View _rechercher;
	View _ajouterAlerte;
	TextView _nombreAnnonces;

	View _carrosserie;
	View _marqueModele;
	View _energie;
	View _boite_de_vitesse;
	View _nb_portes;
	View _annee;
	View _km_max;
	View _prix;
	View _departement;

	View[] _views;



	List<NameValuePair> donnees = new ArrayList<NameValuePair>();


	String recherche_type = null;
	String recherche_categorie_id = null;
	String recherche_marque_id = null;

	String recherche_chantier_id = null;
	String recherche_modele_id = null;

	String recherche_prix_min = null;
	String recherche_prix_max = null;

	String recherche_longueur_min = null;
	String recherche_longueur_max = null;

	String recherche_puissance_min = null;
	String recherche_puissance_max = null;

	String recherche_localisation_id = null;
	String recherche_etat_id = null;

	List<Marque> _marques = null;

	public AnnoncesFormulaire(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	public void charger(){
		_rechercher = _view.findViewById(R.id.annonces_formulaire_bouton_rechercher);	
		_ajouterAlerte = _view.findViewById(R.id.annonces_formulaire_bouton_alertes);	
		_nombreAnnonces = (TextView)_view.findViewById(R.id.annonces_formulaire_nombre_annonces);	

		_carrosserie = _view.findViewById(R.id.annonces_formulaire_carrosserie);
		_marqueModele = _view.findViewById(R.id.annonces_formulaire_marque_modele);
		_energie = _view.findViewById(R.id.annonces_formulaire_energie);
		_boite_de_vitesse = _view.findViewById(R.id.annonces_formulaire_boite_vitesse);
		_nb_portes  = _view.findViewById(R.id.annonces_formulaire_nb_porte);
		_annee = _view.findViewById(R.id.annonces_formulaires_annee);
		_km_max = _view.findViewById(R.id.annonces_formulaires_km_max);
		_prix = _view.findViewById(R.id.annonces_formulaires_prix);
		_departement = _view.findViewById(R.id.annonces_formulaires_departement);

		_views = new View[]{
				_carrosserie,
				_marqueModele,
				_energie,
				_boite_de_vitesse,
				_nb_portes,
				_annee,
				_km_max,
				_prix,
				_departement,
		};
	}

	public void remplir(){
	}

	public void ajouterListeners(){
		_rechercher.setOnClickListener(this);
		_ajouterAlerte.setOnClickListener(this);

		_carrosserie.setOnClickListener(this);
		_marqueModele.setOnClickListener(this);
		_energie.setOnClickListener(this);
		_boite_de_vitesse.setOnClickListener(this);
		_annee.setOnClickListener(this);
		_km_max.setOnClickListener(this);
		_prix.setOnClickListener(this);
		_departement.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annonces_formulaire_bouton_rechercher:
			rechercher();
			break;
		case R.id.annonces_formulaire_bouton_alertes:
			ajouterAlerte();
			break;

		case R.id.annonces_formulaire_carrosserie:
			demanderCarrosserie();
			break;
		case R.id.annonces_formulaire_marque_modele:
			demanderMarqueModele();
			break;
		case R.id.annonces_formulaire_energie:
			demanderEnergie();
			break;
		case R.id.annonces_formulaire_boite_vitesse:
			demanderBoiteVitesse();
			break;
		case R.id.annonces_formulaires_annee:
			demanderAnnee();
			break;
		case R.id.annonces_formulaires_km_max:
			demanderKmMax();
			break;
		case R.id.annonces_formulaires_prix:
			demanderPrix();
			break;
		case R.id.annonces_formulaires_departement:
			demanderLocalisation();
			break;
		}
	}

	private void demanderKmMax() {
		// TODO Auto-generated method stub

	}

	private void demanderAnnee() {
		// TODO Auto-generated method stub

	}

	private void demanderBoiteVitesse() {
		// TODO Auto-generated method stub

	}

	private void demanderEnergie() {
		// TODO Auto-generated method stub

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

	protected void demanderCarrosserie(){
	}

	protected void demanderPrix(){

		int prix = 300000;

		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.prix),
				this,
				recherche_prix_min,recherche_prix_max,
				prix
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
	protected void demanderMarqueModele(){
		ajouterFragment(new MarqueSelector(this,MARQUE_MODELE,recherche_type,true));
	}
	protected void demanderEtat(){
		List<Etat> etats = Donnees.etats;
		if(etats != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Etat etat : etats){
				donneesValeurs.put(etat.getNom(), etat.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,ETAT,donneesValeurs));
		}
	}
	protected void demanderLocalisation(){

		List<Lieu> lieux = Donnees.lieux;
		if(lieux != null){
			Map<String,String> donneesValeurs = new HashMap<String,String>();
			for(Lieu lieu : lieux){
				donneesValeurs.put(lieu.getNom(), lieu.getId());
			}

			ajouterFragment(new DonneeValeurSelector(this,LOCALISATION,donneesValeurs));
		}
	}
	protected void demanderPuissance(){
		new MinMaxDialog(
				getActivity(), 
				getActivity().getResources().getString(R.string.puissance),
				this,
				recherche_puissance_min,recherche_puissance_max,
				500
				).show();
	}


	public void afficherAnnoncesListe(List<NameValuePair> donneesFormulaire){
	//	ajouterFragment(new AnnoncesListe(donneesFormulaire));
	}

	public void reset(){
		for(View v : _views){
			v.setOnClickListener(null);
			v.setVisibility(View.GONE);
			Object o = v.findViewById(R.id.text);
			if(o instanceof TextView)
				((TextView)o).setText("");
			else if(o instanceof EditText)
				((EditText)o).setText("");
			//spinners, etc
		}

		recherche_type = null;
		recherche_categorie_id = null;

		recherche_chantier_id = null;
		recherche_modele_id = null;
		recherche_marque_id = null;
		recherche_localisation_id = null;
		recherche_etat_id = null;

		recherche_prix_min = null;
		recherche_prix_max = null;

		recherche_longueur_min = null;
		recherche_longueur_max = null;

		recherche_puissance_min = null;
		recherche_puissance_max = null;

		_nombreAnnonces.setText("");
		donnees.clear();
	}


	@Override
	public void itemSelected(Object from,int idRetour, String item, String value) {	

		Log.e("ItemSelected", item+" | "+value);

		//TODO recupérer les retours des champs
		rechercherNombre();
	}


	@Override
	public void onMinMaxSelected(String titre, String min, String max) {
		if(titre.equals(getActivity().getResources().getString(R.string.prix))){
			recherche_prix_min = min;
			recherche_prix_max = max;

			((TextView)_prix.findViewById(R.id.text)).setText("de "+min+" € à "+max+" €");
		}
		
		//TODO Mettre les min max

		rechercherNombre();

	}

	public void rechercher(){

		if(this.recherche_type == null)
			Toast.makeText(getActivity(), getActivity().getString(R.string.veuillez_choisir_un_type), Toast.LENGTH_SHORT).show();
		else{
			afficherAnnoncesListe(recupererDonnees());
		}
	}

	public void rechercherNombre(){

		if(this.recherche_type != null){
			task = new RechercherNombreAnnoncesTask();
			task.execute();
		}
	}


	protected List<NameValuePair> recupererDonnees(){
		List<NameValuePair> donnees = Net.construireDonnes();
		return donnees;
	}

	@Override
	public void effacer() {
		reset();
		remplir();
		ajouterListeners();
	}


	public void afficherNombreAnnonces(final String nombre){
		getActivity().runOnUiThread(new Runnable(){

			@Override
			public void run() {
				System.err.println(nombre);
				_nombreAnnonces.setText(nombre);
				_nombreAnnonces.invalidate();
				System.err.println(_nombreAnnonces.getText());
			}

		});

	}

	/* --------------------------------------------------------------------------- */

	class RechercherNombreAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...d) {
			synchronized (donnees) {
				donnees.clear();
				donnees.addAll(recupererDonnees());
				String nombre = NetAnnonce.nombreAnnonces(recherche_type, donnees);
				afficherNombreAnnonces(nombre);
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}

	/* --------------------ALERTE-----------------------*/

	protected List<NameValuePair> recupererDonneesFormulaireAlerte(){
		List<NameValuePair> donnees = Net.construireDonnes();

		JetonManager jm = new JetonManager(this.getActivity());
		String jeton = jm.getJeton();

		Net.add(donnees, Constantes.ALERTE_ID_SMARTPHONE,jeton);

		//TODO ajouter le md5 de la date

		if(this.recherche_type != null)
			Net.add(donnees, Constantes.ALERTE_ID_TYPE,recherche_type);

		if(this.recherche_categorie_id != null && !this.recherche_categorie_id.equals("-1"))
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
			final String reponse = NetAlerte.creerAlerte(recupererDonneesFormulaireAlerte());

			getActivity().runOnUiThread(new Runnable(){

				@SuppressLint("DefaultLocale")
				@Override
				public void run() {
					if(!reponse.toLowerCase().trim().equals("false")){
						JetonManager jm = new JetonManager(getActivity());
						jm.setJeton(reponse);
						alerteAjoutee();
					}
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
