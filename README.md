# README - Persistencia de Datos con SQLite en Android

## Objetivo
Persistir datos en una base de datos SQLite en una aplicación Android, permitiendo la gestión de solicitudes de transporte de carga de manera eficiente.

## Desarrollo

### 1. Implementación de Entidades
Se ha creado una base de datos SQLite con una tabla llamada `solicitudes_transporte`, la cual almacena la siguiente información:
- `id` (INTEGER, clave primaria, autoincremental)
- `nombre_solicitante` (TEXT, no nulo)
- `direccion_origen` (TEXT, no nulo)
- `direccion_destino` (TEXT, no nulo)
- `tipo_carga` (TEXT, no nulo)
- `peso_aproximado` (REAL, no nulo)
- `fecha_hora_transporte` (TEXT, no nulo, formato ISO 8601)

La base de datos ha sido gestionada mediante una clase `DatabaseHelper`, que implementa las operaciones CRUD.

### 2. Creación del Formulario
Se ha desarrollado un formulario con los siguientes componentes:
- Campos de entrada (`EditText`) para cada uno de los datos requeridos.
- Un `Button` para agregar la solicitud de transporte a la base de datos.
- Un `Button` para consultar y visualizar las solicitudes almacenadas.

Se han aplicado validaciones en los campos para evitar datos vacíos y asegurar la integridad de la información.

### 3. Funcionalidad de Adición de Datos
El botón de adición de datos captura la información ingresada y la almacena en SQLite mediante la siguiente lógica:
- Se obtienen los valores de los campos.
- Se valida que no haya datos vacíos.
- Se inserta la información en la tabla `solicitudes_transporte` utilizando `SQLiteDatabase` y `ContentValues`.
- Se muestra un mensaje de éxito o error según la operación.

### 4. Funcionalidad de Consulta de Datos
El botón de consulta permite visualizar los datos almacenados en SQLite. La implementación sigue estos pasos:
- Se consulta la tabla `solicitudes_transporte`.
- Los datos recuperados se almacenan en una lista.
- Se utiliza un `RecyclerView` para mostrar la información de manera estructurada.
- Se actualiza la vista dinámicamente para reflejar cambios en la base de datos.

### 5. Pruebas y Validaciones
- Se verificó la persistencia de datos en SQLite tras el cierre y reapertura de la aplicación.
- Se probaron inserciones, consultas y recuperación de datos en distintos dispositivos y emuladores.
- Se optimizó el rendimiento en consultas para evitar bloqueos en la UI.
- Se realizaron pruebas con datos erróneos para asegurar la robustez de la validación.

## Tecnologías Utilizadas
- Android Studio
- Java
- SQLite
- RecyclerView para la visualización de datos

Este sistema garantiza una persistencia confiable y una experiencia de usuario fluida para la gestión de solicitudes de transporte de carga.

