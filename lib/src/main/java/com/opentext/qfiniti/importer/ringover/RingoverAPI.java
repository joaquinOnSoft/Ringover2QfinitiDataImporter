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
 * access your contacts, users and groups and their properties. The API is 
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
public class RingoverAPI {
	private static final String RINGOVER_PROPERTIES_FILE_NAME = "ringover.properties";

	/** Property name used in the `ringover.properties` file */
	private static final String RINGOVER_API_KEY = "apiKey";

	/** Ringover Public API (2.0.3) URL base*/
	public static final String URL_BASE = "https://public-api.ringover.com/v2";
	
	public static final String METHOD_CALLS = "/calls";
	
	/**
	 * <strong>Parameter</strong>: start_date <br/>	
	 * <strong>Data type</strong>: string (date) <br/>
	 * Example: start_date=2020-06-27T00:00:00.53Z <br/>
	 * <strong>Description</strong>: Used to create a time cursor. Must be used with end_date
	 */
	public static final String PARAM_START_DATE = "start_date";

	/**
	 * <strong>Parameter</strong>: end_date <br/>	
	 * <strong>Data type</strong>: string (date) <br/>
	 * Example: end_date=2020-07-27T00:00:00.53Z <br/>
	 * <strong>Description</strong>: Used to create a time cursor. Must be used with 
	 * start_date and the difference between the start_date and the end_date must not exceed 15 days.
	 */
	public static final String PARAM_END_DATE = "end_date";

	/**
	 * <strong>Parameter</strong>: limit_count <br/>	
	 * <strong>Data type</strong>: integer (int64) &lt;= 1000 <br/>
	 * <strong>Description</strong>: Restrict the number of returned rows
	 */
	public static final String PARAM_LIMIT_COUNT = "limit_count";

	/**
	 * <strong>Parameter</strong>: last_id_returned <br/>	
	 * <strong>Data type</strong>: integer (int64) <br/>
	 * Example: last_id_returned=0 <br/>
	 * <strong>Description</strong>: The request will return cdr_id (call logs) prior to this one
	 */
	public static final String PARAM_LAST_ID_RETURNED = "last_id_returned";

	/**
	 * <strong>Parameter</strong>: call_type	
	 * <strong>Data type</strong>: string
	 * <strong>Description</strong>: Used to filter certain types of call. 
	 * <ul>
	 *    <li>'ANSWERED' filters answered calls.</li> 
	 *    <li>'MISSED' filters missed calls.</li> 
	 *    <li>'OUT' filters outgoing calls.</li> 
	 *    <li>'VOICEMAIL' filters calls ending on voicemail.</li>
	 * </ul>   
	 */
	public static final String PARAM_CALL_TYPE = "call_type";
		
    private static final Logger log = LogManager.getLogger(RingoverAPI.class);
    
    private String apiKey = null;
    
    /**
     * Initialize the Ringover API class.
     * <p>
     * Read the Ringover API key from the properties file called `ringover.properties`.
     * </p>
     * <p>
     * <strong>NOTE</strong>: The properties file must be located in the `CLASSPATH` 
     * </p> 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public RingoverAPI() throws FileNotFoundException, IOException {
    	File f = FileUtil.getFileFromResources(RINGOVER_PROPERTIES_FILE_NAME);
    	
    	if(f != null && f.exists()) {
	    	Properties prop = new Properties();
	    	prop.load(new FileInputStream(f));
	    	
	    	this.apiKey = prop.getProperty(RINGOVER_API_KEY);
    	}
    }

    public RingoverAPI(String apiKey) {
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
