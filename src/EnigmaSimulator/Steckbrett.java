package EnigmaSimulator;

/**
 * Klasse zur Verwaltung der Steckbrett Verbindungen der Enigma.
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
public class Steckbrett {
	
	/** In diesem String werden die Buchstaben getauscht*/
	private static String SteckABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** Dient als Abgleich fuer das SteckABC Normales Alphabet*/
	private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** Speichert die Gesteckte Verbindung*/
	private String SteckAB;
	
	/** Ist der Pruef String hier werden alle Gesetzen Steckverbingunegn abgespeichert um Doppel eingaben zu erkennen*/
	private static String pruefen = "";
	
	/**
	 * Konstruktor
	 * Erzeugt ein Steckbrett Objekt das die Steckverbindung enthealt
	 * veraendert auch das SteckABC mit dem abgeglichen wird
	 * 
	 * Sollte die Steckverbindung nicht zulaessing sein wird ein Leeres Objekt ohne Steckverbindung erzeugt !
	 * 
	 * @param SteckVerbindung	Die gesteckte Buchstabenkombination
	 */
	public Steckbrett(String SteckVerbindung) {
		if(SteckVerbindung.length() == 2 ) {		//prueft ob steckvebindung nur 2 buchstaben enthaelt
			if( !(( -1 != pruefen.indexOf(SteckVerbindung.charAt(0))) || 		//prueft auf doppeleinagben
					-1 != pruefen.indexOf(SteckVerbindung.charAt(1))) ||
					( SteckVerbindung.charAt(0) == SteckVerbindung.charAt(1)) )  {
				
				SteckAB = SteckVerbindung;
				pruefen += SteckVerbindung;
				steckABCaendern(SteckVerbindung);
			}
		}
	}
	
	/**
	 * Veraendert das SteckABC so das die gesteckten Buchstaben getauscht werden
	 * 
	 * @param SteckAB	Die gesteckte Buchstabenkombination
	 */
	public static void steckABCaendern(String SteckAB) {
		int erster = SteckABC.indexOf(SteckAB.charAt(0));
		int zweiter = SteckABC.indexOf(SteckAB.charAt(1));
		
		char[] abc = SteckABC.toCharArray();
		abc[erster]=SteckAB.charAt(1);
		abc[zweiter]=SteckAB.charAt(0);
		
		SteckABC = String.valueOf(abc);
	}
	
	/**
	 * Gibt die gesteckte Buchstabenverbindung zurueck
	 * 
	 * @return	Buchstabensteckverbindung
	 */
	public String getSteckString() {
		return SteckAB;
	}
	
	/** 
	 * @return	gibt das SteckABC zurueck
	 */
	public static String getSteckABC() {
		return SteckABC;
	}
	
	/**
	 * sezt den pruef und Steck String zurueck um neue eingaben 
	 * zu zulassen
	 */
	public static void resetSteckbrett() {
		SteckABC = ABC;
		pruefen = "";
	}
	
	/**
	 * @return gibt den pruefe string zurueck
	 */
	public static String getpruefString() {
		return pruefen;
	}
}