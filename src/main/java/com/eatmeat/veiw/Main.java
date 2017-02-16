package com.eatmeat.veiw;

import com.eatmeat.controller.exception.ControllerException;
import com.eatmeat.controller.exception.ExitException;
import com.eatmeat.controller.exception.UnknownCommand;
import com.eatmeat.controller.impl.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        Controller controller = new Controller();

        while (true) {
            try {
                userInput = userInputReader.readLine();
            } catch (IOException e) {
                System.out.println("IO Exception while reading keyboard input. Program will be terminated");
                return;
            }
            try {
                System.out.println(controller.executeTask(userInput));
            } catch (ExitException e) {
                return;
            } catch (UnknownCommand e){
                System.out.println("Unknown Command");
            } catch (ControllerException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
