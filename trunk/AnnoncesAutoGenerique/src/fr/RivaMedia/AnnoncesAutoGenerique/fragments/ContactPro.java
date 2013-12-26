package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.fragments.core.FragmentNormal;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;

public class ContactPro extends FragmentNormal implements View.OnClickListener{

	private GoogleMap mMap;
	private SupportMapFragment mMapFragment;
	Marker localMarker = null;

	View view;

	ImageView image_entreprise;

	TextView _horaire;
	TextView _adresse;
	TextView _adresse_postale;


	View contact_pro_telephone;
	View contact_pro_email;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.contact_pro,container, false);
		charger();
		remplir();
		ajouterListeners();
		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.contact_pro_telephone :
			super.appeller(((TextView)(contact_pro_telephone.findViewById(R.id.text))).getText().toString());
			break;

		case R.id.contact_pro_email :
			super.envoyerEmailDirect(getString(R.string.EMAIL_RIVAMEDIA));
			break;
		}
	}

	@Override
	public void charger() {
		mMapFragment = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map));
		mMap = mMapFragment.getMap();
		contact_pro_telephone = view.findViewById(R.id.contact_pro_telephone);
		contact_pro_email = view.findViewById(R.id.contact_pro_email);
		image_entreprise = (ImageView) view.findViewById(R.id.logo_entreprise);
		_horaire = (TextView) view.findViewById(R.id.horaire);
		_adresse = (TextView) view.findViewById(R.id.adresse);
		_adresse_postale = (TextView) view.findViewById(R.id.adresse_postale);

	}

	@Override
	public void remplir() {
		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(),image_entreprise );
		((TextView)(contact_pro_telephone.findViewById(R.id.text))).setText(Donnees.client.getTel1());
		((TextView)(contact_pro_email.findViewById(R.id.text))).setText(Donnees.client.getEmail());
		_horaire.setText(Donnees.client.getHoraires());
		_adresse.setText(Donnees.client.getAdresse());
		_adresse_postale.setText(Donnees.client.getDepartement() + " " + Donnees.client.getVille());
	}

	@Override
	public void ajouterListeners() {
		contact_pro_telephone.setOnClickListener(this);
		contact_pro_email.setOnClickListener(this);	
	}

}
