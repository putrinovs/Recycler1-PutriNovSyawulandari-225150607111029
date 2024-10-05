package id.ac.ub.papb.recycler1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mahasiswa> mahasiswaList;
    private EditText etNim, etNama, etTanggalLahir, etAsal;
    private Button btSimpan;
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etTanggalLahir = findViewById(R.id.etTanggalLahir);
        etAsal = findViewById(R.id.etAsal);
        btSimpan = findViewById(R.id.btSimpan);
        recyclerView = findViewById(R.id.rvMahasiswa);

        // Initialize Mahasiswa list
        mahasiswaList = new ArrayList<>();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Perbaikan: Tambahkan Context sebagai parameter kedua
        mahasiswaAdapter = new MahasiswaAdapter(mahasiswaList, this);
        recyclerView.setAdapter(mahasiswaAdapter);

        // Set DatePickerDialog for etTanggalLahir
        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from EditText fields
                String nim = etNim.getText().toString().trim();
                String nama = etNama.getText().toString().trim();
                String tanggalLahir = etTanggalLahir.getText().toString().trim();
                String asal = etAsal.getText().toString().trim();

                // Check if all fields are filled
                if (nim.isEmpty()) {
                    etNim.setError("NIM tidak boleh kosong");
                    return;
                }
                if (nama.isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                    return;
                }
                if (tanggalLahir.isEmpty()) {
                    etTanggalLahir.setError("Tanggal Lahir tidak boleh kosong");
                    return;
                }
                if (asal.isEmpty()) {
                    etAsal.setError("Asal tidak boleh kosong");
                    return;
                }

                // Add new Mahasiswa to the list
                mahasiswaList.add(new Mahasiswa(nim, nama, tanggalLahir, asal));

                // Notify adapter about the data change
                mahasiswaAdapter.notifyDataSetChanged();

                // Clear the input fields
                etNim.setText("");
                etNama.setText("");
                etTanggalLahir.setText("");
                etAsal.setText("");

                // Optionally show a success message
                Toast.makeText(MainActivity.this, "Mahasiswa berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            etTanggalLahir.setText(date);
        }, year, month, day);

        datePickerDialog.show();
    }
}
