package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.NetAnnonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.utils.FavorisManager;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;


public class MesAnnonces extends FragmentNormal implements View.OnClickListener{

	View _view;
	SwipeListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();

	boolean afficherProgress = true;

	private FavorisManager _favorisManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_swipe_views,container, false);
		_favorisManager = new FavorisManager(getActivity());

		task = new ChargerAnnoncesTask();
		task.execute();


		return _view;
	}

	public void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}

	public void charger(){
		_liste = (SwipeListView)_view.findViewById(android.R.id.list);		
	}
	public void remplir(){
		_adapter = new AnnonceListAdapter(getActivity(), _annonces,null,true);
		_liste.setAdapter(_adapter);
	}
	public void ajouterListeners(){

		_liste.setSwipeListViewListener(new BaseSwipeListViewListener() {
			@Override
			public void onOpened(int position, boolean toRight) {
			}

			@Override
			public void onClosed(int position, boolean fromRight) {
			}

			@Override
			public void onListChanged() {
			}

			@Override
			public void onMove(int position, float x) {
			}

			@Override
			public void onStartOpen(int position, int action, boolean right) {
				Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
			}

			@Override
			public void onStartClose(int position, boolean right) {
				Log.d("swipe", String.format("onStartClose %d", position));
			}

			@Override
			public void onClickFrontView(int position) {
				Log.d("swipe", String.format("onClickFrontView %d", position));
				_adapter.getView(position).onClickFrontView();
			}

			@Override
			public void onClickBackView(int position) {
				Log.d("swipe", String.format("onClickBackView %d", position));
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
				for (int position : reverseSortedPositions) {
					_annonces.remove(position);
				}
				_adapter.notifyDataSetChanged();
			}

		});

	}




	@Override
	public void onClick(View v) {
		switch(v.getId()){
		}
	}

	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			for(String favoris : _favorisManager.getFavois()){
				try{
					String[] ids = favoris.split(";");
					String id = ids[0];
					String type = ids[1];
					Annonce annonce = NetAnnonce.rechercherAnnonce(type, Net.construireDonnes(Constantes.ANNONCES_DETAIL_ID_ANNONCE,id));
					annonce.setType(type);
					_annonces.add(annonce);
				}catch(Exception e){}
			}

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
