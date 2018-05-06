package telran.cars.controller.items;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import telran.cars.dto.Car;
import telran.cars.dto.Driver;
import telran.cars.dto.RentRecord;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostPopularModelsItem extends CarsItem {

	private static final int MIN_AGE = 18;
	private static final int MAX_AGE = 78;
	public DisplayMostPopularModelsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about most popular models";
	}

	@Override
	public void action() {
		
		int ageFrom=inputOutput.getInteger("Enter age from", MIN_AGE, MAX_AGE);
		int ageTo=inputOutput.getInteger("Enter age to", ageFrom, MAX_AGE);
		Map<String,Long> modelsCounts=
				company.getAllCars()
				.collect(Collectors.groupingBy(Car::getModelName,
						Collectors.summingLong(c->getCountAge(company,c,
								ageFrom,ageTo))));
		
				long maxCount=modelsCounts.values().stream()
						.max(Long::compare).orElse(0L);
				modelsCounts.entrySet().stream()
				.filter(x->x.getValue()==maxCount)
				.forEach(System.out::println);

	}
	private 
           Long getCountAge(IRentCompany rentCompany,
			Car c, int ageFrom, int ageTo) {
		Long res=rentCompany.getAllRecords()
				.filter(r->filterCountAge(rentCompany,r,ageFrom,ageTo,c))
				.count();
		return res;
	}
	private static boolean filterCountAge(IRentCompany rentCompany,
			RentRecord r, int ageFrom, int ageTo, Car c) {
		if(!r.getCarNumber().equals(c.getRegNumber()))
			return false;
		int yearTo=getBirthYear(ageFrom);
		int yearFrom=getBirthYear(ageTo);
		Driver driver=rentCompany.getDriver(r.getLicenseId());
		int birthYear=driver.getBirthYear();
		return birthYear>=yearFrom && birthYear<=yearTo;
	}
	private static int getBirthYear(int age) {
		return LocalDate.now().minusYears(age).getYear();
	}

}
