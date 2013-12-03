
package fr.RivaMedia.tab.alertes;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import fr.RivaMedia.R;
import fr.RivaMedia.adapter.AlerteListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.Alerte;
import fr.RivaMedia.model.Annonce;
import fr.RivaMedia.net.NetAlerte;
import fr.RivaMedia.tab.core.Tab;
import fr.RivaMedia.utils.JetonManager;


@SuppressLint("ValidFragment")
public class TabMesAlertesFormulaires extends Tab {

	View  _view;
	Activity _activity;
	FragmentNormal _fragment;

	List<Alerte> _alertes = new ArrayList<Alerte>();
	SwipeListView _liste = null;
	AlerteListAdapter _adapter = null;

	AsyncTask<Void, Void, Void> task = null;

	public TabMesAlertesFormulaires(String titre, Activity activity, FragmentNormal fragment){
		super(titre,activity.getResources().getDrawable(R.drawable.logo_vendre_blanc));
		this._activity = activity;
		this._fragment = fragment;

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.liste_swipe_views, container, false);

		//task = new ChargerAlertesTask();
		//task.execute();

		return _view;
	}

	public void chargerAlertes(){
		charger();
		remplir();
		ajouterListeners();
		if(_alertes.size()==0){
			((TextView)_view.findViewById(R.id.vide).findViewById(R.id.vide_text)).setText(R.string.aucun_alerte);
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		}else{
			_view.findViewById(R.id.vide).setVisibility(View.GONE);

		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if(getActivity() != null){
			getActivity().runOnUiThread(new Runnable(){
				public void run(){
					if(task == null){
						task = new ChargerAlertesTask();
						task.execute();
					}
				}
			});
		}

	}

	public void charger(){
		_liste = (SwipeListView) _view.findViewById(R.id.list);
	}
	public void remplir(){
		_adapter = new AlerteListAdapter(getActivity(), _alertes, true);
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
			}

			@Override
			public void onClickBackView(int position) {
				new SupprimmerAlertesTask().execute(_alertes.get(position).getId());
				supprimerPosition(position);
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
			}

		});

	}

	protected void supprimerPosition(int position){
		_liste.dismiss(position);
		_liste.dismissSelected();
		_liste.unselectedChoiceStates();
	}

	public void supprimmerAlerteDeListe(String idAlerte){
		/*
		int position = 0;
		for(Alerte alerte : _alertes){
			if(alerte.getId().equals(idAlerte)){
				supprimerPosition(position);
			}
			position++;
		}
		 */
	}


	/* --------------------------------------------------------------------------- */

	class ChargerAlertesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			JetonManager jm = new JetonManager(getActivity());
			String jeton = jm.getJeton();
			_alertes.clear();
			_alertes.addAll(NetAlerte.getAlertes(jeton));
			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAlertes();
					task = null;
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

	/* --------------------------------------------------------------------------- */

	class SupprimmerAlertesTask extends AsyncTask<String, Void, Void> {
		protected Void doInBackground(String...alerteId) {
			final String idAlerte = alerteId[0];
			final String ok = NetAlerte.supprimerAlerte(idAlerte);

			if(getActivity() != null){
				getActivity().runOnUiThread(new Runnable(){

					@Override
					public void run() {
						if(!ok.toLowerCase().equals("false"))
							supprimmerAlerteDeListe(idAlerte);
						task = null;
					}

				});
			}

			return null;
		}

		protected void onPostExecute(){
		}
	}

}