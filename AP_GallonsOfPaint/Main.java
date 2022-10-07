/*
    Author: Carter Speerschneider
    Date Last Updated: 10/2/2022
    Reason: To use functions that return a single value in a more
    complicated program.
    Description: This program is used to determine how many gallons of
    paint are needed to paint an interior with a surface area that you provide. Combines mulitple rooms together in order to give out the total 
		gallons needed
*/

import java.util.Scanner;

public class Main {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		double length, width, height, initialArea, paintableArea;
		String anotherRoom;
		int doors = 0, windows = 0, gallonsNeeded = 0;
		int totalGallons = 0;

		for (anotherRoom = "yes"; anotherRoom.equals("yes"); anotherRoom = scan.nextLine()) {

			length = getDimensions("length");
			width = getDimensions("width");
			height = getDimensions("height");

			initialArea = calculateInitialArea(length, width, height);

			doors = getDoorsAndWindows("doors");
			doors = roomIsValid(initialArea, doors, windows, "doors");
			windows = getDoorsAndWindows("windows");
			windows = roomIsValid(initialArea, doors, windows, "windows");

			paintableArea = calculatePaintableArea(initialArea, doors, windows);
	
			gallonsNeeded = (int) paintableArea / 400;
			totalGallons += gallonsNeeded;

			displayResults(gallonsNeeded, paintableArea, initialArea, doors, windows);

			System.out.println("would you like to enter another room? (\"yes\" or \"no\")");

		}

		System.out.printf("you'll need %d gallons of paint with all rooms combined%n", totalGallons);

	}
	/*
		method: displayResults(int,double,double,int,int)
		purpose: this method displays the information back to the user using a print statement based on the inputted room information
		precondition: must have the correct arguments: gallonsNeeded, paintableArea, initialArea, doors, and windows which are provided by user.
		postconditon: dislays the results using a print statement. the return type is void.
	*/
	public static void displayResults(int gallonsNeeded, double paintableArea, double initialArea, int doors,
			int windows) {

		System.out.printf(
				"if your room is initially %.2f sq ft and you have %d doors and %d windows, then you'll need %d gallons of paint to paint %.2f sq ft%n",
				initialArea, doors, windows, gallonsNeeded, paintableArea);
	}

	/*
		method: roomIsValid(double, int, int, String)
		purpose: this method is used to check if the amount of doors and windows is valid given the inputted demensions
		precondition: must have method getDoorsAndWindows as well as a private static Scanner scan
		postcondition: this method returns the amount of doors necessary for a correct room after calling methods to get the correct amount from the usr.
	*/

	private static int roomIsValid(double initialArea, int doors, int windows, String component) {

		double num;
		for (num = calculatePaintableArea(initialArea, doors, windows); num < 0; num = calculatePaintableArea(initialArea,
				doors, windows)) {
			System.out.printf(
					"your room is not valid; the amount of %s you entered is impossible with a room of %.2f sq ft.%n %d doors and %d windows have a combined total of %.2f sq ft%n%n",
					component, initialArea, doors, windows, (double) (doors * 21 + windows * 15));
			if (component.equals("doors"))
				doors = getDoorsAndWindows(component);
			else
				windows = getDoorsAndWindows(component);
		}

		return component.equals("doors") ? doors : windows;
	}

	/*
		method: calculatePaintableArea(double, int, int)
		purpose: this method returns the paintable area in sq ft that the user can paint on the walls
		precondition: takes in an initialArea based on the demensions the user providied as well as windows and doors
		postcondition: returns the paintable area by subtracting the areas of the doors and windows from the initialArea
	*/

	private static double calculatePaintableArea(double initialArea, int doors, int windows) {

		return initialArea - (doors * 21 + windows * 15);
	}

	/*
		method: getDimensions(String)
		purpose: a multi-use method; by using the String component, the same method can be used to get length, width and height
		precondition: takes in a String component based on the variable you want to prompt the user for. Must have a private static Scanner scan.
		postcondition: returns the amount entered by the user rounded to the nearest hundredth. 
	*/

	private static double getDimensions(String component) {

		double amount;

		for (amount = -1; amount <= 0; amount = Double.parseDouble(scan.nextLine())) {

			System.out.printf("Please enter the %s in square feet of your room. (must be a positive number)%n", component);
		}

		return Math.round(amount * 100D) / 100D;
	}

	/*
		method: getDoorsAndWindows(String)
		purpose: a multi-use method; by using the String component, the same method can be used to get doors, and windows.
		precondition: takes in a String component based on which variable you want to track, should only be "doors" or "windows". must have a print 
		static Scanner scan.
		postcondition: returns an int representing the amount of doors or windows entered in by the user, loops again if no doors or windows are entered.
	*/

	private static int getDoorsAndWindows(String component) {

		int amount;

		for (amount = -1; amount <= 0; amount = Integer.parseInt(scan.nextLine())){

			System.out.printf("Please enter the amount of %s in your room. (must be positive)%n", component);
			

		} 
		return amount;
	}

	/*
		method: calculateInitialArea(double, double, double)
		purpose: used to get the initialArea of the room before taking into account windows and doors
		precondition: takes in 3 doubles and returns the area of the room based on those 3 doubles (length, width, and height)
		postcndition: returns the initialArea by finding the area of the 4 walls.
	*/

	private static double calculateInitialArea(double length, double width, double height) {

		return 2 * (length * height) + 2 * (width * height);
	}

}
