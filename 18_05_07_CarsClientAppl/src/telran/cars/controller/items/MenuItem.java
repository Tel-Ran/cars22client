package telran.cars.controller.items;

import java.util.ArrayList;

import telran.cars.model.IRentCompany;
import telran.view.*;

public abstract class MenuItem extends CarsItem {
ArrayList<Item> items;
	

	public MenuItem(InputOutput inputOutput, IRentCompany company, ArrayList<Item> items) {
	super(inputOutput, company);
	this.items = items;
}

	

	@Override
	public void action() {
		Menu menu=new MenuWithExit(items, inputOutput);
		menu.runMenu();

	}

}
