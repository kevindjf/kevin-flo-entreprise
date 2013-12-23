package fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.woozzu.android.adapter.IndexableListViewAdapter;
import com.woozzu.android.widget.IndexableListView;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Marque;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

@SuppressLint("ValidFragment")
public class MarqueSelector extends FragmentNormal implements OnItemClickListener{

	View _view;
	IndexableListView _listView;

	String _type;
	List<Marque> _marques;
	List<String> libelles;

	ItemSelectedListener _listener;
	int _reponseId;

	boolean afficherIndifferent = true;
	boolean publiees = false;
	
	public MarqueSelector (ItemSelectedListener listener, int reponseId, boolean afficherIndifferent, boolean publiees){
		this._listener = listener;
		this._reponseId = reponseId;
		this.afficherIndifferent = afficherIndifferent;
		this.publiees = publiees;
	}

	public MarqueSelector (ItemSelectedListener listener, int reponseId, boolean publiees){
		this._listener = listener;
		this._reponseId = reponseId;
		this.publiees = publiees;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_selector, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	public void charger() {
		_listView = (IndexableListView)_view.findViewById(android.R.id.list);
		_listView.setFastScrollEnabled(true);
	}

	public void remplir() {
		if(publiees)
			_marques = Donnees.marquesPubliees;
		else
			_marques = Donnees.marques;

		if(_marques != null && _marques.size()>0){
			libelles =  new ArrayList<String>();

			if(afficherIndifferent){
				String indifferent = getString(R.string.indifferent);
				libelles.add(indifferent);
			}


			for(Marque m : _marques){
				libelles.add(m.getNom());
			}

			IndexableListViewAdapter adapter = new IndexableListViewAdapter(getActivity(), R.layout.element_liste_simple,libelles);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}else
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
	}


	public void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		String s = libelles.get(position);
		String indifferent = getString(R.string.indifferent);
		
		int p=position;
		
		if(afficherIndifferent)
			p-=1;
		
		if(afficherIndifferent && s.equals(indifferent)){
			String item = "-1";
			String valeur = indifferent;
			_listener.itemSelected(this,_reponseId,item,valeur);
			getFragmentManager().popBackStack();
		}else{
			ajouterFragment(new ModeleSelector(_listener, _reponseId, _marques.get(p)));
		}
	}

}
