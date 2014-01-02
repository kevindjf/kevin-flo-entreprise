package fr.RivaMedia.AnnoncesAutoGenerique.fragments.core;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.activity.MainActivity;
import fr.RivaMedia.AnnoncesAutoGenerique.dialog.CallDialog;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesAutoGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesAutoGenerique.model.core.Donnees;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public abstract class FragmentNormal extends Fragment implements IFragment, OnClickListener{

	protected AsyncTask<Void, Void, Void> task;
	protected boolean visible = false;
	protected boolean afficherProgress = false;


	public void afficherProgress(boolean afficher){
		this.afficherProgress = afficher;
		((MainActivity)getActivity()).afficherProgress(afficher);
	}

	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		((MainActivity)getActivity()).cacherTrier();
		((MainActivity)getActivity()).cacherFavoris();
		afficherProgress(afficherProgress);
		try{
			if(task != null)
				this.task.cancel(true);
		}
		catch(Exception e){e.printStackTrace();}
		super.onPause();
	}


	public void trackerEcran(String title){

		if(MainActivity.tracker == null)
			MainActivity.tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-46725109-1");

		// Set screen name on the tracker to be sent with all hits.
		MainActivity.tracker.set(Fields.SCREEN_NAME, title);

		MainActivity.tracker.send(MapBuilder
				.createAppView()
				.build()
				);
	}
	
	@Override
	public void onResume() {
		((MainActivity)getActivity()).cacherEffacer();
		((MainActivity)getActivity()).cacherTrier();
		((MainActivity)getActivity()).cacherFavoris();

		afficherProgress(afficherProgress);
		super.onResume();
		getView().setOnClickListener(this);
	}

	@Override
	public void onViewStateRestored(){
		Log.e("FragmentNormal","Refresh");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser == true) { 
			Log.d("VISIBLE", "this fragment is now visible");
			visible = true;
		}

		else if (isVisibleToUser == false) {  
			Log.d("VISIBLE", "this fragment is now invisible");
			visible = false;
		}
	}

	public void ajouterFragment(Fragment fragment){
		ajouterFragment( fragment, true);
	}
	public void ajouterFragment(Fragment fragment, boolean back){
		((MainActivity)getActivity()).ajouterFragment(fragment,back);
	}

	public void envoyerEmailVendeur(String email, Vendeur vendeur){
		((MainActivity)getActivity()).envoyerEmailVendeur(email, vendeur);
	}
	public void envoyerEmailAnnonce(String email, Annonce annonce){
		((MainActivity)getActivity()).envoyerEmailAnnonce(email, annonce);
	}
	public void envoyerEmailDirect(String email){
		((MainActivity)getActivity()).envoyerEmailDirect(email);
	}
	public void appeller(String phone){
		new CallDialog(getActivity(), getString(R.string.telephoner), phone).show();
	}

	@Override
	public void onClick(View v) {

	}

	public View afficherFavoris(){
		return ((MainActivity)getActivity()).afficherFavoris();
	}

	public void cacherFavoris(){
		((MainActivity)getActivity()).cacherFavoris();
	}

	public void setTitre(String titre){
		((MainActivity)getActivity()).setTitre(titre);
	}

	public static void afficherCouleurNormal(View v){
		Log.e("Je color","normalement");
		v.setBackgroundColor(Donnees.parametres.getCouleurPrincipale());
	}
	public static void afficherCouleurTouch(View v){
		v.setBackgroundColor(Donnees.parametres.getCouleurSecondaire());
	}

	public static void selector(View v){
		v.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("Je touche", "Je vais ou ?" + event.getAction());
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					afficherCouleurTouch(v);
					Log.e("Je touche", "Down");
					break;

				case MotionEvent.ACTION_CANCEL:
					afficherCouleurNormal(v);
					break;

				case MotionEvent.ACTION_UP:
					afficherCouleurNormal(v);
					Log.e("Je touche", "Up");

					break;

				}

				return false;
			}
		});
	}


}
