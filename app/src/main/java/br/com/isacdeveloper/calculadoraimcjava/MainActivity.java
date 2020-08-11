package br.com.isacdeveloper.calculadoraimcjava;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView cardViewResultado;
    Button btnCalcularImc;
    EditText edtPeso, edtAltura;
    TextView txtResultado;
    boolean isFormularioOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();

        btnCalcularImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFormularioOK = validarFormulario())
                    calcular();
            }
        });


    }

    private void calcular() {
        //Cria as Variaveis de Peso e Altura convertidos para decimal e inteiro
        int peso = Integer.parseInt(edtPeso.getText().toString());
        Double altura = Double.parseDouble((edtAltura.getText().toString()));

        //Variavel Responsavel por realizar a operação
        Double resultado = peso / (altura * altura);

        if (resultado <= 16.99) {     //Condição Se Verdadeiro, Mostrar Abaixo do Peso
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Muito Abaixo do Peso! Seu IMC é: %.2s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Muito Abaixo do Peso! Recomendamos Consulta Com Especialista!", Toast.LENGTH_LONG).show();
        } else if (resultado >= 17 && resultado <= 18.49) {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Abaixo do Peso! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Abaixo do Peso!", Toast.LENGTH_LONG).show();
        } else if (resultado >= 18.5 && resultado <= 24.9) {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está no Peso Ideal! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está No Peso Ideal!", Toast.LENGTH_LONG).show();
        } else if (resultado >= 25 && resultado <= 29.99) {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Acima do Peso! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Acima do Peso!", Toast.LENGTH_LONG).show();
        } else if (resultado >= 30 && resultado <= 34.99) {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Com Obesidade Tipo 1! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Com Obesidade Grau 1!", Toast.LENGTH_LONG).show();
        } else if (resultado >= 35 && resultado <= 39.99) {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Com Obesidade Tipo 2 (Severa)! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Com Obesidade Grau 2!", Toast.LENGTH_LONG).show();
        } else {
            cardViewResultado.setVisibility(View.VISIBLE);
            txtResultado.setText(String.format("Você Está Com Obesidade Tipo 3 (Mórbida)! Seu IMC é: %.4s", resultado).toUpperCase());
            Toast.makeText(MainActivity.this, "Você Está Com Obesidade Grau 3! Recomendamos Consulta Com Especialista!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarFormulario() {
        boolean retorno = true;

        //Variavel temporária, só existe na validação visando melhor abstração do conteudo.
        String peso = edtPeso.getText().toString();
        String altura = edtAltura.getText().toString();

        //Validar Peso
        if (TextUtils.isEmpty(peso)) {
            edtPeso.setError("*");
            edtPeso.requestFocus();
            Toast.makeText(MainActivity.this, "Preencha o Peso Corretamente!", Toast.LENGTH_LONG).show();
            retorno = false;
        } else {
            //Validar Altura
            if (TextUtils.isEmpty(altura)) {
                edtAltura.setError("*");
                edtAltura.requestFocus();
                Toast.makeText(MainActivity.this, "Preencha a Altura Corretamente!\nSubstituir a Vírgula pelo Ponto", Toast.LENGTH_LONG).show();
                retorno = false;
            }
        }

        //Retorna o valor booleano de acordo com as comparações
        return retorno;
    }

    private void initFormulario() {

        cardViewResultado = findViewById(R.id.cardViewResultado);
        cardViewResultado.setVisibility(View.INVISIBLE);

        btnCalcularImc = findViewById(R.id.btnCalcularImc);

        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        txtResultado = findViewById(R.id.txtResultado);

        isFormularioOK = false;

    }
}