package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.ArrayList;
import java.util.List;

import com.costum.android.widget.LoadMoreListView;
import com.costum.android.widget.LoadMoreListView.OnLoadMoreListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.adapter.ActualiteListAdapter;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetActualite;

public class Actualites extends FragmentNormal{

	View _view;
	LoadMoreListView _liste = null;
	ActualiteListAdapter _adapter = null;
	List<Actualite> _actualites = new ArrayList<Actualite>();

	boolean afficherProgress = true;

	int debut = 0;
	int nombre = 10;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.actualites, container, false);

		afficherProgress(true);
		((TextView)_view.findViewById(R.id.vide_text)).setText(getActivity().getString(R.string.vous_n_avez_aucune_actualite));

		if(task == null){
			task = new ChargerActualitesTask();
			task.execute();
		}

		return _view;
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
		setTitre(getString(R.string.actualites));
	}


	protected void chargerNews(){
		charger();
		remplir();
		ajouterListeners();
		chargerCouleurs();
	}

	public void chargerCouleurs(){
		ImageLoaderCache.charger(Donnees.parametres.getImageFond(), (ImageView)_view.findViewById(R.id.fond));
	}

	public void charger(){
		if(_liste == null)
			_liste = (LoadMoreListView)_view.findViewById(R.id.actualites_liste_listview);		
	}
	public void remplir(){
		if(_adapter == null){
			_adapter = new ActualiteListAdapter(getActivity(), _actualites);
			_liste.setAdapter(_adapter);
		}
		else
			_adapter.notifyDataSetChanged();

		if(_actualites.isEmpty()){
			((TextView)_view.findViewById(R.id.vide).findViewById(R.id.vide_text)).setText(R.string.aucun_favoris);
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		}
		else
			_view.findViewById(R.id.vide).setVisibility(View.GONE);

	}
	public void ajouterListeners(){
		/*
		_liste.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				debut = 0;
				nombre = 10;
				_actualites.clear();
				_adapter.notifyDataSetChanged();
				task = new ChargerActualitesTask();
				task.execute();
			}
		});
		 */

		if(debut > 0)
			_liste.setOnLoadMoreListener(new OnLoadMoreListener() {
				public void onLoadMore() {
					task = new ChargerActualitesTask();
					task.execute();
				}
			});
		else
			_liste.setOnLoadMoreListener(null);
	}


	/* --------------------------------------------------------------------------- */

	class ChargerActualitesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			try{
				List<Actualite> actus = NetActualite.chargerListeActualites(debut, nombre);

				nombre = actus.size();
				debut+=nombre;

				_actualites.addAll(actus);

				getActivity().runOnUiThread(new Runnable(){

					@Override
					public void run() {
						chargerNews();
						afficherProgress = false;
						afficherProgress(afficherProgress);

						//_liste.onRefreshComplete();
						_liste.onLoadMoreComplete();
					}

				});
			}catch(Exception e){
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}



}
