package com.mancala.logic;

import java.io.Serializable;



public class Tablero implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7067096713654563776L;
	private static Integer SEMILLAS= 48-(6*2);
	private static Integer RECEPTACULOS = 14;
	private Boolean turno;
	private Integer[] receptaculos; 

	 public Tablero(){
		 receptaculos = new Integer[RECEPTACULOS];
		 repartir();
		 turno=true;
		
	}

	 public Integer[] getReceptaculos(){
		 return receptaculos.clone();
	 }
	 
	 
	private void repartir() {
		
		//Inicializa las pocisiones en 0
		for(int n=0; n<RECEPTACULOS; n++){
			receptaculos[n]=0;
		}
		
		//Reparte las semillas
		int i=1;
		for (int n=0; n<SEMILLAS;){
			if (i != 0 && i!= RECEPTACULOS/2){
				receptaculos[i]++;
				n++;
			}
			if (i<RECEPTACULOS-1)i++;
			else i=1;
		}	
		
	}
	
	public void mover(Integer pos) throws InvalidMancalaInvalidMoveException, FinalizedGameMancalaException{
		
		if (isFinalizado()) throw new FinalizedGameMancalaException();
		
		if (isValidMove(pos)){
			Integer semillas = receptaculos[pos];
			int i;
			//reparte las fichas
			for (i=1; i<=semillas;i++){
				receptaculos[(pos+i) % RECEPTACULOS]++;
				receptaculos[pos]--;
			}
			int posFinal = ((pos+i-1) % RECEPTACULOS); 
			
			
			//Si no termino en su receptaculo objetivo cede el turno
			if
					( 
				(!((posFinal == 0) && !turno)) 
					&&	
				(!((posFinal == RECEPTACULOS/2 )&& turno))
				){
					//roba semillas
					if (receptaculos[posFinal] ==1 && receptaculos[RECEPTACULOS-posFinal]>0
							//OJO DE QUE LADO QUEDO!
							&& (posFinal== (pos+i-1))
							){
						if (turno) receptaculos[RECEPTACULOS/2]+=receptaculos[RECEPTACULOS-posFinal]+1;
						else receptaculos[0]+=receptaculos[RECEPTACULOS-posFinal]+1;
						receptaculos[RECEPTACULOS-posFinal]=0;
						receptaculos[posFinal]=0;
					}
					turno = !turno;
				}
			
			if (isFinalizado()) throw new FinalizedGameMancalaException();
			
		} else {
			throw new InvalidMancalaInvalidMoveException();
		}
	}

	private boolean isFinalizado() {
		Integer n =0;
		if (turno){
			for (int i=1; i<RECEPTACULOS/2;i++ )
				n+=receptaculos[i];
		}else{
			for (int i=(RECEPTACULOS/2)+1;i<RECEPTACULOS ;i++ )
				n+=receptaculos[i];
		}
		return n==0?true:false;
	}

	private boolean isValidMove(Integer pos) {
		//Existe el casillero?
		if(pos>=RECEPTACULOS) return false; 
		//Existen fichas en ese cassillero?
		else if(receptaculos[pos]<=0) return false;
		//Es un casillero Objetivo?
		else if (pos==0 || pos== RECEPTACULOS/2 ) return false;
		//Es un casillero del jugador con turno?
		else if (turno) return (pos < RECEPTACULOS/2 )? true :false;
		else return (pos > RECEPTACULOS/2 )? true :false;
		
	} 
}
