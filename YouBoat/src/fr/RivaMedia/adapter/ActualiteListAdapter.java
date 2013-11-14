package fr.RivaMedia.adapter;


import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.R;
import fr.RivaMedia.view.ActualiteView;
import fr.RivaMedia.view.AnnonceView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ActualiteListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Object> _actualites;
	private List<Object> _views = new ArrayList<Object>();

	private static LayoutInflater inflater=null;

	public ActualiteListAdapter(Context context, List<Object> actualites){
		this._context = context;
		this._actualites = actualites;

		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(_actualites!=null)
			return _actualites.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return _actualites.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;

		view = inflater.inflate(R.layout.actualite_element_liste, null);

		ActualiteView av = new ActualiteView(_actualites.get(position),_context,view,position);
		_views.add(position,av);

		return view;
	}

}
