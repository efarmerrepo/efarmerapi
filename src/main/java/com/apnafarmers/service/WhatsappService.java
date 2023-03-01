package com.apnafarmers.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WhatsappService {

	public String sendMessage() {

		String phoneNumber = "";
		String phoneNumberId = "";
		String authToken = "";
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://graph.facebook.com/v13.0/" + phoneNumberId + "/messages"))
					.header("Authorization", "Bearer " + authToken).header("Content-Type", "application/json")
					// Getting started example
					// .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\":
					// \"whatsapp\", \"recipient_type\": \"individual\", \"to\":
					// \""+phoneNumber+"\", \"type\": \"template\", \"template\": { \"name\":
					// \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
					// Text message example
					// .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\":
					// \"whatsapp\", \"recipient_type\": \"individual\", \"to\":
					// \""+phoneNumber+"\", \"type\": \"text\", \"text\": { \"preview_url\": false,
					// \"body\": \"This is an example of a text message\" } }"))
					// Bespoke message template example
					.POST(HttpRequest.BodyPublishers.ofString(
							"{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""
									+ phoneNumber
									+ "\", \"type\": \"template\", \"template\": { \"name\": \"new_customer_offer\", \"language\": { \"code\": \"en\" }, \"components\":[{\"type\":\"header\",\"parameters\":[{\"type\":\"image\",\"image\":{\"link\":\"https://i.ibb.co/DRMHqRj/welcome.jpg\"}}]},{\"type\":\"body\",\"parameters\":[{\"type\":\"text\",\"text\":\"John Smith\"}]}] } }"))
					.build();
			HttpClient http = HttpClient.newHttpClient();
			HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
			log.info(response.body());

		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return authToken;

	}
}
