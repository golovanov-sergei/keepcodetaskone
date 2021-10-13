package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class App {
    private static final String COUNTRYLIST_API_URL = "https://onlinesim.ru/api/getFreeCountryList";
    private static final String PHONELIST_API_URL = "https://onlinesim.ru/api/getFreePhoneList?country=";

    public static void main(String[] args) throws IOException, InterruptedException {
        Map<Country,Set<PhoneNumber>> phonesMap = new HashMap<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(COUNTRYLIST_API_URL))
                .build();
        HttpResponse<String> responseCountries = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNodeCountries = mapper.readTree(responseCountries.body()).get("countries");

        jsonNodeCountries.forEach(jsonNodeCountriesElement -> {
            Set<PhoneNumber> phoneNumbers = new HashSet<>();
            Country country = mapper.convertValue(jsonNodeCountriesElement, Country.class);
            HttpRequest httpRequestPhones = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(PHONELIST_API_URL+country.getCountry()))
                    .build();
            try {
                HttpResponse<String> responsePhones = httpClient.send(httpRequestPhones, HttpResponse.BodyHandlers.ofString());
                JsonNode jsonNodePhones = mapper.readTree(responsePhones.body()).get("numbers");
                jsonNodePhones.forEach(jsonNodePhonesElement ->{
                    PhoneNumber phoneNumber = mapper.convertValue(jsonNodePhonesElement,PhoneNumber.class);
                    phoneNumbers.add(phoneNumber);
                });

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phonesMap.put(country,phoneNumbers);
        });
        for (Map.Entry<Country, Set<PhoneNumber>> entry: phonesMap.entrySet()
             ) {
            System.out.println(entry);
        }
    }
}
