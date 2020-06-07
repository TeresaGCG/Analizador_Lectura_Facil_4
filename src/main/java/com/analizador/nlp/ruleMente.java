package com.analizador.nlp;

import NLP_services.requestLibrAIry;
import ServiciosAnalizador_auxiliares.Frecuencia;
import ServiciosAnalizador_auxiliares.respuestaJSONDeteccion;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@RestController
public class ruleMente {

    /* SALIDA:
       {
         "id":1,
         "name":"Regla - Adverbios -mente",
         "description":"Detectar el uso de adverbios acabados en -mente.",
         "pass": false,
         "reason":"El texto presenta los siguientes adverbios no permitidos: [generalmente(2), igualmente]."
       }
     FUNCIONAMIENTO:
         1. Selecciona todas las palabras que acaban en -mente
         2. LLama a la api librAIry filtrando por adverbios
         3. Crea el JSON a devolver
        */
    @GetMapping("/mente")
    public String mente(@RequestParam(value = "text", defaultValue = "") String text) throws IOException {

        List<String> notPass = new LinkedList<String>();

        int id = 1;
        String name = "Regla - Adverbios -mente";
        String description = "Detectar el uso de adverbios acabados en -mente.";
        boolean pass = true;
        String reason = "Not Apply";

        /*
         Se quiere recorrer las palabras del texto
         Para ello se dividira el texto por espacios
         PERO si el texto presenta puntos, comas, etc. las palabras contendrian dichos caracteres
         Ej. Me llamo Laura. Tengo un amigo, llamado Juan.
         Las palabras serian [Me, llamo, Laura., Tengo, un, amigo,, llamado, Juan.]
         Por eso se hace un tratamiento al texto que consiste en solo dejar aquello que sean letras
         De esta manera, el texto seria: Me llamo Laura Tengo un amigo llamado Juan
         Y las palabras: [Me, llamo, Laura, Tengo, un, amigo, llamado, Juan]
        */
        text = this.tratamientoText(text);
        String[] words = text.split( " ");

        //classify contiene todas las palabras que acaban en mente separadas por un espacio en blanco
        String classify = "";
        List<String> palabrasMente = new LinkedList<String>();
        for (String word : words){
            int length = word.length();
            if(length > 5) {
                if (word.substring(length - 5).equals("mente")) {
                    classify = classify + " " + word;
                    palabrasMente.add(word);
                }
            }
        }

        if (classify.equals("")){
            //no hay adverbios acabados en -mente
            return respuestaJSONDeteccion.codificador(id,name,description, pass, reason);
        }

        //Una vez se tienen todas las palabras terminadas en -mente, se procede a llamar a LibrAIry
        String[] filter = {"ADVERB"};
        String url = "groups"; //Servicio a utilizar de LibrAIry
        String response = requestLibrAIry.request(filter, classify, url);

        if (!response.contains("ERROR")) {

            JsonObject responseJSON = new Gson().fromJson(response, JsonObject.class);
            JsonArray groups = responseJSON.getAsJsonArray("groups");

            int frequency = 0;
            String token = "";
            for (int i = 0; i < groups.size(); i++) {
                //La frequency se representara como adverbio(frequency) si es mayor que 1
                frequency = groups.get(i).getAsJsonObject().getAsJsonPrimitive("freq").getAsInt();
                token = groups.get(i).getAsJsonObject().getAsJsonPrimitive("token").getAsString();

                if (frequency > 1) {
                    notPass.add(token + "(" + frequency + ")");
                }
                else{
                    notPass.add(token);
                }
            }

        } else {
            // Ha fallado la llamada a LibrAIry
            // Se sabe previamente que hay palabras terminadas en -mente
            // pues en caso de no haber, no se llega a llamar a LibrAIry
            // Se supone que dichas palabras son adverbios
            // Se calcula la frecuencia de las mismas
            reason = "El texto presenta los siguientes adverbios no permitidos: [" + Frecuencia.getFrecuencia(palabrasMente) + "].";
            pass = false;
        }

        if (notPass.size() > 0){
            pass = false;
            String adverbs = notPass.get(0);
            for (int i = 1; i < notPass.size(); i++){
                adverbs = adverbs + ", " + notPass.get(i);
            }
            reason = "El texto presenta los siguientes adverbios no permitidos: ["+ adverbs +"].";
        }

        return respuestaJSONDeteccion.codificador(id,name,description, pass, reason);

    }
    //Elimina aquello que no sean letras
    private String tratamientoText (String text){
        return text.replaceAll("[^A-Za-zÀ-ÿ\u00f1\u00d1]"," ");
    }

}
