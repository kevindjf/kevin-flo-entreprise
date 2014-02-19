package fr.RivaMedia.AnnoncesBateauGenerique.activity.core;

import java.util.LinkedList;

import fr.RivaMedia.AnnoncesBateauGenerique.R;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.View;

public abstract class BateauFragmentActivity extends FragmentActivity
implements View.OnClickListener, OnBackStackChangedListener
{

	static LinkedList<Fragment> fragments = new LinkedList<Fragment>();

	boolean destroy = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		onResume();
	}

	@Override
	protected void onStop() {
		try {
			super.onStop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void ajouterVues();

	protected abstract void charger();

	protected void ajouterListeners() {
		getSupportFragmentManager().addOnBackStackChangedListener(this);
	}

	public void ajouterFragment(Fragment fragment) {
		ajouterFragment(fragment, true);
	}

	public void retirerFragment(final int nombre) {
		runOnUiThread(new Runnable() {
			public void run() {
				for (int i = 0; i < nombre; ++i) {
					try {
						Fragment fragment = fragments.removeLast();
						fragment.onPause();

						FragmentTransaction transaction = getSupportFragmentManager()
								.beginTransaction();
						transaction.remove(fragment);
						transaction.commit();

						getSupportFragmentManager().executePendingTransactions();


					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				fragments.getLast().onResume();
			}
		});
	}

	public void retirerFragment() {
		retirerFragment(1);
	}

	public void ajouterFragment(final Fragment fragment, final boolean back) {

		runOnUiThread(new Runnable() {
			public void run() {
				if (!back)
					fragments.clear();
				
				fragments.addLast(fragment);
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

				FragmentManager manager = getSupportFragmentManager();
				Fragment currFrag = (Fragment)manager.findFragmentById(R.id.fragment_container);

				//transaction.hide(getSupportFragmentManager().findFragmentById(R.id.main_fragment));
				transaction.add(R.id.fragment_container, fragment);

				transaction.commit();
			}
		});

	}

	public void onBackStackChanged() {
		FragmentManager manager = getSupportFragmentManager();

		if (manager != null) {
			try {
				Fragment currFrag = (Fragment) manager
						.findFragmentById(R.id.fragment_container);

				currFrag.onResume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void quitter() {
		destroy = true;
		this.finish();
	}

	@Override
	public void onBackPressed() {
		FragmentManager manager = getSupportFragmentManager();

		if (manager != null) {
			retirerFragment();

			System.out.println(fragments);
			if (fragments.size() == 0) {
				System.out.println("quitter : "+fragments);
				quitter();
			}
		}
	}

	public static LinkedList<Fragment> getFragments() {
		return fragments;
	}

	public static void setFragments(LinkedList<Fragment> fragments) {
		BateauFragmentActivity.fragments = fragments;
	}

	@Override
	protected void onPause() {
		try {
			super.onPause();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		try {
			super.onDestroy();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (destroy)
			android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void onClick(View v) {

	}

}