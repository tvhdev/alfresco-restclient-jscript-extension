package de.vhss.alfresco.scriptobjects;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;

import org.alfresco.repo.jscript.BaseScopableProcessorExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.extensions.webscripts.annotation.ScriptClass;
import org.springframework.extensions.webscripts.annotation.ScriptClassType;
import org.springframework.extensions.webscripts.annotation.ScriptMethod;
import org.springframework.extensions.webscripts.annotation.ScriptMethodType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author tvh
 *
 */
@ScriptClass(types=ScriptClassType.JavaScriptRootObject, code="restclient", help="the root object for the restclient service")
public class ScriptRestService
extends BaseScopableProcessorExtension {
	
	/**
	 * @param builder
	 * @return
	 */
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
	    return builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000))
	            .build();
	}

	/**
	 * @param url
	 * @param payload TODO
	 * @param headers TODO
	 * @return
	 */
	@ScriptMethod(help="post method",output="String",code="restclient.post('https://api.service...','{\\'hallo\\':\\'welt\\'}','Content-Type: application/x-www-form-urlencoded')",type=ScriptMethodType.READ)
	public String post(String url, String payload, String headers) {
		return this.post(url,payload,headers, null, null);
	}
	
	/**
	 * @param url
	 * @param payload TODO
	 * @param headers TODO
	 * @param username
	 * @param password
	 * @return
	 */
	@ScriptMethod(help="post method with authentication",output="String",code="restclient.post('https://api.service...','{\\'hallo\\':\\'welt\\'}','Content-Type: application/x-www-form-urlencoded','user','pass')",type=ScriptMethodType.READ)
	public String post(String url, String payload, String headers, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		if (username!=null || password!=null) {
			restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username,password));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		Iterable<String> headerIterable = Arrays.asList(headers.split("\\|"));
		for (String header : headerIterable) {
			String[] contentType = header.split(":");
			httpHeaders.set(contentType[0], contentType[1].trim());
		}		
		
		String ret;
		try 
		{
			HttpEntity<String> request = 
				      new HttpEntity<String>(payload, httpHeaders);
			
			ret = restTemplate.postForObject(url, request, String.class);
		} catch (RestClientException ex) {
			ret = "error: " + ex.getMessage();
		}
	    return ret;
	}
	
	/**
	 * @param url
	 * @return
	 */
	@ScriptMethod(help="get method",output="String",code="restclient.get('https://www.heise.de')",type=ScriptMethodType.READ)
	public String get(String url) {
		return this.get(url,null,null);
	}
	
	/**
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	@ScriptMethod(help="get method with authentication",output="String",code="restclient.get('https://www.heise.de','user','pass')",type=ScriptMethodType.READ)
	public String get(String url, String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		if (username!=null || password!=null) {
			restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username,password));
		}
		
		String ret;
		try 
		{
			ret = restTemplate.getForObject(url, String.class);
		} catch (RestClientException ex) {
			ret = "error: " + ex.getMessage();
		}
	    return ret;
	}
}
