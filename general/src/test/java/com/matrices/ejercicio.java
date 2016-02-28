package com.matrices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ejercicio {
    public static void main(String... args)throws IOException  {

        BufferedReader tcl= new BufferedReader(new InputStreamReader(System.in));
        int M[][];
        int n;


        System.out.println("Ingrese la dimensión de la Matriz: ");
        n = Integer.parseInt(tcl.readLine());
        M = new int[n][n];  // matriz de orden cuadrado nxn
        llenarMatriz(M, n);
        imprimir(M,n);

        System.out.print("La diagonal principal es:    [");
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++){
                if (i == j)
                    System.out.print(M[i][j] +" ");

            }
        System.out.println("]");

        System.out.print("Elementos encima de la diagonal principal:    [");
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++){
                if (i == j)
                    System.out.print("");
                else if(i<j)
                    System.out.print(M[i][j]+" ");
            }

        System.out.println("]");

        System.out.print("Elementos debajo de la diagonal principal:    [");
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++){
                if (i == j)
                    System.out.print("");
                else if(i<j)
                    System.out.print("");
                else
                    System.out.print(M[i][j]+" ");
            }

        System.out.println("]");

        int x = ((n * n) - n) / 2;

        int d[] = new int[n];

        int[] up = new int[ x ];
        int[] bottom = new int[ x ];
        int upCount = 0;
        int bottomCount = 0;

        for (int i = 0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int k = n - j - 1;
                int value = M [ i ] [ j ];

                if (i == k) {
                    d[ j ] = value;
                } else if (i < k) {
                    up[ upCount++ ] = value;
                } else {
                    bottom[ bottomCount++ ] = value;
                }
            }
        }

        imprimir(M, n);
        System.out.print("La diagonal secundaria es:    [");
        for (int i=0; i<n; i++) {
            System.out.print(d[i] + " ");
        }
        System.out.println("]");

        System.out.print("Elementos encima de la diagonal secundaria:    [");
        for (int i=0; i<x; i++) {
            System.out.print(up[i] + " ");
        }
        System.out.println("]");

        System.out.print("Elementos debajo de la diagonal secundaria:    [");
        for (int i=0; i<x; i++) {
            System.out.print(bottom[i] + " ");
        }
        System.out.println("]");
    }




    public static void llenarMatriz(int M[][], int n){

        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                M[i][j] = (int) (Math.random()*10);

    }

    public static void imprimir(int M[][], int n){
        System.out.println();
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++)
                System.out.print(M[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }
}
