package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout table = findViewById(R.id.tabla);

        CharSequence[] numeros = {"-","1","2","3","4","5","6","7","8","9"};
        for(int i = 0; i < 9; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                Spinner spinner = new Spinner(this);
                spinner.setTag(R.id.fila, i);
                spinner.setTag(R.id.columna, j);
                spinner.setTag("init bug");
                spinner.setPadding(0, 15, 0, 15);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {
                        int fila = (int) adapterView.getTag(R.id.fila);
                        int columna = (int) adapterView.getTag(R.id.columna);
                        if ( spinner.getTag().equals("init bug")){
                            spinner.setTag("vale, no mas bugs");
                            return;
                        }
                        String texto = spinner.getSelectedItem().toString();
                        Toast.makeText(MainActivity.this, "Fila: "+ fila +" Columna: " + columna + " Nuevo valor: "+texto, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, numeros);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                row.addView(spinner);
            }
            table.addView(row);
        }

    }
}