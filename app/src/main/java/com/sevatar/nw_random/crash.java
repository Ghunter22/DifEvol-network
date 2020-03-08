package com.sevatar.nw_random;

import java.util.Random;

public class crash {

    crash ( mutant m, vectr v, double alpha1, Integer p1){

       // Random rnd = new Random(System.currentTimeMillis());        // ПЕРЕДАТЬ АЛЬФУ
        p = p1;
        alpha = alpha1;

        for ( int i = 0; i < 3; i++) {
            for(int j = 0; j <3; j++) {
                Random rnd = new Random();
                rand = rnd.nextInt(max);
           //    rand = (Math.random()*(max));
                if(rand > p) {
                   w[i][j] =  m.w[i][j];
                } else {
                    w[i][j] =  v.w[i][j];
                }

            }
        }


        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <3; j++) {
                Random rnd = new Random();
                rand = rnd.nextInt(max);

              //  rand = (Math.random()*(max)) ;
                if(rand > p) {
                    e[i][j] =  m.e[i][j];
                } else {
                    e[i][j] =  v.e[i][j];
                }


            }
        }

        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <3; j++) {
                Random rnd = new Random();
                rand = rnd.nextInt(max);

            //    rand = (Math.random()*(max));
                if(rand > p) {
                    r[i][j] =  m.r[i][j];
                } else {
                    r[i][j] =  v.r[i][j];
                }


            }
        }


        for ( int i = 0; i < 4; i++) {
            for(int j = 0; j <2; j++) {
                Random rnd = new Random();
                rand = rnd.nextInt(max);

            //    rand = (Math.random()*(max));
                if(rand > p) {
                    t[i][j] =  m.t[i][j];
                } else {
                    t[i][j] =  v.t[i][j];
                }


            }
        }


    //  Go();



    }
    Integer p;

    Double w [][] =  new Double[3][3];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ // новая схема
    Double e [][]  = new Double[4][3];
    Double r [][]  = new Double[4][3];
    Double t [][]  = new Double[4][2];
    Integer rand;
    Integer min = 0;
    Integer max = 10;
    Double n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,x1,x2,y1,y2;
    Integer exm = 0;
    Double alpha = 1.0;



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
