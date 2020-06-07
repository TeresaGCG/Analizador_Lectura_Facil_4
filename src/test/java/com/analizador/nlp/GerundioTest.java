package com.analizador.nlp;

import ServiciosAnalizador_auxiliares.respuestaJSONDeteccion;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GerundioTest {

    ruleGerundio rG = new ruleGerundio();

    ArrayList<Pair<Integer, String>> frasesPruebas;
    ArrayList<Pair<Integer, String>> frasesCorrectas;

    public GerundioTest() throws IOException{
        leerPruebas();
        leerCorrecion();
    }

    //Cargar en frasesPruebas los textos del CORPES
    public void leerPruebas() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\ExportacionCORPESgerundio.txt");
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

    //Cargar en frasesCorrectas los gerundios para cada texto del CORPES
    public void leerCorrecion() throws IOException {
        File archivo = new File (".\\src\\test\\java\\TextosRAE\\gerundio.csv");
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

    //Comprobar si la respuesta obtenida al llamar al servicio /gerundio se corresponde con frasesCorrectas
    public Pair<String,String> test(Integer i) throws IOException{
        String pingResponse;
        respuestaJSONDeteccion decoder = new respuestaJSONDeteccion();
        respuestaJSONDeteccion.result res;

        pingResponse = rG.gerundio(frasesPruebas.get(i).getValue());
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

    //OJO: El test gerundio0 comprueba el texto numero 1, gerundio1 el texto 2, gerundio2 el texto 3, etc.
    @Test
    void gerundio0() throws IOException {
        Integer i = 0;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio1() throws IOException {
        Integer i = 1;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio2() throws IOException {
        Integer i = 2;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio3() throws IOException {
        Integer i = 3;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio4() throws IOException {
        Integer i = 4;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio5() throws IOException {
        Integer i = 5;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio6() throws IOException {
        Integer i = 6;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio7() throws IOException {
        Integer i = 7;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio8() throws IOException {
        Integer i = 8;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio9() throws IOException {
        Integer i = 9;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio10() throws IOException {
        Integer i = 10;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio11() throws IOException {
        Integer i = 11;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio12() throws IOException {
        Integer i = 12;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio13() throws IOException {
        Integer i = 13;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio14() throws IOException {
        Integer i = 14;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio15() throws IOException {
        Integer i = 15;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio16() throws IOException {
        Integer i = 16;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio17() throws IOException {
        Integer i = 17;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio18() throws IOException {
        Integer i = 18;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio19() throws IOException {
        Integer i = 19;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio20() throws IOException {
        Integer i = 20;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio21() throws IOException {
        Integer i = 21;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio22() throws IOException {
        Integer i = 22;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio23() throws IOException {
        Integer i = 23;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio24() throws IOException {
        Integer i = 24;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio25() throws IOException {
        Integer i = 25;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio26() throws IOException {
        Integer i = 26;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio27() throws IOException {
        Integer i = 27;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio28() throws IOException {
        Integer i = 28;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio29() throws IOException {
        Integer i = 29;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio30() throws IOException {
        Integer i = 30;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio31() throws IOException {
        Integer i = 31;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio32() throws IOException {
        Integer i = 32;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio33() throws IOException {
        Integer i = 33;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio34() throws IOException {
        Integer i = 34;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio35() throws IOException {
        Integer i = 35;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio36() throws IOException {
        Integer i = 36;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio37() throws IOException {
        Integer i = 37;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio38() throws IOException {
        Integer i = 38;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio39() throws IOException {
        Integer i = 39;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio40() throws IOException {
        Integer i = 40;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio41() throws IOException {
        Integer i = 41;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio42() throws IOException {
        Integer i = 42;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio43() throws IOException {
        Integer i = 43;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio44() throws IOException {
        Integer i = 44;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio45() throws IOException {
        Integer i = 45;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio46() throws IOException {
        Integer i = 46;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio47() throws IOException {
        Integer i = 47;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio48() throws IOException {
        Integer i = 48;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio49() throws IOException {
        Integer i = 49;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio50() throws IOException {
        Integer i = 50;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio51() throws IOException {
        Integer i = 51;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio52() throws IOException {
        Integer i = 52;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio53() throws IOException {
        Integer i = 53;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio54() throws IOException {
        Integer i = 54;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio55() throws IOException {
        Integer i = 55;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio56() throws IOException {
        Integer i = 56;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio57() throws IOException {
        Integer i = 57;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio58() throws IOException {
        Integer i = 58;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }

    @Test
    void gerundio59() throws IOException {
        Integer i = 59;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }


    @Test
    void gerundio60() throws IOException {
        Integer i = 60;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio61() throws IOException {
        Integer i = 61;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio62() throws IOException {
        Integer i = 62;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio63() throws IOException {
        Integer i = 63;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio64() throws IOException {
        Integer i = 64;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio65() throws IOException {
        Integer i = 65;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio66() throws IOException {
        Integer i = 66;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio67() throws IOException {
        Integer i = 67;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio68() throws IOException {
        Integer i = 68;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio69() throws IOException {
        Integer i = 69;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio70() throws IOException {
        Integer i = 70;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio71() throws IOException {
        Integer i = 71;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio72() throws IOException {
        Integer i = 72;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio73() throws IOException {
        Integer i = 73;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio74() throws IOException {
        Integer i = 74;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio75() throws IOException {
        Integer i = 75;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio76() throws IOException {
        Integer i = 76;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio77() throws IOException {
        Integer i = 77;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio78() throws IOException {
        Integer i = 78;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio79() throws IOException {
        Integer i = 79;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio80() throws IOException {
        Integer i = 80;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio81() throws IOException {
        Integer i = 81;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio82() throws IOException {
        Integer i = 82;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio83() throws IOException {
        Integer i = 83;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio84() throws IOException {
        Integer i = 84;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio85() throws IOException {
        Integer i = 85;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio86() throws IOException {
        Integer i = 86;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio87() throws IOException {
        Integer i = 87;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio88() throws IOException {
        Integer i = 88;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio89() throws IOException {
        Integer i = 89;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio90() throws IOException {
        Integer i = 90;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio91() throws IOException {
        Integer i = 91;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio92() throws IOException {
        Integer i = 92;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio93() throws IOException {
        Integer i = 93;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio94() throws IOException {
        Integer i = 94;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio95() throws IOException {
        Integer i = 95;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio96() throws IOException {
        Integer i = 96;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio97() throws IOException {
        Integer i = 97;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio98() throws IOException {
        Integer i = 98;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }



    @Test
    void gerundio99() throws IOException {
        Integer i = 99;
        Pair<String,String> t = test(i);
        assertEquals(t.getKey(), t.getValue(), "Regla gerundio: fallo en " + (i+1));
    }


}
