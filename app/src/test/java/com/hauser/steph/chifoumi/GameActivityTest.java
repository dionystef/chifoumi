package com.hauser.steph.chifoumi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by steph on 23/11/2017.
 */

public class GameActivityTest {

    private Game_controller gameController;

    @Before
    public void before(){
        gameController = new Game_controller();
    }


    @Test
    public void ReturnNumberChoiceComputer(){
        Assert.assertNotNull(gameController.ChoiceComputer());
    }

    @Test
    public void TestCompareEqual(){
        assertEquals("égalité", gameController.compare(1,1));
    }

    @Test
    public void TestCompareGagne(){
        assertEquals("gagné", gameController.compare(0,1));
        assertEquals("gagné", gameController.compare(1,2));
        assertEquals("gagné", gameController.compare(2,0));
    }

    @Test
    public void TestComparePerdu(){
        assertEquals("perdu", gameController.compare(2,1));
        assertEquals("perdu", gameController.compare(0,2));
    }

}
