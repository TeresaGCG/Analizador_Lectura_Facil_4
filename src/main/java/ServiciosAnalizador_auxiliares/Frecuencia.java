package ServiciosAnalizador_auxiliares;


import java.util.*;

public class Frecuencia {

    /*
     Dada una lista de palabras, obtiene la frecuencia de cada una en la lista
     Devuelve en un HashMap con una entrada por palabra indicando su frencuencia
     Ej. elementos = [comiendo, andando, corriendo, pasando, pasando, comiendo, comiendo]
     HashMap = <comiendo,3>,<andando,1>,<corriendo,1>,<pasando,2>"
    */
    private static HashMap<String, Integer> repeticiones(List<String> elementos){

        HashMap<String,Integer> freq = new LinkedHashMap<>();
        for(String e:elementos){
            if(freq.containsKey(e)){
                freq.put(e,freq.get(e)+1);
            }
            else{
                freq.put(e,1);
            }
        }
        return freq;
    }

    /*
     Dada una lista de palabras, obtiene la frecuencia de cada una en la lista
     Devuelve en un string cada palabra indicando su frencuencia entre parentesis
     Si la frecuencia es 1, no se indica
     Ej. elementos = [comiendo, andando, corriendo, pasando, pasando, comiendo, comiendo]
     String = "comiendo(3),andando,corriendo,pasando(2)"
    */
    public static String getFrecuencia(List<String> elementos){
        String result = "";

        HashMap<String,Integer> freq = repeticiones(elementos);

        for(String palabra:freq.keySet()){
            if (result.isEmpty()){
                if(freq.get(palabra)>1){
                    result = palabra + "(" + freq.get(palabra) + ")";
                }
                else{
                    result = palabra;
                }
            }else {
                if (freq.get(palabra) > 1) {
                    result = result + "," + palabra + "(" + freq.get(palabra) + ")";
                } else {
                    result = result + "," + palabra;
                }
            }
        }
        return result;
    }

    //Ejemplos
    public static void main(String[] args) {
        System.out.println("[esto, acaba, siempre, esto, esto, esto]");
        List<String> p = new LinkedList<>();
        p.add("esto");
        p.add("acaba");
        p.add("siempre");
        p.add("esto");
        p.add("esto");
        p.add("esto");
        System.out.println(getFrecuencia(p));

        System.out.println("\n[comiendo, andando, corriendo, pasando, pasando, comiendo, comiendo]");
        List<String> elementos = new LinkedList<>();
        elementos.add("comiendo");
        elementos.add("andando");
        elementos.add("corriendo");
        elementos.add("pasando");
        elementos.add("pasando");
        elementos.add("comiendo");
        elementos.add("comiendo");
        System.out.println(getFrecuencia(elementos));
    }
}
