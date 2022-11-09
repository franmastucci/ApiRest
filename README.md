# UndefApiRestFinal

Trabajo Final Api Rest

### Para probar la API 👇🏻
* Descargar el proyecto o clonarlo.
* Ejecutar el proyecto.
* Importar las colecciones Postman que se encuentran en la carpeta Postman .
* Ejecutar la base de datos en memoria  para corroborar el correcto almacenamiento de datos. http://localhost:8080/h2-console
(El usuario y pass de la BBDD se encuentran declarados en el archivo application.yml)
* Ejecutar las peticiones en el siguiente orden sugerido para probar todas las funcionalidades .


## Orden lógico para ejecutar las peticiones

## 1. Crear una organizacion
**El request para crear una Organizacion se compone de los siguientes atributos:**
   
    - nombre 
    - pocision  
      -  latitud 
      -  longitud 
    
* Al crear una Organizacion se dan de alta automáticamente los cuatro estados de abastecimiento que a su vez están asociados con 4 efectos.

## 2. Crear una movmiento
**El request para crear un Movimiento se compone de los siguientes atributos:**

    - organizacionId
    - abastecimientoId
    - cantidad
    
* Al crear un Movimiento se indica en la ruta de la peticion el tipo de movimiento: /movimiento/1 si es ingreso , /movimiento/2 si es egreso

## 3. Obtener los Movimientos de una Organizacion
**El request para ver los Movimientos se realiza desde la siguiente ruta:**

    - /organizacion/{organizacion_id}/movimientos

## 4. Obtener los Efectos de una Organizacion
**El request para ver los Efectos se realiza desde la siguiente ruta:**

    - /organizacion/{organizacion_id}/efectos
    

## 5. Obtener los datos completos de una Organizacion
**El request para ver los datos de una Organizacion se realiza desde la siguiente ruta:**

    - /organizacion/{organizacion_id}
    
    
 ## 6. Adicionalmente se puede crear un Efecto de forma manual
**El request para crear un Efecto se compone de los siguientes atributos:**

    - tipo
    - descripcionTipo 
    - cantidad 
    - unidadMedida
    - claseId
    
    
 ## 7. Crear un Requerimiento
 **El request para crear un Requerimiento se compone de los siguiente satributos:**

    - organizacionId
    - cantidadDiasParaEntregar

 ## 8. Crear una Solicitud
 **El request para crear una Solicitud se compone de los siguientes atributos:**

    - requerimientoid
    - efecto
     
 ## 9. Obtener los datos completos de  un Requerimiento
**El request para ver los datos de un Requerimiento se realiza desde la siguiente ruta:**

    - /requerimiento/{requerimiento_id}
    
 ## 10. Obtener los datos completos de una Solicitud
**El request para ver los datos de un Solicitud se realiza desde la siguiente ruta:**

    - /solicitud/{solicitud_id}
    
 ## 11. Obtener todas las Solicitudes de un Requerimiento
**El request para ver las Solicitudes de un Eequerimiento se realiza desde la siguiente ruta:**

    - /requerimiento/solicitudes/{requerimeinto_id}
    
 ## 12. Confirmar un Requerimiento
**El request para confirmar un Requerimiento se realiza desde la siguiente ruta:**

    - /requerimiento/{requerimeinto_id}
    
* La peticion devolverá un boolean confirmando o no el requerimiento. El criterio para confirmar el requeirmiento se basa en que los efectos asociados a las solicitudes del requerimiento estén para la organizacion por debajo de la cantidad necesaria, es decir, en estado "con faltantes" Si esto no es así, el reuqerimiento no podrá ser confirmado

 ## 13. Agregar un Requerimiento
**El request para agregar un Requerimiento se realiza desde la siguiente ruta:**

    - /requerimiento/{requermiento_id}/{organizacion_id}   


