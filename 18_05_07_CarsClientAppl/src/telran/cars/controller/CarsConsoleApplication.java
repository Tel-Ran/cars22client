package telran.cars.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import telran.cars.controller.items.*;

import telran.cars.model.*;
import telran.view.*;
import telran.cars.dto.*;
public class CarsConsoleApplication {
	static InputOutput inputOutput=new ConsoleInputOutput();
	static IRentCompany company;
	private static String HOST_NAME="localhost";
public static void main(String[] args) throws Exception{
	Socket socket=new Socket(HOST_NAME, CarsApiConstants.PORT);
	company=new RentCompanyTcpProxy(socket);
	ArrayList<Item> items=getItemsMain();
	
	
	
	Menu menu=new MenuWithExit(items, inputOutput);
	menu.runMenu();
}
private static ArrayList<Item> getItemsMain() {
	ArrayList<Item> res=new ArrayList<>();
	ArrayList<Item> itemsAdmin=getItemsAdmin();
	res.add(new AdministratorMenuItem(inputOutput, company, itemsAdmin));
	ArrayList<Item> itemsClerk=getItemsClerk();
	res.add(new ClearkMenuItem(inputOutput, company, itemsClerk));
	ArrayList<Item> itemsStatist=getItemsStatist();
	res.add(new StatistMenuItem(inputOutput, company, itemsStatist));
	res.add(new DisplayAllCarsItem(inputOutput, company));
	res.add(new DisplayAllModels(inputOutput, company));
	res.add(new ExitWithSave(inputOutput, company));
	return res;
}
private static ArrayList<Item> getItemsStatist() {
	ArrayList<Item> res=new ArrayList<>();
	res.add(new DisplayMostProfitableModel(inputOutput, company));
	res.add(new DisplayMostActiveDriversItem(inputOutput, company));
	res.add(new DisplayMostPopularModelsItem(inputOutput, company));
	res.add(new DisplayMostPopularColors(inputOutput, company));
	return res;
}
private static ArrayList<Item> getItemsClerk() {
	ArrayList<Item> res=new ArrayList<>();
	res.add(new RentCarItem(inputOutput, company));
	res.add(new ReturnCarItem(inputOutput, company));
	res.add(new DisplayCarItem(inputOutput, company));
	res.add(new DisplayDriverItem(inputOutput, company));
	res.add(new DisplayModelItem(inputOutput,company));
	res.add(new DisplayAllModels(inputOutput, company));
	res.add(new DisplayAllCarsItem(inputOutput, company));
	res.add(new DisplayCarDrivers(inputOutput, company));
	res.add(new DisplayDriverCars(inputOutput, company));
	return res;
}
private static ArrayList<Item> getItemsAdmin() {
	ArrayList<Item> res=new ArrayList<>();
	res.add(new AddCarItem(inputOutput, company));
	res.add(new AddCarModelItem(inputOutput, company));
	res.add(new AddDriverItem(inputOutput, company));
	res.add(new DisplayAllCarsItem(inputOutput, company));
	res.add(new DisplayAllModels(inputOutput, company));
	res.add(new DisplayAllDrivers(inputOutput, company));
	res.add(new RemoveCarItem(inputOutput, company));
	res.add(new ClearCarsItem(inputOutput, company));
	return res;
}
}
