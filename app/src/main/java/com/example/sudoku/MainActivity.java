package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private SudokuModel modelo=new SudokuModel();
    private Spinner[][] matriz=new Spinner[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tabla = findViewById(R.id.tabla);

        CharSequence[] numeros = {"·","1","2","3","4","5","6","7","8","9"};

        for(int i = 0; i < 9; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                Spinner spinner = new Spinner(this);
                spinner.setTag(R.id.fila, i);
                spinner.setTag(R.id.columna, j);
                spinner.setPadding(0, 15, 0, 15);
                spinner.setTag("init bug");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {
                        int fila = (int) adapterView.getTag(R.id.fila);
                        int columna = (int) adapterView.getTag(R.id.columna);
                        if ( spinner.getTag().equals("init bug")) {
                            spinner.setTag("vale, no mas bugs");
                            return;
                        }
                        Toast.makeText(MainActivity.this, "Fila: "+ fila +" Columna: " + columna + " Nuevo valor: "+b, Toast.LENGTH_SHORT).show();
                        if(modelo.setVal(fila,columna,b)>-1){
                            refrescaGUI();
                        }
                        else{
                            matriz[fila][columna].setSelection(0);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, numeros);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                this.matriz[i][j]=spinner;
                row.addView(spinner);
            }
            tabla.addView(row);
        }
        refrescaGUI();
    }
    private void refrescaGUI(){
        for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                matriz[i][j].setSelection(modelo.getVal(i,j));
            }
        }
    }
}