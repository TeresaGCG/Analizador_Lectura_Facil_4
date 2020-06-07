package com.analizador.nlp;

import ServiciosAnalizador_auxiliares.respuestaJSONDeteccion;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenteTest {

    ruleMente rM = new ruleMente();

    ArrayList<Pair<Integer, String>> frasesPruebas;
    ArrayList<Pair<Integer, String>> frasesCorrectas;

    public MenteTest() throws IOException{
        leerPruebas();
        leerCorrecion();
    }

    //Cargar en frasesPruebas los textos del CORPES
    public void leerPruebas() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\ExportacionCORPESadverbios.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

        //Separadores para el txt
        String concordancia = "-={Concordancia}=-";
        String referencia = "-={Referencia bibliográfica}=-";
        boolean isText = false;
        int i = 0;
        String linea="";

        frasesPruebas = new ArrayList<Pair<Integer, String>>();

        while((linea=in.readLine())!=null){
            if (i==500){
                break;
            }
            if(linea.contains(referencia)){
                isText = false;
            }
            if (isText && linea.compareTo("")!=0){
                frasesPruebas.add(new Pair<>(i, linea));
                i++;
            }
            if (linea.contains(concordancia)){
                isText = true;
            }
        }
    }

    //Cargar en frasesCorrectas los adverbios que terminan en mente para cada texto del CORPES
    public void leerCorrecion() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\adverbios.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

        String linea="";
        frasesCorrectas = new ArrayList<Pair<Integer, String>>();

        int i = 1;
        while((linea=in.readLine())!=null){
            String[] fila = linea.split(";");
            if (fila.length>1){
                frasesCorrectas.add(new Pair<>(new Integer(i), fila[1]));
            }
            else{
                frasesCorrectas.add(new Pair<>(new Integer(i), ""));

            }
            i++;
        }
    }

    //Comprobar si la respuesta obtenida al llamar al servicio /mente se corresponde con frasesCorrectas
    public Pair<String,String> test(Integer i) throws IOException{
        String pingResponse;
        respuestaJSONDeteccion decoder = new respuestaJSONDeteccion();
        respuestaJSONDeteccion.result res;

        pingResponse = rM.mente(frasesPruebas.get(i).getValue());
        res = decoder.decodificador(pingResponse);

        String palabraRule = "";
        String palabraCorrecta = frasesCorrectas.get(i).getValue();

        if (res.pass == false){
            palabraRule = res.reason.split(":")[1];
            palabraRule = palabraRule.replace("[","");
            palabraRule = palabraRule.replace(" ","");
            palabraRule = palabraRule.replace("].","");

        }
        palabraCorrecta = palabraCorrecta.toLowerCase();
        palabraRule = palabraRule.toLowerCase();
        return new Pair<>(palabraCorrecta,palabraRule);

    }

    //OJO: El test mente0 comprueba el texto numero 1, mente1 el texto 2, mente2 el texto 3, etc.
    @Test
    void mente0() throws IOException {
        Integer i = 0;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente1() throws IOException {
        Integer i = 1;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente2() throws IOException {
        Integer i = 2;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente3() throws IOException {
        Integer i = 3;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente4() throws IOException {
        Integer i = 4;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente5() throws IOException {
        Integer i = 5;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente6() throws IOException {
        Integer i = 6;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente7() throws IOException {
        Integer i = 7;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente8() throws IOException {
        Integer i = 8;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente9() throws IOException {
        Integer i = 9;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente10() throws IOException {
        Integer i = 10;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente11() throws IOException {
        Integer i = 11;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }


    @Test
    void mente12() throws IOException {
        Integer i = 12;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente13() throws IOException {
        Integer i = 13;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente14() throws IOException {
        Integer i = 14;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente15() throws IOException {
        Integer i = 15;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente16() throws IOException {
        Integer i = 16;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente17() throws IOException {
        Integer i = 17;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente18() throws IOException {
        Integer i = 18;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente19() throws IOException {
        Integer i = 19;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente20() throws IOException {
        Integer i = 20;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente21() throws IOException {
        Integer i = 21;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente22() throws IOException {
        Integer i = 22;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente23() throws IOException {
        Integer i = 23;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente24() throws IOException {
        Integer i = 24;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente25() throws IOException {
        Integer i = 25;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente26() throws IOException {
        Integer i = 26;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente27() throws IOException {
        Integer i = 27;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente28() throws IOException {
        Integer i = 28;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente29() throws IOException {
        Integer i = 29;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente30() throws IOException {
        Integer i = 30;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente31() throws IOException {
        Integer i = 31;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente32() throws IOException {
        Integer i = 32;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente33() throws IOException {
        Integer i = 33;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente34() throws IOException {
        Integer i = 34;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente35() throws IOException {
        Integer i = 35;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente36() throws IOException {
        Integer i = 36;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente37() throws IOException {
        Integer i = 37;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente38() throws IOException {
        Integer i = 38;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente39() throws IOException {
        Integer i = 39;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente40() throws IOException {
        Integer i = 40;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente41() throws IOException {
        Integer i = 41;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente42() throws IOException {
        Integer i = 42;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente43() throws IOException {
        Integer i = 43;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente44() throws IOException {
        Integer i = 44;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente45() throws IOException {
        Integer i = 45;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente46() throws IOException {
        Integer i = 46;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente47() throws IOException {
        Integer i = 47;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente48() throws IOException {
        Integer i = 48;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente49() throws IOException {
        Integer i = 49;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente50() throws IOException {
        Integer i = 50;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente51() throws IOException {
        Integer i = 51;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente52() throws IOException {
        Integer i = 52;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente53() throws IOException {
        Integer i = 53;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente54() throws IOException {
        Integer i = 54;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente55() throws IOException {
        Integer i = 55;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente56() throws IOException {
        Integer i = 56;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente57() throws IOException {
        Integer i = 57;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente58() throws IOException {
        Integer i = 58;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente59() throws IOException {
        Integer i = 59;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente60() throws IOException {
        Integer i = 60;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente61() throws IOException {
        Integer i = 61;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente62() throws IOException {
        Integer i = 62;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente63() throws IOException {
        Integer i = 63;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente64() throws IOException {
        Integer i = 64;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente65() throws IOException {
        Integer i = 65;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente66() throws IOException {
        Integer i = 66;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente67() throws IOException {
        Integer i = 67;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente68() throws IOException {
        Integer i = 68;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente69() throws IOException {
        Integer i = 69;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente70() throws IOException {
        Integer i = 70;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente71() throws IOException {
        Integer i = 71;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente72() throws IOException {
        Integer i = 72;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente73() throws IOException {
        Integer i = 73;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente74() throws IOException {
        Integer i = 74;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente75() throws IOException {
        Integer i = 75;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente76() throws IOException {
        Integer i = 76;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente77() throws IOException {
        Integer i = 77;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente78() throws IOException {
        Integer i = 78;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente79() throws IOException {
        Integer i = 79;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente80() throws IOException {
        Integer i = 80;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente81() throws IOException {
        Integer i = 81;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente82() throws IOException {
        Integer i = 82;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente83() throws IOException {
        Integer i = 83;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente84() throws IOException {
        Integer i = 84;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente85() throws IOException {
        Integer i = 85;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente86() throws IOException {
        Integer i = 86;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente87() throws IOException {
        Integer i = 87;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente88() throws IOException {
        Integer i = 88;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente89() throws IOException {
        Integer i = 89;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente90() throws IOException {
        Integer i = 90;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente91() throws IOException {
        Integer i = 91;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente92() throws IOException {
        Integer i = 92;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente93() throws IOException {
        Integer i = 93;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente94() throws IOException {
        Integer i = 94;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente95() throws IOException {
        Integer i = 95;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente96() throws IOException {
        Integer i = 96;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente97() throws IOException {
        Integer i = 97;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente98() throws IOException {
        Integer i = 98;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente99() throws IOException {
        Integer i = 99;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente100() throws IOException {
        Integer i = 100;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente101() throws IOException {
        Integer i = 101;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente102() throws IOException {
        Integer i = 102;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente103() throws IOException {
        Integer i = 103;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente104() throws IOException {
        Integer i = 104;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente105() throws IOException {
        Integer i = 105;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente106() throws IOException {
        Integer i = 106;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente107() throws IOException {
        Integer i = 107;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente108() throws IOException {
        Integer i = 108;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente109() throws IOException {
        Integer i = 109;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente110() throws IOException {
        Integer i = 110;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente111() throws IOException {
        Integer i = 111;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente112() throws IOException {
        Integer i = 112;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente113() throws IOException {
        Integer i = 113;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente114() throws IOException {
        Integer i = 114;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente115() throws IOException {
        Integer i = 115;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente116() throws IOException {
        Integer i = 116;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente117() throws IOException {
        Integer i = 117;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente118() throws IOException {
        Integer i = 118;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente119() throws IOException {
        Integer i = 119;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente120() throws IOException {
        Integer i = 120;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente121() throws IOException {
        Integer i = 121;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente122() throws IOException {
        Integer i = 122;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente123() throws IOException {
        Integer i = 123;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente124() throws IOException {
        Integer i = 124;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente125() throws IOException {
        Integer i = 125;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente126() throws IOException {
        Integer i = 126;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente127() throws IOException {
        Integer i = 127;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente128() throws IOException {
        Integer i = 128;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente129() throws IOException {
        Integer i = 129;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente130() throws IOException {
        Integer i = 130;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente131() throws IOException {
        Integer i = 131;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente132() throws IOException {
        Integer i = 132;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente133() throws IOException {
        Integer i = 133;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente134() throws IOException {
        Integer i = 134;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente135() throws IOException {
        Integer i = 135;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente136() throws IOException {
        Integer i = 136;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente137() throws IOException {
        Integer i = 137;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente138() throws IOException {
        Integer i = 138;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente139() throws IOException {
        Integer i = 139;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente140() throws IOException {
        Integer i = 140;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente141() throws IOException {
        Integer i = 141;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente142() throws IOException {
        Integer i = 142;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente143() throws IOException {
        Integer i = 143;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente144() throws IOException {
        Integer i = 144;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente145() throws IOException {
        Integer i = 145;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente146() throws IOException {
        Integer i = 146;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente147() throws IOException {
        Integer i = 147;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente148() throws IOException {
        Integer i = 148;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente149() throws IOException {
        Integer i = 149;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente150() throws IOException {
        Integer i = 150;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente151() throws IOException {
        Integer i = 151;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente152() throws IOException {
        Integer i = 152;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente153() throws IOException {
        Integer i = 153;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente154() throws IOException {
        Integer i = 154;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente155() throws IOException {
        Integer i = 155;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente156() throws IOException {
        Integer i = 156;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente157() throws IOException {
        Integer i = 157;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente158() throws IOException {
        Integer i = 158;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente159() throws IOException {
        Integer i = 159;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente160() throws IOException {
        Integer i = 160;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente161() throws IOException {
        Integer i = 161;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente162() throws IOException {
        Integer i = 162;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente163() throws IOException {
        Integer i = 163;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente164() throws IOException {
        Integer i = 164;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente165() throws IOException {
        Integer i = 165;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente166() throws IOException {
        Integer i = 166;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente167() throws IOException {
        Integer i = 167;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente168() throws IOException {
        Integer i = 168;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente169() throws IOException {
        Integer i = 169;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente170() throws IOException {
        Integer i = 170;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente171() throws IOException {
        Integer i = 171;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente172() throws IOException {
        Integer i = 172;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente173() throws IOException {
        Integer i = 173;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente174() throws IOException {
        Integer i = 174;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente175() throws IOException {
        Integer i = 175;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente176() throws IOException {
        Integer i = 176;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente177() throws IOException {
        Integer i = 177;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente178() throws IOException {
        Integer i = 178;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente179() throws IOException {
        Integer i = 179;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente180() throws IOException {
        Integer i = 180;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente181() throws IOException {
        Integer i = 181;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente182() throws IOException {
        Integer i = 182;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente183() throws IOException {
        Integer i = 183;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente184() throws IOException {
        Integer i = 184;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente185() throws IOException {
        Integer i = 185;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente186() throws IOException {
        Integer i = 186;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente187() throws IOException {
        Integer i = 187;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente188() throws IOException {
        Integer i = 188;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente189() throws IOException {
        Integer i = 189;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente190() throws IOException {
        Integer i = 190;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente191() throws IOException {
        Integer i = 191;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente192() throws IOException {
        Integer i = 192;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente193() throws IOException {
        Integer i = 193;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente194() throws IOException {
        Integer i = 194;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente195() throws IOException {
        Integer i = 195;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente196() throws IOException {
        Integer i = 196;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente197() throws IOException {
        Integer i = 197;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente198() throws IOException {
        Integer i = 198;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente199() throws IOException {
        Integer i = 199;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente200() throws IOException {
        Integer i = 200;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente201() throws IOException {
        Integer i = 201;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente202() throws IOException {
        Integer i = 202;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente203() throws IOException {
        Integer i = 203;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente204() throws IOException {
        Integer i = 204;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente205() throws IOException {
        Integer i = 205;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente206() throws IOException {
        Integer i = 206;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente207() throws IOException {
        Integer i = 207;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente208() throws IOException {
        Integer i = 208;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente209() throws IOException {
        Integer i = 209;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente210() throws IOException {
        Integer i = 210;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente211() throws IOException {
        Integer i = 211;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente212() throws IOException {
        Integer i = 212;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente213() throws IOException {
        Integer i = 213;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente214() throws IOException {
        Integer i = 214;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente215() throws IOException {
        Integer i = 215;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente216() throws IOException {
        Integer i = 216;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente217() throws IOException {
        Integer i = 217;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente218() throws IOException {
        Integer i = 218;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente219() throws IOException {
        Integer i = 219;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente220() throws IOException {
        Integer i = 220;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente221() throws IOException {
        Integer i = 221;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente222() throws IOException {
        Integer i = 222;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente223() throws IOException {
        Integer i = 223;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente224() throws IOException {
        Integer i = 224;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente225() throws IOException {
        Integer i = 225;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente226() throws IOException {
        Integer i = 226;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente227() throws IOException {
        Integer i = 227;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente228() throws IOException {
        Integer i = 228;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente229() throws IOException {
        Integer i = 229;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente230() throws IOException {
        Integer i = 230;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente231() throws IOException {
        Integer i = 231;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente232() throws IOException {
        Integer i = 232;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente233() throws IOException {
        Integer i = 233;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente234() throws IOException {
        Integer i = 234;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente235() throws IOException {
        Integer i = 235;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente236() throws IOException {
        Integer i = 236;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente237() throws IOException {
        Integer i = 237;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente238() throws IOException {
        Integer i = 238;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente239() throws IOException {
        Integer i = 239;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente240() throws IOException {
        Integer i = 240;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente241() throws IOException {
        Integer i = 241;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente242() throws IOException {
        Integer i = 242;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente243() throws IOException {
        Integer i = 243;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente244() throws IOException {
        Integer i = 244;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente245() throws IOException {
        Integer i = 245;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente246() throws IOException {
        Integer i = 246;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente247() throws IOException {
        Integer i = 247;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente248() throws IOException {
        Integer i = 248;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente249() throws IOException {
        Integer i = 249;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente250() throws IOException {
        Integer i = 250;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente251() throws IOException {
        Integer i = 251;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente252() throws IOException {
        Integer i = 252;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente253() throws IOException {
        Integer i = 253;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente254() throws IOException {
        Integer i = 254;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente255() throws IOException {
        Integer i = 255;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente256() throws IOException {
        Integer i = 256;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente257() throws IOException {
        Integer i = 257;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente258() throws IOException {
        Integer i = 258;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente259() throws IOException {
        Integer i = 259;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente260() throws IOException {
        Integer i = 260;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente261() throws IOException {
        Integer i = 261;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente262() throws IOException {
        Integer i = 262;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente263() throws IOException {
        Integer i = 263;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente264() throws IOException {
        Integer i = 264;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente265() throws IOException {
        Integer i = 265;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente266() throws IOException {
        Integer i = 266;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente267() throws IOException {
        Integer i = 267;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente268() throws IOException {
        Integer i = 268;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente269() throws IOException {
        Integer i = 269;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente270() throws IOException {
        Integer i = 270;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente271() throws IOException {
        Integer i = 271;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente272() throws IOException {
        Integer i = 272;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente273() throws IOException {
        Integer i = 273;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente274() throws IOException {
        Integer i = 274;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente275() throws IOException {
        Integer i = 275;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente276() throws IOException {
        Integer i = 276;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente277() throws IOException {
        Integer i = 277;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente278() throws IOException {
        Integer i = 278;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente279() throws IOException {
        Integer i = 279;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente280() throws IOException {
        Integer i = 280;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente281() throws IOException {
        Integer i = 281;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente282() throws IOException {
        Integer i = 282;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente283() throws IOException {
        Integer i = 283;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente284() throws IOException {
        Integer i = 284;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente285() throws IOException {
        Integer i = 285;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente286() throws IOException {
        Integer i = 286;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente287() throws IOException {
        Integer i = 287;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente288() throws IOException {
        Integer i = 288;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente289() throws IOException {
        Integer i = 289;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente290() throws IOException {
        Integer i = 290;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente291() throws IOException {
        Integer i = 291;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente292() throws IOException {
        Integer i = 292;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente293() throws IOException {
        Integer i = 293;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente294() throws IOException {
        Integer i = 294;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente295() throws IOException {
        Integer i = 295;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente296() throws IOException {
        Integer i = 296;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente297() throws IOException {
        Integer i = 297;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente298() throws IOException {
        Integer i = 298;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente299() throws IOException {
        Integer i = 299;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente300() throws IOException {
        Integer i = 300;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente301() throws IOException {
        Integer i = 301;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente302() throws IOException {
        Integer i = 302;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente303() throws IOException {
        Integer i = 303;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente304() throws IOException {
        Integer i = 304;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente305() throws IOException {
        Integer i = 305;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente306() throws IOException {
        Integer i = 306;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente307() throws IOException {
        Integer i = 307;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente308() throws IOException {
        Integer i = 308;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente309() throws IOException {
        Integer i = 309;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente310() throws IOException {
        Integer i = 310;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente311() throws IOException {
        Integer i = 311;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente312() throws IOException {
        Integer i = 312;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente313() throws IOException {
        Integer i = 313;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente314() throws IOException {
        Integer i = 314;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente315() throws IOException {
        Integer i = 315;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente316() throws IOException {
        Integer i = 316;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente317() throws IOException {
        Integer i = 317;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente318() throws IOException {
        Integer i = 318;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente319() throws IOException {
        Integer i = 319;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente320() throws IOException {
        Integer i = 320;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente321() throws IOException {
        Integer i = 321;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente322() throws IOException {
        Integer i = 322;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente323() throws IOException {
        Integer i = 323;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente324() throws IOException {
        Integer i = 324;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente325() throws IOException {
        Integer i = 325;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente326() throws IOException {
        Integer i = 326;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente327() throws IOException {
        Integer i = 327;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente328() throws IOException {
        Integer i = 328;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente329() throws IOException {
        Integer i = 329;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente330() throws IOException {
        Integer i = 330;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente331() throws IOException {
        Integer i = 331;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente332() throws IOException {
        Integer i = 332;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente333() throws IOException {
        Integer i = 333;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente334() throws IOException {
        Integer i = 334;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente335() throws IOException {
        Integer i = 335;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente336() throws IOException {
        Integer i = 336;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente337() throws IOException {
        Integer i = 337;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente338() throws IOException {
        Integer i = 338;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente339() throws IOException {
        Integer i = 339;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente340() throws IOException {
        Integer i = 340;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente341() throws IOException {
        Integer i = 341;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente342() throws IOException {
        Integer i = 342;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente343() throws IOException {
        Integer i = 343;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente344() throws IOException {
        Integer i = 344;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente345() throws IOException {
        Integer i = 345;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente346() throws IOException {
        Integer i = 346;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente347() throws IOException {
        Integer i = 347;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente348() throws IOException {
        Integer i = 348;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente349() throws IOException {
        Integer i = 349;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente350() throws IOException {
        Integer i = 350;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente351() throws IOException {
        Integer i = 351;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente352() throws IOException {
        Integer i = 352;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente353() throws IOException {
        Integer i = 353;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente354() throws IOException {
        Integer i = 354;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente355() throws IOException {
        Integer i = 355;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente356() throws IOException {
        Integer i = 356;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente357() throws IOException {
        Integer i = 357;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente358() throws IOException {
        Integer i = 358;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente359() throws IOException {
        Integer i = 359;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente360() throws IOException {
        Integer i = 360;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente361() throws IOException {
        Integer i = 361;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente362() throws IOException {
        Integer i = 362;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente363() throws IOException {
        Integer i = 363;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente364() throws IOException {
        Integer i = 364;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente365() throws IOException {
        Integer i = 365;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente366() throws IOException {
        Integer i = 366;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente367() throws IOException {
        Integer i = 367;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente368() throws IOException {
        Integer i = 368;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente369() throws IOException {
        Integer i = 369;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente370() throws IOException {
        Integer i = 370;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente371() throws IOException {
        Integer i = 371;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente372() throws IOException {
        Integer i = 372;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente373() throws IOException {
        Integer i = 373;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente374() throws IOException {
        Integer i = 374;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente375() throws IOException {
        Integer i = 375;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente376() throws IOException {
        Integer i = 376;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente377() throws IOException {
        Integer i = 377;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente378() throws IOException {
        Integer i = 378;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente379() throws IOException {
        Integer i = 379;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente380() throws IOException {
        Integer i = 380;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente381() throws IOException {
        Integer i = 381;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente382() throws IOException {
        Integer i = 382;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente383() throws IOException {
        Integer i = 383;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente384() throws IOException {
        Integer i = 384;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente385() throws IOException {
        Integer i = 385;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente386() throws IOException {
        Integer i = 386;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente387() throws IOException {
        Integer i = 387;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente388() throws IOException {
        Integer i = 388;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente389() throws IOException {
        Integer i = 389;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente390() throws IOException {
        Integer i = 390;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente391() throws IOException {
        Integer i = 391;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente392() throws IOException {
        Integer i = 392;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente393() throws IOException {
        Integer i = 393;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente394() throws IOException {
        Integer i = 394;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente395() throws IOException {
        Integer i = 395;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente396() throws IOException {
        Integer i = 396;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente397() throws IOException {
        Integer i = 397;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente398() throws IOException {
        Integer i = 398;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente399() throws IOException {
        Integer i = 399;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente400() throws IOException {
        Integer i = 400;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente401() throws IOException {
        Integer i = 401;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente402() throws IOException {
        Integer i = 402;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente403() throws IOException {
        Integer i = 403;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente404() throws IOException {
        Integer i = 404;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente405() throws IOException {
        Integer i = 405;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente406() throws IOException {
        Integer i = 406;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente407() throws IOException {
        Integer i = 407;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente408() throws IOException {
        Integer i = 408;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente409() throws IOException {
        Integer i = 409;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente410() throws IOException {
        Integer i = 410;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente411() throws IOException {
        Integer i = 411;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente412() throws IOException {
        Integer i = 412;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente413() throws IOException {
        Integer i = 413;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente414() throws IOException {
        Integer i = 414;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente415() throws IOException {
        Integer i = 415;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente416() throws IOException {
        Integer i = 416;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente417() throws IOException {
        Integer i = 417;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente418() throws IOException {
        Integer i = 418;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente419() throws IOException {
        Integer i = 419;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente420() throws IOException {
        Integer i = 420;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente421() throws IOException {
        Integer i = 421;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente422() throws IOException {
        Integer i = 422;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente423() throws IOException {
        Integer i = 423;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente424() throws IOException {
        Integer i = 424;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente425() throws IOException {
        Integer i = 425;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente426() throws IOException {
        Integer i = 426;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente427() throws IOException {
        Integer i = 427;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente428() throws IOException {
        Integer i = 428;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente429() throws IOException {
        Integer i = 429;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente430() throws IOException {
        Integer i = 430;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente431() throws IOException {
        Integer i = 431;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente432() throws IOException {
        Integer i = 432;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente433() throws IOException {
        Integer i = 433;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente434() throws IOException {
        Integer i = 434;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente435() throws IOException {
        Integer i = 435;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente436() throws IOException {
        Integer i = 436;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente437() throws IOException {
        Integer i = 437;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente438() throws IOException {
        Integer i = 438;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente439() throws IOException {
        Integer i = 439;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente440() throws IOException {
        Integer i = 440;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente441() throws IOException {
        Integer i = 441;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente442() throws IOException {
        Integer i = 442;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente443() throws IOException {
        Integer i = 443;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente444() throws IOException {
        Integer i = 444;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente445() throws IOException {
        Integer i = 445;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente446() throws IOException {
        Integer i = 446;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente447() throws IOException {
        Integer i = 447;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente448() throws IOException {
        Integer i = 448;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente449() throws IOException {
        Integer i = 449;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente450() throws IOException {
        Integer i = 450;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente451() throws IOException {
        Integer i = 451;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente452() throws IOException {
        Integer i = 452;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente453() throws IOException {
        Integer i = 453;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente454() throws IOException {
        Integer i = 454;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente455() throws IOException {
        Integer i = 455;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente456() throws IOException {
        Integer i = 456;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente457() throws IOException {
        Integer i = 457;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente458() throws IOException {
        Integer i = 458;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente459() throws IOException {
        Integer i = 459;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente460() throws IOException {
        Integer i = 460;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente461() throws IOException {
        Integer i = 461;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente462() throws IOException {
        Integer i = 462;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente463() throws IOException {
        Integer i = 463;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente464() throws IOException {
        Integer i = 464;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente465() throws IOException {
        Integer i = 465;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente466() throws IOException {
        Integer i = 466;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente467() throws IOException {
        Integer i = 467;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente468() throws IOException {
        Integer i = 468;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente469() throws IOException {
        Integer i = 469;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente470() throws IOException {
        Integer i = 470;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente471() throws IOException {
        Integer i = 471;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente472() throws IOException {
        Integer i = 472;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente473() throws IOException {
        Integer i = 473;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente474() throws IOException {
        Integer i = 474;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente475() throws IOException {
        Integer i = 475;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente476() throws IOException {
        Integer i = 476;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente477() throws IOException {
        Integer i = 477;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente478() throws IOException {
        Integer i = 478;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente479() throws IOException {
        Integer i = 479;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente480() throws IOException {
        Integer i = 480;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente481() throws IOException {
        Integer i = 481;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente482() throws IOException {
        Integer i = 482;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente483() throws IOException {
        Integer i = 483;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente484() throws IOException {
        Integer i = 484;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente485() throws IOException {
        Integer i = 485;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente486() throws IOException {
        Integer i = 486;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente487() throws IOException {
        Integer i = 487;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente488() throws IOException {
        Integer i = 488;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente489() throws IOException {
        Integer i = 489;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente490() throws IOException {
        Integer i = 490;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }



    @Test
    void mente491() throws IOException {
        Integer i = 491;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente492() throws IOException {
        Integer i = 492;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente493() throws IOException {
        Integer i = 493;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente494() throws IOException {
        Integer i = 494;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente495() throws IOException {
        Integer i = 495;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente496() throws IOException {
        Integer i = 496;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente497() throws IOException {
        Integer i = 497;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente498() throws IOException {
        Integer i = 498;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

    @Test
    void mente499() throws IOException {
        Integer i = 499;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla mente: fallo en " + (i+1));
    }

}