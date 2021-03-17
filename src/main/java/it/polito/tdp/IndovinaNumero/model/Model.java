package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set<Integer> tentativi;
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso
		if(!this.inGioco)
			throw new IllegalStateException("HAI PERSO IL SEGRETO ERA: " + this.segreto);
		
		if(!tentativoValido(tentativo))
			throw new InvalidParameterException("Devi inserire un numero da 1 a 100 diverso da quelli che hai inserito");
			
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti == (TMAX-1)) {
			this.inGioco = false;
		}
		
		if(tentativo == segreto) {
			this.inGioco = false;
			return 0;
		} else if(tentativo < this.segreto) {
			return -1;
		} else {
			return 1;
		}
	}

	private boolean tentativoValido(int tentativo) {
		//controllo input
		if(tentativo < 1 || tentativo > NMAX)
			return false;
		
		if(tentativi.contains(tentativo))
			return false;
		
		return true;
	}
	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	
}
