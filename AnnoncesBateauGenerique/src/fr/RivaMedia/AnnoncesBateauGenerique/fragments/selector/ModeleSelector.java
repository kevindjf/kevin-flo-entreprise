package fr.RivaMedia.AnnoncesBateauGenerique.fragments.selector;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.MainActivity;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.core.BateauFragmentActivity;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesBateauGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Marque;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Modele;
import fr.RivaMedia.AnnoncesBateauGenerique.net.NetChargement;

@SuppressLint("ValidFragment")
public class ModeleSelector extends FragmentNormal implements OnItemClickListener{

	View _view;
	ListView _listView;

	String _type;
	Marque _marque;

	ItemSelectedListener _listener;
	int _reponseId;

	boolean afficherProgress = true;
	boolean WA = true;

	public ModeleSelector (ItemSelectedListener listener, int reponseId, String type, Marque marque, boolean WA){
		this._type = type;
		this._listener = listener;
		this._reponseId = reponseId;
		this._marque = marque;
		this.WA = WA;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_selector, container, false);

		if(_marque.getModeles() == null){
			((MainActivity)getActivity()).afficherProgress(true);
			task = new ChargerModelesTask();
			task.execute();
		}else{
			chargerVue();
		}

		return _view;
	}

	private void chargerVue(){
		charger();
		remplir();
		ajouterListeners();
	}

	public void charger() {
		_listView = (ListView)_view.findViewById(android.R.id.list);
	}

	public void remplir() {

		List<Modele> _modeles = _marque.getModeles();

		if(_modeles != null && _modeles.size()>0){
			List<String> libelles =  new ArrayList<String>();

			for(Modele m : _modeles){
				libelles.add(m.getLibelle());
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,libelles);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}else
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
	}


	public void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}

	@Override
	public void onResume() {
		super.onResume();
		afficherProgress(afficherProgress);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		Modele modele = _marque.getModeles().get(position);
		_listener.itemSelected(this,_reponseId,_marque.getId()+";"+modele.getId(), _marque.getLibelle()+" / "+modele.getLibelle());
		((BateauFragmentActivity)getActivity()).retirerFragment();
		((BateauFragmentActivity)getActivity()).retirerFragment();
	}

	class ChargerModelesTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {
			//tests
			_marque.setModeles(NetChargement.chargerModeles(_marque.getId(),_type,null,WA));
			afficherProgress = false;

			getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run() {
					chargerVue();
					afficherProgress(afficherProgress);			
					

				}

			});

			
			return null;
		}

		protected void onPostExecute(){
		}
	}

}
