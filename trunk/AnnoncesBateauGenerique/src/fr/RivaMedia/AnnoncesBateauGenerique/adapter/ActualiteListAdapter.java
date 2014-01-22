package fr.RivaMedia.AnnoncesBateauGenerique.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Actualite;
import fr.RivaMedia.AnnoncesBateauGenerique.view.ActualiteView;

public class ActualiteListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Actualite> _actualites;
	private List<Object> _views = new ArrayList<Object>();

	private static LayoutInflater inflater=null;

	public ActualiteListAdapter(Context context, List<Actualite> _news){
		this._context = context;
		this._actualites = _news;

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
