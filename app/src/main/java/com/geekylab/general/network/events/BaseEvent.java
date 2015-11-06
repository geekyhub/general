/**
 * 
 */
package com.geekylab.general.network.events;



public class BaseEvent{
	private int data;
	public BaseEvent(int data){
		this.data = data;
	}
	public int getData(){
		return data;
	}
}