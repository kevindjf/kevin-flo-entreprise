package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.VendeurListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Vendeur;
import fr.RivaMedia.net.NetVendeur;

@SuppressLint("ValidFragment")
public class VendeursListe extends FragmentNormal implements View.OnClickListener{

	View _view;
	LoadMoreListView _liste = null;
	VendeurListAdapter _adapter = null;
	List<Vendeur> _vendeurs = new ArrayList<Vendeur>();

	List<NameValuePair> _donneesFormulaire;

	boolean afficherProgress = true;
	
	int page = 0;
	int dernierNombre = 1;

	public VendeursListe(List<NameValuePair> donneesFormulaire){
		this._donneesFormulaire = donneesFormulaire;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_annonces,container, false);

		task = new ChargerVendeursTask();
		task.execute();

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}

	public void charger(){
		if(_liste == null)
			_liste = (LoadMoreListView)_view.findViewById(android.R.id.list);		
	}
	public void remplir(){

		if(_vendeurs == null || _vendeurs.size()==0)
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		else{
			if(_adapter == null){
				_adapter = new VendeurListAdapter(getActivity(), _vendeurs);
				_liste.setAdapter(_adapter);
			}
			else
				_adapter.notifyDataSetChanged();
		}
	}
	public void ajouterListeners(){
		
		if(dernierNombre > 0)
			_liste.setOnLoadMoreListener(new OnLoadMoreListener() {
				public void onLoadMore() {
					page+=1;
					task = new ChargerVendeursTask();
					task.execute();
				}
			});
		else
			_liste.setOnLoadMoreListener(null);
		
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

	class ChargerVendeursTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			List<Vendeur> vendeurs = NetVendeur.listeVendeurs(_donneesFormulaire);
			dernierNombre = vendeurs.size();
			_vendeurs.addAll(vendeurs);

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
