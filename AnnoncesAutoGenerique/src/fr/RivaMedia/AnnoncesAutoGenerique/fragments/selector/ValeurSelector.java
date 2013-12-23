package fr.RivaMedia.AnnoncesAutoGenerique.fragments.selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.ItemSelectedListener;

@SuppressLint("ValidFragment")
public class ValeurSelector extends FragmentNormal implements OnItemClickListener{

	View _view;
	IndexableListView _listView;

	List<String> _valeurs;

	ItemSelectedListener _listener;

	String[] _stringArray;
	int _idRetour;

	public ValeurSelector (ItemSelectedListener listener, int idRetour, String[] stringArray){
		this._listener = listener;
		this._stringArray = stringArray;
		this._idRetour = idRetour;
	}
	
	public ValeurSelector (ItemSelectedListener listener, int idRetour, List<String> strings){
		this._listener = listener;
		_stringArray = new String[strings.size()];
		for(int i=0;i<strings.size();++i)
			_stringArray[i] = strings.get(i);
		this._idRetour = idRetour;
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
		_valeurs = new ArrayList<String>(Arrays.asList(_stringArray));
		if(_valeurs != null && _valeurs.size()>0){
			Collections.sort(_valeurs);

			IndexableListViewAdapter adapter = new IndexableListViewAdapter(getActivity(), R.layout.element_liste_simple,_valeurs);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		else
			_view.findViewById(R.id.vide).setVisibility(View.VISIBLE);
	}


	public void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		String valeur = _valeurs.get(position);
		_listener.itemSelected(ValeurSelector.this,_idRetour,valeur,valeur);
		getFragmentManager().popBackStack();
	}

}
