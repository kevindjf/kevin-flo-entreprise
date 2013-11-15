package fr.RivaMedia.fragments;


import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.factory.BateauFactory;
import fr.RivaMedia.net.NetNews;
import fr.RivaMedia.net.NetRecherche;

public class AnnoncesListe extends Fragment implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Object> _annonces;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_liste,container, false);

		new ChargerAnnoncesTask().execute();
		
		return _view;
	}

	protected void charger(){
		_liste = (ListView)_view.findViewById(R.id.annonces_liste_listview);		
	}
	protected void remplir(){
		_adapter = new AnnonceListAdapter(getActivity(), _annonces);
		_liste.setAdapter(_adapter);
	}
	protected void ajouterListeners(){
	}


	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
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
			_annonces = NetRecherche.chargerListeBateaux();

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
