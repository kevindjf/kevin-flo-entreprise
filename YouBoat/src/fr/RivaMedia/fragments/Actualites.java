package fr.RivaMedia.fragments;

import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.RivaMedia.R;
import fr.RivaMedia.adapter.ActualiteListAdapter;
import fr.RivaMedia.model.News;
import fr.RivaMedia.net.NetNews;

public class Actualites extends Fragment{

	View _view;
	ListView _liste = null;
	ActualiteListAdapter _adapter = null;
	
	List<News> _news = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		_view = inflater.inflate(R.layout.actualites, container, false);
		
		new ChargerNewsTask().execute();
		
		return _view;
	}
	
	
	protected void chargerNews(){
		charger();
		remplir();
		ajouterListeners();
	}
	
	protected void charger(){
		_liste = (ListView)_view.findViewById(R.id.actualites_liste_listview);		
	}
	protected void remplir(){
		_adapter = new ActualiteListAdapter(getActivity(), _news);
		_liste.setAdapter(_adapter);
	}
	protected void ajouterListeners(){
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
				}
				
			});
			
			return null;
		}

		protected void onPostExecute(){
		}
	}
}
