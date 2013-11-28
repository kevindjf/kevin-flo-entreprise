package fr.RivaMedia.fragments;


import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.comparator.AnnonceDateComparator;
import fr.RivaMedia.comparator.AnnoncePrixComparator;
import fr.RivaMedia.fragments.core.FragmentListe;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.NetAnnonce;

@SuppressLint("ValidFragment")
public class AnnoncesListe extends FragmentListe implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces;

	List<NameValuePair> _donneesFormulaire;
	String _type;

	int page = 1;
	String idClient = null; 
	
	public AnnoncesListe(List<NameValuePair> donneesFormulaire, String type){
		this._donneesFormulaire = donneesFormulaire;
		this._type = type;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_annonces,container, false);
		afficherProgress(afficherProgress);

		task = new ChargerAnnoncesTask();
		task.execute();

		return _view;
	}

	public void charger(){
		_liste = (ListView)_view.findViewById(android.R.id.list);		
	}
	public void remplir(){

		if(_annonces == null || _annonces.size()==0)
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		else{
			_adapter = new AnnonceListAdapter(getActivity(), _annonces,_type);
			_liste.setAdapter(_adapter);
		}

	}
	public void ajouterListeners(){
	}




	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		}
	}


	protected void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
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
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherPrixDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherDateCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	@Override
	public void afficherDateDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}
	
	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_annonces = NetAnnonce.rechercher(_type, Integer.valueOf(page), _donneesFormulaire, idClient);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					afficherProgress = false;
					afficherProgress(afficherProgress);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
