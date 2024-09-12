package com._0.assignment2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class API {

    @GetMapping("/getGame")
    public Object getGame(){
        try{
            String url = "https://www.cheapshark.com/api/1.0/games?title=Batman";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);


            for (JsonNode rt : root){
                String quoteGame = rt.get("external").asText(); //issue here
                System.out.println("title: " + ": " + quoteGame);
            }


            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(API.class.getName()).log(Level.SEVERE,
                     null, ex);
            return "error in /getGame";
        }
    }


}
