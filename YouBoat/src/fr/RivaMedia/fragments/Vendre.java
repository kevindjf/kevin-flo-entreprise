package fr.RivaMedia.fragments;

import fr.RivaMedia.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Vendre extends Fragment implements View.OnClickListener{

	private View _boutonBateaux;
	private View _boutonMoteurs;
	private View _boutonDivers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.vendre,container, false);

		_boutonBateaux = view.findViewById(R.id.vendre_bateaux);
		_boutonMoteurs = view.findViewById(R.id.vendre_moteurs);
		_boutonDivers = view.findViewById(R.id.vendre_divers);

		_boutonBateaux.setOnClickListener(this);
		_boutonMoteurs.setOnClickListener(this);
		_boutonDivers.setOnClickListener(this);

		_boutonBateaux.setSelected(true);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.vendre_bateaux:
			vendreBateaux();
			break;
		case R.id.vendre_moteurs:
			vendreMoteurs();
			break;
		case R.id.vendre_divers:
			vendreDivers();
			break;
		}
	}

	public void vendreBateaux(){
		_boutonBateaux.setSelected(true);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(false);

	}
	public void vendreMoteurs(){
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(false);
		_boutonMoteurs.setSelected(true);
	}
	public void vendreDivers(){
		_boutonBateaux.setSelected(false);
		_boutonDivers.setSelected(true);
		_boutonMoteurs.setSelected(false);
	}

}
