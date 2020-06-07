package com.analizador.nlp;

import NLP_auxiliares.PronombresEncliticos;
import NLP_services.requestLibrAIry;
import ServiciosAnalizador_auxiliares.Frecuencia;
import ServiciosAnalizador_auxiliares.respuestaJSONDeteccion;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@RestController
public class ruleGerundio {

    /* SALIDA:
    Devuelve un json de la siguiente forma:
       {
         "id":2,
         "name":"Regla - Gerundio",
         "description":"Detectar el uso de gerundios.",
         "pass": false,
         "reason":"Encontrados los siguientes gerundios: [hablando, bailándose]."
       }
     */
    @GetMapping("/gerundio")
    public static String gerundio(@RequestParam(value = "text", defaultValue = "") String text) throws IOException {

        List<String> notPass = new LinkedList<String>();

        int id = 2;
        String name = "Regla - Gerundios";
        String description = "Detectar el uso de gerundios.";
        boolean pass = true;
        String reason = "Not Apply";

        //Tratar los pronombres encliticos
        //La explicacion del contenido del HashMap esta en la clase PronombresEncliticos
        String nuevoText = "";
        HashMap<String,ArrayList<String>> posiblesGerundios = PronombresEncliticos.pronombresEncliticos(text);
        for(String key:posiblesGerundios.keySet()){
            // Con el punto confunde menos gerundios por nombres (LibrAIry)
            nuevoText = nuevoText + key + " . ";
        }
        text = nuevoText;

        if (posiblesGerundios.isEmpty() || text.isEmpty()){
            //no hay palabras que contengan ando o iendo o ándo o iéndo o yendo
            return respuestaJSONDeteccion.codificador(id,name,description, pass, reason);
        }

        //Llamada a LibrAIry sin filtro
        String[] filter = new String[0];
        String url = "annotations";
        String response = requestLibrAIry.request(filter, text, url);

        if (!response.contains("ERROR")) {

            JsonObject responseJSON = new Gson().fromJson(response, JsonObject.class);
            JsonArray tokens = responseJSON.getAsJsonArray("annotatedText");

            String target = ""; // La forma
            String label = ""; // Etiqueta EAGLE
            for (int i = 0; i < tokens.size(); i++) {
                try {
                    target = tokens.get(i).getAsJsonObject().get("token").getAsJsonObject().get("target").getAsString();
                } catch (Exception e) {
                    System.out.println("Excepcion obteniendo target: " + e);
                }
                try {
                    if (tokens.get(i).getAsJsonObject().get("token").getAsJsonObject().has("morphoFeat")) {
                        label = tokens.get(i).getAsJsonObject().get("token").getAsJsonObject().get("morphoFeat").getAsString();
                        if (label.length() > 2 && label.charAt(0) == 'V' && label.charAt(2) == 'G') {
                            notPass.add(target);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Excepcion obteniendo label: " + e);
                }


            }

        } else {
            reason = "Se ha producido un error interno. Pruebe de nuevo.";
            pass = false;
        }

        if (notPass.size() > 0) {
            pass = false;
            String gerunds = Frecuencia.getFrecuencia(PronombresEncliticos.getOriginales(posiblesGerundios,notPass));
            reason = "El texto presenta los siguientes gerundios no permitidos: [" + gerunds + "].";
        }

        return respuestaJSONDeteccion.codificador(id, name, description, pass, reason);

    }

}
