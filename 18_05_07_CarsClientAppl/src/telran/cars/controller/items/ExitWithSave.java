package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ExitWithSave extends CarsItem {

	public ExitWithSave(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Exit with save";
	}

	@Override
	public void action() {
		company.save();

	}
	@Override
	public boolean isExit(){
		return true;
	}

}
