package fr.RivaMedia.AnnoncesAutoGenerique.activity;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.AnnoncesAutoGenerique.R;
import fr.RivaMedia.AnnoncesAutoGenerique.image.ImageLoaderCache;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoView;


public class Gallery extends Activity{
	
	private List<String> images = new ArrayList<String>();
	
	public static String TEXTE = "texte";
	public static String IMAGES = "images";

	private class ImagePagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(final ViewGroup container, final int position, final Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public Object instantiateItem(final ViewGroup container, final int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			
			ImageLoaderCache.charger(images.get(position), photoView);

			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public boolean isViewFromObject(final View view, final Object object) {
			return view == ((ImageView) object);
		}
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		
		ImageLoaderCache.load(this);

		Bundle extras = getIntent().getExtras();
		if(extras.containsKey(TEXTE)){
			TextView tvText = (TextView) findViewById(R.id.gallery_texte);
			tvText.setText(extras.getString(TEXTE));
		}
		if(extras.containsKey(IMAGES)){
			images = extras.getStringArrayList(IMAGES);
		}
		
		final ViewPager viewPager = (ViewPager)findViewById(R.id.gallery_pager);
		final ImagePagerAdapter adapter = new ImagePagerAdapter();
		viewPager.setAdapter(adapter);
	}

}

