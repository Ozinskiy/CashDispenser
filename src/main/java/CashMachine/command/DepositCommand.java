package CashMachine.command;


import CashMachine.exсeption.InterruptOperationException;

class DepositCommand implements Command {
     @Override
     public void execute() throws InterruptOperationException {
         CashMachine.ConsoleHelper.writeMessage("Depositing...");
         String currencyCode = CashMachine.ConsoleHelper.askCurrencyCode();
         CashMachine.CurrencyManipulator manipulator = CashMachine.CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

         while (true) {
             String[] digits = CashMachine.ConsoleHelper.getValidTwoDigits(currencyCode);
             try {
                 int denomination = Integer.parseInt(digits[0]);
                 int count = Integer.parseInt(digits[1]);
                 manipulator.addAmount(denomination, count);
                 CashMachine.ConsoleHelper.writeMessage(String.format("%d %s was deposited successfully", (denomination * count), currencyCode));
                 break;
             } catch (NumberFormatException e) {
                 CashMachine.ConsoleHelper.writeMessage("Please specify valid data.");
             }
         }
     }
 }
