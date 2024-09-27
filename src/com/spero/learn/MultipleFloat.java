package com.spero.learn;

import java.math.BigDecimal;

public class MultipleFloat {
	
	public static void main(String[] args) {
		float i = 0.59f;
		BigDecimal value = new BigDecimal(""+i);
		BigDecimal multiplier = new BigDecimal("100");
        BigDecimal result = value.multiply(multiplier);
		i = i * 100;
		System.out.println("Multiple value::"+i);
		System.out.println("Multiple with big decimal::"+result);
	}

}
