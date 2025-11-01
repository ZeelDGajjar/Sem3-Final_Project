/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sem3.final_project;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
        Slider angleSlider = new Slider();
        
        //button 
        Button Launch = new Button("Launch");
        
        //labels 
        Label levelLabel = new Label("Level");
        Label TimerLabel = new Label("Timer ");
        
          angleSlider.valueProperty().addListener((observeable, oldvalue, newvalue) −>
          {e -> handelSlider()
             });
       
       stage.show();
    }
    
    //method 
    public void ShowZombied() {
        //result feedback , animation , hit effect 
        
    }
    
    public void updateScore() {
        
    }

    private void handelSlider() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
