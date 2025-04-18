# Odontologica
Proyecto de git creado para alojar el repositorio del taller de java JSP `Odontológica`

# Instrucciones de Despliegue

## Requisitos previos
- **Java**: Asegúrese de tener instalada la versión 1.8 de Java.
- **Tomcat**: Utilice la versión 8.5.96 de Apache Tomcat (se puede usar la version de xampp).

## Pasos para el despliegue
1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/juanpcortes-ucompensar/Odontologica.git
    ```
2. **Importar el proyecto** en su IDE preferido (Eclipse, IntelliJ, etc.). Para el desarrollo del mismo se utilizó Visual Studio Code.
3. **Compilar el proyecto** utilizando Maven:
    ```bash
    mvn clean install
    ```
4. **Desplegar en Tomcat**:
    - Copie el archivo `odontologica.war` generado en la carpeta `target` a la carpeta `webapps` de su instalación de Tomcat.
    - Inicie el servidor Tomcat.

## Acceso a la aplicación
- **URL predeterminada**: [http://localhost:8080/odontologica/index](http://localhost:8080/odontologica/index)

## Adicional:
    - Nombre de la base de datos: ondotologicadb
