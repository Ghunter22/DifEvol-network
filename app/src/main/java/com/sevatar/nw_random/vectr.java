package com.sevatar.nw_random;

import java.util.Random;

public class vectr {
    public vectr( Double[][] W, Double[][] E, Double[][] R, Double[][] T, double alpha1) { // ПЕРЕДАТЬ АЛЬФУ

      alpha = alpha1;
        for (int a =0; a < 3; a ++) {
            for (int b =0; b < 3; b ++) {

                w[a][b] = W[a][b];
            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 3; b ++) {

                e[a][b] = E[a][b];
            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 3; b ++) {

                r[a][b] = R[a][b];
            }

        }

        for (int a =0; a < 4; a ++) {
            for (int b =0; b < 2; b ++) {

                t[a][b] = T[a][b];
            }

        }



    // Go();






    }
    Integer exm = 0;
    Double alpha;
    Double min =0.001;
    Double  max = 1.0;
//    Double w [][] =  new Double[3][2];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ
//    Double e [][]  = new Double[3][3];
//    Double r [][]  = new Double[4][2];

    Double w [][] =  new Double[3][3];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ // новая схема
    Double e [][]  = new Double[4][3];
    Double r [][]  = new Double[4][3];
    Double t [][]  = new Double[4][2];
    Double rand;
    Double n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,x1,x2,y1,y2;       //каждый нейрон считается


    public  double  s1(double w01, double w11, double w21, Integer i, double alpha){ // с использованием радиально-базисной функции
        double s = w01 + (w11 * x1 +w21 * x2);
        double fs = Math.pow(Math.E,-(alpha*s*alpha*s));

        return fs;
    }
    public  double  s2 (double e01, double e11, double e21, double e31, Integer i, double alpha){
        double s = e01 + (e11 * n1 +(e21 * n2) + e31*n3);
        double fs = Math.pow(Math.E,-(alpha*s*alpha*s));

        return fs;
    }

    public  double  s3 (double e01, double e11, double e21, double e31, Integer i, double alpha){
        double s = e01 + (e11 * n4 +(e21 * n5) + e31*n6);
        double fs = Math.pow(Math.E,-(alpha*s*alpha*s));

        return fs;
    }

    public  double  s4 (double e01, double e11, double e21, double e31, Integer i, double alpha){
        double s = e01 + (e11 * n7 +(e21 * n8) + e31*n9);
        double fs = Math.pow(Math.E,-(alpha*s*alpha*s));

        return fs;
    }


//    public  double  s1(double w01, double w11, double w21, Integer i, double alpha){ // для каждого слоя своя формула
//        double s = w01 + (w11 * x1 +w21 * x2);
//        double fs = 1/(1+(Math.pow(Math.E,-(alpha*s))));
//
//        return fs;
//    }
//    public  double  s2 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n1 +(e21 * n2) + e31*n3);
//        double fs = 1/(1+(Math.pow(Math.E,-(alpha*s))));
//
//        return fs;
//    }
//
//    public  double  s3 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n4 +(e21 * n5) + e31*n6);
//        double fs = 1/(1+(Math.pow(Math.E,-(alpha*s))));
//
//        return fs;
//    }
//
//    public  double  s4 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n7 +(e21 * n8) + e31*n9);
//        double fs = 1/(1+(Math.pow(Math.E,-(alpha*s))));
//
//        return fs;
//    }


//    public  double  s1(double w01, double w11, double w21, Integer i, double alpha){ // для каждого слоя своя формула
//        double s = w01 + (w11 * x1 +w21 * x2);
//        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));
//
//        return fs;
//    }
//    public  double  s2 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n1 +(e21 * n2) + e31*n3);
//        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));
//
//        return fs;
//    }
//
//    public  double  s3 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n4 +(e21 * n5) + e31*n6);
//        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));
//
//        return fs;
//    }
//
//    public  double  s4 (double e01, double e11, double e21, double e31, Integer i, double alpha){
//        double s = e01 + (e11 * n7 +(e21 * n8) + e31*n9);
//        double fs = 1/(1+(Math.pow(Math.E,-alpha*s)));
//
//        return fs;
//    }




    public  void Go() { // функция рассчета новых Y


        n1 = s1(w[0][0],w[1][0],w[2][0],exm, alpha);
        n2 = s1(w[0][1],w[1][1],w[2][1],exm, alpha);
        n3 = s1(w[0][2],w[1][2],w[2][2],exm, alpha);

        ////////////////////первый слой /////////////////////////////
        n4 = s2(e[0][0],e[1][0],e[2][0],e[3][0],exm, alpha);
        n5 = s2(e[0][1],e[1][1],e[2][1],e[3][1],exm, alpha);
        n6 = s2(e[0][2],e[1][2],e[2][2],e[3][2],exm, alpha);
        ////////////////второй слой////////////////////y1
        n7 = s3(r[0][0],r[1][0],r[2][0],r[3][0],exm, alpha);
        n8 = s3(r[0][1],r[1][1],r[2][1],r[3][1],exm, alpha);
        n9 = s3(r[0][2],r[1][2],r[2][2],r[3][2],exm, alpha);
        ////////////////третий слой//////////////////
        n10 = s4(t[0][0],t[1][0],t[2][0],t[3][0],exm, alpha);
        n11 = s4(t[0][1],t[1][1],t[2][1],t[3][1],exm, alpha);


        y1 = n10;
        y2 = n11;
    }
}
