package fr.RivaMedia.net.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import fr.RivaMedia.Constantes;

public class Net {

	public static final String OK = "OK";
	public static final String NO = "NO";

	public static final String SITE = Constantes.URL_BASE;

	public static List<NameValuePair> newListNameValuePair(){
		return new ArrayList<NameValuePair>();
	}

	public static List<NameValuePair> construireDonnes(Object...donnes){
		List<NameValuePair> donneesPost = new ArrayList<NameValuePair>();
		add(donneesPost,donnes);
		return donneesPost;
	}

	public static void add(List<NameValuePair> donneesPost, Object...donnes){
		for(int i=0;i<donnes.length;i+=2){
			String cle = donnes[i].toString();
			String valeur = donnes[i+1].toString();
			donneesPost.add(new BasicNameValuePair(cle, valeur));
		}
	}

	public static MultipartEntity construireDonnesMultiPart(Object...donnes){
		MultipartEntity donneesPost = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		add(donneesPost,donnes);
		return donneesPost;
	}

	public static MultipartEntity newMultipartEntity(){
		return new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	}

	public static void add(MultipartEntity donneesPost, Object...donnes){
		for(int i=0;i<donnes.length;i+=2){
			String cle = donnes[i].toString();
			String valeur = donnes[i+1].toString();

			try {
				donneesPost.addPart(cle, new StringBody(valeur, Charset.forName(HTTP.UTF_8)));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addBitmap(MultipartEntity donneesPost, String cle, Bitmap bm){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.JPEG, 100, bos);
		byte[] data = bos.toByteArray();
		ByteArrayBody bab = new ByteArrayBody(data, cle+".jpg");
		donneesPost.addPart(cle, bab);		
	}

	public static void addImage(MultipartEntity donneesPost, String cle, Bitmap image){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		image.compress(CompressFormat.JPEG, 100, bos);
		byte[] data = bos.toByteArray();

		donneesPost.addPart(cle, new ByteArrayBody(data, "image/jpeg"));
	}

	public static String requete(String url, List<NameValuePair> donneesPost){

		add(donneesPost,"os","android");

		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost requete = new HttpPost(SITE+(url.replace("?", "")));
			requete.setEntity(new UrlEncodedFormEntity(donneesPost,"UTF-8"));

			Log.d("NET",SITE+url+"/");
			HttpResponse httpReponse = httpClient.execute(requete);

			//StatusLine statusLine = httpReponse.getStatusLine();
			//Log.e("WYDEEZ",statusLine.toString());
			//if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			//String response = responseToString(httpReponse.getEntity());
			String response = EntityUtils.toString( httpReponse.getEntity(), HTTP.ISO_8859_1 ).trim().replace("&aecute", "é");  
			Log.d("NET",response);
			return response;
			//}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally{
			if(httpClient!=null)
				httpClient.getConnectionManager().shutdown();
		}

		Log.e("NET","echec de la requete");
		return NO; //en cas d'echec
	}

	public static String requeteGet(String url, List<NameValuePair> donnees){

		StringBuilder sb = new StringBuilder();
		if(donnees != null){
			boolean premier = true;
			for(NameValuePair nvp : donnees){
				if(!premier)
					sb.append("&");
				premier = false;

				sb.append(nvp.getName());
				sb.append("=");
				sb.append(nvp.getValue());
			}
		}

		String urlRequete = SITE+url+sb.toString();
		Log.d("NET",urlRequete);

		HttpClient httpClient = new DefaultHttpClient();
		try {

			HttpGet requete = new HttpGet(urlRequete);

			HttpResponse httpReponse = httpClient.execute(requete);

			//StatusLine statusLine = httpReponse.getStatusLine();
			//Log.e("NET",statusLine.toString());
			//if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			//String response = responseToString(httpReponse.getEntity());
			String response = EntityUtils.toString( httpReponse.getEntity(), HTTP.ISO_8859_1 ).trim().replace("&aecute", "é");  
			Log.d("NET",response);
			return response;
			//}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally{
			if(httpClient!=null)
				httpClient.getConnectionManager().shutdown();
		}

		Log.e("NET","echec de la requete");
		return NO; //en cas d'echec
	}

	public static String requete(String url, MultipartEntity donneesPost){

		add(donneesPost,"os","android");

		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		try {

			HttpPost requete = new HttpPost(SITE+url);
			requete.setEntity(donneesPost);

			HttpResponse httpReponse = httpClient.execute(requete);

			StatusLine statusLine = httpReponse.getStatusLine();
			//Log.e("WYDEEZ",statusLine.toString());

			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				//String response = responseToString(httpReponse.getEntity());
				String response = EntityUtils.toString( httpReponse.getEntity(), HTTP.ISO_8859_1 ).trim();
				Log.d("NET",response);
				return response.replace("<br>", "").replace("<br>", "").replace("<br/>", "").replace("<br />", "");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally{
			if(httpClient!=null)
				httpClient.getConnectionManager().shutdown();
		}

		return NO; //en cas d'echec
	}

	public static String responseToString(HttpEntity entity){
		StringBuilder out = new StringBuilder();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			for(String line = reader.readLine(); line != null; line = reader.readLine()) 
				out.append(line);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return out.toString();
	}
}
