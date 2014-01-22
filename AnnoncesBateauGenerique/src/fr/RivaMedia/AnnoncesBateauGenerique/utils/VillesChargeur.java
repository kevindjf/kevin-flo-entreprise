package fr.RivaMedia.AnnoncesBateauGenerique.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Context;
import fr.RivaMedia.AnnoncesBateauGenerique.model.Ville;
import fr.RivaMedia.AnnoncesBateauGenerique.xml.VilleXmlParser;

public class VillesChargeur {

	public static String getRawFile(Context context, int id){
		StringBuffer sb = new StringBuffer();

		try{
			InputStream raw = context.getResources().openRawResource(id);
			BufferedReader bf = new BufferedReader(new InputStreamReader(raw));

			String line = "";

			while((line = bf.readLine()) != null){
				sb.append(line);
			}

		}catch(Exception e){
		}
		return sb.toString();
	}
	
	public static List<Ville> chargerVilles(Context context, int id){
		return new VilleXmlParser(getRawFile(context, id)).getVilles();
	}

}
