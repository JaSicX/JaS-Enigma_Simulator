package EnigmaSimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


/**
 * Start Klasse fuer den Enigma Simulator
 * 
 * @author Jasley Jangk
 *
 *
 * Copyright (C) 2017-2020 Jasley Jangk | Github JaSicX
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This program is licensed under GPL-2.0-or-later
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2,
 * as published by the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY!
 * See the GNU General Public License for more details. http://www.gnu.org/licenses/
 */
public class Main extends Application {
	
	private static Stage mainSatge;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("EnigmaGUI.fxml"));
			
			BorderPane root = loader.load();
			Scene scene = new Scene(root);
			
			//Enigma Hauptfenster Controller uebergeben an die unter Controller
			EnigmaController controller = loader.getController();
			KeyManagerController.setEnigmaController(controller);
			EinstellungenController.setEnigmaController(controller);
			
			mainSatge = primaryStage;
			
			primaryStage.setTitle("Enigma Simulator");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png"))); 
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setMinHeight(800);
			primaryStage.setMinWidth(400);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return Gibt das derzeitig genutzte Stage Objekt zurueck
	 */
	public static Stage getStage() {
			return mainSatge;
	}
	
	/**
	 * Startet die Anwendung
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
