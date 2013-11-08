package fr.RivaMedia.tab.core;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagesAdapter extends FragmentStatePagerAdapter {

	List<Tab> _pages = null;
	
	public PagesAdapter(FragmentManager fm, List<Tab> pages) {
		super(fm);
		this._pages = pages;
	}
	
	public Fragment getItem(int position) {
		System.out.println(_pages);
		return _pages.get(position);
	}

	public int getCount() {
		return _pages.size();
	}

	public CharSequence getPageTitle(int position) {
		return _pages.get(position).getTitle();
	}
}
