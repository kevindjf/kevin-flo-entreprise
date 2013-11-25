package fr.RivaMedia.fragments.selector;

import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import fr.RivaMedia.R;
import fr.RivaMedia.fragments.core.FragmentNormal;
import fr.RivaMedia.fragments.core.ItemSelectedListener;

@SuppressLint("ValidFragment")
public class AnnuaireLocaliteSelector extends FragmentNormal implements View.OnClickListener, OnSeekBarChangeListener{

	View _view;

	Location _location; // location
	double latitude; // latitude
	double longitude; // longitude

	MaLocationListener _locationListener = null;
	LocationManager _locationManager = null;

	View _boutonGPS;
	EditText _texteLocalite;
	EditText _rayon_texte;
	SeekBar _rayon_valeur;

	View _boutonValider;

	int _idRetour;
	ItemSelectedListener _listener;

	public AnnuaireLocaliteSelector(ItemSelectedListener listener, int idRetour){
		_listener = listener;
		_idRetour = idRetour;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.annuaire_localite, container, false);

		charger();
		remplir();
		ajouterListeners();

		return _view;
	}

	@Override
	public void charger() {
		_boutonGPS = _view.findViewById(R.id.annuaire_localite_gps);
		_texteLocalite = (EditText)_view.findViewById(R.id.annuaire_localite_localite);
		_rayon_texte = (EditText)_view.findViewById(R.id.annuaire_localite_rayon_texte);
		_rayon_valeur = (SeekBar)_view.findViewById(R.id.annuaire_localite_rayon_valeur);

		_boutonValider = _view.findViewById(R.id.annuaire_localite_valider);
	}

	@Override
	public void remplir() {
	}

	@Override
	public void ajouterListeners() {
		_boutonValider.setOnClickListener(this);
		_boutonGPS.setOnClickListener(this);
		_rayon_valeur.setOnSeekBarChangeListener(this);

	}

	public void valider(){
		if(_texteLocalite.getText() != null){
			String item = longitude+";"+latitude;
			String valeur = _rayon_texte.getText().toString()+";"+_texteLocalite.getText().toString();
			_listener.itemSelected(this,_idRetour,item,valeur);
			getFragmentManager().popBackStack();

			if(_locationManager != null && _locationListener != null)
				_locationManager.removeUpdates(_locationListener);
		}else{
			Toast.makeText(getActivity(), R.string.veuillez_choisir_une_localite, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.annuaire_localite_valider:
			valider();
			break;
		case R.id.annuaire_localite_gps:
			getLocation();
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar s) {}

	@Override
	public void onStopTrackingTouch(SeekBar s) {}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		if(progress == 0)
			_rayon_texte.setText("");
		else
			_rayon_texte.setText(progress+" km");
	}

	protected void getLocation(){

		if(_location == null){
			try{
				_locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

				if( 
						!_locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) 
						&&
						!_locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				{

					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setTitle(R.string.gps_not_found_title);  // GPS not found
					builder.setMessage(R.string.gps_not_found_message); // Want to enable?
					builder.setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialogInterface, int i) {
							startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
						}
					});
					builder.setNegativeButton(R.string.non, null);
					builder.create().show();
					return;
				}
				else{
					_locationListener = new MaLocationListener();
					_locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, _locationListener);
					_locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, _locationListener);
					System.err.println("En attente de localisation");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//remise a 0
			_location = null;
			_texteLocalite.setText("");
		}
	}

	public class MaLocationListener implements LocationListener{

		public void onLocationChanged(Location location) {
			if(getActivity() != null){
				System.err.println("Localisation trouvee");
				_location = location;
				_texteLocalite.setVisibility(View.VISIBLE);

				Geocoder gCoder = new Geocoder(getActivity());
				ArrayList<Address> addresses;
				try {
					addresses = (ArrayList<Address>) gCoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

					if (addresses != null && addresses.size() > 0) {
						_texteLocalite.setText(addresses.get(0).getLocality());
						//Toast.makeText(_activity, "position: " + addresses.get(0).getLocality(), Toast.LENGTH_SHORT).show();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//Toast.makeText(_activity, _activity.getResources().getString(R.string.location_trouvee), Toast.LENGTH_LONG).show();
				_locationManager.removeUpdates(_locationListener);	
				System.err.println("Localisation trouvee 2");
			}
		}
		public void onProviderDisabled(String location) {}
		public void onProviderEnabled(String arg0) {}
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
	}

	public void onPause(){
		if(_locationManager != null && _locationListener != null)
			_locationManager.removeUpdates(_locationListener);
		super.onPause();
	}

	public void onResume(){
		if(_locationManager != null && _locationListener != null)
			_locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, _locationListener);
		super.onResume();
	}




}
