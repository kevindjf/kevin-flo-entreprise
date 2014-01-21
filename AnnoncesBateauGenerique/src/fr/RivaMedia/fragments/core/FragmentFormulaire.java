package fr.RivaMedia.fragments.core;

import fr.RivaMedia.activity.MainActivity;

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