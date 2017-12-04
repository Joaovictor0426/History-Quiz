package com.projetointegrador.historyquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalLabel = (TextView)findViewById(R.id.totalLabel);

        int pontos = getIntent().getIntExtra("Respostas Certas", 0);

        SharedPreferences settings = getSharedPreferences("History Quiz", Context.MODE_PRIVATE);
        int totalPontos = settings.getInt("totalPontos", 0);
        totalPontos += pontos;

        resultLabel.setText(pontos + " / 5");
        totalLabel.setText("Total de Pontos : " + totalPontos);

        //Atualizando o total de pontos.
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalPontos", totalPontos);
        editor.commit();

    }

    public void voltar(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
