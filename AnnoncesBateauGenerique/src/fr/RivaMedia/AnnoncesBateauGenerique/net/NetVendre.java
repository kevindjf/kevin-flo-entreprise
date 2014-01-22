package fr.RivaMedia.AnnoncesBateauGenerique.net;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntity;

import fr.RivaMedia.AnnoncesBateauGenerique.Constantes;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.MD5;
import fr.RivaMedia.AnnoncesBateauGenerique.net.core.Net;

import android.graphics.Bitmap;

public class NetVendre extends Net{

	public static String getIdAleatoire(){
		return MD5.getDateFormateeJMHMSMD5();
	}
	
	public static String vendre(MultipartEntity donnees, List<Bitmap> photos) {
		
		Net.add(donnees, Constantes.VENDRE_ALEA_NUMBER,getIdAleatoire());
		Net.add(donnees, Constantes.VENDRE_HASH,MD5.getDateFormateeMD5());
		if(photos != null && photos.size()>0)
			Net.addBitmap(donnees, Constantes.VENDRE_IMAGE, photos.get(0));
		return Net.requete(Constantes.URL_VENDRE, donnees);
		
	}

}
