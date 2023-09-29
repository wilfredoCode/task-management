# task-management
Task Management Microservice, built with Spring Boot
# Ejecución del Proyecto

## Ejecución con Docker Desktop

1. Abre Docker Desktop y asegúrate de que la franja inferior izquierda esté en verde.

2. Abre la consola en la ruta raíz del proyecto (al mismo nivel que el archivo Dockerfile).

3. Ejecuta el siguiente comando. Esto descargará las imágenes necesarias para la ejecución del proyecto (imagen de Java 11 y Maven) y creará la imagen para poder ejecutarla:

    ```
    docker build -t task-management:test .
    ```

4. Con la imagen construida, vamos a ejecutarla con el siguiente comando:

    ```
    docker run -p 8081:8080 task-management:test
    ```

5. Cuando en la consola aparezca el mensaje "Started TaskManagementApplication", ya está listo para hacer las pruebas.

6. En la carpeta `task-management/collection-to-call-services`, se encuentra una colección de Postman para llamar al proyecto. Esta colección contiene ejemplos de respuestas para cada una de las peticiones.

## Notas Importantes

1. En este proyecto se está utilizando una base de datos en memoria llamada H2, ya que es fácil de probar porque no es necesario instalar ningún motor de base de datos por fuera. Como es en memoria, es manejada por dentro de Spring Boot mediante la dependencia H2.

2. Al ser una base de datos en memoria, los datos solo estarán vigentes mientras esté ejecutando el microservicio. Esta dependencia H2 es utilizada solo en desarrollo para pruebas.

3. Para que se pueda probar con facilidad, se dejó una clase que, al iniciar el microservicio, ingresa 10 tareas. Esto es para no tener que ingresar datos de manera manual para probar. Si no se quiere utilizar esta clase, se puede borrar `DataInitializer.java` en la carpeta `task-management/src/main/java/com/test/taskmanagement/dataInitializer`.
