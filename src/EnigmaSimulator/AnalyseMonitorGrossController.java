package EnigmaSimulator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Klasse zur Verwaltung des Analyse Monitors Groß
 * 
 * Dieser zeigt die Arbeitsweise des in der der Enigma Klasse
 * implementierten verschluesselungs Algorithmus der eine Nachbau der
 * verschluessleungstechink der echten Enigma darstellt 
 * 
 * Dabei sind die Einzelne walzen und ihre Verschluesselungsatien in jeweils ein Textfeld abgebildet.
 * 
 * Verwendung mit als Debug Tool fuer den Verschluesselungs Algorithmus 
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
public class AnalyseMonitorGrossController {
	
	// ------------------ Oberflaechen Elemente Anfang alles Text Felder ----------------
	@FXML private TextField txtEinagbe;
	
	@FXML private TextField txtSteckbrettEinagbe;
	
	@FXML private TextField txtRotorEGWzu1UebergangHIN;
	
	@FXML private TextField txtRotor1EingangHIN;
	@FXML private TextField txtRotor1InnerHIN;
	@FXML private TextField txtRotor1AusgangHIN;
	@FXML private TextField txtRotor1zu2UebergangHIN;
	
	@FXML private TextField txtRotor2EingangHIN;
	@FXML private TextField txtRotor2InnerHIN;
	@FXML private TextField txtRotor2AusgangHIN;
	@FXML private TextField txtRotor2zu3UebergangHIN;
	
	@FXML private TextField txtRotor3EingangHIN;
	@FXML private TextField txtRotor3InnerHIN;
	@FXML private TextField txtRotor3AusgangHIN;
	@FXML private TextField txtRotor3zu4UebergangHIN;
	
	@FXML private TextField txtRotor4EingangHIN;
	@FXML private TextField txtRotor4InnerHIN;
	@FXML private TextField txtRotor4AusgangHIN;
	@FXML private TextField txtRotor4zuUKW_UebergangHIN;
	
	
	@FXML private TextField txtUKWInner;
	
	
	@FXML private TextField txtRotor1EingangRUECK;
	@FXML private TextField txtRotor1InnerRUECK;
	@FXML private TextField txtRotor1AusgangRUECK;
	@FXML private TextField txtRotor2zu1UebergangRUECK;
	
	@FXML private TextField txtRotor2EingangRUECK;
	@FXML private TextField txtRotor2InnerRUECK;
	@FXML private TextField txtRotor2AusgangRUECK;
	@FXML private TextField txtRotor3zu2UebergangRUECK;
	
	@FXML private TextField txtRotor3EingangRUECK;
	@FXML private TextField txtRotor3InnerRUECK;
	@FXML private TextField txtRotor3AusgangRUECK;
	@FXML private TextField txtRotor4zu3UebergangRUECK;
	
	@FXML private TextField txtRotor4EingangRUECK;
	@FXML private TextField txtRotor4InnerRUECK;
	@FXML private TextField txtRotor4AusgangRUECK;
	@FXML private TextField txtRotorUKWzu4UebergangRUECK;
	
	@FXML private TextField txtRotor1zuEGW_UebergangRUECK;
	
	@FXML private TextField txtSteckbrettAusgabe;
	
	// ------------------ Oberflaechen Elemente Ende ----------------
	
	/**
	 * Zeigt die Zwischenschritte in der Verschluesselung in den 
	 * Textfeldern an bzw. somit wird der Verchluesselungsweg durch die 
	 * Walzen verdeutlicht 
	 * Fuer Enigma M4
	 * 
	 * @param CharsZwischenSchritte Array das die einzelnen schritte der Verschluesslung als Buchstaben enthaelt
	 * @param Eingabe Eingegebener Buchstabe fuer Verschluesselung
	 */
	public void ausgabeCharsMonitorGrossZwischenSchritteEnigmaM4(char[] CharsZwischenSchritte, char Eingabe) {

		txtEinagbe.setText(Character.toString(Eingabe));
		
		txtSteckbrettEinagbe.setText(Character.toString(CharsZwischenSchritte[0]));
		
		txtRotorEGWzu1UebergangHIN.setText(Character.toString(CharsZwischenSchritte[1]));
		
		txtRotor1EingangHIN.setText(Character.toString(CharsZwischenSchritte[2]));
		txtRotor1InnerHIN.setText(Character.toString(CharsZwischenSchritte[3]));
		txtRotor1AusgangHIN.setText(Character.toString(CharsZwischenSchritte[4]));
		txtRotor1zu2UebergangHIN.setText(Character.toString(CharsZwischenSchritte[5]));
		
		txtRotor2EingangHIN.setText(Character.toString(CharsZwischenSchritte[6]));
		txtRotor2InnerHIN.setText(Character.toString(CharsZwischenSchritte[7]));
		txtRotor2AusgangHIN.setText(Character.toString(CharsZwischenSchritte[8]));
		txtRotor2zu3UebergangHIN.setText(Character.toString(CharsZwischenSchritte[9]));
		
		txtRotor3EingangHIN.setText(Character.toString(CharsZwischenSchritte[10]));
		txtRotor3InnerHIN.setText(Character.toString(CharsZwischenSchritte[11]));
		txtRotor3AusgangHIN.setText(Character.toString(CharsZwischenSchritte[12]));
		txtRotor3zu4UebergangHIN.setText(Character.toString(CharsZwischenSchritte[13]));
		
		txtRotor4EingangHIN.setText(Character.toString(CharsZwischenSchritte[14]));
		txtRotor4InnerHIN.setText(Character.toString(CharsZwischenSchritte[15]));
		txtRotor4AusgangHIN.setText(Character.toString(CharsZwischenSchritte[16]));
		txtRotor4zuUKW_UebergangHIN.setText(Character.toString(CharsZwischenSchritte[17]));
		
		
		txtUKWInner.setText(Character.toString(CharsZwischenSchritte[18]));
		
		
		txtRotorUKWzu4UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[19]));
		txtRotor4EingangRUECK.setText(Character.toString(CharsZwischenSchritte[20]));
		txtRotor4InnerRUECK.setText(Character.toString(CharsZwischenSchritte[21]));
		txtRotor4AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[22]));
		
		txtRotor4zu3UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[23]));
		txtRotor3EingangRUECK.setText(Character.toString(CharsZwischenSchritte[24]));
		txtRotor3InnerRUECK.setText(Character.toString(CharsZwischenSchritte[25]));
		txtRotor3AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[26]));
		
		txtRotor3zu2UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[27]));
		txtRotor2EingangRUECK.setText(Character.toString(CharsZwischenSchritte[28]));
		txtRotor2InnerRUECK.setText(Character.toString(CharsZwischenSchritte[29]));
		txtRotor2AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[30]));
		
		txtRotor2zu1UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[31]));
		txtRotor1EingangRUECK.setText(Character.toString(CharsZwischenSchritte[32]));
		txtRotor1InnerRUECK.setText(Character.toString(CharsZwischenSchritte[33]));
		txtRotor1AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[34]));
		
		txtRotor1zuEGW_UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[35]));
		
		txtSteckbrettAusgabe.setText(Character.toString(CharsZwischenSchritte[36]));
	}
	

	/**
	 * Zeigt die Zwischenschritte in der Verschluesselung in den 
	 * Textfeldern an bzw. somit wird der Verchluesselungsweg durch die 
	 * Walzen verdeutlicht 
	 * Fuer Enigma M3
	 * 
	 * @param CharsZwischenSchritte Array das die einzelnen schritte der Verschluesslung als Buchstaben enthaelt
	 * @param Eingabe Eingegebener Buchstabe fuer Verschluesselung
	 */
	public void ausgabeCharsMonitorGrossZwischenSchritteEnigmaM3(char[] CharsZwischenSchritte, char Eingabe) {

		txtEinagbe.setText(Character.toString(Eingabe));
		
		txtSteckbrettEinagbe.setText(Character.toString(CharsZwischenSchritte[0]));
		
		txtRotorEGWzu1UebergangHIN.setText(Character.toString(CharsZwischenSchritte[1]));
		
		txtRotor1EingangHIN.setText(Character.toString(CharsZwischenSchritte[2]));
		txtRotor1InnerHIN.setText(Character.toString(CharsZwischenSchritte[3]));
		txtRotor1AusgangHIN.setText(Character.toString(CharsZwischenSchritte[4]));
		txtRotor1zu2UebergangHIN.setText(Character.toString(CharsZwischenSchritte[5]));
		
		txtRotor2EingangHIN.setText(Character.toString(CharsZwischenSchritte[6]));
		txtRotor2InnerHIN.setText(Character.toString(CharsZwischenSchritte[7]));
		txtRotor2AusgangHIN.setText(Character.toString(CharsZwischenSchritte[8]));
		txtRotor2zu3UebergangHIN.setText(Character.toString(CharsZwischenSchritte[9]));
		
		txtRotor3EingangHIN.setText(Character.toString(CharsZwischenSchritte[10]));
		txtRotor3InnerHIN.setText(Character.toString(CharsZwischenSchritte[11]));
		txtRotor3AusgangHIN.setText(Character.toString(CharsZwischenSchritte[12]));
		txtRotor3zu4UebergangHIN.setText(Character.toString(CharsZwischenSchritte[13]));
		
		//Rotor 4 wird bei Enigma M3 einfach blank gesetzt
		txtRotor4EingangHIN.setText("");
		txtRotor4InnerHIN.setText("");
		txtRotor4AusgangHIN.setText("");
		txtRotor4zuUKW_UebergangHIN.setText("");
		
		
		txtUKWInner.setText(Character.toString(CharsZwischenSchritte[14]));
		
		//Rotor 4 wird bei Enigma M3 einfach blank gesetzt
		txtRotorUKWzu4UebergangRUECK.setText("");
		txtRotor4EingangRUECK.setText("");
		txtRotor4InnerRUECK.setText("");
		txtRotor4AusgangRUECK.setText("");
		
		txtRotor4zu3UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[15]));
		txtRotor3EingangRUECK.setText(Character.toString(CharsZwischenSchritte[16]));
		txtRotor3InnerRUECK.setText(Character.toString(CharsZwischenSchritte[17]));
		txtRotor3AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[18]));
		
		txtRotor3zu2UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[19]));
		txtRotor2EingangRUECK.setText(Character.toString(CharsZwischenSchritte[20]));
		txtRotor2InnerRUECK.setText(Character.toString(CharsZwischenSchritte[21]));
		txtRotor2AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[22]));
		
		txtRotor2zu1UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[23]));
		txtRotor1EingangRUECK.setText(Character.toString(CharsZwischenSchritte[24]));
		txtRotor1InnerRUECK.setText(Character.toString(CharsZwischenSchritte[25]));
		txtRotor1AusgangRUECK.setText(Character.toString(CharsZwischenSchritte[26]));
		
		txtRotor1zuEGW_UebergangRUECK.setText(Character.toString(CharsZwischenSchritte[27]));
		
		txtSteckbrettAusgabe.setText(Character.toString(CharsZwischenSchritte[28]));
	}
}
