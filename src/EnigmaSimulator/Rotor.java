package EnigmaSimulator;

import java.util.Arrays;

/**
 * Klasse zur Erstellung und Verwaltung/Bearbeitung der Rotoren fuer die Enigma M3 und M4 
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

public class Rotor {

	/** Repraesentiert die Verdrahtung des Rotors als String*/
	private String r;
	
	/** Enthaelt das Referenz alphabet fuer den aeußeren ring*/
	private String refABC;
	
	/** Enthaelt das interne Alphabet zur Verdrahtung des Rotors das Relativ zur Verdrahtung verschoben sein kann*/
	private String ringABC;
	
	/** speichert die Stellung des Rotors*/
	private int rotorstellung;
	
	/**
	 * Speichert die fortschaltkerben des Rotors
	 * Array große auf 5 gesetzt weil keine Rotoren mit mehr als 5 Fortschaltkerben bekannt sind
	 */
	private int[] fortschaltkerbe = new int[5];
	

	/**
	 * Konstruktor der Rotor klasse zum erzeugn der Rotoren bzw hier ist auch die verkabelung der Rotoren im Rotor String abgelgt
	 * case 0 ist die EGW eingabe walze
	 * case 1-8 die drei normalen Rotoren (1-8) der Enigma M3 und M4 wobei nur die ersten 3 rotorplaetze mit diesen 
	 * (von der EGW angefangen ) belegt werden koennen.
	 * 
	 * case 9-10 die spezialwalzen der M4 fuer die verwendung als 4 walze rechts neben der UKW 
	 * auch als duenne walze oder griechen Walze bezeichnet. Diese Walzen werden als Beta und Gamma bezeichnet
	 * 
	 * @param n Auswahl des Rotors welcher Rotor erzeugt werden soll
	 * @param verdrehen um wie viel der Rotor verdreht werden soll bzw. in welche Stellung dieser gedreht werden soll (= Rotorstellung)
	 * @param ringstellung Um wie viel der Ring am Rotor verschoben werden soll
	 */
	public Rotor(int n, int verdrehen, int ringstellung) {
		
		refABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		ringABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		Arrays.fill(fortschaltkerbe, -2);	//Array fuellen mit -2 weil 0 und -1 eine forschaltung ausloest   (-1 weil erste Rotor anomalie beim Fortschalten)
		
		switch (n) {
		
		case 0: //EGW
			r = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			rotorstellung = 0;
			break;
		case 1: //Rotor 1
			r = rotorDrehungAllinOne("EKMFLGDQVZNTOWYHXUSPAIBRCJ", verdrehen, ringstellung);
			
			rotorstellung = verdrehen;
			fortschaltkerbe[0]=16;
			break;
		case 2:	//Rotor 2
			r = rotorDrehungAllinOne("AJDKSIRUXBLHWTMCQGZNPYFVOE", verdrehen, ringstellung);
			
			rotorstellung = verdrehen;
			fortschaltkerbe[0]=4;
			break;
		case 3:	//Rotor 3
			r = rotorDrehungAllinOne("BDFHJLCPRTXVZNYEIWGAKMUSQO", verdrehen, ringstellung);
			
			rotorstellung = verdrehen;
			fortschaltkerbe[0]=21;
			break;
		case 4: //Rotor 4
			r = rotorDrehungAllinOne("ESOVPZJAYQUIRHXLNFTGKDCMWB", verdrehen, ringstellung);
			fortschaltkerbe[0]=9;

			rotorstellung = verdrehen;
			break;
		case 5: //Rotor 5
			r = rotorDrehungAllinOne("VZBRGITYUPSDNHLXAWMJQOFECK", verdrehen, ringstellung);
			fortschaltkerbe[0]=25;
			rotorstellung = verdrehen;
			break;
		case 6: //Rotor 6
			r = rotorDrehungAllinOne("JPGVOUMFYQBENHZRDKASXLICTW", verdrehen, ringstellung);
			fortschaltkerbe[0]=12;
			fortschaltkerbe[1]=25;
			rotorstellung = verdrehen;
			break;
		case 7: //Rotor 7
			r = rotorDrehungAllinOne("NZJHGRCXMYSWBOUFAIVLPEKQDT", verdrehen, ringstellung);
			fortschaltkerbe[0]=12;
			fortschaltkerbe[1]=25;
			rotorstellung = verdrehen;
			break;
		case 8: //Rotor 8
			r = rotorDrehungAllinOne("FKQHTLXOCBJSPDZRAMEWNIUYGV", verdrehen, ringstellung);
			fortschaltkerbe[0]=12;
			fortschaltkerbe[1]=25;
			rotorstellung = verdrehen;
			break;
			
		case 10: //Walze Beta
			r = rotorDrehungAllinOne("LEYJVCNIXWPBQMDRTAKZGFUHOS", verdrehen, ringstellung);
			
			rotorstellung = verdrehen;
			break;
		case 11: //Walze Gamma
			r = rotorDrehungAllinOne("FSOKANUERHMBTIYCWLQPZXVGJD", verdrehen, ringstellung);
			
			rotorstellung = verdrehen;
			break;

		default: //EGW
			r = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			rotorstellung = 0;
			break;
		}
	}       
	
	/**
	 * Dreht den Rotor um eins weiter und speichert den neuen Rotorstring beim Rotor ab
	 * dabei wird der RotorString und das RefABC sowie das RingABC um eine stelle verschoben was einer Drehung entspricht
	 */
	public void rotorDrehung() {
			
		int lenghtRef = refABC.length();
		int lenghtRing = ringABC.length();
		int lenghtR = r.length();
		
		String tempRotorString;
		String tempRingAbc;
		String tempRefAbc;
		
		char firstRefAbc = refABC.charAt(0);
		char firstRingAbc = ringABC.charAt(0);
		char firstRotorString = r.charAt(0);
		
		tempRefAbc = refABC.substring(1, lenghtRef);
		tempRingAbc = ringABC.substring(1, lenghtRing);
		tempRotorString = r.substring(1, lenghtR);
		
		refABC = tempRefAbc+firstRefAbc;
		ringABC = tempRingAbc+firstRingAbc;
		r = tempRotorString+firstRotorString;
		
		if(rotorstellung == 25) {
			rotorstellung = 0;
		}
		else {
			rotorstellung++;
		}
	}
	
	/**
	 *  Wird nur fuer den Konstruktor benutzt um die Rotoren bei der Erstellung zu verschieben
	 *  dabei wird der RotorString und das RefABC sowie das RingABC verschoben.
	 * 
	 * @param zuVerschiebenRotorString der Rotorstring der Verschoben werden soll
	 * @param StellungRotor anzahl um die der Rotor verschoben werden soll (RefABC, RotorString und RingABC werden verschoben)
	 * @param Ringstellung anzahl um die das RingABC verschoben werden soll (RotorString und RingABC werden verschoben)
	 * @return verschobener RotorString
	 */
	private String rotorDrehungAllinOne(String zuVerschiebenRotorString, int StellungRotor, int Ringstellung) {
		
		
		int lenght = zuVerschiebenRotorString.length();
		
		String tempRing;
		String tempRotor;
		String tempRef;
		String temp;
		
		for(int i=0; i< Ringstellung;i++) {
			
			char lastRing = ringABC.charAt(25);
			char lastRotor = zuVerschiebenRotorString.charAt(25);
			
			tempRing = ringABC.substring(0, 25);
			temp = zuVerschiebenRotorString.substring(0, 25);
			
			ringABC = lastRing+tempRing;
			zuVerschiebenRotorString = lastRotor+ temp;
		}
		
		for(int i=0; i < StellungRotor; i++) {
			
			char first = zuVerschiebenRotorString.charAt(0);
			char firstRef = refABC.charAt(0);
			char firstRing = ringABC.charAt(0);
			
			tempRotor = zuVerschiebenRotorString.substring(1, lenght);
			tempRef = refABC.substring(1, lenght);
			tempRing = ringABC.substring(1, lenght);
			
			zuVerschiebenRotorString = tempRotor+first;
			refABC = tempRef+firstRef;
			ringABC = tempRing+firstRing;
		}
		
		return zuVerschiebenRotorString;
	}
	
	/**
	 * @return gibt den RingABC String zurueck
	 */
	public String getRingABC() {
		return ringABC;
	}
		
	/**
	 * @return gibt den Rotorstring (die Verdrahtung des Rotors) zurueck
	 */
	public String getRotorString() {
		return r;
	}
	
	/**
	 * @return gibt die derzeitige rotorstellung als cahr zurueck
	 */
	public char getRotorCharStellung() {
		String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		 return (ABC.charAt(this.rotorstellung));
	}
	
	/**
	 * @return gibt die derzeitige Rotorstellung als int zurueck
	 */
	public int getRotorIntStellung() {
		return this.rotorstellung;
		
	}
	
	/**
	 * @return gibt das Refernezalphabet zurueck
	 */
	public String getRefABC() {
		return refABC;
	}
	
	/**
	 * @return position der vorstschaltkerbe als int array
	 */
	public int[] getVortschaltkerbe() {
		return fortschaltkerbe;
	}
}