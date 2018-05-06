package telran.cars.controller.items;

import telran.cars.dto.*;
import telran.cars.model.*;
import telran.view.*;

public class RemoveCarItem extends CarsItem {

	public RemoveCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Removing car";
	}

	@Override
	public void action() {
		String carNumber=inputOutput.getString("Enter car registration number");
		CarsReturnCode code=company.removeCar(carNumber);
		inputOutput.displayLine(code);

	}

}
