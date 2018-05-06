package telran.cars.controller.items;

import java.util.List;

import telran.cars.dto.Car;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriverCars extends CarsItem {

	public DisplayDriverCars(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all cars data the given driver has been driving";
	}

	@Override
	public void action() {
		long licenseId=inputOutput.getLong("Enter license Id");
		Driver driver=company.getDriver(licenseId);
		if(driver==null){
			inputOutput.displayLine(String.format("Driver with license id %d "
					+ "doesn't exist" , licenseId));
			
		}
		else {
			List<Car> cars=company.getDriverCars(licenseId);
			cars.forEach(inputOutput::displayLine);
		}

	}

}
