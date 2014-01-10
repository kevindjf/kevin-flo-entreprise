package fr.RivaMedia.AnnoncesAutoGenerique.fragments.core;

import fr.RivaMedia.AnnoncesAutoGenerique.activity.MainActivityAnnoncesAuto;


//TODO: n'arriche pas "effacer" en cas de popStack

public abstract class FragmentFormulaire extends FragmentNormal implements Effaceable{
	
	@Override
	public void onPause() {
		super.onPause();
		((MainActivityAnnoncesAuto)getActivity()).cacherEffacer();
	}

	@Override
	public void onResume() {
		super.onResume();
		((MainActivityAnnoncesAuto)getActivity()).afficherEffacer(this);
	}
	
}