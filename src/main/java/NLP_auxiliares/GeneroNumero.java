package NLP_auxiliares;

import java.util.ArrayList;

public class GeneroNumero {

    // Transforma un adjetivo masculino en femenino
    public static String transformacionEnFemenino(String masculino){

        //Si termina en -o, -ete, -ote, se cambia la ultima vocal por a
        if ((masculino.length()>0 && masculino.charAt(masculino.length()-1) == 'o') ||
                (masculino.length()>2 && masculino.charAt(masculino.length()-1) == 'e' && masculino.charAt(masculino.length()-2) == 't' && masculino.charAt(masculino.length()-3)=='e') ||
                (masculino.length()>2 && masculino.charAt(masculino.length()-1) == 'e' && masculino.charAt(masculino.length()-2) == 't' && masculino.charAt(masculino.length()-3)=='o')){
            masculino = masculino.substring(0, masculino.length()-1);
            return masculino.concat("a");
        }

        //Si termina en consonante se anade -a al final
        else if(masculino.length()>0 && esConsonante(masculino.charAt(masculino.length()-1))){
            //Manejar la tilde si acaba en consonante: jugueton --> juguetona
            masculino = quitarTildeUltSilaba(masculino);
            return masculino.concat("a");
        }

        return masculino;
    }

    // Comprobar si un caracter es consonante
    public static boolean esConsonante(char l) {
        /* METODO 1:
        * Las variables char se pueden tratar como un entero asociandolo al código UTF-8 apropiado
        * Siguiendo los valores de la tabla ASCII, se tiene que las letras mayusculas se encuentran en (65,90)
        * Mientras que las minusculas (97,122)
        * Ademas, hay que considerar la letra enie: 164,165
        */

        /* METODO 2:
        * Dado que el metodo 1 presentaba fallos, se opta por el siguiente metodo.
        * En una lista se anaden todas las posibles vocales
        * Aquello que no este en la lista se consideraconsonante
        * */
        boolean result = false;
        ArrayList<Character> vocales = new ArrayList<Character>();
        //mayusculas sin tilde
        vocales.add('A');
        vocales.add('E');
        vocales.add('I');
        vocales.add('O');
        vocales.add('U');
        //mayusculas con tilde
        vocales.add('Á');
        vocales.add('É');
        vocales.add('Í');
        vocales.add('Ó');
        vocales.add('Ú');
        //minusculas sin tilde
        vocales.add('a');
        vocales.add('e');
        vocales.add('i');
        vocales.add('o');
        vocales.add('u');
        //minusculas con tilde
        vocales.add('á');
        vocales.add('é');
        vocales.add('í');
        vocales.add('ó');
        vocales.add('ú');

        if (!vocales.contains((Character) l)) {
            result = true;
        }
        return result;
    }

    // Este metodo es utilizado por transformacionEnFemenino
    // Funcion auxiliar para eliminar la tilde de una palabra si esta se encuentra en los ultimos 3 caracteres
    public static String quitarTildeUltSilaba(String palabra){

        ArrayList<Character> vocales = new ArrayList<Character>();
        vocales.add('a');
        vocales.add('e');
        vocales.add('i');
        vocales.add('o');
        vocales.add('u');
        ArrayList<Character> vocalesTilde = new ArrayList<Character>();
        vocalesTilde.add('á');
        vocalesTilde.add('é');
        vocalesTilde.add('í');
        vocalesTilde.add('ó');
        vocalesTilde.add('ú');

        if (palabra.length()>3 && esConsonante(palabra.charAt(palabra.length()-1))){
            // Solo se quiere eliminar la tilde si esta esta en la ultima silaba
            // Se considera que la ultima silaba estara en las ultimas 3 posiciones
            String silaba = palabra.substring(palabra.length()-3, palabra.length());
            for (int i = 0; i<vocales.size();i++){
                silaba = silaba.replace(vocalesTilde.get(i),vocales.get(i));
            }
            palabra = palabra.substring(0, palabra.length()-3) + silaba;

        }
        return palabra;
    }

    // Este metodo es utilizado para obtener el gerundio simple cuando esta acompanado por pronombres
    // Ej. dandome --> dando; comentandolo --> comentando
    // Metodo para quitar la tilde de una palabra independientemente de su posicion
    public static String quitarTilde(String palabra){

        ArrayList<Character> vocales = new ArrayList<Character>();
        vocales.add('a');
        vocales.add('e');
        vocales.add('i');
        vocales.add('o');
        vocales.add('u');

        ArrayList<Character> vocalesTilde = new ArrayList<Character>();
        vocalesTilde.add('á');
        vocalesTilde.add('é');
        vocalesTilde.add('í');
        vocalesTilde.add('ó');
        vocalesTilde.add('ú');

        for (int i = 0; i<vocalesTilde.size(); i++){
            palabra = palabra.replace(vocalesTilde.get(i), vocales.get(i));
        }

        return palabra;
    }

    // Transforma un adjetivo singular en plural
    public static String transformacionEnPlural(String singular){

        ArrayList<Character> vocalesTilde = new ArrayList<Character>();
        vocalesTilde.add('á');
        vocalesTilde.add('é');
        vocalesTilde.add('í');
        vocalesTilde.add('ó');
        vocalesTilde.add('ú');

        //Si termina en -z se sustituye por -ces
        if (singular.length()>0 && singular.charAt(singular.length()-1)=='z'){
            singular = singular.substring(0, singular.length()-1) + "ces";
        }
        //Si acaban en vocal atona se aniade s
        //Vocal atona: en este caso significa que no lleva tilde, pues si es aguda y acaba en vocal llevaria tilde
        else if(singular.length()>0 && !esConsonante(singular.charAt(singular.length()-1)) || vocalesTilde.contains(singular.charAt(singular.length()-1))){
            singular = singular + "s";
        }
        //Si acaban en vocal tonica o consonante se aniade es
        else {
            singular = quitarTildeUltSilaba(singular);
            singular = singular + "es";
        }

        return singular;
    }

    // Se va a probar el funcionamiento de estos metodos
    public static void main(String[] args) {
        System.out.println("\nFEMENINOS: ");
        String[] masculinos = new String[]{"bueno","guapo","regordete","blanco","alto","buen","gordo","juguetón","rojo"};
        for(int i=0; i< masculinos.length; i++){
            System.out.println(masculinos[i] + ": " + transformacionEnFemenino(masculinos[i]));
        }
        System.out.println("\nCONSONANTES: ");
        System.out.println("a: " + esConsonante('a'));
        System.out.println("A: " + esConsonante('A'));
        System.out.println("J: " + esConsonante('J'));
        System.out.println("e: " + esConsonante('e'));
        System.out.println("Ñ: " + esConsonante('Ñ'));
        System.out.println("ñ: " + esConsonante('ñ'));
        System.out.println("á: " + esConsonante('á'));

        System.out.println("\nSIN TILDE: ");
        System.out.println("camión: " + quitarTilde("camión"));
        System.out.println("juguetón: " + quitarTilde("juguetón"));

        System.out.println("\nPLURALES: ");
        String[] singulares = new String[]{"bueno","guapo","regordete","feliz","ideal","feroz","juguetón","azul","ceutí"};
        for(int i=0; i< masculinos.length; i++){
            System.out.println(singulares[i] + ": " + transformacionEnPlural(singulares[i]));
        }
    }
}
