package com.demo.controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class DemoController {

	private final OAuth2AuthorizedClientService oAuth2ClientService;

	public DemoController(OAuth2AuthorizedClientService oAuth2ClientService) {
		this.oAuth2ClientService = oAuth2ClientService;
	}
	
	@GetMapping
	public Map<String,String> useriInfo(OAuth2AuthenticationToken oA2Token){
		OAuth2AuthorizedClient client = oAuth2ClientService.loadAuthorizedClient(oA2Token.getAuthorizedClientRegistrationId(), 
																				oA2Token.getPrincipal().getName());
		
		OAuth2AccessToken accessToken = client.getAccessToken();
		
		Map<String,String> responseMap = Map.of("access-token", accessToken.getTokenValue(),
												"issued-at",accessToken.getIssuedAt().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME),
												"expires-at", accessToken.getExpiresAt().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME),
												"scope", accessToken.getScopes().toString(),
												"type", accessToken.getTokenType().getValue());
		
		return responseMap;
		
	}
	
	
	
	
}
