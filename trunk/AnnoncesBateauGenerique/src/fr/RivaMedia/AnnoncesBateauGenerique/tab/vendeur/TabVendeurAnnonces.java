
package fr.RivaMedia.AnnoncesBateauGenerique.tab.vendeur;


import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.adapter.AnnonceListAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.Tab;


@SuppressLint("ValidFragment") 
public class TabVendeurAnnonces extends Tab {

	View  _view;
	Activity _activity;

	Vendeur _vendeur;
	String _type;
	ListView _liste;
	List<Annonce> _annonces;

	AnnonceListAdapter _adapter;

	AsyncTask<Void, Void, Void> task = null;

	public TabVendeurAnnonces(String titre, Vendeur vendeur, String type, Activity activity){
		super(titre,activity.getResources().getDrawable(R.drawable.logo_vendre_blanc));
		this._vendeur = vendeur;
		this._type = type;
		this._activity = activity;

		if(_vendeur != null && _type != null){
			task = new ChargerAnnoncesTask();
			task.execute();
		}
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_annonces, container, false);

		if(task == null){
			task = new ChargerAnnoncesTask();
			task.execute();
		}

		return _view;
	}
	

	@Override
	public void onResume() {
		super.onResume();
		if(task == null){
			task = new ChargerAnnoncesTask();
			task.execute();
		}
	}

	public void charger(){
		if(_view != null)
			_liste = (ListView)_view.findViewById(android.R.id.list);		
	}
	public void remplir(){
		if(_liste != null){
			_adapter = new AnnonceListAdapter(_activity, _annonces,_type);
			_liste.setAdapter(_adapter);
		}else
			super.afficherVide(_view, true);
	}
	public void ajouterListeners(){
	}


	protected void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
	}

	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_annonces = NetAnnonce.getAnnoncesDe(_vendeur.getNumero(),_type);

			while(_annonces == null){
				//attente du chargement des annonces
			} 
			
			_activity.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
				}

			});
			
			task = null;

			return null;
		}

		protected void onPostExecute(){
		}
	}

}