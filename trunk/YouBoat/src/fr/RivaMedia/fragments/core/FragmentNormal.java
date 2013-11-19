package fr.RivaMedia.fragments.core;

import fr.RivaMedia.R;
import fr.RivaMedia.activity.MainActivity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public abstract class FragmentNormal extends Fragment implements IFragment{

	protected AsyncTask<Void, Void, Void> task;
	protected boolean visible = false;
	
	public void afficherProgress(boolean afficher){
		((MainActivity)getActivity()).afficherProgress(afficher);
	}
	
	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		afficherProgress(false);
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
		afficherProgress(false);
		super.onResume();
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
}
