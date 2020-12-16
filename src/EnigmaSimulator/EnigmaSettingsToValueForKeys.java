package EnigmaSimulator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * Klasse zum Erzeugen und Einlesen von Keys die die Einstellungen der Enigma wiedergeben.
 * Die Keys werden als Base58 Codierung ausgegeben.
 * Oder ein Key in Base58 Codierung in eine Valide Einstellung fuer die Enigma umgewandelt.
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
public class EnigmaSettingsToValueForKeys {
	
	/** Array das alle Zeichen der BASE58 Codierung in einer aufsteigen sortierten Alphabetischen Reihenfolge enthaelt*/
	static final char[] BASE58 = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/** Array das alle Zeichen der BASE58 Codierung in einer zufaelligen gemischten Reihenfolge enthaelt*/  // Derzeit nicht genutzt
	static final char[] BASE58_MIX = { 'M', 'a', 's', 'L', 'F', 'y', 't', 'c', 'j', 'r', 'P', '1', 'J', 'N', 'W', 'S', '6',
			'n', 'V', '9', '4', 'z', 'D', 'h', 'R', 'H', 'm', '7', 'd', 'Y', 'e', 'v', 'A', 'b', '3', '8', 'f', 'x',
			'5', 'X', 'g', '2', 'q', 'C', 'w', 'k', 'B', 'i', 'p', 'Q', 'E', 'Z', 'u', 'T', 'o', 'K', 'U', 'G' };
	
	
	/** Zusammengesetzter key enigma einst + steck*/
	private BigInteger key;
	/** Vollstaendiger Key der alles enthaelt (Enigma einst. + steck + M4aktiv + pruefsumme)*/
	private BigInteger keyMitPruefsumme;
	/** Key der nur die Einstellungen der Enigma repraesentiert*/
	private BigInteger keyEinst;
	/** Key der nur das Steckbrett der Enigma repraesentiert*/
	private BigInteger keySteck;
	/** Key der repraesentiert ob die Enigma M4 aktiv ist (0 == M3)(1 == M4)*/
	private BigInteger keyEnigmaM4aktiv;		// eig ein boolean aber muss in key konvertiert werden repraesentiert nur 0 oder 1 (0 == M3 // 1 == M4)
	/** Die Enigma Einstellungen als Array*/
	private int[] enigmaEinst;
	/** Das Steckbrett der Enigma */
	private String[] enigmaSteck;
	/** Ob enigma M4 aktiv ist*/
	private boolean enigmaM4aktiv;
	/** Pruefsumme fuer den Key ob dieser valide ist*/
	private long pruefsumme;
	/** der endgueltige Key kodiert in Base58*/
	private String keyBase58 = "";
	
	/** Konstante fuer die Base58 Codierung*/
	private static final BigInteger BASE58_NUM = new BigInteger("58");
	
	
	/**
	 * Konstruktor um Einstellungen der Enigma in einen Key zu codieren
	 * 
	 * @param enigmaEinstellngen Array mit den Einstellungen der Enigma
	 * @param enigmaSteckverbindungen Array mit den Steckverbindungen der Enigma
	 * @param istEnigmaM4aktiv Wert ob Enigma M4 aktiv ist
	 */
	public EnigmaSettingsToValueForKeys(int[] enigmaEinstellngen, String[] enigmaSteckverbindungen, boolean istEnigmaM4aktiv) {
		enigmaEinst = enigmaEinstellngen;
		enigmaSteck = enigmaSteckverbindungen;
		enigmaM4aktiv = istEnigmaM4aktiv;
		
	}
	
	/**
	 * Konstruktor um einen Key in valide Einstellungen zurueck zu wandele
	 * 
	 * @param KeyInBase58 key Der Key von welchem die Einstellungen gelesen werden sollen
	 */
	public EnigmaSettingsToValueForKeys(String KeyInBase58) {
		this.keyBase58 = KeyInBase58;
	}
	
	/**
	 * Erstellt einen Key der die Einstellungen der Enigma repraesentiert
	 * aus den Daten die an das Objekt uebergeben wurden 
	 * und gibt diesen Key in Base58 Kodierung zurueck
	 * 
	 * @return erstellter Key in Base58 Kodierung
	 */
	public String makeBase58Key() {
		
		makeEinstZuBigInt();
		makeSteckZuBigInt();
		makeM4ativzuBigInt();
		combineTooKeysToOneKey();
		pruefsummeBerechnen();
		generateKey();
		KeyToBase58();
		
		return keyBase58;
	}
	
	/**
	 * Wandelt einen Key der an ein Ojekt uebergen wurde 
	 * wieder zurueck in seine Einzelnen Bestandteile 
	 * Der Enigma Einstellungen also in
	 * - Enigma Einstellungen
	 * - Steckbrett Verbindungen
	 * - Enigma M4 aktiv
	 * 
	 * @return Ob die Pruefsumme das Keys korrekt ist
	 */
	public boolean getEinstandSteckfromBase58() {
		
		if(!checkIfKeyIsValidBase58()) {
			return false;
		}
		
		Base58ToKey();
		getKeyEinstuKeySteckvonKey();
		getEinstIntZuArray();
		getEinstStringZuArray();
		getM4aktivVonKey();
		boolean pruefsummeKorekt = pruefsummeCheck();
		return pruefsummeKorekt;
	}
	
	/**
	 * Prueft ob ein Key der eingeben wurde theoretisch Valide ist an den Kriterien
	 * Ob er nur aus den zeichen besteht die in Base58 Codierung enthalten sind
	 * und ob er eine abgeschaetzte mindest und hoechtslaenge nicht unter-/ ueberschreitet
	 * 
	 * @return wenn der Key die Kriterien erfuellt true
	 */
	public boolean checkIfKeyIsValidBase58() {
		
		String base58CodirungString = new String(BASE58);
		if(keyBase58.length() > 35) {			//pruefen der unteren schranke
			if(keyBase58.length() < 70) {		//pruefen der oberen schranke
				if(Pattern.matches("["+base58CodirungString+"]*", keyBase58)) {		//den zeichensatz pruefen
					return true;
				}
			}
			
		}
		return false;
	}

	/**
	 * @return Einstellungen der Enigma als int Array
	 */
	public int[] getEnigmaEinst() {
		return enigmaEinst;
	}

	/**
	 * Zum setzen des Enigma Einstellungs Arrays fuer Key
	 * 
	 * @param enigmaEinst Enigma Einstellungs array
	 */
	public void setEnigmaEinst(int[] enigmaEinst) {
		this.enigmaEinst = enigmaEinst;
	}

	/**
	 * @return Enigma Steckbrett Steckverbindungen Array
	 */
	public String[] getEnigmaSteck() {
		return enigmaSteck;
	}

	/**
	 * Zum setzen der EnigmaSteckverbindungen fuer Key
	 * 
	 * @param enigmaSteck Enigma Steckbrett Steckverbindungen Array
	 */
	public void setEnigmaSteck(String[] enigmaSteck) {
		this.enigmaSteck = enigmaSteck;
	}

	/**
	 * @return Key der die Einstellungen der Enigma repraesentiert in Base58 Codierung
	 */
	public String getKeyBase58() {
		return keyBase58;
	}
	
	/**
	 * @return Gibt zurueck ob im Key die Enigma M4 aktiv war oder die M3
	 */
	public boolean isEnigmaM4aktiv() {
		return enigmaM4aktiv;
	}

	/**
	 * Zum setzen ob die Enigma M4 aktiv ist oder die M3 fuer Key
	 * 
	 * @param enigmaM4aktiv Wert ob Enigma M4 aktiv
	 */
	public void setEnigmaM4aktiv(boolean enigmaM4aktiv) {
		this.enigmaM4aktiv = enigmaM4aktiv;
	}
	
	/**
	 * Berechnet die Pruefsumme fuer den Key.
	 */
	private void pruefsummeBerechnen() {
		String keyS = key.toString();
		long pruefsummeTemp = 0;
		for(int i =0;i< keyS.length(); i++) {
			pruefsummeTemp += Long.valueOf(keyS.charAt(i));
		}
		this.pruefsumme = pruefsummeTemp;
	}
	
	/**
	 * Prueft ob die Pruefsumme von einem key korrekt ist
	 * 
	 * @return ob pruefsumme korekt
	 */
	private boolean pruefsummeCheck() {
		
		String keyS = keyMitPruefsumme.toString().substring(0, 79);
		long pruefsummeTemp = 0;
		for(int i =0;i< keyS.length(); i++) {
			pruefsummeTemp += Long.valueOf(keyS.charAt(i));
		}
		
		if(pruefsumme == pruefsummeTemp) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Wandelt den boolean EnigmaM4 aktiv wert in ein repraesentativen BigInt wert um
	 * und speichert ihn im Key object
	 */
	private void makeM4ativzuBigInt() {
		if(enigmaM4aktiv) {
			keyEnigmaM4aktiv = BigInteger.ONE;
		} else {
			keyEnigmaM4aktiv = BigInteger.ZERO;
		}
	}
	
	/**
	 * Wandelt den in BigInt codierten wert fuer die EnigmaM4 aktiv in ein boolean um 
	 * und speichert ihn im Key object
	 */
	private void getM4aktivVonKey() {
		
		if(keyEnigmaM4aktiv.equals(BigInteger.ONE)) {
			enigmaM4aktiv = true;
		} else {
			enigmaM4aktiv = false;
		}
	}
	
	/**
	 * Wandelt die Einstellungen der Enigma von einem int Array in einen BigInt teilkey um
	 * und speichert ihn im Key object
	 */
	private void makeEinstZuBigInt() {
		String[] einstIntArray = new String[enigmaEinst.length];
		
		String einstIntString = "";
		
		//Wandle das int Array in ein String Array
		for (int i = 0; i < enigmaEinst.length; i++) {
			einstIntArray[i] = String.valueOf(enigmaEinst[i]);
		}
		
		//Fuelle idexe mit einer einstelligen Zahl auf zweistellige auf
		for (int i = 0; i < einstIntArray.length; i++) {
			
			if (einstIntArray[i].length() < 2) {
				einstIntArray[i] = "0" + einstIntArray[i];
			}
		}
		
		//Erstelle string fuer BigInt objekt
		for (int i = 0; i < einstIntArray.length; i++) {
			einstIntString += einstIntArray[i];
		}
		
		keyEinst = new BigInteger(einstIntString);
	}
	
	/**
	 * Verbindet den EnigmaEinst Key mit dem Steckbrett Key und dem M4aktiv Key.
	 */
	private void combineTooKeysToOneKey() {
		String keyS = ((String.valueOf(keyEinst))+(String.valueOf(keySteck))+(String.valueOf(keyEnigmaM4aktiv)));
		this.key = new BigInteger(keyS);
	}
	
	/**
	 * Setzt den vollstaendigen Key zusammen.
	 * EinstellungenEnigma+Pruefsumme
	 */
	private void generateKey() {
		String keyS = ((String.valueOf(key))+(String.valueOf(pruefsumme)));
		this.keyMitPruefsumme = new BigInteger(keyS);
		
	}
	
	/**
	 * Zerschneidet einen kompletten key in seinen urspuenglichen
	 * Bestandteile EnigmaEinst. , Steckbrett und M4aktiv sowie die pruefsumme
	 */
	private void getKeyEinstuKeySteckvonKey() {
		String keyP = keyMitPruefsumme.toString();
		String keyEinst = keyP.substring(0, 26);
		String keySteck = keyP.substring(26, 78);
		String keyEnigmaM4aktiv = keyP.substring(78,79);
		String pruefsumme = keyP.substring(79);
		
		this.keyEinst = new BigInteger(keyEinst);
		this.keySteck = new BigInteger(keySteck);
		this.keyEnigmaM4aktiv = new BigInteger(keyEnigmaM4aktiv);
		this.pruefsumme = Long.parseLong(pruefsumme);
	}
	
	/**
	 * Wandelt den BigInt Key mit den Einstellungen der Enigma in ein int Array um
	 * und speichert es im Key object
	 */
	private void getEinstIntZuArray() {
		String einstS = keyEinst.toString();
		
		int[] einstInt = new int[einstS.length() / 2];
		
		for (int i = 0; i < einstInt.length; i++) {
			einstInt[i] = Integer.parseInt(einstS.substring(i + i, i + i + 2));
			
		}
		
		enigmaEinst = einstInt;
	}
	
	/**
	 * Wandelt den BigInt Steckbrett key wieder in ein String Array um das die Steckverbindungen enthaelt
	 * und speichert es im Key object
	 */
	private void getEinstStringZuArray() {
		
		int[] steckArrayInt = getEinstIntZuArray(keySteck);
		
		String[] steck = new String[steckArrayInt.length/2];
		String stecktemp = "";
		int x = 0;
		for (int i = 0; i < steck.length; i++) {
			
				stecktemp += (char) steckArrayInt[x];
				stecktemp += (char) steckArrayInt[x+1];
				steck[i] = stecktemp;
				x += 2;
				stecktemp = "";
			
		}
		this.enigmaSteck = steck;	
		eleminateSpacesinSteckArray();
	}
	
	/**
	 * Wandelt das Steckbrett String Array der Enigma in einen BigInt Key um
	 * und speichert ihn im Key object
	 */
	private void makeSteckZuBigInt() {
		
		checkAndFillEmptyIndexSteckArray();
		
		int[] steckInt = new int[enigmaSteck.length * 2];
		int x = 0;
		for (int i = 0; i < steckInt.length; i++) {
			
			if (i % 2 == 0) {
				steckInt[i] = (int) enigmaSteck[x].charAt(0);
			} else {
				steckInt[i] = (int) enigmaSteck[x].charAt(1);
				x++;
			}
		}
		
		
		String[] steckIntArray = new String[steckInt.length];
		String steckIntString = "";
		
		for (int i = 0; i < steckInt.length; i++) {
			steckIntArray[i] = String.valueOf(steckInt[i]);
		}
		
		for (int i = 0; i < steckIntArray.length; i++) {
			
			if (steckIntArray[i].length() < 2) {
				steckIntArray[i] = "0" + steckIntArray[i];
			}
		}
		
		for (int i = 0; i < steckIntArray.length; i++) {
			steckIntString += steckIntArray[i];
		}
		
		BigInteger einstBigInt = new BigInteger(steckIntString);
		
		keySteck = einstBigInt;
	}
	
	/**
	 * Fuellt ein steck array mit leerzeichen "  " auf wenn in den index Positionen 
	 * nichts voranden ist ("")  oder sich nur ein Buchstabe dort befindet.
	 * Wenn also ein 13 stelliges array leerstellen was die steckverbindung betrifft besitzt
	 */
	private void checkAndFillEmptyIndexSteckArray() {
		
		for(int i = 0; i < enigmaSteck.length; i++) {
			if(enigmaSteck[i].equals("") || enigmaSteck[i] == null) {
				enigmaSteck[i] = "  ";
			} else if (enigmaSteck[i].length() == 1) {		// falls aus der Gui nur ein Buchstabe uebergen wird diesen Loeschen und mit Leerzeichen ersetzten
				enigmaSteck[i] = "  ";
			}
		}
	}
	
	/**
	 * Eliminiert alle Leerzeichen in einem String Array (Steckbrett Array) in einer Position
	 * die ggf. bei der Umwandlung in den Key eingefuegt wurden. 
	 * Um das Urspruengliche Array zu erhalten.
	 * Es werden nur Leerzeichen in einer Position eliminiert die nur aus zwei Leerzeichen besteht.
	 * Die Array laenge bleibt jedoch 13
	 * 
	 * @return Array ohne Leerzeichen in einem Array element 
	 */
	private String[] eleminateSpacesinSteckArray() {
		
		for(int i = 0; i < enigmaSteck.length; i++) {
			if(enigmaSteck[i].equals("  ")) {
				enigmaSteck[i] = "";
			}
		}
		
		return enigmaSteck;
	}
	
	/**
	 * Wandelt eine BigInt value/Key in ein Array mit jeweils 2 Stellen pro index um
	 * 
	 * Bsp.: BigInt value = 123456
	 * Array Inhalt = [12,34,56]
	 * 
	 * @param value der umzuwandelnde wert
	 * @return int Array mit BigInt value in ein Array aufgespalten
	 */
	private static int[] getEinstIntZuArray(BigInteger value) {
		String einstS = value.toString();
		
		int[] einstInt = new int[einstS.length() / 2];
		
		for (int i = 0; i < einstInt.length; i++) {
			einstInt[i] = Integer.parseInt(einstS.substring(i + i, i + i + 2));
			
		}
		
		return einstInt;
	}
	
	/**
	 * Wandelt den vollstaendigen Key von einer Zahlenform (Base10) in eine Base58 Codierung um.
	 */
	private void KeyToBase58() {
		
		ArrayList<Integer> dividerResult = new ArrayList<Integer>(100);
		BigInteger divideNumber = keyMitPruefsumme;
		
		while(divideNumber.compareTo(BASE58_NUM) > 0 ) {
			
			int divResult = divideNumber.remainder(BASE58_NUM).intValue();
			if(divResult == 58) {
				dividerResult.add(0);
			} else {
				dividerResult.add(divResult);
			}
			
			divideNumber = divideNumber.divide(BASE58_NUM);
		}
		
		int divResult = divideNumber.remainder(BASE58_NUM).intValue();
		if(divResult == 58) {
			dividerResult.add(0);
		} else {
			dividerResult.add(divResult);
		}
		
		Collections.reverse(dividerResult);
		for (int currentNumber : dividerResult) {
			keyBase58 += BASE58[currentNumber];
		}
	}
	
	/**
	 * Wandelt ein in Base58 codierten vollstaendigen Key wieder in seine Zahlenform um.
	 */
	private void Base58ToKey() {
		ArrayList<BigInteger> dividerResult = new ArrayList<BigInteger>(keyBase58.length());
		BigInteger keyL = new BigInteger("0");
		
		for (int i = 0; i < keyBase58.length(); i++) {
			int position = search(keyBase58.charAt(i));
			dividerResult.add(new BigInteger(Integer.toString(position)));
		}
		
		for (int i = 0; i < dividerResult.size(); i++) {
			keyL = keyL.multiply(BASE58_NUM);
			keyL = keyL.add(dividerResult.get(i));
		}
		keyMitPruefsumme = keyL;
	}
	
	//Benutzung von linearer suche weil 1. wenig elemente 2. "nicht sortiertheit der Elemente" 
	/**
	 * Einfache suchmethode um in BASE58 array einen bestimmten char zu finden
	 * 
	 * @param toSearch zu suchender character im Array
	 * @return index Position dieses Chars im BASE58 array
	 */
	private static int search(char toSearch) {
		for(int i = 0; i < BASE58.length; i++) {
			if(BASE58[i] == toSearch) {
				return i;
			}
		}
		return -1;
	}
}
