package fr.RivaMedia.factory;

import java.util.ArrayList;
import java.util.List;

import fr.RivaMedia.model.Logo;

public class LogoFactory {
	
	public static Logo getLogo(){
		Logo logo = new Logo();
		
		logo.setUrl("http://www.logo00.com/logo-superman/logo-superman.png");
		
		return logo;
	}

	public static List<Logo> getListeLogo(){
		List<Logo> logos = new ArrayList<Logo>();
		for(int i=0;i<10;++i)
			logos.add(getLogo());
		return logos;
	}
	
}
