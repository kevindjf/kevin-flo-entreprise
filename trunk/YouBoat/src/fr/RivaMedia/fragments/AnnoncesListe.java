package fr.RivaMedia.fragments;


import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.factory.BateauFactory;
import fr.RivaMedia.net.NetNews;
import fr.RivaMedia.net.NetRecherche;

@SuppressLint("ValidFragment")
public class AnnoncesListe extends Fragment implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Object> _annonces;
	
	String _url;
	List<NameValuePair> _donneesFormulaire;
	String _type;
	
	public AnnoncesListe(String url, List<NameValuePair> donneesFormulaire, String type){
		this._url = url;
		this._donneesFormulaire = donneesFormulaire;
		this._type = type;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annonces_liste,container, false);

		((MainActivity)getActivity()).afficherProgress(true);
		new ChargerAnnoncesTask().execute();
		
		return _view;
	}

	protected void charger(){
		_liste = (ListView)_view.findViewById(R.id.annonces_liste_listview);		
	}
	protected void remplir(){
		_adapter = new AnnonceListAdapter(getActivity(), _annonces,_type);
		_liste.setAdapter(_adapter);
		Log.e("AnnoncesListe","Test");
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
			_annonces = NetRecherche.rechercher(_url, _donneesFormulaire);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					((MainActivity)getActivity()).afficherProgress(false);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

}
