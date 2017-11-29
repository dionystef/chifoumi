package com.hauser.steph.chifoumi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Game_controller controller = new Game_controller();

    private int      choixPlayer;
    private int      nbManche;
    private int      scorePlayer;
    private int      scoreComputer;

    private TextView computer_choice;
    private TextView computer_score;
    private TextView player_score;
    private TextView player_choice;
    private TextView player_name;
    private View     include_menu;
    private Button   choice_ciseaux;
    private Button   choice_pierre;
    private Button   choice_papier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // assignation des variables de la vue
        include_menu      = (View)     findViewById(R.id.choice_menu);
        computer_choice   = (TextView) findViewById(R.id.computer_choice);
        computer_score    = (TextView) findViewById(R.id.computer_score);
        player_score      = (TextView) findViewById(R.id.player_score);
        player_choice     = (TextView) findViewById(R.id.player_choice);
        player_name       = (TextView) findViewById(R.id.player_name);
        choice_ciseaux    = (Button)   include_menu.findViewById(R.id.choice_ciseaux);
        choice_pierre     = (Button)   include_menu.findViewById(R.id.choice_pierre);
        choice_papier     = (Button)   include_menu.findViewById(R.id.choice_papier);

        // reset score
        scorePlayer   = 0;
        scoreComputer = 0;

        // récupération du choix du joueur
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        choixPlayer = extras.getInt("choix");
        nbManche    = extras.getInt("manche");

        // selection du choix du joueur
        switch(choixPlayer){
            // player vs computer
            case 0:
                player_name.setText("player");
                choice_ciseaux.setOnClickListener(this);
                choice_pierre.setOnClickListener(this);
                choice_papier.setOnClickListener(this);
                break;

            // computer vs computer
            case 1:
                player_name.setText("ordinateur");
                break;

            default:
        }
    }

    @Override
    public void onClick(View view) {
        int scoreTempPlayer   = scorePlayer;
        int scoreTempComputer = scoreComputer;
        int temp = -1;

            switch (view.getId()){

                case R.id.choice_pierre :
                    // affichage du choix du joueur
                    player_choice.setText(choice_pierre.getText());

                    // affichage du choix du pc
                    temp = controller.ChoiceComputer();
                    setChoiceComputer(temp);

                    // affichage du resultat
                    String result = controller.compare(0, temp);
                    resultat(result, scoreTempPlayer,scoreTempComputer);
                    break;

                case R.id.choice_ciseaux :
                    player_choice.setText(choice_ciseaux.getText());

                    // affichage du choix du pc
                    temp = controller.ChoiceComputer();
                    setChoiceComputer(temp);

                    result = controller.compare(1, temp);
                    resultat(result, scoreTempPlayer,scoreTempComputer);
                    break;

                case R.id.choice_papier :
                    player_choice.setText(choice_papier.getText());

                    // affichage du choix du pc
                    temp = controller.ChoiceComputer();
                    setChoiceComputer(temp);

                    result = controller.compare(2, temp);
                    resultat(result, scoreTempPlayer,scoreTempComputer);
                    break;
            }
    }

    private void setChoiceComputer(int i){
        switch (i){
            case 0:
                computer_choice.setText("pierre");
                break;
            case 1:
                computer_choice.setText("ciseaux");
                break;
            case 2:
                computer_choice.setText("papier");
                break;
            default:
                // erreur
        }
    }

    public void resultat(String result, int scoreTempPlayer, int scoreTempComputer){
        if ( result == "gagné"){
            scorePlayer = scoreTempPlayer + 1;
            player_score.setText(String.valueOf(scorePlayer));
            Toast toast = Toast.makeText(this, result, LENGTH_SHORT);
            toast.show();
        }else {
            if (result == "perdu"){
                scoreComputer = scoreTempComputer + 1;
                computer_score.setText(String.valueOf(scoreComputer));
            }
            Toast toast = Toast.makeText(this, result, LENGTH_SHORT);
            toast.show();
        }
        endSession();
    }

    private void endSession(){
        if (scorePlayer == nbManche || scoreComputer == nbManche){
            if (scorePlayer > scoreComputer){
                String verdict = " Bravo tu as gagné !!";
                Toast toast = Toast.makeText(this, verdict, LENGTH_SHORT);
                toast.show();
            } else if(scorePlayer < scoreComputer){
                String verdict = " Tu as perdu :( ";
                Toast toast = Toast.makeText(this, verdict, LENGTH_SHORT);
                toast.show();
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    GameActivity.this.finish();
                }
            }, 3500);
        }
    }
}
