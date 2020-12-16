package EnigmaSimulator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse zum starten des Info Fensters
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
public class InfoFensterStarter extends Stage {
	
	/**
	 * Methode zum starten Info Fensters
	 */
	public InfoFensterStarter() {
		try {
			Parent root = FXMLLoader.load(InfoFensterStarter.class.getResource("InfoFenster.fxml"));
			Scene scene = new Scene(root);
			Stage infoFenster = new Stage();
			
			KeyManagerController.setKeyManagerStage(infoFenster);
			
			infoFenster.setScene(scene);
			infoFenster.getIcons().add(new Image(InfoFensterStarter.class.getResourceAsStream("icon.png"))); 
			infoFenster.setTitle("Info");
			infoFenster.setResizable(false);

			infoFenster.initModality(Modality.APPLICATION_MODAL);
			infoFenster.initOwner(Main.getStage());
			
			infoFenster.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
