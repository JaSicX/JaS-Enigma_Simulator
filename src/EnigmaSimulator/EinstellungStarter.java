package EnigmaSimulator;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasse zum starten des Einstellungs Fensterns
 * um darin Einstellungen vorzunehmen
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
public class EinstellungStarter extends Stage {

	/**
	 * Methode zum starten des Einstellungs Fensterns
	 */
	public EinstellungStarter() {
        try {
        	Parent root = FXMLLoader.load(EinstellungStarter.class.getResource("EinstellungensFenster.fxml"));
			Scene scene = new Scene(root);
			Stage EinstellungsFenster = new Stage();
			EinstellungsFenster.setScene(scene);
			EinstellungsFenster.getIcons().add(new Image(EinstellungStarter.class.getResourceAsStream("icon.png"))); 
			EinstellungsFenster.setTitle("Einstellungen");
			EinstellungsFenster.setResizable(false);

			EinstellungsFenster.initModality(Modality.APPLICATION_MODAL);
	        
			EinstellungsFenster.initOwner(Main.getStage());
		
			EinstellungsFenster.show();
			
			

		}catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
