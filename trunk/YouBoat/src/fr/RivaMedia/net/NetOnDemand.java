package fr.RivaMedia.net;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntity;

import android.graphics.Bitmap;
import fr.RivaMedia.Constantes;
import fr.RivaMedia.net.core.Net;

public class NetOnDemand {

	public static void onDemand(MultipartEntity donnees, List<Bitmap> photos) {
		
		if(photos != null && photos.size()>0)
			Net.addBitmap(donnees, Constantes.VENDRE_IMAGE, photos.get(0));
		Net.requete(Constantes.URL_ON_DEMAND, donnees);
		
	}
	
}
