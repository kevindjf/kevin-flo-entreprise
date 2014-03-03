package fr.RivaMedia.AnnoncesBateauGenerique.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

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
		charger(url, imageView,null);
	}

	public static void charger(String url, ImageView imageView, final View progress) {

		final ImageLoadingListener imageListener = new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				//if(progress != null)
				//	progress.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				view.setVisibility(View.GONE);
				if(progress != null)
					progress.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				if(progress != null)
					progress.setVisibility(View.GONE);
				if(loadedImage == null || loadedImage.getWidth() == 0 || loadedImage.getHeight() == 0)
					view.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				view.setVisibility(View.GONE);
				if(progress != null)
					progress.setVisibility(View.GONE);
			}
		};

		ImageLoader.getInstance().displayImage(url, imageView, options, imageListener);
	}

}
