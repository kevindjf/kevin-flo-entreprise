package fr.RivaMedia.AnnoncesAutoGenerique.fragments.core;

import fr.RivaMedia.AnnoncesAutoGenerique.activity.MainActivity;

//TODO: n'arriche pas "effacer" en cas de popStack

public abstract class FragmentFormulaire extends FragmentNormal implements Effaceable{
	
	@Override
	public void onPause() {
		super.onPause();
		((MainActivity)getActivity()).cacherEffacer();
	}

	@Override
	public void onResume() {
		super.onResume();
		((MainActivity)getActivity()).afficherEffacer(this);
	}
	
}