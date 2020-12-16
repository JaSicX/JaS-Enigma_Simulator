package EnigmaSimulator;

/**
 * Klasse zur Verwaltung der UKW (Umkehrwalze) speichert die UKW strings 
 * und gibt diese zurueck
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
public class UKW {
	
	/** Der Rotor String (Verdrahtung) der ausgewaehlten UKW*/
	String UKW;
	
	/**
	 * Erstellt die UKW Elemente die die den String (Verdrahtung) der jeweiligen UKW beinhalten
	 * case 1-2 UKWs der M3 enigma
	 * case 4 und 5 die UKW B "duenn" und UKW C "duenn" der M4 Enigma
	 * 
	 * case 5 (UKW A) der ersten Enigma wird eig. nicht genutzt
	 * 
	 * @param art	welche UKW erstellt werden soll
	 */
	public UKW(int art) {
		
		switch(art) {
		
		case 1: //UKW B
			UKW = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
			break;
		case 2:	//UKW C
			UKW = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
			break;
		
			
		//UKWs der M4
		case 10:	//UKW B duenn
			UKW = "ENKQAUYWJICOPBLMDXZVFTHRGS";
			break;
		case 11:	//UKW C duenn
			UKW = "RDOBJNTKVEHMLFCWZAXGYIPSUQ";
			break;
		
		//eig. nicht genutzt nur zur Vollstaendigkeit	
		case 5: //UKW A
			UKW = "EJMZALYXVBWFCRQUONTSPIKHGD";
		break;
		}
	}
	
	/**
	 * @return den UKW string.
	 */
	public String getUKWstring() {
		return UKW;
	}
}