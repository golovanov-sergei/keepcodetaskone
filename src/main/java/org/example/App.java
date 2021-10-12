package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static final String COUNTRYLIST_API_URL = "https://onlinesim.ru/api/getFreeCountryList";
    private static final String PHONELIST_API_URL = "https://onlinesim.ru/api/getFreePhoneList?country=";

    public static void main(String[] args) throws IOException, InterruptedException {
        Map<Country,List<PhoneNumber>> phonesMap = new HashMap<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(COUNTRYLIST_API_URL))
                .build();
        HttpResponse<String> responseCountires = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
        JsonNode jsonNodeCountries = new ObjectMapper().readTree(responseCountires.body());
        ObjectMapper mapper = new ObjectMapper();
//        List<Country> countryList = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode countries = jsonNodeCountries.get("countries");
        countries.forEach(jsonNodeCountriesElement -> {
            Country country = mapper.convertValue(jsonNodeCountriesElement, Country.class);
            HttpRequest httpRequestPhones = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(PHONELIST_API_URL+country.getCountry()))
                    .build();
            try {
                HttpResponse<String> responsePhones = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            countryList.add(country);
        });
        countryList.forEach(System.out::println);
    }
}
