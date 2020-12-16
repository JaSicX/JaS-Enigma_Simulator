package EnigmaSimulator;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Klasse zur Verwaltung des Key Manager Fensters
 * 
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
public class KeyManagerController {
	
	/** Oberflaechen Element TextFeld fuer die Eingabe eines Keys der die Einstellungen der Enigma repraesentiert*/
	@FXML TextField txtEingabeKey;
	/** Oberflaechen Element TextFeld fuer die Ausgabe eines Keys der die Einstellungen der Enigma repraesentiert*/
	@FXML TextField txtGenerierterKey;
	/** Oberflaechen Element Button fuer das Einlesen eines Keys aus dem Eingabefeld und dessen Umwandlung in die Einstellungen
	 * fuer die Enigma sowie das festsetzten dieser Einstellungen*/
	@FXML Button btnEinstellungenSetzen;
	/** Oberflaechen Element Button kopiert den Erzeugten Key aus dem Ausgabefeld in die Zwischenablage des Nutzer-PCs*/
	@FXML Button btnKopiereKey;
	/** Oberflaechen Element Button fuer das Erzeugen eines neuen Keys aus den Einstellungen der Enigma*/
	@FXML Button btnKeyGenerieren;
	/** Oberflaechen Element Button schließt das geoeffnete Fenster*/
	@FXML Button btnSchließen;
	
	/** Controller element der HauptGUI*/
	private static EnigmaController controller;
	/** Stage element des derzeitigen Fensters*/
	private static Stage keyManagerStage;
	

	/**
	 * Legt den zu benutzenden Controller fuer die abfrage der hauptGUI fest
	 * 
	 * @param controller Controller der benutzt werden soll
	 */
	public static void setEnigmaController(EnigmaController controller) {
		KeyManagerController.controller = controller;
	}
	
	/**
	 * Legt die aktuelle Satge fest
	 * 
	 * @param keyManagerStage die zu benutzende Stage
	 */
	public static void setKeyManagerStage(Stage keyManagerStage) {
		KeyManagerController.keyManagerStage = keyManagerStage;
	}

	/**
	 * handelt die Eingabe des Einstellungs setzten Buttons
	 * 
	 * Liest den eingegeben Key ein und decodiert ihn und legt die 
	 * Einstellungen in der Haupt GUI fest
	 * Falls dieser nicht korrekt ist wird eine Fehler Meldung ausgegeben
	 */
	@FXML
	private void handleBtnEinstellungenSetzen() {
		String keyS = txtEingabeKey.getText();
		
		EnigmaSettingsToValueForKeys key = new EnigmaSettingsToValueForKeys(keyS);
		
		// Zuweisung des extra array koennte gespart werden
		int[] enigmaEinst = null; 
		String[] enigmaSteck = null;
		
		try {
			if(key.getEinstandSteckfromBase58()) {
				enigmaEinst = key.getEnigmaEinst();
				enigmaSteck = key.getEnigmaSteck();
				
				if(!checkObEinstellungenValide(enigmaEinst, enigmaSteck)) {
					showErorrDialog("Der eingegebene key ist Falsch oder nicht Valide !");
					return;
				} 
			} 
			else {
				showErorrDialog("Der eingegebene key ist Falsch oder nicht Valide !");
				return;
			}
			
			controller.setEinstellEnigmaExtern(enigmaEinst, enigmaSteck, key.isEnigmaM4aktiv());
			
		} catch (Exception e) {		//faengt etwaige mogliche fehler bei der konvertierung des keys ab und gibt eine fehlermeldung aus
			showErorrDialog("Der eingegebene key ist Falsch oder nicht Valide !");
			return;
		}
		
		keyManagerStage.close();
	}
	
	/**
	 * handelt die Eingabe des KeyGeneriren buttons
	 * Dabei werden die Einstellungen aus der oberflaeche ausgelesen und
	 * dann wird dies in einen Key umgewandelt der im Textfeld GenerierterKey
	 * angezeigt wird
	 */
	@FXML
	public void handleBtnKeyGenerieren() {
		Object[] enigmaEinstGesamt = controller.getEnigmaExtern();
		
		int[] enigmaEinst = (int[]) enigmaEinstGesamt[0];
		String[] enigmaSteck = (String[]) enigmaEinstGesamt[1];
		boolean isEnigmaM4aktiv = (boolean) enigmaEinstGesamt[2];
		
		
		EnigmaSettingsToValueForKeys newKey = new EnigmaSettingsToValueForKeys(enigmaEinst, enigmaSteck, isEnigmaM4aktiv);
		String Base58Key = newKey.makeBase58Key();
		
		txtGenerierterKey.setText(Base58Key);
	}
	
	/**
	 * handelt die Eingabe des schließen buttons
	 * und schließt das Fenster
	 */
	@FXML
	public void handleBtnSchließen() {
		keyManagerStage.close();
	}
	
	/**
	 * Ruft einen Error Dialog auf
	 * 
	 * @param text der Anzuzeigende Text
	 */
	private static void showErorrDialog(String text) {
		Alert errorKeyFalse = new Alert(AlertType.ERROR);
		errorKeyFalse.setContentText(text);
		errorKeyFalse.show();
	}
	
	/**
	 * handelt die Eingabe des Kopiert buttons
	 * und kopiert den generierten Key in die Zwischenablage
	 */
	@FXML
	private void handleBtnKopiereKey() {
		String kopieKey = txtGenerierterKey.getText();
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(kopieKey), null);
	}
	
	/**
	 * Prueft ob die uebergebenen Einstellungen Valide sind (Checkt Enigma Interne Werte)
	 * 
	 * @param EnigmaEinstellungen Ein einstellungs Array fuer die Enigma
	 * @param SteckBrett Ein Steckbrett Array fuer die Enigma 
	 * @return Ob uebergebene Einstellungen Valide
	 */
	private static boolean checkObEinstellungenValide(int[] EnigmaEinstellungen, String[] SteckBrett) {
		
		//Rotor 4 check
		if( !(EnigmaEinstellungen[0] == 10 || EnigmaEinstellungen[0] == 11) ) {
			return false;
		}
		
		//UKW check
		if( !(EnigmaEinstellungen[12] == 1 || EnigmaEinstellungen[12] == 2 || 
				EnigmaEinstellungen[12] == 10 ||EnigmaEinstellungen[12] == 11) ) {
			return false;
		}
		
		//Rotoren 1-3, Rotorstellung und Ringstellung check
		for(int i = 1; i < EnigmaEinstellungen.length-1; i++) {
			if(i % 3 == 0) {
				
				//Rotoren 1-3
				if(EnigmaEinstellungen[i] > 8) {
					return false;
				}
				continue;
			}
			
			//Rotor- und Ringstellung
			if(EnigmaEinstellungen[i] > 25) {
				return false;
			}
		}
		
		// Steckbrett check
		String steckABPruefen = "";
		for(int i = 0; i < SteckBrett.length; i++) {
			
			if(SteckBrett[i] == "") {
				continue;
			}
			
			if( (( -1 != steckABPruefen.indexOf(SteckBrett[i].charAt(0))) || 		//prueft auf Doppeleinagben
					-1 != steckABPruefen.indexOf(SteckBrett[i].charAt(1))) ||
					( SteckBrett[i].charAt(0) == SteckBrett[i].charAt(1))) {
				return false;
			}
			steckABPruefen = steckABPruefen + SteckBrett[i];
		}
		
		return true;
	}
}
