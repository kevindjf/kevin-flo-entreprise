package fr.RivaMedia.tab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.RivaMedia.R;
import fr.RivaMedia.activity.Alertes;
import fr.RivaMedia.activity.AnnoncesFormulaire;
import fr.RivaMedia.tab.core.Tab;

@SuppressLint("ValidFragment")
public class MoreTab extends Tab implements View.OnClickListener{

	private View _buttonMore;

	public MoreTab(String titre,Drawable icon) {
		super(titre,icon);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.more, container, false);
		_buttonMore = v.findViewById(R.id.more_alerte);

		return v;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.more_alerte:
			afficherAlerte();
			break;
		}
	}
	
	public void afficherAlerte(){
		Intent i = new Intent(getActivity(),Alertes.class);
		getActivity().startActivity(i);
	}

}
