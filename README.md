Pasos para la Instalacion:
  1) descargar el proyecto o usar git clone para obtener los datos del repo
  2) configurar el archivo "application.propertis" dentro de la carpeta "src/main/resources" con sus crendenciales de base de datos
  3) luego de agregadas las credenciales dar click derecho en el proyecto "tipo de cambio" y dar click en "clean and build"
  4) luego de ejecutado compilar el proyecto


el esquema de base de datos utilizado es el siguiente 

create database tipocambio;
use tipocambio;

drop table tipo_Cambio
CREATE TABLE tipo_Cambio(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
xml_response LONGTEXT,
timestamp DATETIME
);
