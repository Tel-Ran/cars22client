package telran.cars.controller.items;

import java.util.ArrayList;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class StatistMenuItem extends MenuItem {

	public StatistMenuItem(InputOutput inputOutput, IRentCompany company, ArrayList<Item> items) {
		super(inputOutput, company, items);
	}

	@Override
	public String displayedName() {
		return "Statistics";
	}

}
