package com.snapyou.rest;

import org.codegist.crest.annotate.*;

@EndPoint("172.16.210.25")
@Path("web/app_dev.php/api")
public interface SnapYouService {

	@POST
	@Path("images")
	public String ajouterPhoto(
			@QueryParam("key") String key,
			@MultiPartParam("photo") byte[] photo);


}