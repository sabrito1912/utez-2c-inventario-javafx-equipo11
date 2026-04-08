# Tiendita :D - Sistema de Inventario

## Descripción del sistema
"Tiendita :D" es una aplicación de escritorio diseñada para gestionar el inventario de una pequeña tienda de forma sencilla. 
* Cuenta con una interfaz gráfica amigable, limpia y fácil de usar.

### Características principales:
* Visualización: Una tabla donde puedes ver todos tus productos registrados al instante.
* Búsqueda en tiempo real: Un buscador que filtra automáticamente los productos mientras escribes su nombre o código.
* Gestión de Inventario:
    * Agregar: Puedes registrar nuevos productos (el sistema te avisa si hay errores, como poner letras en el preciop números en el nombre).
    * Actualizar: Puedes buscar un producto por su código y modificar su información.
    * Eliminar: Puedes borrar rápidamente un producto ingresando su código.
* Guardado automático: Todo lo que hagas se guarda automáticamente en un archivo en tu computadora, sin necesidad de instalar programas adicionales o bases de datos.

---

## Cómo ejecutar

Para abrir y usar este proyecto en tu computadora, sigue estos pasos:

1. Descarga el proyecto en tu computadora o clónalo usando la terminal:
   `git clone https://github.com/sabrito1912/utez-2c-inventario-javafx-equipo11.git`
2. Abre la carpeta del proyecto en el programa que uses para programar en Java (como IntelliJ IDEA, Eclipse, etc.).
3. Espera un momento a que tu programa cargue todos los archivos correctamente.
4. Ejecuta la aplicación abriendo el archivo principal del proyecto (Hello Application) y dándole al botón de iniciar (Run).

---

## ¿Dónde se guardan los datos?

Para que no pierdas tu información al cerrar el programa, el sistema guarda todo en un archivo de texto simple.

* Formato: La información se guarda separada por comas, siguiendo siempre este orden:
  ID,Nombre,Precio,Stock,Categoría

* Ubicación: El archivo se llama inventario.csv y se crea automáticamente dentro de una carpeta llamada data (justo en la misma carpeta de tu proyecto). No te preocupes si no lo ves al principio, el programa lo creará solo la primera vez que lo uses.

* Datos de prueba para empezar: Si quieres ver cómo funciona la tabla rápidamente, puedes crear ese archivo inventario.csv dentro de la carpeta data y pegar esto adentro:


  001,Galletas de Chocolate,15.50,30,Abarrotes
  002,Refresco de Cola,20.0,50,Bebidas
  003,Jabón de Baño,12.0,15,Limpieza
  004,Papas Fritas,18.0,25,Botanas
  005,Leche Entera 1L,28.50,20,Lácteos