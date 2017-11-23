package com.hauser.steph.chifoumi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // déclarations des variables
    private Button   playerVScomputer;
    private Button   computerVScomputer;
    private EditText nbManche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assignation des variables de la vue
        playerVScomputer   = (Button) findViewById(R.id.button_player);
        computerVScomputer = (Button) findViewById(R.id.button_computer);
        nbManche           = (EditText) findViewById(R.id.manche);

        // fonctions de changements d'écran selon le choix

        // choix player
        playerVScomputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int mancheInt = Integer.parseInt(nbManche.getText().toString());

                while(String.valueOf(mancheInt) != "") {

                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("choix", 0);

                    mancheInt = Integer.parseInt(nbManche.getText().toString());
                    intent.putExtra("manche", mancheInt);

                    startActivity(intent);
                }
            }
        });

        // choix computer
        computerVScomputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("choix", 1);

                int mancheInt = Integer.parseInt(nbManche.getText().toString());
                intent.putExtra("manche", mancheInt);

                startActivity(intent);
            }
        });
    }
}
