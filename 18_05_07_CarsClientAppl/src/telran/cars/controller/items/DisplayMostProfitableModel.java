package telran.cars.controller.items;

import java.util.Map;
import java.util.stream.Collectors;

import telran.cars.dto.Car;
import telran.cars.dto.RentRecord;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostProfitableModel extends CarsItem {

	public DisplayMostProfitableModel(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
		
	}

	@Override
	public String displayedName() {
		return "Displaying data about most profitable model";
	}

	@Override
	public void action() {
		displayMostProfitableModels(company);

	}
	private static void displayMostProfitableModels(IRentCompany rentCompany) {
		Map<String,Double>modelCost=rentCompany.getAllCars()
		.collect(Collectors.groupingBy(Car::getModelName,
				Collectors.summingDouble(c->getCarCost(rentCompany,c))));
		double max=modelCost.values().stream().max(Double::compare)
				.orElse(0.0);
		modelCost.entrySet().stream().filter(x->x.getValue()==max)
		.forEach(System.out::println);
		
	}
	private static double getCarCost(IRentCompany rentCompany,Car c){
		double cost=rentCompany.getAllRecords()
				.filter(r->r.getCarNumber().equals(c.getRegNumber()))
				.collect(Collectors.summingDouble(RentRecord::getCost));
		return cost;
	}


}
