package telran.cars.controller.items;

import java.time.LocalDate;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ClearCarsItem extends CarsItem {

	public ClearCarsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Clear cars";
	}

	@Override
	public void action() {
		LocalDate currentDate=inputOutput.getDate("Enter current date", "dd/MM/yyyy");
		int days=inputOutput.getInteger("Enter number of days since the last renting");
		inputOutput.displayLine("List of cars that has been removed:");
		company.clear(currentDate, days).forEach(inputOutput::displayLine);
		

	}

}
