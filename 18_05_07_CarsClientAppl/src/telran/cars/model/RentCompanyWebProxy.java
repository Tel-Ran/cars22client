package telran.cars.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.cars.dto.*;


public class RentCompanyWebProxy extends AbstractRentCompany{
RestTemplate restTemplate=new RestTemplate();
String url;
HttpHeaders headers=new HttpHeaders();

	public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public HttpHeaders getHeaders() {
	return headers;
}

public void setHeaders(HttpHeaders headers) {
	this.headers = headers;
}

	public RentCompanyWebProxy(String url) {
	super();
	this.url = url;
}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		HttpEntity<Model> requestEntity=
				new HttpEntity<Model>(model, headers);
		ResponseEntity<CarsReturnCode> response=restTemplate.exchange
		(url+CarsApiConstants.ADD_CAR_MODEL, HttpMethod.POST,
				requestEntity, CarsReturnCode.class);
		return response.getBody();
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getModel(String modelName) {
		HttpEntity<String> requestEntity=
				new HttpEntity<>(headers);
		ResponseEntity<Model> response=
			restTemplate.exchange
			(url+CarsApiConstants.GET_MODEL+"?"+"modelName="+modelName,
				HttpMethod.GET, requestEntity, Model.class)	;
		return response.getBody();
	}

	@Override
	public Car getCar(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver getDriver(long licenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, long licenseId, LocalDate returnDate, int gasTankPercent,
			int damages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

}
