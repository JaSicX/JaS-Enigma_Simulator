package EnigmaSimulator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



/**
 * Klasse zum Speichern der Einstellungen der
 * Enigma in eine Datei.
 * Oder das Laden von Einstellungen aus einer Datei.
 * Das Error handling passiert in dieser Klasse.
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
public class SaveAndLoadEnigma implements Serializable {

	
	/** Die Versions variable (Repesentiert die derzeitige version dieser Klasse)*/
	private static final long serialVersionUID = -4479008407681471277L;
	
	/** Array fuer die Einstellungen der Enigma*/
	private int[] EinstellEnigma;
	
	/** Array fuer die Gesteckten Verbindungen der Enigma*/
	private String[] Steckbrettverbindungen;
	
	/** Wert ob Enigma M4 aktiv ist sonst Enigma M3 aktiv*/
	private boolean EnigmaM4Active;
	
	/** Die Unverschluesselte Eingabe (wird nur auf wunsch mit abgespeichert)*/
	private String eingabeUnverschluesselt;
	
	/** Die Verschluessekte Ausgabe (wird nur auf wunsch mit abgespeichert)*/
	private String ausgabeVerschluesselt;

	/** Wert ob die Eingabe mit Abgespeichert werden soll*/
	private boolean einAusgabeSpeichen;
	
	/** Wert ob der Original modus der Enigma zur zeit der Speicherung aktiv war*/
	private boolean originalEnigmaModeOn;

	/** Pfad wo sich die zu Speicheernde Datei befindet oder welche geladen werden soll*/
	private File directory;
	
	/** Variable ob das Laden der Einstellungen Erfolgreich war */
	private boolean loadSucces = false;
	
	/**
	 * Konstruktor der Speicher und Laden Klasse (Speichern)
	 * wird verwendet um die Einstellungen der Enigma abzuspeichern
	 * die werte werden dem Konstrukor uebergeben
	 * 
	 * @param directory Pfad wohin abgespeichert werden soll
	 * @param EinstellungenEnigma Einstellungen der Enigma die gespeichert werden sollen
	 * @param Steckverbindungen Die Steckverbindungen die gespeichert werden sollen
	 * @param EnigmaM4Active Wert ob die Enigma M4 aktiv ist sonst M3 aktiv
	 * @param eingabeUnverschluesselt Die Eingabe die mit abgespeichert werden soll
	 * @param ausgabeVerschluesselt Die Verschluesselte Ausgabe die mit abgespeichert werden soll
	 * @param einAusgabeSpeichen Wert ob die Eingabe mit gespeichert werden soll bei true aktiv
	 * @param liveModusAktiv Wert ob der Live Modus aktiv ist
	 */
	public SaveAndLoadEnigma(File directory, int[] EinstellungenEnigma, String[] Steckverbindungen, boolean EnigmaM4Active,
			String eingabeUnverschluesselt, String ausgabeVerschluesselt, boolean einAusgabeSpeichen, boolean liveModusAktiv) {
		this.directory = directory;
		this.EinstellEnigma = EinstellungenEnigma;
		this.Steckbrettverbindungen = Steckverbindungen;
		this.EnigmaM4Active = EnigmaM4Active;
		this.eingabeUnverschluesselt = eingabeUnverschluesselt;
		this.ausgabeVerschluesselt = ausgabeVerschluesselt;
		this.einAusgabeSpeichen = einAusgabeSpeichen;
		this.originalEnigmaModeOn = liveModusAktiv;
	}
	
	/**
	 * Konstruktor der Speicher und Laden Klasse (Laden)
	 * wird verwendet um Einstellungen aus einer Datei wieder zu lesen
	 * und in das Objekt zu speichern 
	 * 
	 * @param directory Pfad wo die Datei sich befindet
	 */
	public SaveAndLoadEnigma(File directory) {
		this.directory = directory;
		this.EinstellEnigma = null;
		this.Steckbrettverbindungen = null;
		this.eingabeUnverschluesselt = null;
		this.ausgabeVerschluesselt = null;
		this.einAusgabeSpeichen = false;
		this.originalEnigmaModeOn = false;
	}
	
	/**
	 * Laed die in einer Datei gespeicherten Einstellungswerte 
	 * und speichert diese im Objekt ab
	 * 
	 *  Außerdem pruefung ob es sich um die selbe version handelt mittels 
	 *  serial Version UID
	 *  
	 *  Bei einem Fehler wird ein error Dialog angezeigt 
	 */
	public void Load() {
		
		try {
			FileInputStream fis = new FileInputStream(directory);
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			//Pruefung ob versionsUID uebereinstimmt
			long readVersionUID = ois.readLong();
			if(serialVersionUID != readVersionUID) {
				ois.close();
				
				loadSucces = false;
				Alert errorSave = new Alert(AlertType.ERROR, "Ein fehler beim laden der Datei ist aufgetreten\n"
						+ "Diese Datei ist nicht Kompatibel mit dieser Version");
				errorSave.show();
				return;
			}
			
			EinstellEnigma = (int[]) ois.readObject();
			Steckbrettverbindungen = (String[]) ois.readObject();
			EnigmaM4Active = ois.readBoolean();
			einAusgabeSpeichen = ois.readBoolean();
			originalEnigmaModeOn = ois.readBoolean();
			eingabeUnverschluesselt = (String) ois.readObject();
			ausgabeVerschluesselt = (String) ois.readObject();
			
			ois.close();
			
			loadSucces = true;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
			loadSucces = false;
			Alert errorSave = new Alert(AlertType.ERROR, "Ein fehler beim laden der Datei ist aufgetreten");
			errorSave.show();
		}
	}
	
	/**
	 * Speichert die an das Objekt uebergenen Einstellungs Werte 
	 * in eine Datei 
	 * 
	 * Bei einem Fehler wird ein error Dialog angezeigt 
	 */
	public void Save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(directory);
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeLong(serialVersionUID);
			
			oos.writeObject(EinstellEnigma);
			oos.writeObject(Steckbrettverbindungen);
			oos.writeBoolean(EnigmaM4Active);
			oos.writeBoolean(einAusgabeSpeichen);
			oos.writeBoolean(originalEnigmaModeOn);
			oos.writeObject(eingabeUnverschluesselt);
			oos.writeObject(ausgabeVerschluesselt);
			
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			Alert errorSave = new Alert(AlertType.ERROR, "Ein Fehler beim speichern der Datei ist aufgetreten");
			errorSave.show();
		}
	}
	
	/**
	 * @return Die Einstellungen der Enigma
	 */
	public int[] getEinstellungenEnigma() {
		return EinstellEnigma;
	}
	
	/**
	 * @return Die gesetzten Steckverbindungen
	 */
	public String[] getSteckverbindungen() {
		return Steckbrettverbindungen;
	}
	
	/**
	 * @return Ist Enigma M4 aktiv 
	 */
	public boolean getEnigmaM4Active() {
		return EnigmaM4Active;
	}
	
	/**
	 * @return Die unverschluesselte Eingabe 
	 */
	public String getEingabeUnverschluesselt() {
		return eingabeUnverschluesselt;
	}
	
	/**
	 * @return Ob die Eingabe mit gespeichert wurde
	 */
	public boolean getEinAusgabeSpeichern() {
		return einAusgabeSpeichen;
	}
	
	/**
	 * @return ausgabeVerschluesselt
	 */
	public String getAusgabeVerschluesselt() {
		return ausgabeVerschluesselt;
	}

	/**
	 * @param ausgabeVerschluesselt das zu setzende Objekt ausgabeVerschluesselt
	 */
	public void setAusgabeVerschluesselt(String ausgabeVerschluesselt) {
		this.ausgabeVerschluesselt = ausgabeVerschluesselt;
	}

	/**
	 * @return Ob das laden erfolgreich war
	 */
	public boolean getLoadSucces() {
		return loadSucces;
	}
	
	/**
	 * @return liveModusAktiv
	 */
	public boolean isOriginalEnigmaModeOn() {
		return originalEnigmaModeOn;
	}

	/**
	 * @param liveModusAktiv das zu setzende Objekt liveModusAktiv
	 */
	public void setOriginalEnigmaModeOn(boolean liveModusAktiv) {
		this.originalEnigmaModeOn = liveModusAktiv;
	}
}
