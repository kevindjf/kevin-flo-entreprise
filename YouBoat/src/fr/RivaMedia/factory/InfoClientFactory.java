package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.*;

public class InfoClientFactory {
	
	public static InfoClient getInfoClient(){
		InfoClient infoClient = new InfoClient();
		
		infoClient.setNumero("1");
		infoClient.setNom("Dupont Jean");
		infoClient.setVille("Paris");
		infoClient.setTel1("0607080910");
		infoClient.setTel1("0606060606");
	   // private String logo;
		infoClient.setGpsLatitude("0.2");
		infoClient.setGpsLongtitude("-3.3");
		infoClient.setEmail("jean.dupont@orange.fr");
		infoClient.setAdresse("1 rue de la paix");
		infoClient.setEmail("75000");
		infoClient.setEmail("0707070707");
		infoClient.setSiteWeb("www.google.fr");
		infoClient.setContact("LeContact");
		infoClient.setHoraires("8h ˆ 11h - 14h ˆ 17h");
		infoClient.setService("LeService");
		infoClient.setDescription("La description de Jean Dupont");
		infoClient.setNbBateau("10");
		infoClient.setNbMoteur("10");
		infoClient.setNbAccessoire("10");
	    //NSMutableArray* serviceArray;
		infoClient.setCurLogo(LogoFactory.getLogo());
	    infoClient.setPhotos(LogoFactory.getListeLogo());
	    //Services* adservice;
	    
	    //List<Object> services;
		
		return infoClient;
	}
	
	public static List<InfoClient> getListeInfoClient(){
		List<InfoClient> infoClients = new ArrayList<InfoClient>();
		for(int i=0;i<10;++i)
			infoClients.add(getInfoClient());
		return infoClients;
	}

    
}
