package com.example.sudoku;


import java.util.Arrays;
import java.util.Random;

public class SudokuModel {

    private static int [][] matriz= new int[9][9];

    public int getVal(int fila, int columna) {
        return matriz[fila][columna];
    }
    public int setVal(int fila, int columna, int valor) {
        int valorPrevio = matriz[fila][columna];
        matriz[fila][columna] = valor;
        if (!comprovaFila(fila)){
            matriz[fila][columna] = valorPrevio;
            return -1;
        }
        if (!comprovaCol(columna)){
            matriz[fila][columna] = valorPrevio;
            return -1;
        }
        if (!comprovaQuad(fila,columna)){
            matriz[fila][columna] = valorPrevio;
            return -1;}
        return 0;
    }

    public boolean comprovaFila(int fila) {
        for (int k = 0; k<9; k++){
            for (int l = 0; l<9; l++){
                if (k != l && matriz[fila][k] != 0){
                    if (matriz[fila][k] == matriz[fila][l]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean comprovaCol(int columna) {
        for (int k = 0; k < 9; k++){
            for (int l = 0; l < 9; l++){
                if (k != l && matriz[l][columna] != 0){
                    if (matriz[k][columna] == matriz[l][columna]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean comprovaQuad (int fila, int columna) {
        boolean valido = true;
        int inicioFila = 0;
        int inicioColumna = 0;
        if (fila < 3){
            inicioFila = 0;
            if (columna<3) {
                inicioColumna = 0;
            }
            else if (columna < 6){
                inicioColumna = 3;
            }
            else {
                inicioColumna = 6;
            }
        }else if (fila < 6) {
            inicioFila = 3;
            if (columna<3) {
                inicioColumna = 0;
            }
            else if (columna < 6){
                inicioColumna = 3;
            }
            else {
                inicioColumna = 6;
            }
        } else {
            inicioFila = 6;
            if (columna<3) {
                inicioColumna = 0;
            }
            else if (columna < 6){
                inicioColumna = 3;
            }
            else {
                inicioColumna = 6;
            }
        }

        for (int a = inicioFila; a < inicioFila+3 ; a++){
            for (int b = inicioColumna; b < inicioColumna + 3; b++) {
                for (int c = inicioFila; c < inicioFila + 3; c++) {
                    for (int d = inicioColumna; d < inicioColumna + 3; d++) {
                        if (a != c && b != d && matriz[a][b] != 0) {
                            if (matriz[a][b] == matriz[c][d]) {
                                valido = false;
                            }
                        }
                    }
                }
            }
        }
        return valido;
    }

    public void creaPartida () {
        Random random = new Random();
        int numsIntroduits = 20;
        int fila = 0;
        int columna = 0;
        int valor = 0;
        for (int a = 0; a < numsIntroduits; a++){
            fila = random.nextInt(9-1);
            columna = random.nextInt(9-1);
            valor = random.nextInt(9-1)+1;
            setVal(fila,columna,valor);
            while (setVal(fila,columna,valor) == -1){
                fila = random.nextInt(9-1);
                columna = random.nextInt(9-1);
            }
        }
    }
    SudokuModel() {
        creaPartida();
    }

}
