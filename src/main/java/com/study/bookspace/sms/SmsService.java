package com.study.bookspace.sms;

import java.net.URI;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@PropertySource("classpath:application.properties")
@Service("smsService")
public class SmsService {
	
	@Value("${naver-cloud-sms.accessKey}")
	private String accessKey;
	
	@Value("${naver-cloud-sms.secretKey}")
	private String secretKey;
	
	@Value("${naver-cloud-sms.serviceId}")
	private String serviceId;
	
	@Value("${naver-cloud-sms.senderPhone}")
	private String phone;
	
    public String makeSignature(String time) throws Exception {
    	System.out.println("~~~~makeSignature~~~~");
    	
    	String space = " ";
    	String newLine = "\n";
    	String method = "POST";
    	String url = "/sms/v2/services/"+ this.serviceId+"/messages";
		String accessKey = this.accessKey;
		String secretKey = this.secretKey;
		
		String message = new StringBuilder()
				.append(method)
				.append(space)
				.append(url)
				.append(newLine)
				.append(time)
				.append(newLine)
				.append(accessKey)
				.toString();
		
		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);
		
		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);
		
		return encodeBase64String;
    }
    
    public SmsResponseVO sendSms(List<SmsVO> messages) throws Exception {
    	String time = Long.toString(System.currentTimeMillis());
    	
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
		
		SmsRequestVO request = SmsRequestVO.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from(phone)
				.messages(messages)
				.content("")
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(request);
		HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		SmsResponseVO smsResponseVO = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseVO.class);

		return smsResponseVO;
    }
    
    public String createRandomNumber(int len) {
    	SecureRandom random = new SecureRandom();
    	StringBuilder num = new StringBuilder();
    	for (int i = 0; i < len; i++) {
    		num.append(random.nextInt(10));
        }
        return num.toString();
    }
	
}
