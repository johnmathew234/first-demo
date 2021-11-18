package runner;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matcher;
import org.json.simple.JSONObject;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import com.sun.net.ssl.X509TrustManager;

import utils.HelperUtil;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Map;

import org.hamcrest.Matcher;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import utils.HelperUtil;
public class Driver {
	private String uri;
	private ValidatableResponse response;
	private String contentType;
	private String body;
	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	private static final String RANDOM = "random";
	public void sendRequest(String reqestMethod) throws FileNotFoundException {
		if (GET.equalsIgnoreCase(reqestMethod)) {
			RequestSpecification request = setSSLConfig();
			request.header("Content-Type", "application/json");
			request.header("x-correlation-id", "1234");
			request.header("client_secret", "1234");
			request.header("client_id", "1234");
			// response = given().when().delete(uri).then();
			response = request.get(uri).then();
		} else if (POST.equalsIgnoreCase(reqestMethod)) {
			RequestSpecification request = RestAssured.given().config(
					RestAssured
					  .config()
					  .sslConfig(
					    new SSLConfig().with().trustStoreType("JKS").and()
					      .trustStore(ResourceUtils.getFile("classpath:certs/apix-magento-sapi.jks"), "njclabs"))
					);
			request.header("Content-Type", "application/json");
			request.header("x-correlation-id", "1234");
			request.header("client_secret", "1234");
			request.header("client_id", "1234");
			request.body(this.body);
			response = request.post(uri).then();
		} else if (PUT.equalsIgnoreCase(reqestMethod)) {
			RequestSpecification request = RestAssured.given().config(
					RestAssured
					  .config()
					  .sslConfig(
					    new SSLConfig().with().trustStoreType("JKS").and()
					      .trustStore(ResourceUtils.getFile("classpath:certs/apix-magento-sapi.jks"), "njclabs"))
					);
			request.header("Content-Type", "application/json");
			request.header("x-correlation-id", "1234");
			request.header("client_secret", "1234");
			request.header("client_id", "1234");
			request.body(this.body);
			response = request.put(uri).then();
		} else if (DELETE.equalsIgnoreCase(reqestMethod)) {
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.header("x-correlation-id", "1234");
			request.header("client_secret", "1234");
			request.header("client_id", "1234");
			// response = given().when().delete(uri).then();
			response = request.delete(uri).then();
		}
	}
	private RequestSpecification setSSLConfig() {
		// TODO Auto-generated method stub
		return null;
	}
	public void createURI(String serviceName) {
		this.uri = serviceName;
	}
	public void addEndpoint(String endpoint) {
		this.uri = uri.concat(endpoint);
	}
	public void expectedResponse(int responseCode) {
		response.statusCode(responseCode);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validateResponse(String filePath) throws IOException {
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> map = objectmapper.readValue(HelperUtil.getJsonStringFromPath(filePath), Map.class);
		for (Map.Entry<String, String> entrySet : map.entrySet()) {
			String key = entrySet.getKey();
			final String value = entrySet.getValue();
			response.body(key, new ResponseAwareMatcher() {
				public Matcher matcher(ResponseBody response) throws Exception {
					return equalTo(value);
				}
			});
		}
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public void setRequestBody(String filePath) throws IOException {
		String jsonString = HelperUtil.getJsonStringFromPath(filePath);
		if (jsonString != null && jsonString.contains(RANDOM)) {
			jsonString = getManipulatedString(jsonString);
		}
		this.body = jsonString;
	}
	@SuppressWarnings("unchecked")
	private String getManipulatedString(String jsonString) throws IOException {
		JsonObject jsonObject = new JsonObject();
		ObjectMapper objectmapper = new ObjectMapper();
		Map<String, String> map = objectmapper.readValue(jsonString, Map.class);
		for (Map.Entry<String, String> entrySet : map.entrySet()) {
			String value = entrySet.getValue();
			if (value.contains(RANDOM)) {
				entrySet.setValue(HelperUtil.getRandomValue(value));
			}
			//jsonObject.put(entrySet.getKey(), entrySet.getValue());
		}
		return jsonObject.toString();
	}
}