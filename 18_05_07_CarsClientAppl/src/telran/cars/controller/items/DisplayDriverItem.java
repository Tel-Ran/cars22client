package telran.cars.controller.items;

import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriverItem extends CarsItem {

	public DisplayDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display driver's data";
	}

	@Override
	public void action() {
		long licenseId=inputOutput.getLong("Enter driver's license number");
		Driver driver=company.getDriver(licenseId);
		if(driver==null){
			inputOutput.displayLine("No driver with license: "+licenseId);
		}
		else{
			inputOutput.displayLine(driver);
		}

	}

}
