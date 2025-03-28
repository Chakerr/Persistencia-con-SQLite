package unipiloto.edu.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {

    private static final String DATABASE_NAME = "MascotasDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SOLICITUDES = "solicitudes";

    // Columnas de la tabla
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DUENO = "dueno";
    private static final String COLUMN_MASCOTA = "mascota";
    private static final String COLUMN_TIPO_MASCOTA = "tipo_mascota";
    private static final String COLUMN_DESTINO = "destino";
    private static final String COLUMN_FECHA_HORA = "fecha_hora";

    private static final String CREATE_TABLE_SOLICITUDES =
            "CREATE TABLE " + TABLE_SOLICITUDES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DUENO + " TEXT NOT NULL, " +
                    COLUMN_MASCOTA + " TEXT NOT NULL, " +
                    COLUMN_TIPO_MASCOTA + " TEXT NOT NULL, " +
                    COLUMN_DESTINO + " TEXT NOT NULL, " +
                    COLUMN_FECHA_HORA + " TEXT NOT NULL);";

    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertarSolicitud(String dueno, String mascota, String tipoMascota, String destino, String fechaHora) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DUENO, dueno);
        values.put(COLUMN_MASCOTA, mascota);
        values.put(COLUMN_TIPO_MASCOTA, tipoMascota);
        values.put(COLUMN_DESTINO, destino);
        values.put(COLUMN_FECHA_HORA, fechaHora);
        database.insert(TABLE_SOLICITUDES, null, values);
    }

    public Cursor obtenerSolicitudes() {
        return database.query(TABLE_SOLICITUDES, null, null, null, null, null, COLUMN_ID + " DESC");
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_SOLICITUDES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLICITUDES);
            onCreate(db);
        }
    }
}