package com.mancala.logic;

public class Player implements Playable{
	
	String name;
	
	
	//inyeccion de dependecia
	Logic logicMotor= null;

	public Logic getLogicMotor() {
		return logicMotor;
	}

	public void setLogicMotor(Logic logicMotor) {
		this.logicMotor = logicMotor;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	
}
