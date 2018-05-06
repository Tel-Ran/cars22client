package telran.cars.controller.items;

import java.util.Map;
import java.util.stream.Collectors;

import telran.cars.dto.Car;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostPopularColors extends CarsItem {

	public DisplayMostPopularColors(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Displaying most popular colors";
	}

	@Override
	public void action() {
		displayMostPopularColor(company);

	}
	private static void displayMostPopularColor(IRentCompany rentCompany) {
		Map<String,Long> colorCount=
		rentCompany.getAllCars()
		.collect(Collectors.groupingBy(Car::getColor,
				Collectors.counting()));
		long maxCount=colorCount.values().stream()
				.max(Long::compare).orElse(0L);
		colorCount.entrySet().stream()
		.filter(x->x.getValue()==maxCount)
		.forEach(System.out::println);
		
	}

}
