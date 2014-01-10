package fr.RivaMedia.AnnoncesAutoGenerique.fragments;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

	View shape_ovale;
	View button_email;
	View button_telephone;
	View contact_pro_telephone;
	View contact_pro_email;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}
		try {
			view = inflater.inflate(R.layout.contact_pro,container, false);
			charger();
			remplir();
			ajouterListeners();
			changerCouleur();
		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}

		trackerEcran("Ecran Contact Pro Android");

		return view;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id == R.id.contact_pro_button_telephone){
			afficherCouleurTouch(v);
			if(Donnees.client.getTel1() != null)
				super.appeller(Donnees.client.getTel1());
		}
		else if(id==R.id.contact_pro_button_email){
			afficherCouleurTouch(v);
			if(Donnees.client.getEmail() != null)
				super.envoyerEmailDirect(Donnees.client.getEmail());
		}
	}



	@Override
	public void charger() {
		mMapFragment = ((SupportMapFragment)getFragmentManager().findFragmentById(R.id.map));
		mMap = mMapFragment.getMap();

		shape_ovale = view.findViewById(R.id.contact_pro_shape_oval);
		button_email = view.findViewById(R.id.contact_pro_button_email);
		button_telephone = view.findViewById(R.id.contact_pro_button_telephone);

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

		if(Donnees.client.getHoraires() != null)
			_horaire.setText(Donnees.client.getHoraires());

		if(Donnees.client.getAdresse() != null)
			_adresse.setText(Donnees.client.getAdresse());

		if(Donnees.client.getDepartementNum() != null && Donnees.client.getVille() != null)
			_adresse_postale.setText(Donnees.client.getDepartementNum() + " " + Donnees.client.getVille());

		
		
		
		//Geocoder coder = new Geocoder(getActivity());
		//List<Address> address;

		try {
			//address = coder.getFromLocationName(Donnees.client.getVille(),5);
			//Address location = address.get(0);
			//LatLng position = new LatLng(location.getLatitude(),location.getLongitude());
			
			LatLng position = new LatLng(Double.parseDouble(Donnees.client.getLat()), Double.parseDouble(Donnees.client.getLng()));
			
			if(Donnees.client.getDistributeur() != null && Donnees.client.getNom() != null){
				mMap.addMarker(new MarkerOptions()
				.position(position)
				.title(Donnees.client.getNom())
				.snippet(Donnees.client.getDistributeur())
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			}else{
				mMap.addMarker(new MarkerOptions()
				.position(position)
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			}
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

			mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		}catch(Exception e){
			Log.e("Error", e.toString());
		}
		
	}

	@SuppressWarnings("deprecation")
	public void changerCouleur(){
		afficherCouleurTouch(button_email);
		afficherCouleurNormal(contact_pro_email);

		afficherCouleurTouch(button_telephone);
		afficherCouleurNormal(contact_pro_telephone);

		selector(button_email,false);
		selector(contact_pro_email);



		selector(button_telephone,false);
		selector(contact_pro_telephone);

		GradientDrawable drawable = (GradientDrawable) getResources().getDrawable(R.drawable.circle);
		drawable.setColor(Donnees.parametres.getCouleurPrincipale());
		drawable.setStroke(4 , Color.WHITE);
		drawable.setCornerRadius(270);
		shape_ovale.setBackgroundDrawable(drawable);

	}

	@Override
	public void ajouterListeners() {
		button_email.setOnClickListener(this);
		button_telephone.setOnClickListener(this);	
	}

	@Override
	public void onResume() {
		super.onResume();
		
		trackerEcran("Ecran Contact Pro Android");

		if(Donnees.client!= null && Donnees.client.getNom() != null)
			setTitre(Donnees.client.getNom());	
	}

}
