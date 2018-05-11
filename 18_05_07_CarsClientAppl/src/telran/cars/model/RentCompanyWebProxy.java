package telran.cars.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.ParameterizedTypeReference;
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
		HttpEntity<String> requestEntity=
				new HttpEntity<>(headers);
			ResponseEntity<String>response=restTemplate.exchange
		(url+CarsApiConstants.SAVE, HttpMethod.POST,
				requestEntity, String.class);
			System.out.println(response);//just for save logging
		
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
		HttpEntity<Car> requestEntity=
				new HttpEntity<>(car, headers);
		ResponseEntity<CarsReturnCode> response=restTemplate.exchange
		(url+CarsApiConstants.ADD_CAR, HttpMethod.POST,
				requestEntity, CarsReturnCode.class);
		return response.getBody();
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		HttpEntity<Driver> requestEntity=
				new HttpEntity<>(driver, headers);
		ResponseEntity<CarsReturnCode> response=restTemplate.exchange
		(url+CarsApiConstants.ADD_DRIVER, HttpMethod.POST,
				requestEntity, CarsReturnCode.class);
		return response.getBody();
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
		HttpEntity<String> requestEntity=
				new HttpEntity<>(headers);
		ResponseEntity<Car> response=
			restTemplate.exchange
			(url+CarsApiConstants.GET_CAR+"?"+"carNumber="+carNumber,
				HttpMethod.GET, requestEntity, Car.class)	;
		return response.getBody();
	}

	@Override
	public Driver getDriver(long licenseId) {
		HttpEntity<String> requestEntity=
				new HttpEntity<>(headers);
		ResponseEntity<Driver> response=
			restTemplate.exchange
			(url+CarsApiConstants.GET_DRIVER+"?"+"licenseId="+licenseId,
				HttpMethod.GET, requestEntity, Driver.class)	;
		return response.getBody();
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		RentRecord record=new RentRecord(licenseId, carNumber, rentDate, rentDays);
		HttpEntity<RentRecord> requestEntity=new HttpEntity<RentRecord>(record, headers);
		ResponseEntity<CarsReturnCode> response=restTemplate.exchange
		(url+CarsApiConstants.RENT_CAR, HttpMethod.POST, requestEntity, CarsReturnCode.class);
		return response.getBody();
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, long licenseId, LocalDate returnDate, int gasTankPercent,
			int damages) {
		RentRecord record=new RentRecord(licenseId, carNumber, null, 0);
		record.setDamages(damages);
		record.setReturnDate(returnDate);
		record.setGasTankPercent(gasTankPercent);
		HttpEntity<RentRecord> requestEntity=new HttpEntity<RentRecord>(record, headers);
		ResponseEntity<CarsReturnCode> response=restTemplate.exchange
		(url+CarsApiConstants.RETURN_CAR, HttpMethod.POST, requestEntity, CarsReturnCode.class);
		return response.getBody();
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		HttpEntity<String> requestEntity=new HttpEntity<>(carNumber,headers);
		ResponseEntity<CarsReturnCode> response=
				restTemplate.exchange(url+CarsApiConstants.REMOVE_CAR,
						HttpMethod.POST, requestEntity, CarsReturnCode.class);
		return response.getBody();
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		DateDays dd=new DateDays(currentDate, days);
		HttpEntity<DateDays> requestEntity=new HttpEntity<>(dd,headers);
		ResponseEntity<List<Car>> response=restTemplate.exchange
				(url+CarsApiConstants.CLEAR_CARS, HttpMethod.POST, requestEntity,
						new ParameterizedTypeReference<List<Car>>() {
						});
		return response.getBody();
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<Driver>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_CAR_DRIVERS+"?"+"carNumber="+carNumber, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Driver>>() {
				});
		return response.getBody();
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<Car>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_DRIVER_CARS+"?"+"licenseId="+licenseId, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Car>>() {
				});
		return response.getBody();
	}

	@Override
	public Stream<Car> getAllCars() {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<Car>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_ALL_CARS, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Car>>() {
				});
		return response.getBody().stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<Driver>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_ALL_DRIVERS, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Driver>>() {
				});
		return response.getBody().stream();
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<RentRecord>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_ALL_RECORDS, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<RentRecord>>() {
				});
		return response.getBody().stream();
	}

	@Override
	public List<String> getAllModels() {
		HttpEntity<String> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<List<String>> response=restTemplate.exchange
		(url+CarsApiConstants.GET_ALL_MODELS, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<String>>() {
				});
		return response.getBody();
	}

}
