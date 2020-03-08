package com.sevatar.nw_random;

import java.io.Serializable;

public class end_vectr implements Serializable {
    public  end_vectr(vectr v, crash c, boolean isBetter, double alpha1){        //этим классом определяются вектора, созданные при сравнении старых и пробных
          //public  end_vectr(vectr v, crash c, Double y_1, Double y_2 , Double vy1, Double vy2m, Double cy1, Double cy2){
//        Verr_y1 = (v.y1 - y_1)*(v.y1 - y_1); // ошибка изначального вектора по y1
//        Cerr_y1 = (c.y1 - y_1)*(c.y1 - y_1);// ошибка пробного вектора по y1
//        Verr_y2 = (v.y2 - y_2)*(v.y2 - y_2); // ошибка изначального вектора по y2
//        Cerr_y2 = (c.y2 - y_2)*(c.y2 - y_2);// ошибка пробного вектора по y2
//        v1 = v;
//        c1 = c;
            alpha = alpha1;
        if(isBetter) { // если лучше скрещенный вектор  // ПЕРЕДАТЬ АЛЬФУ


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    w[i][j] = c.w[i][j];

                }
            }


            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {


                    e[i][j] = c.e[i][j];

                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {

                    r[i][j] = c.r[i][j];


                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {

                    t[i][j] = c.t[i][j];


                }
            }
        } else { // если лучше базовый вектор



            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    w[i][j] = v.w[i][j];

                }
            }


            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {


                    e[i][j] = v.e[i][j];

                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {

                    r[i][j] = v.r[i][j];


                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {

                    t[i][j] = v.t[i][j];


                }
            }






        }



    }

    static Double w [][] =  new Double[3][3];            //МАССИВЫ ВЕСОВЫХ КОЭФФИЦИЕНТОВ // новая схема
    static Double e [][]  = new Double[4][3];
    static Double r [][]  = new Double[4][3];
    static Double t [][]  = new Double[4][2];


    Double n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,x1,x2,y1,y2;
    Integer exm = 0;
    Double alpha;



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
