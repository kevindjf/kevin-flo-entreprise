package com.snapyou.rest;

import org.codegist.crest.annotate.*;

@EndPoint("http://api.example.com")
@Path("/rest/myservice")
public interface SnapYouService {

	@POST
	@Path("ajouterPhoto")
	public String ajouterPhoto(
			@QueryParam("key") String key,
			@MultiPartParam("photo") byte[] photo);


}