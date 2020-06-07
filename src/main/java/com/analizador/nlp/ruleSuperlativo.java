package com.analizador.nlp;

import NLP_services.requestTextServer;
import NLP_auxiliares.GeneroNumero;
import ServiciosAnalizador_auxiliares.Frecuencia;
import ServiciosAnalizador_auxiliares.respuestaJSONCorreccion;
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
public class ruleSuperlativo {

    /* SALIDA TEXTSERVER:
        * { "cputime" : "0.018686", "wordcount" : "13",
             "paragraphs" : [
               { "sentences" : [
                  { "id":"1",
                    "tokens" : [
                       { "id" : "t1.11", "begin" : "65", "end" : "74", "form" : "guapísimo", "lemma" : "guapo", "tag" : "AQSMS00", "ctag" : "AQ", "pos" : "adjective", "type" : "qualificative", "degree" : "superlative", "gen" : "masculine", "num" : "singular"},
              ]}
        */

        /* SALIDA SUPERLATIVO DETECCION:
         {
         "id":3,
         "name":"Regla - Superlativos",
         "description":"Detectar el uso de superlativos.",
         "pass": false,
         "reason":"El texto presenta los siguientes superlativos no permitidos: [guapísimo(2)]"
       }
       * */
    @GetMapping("/superlativoDeteccion")
    public String superlativoDeteccion(@RequestParam(value = "text", defaultValue = "") String text) throws IOException {

        int id = 3;
        String name = "Regla - Superlativos";
        String description = "Detectar el uso de superlativos.";
        boolean pass = true;
        String reason = "Not Apply";

        List<String> notPass = new LinkedList<String>();
        String request = new requestTextServer().request(text, "tagger"); // Servicio Desambiguacion PoS de TextServer

        if(request.compareTo("ERROR")==0){
            pass = false;
            reason = "Se ha producido un error interno. Pruebe de nuevo.";
            return respuestaJSONDeteccion.codificador(id, name, description, pass, reason);
        }

        JsonObject responseJSON = new Gson().fromJson(request, JsonObject.class);
        JsonArray paragraphs = responseJSON.getAsJsonArray("paragraphs");
        JsonArray sentences;
        JsonArray tokens;

        String form = "";
        String tag = "";

        //Hay que recorrer todas las palabras (tokens) de cada frase (sentences) de cada parrafo
        for (int i = 0; i < paragraphs.size(); i++) {
            sentences = paragraphs.get(i).getAsJsonObject().get("sentences").getAsJsonArray();
            for (int j = 0; j < sentences.size(); j++){
                tokens = sentences.get(j).getAsJsonObject().get("tokens").getAsJsonArray();
                for (int k = 0; k < tokens.size(); k++) {
                    form = tokens.get(k).getAsJsonObject().get("form").getAsString();
                    tag = tokens.get(k).getAsJsonObject().get("tag").getAsString();

                    //ETIQUETA DE SUPERLATIVOS COMIENZAN POR AQS
                    if (tag.length()>2 && tag.charAt(0) == 'A' && tag.charAt(1) == 'Q' && tag.charAt(2) == 'S') {
                        notPass.add(form);
                    }
                }
            }
        }

        if(notPass.size()>0) {
            pass = false;
            String superlativos = Frecuencia.getFrecuencia(notPass);
            reason = "El texto presenta los siguientes superlativos no permitidos: [" + superlativos + "].";
        }

        return respuestaJSONDeteccion.codificador(id, name, description, pass, reason);
    }

    /* SALIDA SUPERLATIVO DETECCION:
         {
         "id":4,
         "name":"Regla - Correcion - Superlativos",
         "description":"Corregir el uso de superlativos.",
         "textCorrected": "El chico muy guapo de la esquina que también es muy alto me mira."
       }
       * */
    @GetMapping("/superlativoCorreccion")
    public String superlativoCorreccion(@RequestParam(value = "text", defaultValue = "") String text) throws IOException {

        int id = 4;
        String name = "Regla - Corrección - Superlativos";
        String description = "Corregir el uso de superlativos.";
        String textCorrected = "Not Apply";

        //Contiene el [form,tag,lemma] de los adjetivos superlativos
        List<String[]> notPass = new LinkedList<String[]>();
        String request = new requestTextServer().request(text, "tagger");

        if(request.compareTo("ERROR")==0){
            textCorrected = "Se ha producido un error interno. Pruebe de nuevo.";
            return respuestaJSONCorreccion.codificador(id, name, description, textCorrected);
        }

        JsonObject responseJSON = new Gson().fromJson(request, JsonObject.class);
        JsonArray paragraphs = responseJSON.getAsJsonArray("paragraphs");
        JsonArray sentences;
        JsonArray tokens;

        String form = "";
        String tag = "";
        String lemma = "";
        String lemmas = "";

        //Buscamos todos los superlativos en todos los parrafos, frases, palabras
        for (int i = 0; i < paragraphs.size(); i++) {
            sentences = paragraphs.get(i).getAsJsonObject().get("sentences").getAsJsonArray();
            for (int j = 0; j < sentences.size(); j++){
                tokens = sentences.get(j).getAsJsonObject().get("tokens").getAsJsonArray();
                for (int k = 0; k < tokens.size(); k++) {
                    form = tokens.get(k).getAsJsonObject().get("form").getAsString();
                    tag = tokens.get(k).getAsJsonObject().get("tag").getAsString();
                    lemma = tokens.get(k).getAsJsonObject().get("lemma").getAsString();

                    if (tag.length()>2 && tag.charAt(0) == 'A' && tag.charAt(1) == 'Q' && tag.charAt(2) == 'S') {
                        notPass.add(new String[]{form,tag,lemma});
                        lemmas = lemmas + " " + lemma;
                    }
                }
            }
        }

        if(notPass.size()>0) {
            //Llamada a la API con todos los lemmas y guardar la etiqueta para comparar luego el genero y el numero
            //Dado que solo se le pasa un parrafo con una frase, solo hace falta un bucle para recorrer los lemmas
            String request2 = new requestTextServer().request(lemmas, "tagger");
            //Contiene los lemmas y las etiquetas correspondientes de los adjetivos superlativos
            List<String[]> notPassLemmas = new LinkedList<String[]>();

            if(request2.compareTo("ERROR")==0){
                textCorrected = "Se ha producido un error interno. Pruebe de nuevo.";
                return respuestaJSONCorreccion.codificador(id, name, description, textCorrected);
            }

            JsonArray sentences2 = paragraphs.get(0).getAsJsonObject().get("sentences").getAsJsonArray();
            JsonArray tokens2 = sentences2.get(0).getAsJsonObject().get("tokens").getAsJsonArray();
            for (int k = 0; k < tokens2.size(); k++) {
                form = tokens2.get(k).getAsJsonObject().get("form").getAsString();
                tag = tokens2.get(k).getAsJsonObject().get("tag").getAsString();
                notPassLemmas.add(new String[]{form,tag});
            }
            //notPassLemmas contiene las etiquetas de los lemmas
            //Hay que ir comparando los generos de ambas para sustituir form1 por muy + lemma en el genero y numero correcto
            String tagLemma;
            String tagForm;
            for(int i=0; i<notPass.size(); i++){
                tagLemma = notPassLemmas.get(i)[1];
                tagForm = notPass.get(i)[1];
                lemma = notPass.get(i)[2];
                form = notPass.get(i)[0];

                //GENERO:
                //Dado que el lemma siempre viene en singular masculino
                //Si el genero es comun en el lemma o la form es masculino, no hay que hacer modificaciones
                //Por lo tanto, solo hay que cambiar a femenino si el genero no es comun en el lema y la form es femenina
                if(tagLemma.length()>3 && tagLemma.charAt(3)!='C' && tagForm.length()>3 && tagForm.charAt(3)=='F'){
                    //HAY QUE CAMBIAR EL LEMMA A FEMENINO
                    lemma = GeneroNumero.transformacionEnFemenino(lemma);
                }

                //NUMERO:
                //Para el caso del numero es igual
                //Si el lemma es invariante o si la forma es singular, no hace falta hacer transformaciones
                //Por lo tanto, solo hay que cambiar el lema si la form es plural
                if((tagLemma.length()>4 && tagLemma.charAt(4)=='N') ||
                        (tagForm.length()>4 && tagForm.charAt(4)=='P')) {
                    lemma = GeneroNumero.transformacionEnPlural(lemma);
                }

                //Una vez realizadas las transformaciones, ya se sustituye en el texto la form por el lemma transformado
                //Si la frase empieza por el superlativo, el muy deberia ser Muy
                //Para ver si la primera letra es mayuscula, se puede transformar el string a minusculas y comparar
                if(form.toLowerCase().compareTo(form) == 0){
                    text = text.replaceAll(form, "muy " + lemma);
                }
                else{
                    text = text.replaceAll(form, "Muy " + lemma);
                }


            }
            textCorrected = text;
        }

        return respuestaJSONCorreccion.codificador(id, name, description, textCorrected);
    }

}

