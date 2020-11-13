package com.tcs.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

	public static void start() {
		String menuOptions = "'c' - Cancel | 'e' - Employee | 'd' - Department | "
				+ "o - Organization";
		String input = getInput(menuOptions);
		while(!"c".equals(input)) {
			switch(input) {
			case "c":
				break;
			case "e":
				EmployeeMenu.start();
				break;
			case "d":
				DepartmentMenu.start();
				break;
			case "o":
				OrganizationMenu.start();
				break;
			default:
				System.out.println("invalid command");
			}
			System.out.println();
			input = getInput(menuOptions);
		}
	}
	
	public static int getInputInt(String output) {
		String input = getInput(output);
		int inputInt = -1;
		try {
			inputInt = Integer.valueOf(input);
		} catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("Number not detected.");
		}
		return inputInt;
	}
	
	public static long getInputLong(String output) {
		String input = getInput(output);
		long inputLong = -1;
		try {
			inputLong = Long.valueOf(input);
		} catch(NumberFormatException e){
			e.printStackTrace();
			System.out.println("Number not detected.");
		}
		return inputLong;
	}
	
	public static String getInput(String output) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println(output);
		String input = "";
		try {
			input = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}
}
