package fr.RivaMedia.AnnoncesBateauGenerique.image;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageLoaderCache {
	
	public static DisplayImageOptions options = null;
	public static ImageLoaderConfiguration config = null;

	public static void load(Context context){
		if(options == null){
			options = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.build();

			config = new ImageLoaderConfiguration.Builder(context)
			.defaultDisplayImageOptions(options)
			.build();

			ImageLoader.getInstance().init(config);
		}
	}

	public static void charger(String url, ImageView imageView) {
		ImageLoader.getInstance().displayImage(url, imageView, options);
		ImageLoader.getInstance().displayImage(url, imageView, options);
	}
	
}
