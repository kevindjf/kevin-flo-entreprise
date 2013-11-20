package fr.RivaMedia.fragments;


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
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.NetAnnonce;

@SuppressLint("ValidFragment")
public class AnnoncesListe extends FragmentNormal implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces;

	String _url;
	List<NameValuePair> _donneesFormulaire;
	String _type;

	boolean afficherProgress = true;

	public AnnoncesListe(String url, List<NameValuePair> donneesFormulaire, String type){
		this._url = url;
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

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
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
			_annonces = NetAnnonce.rechercher(_url, _donneesFormulaire);

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
