package com.wipro.eb.service;

import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.*;

public class ConnectionService {
public boolean validate(int currentReading, int previousReading,String type)throws InvalidReadingException, InvalidConnectionException {
	if((currentReading<previousReading)||(currentReading<0)||(previousReading<0)) {
		try {
			throw new InvalidReadingException();
		} catch (InvalidReadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if((type.equals("Domestic")==false)&&(type.equals("Commercial")==false)) {
		try {
			throw new InvalidConnectionException();
		}	
		catch(InvalidConnectionException e1) {
			e1.printStackTrace();
		}
	}
	/*if(!type.equals("Commercial")) {
		try {
			throw new InvalidConnectionException();
		}	
		catch(InvalidConnectionException e1) {
			e1.printStackTrace();
		}
	}*/
	return true;
}
public float calculateBillAmt(int currentReading,int previousReading, String type) {
	float billl=0.0f;
	try {
		boolean valid=validate(currentReading,previousReading,type);
		if(valid==true) {
			if(type.equals("Commercial")) {
				float slabs[]= {5.2f,6.8f,8.3f};
				Commercial c=new Commercial(currentReading,previousReading,slabs);
				billl= c.computeBill();
			}
			else if(type.equals("Domestic")) {
				float slabs[]= {2.3f,4.2f,5.5f};
				Domestic d=new Domestic(currentReading,previousReading,slabs);
				billl= d.computeBill();
			}
		}
	}
	catch(InvalidConnectionException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		return -2;
	}
	catch(InvalidReadingException e) {
		return -1;
	}
	return billl;
}
public String generateBill(int currentReading,int previousReading, String type) {
	float finBill=calculateBillAmt(currentReading,previousReading,type);
	String str="";
	if(finBill>=0)
		str+="Amount to be paid:"+finBill;
	else if(finBill==-1)
		str+="Incorrect Reading";
	else if(finBill==-2)
		str+="Invalid Connection Type";
	return str;
	
}
}