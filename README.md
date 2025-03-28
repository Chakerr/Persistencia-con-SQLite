# Persistencia de Datos con SQLite en Android - Transporte de Mascotas

## Objetivo
Persistir datos en una base de datos SQLite en una aplicación Android, permitiendo la gestión de solicitudes de transporte de mascotas de manera eficiente.

## Desarrollo

### Implementación de Entidades
Se ha creado una base de datos SQLite con una tabla llamada `solicitudes_transporte_mascotas`, la cual almacena la siguiente información:
- `id`
- `nombre_dueno`
- `nombre_mascota`
- `tipo_mascota`
- `destino`
- `hora_transporte`

La base de datos ha sido gestionada mediante una clase `DatabaseHelper`, que implementa las operaciones CRUD.

### Creación del Formulario
Se ha desarrollado un formulario con los siguientes componentes:
- Campos de entrada (`EditText`) para el nombre del dueño y la mascota.
- Un `Spinner` para la selección del tipo de mascota.
- Un `Spinner` para la selección del destino del transporte.
- Un `TimePickerDialog` para la selección de la hora del transporte.
- Un `Button` para agregar la solicitud de transporte a la base de datos.
- Un `Button` para consultar y visualizar las solicitudes almacenadas.

Se han aplicado validaciones en los campos para evitar datos vacíos y asegurar la integridad de la información.

### Funcionalidad de Adición de Datos
El botón de adición de datos captura la información ingresada y la almacena en SQLite mediante la siguiente lógica:
- Se obtienen los valores de los campos.
- Se valida que no haya datos vacíos.
- Se inserta la información en la tabla `solicitudes_transporte_mascotas` utilizando `SQLiteDatabase` y `ContentValues`.
- Se muestra un mensaje de éxito o error según la operación.

### Funcionalidad de Consulta de Datos
El botón de consulta permite visualizar los datos almacenados en SQLite. La implementación sigue estos pasos:
- Se consulta la tabla `solicitudes_transporte_mascotas`.
- Los datos recuperados se almacenan en una lista.
- Se muestra la información en un `TextView`.
- Se actualiza la vista dinámicamente para reflejar cambios en la base de datos.

## Tecnologías Utilizadas
- Android Studio
- Java
- SQLite
- Spinner para selección de opciones
- TimePickerDialog para la selección de hora
