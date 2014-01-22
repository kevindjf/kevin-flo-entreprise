
package fr.RivaMedia.AnnoncesBateauGenerique.tab.alertes;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.adapter.AnnonceListAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnonceDateComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnoncePrixComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.comparator.AnnoncePrixParLongueurComparator;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.MesAlertes;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetAnnonce;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;
import fr.RivaMedia.AnnoncesBateauGenerique.tab.core.Tab;
import fr.RivaMedia.AnnoncesBateauGenerique.utils.AlertesManager;


@SuppressLint("ValidFragment") 
public class TabMesAlertesAnnonces extends Tab {

	View  _view;
	Activity _activity;
	MesAlertes _fragment;

	private AlertesManager _alertesManager;
	View _bouton_supprimer;
	SwipeListView _liste = null;
	AnnonceListAdapter _adapter = null;
	List<Annonce> _annonces = new ArrayList<Annonce>();
	static int  positionRetour;
	boolean afficherProgress = true;

	AsyncTask<Void, Void, Void> task = null;

	public TabMesAlertesAnnonces(String titre, Activity activity, MesAlertes fragment){
		super(titre,activity.getResources().getDrawable(R.drawable.logo_vendre_blanc));
		this._activity = activity;
		this._fragment = fragment;
		
		_alertesManager = new AlertesManager(_activity);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_swipe_views, container, false);

		if(task == null){
			_fragment.afficherProgressAnnonces(true);
			task = new ChargerAnnoncesTask();
			task.execute();
		}

		return _view;
	}
	

	public void chargerAnnonces(){
		charger();
		remplir();
		ajouterListeners();
		if(_annonces.size()==0){
			((TextView)_view.findViewById(R.id.vide).findViewById(R.id.vide_text)).setText(R.string.aucun_alerte);
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		_fragment.afficherProgress(afficherProgress);
		_activity.runOnUiThread(new Runnable(){
			public void run(){
				synchronized(_annonces){
					if(_adapter != null){
						int position = 0;
						for(Annonce a : _annonces){
							if(!_alertesManager.contient(a.getNumero())){
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
		_adapter = new AnnonceListAdapter(_activity, _annonces,null,true);
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
				_alertesManager.retirer(_annonces.get(position).getNumero(), _annonces.get(position).getType());
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
			List<String> alertes = _alertesManager.getAll();
			for(String favoris : alertes){
				try{
					String[] ids = favoris.split(";");
					String id = ids[0];
					String type = ids[1];
					Annonce annonce = NetAnnonce.rechercherAnnonce(type, Net.construireDonnes(Constantes.ANNONCES_DETAIL_ID_ANNONCE,id));
					annonce.setType(type);
					_annonces.add(annonce);
				}catch(Exception e){}
			}
			_activity.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerAnnonces();
					afficherProgress = false;
					_fragment.afficherProgress(afficherProgress);
					_fragment.afficherProgressAnnonces(false);
				}

			});

			return null;
		}

		protected void onPostExecute(){
		}
	}

	public void afficherPrixCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	public void afficherPrixDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}


	public void afficherDateCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	public void afficherDateDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnonceDateComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}
	
	public void afficherLongueurCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixParLongueurComparator(true));
			_adapter.notifyDataSetChanged();
		}
	}


	public void afficherLongueurDeCroissant() {
		if(_adapter != null && _annonces != null){
			Collections.sort(_annonces,new AnnoncePrixParLongueurComparator(false));
			_adapter.notifyDataSetChanged();
		}
	}

}