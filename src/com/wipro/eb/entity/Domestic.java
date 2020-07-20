package com.wipro.eb.entity;

public class Domestic extends Connection {
	public Domestic(int currentReading, int previousReading, float slabs[]) {
		super(currentReading,previousReading,slabs);
	}
	public float computeBill() {
		float bill=0f;
		int unitsConsumed=currentReading-previousReading;
		if(unitsConsumed>100) {
			bill=(float)(((50*slabs[0])+(50*slabs[1]))+((unitsConsumed-100)*slabs[2]));
		}
		else if(unitsConsumed<=100&&unitsConsumed>50) {
			bill=(float)((50*slabs[0])+((unitsConsumed-50)*slabs[1]));
		}
		else if(unitsConsumed<=50) {
			bill=(float)(unitsConsumed*slabs[0]);
		}
		return bill;
	}
}
