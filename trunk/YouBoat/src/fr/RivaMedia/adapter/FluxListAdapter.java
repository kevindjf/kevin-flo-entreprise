package fr.RivaMedia.adapter;

/*
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AnnonceListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Object> _annonces;
	private List<Object> _views = new ArrayList<Object>();

	private static LayoutInflater inflater=null;

	public AnnonceListAdapter(Context context, List<Object> annonces){
		this._context = context;
		this._annonces = annonces;

		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(_annonces!=null)
			return _annonces.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return _annonces.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;
		
		Object element = _annonces.get(position);
		if(element instanceof Annonce)
			view = inflater.inflate(R.layout.liste_element_annonce, null);

			AnnonceView av = new AnnonceView((Annonce) element,_context,view,position);
			_views.add(position,av);
		}
	
		return view;
	}

}
*/
