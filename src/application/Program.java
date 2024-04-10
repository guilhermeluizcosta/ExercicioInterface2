package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre com os dados do contrato:");
		System.out.print("Numero: ");
		int contractNumber = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double contractValue = sc.nextDouble();
		System.out.print("Numero de parcelas: ");
		int installments = sc.nextInt();
		
		Contract obj = new Contract(contractNumber, date, contractValue);
		
		
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(obj, installments);
		
		System.out.println("Parcelas:");
		for (Installment installment : obj.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
		
	}

}
