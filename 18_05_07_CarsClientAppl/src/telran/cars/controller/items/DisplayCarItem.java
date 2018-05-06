package telran.cars.controller.items;

import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayCarItem extends CarsItem {

	public DisplayCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about car";
	}

	@Override
	public void action() {
		String carNumber=inputOutput.getString("Enter car's registration number");
		Car car=company.getCar(carNumber);
		if(car==null){
			inputOutput.displayLine("No car with number "+carNumber);
		}
		else
			inputOutput.displayLine(car);

	}

}
