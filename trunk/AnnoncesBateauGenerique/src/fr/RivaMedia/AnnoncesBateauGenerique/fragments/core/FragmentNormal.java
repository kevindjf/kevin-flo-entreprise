package fr.RivaMedia.AnnoncesBateauGenerique.fragments.core;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import fr.RivaMedia.AnnoncesBateauGenerique.R;
import fr.RivaMedia.AnnoncesBateauGenerique.activity.MainActivity;
import fr.RivaMedia.AnnoncesBateauGenerique.dialog.CallDialog;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Annonce;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Vendeur;
import fr.RivaMedia.AnnoncesBateauGenerique.model.core.Donnees;

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
	
	
	public static void afficherCouleurNormal(View...vs){
		for(View v : vs)
			afficherCouleurNormal(v);
	}
	public static void afficherCouleurNormal(View v){
		Log.e("Je color","normalement");
		v.setBackgroundColor(Donnees.parametres.getBackgroundColorUn());
	}
	public static void afficherCouleurTouch(View v){
		v.setBackgroundColor(Donnees.parametres.getBackgroundColorDeux());
	}

	public static void afficherTexteCouleurTexte(View v){
			try{
				((TextView)v).setTextColor(Donnees.parametres.getFontColorUn());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.text)).setTextColor(Donnees.parametres.getFontColorUn());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.titre)).setTextColor(Donnees.parametres.getFontColorUn());
			}catch(Exception e){
			}
	}
	public static void afficherTexteCouleurTexte(View...views){
		for(View v : views){
			afficherTexteCouleurTexte(v);
		}
	}
	public static void afficherTexteCouleurTitre(View v){
			try{
				((TextView)v).setTextColor(Donnees.parametres.getFontColorDeux());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.text)).setTextColor(Donnees.parametres.getFontColorDeux());
			}catch(Exception e){
			}
			try{
				((TextView)v.findViewById(R.id.titre)).setTextColor(Donnees.parametres.getFontColorDeux());
			}catch(Exception e){
			}
	}
	public static void afficherTexteCouleurTitre(View...views){
		for(View v : views){
			afficherTexteCouleurTitre(v);
		}
	}

	public static void selector(View v){
		selector(v,true);
	}
	public static void selector(View v, final boolean primaire){
		afficherTexteCouleurTitre(v);
		v.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("Je touche", "Je vais ou ?" + event.getAction());
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if(primaire)
						afficherCouleurTouch(v);
					else
						afficherCouleurNormal(v);
					Log.e("Je touche", "Down");
					break;

				case MotionEvent.ACTION_CANCEL:
					if(primaire)
						afficherCouleurNormal(v);
					else 
						afficherCouleurTouch(v);
					break;

				case MotionEvent.ACTION_UP:
					if(primaire)
						afficherCouleurNormal(v);
					else 
						afficherCouleurTouch(v);
					Log.e("Je touche", "Up");

					break;

				}

				return false;
			}
		});
	}
	
	

}
