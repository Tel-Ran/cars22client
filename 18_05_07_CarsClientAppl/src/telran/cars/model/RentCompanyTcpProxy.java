package telran.cars.model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import telran.cars.dto.*;
import static telran.cars.dto.CarsApiConstants.*;
import java.io.*;
import java.net.*;
@SuppressWarnings("serial")
public class RentCompanyTcpProxy extends AbstractRentCompany {
BufferedReader reader;
PrintStream writer;
ObjectMapper mapper=new ObjectMapper();
public RentCompanyTcpProxy(Socket socket) throws Exception{
	reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	writer=new PrintStream(socket.getOutputStream());
	mapper.registerModule(new JavaTimeModule());
}
	@Override
	public void save() {
		String request=SAVE+"#"+" ";
		writer.println(request);

	}

	
	@Override
	public CarsReturnCode addModel(Model model) {
		try {
			String modelJson=mapper.writeValueAsString(model);
			String request=ADD_CAR_MODEL+"#"+modelJson;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		try {
			String carJson=mapper.writeValueAsString(car);
			String request=ADD_CAR+"#"+carJson;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		try {
			String driverJson=mapper.writeValueAsString(driver);
			String request=ADD_DRIVER+"#"+driverJson;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Model getModel(String modelName) {
		try {
			String request=GET_MODEL+"#"+modelName;
			writer.println(request);
			String response=reader.readLine();
			Model model=mapper.readValue(response, Model.class);
			return model;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car getCar(String carNumber) {
		try {
			String request=GET_CAR+"#"+carNumber;
			writer.println(request);
			String response=reader.readLine();
			Car car=mapper.readValue(response, Car.class);
			return car;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Driver getDriver(long licenseId) {
		try {
			String request=GET_DRIVER+"#"+licenseId;
			writer.println(request);
			String response=reader.readLine();
			Driver driver=mapper.readValue(response, Driver.class);
			return driver;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		try{
			RentRecord record=new RentRecord(licenseId, carNumber, rentDate,
					rentDays);
			String recordJson=mapper.writeValueAsString(record);
			String request=RENT_CAR+"#"+recordJson;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, long licenseId, LocalDate returnDate, int gasTankPercent,
			int damages) {
		try{
			RentRecord record=new RentRecord(licenseId, carNumber, null,
					0);
			record.setDamages(damages);
			record.setGasTankPercent(gasTankPercent);
			record.setReturnDate(returnDate);
			String recordJson=mapper.writeValueAsString(record);
			String request=RETURN_CAR+"#"+recordJson;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		try {
			String request=REMOVE_CAR+"#"+carNumber;
			writer.println(request);
			String response=reader.readLine();
			CarsReturnCode code=CarsReturnCode.valueOf(response);
			return code;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		try {
			String request=CLEAR_CARS+"#"+currentDate+"#"+days;
			writer.println(request);
			String response=reader.readLine();
			List<Car> cars=mapper.readValue(response,
					new TypeReference<List<Car>>() {
			});
			return cars;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		try {
			String request=GET_CAR_DRIVERS+"#"+carNumber;
			writer.println(request);
			String response=reader.readLine();
			List<Driver> drivers=mapper.readValue(response, new TypeReference<List<Driver>>() {
			});
			return drivers;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		try {
			String request=GET_DRIVER_CARS+"#"+licenseId;
			writer.println(request);
			String response=reader.readLine();
			List<Car> cars=mapper.readValue(response, new TypeReference<List<Car>>() {
			});
			return cars;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Stream<Car> getAllCars() {
		try {
			String request=GET_ALL_CARS+"#"+" ";
			writer.println(request);
			String response=reader.readLine();
			List<Car> cars=mapper.readValue(response, new TypeReference<List<Car>>() {
			});
			return cars.stream();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		try {
			String request=GET_ALL_DRIVERS+"#"+" ";
			writer.println(request);
			String response=reader.readLine();
			List<Driver> drivers=mapper.readValue(response, new TypeReference<List<Driver>>() {
			});
			return drivers.stream();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		try {
			String request=GET_ALL_RECORDS+"#"+" ";
			writer.println(request);
			String response=reader.readLine();
			List<RentRecord> records=mapper.readValue(response, new TypeReference<List<RentRecord>>() {
			});
			return records.stream();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getAllModels() {
		try {
			String request=GET_ALL_MODELS+"#"+" ";
			writer.println(request);
			String response=reader.readLine();
			List<String> modelNames=mapper.readValue(response, new TypeReference<List<String>>() {
			});
			return modelNames;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
