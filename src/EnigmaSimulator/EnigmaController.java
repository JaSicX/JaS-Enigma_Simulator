package EnigmaSimulator;

import java.io.File;
import java.security.SecureRandom;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;


/**
 * Klasse zum verwalten der Eingaben der GUI  des Enigma Simulators 
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
public class EnigmaController {
	
	/** Normales Alphabet*/
	private final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//---- Walzen Elemente ----//
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Umkehrwalze*/
	@FXML private ComboBox<String> jcbUKW;
	
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der ersten Walze*/
	@FXML private ComboBox<String> jcbWalze1;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der zweiten Walze*/
	@FXML private ComboBox<String> jcbWalze2;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der dritten Walze*/
	@FXML private ComboBox<String> jcbWalze3;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der vierten Walze (Griechenwalze)*/
	@FXML private ComboBox<String> jcbWalze4;
	
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Walzenstellung der 1.Walze*/
	@FXML private ComboBox<String> jcbWalze1Stellung;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Walzenstellung der 2.Walze*/
	@FXML private ComboBox<String> jcbWalze2Stellung;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Walzenstellung der 3.Walze*/
	@FXML private ComboBox<String> jcbWalze3Stellung;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Walzenstellung der 4.Walze*/
	@FXML private ComboBox<String> jcbWalze4Stellung;
	
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Ringstellung der 1.Walze*/
	@FXML private ComboBox<String> jcbWalze1Ring;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Ringstellung der 2.Walze*/
	@FXML private ComboBox<String> jcbWalze2Ring;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Ringstellung der 3.Walze*/
	@FXML private ComboBox<String> jcbWalze3Ring;
	/** Oberflaechen Element Dropdownmenu fuer Auswahl der Ringstellung der 4.Walze*/
	@FXML private ComboBox<String> jcbWalze4Ring;
	
	//---- Eingabe Elemente ----//
	/** Oberflaechen Element Textarea fuer die Eingabe des zu verschluesselden Textet*/
	@FXML private TextArea txaEingabe;
	/** Oberflaechen Element Textarea fuer die Ausgabe des verschluesselden Textetes*/
	@FXML private TextArea txaAusgabe;
	
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 1 von 13*/
	@FXML private TextField txtSteck1;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 2 von 13*/
	@FXML private TextField txtSteck2;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 3 von 13*/
	@FXML private TextField txtSteck3;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 4 von 13*/
	@FXML private TextField txtSteck4;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 5 von 13*/
	@FXML private TextField txtSteck5;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 6 von 13*/
	@FXML private TextField txtSteck6;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 7 von 13*/
	@FXML private TextField txtSteck7;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 8 von 13*/
	@FXML private TextField txtSteck8;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 9 von 13*/
	@FXML private TextField txtSteck9;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 10 von 13*/
	@FXML private TextField txtSteck10;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 11 von 13*/
	@FXML private TextField txtSteck11;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 12 von 13*/
	@FXML private TextField txtSteck12;
	/** Oberflaechen Element Textfeld fuer die Eingabe der Steckverbindungen 13 von 13*/
	@FXML private TextField txtSteck13;
	
	//---- Button Elemente ----//
	/** Oberflaechen Element Button fuer die Erzeugung von zuffaelligen Einstellungen*/
	@FXML private Button btnRand;
	/** Oberflaechen Element Button fuer das Resten der Einstellungen der Enigma in den anfangs zustand*/
	@FXML private Button btnReset;
	/** Oberflaechen Element Button fuer das Schnelle und kurze Speichern der Einstellungen der Enigma*/
	@FXML private Button btnQuickSave;
	/** Oberflaechen Element Button fuer das Laden der mit dem (Schnellspeichern) btnQuicksave Button gespeicherten Einstellungen der Enigma*/
	@FXML private Button btnQuickLoad;
	/** Oberflaechen Element Button fuer das festsetzten der vorgenommen Einstellungen der Enigma um damit zu Verschluesseln*/
	@FXML private Button btnEinstellungenEnigma;
	/** Oberflaechen Element ToggleButton fuer das Wechseln der Enigma Varianten um zwischen M3 und M4 zu wechseln*/
	@FXML private ToggleButton btnM4SwitchM3;
	
	//---- Menu Elemente ----//
	/** Oberflaechen Element MenuItem fuer das Laden von zuvor in einer Datei gespeicherten Einstellungen*/
	@FXML private MenuItem meiLaden;
	/** Oberflaechen Element MenuItem fuer das Speichern von Einstellungen der Enigma in eine Datei*/
	@FXML private MenuItem meiSpeichern;
	/** Oberflaechen Element MenuItem fuer das Starten des Kleinen Analyse Monitors*/
	@FXML private MenuItem meiAnalyseMonitorKlein;
	/** Oberflaechen Element MenuItem fuer das Starten des Großen Analyse Monitors*/
	@FXML private MenuItem meiAnalyseMonitorGroß;
	/** Oberflaechen Element MenuItem fuer das Aufrufen eines Einstellungsfensters in dem Einstellungen am Simulator
	 * vorgenommen werden koennen*/
	@FXML private MenuItem menEinstellungen;
	/** Oberflaechen Element MenuItem fuer das Aufrufen eines Fensterns in dem die Einstellungen der Enigma 
	 * in einen Schluessel umgewandelt werden der auch wieder eingegeben werden kann um die Einstellungen wieder zu erhalten*/
	@FXML private MenuItem meiKeyManager;
	/** Oberflaechen Element MenuItem fuer das Aufrufen eines Info Fensters das Informationen ueber die Anwendung zeigt*/
	@FXML private MenuItem meiInfoUeberEnigmaSimulator;

	
	/** Wird als speicher fuer die Enigma Einstellungen benutz fuer den Quick Save*/
	private static int QuickSaveEinstellungen[] = new int[13];
	
	/** Wird als speicher fuer das Steckbrett des Quick Save benuzt*/
	private static String QuickSaveEinstellungenSteckbrett[] = new String[13];
	
	/** Wird Verwendet um die Eingabe des Steckbrettes abzuspeichern und so Doppeleingaben in der Oberflaeche zu erkennen*/
	private static String SteckbrettPruefOberflaeche[] = new String[13];
	
	/** Enigma Objekt das zum verschluesseln verwendet wird*/
	private Enigma enigma;
	
	/** Referenz Objekt der Enigma um urspruengliche Einstellungen wieder herzustellen*/
	private Enigma enigmaRef;
	
	/** statische variable ob Enigma M4 aktiv ist*/
	private static boolean istM4Aktiv = true;
	
	/** statisches Array zum ablegen der Einstellungen der Enigma wird zum erzeugen des Enigma Objekts verwendet*/
	private static int[] EnigmaEinstellungen = new int[13];
	
	/**
	 * statische Variable fuer den wert der painting groups
	 * default ist 5 und wird aus der Einstellungscontrollerklasse zugewiesen
	 */
	private static long paintingGroups = EinstellungenController.DEF_PAINTING_GROUPS;
	
	/**
	 * statische Variable für den wert der zufaellig zu erzeugenden steck verbindungen 
	 * standart wert 10 weil damit die höchste sicherheit erreicht wird
	 * wird aus der Einstellungscontrollerklasse zugewiesen
	 */
	private static int randSteckBrettAnzahl = EinstellungenController.DEF_RANDOME_STECK_COUNT;
	
	/**
	 * Legt fest ob der Einagbetext mit abgespeichert werden soll (und AusgabeText)
	 * default false wird aus der Einstellungscontrollerklasse zugewiesen
	 */
	private static boolean einAusgabeTextSpeichern = EinstellungenController.DEF_EIN_AUSGABE_TEXT_SPEICHERN;
	
	/**
	 * Legt fest ob die steuerung der enigma in den Original Enigma Modus umeschalten wird
	 * default false wird aus der Einstellungscontrollerklasse zugewiesen
	 */
	private static boolean originalEnigmaModeOn = EinstellungenController.DEF_ORIGINAL_MODE;

	/**
	 * statische variable um das AnalyseMonitorController objekt abzulegen
	 * um damit auf diese controllerklasse zu zugreifen
	 */
	private static AnalyseMonitorController analyseKleinController;
	
	/**
	 * statische variable um das AnalyseMonitorGroßController objekt abzulegen
	 * um damit auf diese controllerklasse zu zugreifen
	 */
	private static AnalyseMonitorGrossController analyseGroßController;
	
	/** statische Variable ob der AnalyseMonitor geoeffent ist*/
	private static boolean isAnalyseMonitorAktiv = false;
	
	/** statische Variable ob der AnalyseMonitorGroß geoeffent ist*/
	private static boolean isAnalyseMonitorGroßAktiv = false;

	/**
	 * Initialisiert die GUI
	 * ruft dabei die Initialisirungs Methoden für die einzelnen Elemente auf
	 */
	public void initialize() {
	   initializeUKW();
	   initializeWalzen(jcbWalze1, "I");
	   initializeWalzen(jcbWalze2, "II");
	   initializeWalzen(jcbWalze3, "III");
	   initializeWalzen4Grich();
	   initializieWalzenStellung(jcbWalze1Stellung);
	   initializieWalzenStellung(jcbWalze2Stellung);
	   initializieWalzenStellung(jcbWalze3Stellung);
	   initializieWalzenStellung(jcbWalze4Stellung);
	   initializeWalzenRing(jcbWalze1Ring);
	   initializeWalzenRing(jcbWalze2Ring);
	   initializeWalzenRing(jcbWalze3Ring);
	   initializeWalzenRing(jcbWalze4Ring);
	}

	/**
	 * Initialisiert die Umkherwalze
	 */
	private void initializeUKW() {
	    jcbUKW.getItems().removeAll(jcbUKW.getItems());
	    jcbUKW.getItems().addAll("B", "C");
	    jcbUKW.getSelectionModel().select("B");
	}
	
	/**
	 * Initialisiert die Walzenstellung fuer die GUI mit den Buchsateben
	 * 
	 * @param jcb welches WalzenstellungsFeld ist zu Initialisieren (ComboBox)
	 */
	private void initializieWalzenStellung(ComboBox<String> jcb) {
		jcb.getItems().removeAll(jcb.getItems());
		jcb.getItems().addAll("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		jcb.getSelectionModel().select("A");
	}
	
	/**
	 * Initialisiert die Ringstellung fuer die GUI mit den Zahlen
	 * 
	 * @param jcb welches WalzenRingFeld ist zu Initialisieren (ComboBox)
	 */
	private void initializeWalzenRing(ComboBox<String> jcb) {
		jcb.getItems().removeAll(jcb.getItems());
	    jcb.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26");
	    jcb.getSelectionModel().select("1");
	}
	
	/**
	 * Initialisiert die Walzen fuer die GUI mit den Bezeichnungen der Walzen  
	 * 
	 * @param jcb welches WalzenFeld ist zu Initialisieren (ComboBox) 
	 * @param Standart welche Walzenauswahl als voreingestellt ausgewaehlt werden soll
	 */
	private void initializeWalzen(ComboBox<String> jcb, String Standart) {
		jcb.getItems().removeAll(jcb.getItems());
	    jcb.getItems().addAll("I","II","III","IV","V","VI","VII","VIII");
	    jcb.getSelectionModel().select(Standart);
	}
	
	/**
	 * Initialisiert die vierte Walze der Enigma M4 fuer die GUI mit der besonderen auswahl fuer diese
	 * 
	 */
	private void initializeWalzen4Grich() {
		jcbWalze4.getItems().removeAll(jcbWalze4.getItems());
		jcbWalze4.getItems().addAll("beta β", "gamma ɣ");
		jcbWalze4.getSelectionModel().select("beta β");
	}
	
	/**
	 * sezte den wert fuer die painting groups
	 * 
	 * @param paintingGroups Painting Groups anzahl der Buchstaben die gegroupt werden sollen
	 */
	public static void setPaintingGroups(long paintingGroups) {
		EnigmaController.paintingGroups = paintingGroups;
	}
	
	/**
	 * gibt die Anzahl der Aktuellen PaintingGroups zurueck
	 * 
	 * @return Wert der derzeit gesetzt und gespeichert ist.
	 */
	public static long getPaintingGroups() {
		return paintingGroups;
	}
	
	/**
	 * Gibt die derzeit gesetzte Anzahl an Zufaelligen zu erzeugenden Steckverbindugen zurueck
	 * 
	 * @return Wert der derzeit gesetzt und gespeichert ist.
	 */
	public static int getRandSteckBrettAnzahl() {
		return randSteckBrettAnzahl;
	}

	/**
	 * Setzt den Wert fuer die zufaellig zu erzeuegenden Steckverbindungen
	 * 
	 * @param randSteckBrettAnzahl Wert der gesetzt werden soll.
	 */
	public static void setRandSteckBrettAnzahl(int randSteckBrettAnzahl) {
		EnigmaController.randSteckBrettAnzahl = randSteckBrettAnzahl;
	}
	
	/**
	 * Setzt den zu verwenden Controller fuer den Analyse Monitor
	 * 
	 * @param controller Zusetzender controller
	 */
	public static void setControllerAnalyse(AnalyseMonitorController controller) {
		EnigmaController.analyseKleinController = controller;
	}
	
	/**
	 * Setzt den zu verwenden Controller fuer den Analyse Monitor Groß
	 * 
	 * @param controllerGroß Zusetzender controller
	 */
	public static void setControllerGroß(AnalyseMonitorGrossController controllerGroß) {
		EnigmaController.analyseGroßController = controllerGroß;
	}
	
	/**
	 * Gibt den den Wert zurueck ob die Speicherung des Eingabetextes beim
	 * Speichern mit erfolgen soll.
	 * 
	 * @return Wert ob Eingabetext mit gespeichert wird.
	 */
	public static boolean isEinAusgabeTextSpeichern() {
		return einAusgabeTextSpeichern;
	}

	/**
	 * Setzt den Wert ob der Eingabetext beim Speichern mit gespeichert werden soll
	 * 
	 * @param eingabeTextSpeichern Wert ob Eingabetext mit gespeichert wird 
	 */
	public static void setEinAusgabeTextSpeichern(boolean eingabeTextSpeichern) {
		EnigmaController.einAusgabeTextSpeichern = eingabeTextSpeichern;
	}
	
	/**
	 * Setzt den wert ob der Analyse Monitor aktiv (Geoeffent wurde)
	 * 
	 * @param isAnalyseMonitorAktiv zu setzender Wert.
	 */
	public static void setAnalyseMonitorAktiv(boolean isAnalyseMonitorAktiv) {
		EnigmaController.isAnalyseMonitorAktiv = isAnalyseMonitorAktiv;
	}
	
	/**
	 * Setzt den wert ob der Analyse Monitor Groß aktiv (Geoeffent wurde)
	 * 
	 * @param isAnalyseMonitorGroßAktiv zu setzender Wert.
	 */
	public static void setAnalyseMonitorGroßAktiv(boolean isAnalyseMonitorGroßAktiv) {
		EnigmaController.isAnalyseMonitorGroßAktiv = isAnalyseMonitorGroßAktiv;
	}

	/**
	 * @return Den wert ob der Original Enigma Modus aktiv ist.
	 */
	public static boolean isOriginalEnigmaModeOnOff() {
		return originalEnigmaModeOn;
	}

	/**
	 * setzt den wert ob Original Enigma Modus aktiv gesetzt wurde
	 * 
	 * @param originalEnigmaModeOnOff zu setzender Wert.
	 */
	public static void setOriginalEnigmaModeOnOff(boolean originalEnigmaModeOnOff) {
		EnigmaController.originalEnigmaModeOn = originalEnigmaModeOnOff;
	}
	
	/**
	 * @return isAnalyseMonitorAktiv
	 */
	public static boolean isAnalyseMonitorAktiv() {
		return isAnalyseMonitorAktiv;
	}

	/**
	 * @return isAnalyseMonitorGroßAktiv
	 */
	public static boolean isAnalyseMonitorGroßAktiv() {
		return isAnalyseMonitorGroßAktiv;
	}
	
	/**
	 * setzt den Einstellungsbutton per booleschen wert auf inaktiv
	 * und gleichzeitig die editierbarkeit des txa Eingabefeld auf dessen Gegenwert
	 * 
	 * @param EinstAndTxaEingabeOnOff zu setzender Wert.
	 */
	public void setBtnEinstellungenAndTxaEingabeDisableAktive(boolean EinstAndTxaEingabeOnOff) {
		btnEinstellungenEnigma.setDisable(EinstAndTxaEingabeOnOff);
		
		/*resetet und deaktiviert/aktiviert nur wenn sich der modus auch aeSndert*/
		if(EinstAndTxaEingabeOnOff != originalEnigmaModeOn) {
			txaEingabe.setDisable(!EinstAndTxaEingabeOnOff);		//wenn der button wieder aktiviert muss auch die einagbe wieder deaktivirt werden
			txaEingabe.setEditable(!EinstAndTxaEingabeOnOff);		//Komplementer zum button wenn dieser inaktiv muss die Eingabe aktiv sein
			handleBbtnReset();
		}
	}

	/**
	 * Handelt die einagbe des eingabe feldes und ruft je nach gesetzten werten die richtige verschluesselungs methode auf
	 */
	@FXML
	private void handleEingabeTXA() {
		
		if(isAnalyseMonitorAktiv) {
			analyseMonitorEingabeHandle();
		} else if(isAnalyseMonitorGroßAktiv) {
			analyseMonitorGrossEingabeHandle();
		} else if(originalEnigmaModeOn) {
			handelEingabetxa1OriginalEnigmaMode();
		}
		else {
			eingabeNormalHandle();
		}
	}

	/**
	 * Wird bei Eingabe in das Eingabe Feld aktiv liest den Eingegeben Text aus und verschluesselt diesen indem dieser der durch den 
	 * Aufruf der jeweiligen Verschluesslungs Methode mit dem Enigma Objekt verschluesselt wird und dann im Ausgabe Feld ausgegen wird.
	 */
	private void eingabeNormalHandle() {
			
		try {
			String Eingabe = txaEingabe.getText();
			
			enigmaRef = new Enigma(
					EnigmaEinstellungen[0], EnigmaEinstellungen[1],	EnigmaEinstellungen[2],
					EnigmaEinstellungen[3], EnigmaEinstellungen[4],	EnigmaEinstellungen[5],
					EnigmaEinstellungen[6], EnigmaEinstellungen[7],	EnigmaEinstellungen[8],
					EnigmaEinstellungen[9], EnigmaEinstellungen[10], EnigmaEinstellungen[11],
					EnigmaEinstellungen[12]
			);
			
			Eingabe = Eingabe.replaceAll("[^a-zA-Z]", "");
			Eingabe = Eingabe.toUpperCase();
			Eingabe = Eingabe.replaceAll(" ", "");
			
			String verssAusgabe ="";
			long paintingGroupsCounter = 0;
			for(int i = 0; i < Eingabe.length();i++) {

				if(paintingGroupsCounter == paintingGroups) {
					if(istM4Aktiv) {
						verssAusgabe =verssAusgabe + "  "+enigma.VerschluesselungEnigmaM4(Eingabe.charAt(i));
					}
					else {
						verssAusgabe =verssAusgabe + "  "+enigma.VerschluesselungEnigmaM3(Eingabe.charAt(i));
					}
					paintingGroupsCounter=0;
				}
				else {
					if(istM4Aktiv) {
						verssAusgabe = verssAusgabe +enigma.VerschluesselungEnigmaM4(Eingabe.charAt(i));
					}
					else {
						verssAusgabe = verssAusgabe +enigma.VerschluesselungEnigmaM3(Eingabe.charAt(i));
					}
				}
				paintingGroupsCounter++;
			}
			
			txaAusgabe.setText(verssAusgabe);
			updateOberFlaeche();
			
			enigma = enigmaRef;
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Es Ist ein Unerwarteter Fehler Aufgetreten Bitte erneut versuchen");
		}
	}
	
	/**
	 * Wird als Verschluesslungs Methode nur bei der verwendet wenn der Analyse monitor aktiv ist.
	 */
	private void analyseMonitorEingabeHandle() {
		
		try {
			String Eingabe = txaEingabe.getText();
			
			enigmaRef = new Enigma(
					EnigmaEinstellungen[0], EnigmaEinstellungen[1],	EnigmaEinstellungen[2],
					EnigmaEinstellungen[3], EnigmaEinstellungen[4],	EnigmaEinstellungen[5],
					EnigmaEinstellungen[6], EnigmaEinstellungen[7],	EnigmaEinstellungen[8],
					EnigmaEinstellungen[9], EnigmaEinstellungen[10], EnigmaEinstellungen[11],
					EnigmaEinstellungen[12]
			);
			
			Eingabe = Eingabe.replaceAll("[^a-zA-Z]", "");
			Eingabe = Eingabe.toUpperCase();
			Eingabe = Eingabe.replaceAll(" ", "");
			
			String verssAusgabe ="";
			long paintingGroupsCounter = 0;
			for(int i = 0; i < Eingabe.length();i++) {

				if(paintingGroupsCounter == paintingGroups) {
					if(istM4Aktiv) {
						char [] temp1 = enigma.VerschluesselungEnigmaM4Analyse(Eingabe.charAt(i));
						verssAusgabe =verssAusgabe + "  " + temp1[36];
						analyseKleinController.AusgabeCharsMonitorZwischenSchritteEnigmaM4(temp1);
					}
					else {
						char [] temp1 = enigma.VerschluesselungEnigmaM3Analyse(Eingabe.charAt(i));
						verssAusgabe =verssAusgabe + "  " + temp1[28];
						analyseKleinController.AusgabeCharsMonitorZwischenSchritteEnigmaM3(temp1);
					}
					paintingGroupsCounter=0;
				}
				else {
					if(istM4Aktiv) {
						char [] temp1 = enigma.VerschluesselungEnigmaM4Analyse(Eingabe.charAt(i));
						verssAusgabe = verssAusgabe + temp1[36];
						analyseKleinController.AusgabeCharsMonitorZwischenSchritteEnigmaM4(temp1);
					}
					else {
						char [] temp1 = enigma.VerschluesselungEnigmaM3Analyse(Eingabe.charAt(i));
						verssAusgabe =verssAusgabe + temp1[28];
						analyseKleinController.AusgabeCharsMonitorZwischenSchritteEnigmaM3(temp1);
					}
				}
				paintingGroupsCounter++;
			}
			
			txaAusgabe.setText(verssAusgabe);
			updateOberFlaeche();
			
			enigma = enigmaRef;
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Es Ist ein Unerwarteter Fehler Aufgetreten Bitte erneut versuchen");
		}
	}
	
	/**
	 * Wird als Verschluesslungs Methode nur bei der verwendet wenn der Analyse monitor Gross aktiv ist
	 */
	private void analyseMonitorGrossEingabeHandle() {
		
		try {
			String Eingabe = txaEingabe.getText();
			
			enigmaRef = new Enigma(
					EnigmaEinstellungen[0], EnigmaEinstellungen[1],	EnigmaEinstellungen[2],
					EnigmaEinstellungen[3], EnigmaEinstellungen[4],	EnigmaEinstellungen[5],
					EnigmaEinstellungen[6], EnigmaEinstellungen[7],	EnigmaEinstellungen[8],
					EnigmaEinstellungen[9], EnigmaEinstellungen[10], EnigmaEinstellungen[11],
					EnigmaEinstellungen[12]
			);
			
			Eingabe = Eingabe.replaceAll("[^a-zA-Z]", "");
			Eingabe = Eingabe.toUpperCase();
			Eingabe = Eingabe.replaceAll(" ", "");
			
			String verssAusgabe ="";
			long paintingGroupsCounter = 0;
			for(int i = 0; i < Eingabe.length();i++) {
				
				char eingabeChar = Eingabe.charAt(i);
				
				if(paintingGroupsCounter == paintingGroups) {
					if(istM4Aktiv) {
						char [] temp1 = enigma.VerschluesselungEnigmaM4Analyse(eingabeChar);
						verssAusgabe =verssAusgabe + "  " + temp1[36];
						analyseGroßController.ausgabeCharsMonitorGrossZwischenSchritteEnigmaM4(temp1, eingabeChar);
					}
					else {
						char [] temp1 = enigma.VerschluesselungEnigmaM3Analyse(eingabeChar);
						verssAusgabe =verssAusgabe + "  " + temp1[28];
						analyseGroßController.ausgabeCharsMonitorGrossZwischenSchritteEnigmaM3(temp1, eingabeChar);
					}
					paintingGroupsCounter=0;
				}
				else {
					if(istM4Aktiv) {
						char [] temp1 = enigma.VerschluesselungEnigmaM4Analyse(eingabeChar);
						verssAusgabe = verssAusgabe + temp1[36];
						analyseGroßController.ausgabeCharsMonitorGrossZwischenSchritteEnigmaM4(temp1, eingabeChar);
					}
					else {
						char [] temp1 = enigma.VerschluesselungEnigmaM3Analyse(eingabeChar);
						verssAusgabe =verssAusgabe + temp1[28];
						analyseGroßController.ausgabeCharsMonitorGrossZwischenSchritteEnigmaM3(temp1, eingabeChar);
					}
				}
				paintingGroupsCounter++;
			}
			
			txaAusgabe.setText(verssAusgabe);
			updateOberFlaeche();
			
			enigma = enigmaRef;
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Es Ist ein Unerwarteter Fehler Aufgetreten Bitte erneut versuchen");
		}
	}
	
	/**
	 * Verschluesselungs Methode wenn der Originalmode Aktiv ist 
	 * hierbei wird die Eingabe sofort wieder geloescht und aenderungen an diese sind nicht mehr moeglich
	 * Die Einstellungen werden bei Jedem Verschluesselungsvorgang neu eingelesen.
	 */
	private void handelEingabetxa1OriginalEnigmaMode() {
		
		try {
			handleEinstellEnigma();
			
			String Eingabe = txaEingabe.getText();
			
			txaEingabe.clear();
			
			Eingabe = Eingabe.replaceAll("[^a-zA-Z]", "");
			Eingabe = Eingabe.toUpperCase();
			Eingabe = Eingabe.replaceAll(" ", "");
			
			String ausgabeText = txaAusgabe.getText();
			long paintingGroupsCounter = 0;
			
			boolean paintingGroupsNOTZero = (paintingGroups == 0) ? false : true;
			
			if (paintingGroupsNOTZero) {
				paintingGroupsCounter = (ausgabeText.length() % (paintingGroups + 2));
			}
			
			for(int i = 0; i < Eingabe.length();i++) {
				
				if(paintingGroupsCounter == paintingGroups && paintingGroupsNOTZero) {
					if(istM4Aktiv) {
						ausgabeText =ausgabeText + "  "+enigma.VerschluesselungEnigmaM4(Eingabe.charAt(i));
					}
					else {
						ausgabeText =ausgabeText + "  "+enigma.VerschluesselungEnigmaM3(Eingabe.charAt(i));
					}
					paintingGroupsCounter=0;
				}
				else {
					if(istM4Aktiv) {
						ausgabeText = ausgabeText +enigma.VerschluesselungEnigmaM4(Eingabe.charAt(i));
					}
					else {
						ausgabeText = ausgabeText +enigma.VerschluesselungEnigmaM3(Eingabe.charAt(i));
					}					
				}
				paintingGroupsCounter++;
			}
			txaAusgabe.setText(ausgabeText);
			
			updateOberFlaeche();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Es Ist ein Unerwarteter Fehler Aufgetreten Bitte erneut versuchen");
		}
	}
	
	
	/**
	 * Gibt den Buchstaben index aus dem Alphabet wieder fuer die Walzen Stellung 
	 * 
	 * @param jcb Fuer welches Walzenstellungs Feld
	 * @return index des Buchstabens im Alphabet 
	 */
	private int getBuchstabenNr(ComboBox<String> jcb) {
		String Stellung = String.valueOf(jcb.getValue());
	
		return ABC.indexOf(Stellung.charAt(0));
	}
	
	/**
	 * Updated die stellung aller walzen der Enigma 
	 */
	private void updateOberFlaeche() {
		
		jcbWalze1Stellung.getSelectionModel().select(enigma.getRotor1().getRotorIntStellung());
		jcbWalze2Stellung.getSelectionModel().select(enigma.getRotor2().getRotorIntStellung());
		jcbWalze3Stellung.getSelectionModel().select(enigma.getRotor3().getRotorIntStellung());
		jcbWalze4Stellung.getSelectionModel().select(enigma.getRotor4().getRotorIntStellung());
	}
	
	/**
	 * Handelt die Eingabe des Zufaellige Einstellungs Buttons und
	 * Erzeugt fuer die Enigma zufaellige einstellungen
	 * und setzt diese auch in die oberflaeche sowie uebernimmt sie als aktuell 
	 * fuer die Verschluesselung
	 */
	@FXML
	private void handleRandomeEinstellungen() {
		
		int einstellungen[] = new int[13];
		
		SecureRandom SecRand = new SecureRandom();
		
		for(int i = 1; i < 12; i++) {
			if( i%3 == 0 ) {
				//zuffaellige Rotoren auswaehlen 1-3
				einstellungen[i] = SecRand.nextInt(8);
				
			}
			else {
				//zufaellige Walzenstellung erzeugen
				einstellungen[i] = SecRand.nextInt(26);
			}
		}
		//zufaelliger 4 Rotor auswaehlen
		einstellungen[0] = SecRand.nextInt(2);
		//zufaellige UKW auswaehlen
		einstellungen[12] = SecRand.nextInt(2);
		
		String[] steckParre = new String[13];
		Arrays.fill(steckParre, "");
		
		//zufaellige Steckverbindungen generieren
		makeRandSteck(steckParre, 0, randSteckBrettAnzahl, null);
		
		if(istM4Aktiv) {
			jcbWalze4.getSelectionModel().select(einstellungen[0]);
			jcbWalze4Stellung.getSelectionModel().select(einstellungen[1]);
			jcbWalze4Ring.getSelectionModel().select(einstellungen[2]);
		}
		else {
			jcbWalze4.getSelectionModel().select(0);
			jcbWalze4Stellung.getSelectionModel().select(0);
			jcbWalze4Ring.getSelectionModel().select(0);
		}
		jcbWalze1.getSelectionModel().select(einstellungen[9]);
		jcbWalze2.getSelectionModel().select(einstellungen[6]);
		jcbWalze3.getSelectionModel().select(einstellungen[3]);
		
		jcbWalze1Stellung.getSelectionModel().select(einstellungen[10]);
		jcbWalze2Stellung.getSelectionModel().select(einstellungen[7]);
		jcbWalze3Stellung.getSelectionModel().select(einstellungen[4]);
		
		jcbWalze1Ring.getSelectionModel().select(einstellungen[11]);
		jcbWalze2Ring.getSelectionModel().select(einstellungen[8]);
		jcbWalze3Ring.getSelectionModel().select(einstellungen[5]);
		
		jcbUKW.getSelectionModel().select(einstellungen[12]);
		
		SteckbrettPruefOberflaeche = steckParre;
		
		txtSteck1.setText(steckParre[0]);
		txtSteck2.setText(steckParre[1]);
		txtSteck3.setText(steckParre[2]);
		txtSteck4.setText(steckParre[3]);
		txtSteck5.setText(steckParre[4]);
		txtSteck6.setText(steckParre[5]);
		txtSteck7.setText(steckParre[6]);
		txtSteck8.setText(steckParre[7]);
		txtSteck9.setText(steckParre[8]);
		txtSteck10.setText(steckParre[9]);
		txtSteck11.setText(steckParre[10]);
		txtSteck12.setText(steckParre[11]);
		txtSteck13.setText(steckParre[12]);
		
		txaAusgabe.clear();
		txaEingabe.clear();
		
		handleEinstellEnigma();
	}	
	
	/**
	 * Erzeugt ein String Array mit zufaelligen Buchstaben Kombinationen aus zweier Gruppen pro Array Feld die sich in einem anderem
	 * Feld nicht wiederholen somit ist jeder wert im array einzigartig.
	 * 
	 * Wird fuer die Erstellung von zufaelligen Buchstaben Kombinationen fuer das Steckbrett verwendet
	 * 
	 * ! Achtung beim aufruf ist sich strikt an die grenzen/Vorgaben fuer die parameter zu halten
	 * 
	 * Diese Funktion ruft sich rekursiv bis zur kompletten array Findung selbst wieder auf. 
	 * 
	 * @param steckParre Die Buchstabenkombinationen die gefunden wurden. Am besten ein leeres Array übergeben
	 * @param zaehler Zaehlt die anzahl der erfolgreichen konstruktionen eines Buchstabenpaares es sollte eine 0 übergeben werden
	 * @param grenze Anzahl der Buchsatbenparre die gefunden werden soll Darf 13 nicht ueberschreiten
	 * @param schonGefunden	Ein buchstabe der schon gefunden wurde wird hier abgelegt beim aufruf stets ein "null" wert uebergeben
	 * @return Array mit zuffaelligen Steckverbindungen
	 */
	private String[] makeRandSteck(String[] steckParre, int zaehler ,int grenze, String schonGefunden) {
		
		if(zaehler > 13 || steckParre.length > 13 || grenze > 13) {
			String[] empty = new String[13];	
			return empty;
		}
		
		
		SecureRandom SecRand = new SecureRandom();
		
		String curr = schonGefunden;
		
		if(schonGefunden == null) {
		
			curr = "" + (ABC.charAt(SecRand.nextInt(26)));
			if(Arrays.toString(steckParre).contains(curr)) {
				return makeRandSteck(steckParre, zaehler, grenze, null);
			}
			schonGefunden = curr;
		}
		
		String curr2 = "" + (ABC.charAt(SecRand.nextInt(26)));
		if(Arrays.toString(steckParre).contains(curr2)) {
			return makeRandSteck(steckParre, zaehler, grenze, schonGefunden);
		}
		
		if((curr.equals(curr2))) {
			return makeRandSteck(steckParre, zaehler, grenze, null);
		}
		else {
			steckParre[zaehler] = curr + curr2;
			zaehler++;
			
			if(zaehler == steckParre.length || grenze == zaehler) {
				return steckParre;
			}
			
			return makeRandSteck(steckParre, zaehler, grenze, null);
		}
	}
	
	/**
	 * handelt die Eingabe des Einstellung Setzen Buttons
	 * Setzt die aktuellen Einstellungen der Enigma dafuer wird ein Array angelegt was als Ursprungsrefernz 
	 * fuer die gesetzten Einstellungen fungiert im array werden alle Einstellung bis auf das Steckbrett abgelegt 
	 * mit dem Array EnigmaEinstellungen wird dann das Enigma Objekt erzeugt
	 * 
	 */
	@FXML
	public void handleEinstellEnigma() {
		
		if(istM4Aktiv) {
			EnigmaEinstellungen[0]=jcbWalze4.getSelectionModel().getSelectedIndex()+10;
			EnigmaEinstellungen[1]=getBuchstabenNr(jcbWalze4Stellung);
			EnigmaEinstellungen[2]=jcbWalze4Ring.getSelectionModel().getSelectedIndex();
			
			EnigmaEinstellungen[12]=jcbUKW.getSelectionModel().getSelectedIndex()+10;
		}
		else {
			EnigmaEinstellungen[0]=0;
			EnigmaEinstellungen[1]=0;
			EnigmaEinstellungen[2]=0;
			
			EnigmaEinstellungen[12]=jcbUKW.getSelectionModel().getSelectedIndex()+1;
		}
				
		EnigmaEinstellungen[3]=jcbWalze3.getSelectionModel().getSelectedIndex()+1;
		EnigmaEinstellungen[4]=getBuchstabenNr(jcbWalze3Stellung);
		EnigmaEinstellungen[5]=jcbWalze3Ring.getSelectionModel().getSelectedIndex();
		
		EnigmaEinstellungen[6]=jcbWalze2.getSelectionModel().getSelectedIndex()+1;
		EnigmaEinstellungen[7]=getBuchstabenNr(jcbWalze2Stellung);
		EnigmaEinstellungen[8]=jcbWalze2Ring.getSelectionModel().getSelectedIndex();
		
		
		EnigmaEinstellungen[9]=jcbWalze1.getSelectionModel().getSelectedIndex()+1;
		EnigmaEinstellungen[10]=getBuchstabenNr(jcbWalze1Stellung);
		EnigmaEinstellungen[11]=jcbWalze1Ring.getSelectionModel().getSelectedIndex();
		
		
		//Enigma element erzeugen
		enigma = new Enigma(
				EnigmaEinstellungen[0], EnigmaEinstellungen[1],	EnigmaEinstellungen[2],
				EnigmaEinstellungen[3], EnigmaEinstellungen[4],	EnigmaEinstellungen[5],
				EnigmaEinstellungen[6], EnigmaEinstellungen[7],	EnigmaEinstellungen[8],
				EnigmaEinstellungen[9], EnigmaEinstellungen[10], EnigmaEinstellungen[11],
				EnigmaEinstellungen[12]
		);
		
		Steckbrett.resetSteckbrett();
		
		//steckbrett elemente erzeugen
		Steckbrett steck1 = new Steckbrett(txtSteck1.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck2 = new Steckbrett(txtSteck2.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck3 = new Steckbrett(txtSteck3.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck4 = new Steckbrett(txtSteck4.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck5 = new Steckbrett(txtSteck5.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck6 = new Steckbrett(txtSteck6.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck7 = new Steckbrett(txtSteck7.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck8 = new Steckbrett(txtSteck8.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck9 = new Steckbrett(txtSteck9.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck10 = new Steckbrett(txtSteck10.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck11 = new Steckbrett(txtSteck11.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck12 = new Steckbrett(txtSteck12.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		Steckbrett steck13 = new Steckbrett(txtSteck13.getText().toUpperCase().replaceAll("[^A-Za-z]", ""));
		
		txaEingabe.setDisable(false);
		txaEingabe.setEditable(true);
	}
	
	/**
	 * Wird verwendet um von von externer seite Einstellungen an die Enigma zu uebergeben und diese fuer die 
	 * verschluesselung zu setzten.
	 * 
	 * Wichtig die Einstellungen die uebergen werden sind vor dem Aufruf dieser Funktion auf plausibilitaet zu pruefen !
	 * 
	 * @param EnigmaEinstellungen Array mit einstellunge fuer die Enigma
	 * @param EnigmaSteck Array mit den Steckverbindungen die gesetzt werden sollen
	 * @param isEnigmaM4aktiv wert ob die Enigma M4 aktiv ist oder die M3
	 */
	public void setEinstellEnigmaExtern(int[] EnigmaEinstellungen, String[] EnigmaSteck, boolean isEnigmaM4aktiv) {
		
		handleBbtnReset();
		
		istM4Aktiv = isEnigmaM4aktiv;
		
		if(istM4Aktiv) {
			
			btnM4SwitchM3.setText("Enigma M4 Ausgewählt");
			btnM4SwitchM3.setSelected(false);
			jcbWalze4.setDisable(false);
			jcbWalze4Ring.setDisable(false);
			jcbWalze4Stellung.setDisable(false);
		}
		else {
			btnM4SwitchM3.setText("Enigma M3 Ausgewählt");
			btnM4SwitchM3.setSelected(true);
			jcbWalze4.setDisable(true);
			jcbWalze4Ring.setDisable(true);
			jcbWalze4Stellung.setDisable(true);
		}
		
		if(istM4Aktiv) {
			jcbWalze4.getSelectionModel().select(EnigmaEinstellungen[0]-10);
			jcbWalze4Stellung.getSelectionModel().select(EnigmaEinstellungen[1]);
			jcbWalze4Ring.getSelectionModel().select(EnigmaEinstellungen[2]);
			
			jcbUKW.getSelectionModel().select(EnigmaEinstellungen[12]-10);
		}
		else {
			jcbWalze4.getSelectionModel().select(0);
			jcbWalze4Stellung.getSelectionModel().select(0);
			jcbWalze4Ring.getSelectionModel().select(0);
			
			jcbUKW.getSelectionModel().select(EnigmaEinstellungen[12]-1);
		}
		
		jcbWalze3.getSelectionModel().select(EnigmaEinstellungen[3]-1);
		jcbWalze3Stellung.getSelectionModel().select(EnigmaEinstellungen[4]);
		jcbWalze3Ring.getSelectionModel().select(EnigmaEinstellungen[5]);
		
		jcbWalze2.getSelectionModel().select(EnigmaEinstellungen[6]-1);
		jcbWalze2Stellung.getSelectionModel().select(EnigmaEinstellungen[7]);
		jcbWalze2Ring.getSelectionModel().select(EnigmaEinstellungen[8]);
		
		jcbWalze1.getSelectionModel().select(EnigmaEinstellungen[9]-1);
		jcbWalze1Stellung.getSelectionModel().select(EnigmaEinstellungen[10]);
		jcbWalze1Ring.getSelectionModel().select(EnigmaEinstellungen[11]);
		
		txtSteck1.setText(EnigmaSteck[0]);
		txtSteck2.setText(EnigmaSteck[1]);
		txtSteck3.setText(EnigmaSteck[2]);
		txtSteck4.setText(EnigmaSteck[3]);
		txtSteck5.setText(EnigmaSteck[4]);
		txtSteck6.setText(EnigmaSteck[5]);
		txtSteck7.setText(EnigmaSteck[6]);
		txtSteck8.setText(EnigmaSteck[7]);
		txtSteck9.setText(EnigmaSteck[8]);
		txtSteck10.setText(EnigmaSteck[9]);
		txtSteck11.setText(EnigmaSteck[10]);
		txtSteck12.setText(EnigmaSteck[11]);
		txtSteck13.setText(EnigmaSteck[12]);
		
		SteckbrettPruefOberflaeche = EnigmaSteck;
		
		handleEinstellEnigma();
	}
	
	/**
	 * Gibt die derzeitigen Einstellungen die in der Uberflaeche gesetzt sind
	 * in einem Object array zurueck
	 * 
	 * der Inhalt des Arrays setzt sich folgt zusammen
	 * [int array EinstellungenEnigma, String array Steckbrettverbindungen, boolean Enigma M4 aktiv] 
	 * 
	 * @return Array mit allen Einstellungen der Enigma 
	 */
	public Object[] getEnigmaExtern() {
		
		int[] enigmaEinst = new int[13]; 
		String[] enigmaSteck = new String[13];
		
		enigmaEinst[0]=jcbWalze4.getSelectionModel().getSelectedIndex()+10;
		enigmaEinst[1]=getBuchstabenNr(jcbWalze4Stellung);
		enigmaEinst[2]=jcbWalze4Ring.getSelectionModel().getSelectedIndex();
		
		if(istM4Aktiv) {
			enigmaEinst[12]=jcbUKW.getSelectionModel().getSelectedIndex()+10;			
		}
		else {
			enigmaEinst[12]=jcbUKW.getSelectionModel().getSelectedIndex()+1;
		}
				
		enigmaEinst[3]=jcbWalze3.getSelectionModel().getSelectedIndex()+1;
		enigmaEinst[4]=getBuchstabenNr(jcbWalze3Stellung);
		enigmaEinst[5]=jcbWalze3Ring.getSelectionModel().getSelectedIndex();
		
		enigmaEinst[6]=jcbWalze2.getSelectionModel().getSelectedIndex()+1;
		enigmaEinst[7]=getBuchstabenNr(jcbWalze2Stellung);
		enigmaEinst[8]=jcbWalze2Ring.getSelectionModel().getSelectedIndex();
		
		enigmaEinst[9]=jcbWalze1.getSelectionModel().getSelectedIndex()+1;
		enigmaEinst[10]=getBuchstabenNr(jcbWalze1Stellung);
		enigmaEinst[11]=jcbWalze1Ring.getSelectionModel().getSelectedIndex();
		
		
		enigmaSteck[0] = txtSteck1.getText();
		enigmaSteck[1] = txtSteck2.getText();
		enigmaSteck[2] = txtSteck3.getText();
		enigmaSteck[3] = txtSteck4.getText();
		enigmaSteck[4] = txtSteck5.getText();
		enigmaSteck[5] = txtSteck6.getText();
		enigmaSteck[6] = txtSteck7.getText();
		enigmaSteck[7] = txtSteck8.getText();
		enigmaSteck[8] = txtSteck9.getText();
		enigmaSteck[9] = txtSteck10.getText();
		enigmaSteck[10] = txtSteck11.getText();
		enigmaSteck[11] = txtSteck12.getText();
		enigmaSteck[12] = txtSteck13.getText();
		
		return new Object[] { enigmaEinst, enigmaSteck, istM4Aktiv};
	}
	
	/**
	 * handelt die Eingabe des Umschalters
	 * Schaltet die Oberflaeche sowie die Verschluesselungs Methode zwischen Enigma M4 und M3 um
	 */
	@FXML
	public void handleBtnSwitch() {
		
		if(btnM4SwitchM3.isSelected()) {
			btnM4SwitchM3.setText("Enigma M3 Ausgewählt");
			istM4Aktiv=false;
			
			jcbWalze4.setDisable(true);
			jcbWalze4Ring.setDisable(true);
			jcbWalze4Stellung.setDisable(true);
		}
		else {
			istM4Aktiv=true;
			btnM4SwitchM3.setText("Enigma M4 Ausgewählt");
			jcbWalze4.setDisable(false);
			jcbWalze4Ring.setDisable(false);
			jcbWalze4Stellung.setDisable(false);
		}    
	}

	/**
	 * handelt die Eingabe des Zuruecksetzen Buttons btnReset
	 * Setzt die Enigma in die Ursprungs Einstellung (initalisirung zustand) zurueck
	 */
	@FXML
	public void handleBbtnReset() {
		jcbWalze1.getSelectionModel().select("I");
		jcbWalze2.getSelectionModel().select("II");
		jcbWalze3.getSelectionModel().select("III");
		jcbWalze4.getSelectionModel().select("beta β");
		
		jcbWalze1Stellung.getSelectionModel().select("A");
		jcbWalze2Stellung.getSelectionModel().select("A");
		jcbWalze3Stellung.getSelectionModel().select("A");
		jcbWalze4Stellung.getSelectionModel().select("A");
		
		jcbWalze1Ring.getSelectionModel().select(0);
		jcbWalze2Ring.getSelectionModel().select(0);
		jcbWalze3Ring.getSelectionModel().select(0);
		jcbWalze4Ring.getSelectionModel().select(0);
		
		jcbUKW.getSelectionModel().select("B");
		
		txtSteck1.clear();
		txtSteck2.clear();
		txtSteck3.clear();
		txtSteck4.clear();
		txtSteck5.clear();
		txtSteck6.clear();
		txtSteck7.clear();
		txtSteck8.clear();
		txtSteck9.clear();
		txtSteck10.clear();
		txtSteck11.clear();
		txtSteck12.clear();
		txtSteck13.clear();
		
		SteckbrettPruefOberflaeche = new String[13];
		Steckbrett.resetSteckbrett();
		
		txaAusgabe.clear();
		txaEingabe.clear();
		resestSteckbrettColor();
		
		handleEinstellEnigma();
	}
	
	/**
	 * handelt die Eingabe des Schnellspeichern Buttons (btnQuickSave)
	 * Speichert die derzeitigen Einstellung der Enigma in 2 Arrays ab
	 * 
	 * 
	 * Array QuickSaveEinstellungen
	 * Walze 0-3
	 * Walzenstellung 4-7
	 * WalzenRing 8-11
	 */
	@FXML
	private void handleQuickSave() {
		
		QuickSaveEinstellungen[0] = jcbWalze1.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[1] = jcbWalze2.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[2] = jcbWalze3.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[3] = jcbWalze4.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[4] = jcbWalze1Stellung.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[5] = jcbWalze2Stellung.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[6] = jcbWalze3Stellung.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[7] = jcbWalze4Stellung.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[8] = jcbWalze1Ring.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[9] = jcbWalze2Ring.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[10] = jcbWalze3Ring.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[11] = jcbWalze4Ring.getSelectionModel().getSelectedIndex();
		QuickSaveEinstellungen[12] = jcbUKW.getSelectionModel().getSelectedIndex();
		
		QuickSaveEinstellungenSteckbrett[0] = txtSteck1.getText();
		QuickSaveEinstellungenSteckbrett[1] = txtSteck2.getText();
		QuickSaveEinstellungenSteckbrett[2] = txtSteck3.getText();
		QuickSaveEinstellungenSteckbrett[3] = txtSteck4.getText();
		QuickSaveEinstellungenSteckbrett[4] = txtSteck5.getText();
		QuickSaveEinstellungenSteckbrett[5] = txtSteck6.getText();
		QuickSaveEinstellungenSteckbrett[6] = txtSteck7.getText();
		QuickSaveEinstellungenSteckbrett[7] = txtSteck8.getText();
		QuickSaveEinstellungenSteckbrett[8] = txtSteck9.getText();
		QuickSaveEinstellungenSteckbrett[9] = txtSteck10.getText();
		QuickSaveEinstellungenSteckbrett[10] = txtSteck11.getText();
		QuickSaveEinstellungenSteckbrett[11] = txtSteck12.getText();
		QuickSaveEinstellungenSteckbrett[12] = txtSteck13.getText();
		
		btnQuickLoad.setDisable(false);
	}
	
	/**
	 * handelt die Eingabe des Schnelladen Buttons btnQuickLoad
	 * Laed die zuvor von der handleQuickSave() abgespeicherten Elemente wieder aus den Arrays in die Enigma
	 */
	@FXML
	private void handleQuickLoad() {
		
		handleBbtnReset();
		
		jcbWalze1.getSelectionModel().select(QuickSaveEinstellungen[0]);
		jcbWalze2.getSelectionModel().select(QuickSaveEinstellungen[1]);
		jcbWalze3.getSelectionModel().select(QuickSaveEinstellungen[2]);
		jcbWalze4.getSelectionModel().select(QuickSaveEinstellungen[3]);
		jcbWalze1Stellung.getSelectionModel().select(QuickSaveEinstellungen[4]);
		jcbWalze2Stellung.getSelectionModel().select(QuickSaveEinstellungen[5]);
		jcbWalze3Stellung.getSelectionModel().select(QuickSaveEinstellungen[6]);
		jcbWalze4Stellung.getSelectionModel().select(QuickSaveEinstellungen[7]);
		jcbWalze1Ring.getSelectionModel().select(QuickSaveEinstellungen[8]);
		jcbWalze2Ring.getSelectionModel().select(QuickSaveEinstellungen[9]);
		jcbWalze3Ring.getSelectionModel().select(QuickSaveEinstellungen[10]);
		jcbWalze4Ring.getSelectionModel().select(QuickSaveEinstellungen[11]);
		jcbUKW.getSelectionModel().select(QuickSaveEinstellungen[12]);
		
		txtSteck1.setText(QuickSaveEinstellungenSteckbrett[0]);
		txtSteck2.setText(QuickSaveEinstellungenSteckbrett[1]);
		txtSteck3.setText(QuickSaveEinstellungenSteckbrett[2]);
		txtSteck4.setText(QuickSaveEinstellungenSteckbrett[3]);
		txtSteck5.setText(QuickSaveEinstellungenSteckbrett[4]);
		txtSteck6.setText(QuickSaveEinstellungenSteckbrett[5]);
		txtSteck7.setText(QuickSaveEinstellungenSteckbrett[6]);
		txtSteck8.setText(QuickSaveEinstellungenSteckbrett[7]);
		txtSteck9.setText(QuickSaveEinstellungenSteckbrett[8]);
		txtSteck10.setText(QuickSaveEinstellungenSteckbrett[9]);
		txtSteck11.setText(QuickSaveEinstellungenSteckbrett[10]);
		txtSteck12.setText(QuickSaveEinstellungenSteckbrett[11]);
		txtSteck13.setText(QuickSaveEinstellungenSteckbrett[12]);
		
		handleEinstellEnigma();
	}
	
	/**
	 * Handelt die Eingabe des Analyse Monitor klein Menu Punktes.
	 * Startet einen neuen Analyse Monitor 
	 * Startet jedoch keinen Analyse Monitor falls der Live Modus Aktiv ist.
	 */
	@FXML
	private void handleEingabemeiAnalysemonitorKlein() {
		if(originalEnigmaModeOn) {
			Alert keinAnalyseMonitor = new Alert(AlertType.ERROR, "Der Analyse Monitor Klein ist im Enigma Original Modus "
					+ "nicht verfügbar bitte Live Modus verwenden", ButtonType.OK);
			keinAnalyseMonitor.show();
		} else if (isAnalyseMonitorGroßAktiv) {
			Alert keinAnalyseMonitor = new Alert(AlertType.ERROR, "Der Analyse Monitor Klein kann nicht verwendet werden "
					+ "solange der Analyse Monitor Groß aktiv ist. Bitte erst den Analyse Monitor Groß schließen", ButtonType.OK);
			keinAnalyseMonitor.show();
		} else {
			new AnalyseMonitorStarter();
		}
		
		
	}
	
	/**
	 * Handelt die Eingabe des Analyse Monitor Groß Menue Punktes.
	 * Startet einen neuen Analyse Monitor Groß
	 * Startet jedoch keinen Analyse Monitor falls der Live Modus Aktiv ist.
	 */
	@FXML
	private void handleEingabeAnalysemonitorGroß() {
		if(originalEnigmaModeOn) {
			Alert keinAnalyseMonitor = new Alert(AlertType.ERROR, "Der Analyse Monitor Groß ist im Enigma Original Modus "
					+ "nicht verfügbar bitte Live Modus verwenden", ButtonType.OK);
			keinAnalyseMonitor.show();
		} else if (isAnalyseMonitorAktiv) {
			Alert keinAnalyseMonitor = new Alert(AlertType.ERROR, "Der Analyse Monitor Groß kann nicht verwendet werden "
					+ "solange der Analyse Monitor Klein aktiv ist. Bitte erst den Analyse Monitor Klein schließen", ButtonType.OK);
			keinAnalyseMonitor.show();
		} else {
			new AnalyseMonitorGrossStarter();
		}
	}
	
	/**
	 * Handelt die Eingabe des Menu Punktes Key Manager
	 * Startet ein neues Key Manager Fenster
	 * Worin die Einstellung zu einem Schluessel Exportiert oder aus einem Schluessel Importiert werden koennen.
	 */
	@FXML
	private void handlemeiEingabeKeyManager() {
		new KeyManagerStarter();
	}
	
	/**
	 * Handelt die Eingabe des Menu Punktes Einstellungen
	 * und startet ein neues Fenster worin der Benutzer 
	 * Einstellungen an der Anwendung vornehmen kann
	 */
	@FXML
	private void handleEinstellungen() {
		new EinstellungStarter();
	}
	
	/**
	 * handelt die Eingabe des Menu Punktes Info ueber Enigma Simulator
	 * und startet ein neues Fenster worin der Benutzer 
	 * Infos zu dieser Anwendung findet
	 */
	@FXML
	private void handlemeiInfoUeberEnigmaSimulator() {
		new InfoFensterStarter();
	}
	
	/**
	 * handelt die Eingabe des Ladens Menu Punkt
	 * Laed eine vom Benutzer ausgewaehlte Datei mit Einstellungen der Enigma 
	 * uns setzt diese fest
	 */
	@FXML
	private void handleLaden() {
		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setTitle("Datei Auswählen");
		File directory = fileChooser.showOpenDialog(Main.getStage());
		if(directory ==  null) {
			return;
		}
		
		SaveAndLoadEnigma load = new SaveAndLoadEnigma(directory);
		
		load.Load();
		
		if(load.getLoadSucces()) {
			
			handleBbtnReset();
			
			istM4Aktiv = load.getEnigmaM4Active();
			
			int[] SaveEinstellungenEnigma =  load.getEinstellungenEnigma();
			String[] SaveSteckbrett = load.getSteckverbindungen();
			
		
			jcbWalze4.getSelectionModel().select(SaveEinstellungenEnigma[0]);
			jcbWalze4Stellung.getSelectionModel().select(SaveEinstellungenEnigma[1]);
			jcbWalze4Ring.getSelectionModel().select(SaveEinstellungenEnigma[2]);
			
			jcbUKW.getSelectionModel().select(SaveEinstellungenEnigma[12]);
			
			
			jcbWalze3.getSelectionModel().select(SaveEinstellungenEnigma[3]);
			jcbWalze3Stellung.getSelectionModel().select(SaveEinstellungenEnigma[4]);
			jcbWalze3Ring.getSelectionModel().select(SaveEinstellungenEnigma[5]);
			
			jcbWalze2.getSelectionModel().select(SaveEinstellungenEnigma[6]);
			jcbWalze2Stellung.getSelectionModel().select(SaveEinstellungenEnigma[7]);
			jcbWalze2Ring.getSelectionModel().select(SaveEinstellungenEnigma[8]);
			
			jcbWalze1.getSelectionModel().select(SaveEinstellungenEnigma[9]);
			jcbWalze1Stellung.getSelectionModel().select(SaveEinstellungenEnigma[10]);
			jcbWalze1Ring.getSelectionModel().select(SaveEinstellungenEnigma[11]);
			
			
			
			txtSteck1.setText(SaveSteckbrett[0]);
			txtSteck2.setText(SaveSteckbrett[1]);
			txtSteck3.setText(SaveSteckbrett[2]);
			txtSteck4.setText(SaveSteckbrett[3]);
			txtSteck5.setText(SaveSteckbrett[4]);
			txtSteck6.setText(SaveSteckbrett[5]);
			txtSteck7.setText(SaveSteckbrett[6]);
			txtSteck8.setText(SaveSteckbrett[7]);
			txtSteck9.setText(SaveSteckbrett[8]);
			txtSteck10.setText(SaveSteckbrett[9]);
			txtSteck11.setText(SaveSteckbrett[10]);
			txtSteck12.setText(SaveSteckbrett[11]);
			txtSteck13.setText(SaveSteckbrett[12]);
			
			SteckbrettPruefOberflaeche = SaveSteckbrett;
			
			if(istM4Aktiv) {
				
				btnM4SwitchM3.setText("Enigma M4 Ausgewählt");
				btnM4SwitchM3.setSelected(false);
				jcbWalze4.setDisable(false);
				jcbWalze4Ring.setDisable(false);
				jcbWalze4Stellung.setDisable(false);
			}
			else {
				btnM4SwitchM3.setText("Enigma M3 Ausgewählt");
				btnM4SwitchM3.setSelected(true);
				jcbWalze4.setDisable(true);
				jcbWalze4Ring.setDisable(true);
				jcbWalze4Stellung.setDisable(true);
			}
			
		
			originalEnigmaModeOn = load.isOriginalEnigmaModeOn();
			
			if(!originalEnigmaModeOn) {
				handleEinstellEnigma();
				updateOberFlaeche();
			} else {
				btnEinstellungenEnigma.setDisable(true);
				txaEingabe.setDisable(false);
				txaEingabe.setEditable(true);
			}
			
			if(load.getEinAusgabeSpeichern()) {
				txaAusgabe.setText(load.getAusgabeVerschluesselt());
				if(!originalEnigmaModeOn) {
					txaEingabe.setText(load.getEingabeUnverschluesselt());
				}
				
				handleEingabeTXA();
			}
		}
	}
	
	/**
	 * handelt die Eingabe des Speichern Menu punktes
	 * Speichert die derzeit gesetzten einstellungen der Enigma in einer vom
	 * Benutzer ausgewaehlten Datei
	 */
	@FXML
	private void handleSpeichern() {
		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setTitle("Verzeichniss Auswählen");
		fileChooser.setInitialFileName("Enigma.einst");
		
		File directory = fileChooser.showSaveDialog(Main.getStage());
		if(directory ==  null) {
			return;
		}
		
		int[] SaveEinstellungenEnigma = new int[13];
		String[] SaveSteckbrett = new String[13];
		
		Arrays.fill(SaveSteckbrett, null);
		
		if(originalEnigmaModeOn) {
			if(istM4Aktiv) {
				SaveEinstellungenEnigma[0]=jcbWalze4.getSelectionModel().getSelectedIndex();
				SaveEinstellungenEnigma[1]=getBuchstabenNr(jcbWalze4Stellung);
				SaveEinstellungenEnigma[2]=jcbWalze4Ring.getSelectionModel().getSelectedIndex();
				
				SaveEinstellungenEnigma[12]=jcbUKW.getSelectionModel().getSelectedIndex();
			}
			else {
				SaveEinstellungenEnigma[0]=0;
				SaveEinstellungenEnigma[1]=0;
				SaveEinstellungenEnigma[2]=0;
				
				SaveEinstellungenEnigma[12]=jcbUKW.getSelectionModel().getSelectedIndex();
			}
					
			SaveEinstellungenEnigma[3]=jcbWalze3.getSelectionModel().getSelectedIndex();
			SaveEinstellungenEnigma[4]=getBuchstabenNr(jcbWalze3Stellung);
			SaveEinstellungenEnigma[5]=jcbWalze3Ring.getSelectionModel().getSelectedIndex();
			
			SaveEinstellungenEnigma[6]=jcbWalze2.getSelectionModel().getSelectedIndex();
			SaveEinstellungenEnigma[7]=getBuchstabenNr(jcbWalze2Stellung);
			SaveEinstellungenEnigma[8]=jcbWalze2Ring.getSelectionModel().getSelectedIndex();
			
			
			SaveEinstellungenEnigma[9]=jcbWalze1.getSelectionModel().getSelectedIndex();
			SaveEinstellungenEnigma[10]=getBuchstabenNr(jcbWalze1Stellung);
			SaveEinstellungenEnigma[11]=jcbWalze1Ring.getSelectionModel().getSelectedIndex();
		} else {
			SaveEinstellungenEnigma = EnigmaEinstellungen;
			
			//Rueckgaening machen der aus dem EnigmaEinstellungen vorgenommen aenderungen zur erzeugung des Enigma Objekts
			SaveEinstellungenEnigma[3] -= 1;
			SaveEinstellungenEnigma[6] -= 1;
			SaveEinstellungenEnigma[9] -= 1;
			
			if(istM4Aktiv) {
				SaveEinstellungenEnigma[0] -= 10;
				SaveEinstellungenEnigma[12] -= 10;
			} else {
				SaveEinstellungenEnigma[12] -= 1;
			}
		}
		
		
		SaveSteckbrett[0] = txtSteck1.getText();
		SaveSteckbrett[1] = txtSteck2.getText();
		SaveSteckbrett[2] = txtSteck3.getText();
		SaveSteckbrett[3] = txtSteck4.getText();
		SaveSteckbrett[4] = txtSteck5.getText();
		SaveSteckbrett[5] = txtSteck6.getText();
		SaveSteckbrett[6] = txtSteck7.getText();
		SaveSteckbrett[7] = txtSteck8.getText();
		SaveSteckbrett[8] = txtSteck9.getText();
		SaveSteckbrett[9] = txtSteck10.getText();
		SaveSteckbrett[10] = txtSteck11.getText();
		SaveSteckbrett[11] = txtSteck12.getText();
		SaveSteckbrett[12] = txtSteck13.getText();
		
		String einagbeText = "";
		String ausgabeText = "";
		
		if (einAusgabeTextSpeichern) {
			einagbeText = txaEingabe.getText();
			ausgabeText = txaAusgabe.getText();
		} 
		
		//Erzeugen des Speichern Objektes
		SaveAndLoadEnigma Save = new SaveAndLoadEnigma(directory, SaveEinstellungenEnigma, SaveSteckbrett,
				istM4Aktiv, einagbeText, ausgabeText, einAusgabeTextSpeichern, originalEnigmaModeOn);
		
		//Soeichern der Einstellungen in eine Datei
		Save.Save();
	}
	
	/**
	 * Prueft ob sich ein Buchstabe in einem anderen Feld es steckbrettes in der oberflaeche wiederholt und kennzeichnet dieses.
	 * 
	 * @param steckField textField das zu pruefen ist
	 * @param steckEinagbe die Eingabe des Textfeldes
	 * @return ob die pruefung ohne doppeleingabe abeschlossen wurde
	 */
	private static boolean checkSteckbrettEinagbe(TextField steckField, String steckEinagbe) {
		
		if((( -1 != Arrays.toString(SteckbrettPruefOberflaeche).indexOf(steckEinagbe.charAt(0))) ||  	
				-1 != Arrays.toString(SteckbrettPruefOberflaeche).indexOf(steckEinagbe.charAt(1))) ||
				( steckEinagbe.charAt(0) == steckEinagbe.charAt(1))) { 			// Prueft auf doppelleingabe in einem feld
			
			steckField.setStyle("-fx-background-color: red;");
			return false;
		} else {
			steckField.setStyle(null);
			return true;
		}
	}
	
	/**
	 * Setzt alle Steckbrett Textfelder auf den urspruenglichen style zurueck.
	 * Sodass beim resten der Oberflaeche keine rot markierten Felder zurueckbleiben.
	 */
	private void resestSteckbrettColor() {
		TextField[] steckbrettArray = { txtSteck1, txtSteck2, txtSteck3, txtSteck4, txtSteck5, txtSteck6, txtSteck7,
				txtSteck8, txtSteck9, txtSteck10, txtSteck11, txtSteck12, txtSteck13 };
		
		for (TextField steckField : steckbrettArray) {
			steckField.setStyle(null);
		}
	}
	
	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 1 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett1() {
		
		String temp = txtSteck1.getText().toUpperCase();
		txtSteck1.setText(temp);
		txtSteck1.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[0] = "";
			txtSteck1.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck1.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck1, temp)) {
				SteckbrettPruefOberflaeche[0] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 2 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett2() {
		
		String temp = txtSteck2.getText().toUpperCase();
		txtSteck2.setText(temp);
		txtSteck2.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[1] = "";
			txtSteck2.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck2.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck2, temp)) {
				SteckbrettPruefOberflaeche[1] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 3 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett3() {
		
		String temp = txtSteck3.getText().toUpperCase();
		txtSteck3.setText(temp);
		txtSteck3.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[2] = "";
			txtSteck3.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck3.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck3, temp)) {
				SteckbrettPruefOberflaeche[2] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 4 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett4() {
		
		String temp = txtSteck4.getText().toUpperCase();
		txtSteck4.setText(temp);
		txtSteck4.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[3] = "";
			txtSteck4.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck4.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck4, temp)) {
				SteckbrettPruefOberflaeche[3] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 5 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett5() {
		
		String temp = txtSteck5.getText().toUpperCase();
		txtSteck5.setText(temp);
		txtSteck5.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[4] = "";
			txtSteck5.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck5.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck5, temp)) {
				SteckbrettPruefOberflaeche[4] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 6 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett6() {
		
		String temp = txtSteck6.getText().toUpperCase();
		txtSteck6.setText(temp);
		txtSteck6.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[5] = "";
			txtSteck6.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck6.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck6, temp)) {
				SteckbrettPruefOberflaeche[5] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 7 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett7() {
		
		String temp = txtSteck7.getText().toUpperCase();
		txtSteck7.setText(temp);
		txtSteck7.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[6] = "";
			txtSteck7.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck7.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck7, temp)) {
				SteckbrettPruefOberflaeche[6] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 8 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett8() {
		
		String temp = txtSteck8.getText().toUpperCase();
		txtSteck8.setText(temp);
		txtSteck8.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[7] = "";
			txtSteck8.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck8.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck8, temp)) {
				SteckbrettPruefOberflaeche[7] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 9 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett9() {
		
		String temp = txtSteck9.getText().toUpperCase();
		txtSteck9.setText(temp);
		txtSteck9.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[8] = "";
			txtSteck9.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck9.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck9, temp)) {
				SteckbrettPruefOberflaeche[8] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 10 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett10() {
		
		String temp = txtSteck10.getText().toUpperCase();
		txtSteck10.setText(temp);
		txtSteck10.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[9] = "";
			txtSteck10.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck10.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck10, temp)) {
				SteckbrettPruefOberflaeche[9] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 11 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett11() {
		
		String temp = txtSteck11.getText().toUpperCase();
		txtSteck11.setText(temp);
		txtSteck11.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[10] = "";
			txtSteck11.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck11.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck11, temp)) {
				SteckbrettPruefOberflaeche[10] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 12 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett12() {
		
		String temp = txtSteck12.getText().toUpperCase();
		txtSteck12.setText(temp);
		txtSteck12.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[11] = "";
			txtSteck12.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck12.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck12, temp)) {
				SteckbrettPruefOberflaeche[11] = temp;
			}
		}
	}

	/**
	 * Prueft die Eingabe auf dem Steckbrett im Feld 13 bzw. Sichert ab das nur 2 Buchstaben Eingegeben werden koennen
	 * 
	 */
	@FXML
	private void handlePruefEingabeSteckbrett13() {
		
		String temp = txtSteck13.getText().toUpperCase();
		txtSteck13.setText(temp);
		txtSteck13.positionCaret(temp.length());
		
		if(temp.length() < 2) {
			SteckbrettPruefOberflaeche[12] = "";
			txtSteck13.setStyle(null);
		}
		
		if(temp.length() > 2) {
			temp = temp.substring(0,2);
			txtSteck13.setText(temp);
		}
		
		if(temp.length() == 2) {
			if(checkSteckbrettEinagbe(txtSteck13, temp)) {
				SteckbrettPruefOberflaeche[12] = temp;
			}
		}
	}
}
