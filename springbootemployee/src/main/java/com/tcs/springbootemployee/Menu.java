package com.tcs.springbootemployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;

public class Menu {

	public static enum model {
		DEPT, EMPLOYEE, ORG
	}

	public static void start(ApplicationContext context) {
		model selected = null;
		String menuOptions = "'c' - Cancel | 'e' - Employee | 'd' - Department | " + "o - Organization";
		String input = getInput(menuOptions);
		while (!"c".equals(input)) {
			switch (input) {
			case "c":
				selected = null;
				break;
			case "e":
				selected = model.EMPLOYEE;
				break;
			case "d":
				selected = model.DEPT;
				break;
			case "o":
				selected = model.ORG;
				break;
			default:
				selected = null;
				System.out.println("invalid command");
			}
			if (selected != null)
				Operations.start(context, selected);
			System.out.println();
			input = getInput(menuOptions);
		}
	}

	public static int getInputInt(String output) {
		String input = getInput(output);
		int inputInt = -1;
		try {
			inputInt = Integer.valueOf(input);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Number not detected.");
			return getInputInt(output);
		}
		return inputInt;
	}

	public static long getInputLong(String output) {
		String input = getInput(output);
		long inputLong = -1;
		try {
			inputLong = Long.valueOf(input);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Number not detected.");
			return getInputLong(output);
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
			return getInput(output);
		}
		return input;
	}
}
