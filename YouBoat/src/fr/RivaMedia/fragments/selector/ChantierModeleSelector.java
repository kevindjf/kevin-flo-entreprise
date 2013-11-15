package fr.RivaMedia.fragments.selector;

import java.util.List;

import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.ItemSelectedListener;
import fr.RivaMedia.model.Categorie;
import fr.RivaMedia.model.core.Donnees;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//TODO n'affiche pas les bonnes données
@SuppressLint("ValidFragment")
public class ChantierModeleSelector extends Fragment implements OnItemClickListener{

	View _view;
	ListView _listView;

	String _type;
	List<Categorie> _categories;
	String[] _elements;

	ItemSelectedListener _listener;

	public ChantierModeleSelector (String type,ItemSelectedListener listener){
		this._type = type;
		this._listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		_view = inflater.inflate(R.layout.liste_recherche, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}


	private void charger() {
		_listView = (ListView)_view.findViewById(R.id.liste);
	}

	private void remplir() {
		_categories = Donnees.getCategories(_type);

		if(_categories != null){
			_elements = new String[_categories.size()];
			int i=0;
			for(Categorie c : _categories){
				_elements[i] = c.getLibelle();
				i++;
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.element_liste_simple,_elements);
			_listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}


	private void ajouterListeners() {
		_listView.setOnItemClickListener(this);		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
		_listener.itemSelected(this,_categories.get(position).getId(), _elements[position]);
		getFragmentManager().popBackStack();
	}

}
