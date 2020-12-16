package EnigmaSimulator;


/**
 * Klasse die den Verschluesselungs Algorithmus der Enigma enthaelt 
 * um einen Text bzw. Buchstabe wie mit einer Originalen 
 * Enigma M4 / M3 zu verschluesseln.
 *
 *	Abkuerzungen
 *	Ver = Verschluesslung
 *	Abgl = Abgleich
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

public class Enigma {
	
	/** Eingangswalze der Enigma*/
	private Rotor egw;
	
	/** Erster Rotor der Enigma von der EGW aus gesehen*/
	private Rotor r1;
	
	/** Zweiter Rotor der Enigma von der EGW aus gesehen*/
	private Rotor r2;
	
	/** Dritter Rotor der Enigma von der EGW aus gesehen*/
	private Rotor r3;
	
	/** Vierter Rotor der Enigma von der EGW aus gesehen bzw. auch Grichenwalze*/
	private Rotor r4;
	
	/** Umkehrwalze der Enigma*/
	private UKW UKW;
	
	/** Normales Alphabet*/
	static final String ABC ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	/**
	 * @param Rotor4art			Legt die Walze des vierten auch griechenwalze Rotors fest 1-8
	 * @param Rotor4stellung	Legt die Stellung des Rotors vierten fest
	 * @param Rotor4ring		Legt die einstellung des Rings f�r den vierten Rotor feste (verschiebung der internen verdrahtung relativ zum au�ere ring (des RefABCs somit auch zur Rotorstellung)
	 * @param Rotor3art			Legt die Walze des dritte Rotors fest 1-8
	 * @param Rotor3stellung	Legt die Stellung des Rotors dritte fest
	 * @param Rotor3ring		Legt die einstellung des Rings f�r den dritte Rotor feste (verschiebung der internen verdrahtung relativ zum au�ere ring (des RefABCs somit auch zur Rotorstellung)
	 * @param Rotor2art			Legt die Walze des zweiten Rotors fest 1-8
	 * @param Rotor2stellung	Legt die Stellung des Rotors zweiten fest
	 * @param Rotor2ring		Legt die einstellung des Rings f�r den zweiten Rotor feste (verschiebung der internen verdrahtung relativ zum au�ere ring (des RefABCs somit auch zur Rotorstellung)
	 * @param Rotor1art			Legt die Walze des ersten Rotors fest 1-8
	 * @param Rotor1stellung	Legt die Stellung des Rotors ersten fest
	 * @param Rotor1ring		Legt die einstellung des Rings f�r den ersten Rotor feste (verschiebung der internen verdrahtung relativ zum au�ere ring (des RefABCs somit auch zur Rotorstellung)
	 * @param UKWart			Legt die Umkehrwalzen art fest
	 */
	public Enigma(int Rotor4art, int Rotor4stellung, int Rotor4ring, int Rotor3art, int Rotor3stellung, int Rotor3ring,
			int Rotor2art, int Rotor2stellung, int Rotor2ring, int Rotor1art, int Rotor1stellung, int Rotor1ring, int UKWart ) {
		
		egw = new Rotor(0, 0, 0);
		r1 = new Rotor(Rotor1art, Rotor1stellung, Rotor1ring);
		r2 = new Rotor(Rotor2art, Rotor2stellung, Rotor2ring);
		r3 = new Rotor(Rotor3art, Rotor3stellung, Rotor3ring);
		r4 = new Rotor(Rotor4art, Rotor4stellung, Rotor4ring);
		UKW = new UKW(UKWart);
	}
	
	/**
	 * Ist die verschluesslungsmethode fuer die Enigma M3 die alle Komponenten enthaelt 
	 * Verschluesselt mit den Einstellungen des Enigma Objektes
	 * 
	 * @param Eingabe der Buchstabe der Verschluesselt werden soll
	 * @return Verschluesslter Buchstabe
	 */
	public char VerschluesselungEnigmaM3(char Eingabe) {
		
		//Fortschaltung der Walzen
		RotorFortschaltung();
				
		//steckbrett
		Eingabe = SteckBrettVer(Eingabe);
		
		//ewg Stellungs Abgleich zur ersten Walze
		Eingabe = RefABCEingangAbgl(Eingabe, egw, r1);
		
		//1 walze
		Eingabe = WalzenVerHin(r1, r2, Eingabe);
		
		//2 walze
		Eingabe = WalzenVerHin(r2, r3, Eingabe);
		
		// 3 walze
		Eingabe = RingABCAbglEinagng(Eingabe, r3);
		Eingabe = RotorNverRing(Eingabe, r3);
		Eingabe = RingABCAbglAusgang(Eingabe, r3);
		Eingabe = RefABCEingangAbgl(Eingabe, r3.getRefABC(), ABC);
		
		
		//Umkerhwalze
		Eingabe = UKWver(Eingabe);
		
		Eingabe = RefABCEingangAbgl(Eingabe, ABC, r3.getRefABC());
		
		//rueckweg
		
		//walze 3
		Eingabe = WalzenVerRueck(r3, r2, Eingabe);
		
		//walze 2
		Eingabe = WalzenVerRueck(r2, r1, Eingabe);
		
		//walze 1
		Eingabe = WalzenVerRueck(r1, egw, Eingabe);
		
		//Steckbrett
		Eingabe = SteckBrettVer(Eingabe);
		
		return Eingabe;
	}
	
	/**
	 * die Verschluesselungs Methode fuer die Enigma M4 mit allen Komponenten enthaelt
	 * Verschluesselt mit den Einstellungen des Enigma Objektes
	 * 
	  @param Eingabe der Buchstabe der Verschluesselt werden soll
	 * @return Verschluesslter Buchstabe
	 */
	public char VerschluesselungEnigmaM4(char Eingabe) {
		
		//Fortschaltung der Walzen
		RotorFortschaltung();
		
		
		//Steckbrett verschluesselung
		Eingabe = SteckBrettVer(Eingabe);
		
		//ewg Stellungs Abgleich zur ersten Walze
		Eingabe = RefABCEingangAbgl(Eingabe, egw, r1);
		
		//1 walze
		Eingabe = WalzenVerHin(r1, r2, Eingabe);
		
		//2 walze
		Eingabe = WalzenVerHin(r2, r3, Eingabe);
		
		// 3 walze
		Eingabe = WalzenVerHin(r3, r4, Eingabe);
		
		// 4 walze
		Eingabe = RingABCAbglEinagng(Eingabe, r4);
		Eingabe = RotorNverRing(Eingabe, r4);
		Eingabe = RingABCAbglAusgang(Eingabe, r4);
		Eingabe = RefABCEingangAbgl(Eingabe, r4.getRefABC(), ABC);
		
		//Umkerhwalze
		Eingabe = UKWver(Eingabe);
		
		Eingabe = RefABCEingangAbgl(Eingabe, ABC, r4.getRefABC());
		
		//rueckweg
		
		// walze 4
		Eingabe = WalzenVerRueck(r4, r3, Eingabe);
		
		//walze 3
		Eingabe = WalzenVerRueck(r3, r2, Eingabe);
		
		//walze 2
		Eingabe = WalzenVerRueck(r2, r1, Eingabe);
		
		//walze 1
		Eingabe = WalzenVerRueck(r1, egw, Eingabe);
		
		//Steckbrett
		Eingabe = SteckBrettVer(Eingabe);
		
		return Eingabe;
	}
	
	/**
	 * Die Analyse Verschluesselungs Methode fuer die Enigma M4 mit allen ihren Komponenten
	 * hierbei wird allerdings ein Array zurueck gegeben welches jeden
	 * einzelnen schritt/Buchstaben der verschluesselung enthaelt
	 * 
	 * @param Eingabe der Buchstabe der Verschluesselt werden soll
	 * @return char Array das jeden Schritt der Verschluesslung als Buchstabe enthaelt
	 */
	public char[] VerschluesselungEnigmaM4Analyse(char Eingabe) {
		
		char[] zwischenschritteSpeicher = new char[37];
		
		//Walzen Rotation Fortschaltung
		//wie bei echter Enigma walzen rotation vor der verschluesselung
		RotorFortschaltung();
		
		//Steckbrett verschluesselung
		zwischenschritteSpeicher[0] = SteckBrettVer(Eingabe);
		
		//Speicherung des buchstabens im array
		
		//Die Schritt in der Verschluesslung durch eine walze sind mit den Schritten 1 bis 5 gekenzeichnet
		
		//1. Abgleich der EGW mit dem ersten rotor in bezug auf ihre stellung zu einander
		zwischenschritteSpeicher[1] = RefABCEingangAbgl(zwischenschritteSpeicher[0], egw, r1);
		
		//1 walze
		zwischenschritteSpeicher[2] = RingABCAbglEinagng(zwischenschritteSpeicher[1], r1);		//2. Abgleich des "Aueßeren" RefABC mit dem Ring ABC um auf die interne Verdrahtung zu schließen
		zwischenschritteSpeicher[3] = RotorNverRing(zwischenschritteSpeicher[2], r1);	//3. Eigentliche verschluesslung hier wird das RingABC mit dem RotorString "Abgeglichen" das die verdrahtung der Walze representiert
		zwischenschritteSpeicher[4] = RingABCAbglAusgang(zwischenschritteSpeicher[3], r1);				//4. Abgleich des "inneren" RingABC mit dem "Aueßeren" RefABC um fuer den uebertrag auf die richtige stellung zu schlißen ist quasi das gegenstueck zu schritt 2 hier wird diser nur rueckwerts gegangen
		zwischenschritteSpeicher[5] = RefABCEingangAbgl(zwischenschritteSpeicher[4], r1, r2);	// wie schritt 1. nur hier abgleich der RefABCs von Rotor 1 und Rotor 2 um auf ihre stellung zueinder zu schließen und den Buchstaben richtig zu uebertragen
		
		//2 walze
		zwischenschritteSpeicher[6] = RingABCAbglEinagng(zwischenschritteSpeicher[5], r2);
		zwischenschritteSpeicher[7] = RotorNverRing(zwischenschritteSpeicher[6], r2);
		zwischenschritteSpeicher[8] = RingABCAbglAusgang(zwischenschritteSpeicher[7], r2);
		zwischenschritteSpeicher[9] = RefABCEingangAbgl(zwischenschritteSpeicher[8], r2, r3);

		// 3 walze
		zwischenschritteSpeicher[10] = RingABCAbglEinagng(zwischenschritteSpeicher[9], r3);
		zwischenschritteSpeicher[11] = RotorNverRing(zwischenschritteSpeicher[10], r3);
		zwischenschritteSpeicher[12] = RingABCAbglAusgang(zwischenschritteSpeicher[11], r3);
		zwischenschritteSpeicher[13] = RefABCEingangAbgl(zwischenschritteSpeicher[12], r3, r4);
		
		// 4 walze
		zwischenschritteSpeicher[14] = RingABCAbglEinagng(zwischenschritteSpeicher[13], r4);
		zwischenschritteSpeicher[15] = RotorNverRing(zwischenschritteSpeicher[14], r4);
		zwischenschritteSpeicher[16] = RingABCAbglAusgang(zwischenschritteSpeicher[15], r4);
		zwischenschritteSpeicher[17] = RefABCEingangAbgl(zwischenschritteSpeicher[16], r4.getRefABC(), ABC);
		

		//Umkerhwalze
		zwischenschritteSpeicher[18] = UKWver(zwischenschritteSpeicher[17]);
	
		zwischenschritteSpeicher[19] = RefABCEingangAbgl(zwischenschritteSpeicher[18], ABC, r4.getRefABC());
		
		
		//rueckweg
		
		// walze 4
		zwischenschritteSpeicher[20] = RingABCAbglEinagng(zwischenschritteSpeicher[19], r4);
		zwischenschritteSpeicher[21] = RotorRueckVer(zwischenschritteSpeicher[20], r4);
		zwischenschritteSpeicher[22] = RingABCAbglAusgang(zwischenschritteSpeicher[21], r4);
		zwischenschritteSpeicher[23] = RefABCEingangAbgl(zwischenschritteSpeicher[22], r4, r3);
		
		//walze 3
		zwischenschritteSpeicher[24] = RingABCAbglEinagng(zwischenschritteSpeicher[23], r3);
		zwischenschritteSpeicher[25] = RotorRueckVer(zwischenschritteSpeicher[24], r3);
		zwischenschritteSpeicher[26] = RingABCAbglAusgang(zwischenschritteSpeicher[25], r3);
		zwischenschritteSpeicher[27] = RefABCEingangAbgl(zwischenschritteSpeicher[26], r3, r2);
		
		//walze 2
		zwischenschritteSpeicher[28] = RingABCAbglEinagng(zwischenschritteSpeicher[27], r2);
		zwischenschritteSpeicher[29] = RotorRueckVer(zwischenschritteSpeicher[28], r2);
		zwischenschritteSpeicher[30] = RingABCAbglAusgang(zwischenschritteSpeicher[29], r2);
		zwischenschritteSpeicher[31] = RefABCEingangAbgl(zwischenschritteSpeicher[30], r2, r1);
		
		//walze 1
		zwischenschritteSpeicher[32] = RingABCAbglEinagng(zwischenschritteSpeicher[31], r1);
		zwischenschritteSpeicher[33] = RotorRueckVer(zwischenschritteSpeicher[32], r1);
		zwischenschritteSpeicher[34] = RingABCAbglAusgang(zwischenschritteSpeicher[33], r1);
		zwischenschritteSpeicher[35] = RefABCEingangAbgl(zwischenschritteSpeicher[34], r1, egw);
		
		zwischenschritteSpeicher[36] = SteckBrettVer(zwischenschritteSpeicher[35]);
		
		
		return zwischenschritteSpeicher;
	}
	
	/**
	 * die Analyse Verschluesselungs Methode fuer die Enigma M3 mit allen Komponenten enthaelt
	 * hierbei wird allerdings ein Array zurueck gegeben welches jeden
	 * einzelnen schritt/Buchstaben der verschluesselung enthaelt
	 * 
	 * @param Eingabe der Buchstabe der Verschluesselt werden soll
	 * @return char Array das jeden Schritt der Verschluesslung als Buchstabe enthaelt
	 */
	public char[] VerschluesselungEnigmaM3Analyse(char Eingabe) {
		
		char[] zwischenschritteSpeicher = new char[29];
		
		//Walzen Rotation Fortschaltung
		//wie bei echter Enigma walzen rotation vor der verschluesselung
		RotorFortschaltung();
		
		//Steckbrett verschluesselung
		zwischenschritteSpeicher[0] = SteckBrettVer(Eingabe);
		
		//Speicherung des buchstabens im array
		
		//Die Schritt in der Verschluesslung durch eine walze sind mit den Schritten 1 bis 5 gekenzeichnet
		
		//1. Abgleich der EGW mit dem ersten rotor in bezug auf ihre stellung zu einander
		zwischenschritteSpeicher[1] = RefABCEingangAbgl(zwischenschritteSpeicher[0], egw, r1);
		
		//1 walze
		zwischenschritteSpeicher[2] = RingABCAbglEinagng(zwischenschritteSpeicher[1], r1);		//2. Abgleich des "Aueßeren" RefABC mit dem Ring ABC um auf die interne Verdrahtung zu schließen
		zwischenschritteSpeicher[3] = RotorNverRing(zwischenschritteSpeicher[2], r1);	//3. Eigentliche verschluesslung hier wird das RingABC mit dem RotorString "Abgeglichen" das die verdrahtung der Walze representiert
		zwischenschritteSpeicher[4] = RingABCAbglAusgang(zwischenschritteSpeicher[3], r1);				//4. Abgleich des "inneren" RingABC mit dem "Aueßeren" RefABC um fuer den uebertrag auf die richtige stellung zu schlißen ist quasi das gegenstueck zu schritt 2 hier wird diser nur rueckwerts gegangen
		zwischenschritteSpeicher[5] = RefABCEingangAbgl(zwischenschritteSpeicher[4], r1, r2);	// wie schritt 1. nur hier abgleich der RefABCs von Rotor 1 und Rotor 2 um auf ihre stellung zueinder zu schließen und den Buchstaben richtig zu uebertragen
		
		//2 walze
		zwischenschritteSpeicher[6] = RingABCAbglEinagng(zwischenschritteSpeicher[5], r2);
		zwischenschritteSpeicher[7] = RotorNverRing(zwischenschritteSpeicher[6], r2);
		zwischenschritteSpeicher[8] = RingABCAbglAusgang(zwischenschritteSpeicher[7], r2);
		zwischenschritteSpeicher[9] = RefABCEingangAbgl(zwischenschritteSpeicher[8], r2, r3);

		// 3 walze
		zwischenschritteSpeicher[10] = RingABCAbglEinagng(zwischenschritteSpeicher[9], r3);
		zwischenschritteSpeicher[11] = RotorNverRing(zwischenschritteSpeicher[10], r3);
		zwischenschritteSpeicher[12] = RingABCAbglAusgang(zwischenschritteSpeicher[11], r3);
		zwischenschritteSpeicher[13] = RefABCEingangAbgl(zwischenschritteSpeicher[12], r3.getRefABC(), ABC);
		
		
		//Umkerhwalze
		zwischenschritteSpeicher[14] = UKWver(zwischenschritteSpeicher[13]);
		
		zwischenschritteSpeicher[15] = RefABCEingangAbgl(zwischenschritteSpeicher[14], ABC, r3.getRefABC());
		
		
		//rueckweg
		
		//walze 3
		zwischenschritteSpeicher[16] = RingABCAbglEinagng(zwischenschritteSpeicher[15], r3);
		zwischenschritteSpeicher[17] = RotorRueckVer(zwischenschritteSpeicher[16], r3);
		zwischenschritteSpeicher[18] = RingABCAbglAusgang(zwischenschritteSpeicher[17], r3);
		zwischenschritteSpeicher[19] = RefABCEingangAbgl(zwischenschritteSpeicher[18], r3, r2);
		
		//walze 2
		zwischenschritteSpeicher[20] = RingABCAbglEinagng(zwischenschritteSpeicher[19], r2);
		zwischenschritteSpeicher[21] = RotorRueckVer(zwischenschritteSpeicher[20], r2);
		zwischenschritteSpeicher[22] = RingABCAbglAusgang(zwischenschritteSpeicher[21], r2);
		zwischenschritteSpeicher[23] = RefABCEingangAbgl(zwischenschritteSpeicher[22], r2, r1);
		
		//walze 1
		zwischenschritteSpeicher[24] = RingABCAbglEinagng(zwischenschritteSpeicher[23], r1);
		zwischenschritteSpeicher[25] = RotorRueckVer(zwischenschritteSpeicher[24], r1);
		zwischenschritteSpeicher[26] = RingABCAbglAusgang(zwischenschritteSpeicher[25], r1);
		zwischenschritteSpeicher[27] = RefABCEingangAbgl(zwischenschritteSpeicher[26], r1, egw);
		
		zwischenschritteSpeicher[28] = SteckBrettVer(zwischenschritteSpeicher[27]);
		
		return zwischenschritteSpeicher;
	}
	
	/**
	 * Funktion zum Fortschalten der Rotoren.
	 */
	private void RotorFortschaltung() {
		
		r1.rotorDrehung();		//erste walze wird immer fortgeschalten
		
		boolean istRotor2Fortzuschalten = false;
		boolean istRotor3Fortzuschalten = false;
		
		if(PruefenRotorfortschaltung(r1, 1)) {
			istRotor2Fortzuschalten = true;
		}
		
		if(PruefenRotorfortschaltung(r2, 0)) {
			istRotor2Fortzuschalten = true;
			istRotor3Fortzuschalten = true;
		}
		
		if(istRotor2Fortzuschalten && istRotor3Fortzuschalten) {
			r2.rotorDrehung();
			r3.rotorDrehung();
		} else if (istRotor2Fortzuschalten) {
			r2.rotorDrehung();
		}
	}
	
	/**
	 * Fast den Verschluesselungsschritt fuer einen Rotor auf dem hinweg zur UKW
	 * in eine Funktion zusammen
	 * 
	 * @param rvon Von welchem Rotor aus
	 * @param rnach Nach welchem Rotor
	 * @param Eingabe Buchstabe der zu verschluesseln ist
	 * @return Der Verschluesselte Buchstabe
	 */
	private char WalzenVerHin(Rotor rvon,Rotor rnach, char Eingabe) {
		
		Eingabe = RingABCAbglEinagng(Eingabe, rvon);		
		Eingabe = RotorNverRing(Eingabe, rvon);			
		Eingabe = RingABCAbglAusgang(Eingabe, rvon);			
		Eingabe = RefABCEingangAbgl(Eingabe, rvon, rnach);
		
		return Eingabe;
	}
	
	/**
	 * Fast den Verschluesselungsschritt fuer einen Rotor auf dem rueckweg zur EWG
	 * in eine Funktion zusammen
	 * 
	 * @param rvon Von welchem Rotor aus
	 * @param rnach Nach welchem Rotor
	 * @param Eingabe Buchstabe der zu verschlueesseln ist
	 * @return Der Verschluesselte Buchstabe
	 */
	private char WalzenVerRueck(Rotor rvon,Rotor rnach, char Eingabe) {
		Eingabe = RingABCAbglEinagng(Eingabe, rvon);
		Eingabe = RotorRueckVer(Eingabe, rvon);
		Eingabe = RingABCAbglAusgang(Eingabe, rvon);
		Eingabe = RefABCEingangAbgl(Eingabe, rvon, rnach);
		
		return Eingabe;
	}
	
	/**
	 * Verschluesselung der Gesteckten Buchstaben
	 * hierzu wird das SteckABC mit dem normalen ABC abgeglichen
	 * 
	 * @param Eingabe Abzugleichender Buchstabe
	 * @return	verschluesselter Buchstabe 
	 */
	private char SteckBrettVer(char Eingabe) {
		
		String steckABC = Steckbrett.getSteckABC();
		
		int indexdesBuchst1 = ABC.indexOf(Eingabe);
		char Verschluesslet = steckABC.charAt(indexdesBuchst1);
		
		return Verschluesslet;
	}
	
	/**
	 * Eigentliche Verschluesselung hier wird der RotorString mit dem RingABC abgeglichen 
	 * was die eigentliche Verdrahtung wiedergibt
	 * zur verschluesselung wird der zu verschluesselnde Buchstabe (Eingabe) im RingABC gesucht und 
	 * dann seien position (indexdesBuchst1) ermittelt 
	 * an der gleichen position (indexdesBuchst1) im RotorString befindet sich dann der verschluesslte Buchstabe 
	 * bzw. dies zeigt auch wie die Walze in echt verdrahtet waere
	 * 
	 * @param Eingabe zu Verschluesselter Buchstabe
	 * @param r der Rotor um den es sich Handelt (von dem der RotorString und RingABC geholt wird)
	 * @return der Verschluesselte Buchstabe
	 */
	private char RotorNverRing(char Eingabe, Rotor r) {
		String RotorString1 = r.getRotorString();
		String RotorStringRing = r.getRingABC();
		
		int indexdesBuchst1 = RotorStringRing.indexOf(Eingabe);
		char Verschluesslet = RotorString1.charAt(indexdesBuchst1);
		
		return Verschluesslet;
	}
	
	/**
	 * Ist die Verschlueeselng des Buchstabens durch die Rotorverdrahtung nur rueckwerts hierbei wird der Buchstabe im 
	 * RotorString gesucht und dann der Buchstabe am gleichen Index im RingABC 
	 * zurueck gegeben spiegelt ebenfalls die Verdrahtung des Rotors wieder.
	 * 
	 * @param Eingabe zu verschluesselder Buchstabe 
	 * @param r Rotor um den es sich handelt von dem die Parameter geholt werden sollen
	 * @return abgeglichener buchstabe 
	 */
	private char RotorRueckVer(char Eingabe, Rotor r) {
		String RotorString = r.getRotorString();
		String RotorStringRing = r.getRingABC();
		
		int indexdesBuchst = RotorString.indexOf(Eingabe);
		char RueckVerschluessetlt = RotorStringRing.charAt(indexdesBuchst);
		
		return RueckVerschluessetlt;
	}
	
	/**
	 * Gleicht die RefABCs zweier Rotoren ab fuer die uebergabe des Buchstabens 
	 * weil die Stellung der Rotoren zu einender verschieden sein kann kann der Ausgang der 
	 * einen walze mit einem anderen Buchstaben an der anderen walze gegenueber liegen
	 * in dem falle das die Referenz Alphabete der Rotoren zueinander verschoben sind
	 * wird der Buchstabe im RefABC des anderen Rotor gesucht und getauscht
	 * 
	 * @param Eingabe Abzugleichender Buchstabe
	 * @param rvon von welchem Rotor 
	 * @param rzu zu welchem Rotor
	 * @return Abgeglichener Buchstabe
	 */
	private char RefABCEingangAbgl(char Eingabe, Rotor rvon, Rotor rzu) {
		String RotorREFABCStringvon = rvon.getRefABC();
		String RotorREFABCStringzu = rzu.getRefABC();
		
		int indexdesBuchst = RotorREFABCStringvon.indexOf(Eingabe);
		char Verschluesslet = RotorREFABCStringzu.charAt(indexdesBuchst);
		return Verschluesslet;
	}
	
	/**
	 * Gleicht die RefABCs zweier Rotoren ab fuer die uebergabe des Buchstabens 
	 * weil die Stellung der Rotoren zu einender verschieden sein kann kann der Ausgang 
	 * der einen walze mit einem anderen Buchstaben an der anderen walze gegenueber liegen
	 * 
	 * Gleich Variante wie (RefABCEingangAbgl) dies nutzt statt der Rotoruebergabe gleich die Strings 
	 * wird nur fuer UKW benoetigt und genutzt
	 * 
	 * @param Eingabe Abzugleichender Buchstabe
	 * @param von Von welchem String abgeglichen werden soll (index geber)
	 * @param zu Zu welchem String abgeglichen werden soll (Buchstabe am index)
	 * @return Abgeglichener Buchstabe
	 */
	private char RefABCEingangAbgl(char Eingabe, String von, String zu) {
		
		int indexdesBuchst = von.indexOf(Eingabe);
		char Verschluesslet = zu.charAt(indexdesBuchst);
		return Verschluesslet;
	}
	
	/**
	 * Gleicht das RingABC mit dem RefABC ab dabei wird der Buchstabe in RingABC gesucht und der Buchstabe am gleichen index 
	 * im RefABC wieder gegeben von RingABC zu RefABC
	 * 
	 * wird Benoetigt um von der Internen Verdrahtung auf die externen Kontakte der Rotoren zu referenzieren
	 * 
	 * Umgekehrte Methode zu RingABCAbglEinagang()
	 * 
	 * @param Eingabe Abzugleichender Buchstabe
	 * @param r Rotor um den es sich handelt von dem die Parameter geholt werden sollen
	 * @return abgeglichener Buchstabe 
	 */
	private char RingABCAbglAusgang(char Eingabe, Rotor r) {
		String RotorStringRing = r.getRingABC();
		String RotorStringRef = r.getRefABC();
		
		int indexdesBuchst1 = RotorStringRing.indexOf(Eingabe);
		char Verschluesslet = RotorStringRef.charAt(indexdesBuchst1);
		
		return Verschluesslet;
	}
	
	/**
	 * Gleicht das RefABC mit dem RingABC ab  dabei wird der Buchstabe in RefABC gesucht und der Buchstabe am gleichen index 
	 * im RingABC wieder gegeben von RefABC zu RingABC
	 * 
	 * wird Benoetigt um von den externen Kontakten der Rotoren auf die Interne Verdrahtung zu referenzieren  
	 * 
	 * Umgekehrte Methode zu RingAbglAusgang()
	 * 
	 * @param Eingabe Eingabe Abzugleichender Buchstabe
	 * @param r Rotor um den es sich handelt von dem die Parameter geholt werden sollen
	 * @return abgeglichener Buchstabe 
	 */
	private char RingABCAbglEinagng(char Eingabe, Rotor r) {
		String RotorStringRing = r.getRingABC();
		String RotorStringRef = r.getRefABC();
		
		int indexdesBuchst1 = RotorStringRef.indexOf(Eingabe);
		char Verschluesslet = RotorStringRing.charAt(indexdesBuchst1);
		
		return Verschluesslet;
	}
			
	/**
	 * Methode fuer die Umkehrwalze zum den Buchstaben "zurueck" zu schicken 
	 * Dabei wird der UKW string mit dem normalen ABC abgeglichen dies gibt die Verdrahtung der UKW wieder 
	 * 
	 * @param Eingabe zu verschluesselder Buchstabe
	 * @return der verschluesselte Buchstabe der quasi umgekehrt wurde 
	 */
	private char UKWver(char Eingabe) {
		
		String UKWstring = UKW.getUKWstring();
		int indexBuchstUKW = ABC.indexOf(Eingabe);
		
		char VerschluessletUKW = UKWstring.charAt(indexBuchstUKW);
		
		return VerschluessletUKW;
	}
	
	/**
	 * Prueft ob ein Rotor sich weiter dreht
	 * 
	 * @param r Rotor um den es sich handelt der die Fortschaltkerbe besitzt
	 * @param istRotorEins ist fuer den ersten Rotor da dieser eine Anomalie zu besitzt wird zur fortschaltkerbe eins dazu addiert
	 * @return true oder false fuer ein weiter drehen oder nicht
	 */
	private boolean PruefenRotorfortschaltung(Rotor r,int istRotorEins) {
		int rotorstellung = r.getRotorIntStellung();
		int[] fortschaltkerben = r.getVortschaltkerbe();
		boolean istfortzuschalten = false;
		
		for(int i=0; i<fortschaltkerben.length; i++) {
			if(rotorstellung==fortschaltkerben[i]+istRotorEins) {
				istfortzuschalten = true;
				break;
				
			}
			
			//sonder regelung fuer die rotoren die die kerbe an der lezten stelle besitzen und als erste eingesezt werden 
			if((rotorstellung==0) && (fortschaltkerben[i]==25) && (istRotorEins == 1)) {
				istfortzuschalten = true;
				break;
			}
		}
		return istfortzuschalten;
	}
	
	/**
	 * @return gibt den ersten Rotor zurueck
	 */
	public Rotor getRotor1() {
		return r1;
	}
	
	/**
	 * @return gibt den zweiten Rotor zurueck
	 */
	public Rotor getRotor2() {
		return r2;
	}
	
	/**
	 * @return gibt den dritten Rotor zurueck
	 */
	public Rotor getRotor3() {
		return r3;
	}
	
	/**
	 * @return gibt den vierten Rotor zurueck
	 */
	public Rotor getRotor4() {
		return r4;
	}
}
