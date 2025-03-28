package unipiloto.edu.sqlitedemo;

import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etDueno, etMascota;
    private Spinner spinnerTipoMascota, spinnerDestino;
    private Button btnSeleccionarHora, btnGuardar, btnConsultar;
    private TextView tvResultados;
    private DatabaseManager databaseManager;

    private String horaSeleccionada = "";
    private String destinoSeleccionado = "";
    private String tipoMascotaSeleccionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los elementos del layout
        etDueno = findViewById(R.id.etDueno);
        etMascota = findViewById(R.id.etMascota);
        spinnerTipoMascota = findViewById(R.id.spinnerTipoMascota);
        spinnerDestino = findViewById(R.id.spinnerDestino);
        btnSeleccionarHora = findViewById(R.id.btnSeleccionarHora);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnConsultar = findViewById(R.id.btnConsultar);
        tvResultados = findViewById(R.id.tvResultados);

        // Inicializar la base de datos
        databaseManager = new DatabaseManager(this);

        // Configurar Spinner de tipo de mascota
        String[] tiposMascota = {"Perro", "Gato", "Ave", "Conejo", "Reptil", "Otro"};
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tiposMascota);
        spinnerTipoMascota.setAdapter(adapterTipo);

        spinnerTipoMascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoMascotaSeleccionado = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tipoMascotaSeleccionado = "";
            }
        });

        // Configurar Spinner de destino
        String[] destinos = {"Veterinaria", "Parque", "Guardería", "Hogar", "Otro"};
        ArrayAdapter<String> adapterDestino = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, destinos);
        spinnerDestino.setAdapter(adapterDestino);

        spinnerDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destinoSeleccionado = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                destinoSeleccionado = "";
            }
        });

        // Evento para seleccionar la hora
        btnSeleccionarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSelectorHora();
            }
        });

        // Evento click del botón "Guardar"
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarSolicitud();
            }
        });

        // Evento click del botón "Consultar"
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarSolicitudes();
            }
        });
    }

    private void mostrarSelectorHora() {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaSeleccionada = hourOfDay + ":" + (minute < 10 ? "0" + minute : minute);
                        btnSeleccionarHora.setText("Hora: " + horaSeleccionada);
                    }
                }, hora, minuto, true);

        timePickerDialog.show();
    }

    private void guardarSolicitud() {
        String dueno = etDueno.getText().toString().trim();
        String mascota = etMascota.getText().toString().trim();

        if (dueno.isEmpty() || mascota.isEmpty() || tipoMascotaSeleccionado.isEmpty() || destinoSeleccionado.isEmpty() || horaSeleccionada.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseManager.open();
        databaseManager.insertarSolicitud(dueno, mascota, tipoMascotaSeleccionado, destinoSeleccionado, horaSeleccionada);
        databaseManager.close();

        Toast.makeText(this, "Solicitud guardada exitosamente", Toast.LENGTH_SHORT).show();

        etDueno.setText("");
        etMascota.setText("");
        horaSeleccionada = "";
        btnSeleccionarHora.setText("Seleccionar Hora");
    }

    private void consultarSolicitudes() {
        databaseManager.open();
        Cursor cursor = databaseManager.obtenerSolicitudes();

        if (cursor.getCount() == 0) {
            tvResultados.setText("No hay solicitudes registradas.");
        } else {
            StringBuilder builder = new StringBuilder();
            while (cursor.moveToNext()) {
                builder.append("Dueño: ").append(cursor.getString(1)).append("\n")
                        .append("Mascota: ").append(cursor.getString(2)).append("\n")
                        .append("Tipo: ").append(cursor.getString(3)).append("\n")
                        .append("Destino: ").append(cursor.getString(4)).append("\n")
                        .append("Hora: ").append(cursor.getString(5)).append("\n")
                        .append("----------------------\n");
            }
            tvResultados.setText(builder.toString());
        }

        cursor.close();
        databaseManager.close();
    }
}
