package com.hauser.steph.chifoumi;

/**
 * Created by steph on 22/11/2017.
 */

public class Game_controller {


    // renvois le choix de l'ordinateur
    public int ChoiceComputer(){
        return (int)(Math.random()*3);
    }

    // fonction qui compare le résultat
    public String compare(int i, int j){
        if (i == j){
            return "égalité";
        }
        int ij = Integer.valueOf(i + "" + j);

        if(ij == 1 || ij == 12 || ij == 20){
            return "gagné";
        }else {
            return "perdu";
        }
    }


}
