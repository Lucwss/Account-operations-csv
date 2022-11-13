package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.enums.ModifierType;
import model.exceptions.BusinessException;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter account data: ");
		System.out.print("Number : ");
		Integer number = input.nextInt();
		System.out.print("Holder : ");
		input.nextLine();
		String holder = input.nextLine();
		System.out.print("Initial Balance : ");
		Double initialBalance = input.nextDouble();
		System.out.print("Withdraw Limit : ");
		Double withdrawLimit = input.nextDouble();
		
		Account acc = new Account(number, holder, initialBalance, withdrawLimit);
		
		System.out.print("Enter the file path to register your account : ");
		input.nextLine();
		String sourceFileStr = input.nextLine();
		
		try {
			
			File sourceFile = new File(sourceFileStr); 
			
			String sourceFolderStr = sourceFile.getPath();
			
			new File(sourceFolderStr + "\\result").mkdir();
			
			String targetFileStr = sourceFolderStr + "\\result\\account.csv";
					
			modifyInFile(acc, targetFileStr, ModifierType.CREATE);
			
			System.out.print("Would you like to depoist ? ");
			Double deposit = input.nextDouble();
			acc.deposit(deposit);
			
			modifyInFile(acc, targetFileStr, ModifierType.UPDATE);
			
			System.out.print("Would you like to withdraw ? ");
			Double withdraw = input.nextDouble();
			acc.withdraw(withdraw);
			
			modifyInFile(acc, targetFileStr, ModifierType.UPDATE);
		} catch (BusinessException e) {
			System.out.println("Account error : " + e.getMessage());
		}
		
		input.close();
	}
	
	public static void modifyInFile(Account acc, String targetFileStr, ModifierType modifierType) {
		String msg = "";
		if(modifierType == ModifierType.CREATE) msg = "CREATED !"; 
		if(modifierType == ModifierType.UPDATE) msg = "UPDATED !"; 
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
			bw.write("Number : " + acc.getNumber() + " , " + "Holder : " + acc.getHolder() + " , " + "Balance : " + acc.getBalance() + " , " + "Withdraw-Limit : " + acc.getWithdrawLimit() );
			System.out.println(targetFileStr + " " + msg);
		}  catch (IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
		}
	}

}
