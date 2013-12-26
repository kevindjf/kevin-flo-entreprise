package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
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
			super.envoyerEmailDirect(((TextView)(contact_pro_email.findViewById(R.id.text))).getText().toString());
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
		
		if(Donnees.parametres.getImageLogo() != null)
		ImageLoaderCache.charger(Donnees.parametres.getImageLogo(),image_entreprise);
		
		if(Donnees.client.getTel1() != null)
		((TextView)(contact_pro_telephone.findViewById(R.id.text))).setText(Donnees.client.getTel1());
		
		if(Donnees.client.getEmail() != null)
		((TextView)(contact_pro_email.findViewById(R.id.text))).setText(Donnees.client.getEmail());
		
		if(Donnees.client.getHoraires() != null)
		_horaire.setText(Donnees.client.getHoraires());
		
		if(Donnees.client.getAdresse() != null)
		_adresse.setText(Donnees.client.getAdresse());
		
		if(Donnees.client.getDepartementNum() != null && Donnees.client.getVille() != null)
		_adresse_postale.setText(Donnees.client.getDepartementNum() + " " + Donnees.client.getVille());

		Geocoder coder = new Geocoder(getActivity());
		List<Address> address;

		try {
			address = coder.getFromLocationName(Donnees.client.getVille(),5);
			Address location = address.get(0);
			LatLng position = new LatLng(location.getLatitude(),location.getLongitude());

			mMap.addMarker(new MarkerOptions()
			.position(position)

			.title("JPV")
			.snippet("Population: 2,074,200")
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		}catch(Exception e){
			Log.e("Error", e.toString());
		}
	}

	@Override
	public void ajouterListeners() {
		contact_pro_telephone.setOnClickListener(this);
		contact_pro_email.setOnClickListener(this);	
	}

}
