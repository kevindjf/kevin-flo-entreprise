package fr.RivaMedia.fragments.core;

import fr.RivaMedia.activity.MainActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentNormal extends Fragment implements IFragment{

	protected AsyncTask<Void, Void, Void> task;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return null;
	}

	
	
	public void afficherProgress(boolean afficher){
		((MainActivity)getActivity()).afficherProgress(afficher);
	}
	
	@Override
	public void onPause() {
		((MainActivity)getActivity()).cacherEffacer();
		afficherProgress(false);
		try{
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
}
