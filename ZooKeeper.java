package zookeeper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
* @author
*
*/
public class ZooKeeper {
private static final String ANIMALS_FILE = "animals.txt"; // name of animals information file
private static final String HABITATS_FILE = "habitats.txt"; // name of the habitats information file

String zooKeeper = ""; // what thing to monitor
String monitorAnimal = ""; // monitor animal
String monitorHabitat = ""; // monitor habitat
String exit = "Exit"; // to match with exit stringS

/**
* to show the option and get the input from the user through console by
* using scanner object
*/
public void run() {
Scanner scnr = new Scanner(System.in); //scanner object is initialized to get the user inputs
showUserOptions();
zooKeeper = scnr.nextLine();

// the entered text will be converted to uppercase to check whether entered option is monitor animals or not
if (zooKeeper.trim().equalsIgnoreCase("Monitor Animal")) {
System.out.println("Which Animal you would like to monitor?");
// Animal details
monitorAnimal = scnr.next();
showInfoForAnimals(monitorAnimal.toUpperCase());
}

// the entered text will be converted to uppercase to check whether entered option is monitor habitat or not
if (zooKeeper.trim().equalsIgnoreCase("Monitor Habitat")) {
System.out.println("Which Habitat you would like to monitor?");
// habitats details
monitorHabitat = scnr.next();
showInfoForHabitats(monitorHabitat.toUpperCase());
}

// to exit the operation user can't give any option once the System.exit(0) is called
if (zooKeeper.trim().equalsIgnoreCase(exit)) {
System.exit(0);
}
}

/**
* to display the menu
*/
public void showUserOptions() {
System.out.println("\nWhat would you like to do: Monitor Animal? Monitor Habitat?\n\nType 'Exit' to exit program");
}

/**
* @param selectedHabitat will be passed as a paramter
*/
public void showInfoForHabitats(String selectedHabitat) {
File file = new File(HABITATS_FILE);
BufferedReader br = null;
String line = "";
int lines = 0;
try {
// to read the contents from the file
br = new BufferedReader(new FileReader(file));
while ((line = br.readLine()) != null) {

if (lines == 1 || lines == 2 || lines == 3) {//this condition will display the next three lines from the file when the habitat name get matched
lines++;
if (line.startsWith("*")) {//if the warning message is found replaces the asterisks from the warning message
alertUser(line.replaceAll("\\*", ""));
}
System.out.println(line);
}

if (lines >= 4) {
lines = 0;
run();
}
  
//trim method is used to remove the space
if (line.trim().equalsIgnoreCase("Habitat - " + selectedHabitat)) { // to check whether the entered animal matches with the habitat name present in the file
lines++;
}

}
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
} finally {
if (br != null) {
try {
br.close(); //br closed
} catch (IOException e) {
e.printStackTrace();
}
}
}
}

/**
* @param selectedAnimal - animal name will be passed as a parameter
*/
public void showInfoForAnimals(String selectedAnimal) {
File file = new File(ANIMALS_FILE);
BufferedReader br = null;
String line = "";
int lines = 0;
try {
// to read the contents from the file
br = new BufferedReader(new FileReader(file));
while ((line = br.readLine()) != null) {

if (lines == 1 || lines == 2 || lines == 3 || lines == 4) { //this condition will display the next four lines from the file when the animal name get matched
lines++;
  
if (line.startsWith("*")) { //if the warning message is found replaces the asterisks from the warning message
alertUser(line.replaceAll("\\*", ""));
}
System.out.println(line);
}

if (lines >= 5) {
lines = 0;
run();
}

if (line.trim().equalsIgnoreCase("ANIMAL - " + selectedAnimal)) { // to check whether the entered animal matches with the animal name present in the file
lines++;
}

}
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
} finally {
if (br != null) {
try {
br.close(); // br closed
} catch (IOException e) {
e.printStackTrace();
}
}
}
}

  

/**
* @param message - to pass the warning message from the file
* method to alert the user if any warning is found in the file
*/
public void alertUser(String message) {
//JDialog is used to show the dialogue on the top of the console window once the warning is noticed from the file
final JDialog dialog = new JDialog();
dialog.setAlwaysOnTop(true);
//Joption pane is used
JOptionPane.showMessageDialog(dialog, message, "WARNING", JOptionPane.INFORMATION_MESSAGE);
}

/**
* @param args
*/
public static void main(String[] args) {
ZooKeeper zooKeeper = new ZooKeeper();
zooKeeper.run();
// Prompt user to describe details about the habitat
}
}