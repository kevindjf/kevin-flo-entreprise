package fr.RivaMedia.AnnoncesBateauGenerique.fragments;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.dialog.MinMaxDialog;
import fr.RivaMedia.AnnoncesBateauGenerique.dialog.OnMinMaxListener;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentFormulaire;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.selector.DonneeValeurSelector;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.selector.MarqueSelector;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Categorie;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Etat;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Lieu;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.TypeCategories;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetAlerte;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetChargement;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.utils.JetonManager;

/**
 * Si 
 *   - BATEAU : [Type,Categorie,Prix,Longueur,Chantier/Modele,Etat,Localisation]
 *   - MOTEUR : [{Type=Moteurs},Categorie,Prix,Puissance,Marque,Etat,Localisation]
 *   - ACCESSOIRES : [{Type=Accessoires}, Categorie, Prix, Localisation]
 */

@SuppressLint("ValidFragment")
public class AnnoncesFormulaire extends FragmentFormulaire implements View.OnClickListener, ItemSelectedListener, OnMinMaxListener{

	public interface AnnoncesFormulaireDelegate{
		public void rechercher(List<NameValuePair> donneesFormulaire, String type);
	}

	private String typeAnnonces;

	private TypeAnnonce typeAnnoncesObject;

	public static int TYPE = 0;
	public static int CATEGORIE = 1;

	public static int CHANTIER_MODELE = 2;
	public static int MARQUE = 3;

	public static int ETAT = 4;
	public static int LOCALISATION = 5;

	View _view;
	View _rechercher;
	View _ajouterAlerte;
	TextView _nombreAnnonces;

	View _categorie;
	View _prix;
	View _longueur;
	View _chantierModele;
	View _etat;

	//MOTEUR
	View _puissance;
	View _marque;

	View[] _views;
	View[] _vuesBateau;
	View[] _vuesMoteur;
	View[] _vuesAccessoire;


	List<NameValuePair> donnees = new ArrayList<NameValuePair>();


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

	AnnoncesFormulaireDelegate delegate;

	public AnnoncesFormulaire(String type,AnnoncesFormulaireDelegate delegate, TypeAnnonce ta){
		this.typeAnnonces = type;
		this.delegate = delegate;
		this.typeAnnoncesObject = ta;
		
		if(typeAnnoncesObject != null)
			typeAnnonces = typeAnnoncesObject.getId();
		
		System.out.println("typeAnnoncesObject="+typeAnnoncesObject);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_formulaire,container, false);

		charger();
		remplir();
		ajouterListeners();
		chargerCouleur();

		return _view;
	}

	private void chargerCouleur() {
		afficherCouleurTouch(_rechercher);
		selector(_rechercher,false);
		((Button)_rechercher).setTextColor(Donnees.parametres.getBackgroundColorUn());		
	}

	public void charger(){
		_rechercher = _view.findViewById(R.id.annonces_formulaire_bouton_rechercher);	
		_ajouterAlerte = _view.findViewById(R.id.annonces_formulaire_bouton_alertes);	
		_nombreAnnonces = (TextView)_view.findViewById(R.id.annonces_formulaire_nombre_annonces);	

		_categorie = _view.findViewById(R.id.annonces_formulaire_categorie);
		_prix = _view.findViewById(R.id.annonces_formulaire_prix);
		_longueur = _view.findViewById(R.id.annonces_formulaire_longueur);
		_chantierModele = _view.findViewById(R.id.annonces_formulaire_chantier_modele);
		_etat = _view.findViewById(R.id.annonces_formulaire_etat);

		//MOTEUR
		_puissance = _view.findViewById(R.id.annonces_formulaire_puissance);
		_marque = _view.findViewById(R.id.annonces_formulaire_marque);

		_vuesBateau = new View[]{
				_categorie,
				_prix,
				_longueur,
				_chantierModele,
				_etat
		};

		_vuesMoteur = new View[]{
				_categorie,
				_prix,
				_puissance,
				_marque,
				_etat
		};

		_vuesAccessoire = new View[]{
				_categorie, 
				_prix
				
		};

		_views = new View[]{
				_categorie,
				_prix,
				_longueur,
				_chantierModele,
				_etat,
				_puissance,
				_marque,
		};
	}

	public void remplir(){
		if(typeAnnoncesObject.getId() != null){
			int id = Integer.parseInt(typeAnnoncesObject.getId());
			Log.e("Id ", id+"");
			if(id<4)
				afficherFormulaireBateau();
			else if (id == 4)
				afficherFormulaireMoteur();
			else
				afficherFormulaireAccessoire();
		}else{
			if(typeAnnonces.equals(Constantes.BATEAUX))
				afficherFormulaireBateau();
			else if(typeAnnonces.equals(Constantes.MOTEURS))
				afficherFormulaireMoteur();
			else if(typeAnnonces.equals(Constantes.ACCESSOIRES))
				afficherFormulaireAccessoire();
		}
	}

	public void ajouterListeners(){
		_rechercher.setOnClickListener(this);
		_ajouterAlerte.setOnClickListener(this);
		_categorie.setOnClickListener(this);
		_prix.setOnClickListener(this);
		_longueur.setOnClickListener(this);
		_chantierModele.setOnClickListener(this);
		_etat.setOnClickListener(this);
		_puissance.setOnClickListener(this);
		_marque.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.annonces_formulaire_bouton_rechercher) {
			rechercher();
		} else if (id == R.id.annonces_formulaire_type) {
			demanderType();
		} else if (id == R.id.annonces_formulaire_categorie) {
			demanderCategorie();
		} else if (id == R.id.annonces_formulaire_prix) {
			demanderPrix();
		} else if (id == R.id.annonces_formulaire_longueur) {
			demanderLongueur();
		} else if (id == R.id.annonces_formulaire_chantier_modele) {
			demanderChantierModele();
		} else if (id == R.id.annonces_formulaire_etat) {
			demanderEtat();
		} else if (id == R.id.annonces_formulaire_puissance) {
			demanderPuissance();
		} else if (id == R.id.annonces_formulaire_marque) {
			demanderMarque();
		}
	}



	protected void demanderType(){
		ajouterFragment(new DonneeValeurSelector(
				this,
				TYPE,
				false,
				DonneeValeurSelector.creerDonneeValeur(
						getString(R.string.bateau_a_moteur),Constantes.BATEAU_A_MOTEUR,
						getString(R.string.voiliers),Constantes.VOILIER,
						getResources().getString(R.string.pneumatiques_semi_rigide),Constantes.PNEU
						)
				)
				);

	}
	
	
	static AsyncTask<Void, Void, Void> taskCategorie = null;
	protected void demanderCategorie(){

		class DemanderCategorie extends AsyncTask<Void, Void, Void> {
			protected Void doInBackground(Void...donnees) {
				
				afficherProgress(true);
				TypeCategories typeCategorie = NetChargement.chargerTypesCategories(true,typeAnnonces);
				final List<Categorie> categories = typeCategorie.getCategories();
				
				getActivity().runOnUiThread(new Runnable(){

					@SuppressLint("DefaultLocale")
					@Override
					public void run() {
						if(categories != null){
							Map<String,String> donneesValeurs = new HashMap<String,String>();
							for(Categorie categorie : categories){
								donneesValeurs.put(categorie.getLibelle(), categorie.getId());
							}

							ajouterFragment(new DonneeValeurSelector(AnnoncesFormulaire.this,CATEGORIE,donneesValeurs));
						}
					}

				});
				
				afficherProgress(false);

				return null;
			}

			protected void onPostExecute(){
			}
		}
		
		try{
		if(taskCategorie != null)
			taskCategorie.cancel(true);
		}catch(Exception e){}
		
		taskCategorie = new DemanderCategorie();
		taskCategorie.execute();
	}

	protected void demanderPrix(){

		int prix = 300000;
		if(typeAnnonces.equals(Constantes.MOTEURS))
			prix=40000; 
		if(typeAnnonces.equals(Constantes.ACCESSOIRES))
			prix=40000; 

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
	protected void demanderChantierModele(){
		if(typeAnnonces.equals(Constantes.BATEAUX))
			ajouterFragment(new MarqueSelector(this,CHANTIER_MODELE,typeAnnonces,true));
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
	protected void demanderMarque(){

		if(typeAnnonces.equals(Constantes.MOTEURS)){
			List<Marque> marques = Donnees.getMarques(""+typeAnnonces,true);
			Map<String,String> donneesValeurs = new HashMap<String, String>();
			for(Marque m : marques){
				donneesValeurs.put(m.getLibelle(), m.getId());
			}
			ajouterFragment(new DonneeValeurSelector(this, MARQUE, donneesValeurs));
		}else{
			ajouterFragment(new MarqueSelector(this,MARQUE,""+typeAnnonces,true));
		}

	}

	public void afficherAnnoncesListe(List<NameValuePair> donneesFormulaire, String type){
		delegate.rechercher(donneesFormulaire,type);
		retirerFragment();
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

	protected void afficherFormulaireBateau(){
		reset();

		for(View v : _vuesBateau)
			v.setVisibility(View.VISIBLE);

	}

	protected void afficherFormulaireMoteur(){
		reset();

		for(View v : _vuesMoteur)
			v.setVisibility(View.VISIBLE);

		rechercherNombre();
	}
	protected void afficherFormulaireAccessoire(){
		reset();

		for(View v : _vuesAccessoire)
			v.setVisibility(View.VISIBLE);

		rechercherNombre();
	}

	@Override
	public void itemSelected(Object from,int idRetour, String item, String value) {	

		Log.e("ItemSelected", item+" | "+value);

		if(idRetour == TYPE){
			recherche_categorie_id = null;
			((TextView)_categorie.findViewById(R.id.text)).setText("");

			_marques = Donnees.getMarques(item,true);

		}else if(idRetour == CATEGORIE){
			recherche_categorie_id = item;
			((TextView)_categorie.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == CHANTIER_MODELE){
			if(item.equals("-1")){
				recherche_chantier_id = null;
				recherche_modele_id = null;
			}else{
				String[] ids = item.split(";");
				recherche_chantier_id = ids[0];
				recherche_modele_id = ids[1];
				Log.e("CHANTIER",recherche_chantier_id);
			}
			((TextView)_chantierModele.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == MARQUE){
			recherche_marque_id = item;
			recherche_modele_id = item;

			Log.e("MODELE",recherche_marque_id);

			((TextView)_marque.findViewById(R.id.text)).setText(value);
		}
		else if(idRetour == ETAT){
			((TextView)_etat.findViewById(R.id.text)).setText(value);
			recherche_etat_id = item;
		}

		rechercherNombre();
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
		if(titre.equals(getActivity().getResources().getString(R.string.puissance))){
			recherche_puissance_min = min;
			recherche_puissance_max = max;

			((TextView)_puissance.findViewById(R.id.text)).setText("de "+min+" ch à "+max+" ch");
		}

		rechercherNombre();

	}

	public void rechercher(){

		afficherAnnoncesListe(recupererDonnees(),recupererType());

	}

	public void rechercherNombre(){

		task = new RechercherNombreAnnoncesTask();
		task.execute();

	}

	protected String recupererType(){
		return typeAnnonces;  
	}

	protected List<NameValuePair> recupererDonnees(){

		List<NameValuePair> donnees = Net.construireDonnes();


		if(this.typeAnnonces != null)
			Net.add(donnees, Constantes.ANNONCES_TYPE_ID,typeAnnonces);

		if(this.recherche_categorie_id != null && !this.recherche_categorie_id.equals("-1"))
			Net.add(donnees, Constantes.ANNONCES_CATEGORIE_ID,recherche_categorie_id);

		if(recherche_localisation_id != null && !this.recherche_localisation_id.equals("-1"))
			Net.add(donnees, Constantes.ANNONCES_REGION_ID,recherche_localisation_id);

		if(this.recherche_longueur_min != null && this.recherche_longueur_max != null){
			if(!this.recherche_longueur_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_TAILLE,recherche_longueur_max);
			//if(!this.recherche_longueur_min.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_TAILLE,recherche_longueur_min);
		}

		if(this.recherche_puissance_min != null && this.recherche_puissance_max != null){
			if(!this.recherche_puissance_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_PUISS,recherche_puissance_max);
			//if(!this.recherche_puissance_min.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_PUISS,recherche_puissance_min);
		}

		if(this.recherche_prix_min != null && this.recherche_prix_max != null){
			if(!this.recherche_prix_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_PRIX,recherche_prix_max);
			//if(!this.recherche_prix_max.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_PRIX,recherche_prix_min);
		}

		if(this.recherche_puissance_min != null && this.recherche_puissance_max != null){
			if(!this.recherche_puissance_max.equals(MinMaxDialog.PLUS))
				Net.add(donnees, Constantes.ANNONCES_MAX_PUISS,recherche_puissance_max);
			//if(!this.recherche_prix_max.equals("0"))
			Net.add(donnees, Constantes.ANNONCES_MIN_PUISS,recherche_puissance_min);
		}


		if(recherche_etat_id != null && !this.recherche_etat_id.equals("-1"))
			Net.add(donnees, Constantes.ANNONCES_ETAT,recherche_etat_id);

		if(typeAnnonces.equals(Constantes.BATEAUX)){
			if(recherche_modele_id != null  && !this.recherche_modele_id.equals("-1"))
				Net.add(donnees,Constantes.ANNONCES_MODELE_ID,recherche_modele_id);
			if(recherche_chantier_id != null  && !this.recherche_chantier_id.equals("-1"))
				Net.add(donnees, Constantes.ANNONCES_MARQUE_ID,recherche_chantier_id);
		}
		else if(typeAnnonces.equals(Constantes.MOTEURS) && recherche_marque_id != null && !this.recherche_marque_id.equals("-1"))
			Net.add(donnees, Constantes.ANNONCES_MARQUE_ID,recherche_marque_id);

		return donnees;

	}

	@Override
	public void effacer() {
		reset();
		remplir();
		ajouterListeners();
	}


	public void afficherNombreAnnonces(final String nombre){
		try{
			if(getActivity() != null)
				getActivity().runOnUiThread(new Runnable(){

					@Override
					public void run() {
						System.err.println(nombre);
						_nombreAnnonces.setText(nombre);
						_nombreAnnonces.invalidate();
						System.err.println(_nombreAnnonces.getText());
					}

				});
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void onResume(){
		super.onResume();
		setTitre(getString(R.string.annonce_formulaire));
	}

	/* --------------------------------------------------------------------------- */

	class RechercherNombreAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...d) {
			synchronized (donnees) {
				donnees.clear();
				donnees.addAll(recupererDonnees());
				String nombre = NetAnnonce.nombreAnnonces(typeAnnonces, donnees);
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

		Net.add(donnees, Constantes.ALERTE_ID_TYPE,typeAnnonces);

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
