package fr.RivaMedia.fragments.selector;

import java.util.ArrayList;
import java.util.List;

import com.woozzu.android.adapter.IndexableListViewAdapter;
import com.woozzu.android.widget.IndexableListView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.core.Donnees;

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

	public MarqueSelector (ItemSelectedListener listener, int reponseId, String type){
		this._type = type;
		this._listener = listener;
		this._reponseId = reponseId;
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
		_marques = Donnees.getMarques(_type);

		if(_marques != null && _marques.size()>0){
			libelles =  new ArrayList<String>();

			if(afficherIndifferent){
				String indifferent = getString(R.string.indifferent);
				libelles.add(indifferent);
			}


			for(Marque m : _marques){
				libelles.add(m.getLibelle());
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
		if(afficherIndifferent && s.equals(indifferent)){
			String item = "-1";
			String valeur = indifferent;
			_listener.itemSelected(this,_reponseId,item,valeur);
			getFragmentManager().popBackStack();
		}else{
			ajouterFragment(new ModeleSelector(_listener, _reponseId, _type, _marques.get(position)));
		}
	}

}
