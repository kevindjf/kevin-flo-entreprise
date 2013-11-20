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
import fr.RivaMedia.adapter.VendeurListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.net.NetVendeur;

@SuppressLint("ValidFragment")
public class VendeursListe extends FragmentNormal implements View.OnClickListener{

	View _view;
	ListView _liste = null;
	VendeurListAdapter _adapter = null;
	List<Vendeur> _vendeurs;

	List<NameValuePair> _donneesFormulaire;

	boolean afficherProgress = true;

	public VendeursListe(List<NameValuePair> donneesFormulaire){
		this._donneesFormulaire = donneesFormulaire;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_annonces,container, false);

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

		if(_vendeurs == null || _vendeurs.size()==0)
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		else{

			_adapter = new VendeurListAdapter(getActivity(), _vendeurs);
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


	protected void chargerVendeurs(){
		charger();
		remplir();
		ajouterListeners();
	}

	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_vendeurs = NetVendeur.listeVendeurs(_donneesFormulaire);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerVendeurs();
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
