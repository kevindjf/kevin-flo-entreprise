package fr.RivaMedia.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.RivaMedia.R;
import fr.RivaMedia.model.Alerte;
import fr.RivaMedia.view.AlerteView;

public class AlerteListAdapter extends BaseAdapter  {

	private Context _context;
	private List<Alerte> _alertes;
	private List<AlerteView> _views = new ArrayList<AlerteView>();

	private static LayoutInflater inflater=null;
	private boolean _swipable = false;

	public AlerteListAdapter(Context context, List<Alerte> alertes, boolean swipable){
		this._context = context;
		this._alertes = alertes;
		this._swipable = swipable;

		try{

			if(_context != null){
				inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}

		}catch(Exception e){

		}
	}

	public AlerteListAdapter(Context context, List<Alerte> alertes){
		this(context,alertes,false);
	}

	@Override
	public int getCount() {
		if(_alertes!=null)
			return _alertes.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return _alertes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public AlerteView getView(int position){
		return _views.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=null;

		if(inflater != null){
			view = inflater.inflate(R.layout.alerte_element_liste_swipable, null);
			AlerteView av = new AlerteView(_alertes.get(position),_context,view,position,_swipable);
			_views.add(position,av);
		}

		return view;
	}

}
