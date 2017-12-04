package com.projetointegrador.historyquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button respostaBtn1;
    private Button respostaBtn2;
    private Button respostaBtn3;
    private Button respostaBtn4;

    private String respostaCerta;
    private  int respostaCertaCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] =  {
            //{"Pergunta", "Resposta certa", "Resposta 2", "Resposta 3", "Resposta 4"}
            {"Quem descobriu o Brasil ?", "Pedro Álvares Cabral", "Dom Pedro II", "Tiradentes", "Napoleão"},
            {"Qual foi a primeira exploração econômica dos portugueses ?", "Extração do Pau-Brasil", "Cultivo da Cana-de-Açúcar", "Mineração", "Monocultura cafeeira"},
            {"Qual o nome da lei responsável pela abolição da escravidão ?", "Lei Áurea", "Lei dos Escravos", "Lei Seca", "Lei Rósea"},
            {"“Os pés e as mãos do senhor de engenho”. Essa frase se refere  aos :", "Escravos negros da colônia", "Trabalhos indígenas", "Trabalhos dos bandeirantes", "Trabalhadores livres da colônia"},
            {"Qual foi a primeira capital do Brasil ?", "Salvador", "Rio de Janeiro", "Brasília", "São Miguel"},
            {"Quando foi proclamada a independência do Brasil ?", "7 de setembro", "12 de outubro", "15 de novembro", "1 de abril"},
            {"Quando foi proclamada a República no Brasil ?", "15 de novembro", "1 de janeiro", "21 de abril", "7 de setembro"},
            {"Qual o nome do primeiro presidente do Brasil ?", "Marechal Deodoro da Fonseca", "Marechal Castelo Branco", "Dilma Rousseff", "Rui Barbosa"},
            {"Quem é o autor do hino nacional brasileiro ?", "Joaquim Osório Duque", "Rui Barbosa", "João Carlos Nóbrega", "Joaquim Silvério"},
            {"Que países tentaram invadir o Brasil?", "Holanda e França", "Inglaterra e Holanda", "Espanha e Inglaterra", "França e Inglaterra"},
            {"Qual presidente brasileiro foi vítima do golpe de 1964 ?", "João Goulart", "Juscelino Kubitschek", "Luíz Inácio", "Getúlio Vargas"},
            {"O Regime Militar extinguiu os partidos políticos e criou o :", "Bipartidarismo", "Unipartidarismo", "Movimento Militar", "Regime Militar"},
            {"Em que ano, após o regime militar, deputados federais e senadores se reuniram ?", "1988", "1990", "1998", "1986"},
            {"Onde surgiram os primeiros ancestrais do homem ?", "África", "Ásia", "América do Sul", "América do Norte"},
            {"Qual foi o último continente a ser povoado pelos seres humanos ?", "América", "Oceania", "Europa", "Ásia"},
            {"Como se chama o tipo de pintura que os homens da pré-história desenvolveram nas cavernas ?", "Rupestre", "Contemporânea", "Antiga", "Moderna"},
            {"Qual era a principal atividade econômica da Mesopotâmia ?", "Comércio", "Pesca", "Agricultura", "Pecuária"},
            {"O que aconteceu após as Guerras Médicas ?", "Atenas tornou-se a cidade grega mais importante.", "Os persas dominaram o Mar Egeu.", "A unificação política grega.", "Esparta expandiu seu império marítimo."},
            {"Qual o nome da escrita desenvolvida pelos egípcios ?", "Hieróglifo", "Cuneiforme", "Hebraico", "Acadiano"},
            {"Qual o nome da cidade italiana destruída pelo vulcão Vesúvio ?", "Pompeia", "Roma", "Nápoles", "Herculano"},
            {"Qual o nome do fóssil humano mais antigo já encontrado ?", "Lucy", "Luana", "Luzia", "Luiza"},
            {"Quem era o presidente dos Estados Unidos durante a Primeira Guerra Mundial ?", "Wilson", "Churchill", "Theodore Roosevelt", "Franklin Roosevelt"},
            {"Em que período de tempo ocorreu a Guerra Fria ?","1947 – 1991","1991 - até hoje","1944 - 1981","1887 – 1987"},
            {"Qual país criou o Plano Marshall ?","EUA","URSS","Brasil","Alemanha"},
            {"Qual foi o principal motivo da Revolução Francesa ?","Insatisfação do povo com o reinado feudo-absolutista","A invasão da Alemanha a França","Guerras napoleônicas","A morte do rei Luiz XV"},
            {"Que invasão desencadeou a segunda guerra mundial ?","A invasão da Polônia","A invasão da Rússia","A invasão da Sérvia","A invasão da Tchecoslováquia"},
            {"Qual é o principal líder do movimento fascista ?","Benito Mussolini","Adolf Hitler","Winston Churchill","Stalin"},
            {"O líder Benito Mussolini tinha um lema. Qual era esse lema?","Crer e obedecer","Lutar e vencer","Beber e matar","Exterminar e julgar"},
            {"O nazismo foi uma ditadura que ocorreu entre qual período ?","1933 a 1945","1930 a 1945","1939 a 1945","1914 a 1920"},
            {"O que significa \"Führer\" ?","Líder","Ordem","Armas","General"}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        respostaBtn1 = (Button)findViewById(R.id.respostaBtn1);
        respostaBtn2 = (Button)findViewById(R.id.respostaBtn2);
        respostaBtn3 = (Button)findViewById(R.id.respostaBtn3);
        respostaBtn4 = (Button)findViewById(R.id.respostaBtn4);

        //Criando o quizArray do quizData...
        for (int i = 0; i < quizData.length; i++){

            //Preparando o Array...
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Pergunta
            tmpArray.add(quizData[i][1]); //Resposta certa
            tmpArray.add(quizData[i][2]); //Resposta 2
            tmpArray.add(quizData[i][3]); //Resposta 3
            tmpArray.add(quizData[i][4]); //Resposta 4

            //Adicionar tmpArray para o quiz...
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        //Atualizando o contador do quiz...
        countLabel.setText("QUESTÃO " + quizCount);

        //Gerando número aleatório entre a quantidade de perguntas...
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //No quiz set...
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Selecionando a questão e a resposta certa...
        //Formato do Array: {"Pergunta", "Resposta certa", "Resposta 2", "Resposta 3", "Resposta 4"}
        questionLabel.setText(quiz.get(0));
        respostaCerta = quiz.get(1);

        //Removendo a "pergunta" do quiz e embaralhando as respostas...
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Selecionando as respostas...
        respostaBtn1.setText(quiz.get(0));
        respostaBtn2.setText(quiz.get(1));
        respostaBtn3.setText(quiz.get(2));
        respostaBtn4.setText(quiz.get(3));

        //Removendo este "quiz" do quizArray
        quizArray.remove(randomNum);

    }

    public void checarResposta(View view) {

        //Quando o botão for apertado...
        Button respostaBtn = (Button)findViewById(view.getId());
        String btnText = respostaBtn.getText().toString();
        String alertTitle;

        if (btnText.equals(respostaCerta)) {
            //Certa!
            alertTitle = "Acertou!";
            respostaCertaCount++;
        } else {
            //Errada!
            alertTitle = "Errou...";

        }
        //Caixa de msg.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Resposta: " + respostaCerta);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    //Resultado.
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("Respostas Certas", respostaCertaCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
