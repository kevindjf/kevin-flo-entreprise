package fr.RivaMedia.fragments;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AnnonceListAdapter;
import fr.RivaMedia.comparator.AnnonceDateComparator;
import fr.RivaMedia.comparator.AnnoncePrixComparator;
import fr.RivaMedia.fragments.core.FragmentListe;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.NetAnnonce;
import fr.RivaMedia.net.core.Net;
import fr.RivaMedia.utils.FavorisManager;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

public class MesAnnonces extends FragmentListe implements View.OnClickListener{

	View _view;
	View _derriere;
	View _bouton_supprimer;
	SwipeListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();
	static int  positionRetour;
	boolean afficherProgress = true;

	private FavorisManager _favorisManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_swipe_views,container, false);
		_favorisManager = new FavorisManager(getActivity());
		_derriere = _view.findViewById(R.id.derriere);
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
		getActivity().runOnUiThread(new Runnable(){
			public void run(){
				synchronized(_annonces){
					if(_adapter != null){
						for(Annonce a : _annonces){
							//TODO Trouver une autre facon de changer la listView
							if(!_favorisManager.contientFavoris(a.getNumero())){
								_annonces.remove(a);
								_adapter.notifyDataSetChanged();
								break;
							}
						}
					}
				}
			}
		});
		
	}

	public void charger(){
		_liste = (SwipeListView)_view.findViewById(R.id.list);		

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
				positionRetour = position;
				_adapter.getView(position).onClickFrontView();
			}

			@Override
			public void onClickBackView(int position) {
				int [] tabposition = {position};
				onDismiss(tabposition);
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
				for (int position : reverseSortedPositions) {
					_favorisManager.retirerFavoris(_annonces.get(position).getNumero(), _annonces.get(position).getType());
					_annonces.remove(position);
				}
				_adapter.notifyDataSetChanged();
			}

		});

	}


	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//TODO Si aucune annonce afficher message
			List<String> fav = _favorisManager.getFavois();
			if(fav.size() > 1){
				for(String favoris : fav){
					try{
						String[] ids = favoris.split(";");
						String id = ids[0];
						String type = ids[1];
						Annonce annonce = NetAnnonce.rechercherAnnonce(type, Net.construireDonnes(Constantes.ANNONCES_DETAIL_ID_ANNONCE,id));
						annonce.setType(type);
						_annonces.add(annonce);
					}catch(Exception e){}
				}
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

}