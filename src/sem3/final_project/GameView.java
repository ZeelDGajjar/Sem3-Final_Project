/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vedika
 */

//display all what the user sees during gamePlay 
//no physics calculations 
//user input , speed input TextField, angleSlider, slider, launch button 
//something to draw projectile path 
//level progression (label)
//timer (label)
//result feedback showZombied() animation, hit effects
//styling 
//score 


public class GameView extends Application {

    @Override
    public void start(Stage stage) {
        //textfield 
        TextField speedInput = new TextField();
        //slider to add 
        
        //labels 
        Label levelLabel = new Label("Level");
        Label TimerLabel = new Label("Timer ");
       
       stage.show();
    }
    
    //method 
    public void ShowZombied() {
        //result feedback , animation , hit effect 
        
    }
    
    public void updateScore() {
        
    }
    
}
