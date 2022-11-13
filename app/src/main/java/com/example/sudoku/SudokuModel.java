package com.example.sudoku;


import androidx.appcompat.app.AppCompatActivity;


public class SudokuModel extends AppCompatActivity {

    private static int[] valores = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static int [][] sudoku= new int[9][9];
    private static int[][] sudokuResuelto = new int[9][9];

    public int getVal(int fila, int columna) {
        return sudoku[fila][columna];
    }
    public boolean setVal(int fila, int columna, int valor) {
        int valorPrevio = sudoku[fila][columna];
        sudoku[fila][columna] = valor;
        if (sudoku[fila][columna] != sudokuResuelto[fila][columna]){
            sudoku[fila][columna] = valorPrevio;
            return false;
        } else {
            sudoku[fila][columna] = valor;
            return true;
        }
    }

    public boolean compruebaSudoku(){
        boolean estaCorrecto = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(!correctoSudoku(i,j)){
                    estaCorrecto = false;
                    break;
                }
            }
        }
        return estaCorrecto;
    }

    public static int[][] generarSudoku(){
        sudoku = new int[9][9];
        boolean flag;
        for (int i = 0; i < 9; i++) {
            int posicion = 0;
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = generarValor(posicion);
                flag=true;
                if (sudoku[i][j] == 0) {
                    if (j > 0) {
                        j = j - 2;
                        flag=false;
                    } else {
                        i--;
                        j = 8;
                        flag=false;
                    }
                }
                if (flag) {
                    if (esCorrecto(i, j)) {
                        posicion = 0;
                    } else {
                        posicion++;
                        j--;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuResuelto[i][j] = sudoku[i][j];
            }
        }
        return sudoku;
    }

    private static boolean comprovaFila(int fila) {
        for (int k = 0; k<9; k++){
            for (int l = 0; l<9; l++){
                if (k != l && sudoku[fila][k] != 0){
                    if (sudoku[fila][k] == sudoku[fila][l]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean comprovaCol(int columna) {
        for (int k = 0; k < 9; k++){
            for (int l = 0; l < 9; l++){
                if (k != l && sudoku[l][columna] != 0){
                    if (sudoku[k][columna] == sudoku[l][columna]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean comprovaQuad (int fila, int columna) {
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
                        if (a != c && b != d && sudoku[a][b] != 0) {
                            if (sudoku[a][b] == sudoku[c][d]) {
                                valido = false;
                            }
                        }
                    }
                }
            }
        }
        return valido;
    }
    public void creaPartida(){
        generarSudoku();
        int fila=0;
        int columna=0;
        for (int j = 0; j < 70; j++) {
            fila = (int) (Math.random() * 9);
            columna = (int) (Math.random() * 9);
            sudoku[fila][columna] = 0;
        }
    }

    private static boolean esCorrecto(int fila, int columna) {
        return (comprovaFila(fila) & comprovaCol(columna) & comprovaQuad(fila, columna));
    }
    private static boolean correctoSudoku(int fila, int columna){
        if (sudoku[fila][columna] != sudokuResuelto[fila][columna]){
            return false;
        } else {
            return true;
        }
    }

    private static int generarValor(int posicion) {
        if (posicion == 0) {
            for (int i = 0; i < 9; i++) {
                valores[i] = i + 1;
            }
        }
        if (posicion == 9) {
            return 0;
        }
        int numeroRandom=(int) (Math.random() * (9-posicion));
        int tmp = valores[8-posicion];
        valores[8-posicion] = valores[numeroRandom];
        valores[numeroRandom] = tmp;
        return valores[8-posicion];
    }


}
