package telran.cars.controller.items;

import java.util.ArrayList;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class ClearkMenuItem extends MenuItem {

	public ClearkMenuItem(InputOutput inputOutput, IRentCompany company, ArrayList<Item> items) {
		super(inputOutput, company, items);
	}

	@Override
	public String displayedName() {
		return "Clerk";
	}

}
