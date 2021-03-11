package CashMachine;

import CashMachine.exсeption.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Objects;

public class ConsoleHelper {

    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if("exit".equals(str.toLowerCase(Locale.ROOT))){
                throw new InterruptOperationException();
            }else {
                return str;
            }
        }catch (IOException ignored){}
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        while (true){
            ConsoleHelper.writeMessage("Please, enter currency code, for example USD");
            String currencyCode = ConsoleHelper.readString();
            if(currencyCode == null || currencyCode.trim().length() != 3){
                ConsoleHelper.writeMessage("Please, re - enter");
            }else {
                return currencyCode.toUpperCase(Locale.ROOT);
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(String.format("Please enter the denomination and the number of banknotes. For example '100 6' means 600 %s", currencyCode));
            String str = ConsoleHelper.readString();
            String[] split = null;
            if (str == null || (split = str.split(" ")).length != 2) {
                ConsoleHelper.writeMessage("Please enter valid data.");
            } else {
                try {
                    if (Integer.parseInt(split[0]) <= 0 || Integer.parseInt(split[1]) <= 0)
                        ConsoleHelper.writeMessage("Please enter valid data.");
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage("Please enter valid data.");
                    continue;
                }
                return split;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        while (true) {
            ConsoleHelper.writeMessage("Please choose an CashMachine.operation desired or type 'EXIT' for exiting");
            ConsoleHelper.writeMessage("\t 1 - INFO");
            ConsoleHelper.writeMessage("\t 2 - DEPOSIT");
            ConsoleHelper.writeMessage("\t 3 - WITHDRAW");
            ConsoleHelper.writeMessage("\t 4 - EXIT");
            Integer i = Integer.parseInt(Objects.requireNonNull(ConsoleHelper.readString()).trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }
}
