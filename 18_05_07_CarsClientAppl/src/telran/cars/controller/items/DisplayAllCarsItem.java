package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllCarsItem extends CarsItem {

	public DisplayAllCarsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about all cars";
	}

	@Override
	public void action() {
		company.getAllCars().forEach(inputOutput::displayLine);

	}

}
