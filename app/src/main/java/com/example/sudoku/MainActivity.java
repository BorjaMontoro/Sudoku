package com.example.sudoku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

        CharSequence[] numeros = {" ","1","2","3","4","5","6","7","8","9"};
        modelo.creaPartida();
        for(int i = 0; i < 9; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                Spinner spinner = new Spinner(this);
                spinner.setBackgroundResource(R.drawable.borde_personalizado);
                spinner.setTag(R.id.fila, i);
                spinner.setTag(R.id.columna, j);
                spinner.setPadding(15, 15, 15, 15);
                spinner.setTag("init bug");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int b, long l) {
                        if (adapterView.getTag().equals("init bug")) {
                            adapterView.setTag("vale, no mas bugs");
                        } else {
                            if (!(adapterView.getSelectedItem().toString().equals(" "))) {
                                int fila = (int) adapterView.getTag(R.id.fila);
                                int columna = (int) adapterView.getTag(R.id.columna);
                                int valor = Integer.parseInt(adapterView.getSelectedItem().toString());

                                if (modelo.setVal(fila, columna, valor)) {
                                    Toast.makeText(getApplicationContext(), "Numero correcto!!!", Toast.LENGTH_LONG).show();
                                    if (modelo.compruebaSudoku()) {
                                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                        alertDialog.setTitle("Sudoku");
                                        alertDialog.setMessage("Enhorabuena has acabado el sudoku");

                                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Salir", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int l) {
                                                System.exit(0);
                                            }
                                        });
                                        alertDialog.show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Numero incorrecto!!!", Toast.LENGTH_LONG).show();
                                }
                                refrescaGUI();
                            } else {
                                adapterView.setSelection(0);
                            }
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
        refrescaGUI();
        deshabilitarNumRandoms();
    }
    private void refrescaGUI(){
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                if (matriz[i][j].getSelectedItem() != " "){
                    matriz[i][j].setSelection(modelo.getVal(i,j));
                }else {
                    matriz[i][j].setSelection(modelo.getVal(i,j));
                }
            }
        }
    }
    private void deshabilitarNumRandoms(){
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                if (matriz[i][j].getSelectedItem() != " "){
                    matriz[i][j].setEnabled(false);
                }else {
                    matriz[i][j].setEnabled(true);
                }
            }
        }
    }
}