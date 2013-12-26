package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


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
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.adapter.AnnonceListAdapter;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentListe;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAnnonce;

@SuppressLint("ValidFragment")
public class AnnoncesListe extends FragmentListe implements View.OnClickListener{

	View _view;
	LoadMoreListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();

	List<NameValuePair> _donneesFormulaire;

	int debut = 0;
	int nombre = 10;

	String idClient = null; 

	public AnnoncesListe(List<NameValuePair> donneesFormulaire){
		this._donneesFormulaire = donneesFormulaire;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_annonces,container, false);
		afficherProgress(afficherProgress);

		task = new ChargerAnnoncesTask();
		task.execute();
		
		//TODO gerer type

		return _view;
	}

	public void charger(){
		if(_liste == null)
			_liste = (LoadMoreListView)_view.findViewById(android.R.id.list);		
	}
	public void remplir(){

		if(_annonces == null || _annonces.size()==0)
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		else{
			if(_adapter == null){
				_adapter = new AnnonceListAdapter(getActivity(), _annonces);
				_liste.setAdapter(_adapter);
			}else
				_adapter.notifyDataSetChanged();
		}
	}
	public void ajouterListeners(){
		if(nombre>0){
			_liste.setOnLoadMoreListener(new OnLoadMoreListener() {
				public void onLoadMore() {
					task = new ChargerAnnoncesTask();
					task.execute();
				}
			});
		}
		else{
			_liste.setOnLoadMoreListener(null);
		}
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
	}


	@Override
	public void afficherPrixDeCroissant() {
	}


	@Override
	public void afficherDateCroissant() {
	}


	@Override
	public void afficherDateDeCroissant() {
	}


	@Override
	public void afficherLongueurCroissant() {
	}


	@Override
	public void afficherLongueurDeCroissant() {
	}
	
	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			List<Annonce> annonces = NetAnnonce.getAnnonces(_donneesFormulaire,""+debut,""+nombre);
			
			nombre = annonces.size();
			debut += nombre;
			
			_annonces.addAll(annonces);

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					afficherProgress = false;
					afficherProgress(afficherProgress);

					_liste.onLoadMoreComplete();
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.resultats));
	}
}
