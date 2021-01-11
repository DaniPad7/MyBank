package com.mybank.main;

import org.apache.log4j.Logger;


import java.sql.Date;
import java.util.Scanner;
import java.util.regex.*;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;
import com.mybank.service.impl.AccountCreateServiceImpl;

public class MyBankMain {
	public static Logger log = Logger.getLogger(MyBankMain.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		AccountCreateService accountCreateService = new AccountCreateServiceImpl();
		
		int option = 0;
		
		log.info("___________Hello, Welcome to MyBank__________________");
		log.info("What would you like to do today...");
		
		
		do {
			log.info("Login Or Sign Up");
			log.info("1) Login");
			log.info("2) Sign Up");
			log.info("9) Exit");
			
			try {
			option = Integer.parseInt(scanner.nextLine());
			
			}catch(NumberFormatException e) {
				
			}
			
			
			switch(option) {
			case 1:
				log.info("This option is still under construction. This will a a Read query. yay!");
				break;
			case 2:
				int signUpOption = 0;
				UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
				UserCorporateInfo userCorporateInfo = new UserCorporateInfo();
				
				do {
					log.info("__________________Sign Up_________________");
					log.info("What would you like to sign up as:");
					log.info("1) Customer");
					log.info("2) Employee");
					log.info("3) Back");
					
					try {
						signUpOption = Integer.parseInt(scanner.nextLine());
						
						}catch(NumberFormatException e) {
							
						}
					
					if(signUpOption == 1) {
						userCorporateInfo.setEmployee(false);
						log.info("_____________Welcome Customer___________");
						log.info("1) Enter Personal Information");
						log.info("16) Back");
					}
					else if(signUpOption == 2){
						userCorporateInfo.setEmployee(true);
						log.info("_____________Welcome Employee___________");
						log.info("1) Enter Personal Information");
						log.info("16) Back");
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
						if(customerSignUpOption > 1 && customerSignUpOption < 16) {
							customerSignUpOption = 0;
						}
						
						
						
						switch(customerSignUpOption) {
						
						case 1:
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
						case 4:
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
								customerSignUpOption = 16;
								log.info("Congratulations, you were registered with us. You will now be redirected back to the Sign Up menu.");
								break;
								
							}
							else {
								log.info("Invalid input. Please try again.");
								customerSignUpOption = 12;
								break;
								}
						case 16:
							log.info("You decided to go back");
							customerSignUpOption = 16;
							break;
						default:
							log.info("Invalid option. Please try again.");
							log.info("1) Enter Personal Information");
							log.info("16) Back");
							break;
						}
						
						} while(customerSignUpOption != 16);
						
					case 3:	
						log.info("");
						break;
					
					default:
						log.info("Invalid option. Please try again.");
						break;
					}
				}while(signUpOption != 3);
				
				
			case 3:
				log.info("You decided to go back.");
				break;
			
			case 9:
				log.info("OK Have a nice day.");
				break;
			
			default:
				log.info("Invalid Option. Please try again.");
				break;
			
			
			
			}
			
			
			
		}while(option != 9);
		
		
		
		
		
		
		
		
		
		
		/*try {
			int b = accountCreateService.regiCustomerAccount(firstUser, firstUserC);
		} catch (BusinessException e) {
			log.info(e);
		}*/
		
		
	}

}
