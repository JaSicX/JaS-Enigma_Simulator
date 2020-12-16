package EnigmaSimulator;

import java.awt.Desktop;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * Controller zum verwalten der Eingaben des Info Fensters
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
public class InfoFensterController {
	
	/** Button fuer das Schließen des Fensters*/
	@FXML Button btnOKclose;
	/** Hyperlink zum verliken auf das Github Repo */
	@FXML Hyperlink hplLinkToGithub;
	

	/**
	 * Handelt das Anklicken des Hyperlinks zum Github Account fuer diese Anwendung
	 */
	@FXML
	private void handleHPLinkToGithub() {
		try {
			Desktop.getDesktop().browse(new URL("https://github.com/JaSicX").toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handelt die Eingabe des btnOKclose Buttons und schließt das Fenster
	 */
	@FXML
	private void handleBtnOKclose() {
		Stage stage = (Stage) btnOKclose.getScene().getWindow();
		stage.close();
	}
}
