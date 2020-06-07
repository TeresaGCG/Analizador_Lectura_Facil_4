# Analizador_Lectura_Facil_4
Este código pertenece al Trabajo de Fin de Grado desarrollado por Teresa Gómez-Carpintero García.

Tiene la estructura de un proyecto de Java desarrollado con la herramienta IntelliJ IDEA. El archivo pom.xml contiene información sobre las librerías del proyecto y los detalles de configuración utilizados por Maven . Existen dos directorios en el proyecto:

•	src/main/java: directorio principal del proyecto. Está dividido a su vez en cuatro directorios diferentes:
  o	NLP_auxiliares: contiene clases auxiliares utilizadas para el desarrollo de los servicios web que están relacionadas con el lenguaje natural.
  o	NLP_services: contiene clases auxiliares para realizar peticiones a los procesadores de lenguaje natural utilizados, es decir, LibrAIry o TextServer.
  o	ServiciosAnalizador_auxiliares: contiene las clases auxiliares necesarias para la creación de los JSON de los servicios web..
  o	com/analizador/nlp: contiene tres clases para los servicios web desarrollados. La clase ruleGerundio.java para el servicio /gerundio, la clave ruleMente.java para el servicio /mente, y la clase ruleSuperlativo.java contiene los servicios /superlativoDeteccion y /superlativoCorreccion.

•	scr/test/java: directorio para las pruebas unitarias realizadas con JUnit. Está dividido en dos directorios:
  o	TextosRAE: contiene los archivos generados con el CORPES XXI y los archivos .csv para la comprobación de los mismos.
  o	com/analizador/nlp: contiene las tres clases con las pruebas unitarias realizadas con JUnit para los servicios web /gerundio, /mente y /superlativoDeteccion.

El JSON que devuelven los servicios /mente, /gerundio, /superlativoDeteccion sigue la siguiente estructura:
{
  "id":int,
  "name":"string",
  "description":"string",
  "pass": boolean,
  "reason":"string" 
}

El JSON que devuelve el servicio /superlativoCorreccion sigue la siguiente estructura:
{
  "id":int,
  "name":"string",
  "description":"string",
  "textCorrected": "string"
}
