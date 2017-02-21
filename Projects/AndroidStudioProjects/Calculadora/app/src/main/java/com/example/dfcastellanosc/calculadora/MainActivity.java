package com.example.dfcastellanosc.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText numeroUno;
    private EditText numeroDos;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroUno = (EditText) findViewById(R.id.numberOne);
        //numeroDos = (EditText) findViewById(R.id.numberTwo);
        resultado = (TextView) findViewById(R.id.mensaje);
    }

    public void setPlus(View view) {
        String valor1 = numeroUno.getText().toString();

        List<String> listC = new ArrayList<>();

        listC.add(valor1);


        String df;
        for (Object c : listC) {
            df = c.toString();
            char[] chars = df.toCharArray();
            List<char[]> cas2 = Arrays.asList(chars);
            System.out.println(cas2.get(0));

        }


        if (!valor1.startsWith("+")) {
            String plus = "+";
            String conca = plus + valor1;
            numeroUno.setText(conca);
            resultado.setText("");
        } else {
            resultado.setText("Digita un numero");
        }
    }


}
