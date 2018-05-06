package telran.cars.controller.items;

import java.util.Arrays;
import java.util.List;

import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddDriverItem extends CarsItem {

	public AddDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Adding new driver";
	}

	@Override
	public void action() {
		long licenseId=inputOutput.getLong("Enter license ID");
		String name=inputOutput.getString("Enter driver's name");
		int birthYear=inputOutput.getInteger("Enter driver's birth year",1940,2000);
		String phone=getPhone();
		Driver driver=new Driver(licenseId,
				name, birthYear, phone);
		CarsReturnCode code=company.addDriver(driver);
		inputOutput.displayLine(code);

	}

	private String getPhone() {
		String prefix=inputOutput.getString("Enter phone prefix",
				getPossiblePrefixes());
		String number=Integer.toString
				(inputOutput.getInteger("Enter phone number", 1000000, 9999999));
		return prefix+"-"+number;
	}

	private List<String> getPossiblePrefixes() {
		
		return Arrays.asList("08","03","04","02","050","052",
				"053","054","055","056","057","058");
	}

}
