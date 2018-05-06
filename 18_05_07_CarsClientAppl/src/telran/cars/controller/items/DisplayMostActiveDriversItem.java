package telran.cars.controller.items;

import java.util.Map;
import java.util.stream.Collectors;

import telran.cars.dto.RentRecord;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostActiveDriversItem extends CarsItem {

	public DisplayMostActiveDriversItem
	(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Displaying data about most active drivers";
	}

	@Override
	public void action() {
		displayMostActiveDriver(company);
	}
	private static void displayMostActiveDriver(IRentCompany rentCompany) {
		Map<Long,Long> driverOccurrences=
		rentCompany.getAllRecords().collect(Collectors
				.groupingBy(RentRecord::getLicenseId,
				Collectors.counting()));
		long maxOccurrences=driverOccurrences.values().stream()
				.max(Long::compare).orElse(0L);
		driverOccurrences.entrySet().stream()
		.filter(x->x.getValue()==maxOccurrences)
		.forEach(x->System.out.println(rentCompany.getDriver(x.getKey())));
		
	}
}
