package fr.RivaMedia.AnnoncesAutoGenerique.fragments;


import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.adapter.AnnonceListAdapter;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentListe;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesAutoGenerique.utils.FavorisManager;

public class MonGarage extends FragmentListe implements View.OnClickListener{

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
		
		cacherTrier();

		return _view;
	}

	public void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
		if(_annonces.size()==0){
			((TextView)_view.findViewById(R.id.vide).findViewById(R.id.vide_text)).setText(R.string.aucun_favoris);
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		}

		afficherProgress = false;
		afficherProgress(afficherProgress);
	}

	@Override
	public void onResume() {
		super.onResume();
		setTitre(getString(R.string.monGarage));
		afficherProgress(afficherProgress);
		getActivity().runOnUiThread(new Runnable(){
			public void run(){
				synchronized(_annonces){
					if(_adapter != null){
						int position = 0;
						for(Annonce a : _annonces){
							if(!_favorisManager.contient(a.getId())){
								_liste.dismiss(position);
								_liste.dismissSelected();
								_liste.unselectedChoiceStates();
							}
							position++;
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
		_adapter = new AnnonceListAdapter(getActivity(), _annonces,true);
		_liste.setAdapter(_adapter);
	}
	public void ajouterListeners(){
		_liste.setSwipeListViewListener(null);

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
				_favorisManager.retirer(_annonces.get(position).getId());
				_liste.dismiss(position);
				_liste.dismissSelected();
				_liste.unselectedChoiceStates();
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
			}

		});

	}


	/* --------------------------------------------------------------------------- */

	class ChargerAnnoncesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//TODO Si aucune annonce afficher message
			List<String> fav = _favorisManager.getAll();
			for(String favoris : fav){
				try{
					String id = favoris;
					Annonce annonce = NetAnnonce.getAnnonce(id);
					_annonces.add(annonce);
				}catch(Exception e){}
			}
			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					onResume();
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
	public void afficherKilometrageCroissant() {
	}

	@Override
	public void afficherKilometrageDeCroissant() {
	}

	@Override
	public void afficherAnneeCroissant() {
	}

	@Override
	public void afficherAnneeDeCroissant() {
	}
	

}
