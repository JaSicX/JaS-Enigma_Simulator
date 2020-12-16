package EnigmaSimulator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Klasse zur Verwaltung des kleinen Analysemonitors
 * 
 * Dieser zeigt den weg der Verschluesselung durch die walzen in einfacher form.
 * Um ein Besseres Verstaendnis der Arbeitsweise der Enigma Verschluesselungs Maschine zu erhalten.
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
public class AnalyseMonitorController {
	
	/** JavaFX Text Feld fuer Ergebnis der Vertauschung durch das Steckbrett*/
	@FXML private TextField txtSteckEing;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des ersten Rotors*/
	@FXML private TextField txtRotor1Eing;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des zweiten Rotors*/
	@FXML private TextField txtRotor2Eing;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des dritten Rotors*/
	@FXML private TextField txtRotor3Eing;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des vierten Rotors*/
	@FXML private TextField txtRotor4Eing;
	/** JavaFX Text Feld fuer das Ergebnis der Vertauschung des UKW Rotors*/
	@FXML private TextField txtUKW;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des ersten Rotors auf dem Rueckweg*/
	@FXML private TextField txtRotor1Ausg;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des zweiten Rotors auf dem Rueckweg*/
	@FXML private TextField txtRotor2Ausg;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des dritten Rotors auf dem Rueckweg*/
	@FXML private TextField txtRotor3Ausg;
	/** JavaFX Text Feld fuer das Ergebnis der Verschluesselung des vierten Rotors*/
	@FXML private TextField txtRotor4Ausg;
	/** JavaFX Text Feld fuer Ergebnis der Vertauschung durch das Steckbrett auf dem Rueckweg*/
	@FXML private TextField txtSteckAusg;
	
	
	/**
	 * Zeigt die Zwischenschritte in der Verschluesselung in den 
	 * Textfeldern an bzw. somit wird der Verchluesselungsweg durch die 
	 * Walzen verdeutlicht 
	 * Fuer Enigma M4
	 * 
	 * @param CharsZwischenSchritte Array das die einzelnen schritte der Verschluesslung als Buchstaben enthaelt
	 */
	public void AusgabeCharsMonitorZwischenSchritteEnigmaM4(char[] CharsZwischenSchritte) {
	
		txtSteckEing.setText(Character.toString(CharsZwischenSchritte[0]));
		txtRotor1Eing.setText(Character.toString(CharsZwischenSchritte[4]));
		txtRotor2Eing.setText(Character.toString(CharsZwischenSchritte[8]));
		txtRotor3Eing.setText(Character.toString(CharsZwischenSchritte[12]));
		txtRotor4Eing.setText(Character.toString(CharsZwischenSchritte[16]));
		txtUKW.setText(Character.toString(CharsZwischenSchritte[19]));
		txtRotor4Ausg.setText(Character.toString(CharsZwischenSchritte[22]));
		txtRotor3Ausg.setText(Character.toString(CharsZwischenSchritte[26]));
		txtRotor2Ausg.setText(Character.toString(CharsZwischenSchritte[30]));
		txtRotor1Ausg.setText(Character.toString(CharsZwischenSchritte[34]));
		txtSteckAusg.setText(Character.toString(CharsZwischenSchritte[36]));
	}
	
	/**
	 * Zeigt die Zwischenschritte in der Verschluesselung in den 
	 * Textfeldern an bzw. somit wird der Verchluesselungsweg durch die 
	 * Walzen verdeutlicht 
	 * Fuer Enigma M3
	 * 
	 * @param CharsZwischenSchritte Array das die einzelnen schritte der Verschluesslung als Buchstaben enthaelt
	 */
	public void AusgabeCharsMonitorZwischenSchritteEnigmaM3(char[] CharsZwischenSchritte) {
		
		txtSteckEing.setText(Character.toString(CharsZwischenSchritte[0]));
		txtRotor1Eing.setText(Character.toString(CharsZwischenSchritte[4]));
		txtRotor2Eing.setText(Character.toString(CharsZwischenSchritte[8]));
		txtRotor3Eing.setText(Character.toString(CharsZwischenSchritte[12]));
		txtRotor4Eing.setText("");
		txtUKW.setText(Character.toString(CharsZwischenSchritte[19]));
		txtRotor4Ausg.setText("");
		txtRotor3Ausg.setText(Character.toString(CharsZwischenSchritte[26]));
		txtRotor2Ausg.setText(Character.toString(CharsZwischenSchritte[30]));
		txtRotor1Ausg.setText(Character.toString(CharsZwischenSchritte[34]));
		txtSteckAusg.setText(Character.toString(CharsZwischenSchritte[36]));
	}
}
