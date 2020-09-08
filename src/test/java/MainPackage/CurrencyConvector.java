package MainPackage;

import TheCalculation.Coins;
import TheCoins.ILS;
import TheCoins.USD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConvector {



    private static Coins choice;
    private static double choiceNumber;
    private static ArrayList<Double> listOfConvert = new ArrayList<Double>();
    private static final USD usdCoin = new USD();
    private static final ILS ilsCoin = new ILS();
    private static String inputUserSelectionYN;
    private static boolean stageCoin = false;
    private static boolean stageAmount = false;
    private static String locationFile ="//Users//eilanpony//listFile.txt";





    public static void main(String[] args) {

        System.out.println("Welcome to currency converter");
        runConverter();
        CheckUserInputEndOfRound();



    }

    private static void runConverter() {
        if(!stageCoin)
        { System.out.println("Pleas choose an option (1/2):");
            System.out.println("1. Dollars to Shekels");
            System.out.println("2. Shekels to Dollars");
            System.out.println("Selected Option:");
        }

        Scanner UserInputCoinNumber = new Scanner(System.in);
        if (CheckInputForCoin(UserInputCoinNumber))
        {
            System.out.println("Please enter an amount to convert");
            amountToConvert();
        }
        else {
            System.out.println("Wrong input please insert new selection 1/2 :");
            stageCoin = true;
            stageAmount = false;
            runConverter();

        }

    }

    //this will do the control all the actual converting process
    private static void amountToConvert() {
        Scanner UserInputAmountNumber= new Scanner(System.in);
        if (checkInoutIsANumber(UserInputAmountNumber)){
            runConverterSum();
        }
        else {
            System.out.println("Wrong input please insert Amount to Convert: ");
            amountToConvert();
        }

        }

    // this will check the input for the selection of the coin
    private static boolean CheckInputForCoin(Scanner UserInputCoinNumber) {
        if(checkInoutIsANumber(UserInputCoinNumber))
        {
            return checkInputIS1or2();
        }
        else
            return false;
    }

    //this will check first if its a number then if its 1 or two then what number it is
    // then will set the selection of the coin to convert to
    private static boolean checkInputIS1or2() {
        if (choiceNumber == 1 || choiceNumber == 2)
        {
            if((int) choiceNumber==1) {
                choice = Coins.USD;
            }
            else{
                choice = Coins.ILS;
            }
            return true;
        }
        else {
            return false;
        }


    }

    // this will check if its a number ot not
    private static boolean checkInoutIsANumber(Scanner UserInputCoinNumber) {
        try {
            choiceNumber = UserInputCoinNumber.nextInt();
            return true;
        }
        catch (Exception e) {
          return false;
        }
    }

    //this will sum the converter and insert the result to a list
    private static void runConverterSum() {
        switch (choice) {
            case USD -> {
                double conversionSum = usdCoin.calculate(choiceNumber);
                listOfConvert.add(conversionSum);
                System.out.println("the conversion amount is " + conversionSum + " ILS ");

            }
            default -> {
                double conversionSum = ilsCoin.calculate(choiceNumber);
                listOfConvert.add(conversionSum);
                System.out.println("the conversion amount is " + conversionSum + " USD ");

            }
        }


    }

    //this will run at the end of a process to see if to run it again or not
    private static void CheckUserInputEndOfRound() {
        if(!stageAmount) {
            System.out.println("Do you want to start over Y/N");
        }
        else {
            System.out.println("Wrong input selection Please enter the selection again Y/N");
        }

        Scanner selectedNewRound = new Scanner(System.in);
        if(inputSecondRoundCheckString(selectedNewRound))
       {
           if(checkIfSelectionIsYOrN()){
               if(actionOnUserSelectionIfToContinueOrNot()){
                   stageCoin = false;
                   runConverter();
                   stageAmount = false;

                   CheckUserInputEndOfRound();

               }
               else {
                   showAllRecords();
                   System.out.println("Thanks for using the currency converter");
               }
           }
           else {
               stageAmount = true;
               CheckUserInputEndOfRound();
           }
       }


    }


    //this will show all the record + buffer the record to a file
    private static void showAllRecords() {
        try {
            String valueString;
            int resultNumber;
            File listFile = new File(locationFile);
            BufferedWriter writer = new BufferedWriter(new FileWriter(listFile));
            for(int i =0;i<listOfConvert.size();i++){
                writer.write(String.valueOf(listOfConvert.get(i))+"\r\n");
                valueString=String.valueOf(listOfConvert.get(i));
                resultNumber= i+1;
                System.out.println(String.format("Result number %d is %s",resultNumber,valueString));

            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //this will check if the user select Y or N to continue again
    private static boolean actionOnUserSelectionIfToContinueOrNot() {
        return inputUserSelectionYN.equalsIgnoreCase("y");


    }
    // this will check that the user did insert Y or N
    private static boolean checkIfSelectionIsYOrN() {
        if(inputUserSelectionYN.equalsIgnoreCase("y"))
        {
            return true;
        }
        else return inputUserSelectionYN.equalsIgnoreCase("n");


    }


    // this will check that the user insert string and will insert the string in to string object so we can check it
    private static boolean inputSecondRoundCheckString(Scanner selectedNewRound) {
       try {
           inputUserSelectionYN = selectedNewRound.next();
          return true;

      } catch (Exception e) {  return false; }


    }













}
