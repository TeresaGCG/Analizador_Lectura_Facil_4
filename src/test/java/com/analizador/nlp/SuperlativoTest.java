package com.analizador.nlp;

import ServiciosAnalizador_auxiliares.respuestaJSONDeteccion;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SuperlativoTest {

    ruleSuperlativo rS = new ruleSuperlativo();

    ArrayList<Pair<Integer, String>> frasesPruebas;
    ArrayList<Pair<Integer, String>> frasesCorrectas;

    public SuperlativoTest() throws IOException{
        leerPruebas();
        leerCorrecion();
    }

    //Cargar en frasesPruebas los textos del CORPES
    public void leerPruebas() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\ExportacionCORPESsuperlativoAdjetivo.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

        //Separadores para el txt
        String concordancia = "-={Concordancia}=-";
        String referencia = "-={Referencia bibliográfica}=-";
        boolean isText = false;
        int i = 0;
        String linea = "";

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

    //Cargar en frasesCorrectas los superlativos para cada texto del CORPES
    public void leerCorrecion() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\superlativos_adjetivos.csv");
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
                frasesCorrectas.add(new Pair<>(new Integer(fila[0]), ""));
            }
            i++;
        }
    }

    //Comprobar si la respuesta obtenida al llamar al servicio /superlativoDeteccion se corresponde con frasesCorrectas
    public Pair<String,String> test(Integer i) throws IOException{
        String pingResponse;
        respuestaJSONDeteccion decoder = new respuestaJSONDeteccion();
        respuestaJSONDeteccion.result res;

        pingResponse = rS.superlativoDeteccion(frasesPruebas.get(i).getValue());
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

    //OJO: El test superlativo0 comprueba el texto numero 1, superlativo1 el texto 2, superlativo2 el texto 3, etc.
    @Test
    void superlativo0() throws IOException {
        Integer i = 0;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo1() throws IOException {
        Integer i = 1;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo2() throws IOException {
        Integer i = 2;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo3() throws IOException {
        Integer i = 3;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo4() throws IOException {
        Integer i = 4;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo5() throws IOException {
        Integer i = 5;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo6() throws IOException {
        Integer i = 6;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo7() throws IOException {
        Integer i = 7;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo8() throws IOException {
        Integer i = 8;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo9() throws IOException {
        Integer i = 9;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo10() throws IOException {
        Integer i = 10;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo11() throws IOException {
        Integer i = 11;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo12() throws IOException {
        Integer i = 12;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo13() throws IOException {
        Integer i = 13;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo14() throws IOException {
        Integer i = 14;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo15() throws IOException {
        Integer i = 15;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo16() throws IOException {
        Integer i = 16;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo17() throws IOException {
        Integer i = 17;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo18() throws IOException {
        Integer i = 18;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo19() throws IOException {
        Integer i = 19;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo20() throws IOException {
        Integer i = 20;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo21() throws IOException {
        Integer i = 21;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo22() throws IOException {
        Integer i = 22;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo23() throws IOException {
        Integer i = 23;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo24() throws IOException {
        Integer i = 24;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo25() throws IOException {
        Integer i = 25;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo26() throws IOException {
        Integer i = 26;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo27() throws IOException {
        Integer i = 27;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo28() throws IOException {
        Integer i = 28;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo29() throws IOException {
        Integer i = 29;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo30() throws IOException {
        Integer i = 30;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo31() throws IOException {
        Integer i = 31;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo32() throws IOException {
        Integer i = 32;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo33() throws IOException {
        Integer i = 33;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo34() throws IOException {
        Integer i = 34;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo35() throws IOException {
        Integer i = 35;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo36() throws IOException {
        Integer i = 36;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo37() throws IOException {
        Integer i = 37;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo38() throws IOException {
        Integer i = 38;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo39() throws IOException {
        Integer i = 39;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo40() throws IOException {
        Integer i = 40;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo41() throws IOException {
        Integer i = 41;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo42() throws IOException {
        Integer i = 42;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo43() throws IOException {
        Integer i = 43;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo44() throws IOException {
        Integer i = 44;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo45() throws IOException {
        Integer i = 45;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo46() throws IOException {
        Integer i = 46;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo47() throws IOException {
        Integer i = 47;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo48() throws IOException {
        Integer i = 48;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo49() throws IOException {
        Integer i = 49;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo50() throws IOException {
        Integer i = 50;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo51() throws IOException {
        Integer i = 51;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo52() throws IOException {
        Integer i = 52;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo53() throws IOException {
        Integer i = 53;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo54() throws IOException {
        Integer i = 54;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo55() throws IOException {
        Integer i = 55;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo56() throws IOException {
        Integer i = 56;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo57() throws IOException {
        Integer i = 57;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo58() throws IOException {
        Integer i = 58;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }

    @Test
    void superlativo59() throws IOException {
        Integer i = 59;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }


    @Test
    void superlativo60() throws IOException {
        Integer i = 60;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo61() throws IOException {
        Integer i = 61;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo62() throws IOException {
        Integer i = 62;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo63() throws IOException {
        Integer i = 63;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo64() throws IOException {
        Integer i = 64;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo65() throws IOException {
        Integer i = 65;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo66() throws IOException {
        Integer i = 66;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo67() throws IOException {
        Integer i = 67;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo68() throws IOException {
        Integer i = 68;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo69() throws IOException {
        Integer i = 69;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo70() throws IOException {
        Integer i = 70;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo71() throws IOException {
        Integer i = 71;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo72() throws IOException {
        Integer i = 72;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo73() throws IOException {
        Integer i = 73;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo74() throws IOException {
        Integer i = 74;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo75() throws IOException {
        Integer i = 75;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo76() throws IOException {
        Integer i = 76;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo77() throws IOException {
        Integer i = 77;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo78() throws IOException {
        Integer i = 78;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo79() throws IOException {
        Integer i = 79;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo80() throws IOException {
        Integer i = 80;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo81() throws IOException {
        Integer i = 81;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo82() throws IOException {
        Integer i = 82;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo83() throws IOException {
        Integer i = 83;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo84() throws IOException {
        Integer i = 84;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo85() throws IOException {
        Integer i = 85;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo86() throws IOException {
        Integer i = 86;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo87() throws IOException {
        Integer i = 87;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo88() throws IOException {
        Integer i = 88;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo89() throws IOException {
        Integer i = 89;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo90() throws IOException {
        Integer i = 90;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo91() throws IOException {
        Integer i = 91;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo92() throws IOException {
        Integer i = 92;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo93() throws IOException {
        Integer i = 93;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo94() throws IOException {
        Integer i = 94;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo95() throws IOException {
        Integer i = 95;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo96() throws IOException {
        Integer i = 96;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo97() throws IOException {
        Integer i = 97;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo98() throws IOException {
        Integer i = 98;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }



    @Test
    void superlativo99() throws IOException {
        Integer i = 99;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla superlativo: fallo en " + (i+1));
    }


}
