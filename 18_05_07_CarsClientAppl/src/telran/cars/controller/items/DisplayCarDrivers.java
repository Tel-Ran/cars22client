package telran.cars.controller.items;

import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayCarDrivers extends CarsItem {

	public DisplayCarDrivers(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display drivers having been drived the given car";
	}

	@Override
	public void action() {
		String carNumber=inputOutput.getString("Enter the car number");
		Car car=company.getCar(carNumber);
		if(car==null)
			inputOutput.displayLine(carNumber+" doesn't exist");
		company.getCarDrivers(carNumber).forEach(inputOutput::displayLine);

	}

}
