package com.mybank.main;

import org.apache.log4j.Logger;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import com.mybank.exception.BusinessException;
import com.mybank.model.UserAccountInfo;
import com.mybank.model.UserBankHistory;
import com.mybank.model.UserCorporateInfo;
import com.mybank.model.UserPersonalInfo;
import com.mybank.service.AccountCreateService;
import com.mybank.service.AccountDeleteService;
import com.mybank.service.AccountReadService;
import com.mybank.service.AccountUpdateService;
import com.mybank.service.impl.AccountCreateServiceImpl;
import com.mybank.service.impl.AccountDeleteServiceImpl;
import com.mybank.service.impl.AccountReadServiceImpl;
import com.mybank.service.impl.AccountUpdateServiceImpl;

public class MyBankMain {
	public static Logger log = Logger.getLogger(MyBankMain.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		AccountCreateService accountCreateService = new AccountCreateServiceImpl();
		AccountReadService accountReadService = new AccountReadServiceImpl();
		AccountUpdateService accountUpdateService = new AccountUpdateServiceImpl();
		AccountDeleteService accountDeleteService = new AccountDeleteServiceImpl();
		
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
									int approveEmployeeOption = 0;
									log.info("Enter the first name of the customer:");
									String firstName = scanner.nextLine();
									log.info("Enter the last name of the customer: ");
									String lastName = scanner.nextLine();
									try {
											List<UserAccountInfo> userAccountInfoList = accountReadService.getAcc(firstName, lastName);
											if(userAccountInfoList != null && userAccountInfoList.size() > 0) {
												log.info("The unapproved customer accounts are printed below:");;
												for(UserAccountInfo uai : userAccountInfoList) {
													if(!uai.isApproved()) {
														log.info(uai);
													}
													else {}
												}
											}
											} catch (BusinessException e) {
												log.info(e.getMessage());
												log.info("Please try again.");
												approveEmployeeOption  =52;
											}
									while(approveEmployeeOption != 52) {		
												log.info("_____Ready to Approve Account______ ");
												log.info("1)Enter Routing Number ");
												log.info("52)Back ");
												try {
													approveEmployeeOption = Integer.parseInt(scanner.nextLine());
												}catch(NumberFormatException e) {
													log.info(e.getMessage());
												}
												switch(approveEmployeeOption) {
												case 1:
													log.info("Enter the Account Routing Number to Approve: ");
													try {
													int routingNumber = Integer.parseInt(scanner.nextLine());
													accountUpdateService.approveAcc(routingNumber);
													}catch(NumberFormatException|BusinessException e) {
														log.info(e.getMessage());
														log.info("Invalid option. Please try again.");
													}
													
													break;
												case 52:
													log.info("You were redirected back.");
														break;
												default:
													log.info("Invalid option. Please try again.");
													break;
												}
											}
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
									log.info("2) Retrieve Account Information");//this might retrieve accounts with ssahred password
									log.info("3) Withdraw/Deposit");
									log.info("4) Transfer Account Balance");
									log.info("5) Accept/Reject Transfer");
									log.info("52) Sign Out");
								try {
									customerOptions = Integer.parseInt(scanner.nextLine());
								}catch(NumberFormatException e) {}
									switch(customerOptions) {
									case 1:
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
													}catch(NumberFormatException e) {}
												if(customerNewAccountOption > 1 && customerNewAccountOption < 51) {
													customerNewAccountOption = 0;
												}
												switch(customerNewAccountOption) {
												case 1:
													log.info("Please input your starting balance: ");
													try {
													userAccountInfo.setBalance(Double.parseDouble(scanner.nextLine()));
													}catch(NumberFormatException e) {
														log.info(e.getMessage());
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
													log.info("You were directed back.");
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
										UserAccountInfo userApprovedAccountInfo = new UserAccountInfo();
										UserBankHistory userBankHistory = new UserBankHistory();
										int withdrawDepositAccountOption = 0;
										log.info("In order to Withdraw or Deposit, enter your password again: ");
										String passwordAgain0 = scanner.nextLine();
										try {
											List<UserAccountInfo> userAccountInfoList = accountReadService.getAcc(passwordAgain0);
											List<UserAccountInfo> approvedUserAccountInfoList = new ArrayList<>();
											if(userAccountInfoList != null && userAccountInfoList.size() > 0) {
												log.info("Your approved accounts are printed below:");
												for(UserAccountInfo uai : userAccountInfoList) {
													if(uai.isApproved()) {
														log.info(uai);
														approvedUserAccountInfoList.add(uai);
													}
													}
												int choseExistingRoutingNumber = 1;
												while(choseExistingRoutingNumber  == 1){
												log.info("Enter the Routing Number of the Account: ");
												int withdrawDepositRoutingNumber = Integer.parseInt(scanner.nextLine());
													for(UserAccountInfo uai : approvedUserAccountInfoList) {
														
														if(uai.getRoutingNumber() == withdrawDepositRoutingNumber) {
															log.info(uai);
															userApprovedAccountInfo.setUserId(uai.getUserId());
															userApprovedAccountInfo.setAccountType(uai.getAccountType());
															userApprovedAccountInfo.setAccountNumber(uai.getAccountNumber());
															userApprovedAccountInfo.setRoutingNumber(uai.getRoutingNumber());
															userApprovedAccountInfo.setBalance(uai.getBalance());
															userApprovedAccountInfo.setApproved(uai.isApproved());
															
															choseExistingRoutingNumber = 0;
														}
														}
												}
												}
											userBankHistory.setUserId(userApprovedAccountInfo.getUserId());
											userBankHistory.setRoutingNumber(userApprovedAccountInfo.getRoutingNumber());
											userBankHistory.setRoutingNumberDest(userApprovedAccountInfo.getRoutingNumber());
											userBankHistory.setAccepted(true);
											do {
												log.info("__________________Withdraw OR Deposit_________________");
												log.info("What type of transaction would you like to do: ");
												log.info("1) Withdrawal");
												log.info("2) Deposit");
												log.info("52) Back");
												try {
												withdrawDepositAccountOption = Integer.parseInt(scanner.nextLine());
												}catch(NumberFormatException e) {}
												if(withdrawDepositAccountOption == 1) {
													userBankHistory.setTransactionType("Withdrawal");
													log.info("_____________How much would you like to Withdraw?___________");
													
												}
												else if(withdrawDepositAccountOption == 2){
													userBankHistory.setTransactionType("Deposit");
													log.info("_____________How much would you like to " + userBankHistory.getTransactionType() + "?___________");
													
													withdrawDepositAccountOption = 1;
													}
												else {}
												userBankHistory.setAmount(0);
												switch(withdrawDepositAccountOption) {
												case 1:
													while(userBankHistory.getAmount() < 1 || userBankHistory.getAmount() > 1_000_000) {
															log.info("Enter the " + userBankHistory.getTransactionType() + " amount: ");
															try {userBankHistory.setAmount(Double.parseDouble(scanner.nextLine()));
															}catch(NumberFormatException e) {}
														}
															accountCreateService.withdrawOrDeposit(userBankHistory,userApprovedAccountInfo);
															/*accountcreateService.withdrawOrDeposit(userBankingHistory,userApprovedAccountInfo)*/
													
													withdrawDepositAccountOption = 52;
													break;
												case 52:	
													log.info("You were directed back.");
													break;
												default:
													log.info("Invalid option. Please try again.");
													break;
												}
											}while(withdrawDepositAccountOption != 52);
											} catch (BusinessException|NumberFormatException e) {
											log.info(e.getMessage());
											log.info("Please try again.");
										}
										break;
									case 4:
										UserAccountInfo  userApprovedAccountInfoDest = new UserAccountInfo();
										UserAccountInfo userApprovedAccountInfoInitial = new UserAccountInfo();
										UserBankHistory userBankHistoryTransfer = new UserBankHistory();
										int postTransferAccountOption = 0;
										log.info("In order to Transfer an amount, enter your username again: ");
										String usernameAgain = scanner.nextLine();
										log.info("In order to Transfer an amount, enter your password again: ");
										String passwordAgain1 = scanner.nextLine();
										List<UserAccountInfo> approvedUserAccountInfoList = accountReadService.getApprovedAccByCorp(usernameAgain, passwordAgain1);//Continue here, you should implement if statement
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
								log.info(e1.getMessage());
						}
						break;
					case 67:
						log.info("You were directed back.");
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
							
							}catch(NumberFormatException e) {}
						if(customerSignUpOption > 1 && customerSignUpOption < 54) { //the problem of switch end is here
							customerSignUpOption = 0;
						}
						int i = 0;
						switch(customerSignUpOption) {
						case 1: 
							while(i < 1) {
								log.info("Enter your First Name: ");
								userPersonalInfo.setFirstName(scanner.nextLine());
								if(userPersonalInfo.getFirstName().matches("^[a-zA-Z]+$")) {
									i = 1;
								}
								else {
									log.info("First name is Alphabetical. Please try again.");
								}
							}
						case 2:
							while(i < 2) {
								log.info("Enter your Last Name: ");
								userPersonalInfo.setLastName(scanner.nextLine());
								if(userPersonalInfo.getLastName().matches("^[a-zA-Z]+$")) {
									i = 2;
								}
								else {
									log.info("Last name is Alphabetical. Please try again.");
								}
							}
						case 3:
								while(i < 3) {
								log.info("Enter your birthdate: ");
								String dateValidator = scanner.nextLine();
								if(dateValidator.matches("^[12]{1}[89012]{1}[0-9]{1}[0-9]{1}-[01]{1}[0-9]{1}-[0123]{1}[0-9]{1}+$")) {
									userPersonalInfo.setBirthDate(Date.valueOf(dateValidator));
									i = 3;
								}
								else {
									log.info("Try the format \"yyyy-mm-dd\". Please try again.");
								}
							}
							case 4:
								while(i < 4) {
									log.info("Enter your phone number: ");
									userPersonalInfo.setPhoneNumber(scanner.nextLine());
									if(userPersonalInfo.getPhoneNumber().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}+$") || userPersonalInfo.getPhoneNumber().isEmpty()) {
										i = 4;
									}
									else {
										log.info("Try the format \"000-000-0000\". Please try again.");
									}
								}
						case 5:
							while(i < 5) {
								log.info("Enter your email: ");
								userPersonalInfo.setEmail(scanner.nextLine());
								if(userPersonalInfo.getEmail().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$") || userPersonalInfo.getEmail().isEmpty() || userPersonalInfo.getEmail() == null) {
									i = 5;
								}
								else {
									log.info("For example /'example12@gmail.com'/. Please try again.");
								}
							}
						case 6:
							while(i < 6) {
								log.info("Enter your Country:");
								log.info("1) USA");
								userPersonalInfo.setCountryId(Integer.parseInt(scanner.nextLine()));
								if(userPersonalInfo.getCountryId() == 1) {
									i = 6;
								}
								else {
									log.info("Please enter 1.");
								}
							}
						case 7:
							while(i < 7) {
								log.info("Enter your home address: ");
								userPersonalInfo.setHomeAddress(scanner.nextLine());
								if(userPersonalInfo.getHomeAddress().matches("[a-zA-Z0-9 ]{2,10} [a-zA-Z0-9 ]{2,40}")) {
									i = 7;
								}
								else {
									log.info("Invalid input. Please try again.");
								}
							}
							case 8:
								while(i < 8) {
									log.info("Enter your home city: ");
									userPersonalInfo.setHomeCity(scanner.nextLine());
									if(userPersonalInfo.getHomeCity().matches("[a-zA-Z ]{2,10}")) {
										i = 8;
									}
									else {
										log.info("Invalid input. Please try again.");
									}
								}
						case 9:
							while(i < 9) {
								log.info("Enter your home state: ");
								userPersonalInfo.setHomeState(scanner.nextLine());
								if(userPersonalInfo.getHomeState().matches("^[A-Z]{2}+$")) {
									i = 9;
								}
								else {
									log.info("Try the Format \"AA\". Please try again.");
								}
							}
						case 10:
							while(i < 10) {
								log.info("Enter your home zipcode: ");
								userPersonalInfo.setHomeZipCode(scanner.nextLine());
								if(userPersonalInfo.getHomeZipCode().matches("^[0-9]{5}+$")) {
									i = 10;
									log.info("Great! Now you can create a Username and Password.");
								}
								else {
									log.info("Try the format /'00000'/. Please try again.");
								}
							}
						case 11:
							while(i < 11) {
								log.info("Enter your password: ");
								userCorporateInfo.setPassword(scanner.nextLine());
								if(userCorporateInfo.getPassword().matches("^[a-zA-Z0-9]+$")) {
									i = 11;
								}
								else {
									log.info("The password is Alphanumeric. Please try again.");
								}
							}
						case 12:
							while(i < 12) {
								log.info("Enter your username: ");
								userCorporateInfo.setUsername(scanner.nextLine());
								if(userCorporateInfo.getUsername().matches("^[a-zA-Z0-9]+$")) {
									try {
										accountCreateService.regiCustomerAccount(userPersonalInfo, userCorporateInfo);
										log.info("Congratulations, you were registered with us. You will now be redirected back to the Sign Up menu.");
									} catch (BusinessException e) {
										log.info(e.getMessage());
										try {
											accountDeleteService.deleteMaxUserIdFromUserPersonalInfo();
										} catch (BusinessException e1) {
											log.info(e1.getMessage());
										}
										}
										i = 12;
								}
								else {
									log.info("The username is Alphanumeric. Please try again.");
								}
							}
						case 54:
							log.info("You were directed back.");
							customerSignUpOption = 54;
							break;
							default:
							log.info("_____________Welcome to MyBank___________");
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
				case 55:
				log.info("You were directed back.");
				break;
			case 56:
				log.info("OK Have a nice day.");
				break;
			default:
				log.info("Invalid Option. Please try again.");
				break;
			}
			}while(option != 56);
		scanner.close();
		}
}
