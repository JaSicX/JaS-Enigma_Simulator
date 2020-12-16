package EnigmaSimulator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse zum starten des Key Manager Fensters
 * um Keys zu generieren oder einzugeben
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
public class KeyManagerStarter extends Stage {
	
	/**
	 * Methode zum starten Key Manager Fensters
	 */
	public KeyManagerStarter() {
		try {
			Parent root = FXMLLoader.load(KeyManagerStarter.class.getResource("KeyManger.fxml"));
			Scene scene = new Scene(root);
			Stage keyManager = new Stage();
			
			KeyManagerController.setKeyManagerStage(keyManager);
			
			keyManager.setScene(scene);
			keyManager.getIcons().add(new Image(KeyManagerStarter.class.getResourceAsStream("icon.png"))); 
			keyManager.setTitle("Key Manager");
			keyManager.setResizable(false);
			
			keyManager.initModality(Modality.APPLICATION_MODAL);
	        
			keyManager.initOwner(Main.getStage());
			
			keyManager.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
