package NLP_auxiliares;

import java.util.*;

public class PronombresEncliticos {

    //Generar HashMap para el texto dado de forma que la lista de claves sea la lista de posibles gerundios
    // Ej. <dando, [dandome]>, <bebiendo, [bebiendolo]>, <caminando, [caminando], <cuando, [cuando]>
    public static HashMap<String,ArrayList<String>> pronombresEncliticos(String texto){
        /*
        El HashMap no es <String, String> porque puede darse siguiente caso:
         aparecer en el mismo texto dos gerundios distintos del mismo verbo
         Ej. Estaba dandome el aceite cuando Juan y Pedro estaban dandoselo.
         Necesitamos guardar <dando, [dandome, dandoselo]>
         Esto se debe a que si dando resulta ser gerundio hay que mostrar dos incorrectos

        La clave contendra el posible gerundio (dando) y la lista los originales (dandome, dandonoslo,...)
        result contendra todos los posibles gerundios, no solo aquellos con pronombres
         */
        HashMap<String,ArrayList<String>> result = new LinkedHashMap<>();

        //Pronombres que pueden ir acompaniados de un complemento directo
        //RECORDATORIO: el pronombre 'se' sustituye a 'le' o 'les' cuando acompania al complemento directo
        ArrayList<String> grupo1 = new ArrayList<>();
        grupo1.add("me");
        grupo1.add("te");
        grupo1.add("se");
        grupo1.add("nos");
        grupo1.add("os");
        //Complementos directos, pueden ir detras de los pronombres personales
        ArrayList<String> grupo2 = new ArrayList<>();
        grupo2.add("los");
        grupo2.add("lo");
        grupo2.add("las");
        grupo2.add("la");

        /* MULTIPLE:
        Contiene las combinaciones posibles de pronombres personales con complemento directo
        Ej. compramelos, daselos, regalatela, comprandoosla,...

        Es importante tener en cuenta el orden en el que se anaden
        melos antes de melo, melas antes de mela, ...
        multiple = [melos, telos, selos, noslos, oslos, melo, telo, selo, noslo, oslo, mela, tela, ...]
        */
        ArrayList<String> multiple = new ArrayList<>();
        for (int i=0;i<grupo1.size();i++){
            for (int j = 0; j<grupo2.size();j++){
                multiple.add(grupo1.get(i)+grupo2.get(j));
            }
        }

        /* MULTIPLE:
         Ahora se aniaden el resto de pronombres personales o complementos directos de forma individual
         Tener en cuenta que el orden es importante: 'nos', 'los' tienen que comprobarse antes de 'os'
         Dado que se va a comprobar si la palabra contiene algun elemento de multiple,
         es importante ver que conociendonos contiene a 'nos' y no a 'os'
         */
        multiple.add("nos");
        multiple.add("les");
        multiple.add("los");
        multiple.add("las");

        multiple.add("me");
        multiple.add("te");
        multiple.add("lo");
        multiple.add("le");
        multiple.add("se");
        multiple.add("la");
        multiple.add("os");

        //Indicativos de posibles gerundios
        String ar = "ando";
        String ar2 = "ándo";
        String erir = "iendo";
        String erir2 = "iéndo";
        String erir3 = "yendo";

        //Eliminamos lo que no son letras para asegurar que dividimos el texto por palabras seperando con espacios
        texto = texto.replaceAll("[^A-Za-zÀ-ÿ\u00f1\u00d1]"," ");
        //Seleccionamos las palabras
        String[] palabras = texto.split(" ");

        int lpal; //longitud de la palabra
        int lpro; //longitud del pronombre
        String nueva = "";
        boolean aniadido = false;
        boolean deberiaAniadirse = false; //posible gerundio que no acaba en pronombre
        for(String palabra: palabras){
            //System.out.println("La palabra es: "+palabra);
            aniadido = false;
            //Si la palabra contiene ando o iendo es un posible gerundio
            // habra que evaluar si acaba en pronombre para separar los pronombres
            if(palabra.contains(ar) || palabra.contains(ar2) || palabra.contains(erir)
                    || palabra.contains(erir2) || palabra.contains(erir3)){
                deberiaAniadirse = true;
                for(String pronombre:multiple){
                    lpal = palabra.length();
                    lpro = pronombre.length();

                    if(lpal>lpro && palabra.substring(lpal-lpro,lpal).contains(pronombre)){
                        nueva = GeneroNumero.quitarTilde(palabra.substring(0,lpal-lpro));
                        if(result.containsKey(nueva)) {
                            result.get(nueva).add(palabra);
                        }
                        else{
                            result.put(nueva,new ArrayList<String>());
                            result.get(nueva).add(palabra);
                        }
                        aniadido = true;
                        break;
                    }

                }
                if(deberiaAniadirse && !aniadido){
                    if(result.containsKey(palabra)) {
                        result.get(palabra).add(palabra);
                    }
                    else{
                        result.put(palabra,new ArrayList<String>());
                        result.get(palabra).add(palabra);
                    }
                }

            }
        }
        /*
        for(String key: result.keySet()){
            System.out.println("\n");
            System.out.println(key);
            System.out.println(result.get(key));
        }
        */
        return result;
    }

    //Dado el HashMap que guarda las relaciones entre los gerundios y sus originales (dando --> dandome)
    // y la lista de gerundios
    //Hay que cambiar todos los elementos de la lista notPass por las palabras que se guardan en el HashMap como originales (los valores de la clave)
    public static List<String> getOriginales (HashMap<String,ArrayList<String>> origYnuev, List<String> notPass){
        //Hay que dejar la lista ready para pasarla directamente a la frecuencia
        List<String> gerundiosOriginales = new LinkedList<>();


        /* RECORDATORIO:
         Si el gerundio que venia en el texto es dando, el valor de la clave 'dando' en el HashMap es [dando]
         Si el gerundio que venia en el texto es comentandolo, el valor de la clave 'comentando' es [comentandolo]
         Luego hay que transformar todas las claves que aparecen en notPass por su valor
         Tener en cuenta que si en el texto aparece dando, dandome y dandonoslo
         El valor de la clave 'dando' sera [dando, dandome, dandonoslo] y hay que anadir todas
        */
        for(String gerundio: notPass){
            if(!gerundiosOriginales.contains(gerundio)) {
                if (origYnuev.containsKey(gerundio)) {

                    for(String e:origYnuev.get(gerundio)){
                        gerundiosOriginales.add(e);
                    }
                } else {
                    gerundiosOriginales.add(gerundio);
                }
            }
        }
        return gerundiosOriginales;
    }


    //Ejemplos
    public static void main(String[] args) {
        pronombresEncliticos("probando. muchos gerundios comiendo andándolo");
        String texto = "probándonoslos inventando comiéndolos estoy bailándomelos mientras inventándoselo está cuando inventándolo ";
        HashMap<String, ArrayList<String>> hm1 = pronombresEncliticos(texto);
        System.out.println("TEXTO: " + texto + "\n");
        System.out.println("POSIBLES GERUNDIOS: ");
        System.out.println(hm1);

        //Tras la llamada a la API LibrAIry, se tiene una lista con los gerundios de texto. En este caso:
        List<String> gerundiosHM1 = new ArrayList<>();
        gerundiosHM1.add("probando");
        gerundiosHM1.add("inventando");
        gerundiosHM1.add("comiendo");
        gerundiosHM1.add("bailando");

        System.out.println("\nGERUNDIOS DETECTADOS: ");
        System.out.println(getOriginales(hm1, gerundiosHM1));

    }
}
