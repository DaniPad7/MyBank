package com.mybank.main;

import org.apache.log4j.Logger;


import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;
import com.mybank.service.AccountReadService;
import com.mybank.service.impl.AccountCreateServiceImpl;
import com.mybank.service.impl.AccountReadServiceImpl;

public class MyBankMain {
	public static Logger log = Logger.getLogger(MyBankMain.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		AccountCreateService accountCreateService = new AccountCreateServiceImpl();
		AccountReadService accountReadService = new AccountReadServiceImpl();
		
		int option = 0;
		
		log.info("___________Hello, Welcome to MyBank__________________");
		log.info("What would you like to do today...");
		
		
		do {
			log.info("Login Or Sign Up");
			log.info("1) Login");
			log.info("2) Sign Up");
			log.info("56) Exit");
			
			try {
			option = Integer.parseInt(scanner.nextLine());
			
			}catch(NumberFormatException e) {}
			
			
			switch(option) {
			case 1:
				int loginOption = 0;
				do {
					log.info("_____________________Login____________________");
					log.info("1) Enter information");
					log.info("67) Back");
					try {
					loginOption = Integer.parseInt(scanner.nextLine());
					}catch(NumberFormatException e) {}
					switch(loginOption) {
					case 1: 
						log.info("Please enter your username: ");
						String username = scanner.nextLine();
						log.info("Please enter your password: ");
						String password = scanner.nextLine();
						try {
							UserPersonalInfo userPersonalInfoRead =	accountReadService.userLogin(username, password);//this one will check the one below
							UserCorporateInfo userCorporateInfoRead = accountReadService.userGetCorporateInfoIsEmployee(username, password);
							if(userPersonalInfoRead != null && userCorporateInfoRead.isEmployee()) {
								int employeeOptions = 0;
							do{
							log.info("_____________Hello " + userPersonalInfoRead.getFirstName() + "!________ ");
							log.info("As an employee, what would you like to do today. ");
							log.info("1) Approve an Account");
							log.info("2) Retrieve All the Customer's Information");
							log.info("3) Rerieve the Logs of A Customer");
							log.info("53) Sign Out");
							try {
								employeeOptions = Integer.parseInt(scanner.nextLine());
								}catch(NumberFormatException e) {}
								switch(employeeOptions) {
								case 1:
									log.info("Enter the first name of the customer:");
									String firstName = scanner.nextLine();
									log.info("Enter the last name of the customer: ");
									String lastName = scanner.nextLine();
									try {
										List<UserAccountInfo> userAccountInfoList = accountReadService.getAcc(firstName, lastName);
										if(userAccountInfoList != null && userAccountInfoList.size() > 0) {
											log.info("The unapproved customer account are printed below:");;
											for(UserAccountInfo uai : userAccountInfoList) {
												if(!uai.isApproved()) {
													log.info(uai);
												}
												else {}
												
											}
											
										}
									} catch (BusinessException e) {
										log.info(e.getMessage());;
										log.info("Please try again.");
									}
									
									//updateservicehere for employee
									break;
									
								case 2:
									log.info("This option is under construction.");
									break;
								case 3:
									log.info("This option is under construction.");
									break;
								case 53:
									log.info("You decided to sign out.");
									break;
								default:
									log.info("Invalid Option. Please try again.");
									break;
								}
								
							}while(employeeOptions != 53);


							}
							else if(userPersonalInfoRead != null && !userCorporateInfoRead.isEmployee()){
								int customerOptions = 0;
								do {
									log.info("Hello " + userPersonalInfoRead.getFirstName() + "! As a customer, what would you like to do today. ");
									log.info("1) Open A New Account");
									log.info("2) Retrieve Account Information");
									log.info("3) Withdraw/Deposit");
									log.info("4) Transfer Account Balance");
									log.info("5) Accept Transfer");
									log.info("52) Sign Out");
								try {
									customerOptions = Integer.parseInt(scanner.nextLine());
								}catch(NumberFormatException e) {}
									switch(customerOptions) {
									case 1:
										//log.info("This option is under construction.");
										//add public int openNewAcc() throws BusinessException;
										int newCustomerAccountOptions = 0;
										UserAccountInfo userAccountInfo = new UserAccountInfo();;
										do {
											log.info("__________________New Account_________________");
											log.info("What account would you like to open:");
											log.info("1) Savings");
											log.info("2) Checkings");
											log.info("52) Back");
											try {
												newCustomerAccountOptions = Integer.parseInt(scanner.nextLine());
											}catch(NumberFormatException e) {}
											
											if(newCustomerAccountOptions == 1) {
												userAccountInfo.setAccountType("Savings");
												log.info("_____________Welcome to Your New "+ userAccountInfo.getAccountType() +" Account___________");
												log.info("1) Enter Account Information");
												log.info("51) Back");
											}
											else if(newCustomerAccountOptions == 2){
												userAccountInfo.setAccountType("Checkings");
												log.info("_____________Welcome to Your New " + userAccountInfo.getAccountType() + " Account___________");
												log.info("1) Enter Account Information");
												log.info("51) Back");
												
												newCustomerAccountOptions = 1;
												}
											else {}
											
											switch(newCustomerAccountOptions) {
											
											case 1:
												int customerNewAccountOption = 0;
												
												do {
										
												try {
													 customerNewAccountOption = Integer.parseInt(scanner.nextLine());
													
													}catch(NumberFormatException e) {
														
													}
												if(customerNewAccountOption > 1 && customerNewAccountOption < 51) {
													customerNewAccountOption = 0;
												}
												switch(customerNewAccountOption) {
												
												case 1:
													log.info("Please input your starting balance: ");
													try {
													userAccountInfo.setBalance(Double.parseDouble(scanner.nextLine()));
													}catch(NumberFormatException e) {
														log.info(e);
													}
													
													if(userAccountInfo.getBalance() < 1_000_000 && userAccountInfo.getBalance() > -1)  {
														accountCreateService.openNewAcc(userPersonalInfoRead,userAccountInfo);
														log.info("Your account was successfully created... Redirecting you back");
														customerNewAccountOption = 51;
														break;
													}
													else {
														log.info("The balance amount is out of bounds.");
														//customerNewAccountOption = 1;
														break;
													}
												
												case 51:
													log.info("Back");
													
													break;
												default:
													log.info("Invalid option. Please try again.");
													log.info("1) Enter Personal Information");
													log.info("51) Back");
													break;
												}
												
												} while(customerNewAccountOption != 51);
												
											case 52:	
												log.info("");
												break;
											
											default:
												log.info("Invalid option. Please try again.");
												break;
											}
										}while(newCustomerAccountOptions != 52);//<--last one was 55 so 54 --> 51
										break;
									case 2:
										log.info("In order to view accounts, enter your password again: ");
										String passwordAgain = scanner.nextLine();
										try {
											List<UserAccountInfo> userAccountInfoList = accountReadService.getAcc(passwordAgain);
											if(userAccountInfoList != null && userAccountInfoList.size() > 0) {
												log.info("Your accounts are printed below:");;
												for(UserAccountInfo uai : userAccountInfoList) {
													log.info(uai);
													}
												
											}
										} catch (BusinessException e) {
											log.info(e.getMessage());
											log.info("Please try again.");
										}
										break;
									case 3:
										log.info("This option is under construction.");
										break;
									case 4:
										log.info("This option is under construction.");
										break;
									case 5:
										log.info("This option is under construction.");
										break;
									case 52:
										log.info("You signed out.");
										break;
									default:
										log.info("Invalid Option from customer. Please try again.");
										break;
									}
									
								}while(customerOptions != 52);
							} 
							else {}
						}catch (BusinessException e1) {
								log.info(e1);
						}
						break;
					case 67:
						log.info("You decided to go back.");
						break;
					default:
						log.info("Invalid input. Please try again.");
						break;
						
						
					}
					
					
					
				 
				}while(loginOption != 67);
				break;
			
			case 2:
				//I can try encapsulating this into serviceImpl
				int signUpOption = 0;
				UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
				UserCorporateInfo userCorporateInfo = new UserCorporateInfo();
				
				do {
					log.info("__________________Sign Up_________________");
					log.info("What would you like to sign up as:");
					log.info("1) Customer");
					log.info("2) Employee");
					log.info("55) Back");
					
					try {
						signUpOption = Integer.parseInt(scanner.nextLine());
						
						}catch(NumberFormatException e) {
							
						}
					
					if(signUpOption == 1) {
						userCorporateInfo.setEmployee(false);
						log.info("_____________Welcome Customer___________");
						log.info("1) Enter Personal Information");
						log.info("54) Back");
					}
					else if(signUpOption == 2){
						userCorporateInfo.setEmployee(true);
						log.info("_____________Welcome Employee___________");
						log.info("1) Enter Personal Information");
						log.info("54) Back");
						signUpOption = 1;
						
					}
					else {}
					
					switch(signUpOption) {
					
					case 1:
						int customerSignUpOption = 0;
						
						do {
				
						try {
							 customerSignUpOption = Integer.parseInt(scanner.nextLine());
							
							}catch(NumberFormatException e) {
								
							}
						if(customerSignUpOption > 1 && customerSignUpOption < 54) {
							customerSignUpOption = 0;
						}
						
						
						
						switch(customerSignUpOption) {
						
						case 1: //needs fixing
							log.info("Enter your First Name: ");
							userPersonalInfo.setFirstName(scanner.nextLine());
							//log.info(userPersonalInfo.getFirstName());
							//log.info(userPersonalInfo.getFirstName().matches("^[a-zA-Z]+$"));
							if(userPersonalInfo.getFirstName().matches("^[a-zA-Z]+$")) {
								customerSignUpOption = 2;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 1;
								break;
								}
							
							
						case 2:
							log.info("Enter your Last Name: ");
							userPersonalInfo.setLastName(scanner.nextLine());
							if(userPersonalInfo.getLastName().matches("^[a-zA-Z]+$")) {
								customerSignUpOption = 3;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 2;
								break;
								}
						case 3:
							log.info("Enter your birthdate: ");
							
							userPersonalInfo.setBirthDate(Date.valueOf(scanner.nextLine()));
							if(userPersonalInfo.getBirthDate().toString().matches("^[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]+$")) {
								customerSignUpOption = 4;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 3;
								break;
								}
							
						case 4: //improve regex
							log.info("Enter your phone number: ");
							userPersonalInfo.setPhoneNumber(scanner.nextLine());
							if(userPersonalInfo.getPhoneNumber().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}+$")) {
								customerSignUpOption = 5;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 4;
								break;
								}
						case 5:
							log.info("Enter your email: ");
							userPersonalInfo.setEmail(scanner.nextLine());
							if(/*userPersonalInfo.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" ||*/ userPersonalInfo.getEmail().isEmpty()) {
								customerSignUpOption = 6;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 5;
								break;
								}
						case 6:
							log.info("Enter your Country:");
							userPersonalInfo.setCountryId(Integer.parseInt(scanner.nextLine()));
							if(userPersonalInfo.getCountryId() == 1) {
								customerSignUpOption = 7;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 6;
								break;
								}
						case 7:
							log.info("Enter your home address: ");
							userPersonalInfo.setHomeAddress(scanner.nextLine());
							if(userPersonalInfo.getHomeAddress().matches("^[a-zA-Z0-9]+$")) {
								customerSignUpOption = 8;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 7;
								break;
								}
							
						case 8:
							log.info("Enter yor home city: ");
							userPersonalInfo.setHomeCity(scanner.nextLine());
							if(userPersonalInfo.getHomeCity().matches("^[a-zA-Z]+$")) {
								customerSignUpOption = 9;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 8;
								break;
								}
						case 9:
							log.info("Enter your home state: ");
							userPersonalInfo.setHomeState(scanner.nextLine());
							if(userPersonalInfo.getHomeState().matches("^[A-Z]{2}+$")) {
								customerSignUpOption = 10;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 9;
								break;
								}
						case 10:
							log.info("Enter your home zipcode: ");
							userPersonalInfo.setHomeZipCode(scanner.nextLine());
							if(userPersonalInfo.getHomeZipCode().matches("^[0-9]{5}+$")) {
								log.info("Great! Now you can create a USERNAME and Password.");
								customerSignUpOption = 11;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 10;
								break;
								}
						case 11:
							log.info("Enter your username: ");
							userCorporateInfo.setUsername(scanner.nextLine());
							if(userCorporateInfo.getUsername().matches("^[a-zA-Z0-9]+$")) {
								customerSignUpOption = 12;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 11;
								break;
								}
						case 12:
							log.info("Enter your password: ");
							userCorporateInfo.setPassword(scanner.nextLine());
							if(userCorporateInfo.getUsername().matches("^[a-zA-Z0-9]+$")) {
								log.info("Attempting connection...");
								try {
									accountCreateService.regiCustomerAccount(userPersonalInfo, userCorporateInfo);
								} catch (BusinessException e) {
									log.info(e);
								}
								customerSignUpOption = 54;
								log.info("Congratulations, you were registered with us. You will now be redirected back to the Sign Up menu.");
								break;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 12;
								break;
								}
						case 54:
							log.info("You decided to go back");
							//customerSignUpOption = 54;
							break;
						default:
							log.info("Invalid option. Please try again.");
							log.info("1) Enter Personal Information");
							log.info("54) Back");
							break;
						}
						
						} while(customerSignUpOption != 54);
						
					case 55:	
						log.info("");
						break;
					
					default:
						log.info("Invalid option. Please try again.");
						break;
					}
				}while(signUpOption != 55);
				//end of encapsulation
				
			case 55:
				log.info("You decided to go back.");
				break;
			
			case 56:
				log.info("OK Have a nice day.");
				break;
			
			default:
				log.info("Invalid Option. Please try again.");
				break;
			
			
			
			}
			
			
			
		}while(option != 56);
		
		
		
		
		
		
		
		
		
		
		/*try {
			int b = accountCreateService.regiCustomerAccount(firstUser, firstUserC);
		} catch (BusinessException e) {
			log.info(e);
		}*/
		
		
	}

}
