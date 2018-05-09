package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayModelItem extends CarsItem {

	public DisplayModelItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about model";
	}

	@Override
	public void action() {
		String modelName=inputOutput.getString("Enter model name");
		inputOutput.displayLine(company.getModel(modelName));

	}

}
