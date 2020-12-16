package EnigmaSimulator;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * Controller klasse fuer die verwaltung der
 * Einstellungsoberflaeche
 * Um bestimmte veraenderungen an der 
 * Enigma vor zunehmen
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
public class EinstellungenController {
	
	/** Oberflaechen Element ToggleButton fuer das wechseln zwischen Live und Original modus der Enigma Oberflaeche*/
	@FXML private ToggleButton btnOrginalEnigmaMode;
	/** Oberflaechen Element Button fuer das Speichern der vorgenommenen Einstellungen*/
	@FXML private Button btnSpeichern;
	/** Oberflaechen Element Button fuer das Laden der Standardwerte der Einstellungen*/
	@FXML private Button btnLadeStandartwerte;
	/** Oberflaechen Element Button fuer das Schließen des Einstellung Fensters*/
	@FXML private Button btnSchliessen;
	/** Oberflaechen Element TextFeld fuer die Eingabe der Anzahl der ausgaben Gruppierung der Enigma*/
	@FXML private TextField txtPaintingGroups;
	/** Oberflaechen Element TextFeld fuer die Auswahl der zuffaellig zu erzeugenden Steckverbindungen*/
	@FXML private TextField txtRandomeSteckCount;
	/** Oberflaechen Element CheckBox zur Auswahl ob bei der Abspeicherung der Einstellungen in eine Datei die 
	 * Eingabe bzw. Ausgabe mit Abgespeichert werden soll*/
	@FXML private CheckBox chbEinAusgabeTextSpeichern;
	
	/** Controller element der HauptGUI*/
	private static EnigmaController controller;
	
	//standartwerte
	/** Standardwert fuer die Painting Groups*/
	public final static int DEF_PAINTING_GROUPS = 5;
	/** Standardwert fuer die Zufaellingen steckverbindungen = 10 wegen hoechster Sicherheit*/
	public final static int DEF_RANDOME_STECK_COUNT = 10;
	/** Standardwert ob die Oberflaeche im live mode oder im Original Enigma modus lauft */
	public final static boolean DEF_ORIGINAL_MODE = false;
	/** Standardwert ob der Eingabetext beim Speicher der Einstellungen mit gespeichert werden soll*/
	public final static boolean DEF_EIN_AUSGABE_TEXT_SPEICHERN = false;
	
	/**
	 * Legt den zu benutzenden Controller fuer die abfrage der hauptGUI fest
	 * 
	 * @param controller Controller der benutzt werden soll
	 */
	public static void setEnigmaController(EnigmaController controller) {
		EinstellungenController.controller = controller;
	}
	
	/**
	 * Initialisiert die Einstellungsfenster oberflaeche
	 */
	public void initialize() {
		txtPaintingGroups.setText(Long.toString(EnigmaController.getPaintingGroups()));
		txtRandomeSteckCount.setText(Integer.toString(EnigmaController.getRandSteckBrettAnzahl()));
		
		chbEinAusgabeTextSpeichern.setSelected(EnigmaController.isEinAusgabeTextSpeichern());	
		
		boolean originalEnigmaModeSpeicher = EnigmaController.isOriginalEnigmaModeOnOff();
		
		btnOrginalEnigmaMode.setSelected(originalEnigmaModeSpeicher);
		if(originalEnigmaModeSpeicher) {
			btnOrginalEnigmaMode.setText("Aktiv");
		} else {
			btnOrginalEnigmaMode.setText("Inaktiv");
		}
	}
	
	/**
	 * Handelt die Aktion des Speichern buttons 
	 * und speichert die Eingaben im Controller um diese zu verwenden
	 */
	@FXML
	private void handlebtnSpeichern() {
		
		int randomeSteckCount = Integer.parseInt(txtRandomeSteckCount.getText().replaceAll("[^0-9]", ""));
		if(randomeSteckCount > 13) {
			/* Fehler Fenster fuer Eingabe von werten ueber 13 als zuffaellige Einstellungen*/
			Alert steckOverMax13 = new Alert(AlertType.ERROR);
			steckOverMax13.setHeaderText("Speichern Fehlgeschlagen");
			steckOverMax13.setContentText("Die Anzahl der Zufälligen Steckverbindungen darf 13 nicht überschreiten"
					+ " bitte einen kleineren Wert eingeben");
			steckOverMax13.show();
			
			txtRandomeSteckCount.setStyle("-fx-background-color: red;");
			
			
			return;
		}
		else {
			EnigmaController.setRandSteckBrettAnzahl(randomeSteckCount);
			txtRandomeSteckCount.setStyle(null);
		}	
		
		boolean originalEnigmamode = btnOrginalEnigmaMode.isSelected();
		if(originalEnigmamode && (EnigmaController.isAnalyseMonitorAktiv() || EnigmaController.isAnalyseMonitorGroßAktiv())){
			/* Fehler Fenster wenn AnalyseMonitor geoeffent ist*/
			Alert errorAnalyseAktiv = new Alert(AlertType.ERROR);
			errorAnalyseAktiv.setHeaderText("Speichern Fehlgeschlagen");
			errorAnalyseAktiv.setContentText("Zur zeit ist der AnalyseMonitor geöffent bitte Schließen um in den LiveModus zu wechseln");
			errorAnalyseAktiv.show();
			return;
		} else {
			controller.setBtnEinstellungenAndTxaEingabeDisableAktive(originalEnigmamode);
			EnigmaController.setOriginalEnigmaModeOnOff(originalEnigmamode);
		}
		
		long paintingGroups = Long.parseLong(txtPaintingGroups.getText().replaceAll("[^0-9]", ""));
		EnigmaController.setPaintingGroups(paintingGroups);
		
		EnigmaController.setEinAusgabeTextSpeichern(chbEinAusgabeTextSpeichern.isSelected());
	}
	
	/**
	 * handelt die Aktion des Schließen buttons und beendet die oberflaeche
	 */
	@FXML
	private void handlebtnSchliessen() {
		Stage stage = (Stage) btnSchliessen.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * handelt die Aktion des Reset Buttons und 
	 * stellt die Standardwerte der Einstellungen
	 * wieder her 
	 */
	@FXML
	private void handleResetbtn() {
		txtPaintingGroups.setText(Integer.toString(DEF_PAINTING_GROUPS));
		txtRandomeSteckCount.setText(Integer.toString(DEF_RANDOME_STECK_COUNT));
		
		chbEinAusgabeTextSpeichern.setSelected(DEF_EIN_AUSGABE_TEXT_SPEICHERN);
		
		btnOrginalEnigmaMode.setSelected(DEF_ORIGINAL_MODE);
		handlebtnOrginalEnigmaMode();
		
		handlebtnSpeichern();
	}
	
	/**
	 * handelt die Eingabe des Original Enigma Mode buttons und aendert seine beschriftung
	 */
	@FXML
	private void handlebtnOrginalEnigmaMode() {
		boolean originalEnigmaModeSelect = btnOrginalEnigmaMode.isSelected();
		
		if(originalEnigmaModeSelect) {
			btnOrginalEnigmaMode.setText("Aktiv");
		} else {
			btnOrginalEnigmaMode.setText("Inaktiv");
		}
	}
}
