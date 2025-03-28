package unipiloto.edu.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MascotasDB"; // Nombre de la base de datos
    private static final int DATABASE_VERSION = 1; // Versión de la base de datos

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla para almacenar las solicitudes de transporte de mascotas
        String CREATE_TABLE = "CREATE TABLE SolicitudesTransporteMascotas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dueno TEXT, " +
                "mascota TEXT, " +
                "tipoMascota TEXT, " +
                "destino TEXT, " +
                "fechaHora TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la base de datos cambia de versión, eliminamos la tabla y la volvemos a crear
        db.execSQL("DROP TABLE IF EXISTS SolicitudesTransporteMascotas");
        onCreate(db);
    }
    public class DatabaseManager {
        private DatabaseHelper dbHelper;
        private SQLiteDatabase database;

        // Constructor
        public DatabaseManager(Context context) {
            dbHelper = new DatabaseHelper(context);
        }

        public void open() {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public void insertarSolicitud(String dueno, String mascota, String tipoMascota, String destino, String fechaHora) {
            ContentValues values = new ContentValues();
            values.put("dueno", dueno);
            values.put("mascota", mascota);
            values.put("tipoMascota", tipoMascota);
            values.put("destino", destino);
            values.put("fechaHora", fechaHora);

            database.insert("SolicitudesTransporteMascotas", null, values);
        }
    }

}