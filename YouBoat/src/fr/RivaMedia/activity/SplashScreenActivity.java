package fr.RivaMedia.activity;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import fr.RivaMedia.Constantes;
import fr.RivaMedia.R;
import fr.RivaMedia.image.ImageLoaderCache;
import fr.RivaMedia.model.Marque;
import fr.RivaMedia.model.Service;
import fr.RivaMedia.model.core.Donnees;
import fr.RivaMedia.net.NetChargement;
import fr.RivaMedia.net.NetNews;
import fr.RivaMedia.net.core.Net;

public class SplashScreenActivity extends Activity{

	public static final int tempsAttenteSecondes = 3;
	
	public static final String TAG = "PLAY";
	
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "AIzaSyB7IT3ndRmf9j_J94Y79Znyl1OptMp3e_s";
    private static final String PROPERTY_APP_VERSION = "1";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "335007548801";
    
    Context context;    
    GoogleCloudMessaging gcm; 
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    String regid;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Net.enableHttpResponseCache(this);
		
		context = getApplicationContext();
		
		
		if(isOnline()){
			enregistrerCloudMessaginClient();
			
			Log.e("PLAY_ID", regid);

			ImageLoaderCache.load(this);
			ImageLoaderCache.charger(Constantes.URL_PUB_MAGASINE_HD, new ImageView(this));

			new ChargementsTask().execute();
		}
		else{
			Toast.makeText(this, getString(R.string.erreur_connexion), Toast.LENGTH_LONG).show();
			new Thread(new Runnable(){

				@Override
				public void run() {

					try {
						Thread.sleep(tempsAttenteSecondes*1000);

						runOnUiThread(new Runnable(){

							@Override
							public void run() {
								finish();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}).start();


		}
	}

	public boolean isOnline() {
		ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	protected void etapeSuivante(){

		Intent i = new Intent(this,MagasineActivity.class);
		startActivity(i);
		finish();

		overridePendingTransition(R.anim.entrer, R.anim.sortir); 

	}

	protected void lancerDecompte(){
		new Thread(new Runnable(){

			@Override
			public void run() {

				try {
					Thread.sleep(tempsAttenteSecondes*1000);

					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							etapeSuivante();

						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}).start();
	}

	/* --------------------------------------------------------------------------- */

	class ChargementsTask extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void...donnees) {

			Donnees.types = NetChargement.chargerTypes();

			final List<Marque> toutesMarques = NetChargement.chargerMarquesBateaux();
			final List<Marque> marquesBateauxAMoteur = NetChargement.chargerMarquesBateauAMoteur();
			final List<Marque> marquesVoilier = NetChargement.chargerMarquesVoiliers();	
			final List<Marque> marquesPneu = NetChargement.chargerMarquesPneu();
			final List<Marque> marquesMoteur = NetChargement.chargerMarquesMoteurs();
			final Map<String,Integer> nbAnnonces = NetChargement.chargerNbAnnonces();

			Donnees.toutesMarques = toutesMarques;
			Donnees.marques.put("0", toutesMarques);
			Donnees.marques.put(Constantes.BATEAU_A_MOTEUR, marquesBateauxAMoteur);
			Donnees.marques.put(Constantes.VOILIER, marquesVoilier);
			Donnees.marques.put(Constantes.PNEU, marquesPneu);
			Donnees.marques.put(Constantes.MOTEURS, marquesMoteur);
			Donnees.nbAnnonces = nbAnnonces;

			final List<Service> services = NetChargement.chargerServices();
			Donnees.services = services;

			//tests
			Donnees.news = NetNews.chargerListeNews();



			lancerDecompte();
			return null;
		}

		protected void onPostExecute(){
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@SuppressLint("NewApi")
	protected void onStop() {
		super.onStop();
		HttpResponseCache cache = HttpResponseCache.getInstalled();
		if (cache != null) {
			cache.flush();
		}
	}
	
	//-----------------------------------GoogleCloudMessaging----------------------------------
	
	private void enregistrerCloudMessaginClient() {
		// Check device for Play Services APK. If check succeeds, proceed with
        //  GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(getApplicationContext());
            Log.e(TAG, "regid :"+regid);
            
            if (regid.length() == 0) {
                registerInBackground();
            }
        } else {
            Log.i("PLAY", "No valid Google Play Services APK found.");
        }
	}
	

    @Override
    protected void onResume() {
        super.onResume();
        // Check device for Play Services APK.
        checkPlayServices();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.length() == 0) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        
        Log.d("PLAY","registrationId: "+registrationId);
        
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
            	Log.e(TAG, "registerInBackground");
                String msg = "";
                try {
                    if (gcm == null) {
                       	Log.e(TAG, "gcm = null");
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                	Log.e(TAG, "register gcm");
                    regid = gcm.register(SENDER_ID);
                    Log.e(TAG, "ok");
                    msg = "Device registered, registration ID=" + regid;
                    
                	Log.e(TAG, msg);

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                
            }
        }.execute(null, null, null);
    }


    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(SplashScreenActivity.class.getSimpleName(),Context.MODE_PRIVATE);
    }
    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to your app. Not needed for this demo since the device sends upstream messages
     * to a server that echoes back the message using the 'from' address in the message.
     */
    private void sendRegistrationIdToBackend() {
    	// Your implementation here.
    	Log.d("PLAY_ID",regid);
    }

}
