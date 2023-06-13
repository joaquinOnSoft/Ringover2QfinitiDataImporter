package com.opentext.qfiniti.importer.ringover;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.qfiniti.importer.ringover.pojo.calls.TerminatedCalls;
import com.opentext.qfiniti.importer.util.FileUtil;

/**
 * <strong>Ringover Public API (2.0.3)</strong>
 * <p>
 * Ringover's REST API allows you to easily retrieve your phone information, 
 * access your contacts, users and groups and their properties. Our API is 
 * designed to answer your requests made via HTTP commands and to receive 
 * data with standard HTTP response codes.
 * </p>
 * 
 * <strong>Warning [Rate limit]</strong>: There is a 2-call per second limit applied to each request.
 * 
 * @see https://developer.ringover.com/
 * @author Joaquín Garzón
 *
 */
public class RingoverAPIWrapper {
	/** Property name used in the `ringover.properties` file */
	private static final String RINGOVER_API_KEY = "apiKey";

	/** Ringover Public API (2.0.3) URL base*/
	public static final String URL_BASE = "https://public-api.ringover.com/v2";
	
	
	public static final String METHOD_CALLS = "/calls";
		
    private static final Logger log = LogManager.getLogger(RingoverAPIWrapper.class);
    
    private String apiKey = null;
    
    public RingoverAPIWrapper() throws FileNotFoundException, IOException {
    	File f = FileUtil.getFileFromResources("ringover.properties");
    	
    	if(f != null && f.exists()) {
	    	Properties prop = new Properties();
	    	prop.load(new FileInputStream(f));
	    	
	    	this.apiKey = prop.getProperty(RINGOVER_API_KEY);
    	}
    }

    public RingoverAPIWrapper(String apiKey) {
    	this.apiKey = apiKey;
    }    
    
	/**
	 * <strong>Get all calls</strong>
	 * <p>
	 * This request allows you to retrieve your terminated calls. All the parameters are optional. 
	 * For a more refined search, please refer to the POST method.
	 * </p>
	 * @see <a href="https://mkyong.com/java/apache-httpclient-examples/">Apache HttpClient Examples</a>
	 */
	public TerminatedCalls getAllCalls() {
		String url = null;
		TerminatedCalls calls = null;
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		
			url = URL_BASE + METHOD_CALLS;
			log.debug("URL: " + url);
			
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
		    		.addHeader("Authorization", apiKey)
		            .build();
		    // The underlying HTTP connection is still held by the response object
		    // to allow the response content to be streamed directly from the network socket.
		    // In order to ensure correct deallocation of system resources
		    // the user MUST call CloseableHttpResponse#close() from a finally clause.
		    // Please note that if response content is not fully consumed the underlying
		    // connection cannot be safely re-used and will be shut down and discarded
		    // by the connection manager.
		    calls = httpclient.execute(httpGet, response -> {
				TerminatedCalls responseCalls = null;

		        log.info(response.getCode() + " " + response.getReasonPhrase());
		        
		        // Do something useful with the response body		        
		        HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    log.debug(METHOD_CALLS + ": " + result);
                    
                	ObjectMapper mapper = new ObjectMapper();
                	//JSON string to Java Object
                	responseCalls = mapper.readValue(result, TerminatedCalls.class);            
                }
		        // Ensure `response` it is fully consumed
		        EntityUtils.consume(entity);		       		        

		        return responseCalls;
		    });
		}
		catch (Exception e) {
			log.error("RingoverAPIWrapper.getAllCalls(): ", e);
		}
		
		return calls;
	}
}
