package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlineService;
	
	
	public ContractService() {
		
	}
	
	public ContractService(OnlinePaymentService onlineService) {
		
		this.onlineService = onlineService;
	}
	
	 public void processContract(Contract contract, int months) {
		
		double basicValue = contract.getTotalValue()/months;
		List<Installment> installments = new ArrayList<>();
		for(int i = 1; i<=months;i++) {
		 LocalDate date = contract.getDate().plusMonths(i);
		 double interest = onlineService.interest(basicValue, i);
		 double fee = onlineService.paymentFee(basicValue + interest);
		 double total = basicValue + interest + fee;
		 contract.getInstallments().add(new Installment(date,total));
			
		}
		
          for(Installment c : installments) {
			 
			 System.out.println(c.getDate() + " - " + c.getAmount());
		 }
		
	
	}
	 
	 
 
}
