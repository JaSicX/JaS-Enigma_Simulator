package EnigmaSimulator;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Klasse zum starten des Großen AnalyseMonitor Fensters
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
public class AnalyseMonitorGrossStarter extends Stage{
	
    /**
     * Methode zum starten des Großen AnalyseMonitor Fensters
     */
    public AnalyseMonitorGrossStarter(){
        try {
        	FXMLLoader fxmlLoader = new FXMLLoader(AnalyseMonitorGrossStarter.class.getResource("AnalyseMonitorGross.fxml"));
        	Parent root = fxmlLoader.load();
        	AnalyseMonitorGrossController controller = fxmlLoader.getController();
        	
        	EnigmaController.setControllerGroß(controller);
			Scene scene = new Scene(root);
			Stage AnalyseMonitor = new Stage();
			AnalyseMonitor.setScene(scene);
			AnalyseMonitor.setTitle("Analyse Monitor Groß");
			AnalyseMonitor.getIcons().add(new Image(AnalyseMonitorGrossStarter.class.getResourceAsStream("icon.png"))); 
			AnalyseMonitor.initOwner(Main.getStage());
			
			EnigmaController.setAnalyseMonitorGroßAktiv(true);
			AnalyseMonitor.show();
			
			//nur max Grosse gesetzt wegen praktischer Anwendung des Verkleinerns 
			AnalyseMonitor.setMaxHeight(AnalyseMonitor.getHeight());
			AnalyseMonitor.setMaxWidth(AnalyseMonitor.getWidth());
			
			AnalyseMonitor.setOnCloseRequest(event ->{
				EnigmaController.setAnalyseMonitorGroßAktiv(false);;
			});

		}catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
