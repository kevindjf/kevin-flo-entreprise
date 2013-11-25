package fr.RivaMedia.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.ActualiteListAdapter;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.model.News;
import fr.RivaMedia.net.NetNews;

public class Actualites extends FragmentNormal{

	View _view;
	ListView _liste = null;
	ActualiteListAdapter _adapter = null;
	
	List<News> _news = null;
	
	boolean afficherProgress = true;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.actualites, container, false);
		
		afficherProgress(true);
		
		task = new ChargerNewsTask();
		task.execute();
		
		return _view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}
	
	
	protected void chargerNews(){
		charger();
		remplir();
		ajouterListeners();
	}
	
	public void charger(){
		_liste = (ListView)_view.findViewById(R.id.actualites_liste_listview);		
	}
	public void remplir(){
		_adapter = new ActualiteListAdapter(getActivity(), _news);
		_liste.setAdapter(_adapter);
	}
	public void ajouterListeners(){
	}

	
	/* --------------------------------------------------------------------------- */

	class ChargerNewsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_news = NetNews.chargerListeNews();
			
			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerNews();
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
