package fr.RivaMedia.activity;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.R;
import fr.RivaMedia.tab.*;
import fr.RivaMedia.tab.core.*;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements OnClickListener{

	PagesAdapter _pagesAdapter;
	ViewPager _page;
	List<Tab> _pages = new ArrayList<Tab>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		charger();
	}
	
	protected void charger(){
		_pages.add(new AnnoncesTab(getResources().getString(R.string.annonces),getResources().getDrawable(R.drawable.sailboat)));
		_pages.add(new VendreTab(getResources().getString(R.string.vendre),getResources().getDrawable(R.drawable.pricetag)));
		_pages.add(new BoatOnDemandTab(getResources().getString(R.string.boat_on_demand),getResources().getDrawable(R.drawable.eye)));
		_pages.add(new ActualitesTab(getResources().getString(R.string.actualites),getResources().getDrawable(R.drawable.compass)));
		_pages.add(new MoreTab(getResources().getString(R.string.more),getResources().getDrawable(R.drawable.ronds)));

		_pagesAdapter = new PagesAdapter(getSupportFragmentManager(),_pages);
		_page = (ViewPager) findViewById(R.id.main_pager);

		_page.setAdapter(_pagesAdapter);
		//_page.setOnPageChangeListener(this);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {

	}
	
	

}
