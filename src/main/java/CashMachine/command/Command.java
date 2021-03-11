package CashMachine.command;

import CashMachine.exсeption.InterruptOperationException;

interface Command {
     void execute() throws InterruptOperationException;
}
