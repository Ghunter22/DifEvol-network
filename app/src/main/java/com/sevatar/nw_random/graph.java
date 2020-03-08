package com.sevatar.nw_random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class graph extends AppCompatActivity {

    public double x1,x2, MinX1,MinX2,MaxX1,MaxX2, y1, y2, err1,err2,MinY1,MinY2,MaxY1,MaxY2,y11,y22;
    EditText x_1, x_2;
    ImageView img4;
    end_vectr end;
    TextView y_11,y_22,y_1,y_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        x_1 = (EditText)findViewById(R.id.x1);
        x_2 = (EditText)findViewById(R.id.x2);

        y_1 = (TextView) findViewById(R.id.y1); //расчитаные сетью
        y_2 = (TextView) findViewById(R.id.y2);
        y_11 = (TextView) findViewById(R.id.y11); // через формулу
        y_22= (TextView) findViewById(R.id.y22);

        img4 = (ImageView)findViewById(R.id.imageView4);
        Bundle arguments = getIntent().getExtras();

        MinX1 = arguments.getDouble("MinX1");
        MinX2 = arguments.getDouble("MinX2");
        MaxX1 = arguments.getDouble("MaxX1");
        MaxX2 = arguments.getDouble("MaxX2"); // получил значения, чтобы нормализовать новые входные данные

        MinY1 = arguments.getDouble("MinY1");
        MinY2 = arguments.getDouble("MinY2");
        MaxY1 = arguments.getDouble("MaxY1");
        MaxY2 = arguments.getDouble("MaxY2");

        if(arguments!=null){
            end = (end_vectr) arguments.getSerializable(end_vectr.class.getSimpleName()); // получаю объект класса


        }
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //запускаю постройку графика
                x1 = Double.valueOf(x_1.getText().toString());
                x2 = Double.valueOf(x_2.getText().toString());

                y1 = x1*x1 - x2;
                y2 = x2*x2 - x1;

                y1 =(y1 - MinY1) / (MaxY1 - MinY1); // нормализовал
                y2 =(y2 - MinY2) / (MaxY2 - MinY2);

                x1 =(x1 - MinX1) / (MaxX1 - MinX1); // нормализовал
                x2 =(x2 - MinX2) / (MaxX2 - MinX2);

             end.x1 = x1;
             end.x2 = x2;
             end.Go();
             err1 = y1 - end.y1;
             err2 = y2 - end.y2;

             GoGraf();
             //денормализация и установка текста
                Denorm();
            }
        });
    }


    public void GoGraf(){

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1,err1),
                new DataPoint(5,err2)


        });
        graph.addSeries(series);


//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
//        layout.addView(graph);
    }

    public  void Denorm() {

        y11 = end.y1*(MaxY1 - MinY1) + MinY1; // полученное сетью не нормализованное значение y1
        y22 = end.y2*(MaxY2 - MinY2) + MinY2;

        y1 = y1*(MaxY1 - MinY1) + MinY1; // полученное по формуле не нормализованное значение y1
        y2 = y2*(MaxY2 - MinY2) + MinY2;

        y_1.setText(String.valueOf(y11)); // сетевые
        y_2.setText(String.valueOf(y22));

        y_11.setText(String.valueOf(y1)); //функции
        y_22.setText(String.valueOf(y2));
    }

}
