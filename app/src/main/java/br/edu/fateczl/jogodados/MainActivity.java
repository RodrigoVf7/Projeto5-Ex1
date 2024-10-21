package br.edu.fateczl.jogodados;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    /*
     *@author: RODRIGO VINICIUS FERRAZ DA SILVA
     *@RA: 1110482313043
     */

    private Spinner spinnerFaces;
    private RadioGroup radioGroupDados;
    private Button btnJogar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinnerFaces = findViewById(R.id.spinner_faces);
        radioGroupDados = findViewById(R.id.radio_group_dados);
        btnJogar = findViewById(R.id.btn_jogar);
        txtResultado = findViewById(R.id.txt_resultado);

        String[] tiposDeDados = {"D4", "D6", "D8", "D10", "D12", "D20", "D100"};

        // Adapter para o spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDeDados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaces.setAdapter(adapter);


        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogarDados();
            }

        });
    }
    @SuppressLint("NonConstantResourceId")
    private void jogarDados() {
        String tipoDado = spinnerFaces.getSelectedItem().toString();
        int faces = Integer.parseInt(tipoDado.substring(1)); // Extrai o número de faces do nome, ex: "D6" -> 6
        int numeroDeDados = 1;


        if (radioGroupDados.getCheckedRadioButtonId() == R.id.radio_dois_dados) {
            numeroDeDados = 2;
        } else if (radioGroupDados.getCheckedRadioButtonId() == R.id.radio_tres_dados) {
            numeroDeDados = 3;
        }


        StringBuilder resultado = new StringBuilder();
        Random random = new Random();

        for (int i = 1; i <= numeroDeDados; i++) {
            int valor = random.nextInt(faces) + 1;
            resultado.append("Dado ").append(i).append(": ").append(valor).append("\n");
        }

        txtResultado.setText(resultado.toString());
    }

}