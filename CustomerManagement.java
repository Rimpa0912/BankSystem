// Description: This program manages customer queues, assigns customer to loan officer,
//              process and release them, etc.

import java.util.LinkedList;

public class CustomerManagement
{
   private LinkedList<Customer> LEQueue;
   private LinkedList<Customer> MEQueue;
   private LinkedList<Customer> SEQueue;

   private LinkedList<Customer> checkoutQueue;

   private LoanOfficer[] officerList;

   //**********************************************
   //Constructor
   public CustomerManagement(int numOfLoanOfficers)
   {
      LEQueue = new LinkedList<Customer>();
      MEQueue = new LinkedList<Customer>();
      SEQueue = new LinkedList<Customer>();
      checkoutQueue = new LinkedList<Customer>();

      //creating LoanOfficer objects
      officerList = new LoanOfficer[numOfLoanOfficers];
      for (int i=0; i< officerList.length; i++)
      {
         officerList[i] = new LoanOfficer(i);
      }
   }

   //*******************************************************************
   //add a customer to the corresponding queue based on its category
   //return true if added to a queue successfully; otherwise return false
   boolean addCustomer(int id, String category)
   {
	   // When the parameter category is LE
	   if(category.equals("LE")) {
		   Customer customer = new Customer(id, category); //Instantiates a Customer object with the id and category as parameters
		   LEQueue.add(customer); //Adds the customer object to the LE Queue
		   return true;
	   }
	// When the parameter category is ME
	   else if(category.equals("ME")) {
		   Customer customer = new Customer(id, category);
		   MEQueue.add(customer); //Adds the customer object to the ME Queue
		   return true;
	   }
	// When the parameter category is SE
	   else if(category.equals("SE")) {
		   Customer customer = new Customer(id, category);
		   SEQueue.add(customer); //Adds the customer object to the SE Queue
		   return true;
	   }
	   else { //If the parameter category is neither LE, ME, nor SE
		   return false;
	   }
   }

   //*******************************************************************
   //assign a customer to a loan officer with large enterprise (LE) queues
   //going first; return null if there are no customers in the queues or if
   //there are no loan officer are available
   Customer assignCustomerToLoanOfficer()
   {
	   Customer customer = null;
	   LoanOfficer officer = null;
	   
	   if(officerList != null) { //If there are officers available
		   
		   //Use a for-loop to iterate through the officer list to get available officers
		   for(int i = 0; i < officerList.length; i++) {
			   
			   if(officerList[i].hasCustomer() == false) { //The officer doesn't have an assigned customer
				   officer = officerList[i]; //The available officer from officer list is assigned to the variable officer
				   break;
				   }
			   }
		   //Customers in LE Queue are assigned first
		   if(officer != null) {
		   if(!LEQueue.isEmpty()) { 
			   customer = LEQueue.getFirst(); // Gets the first customer in LE Queue
			   officer.assignCustomer(customer); //Assigns the chosen customer to an officer
			   customer = LEQueue.remove(); //Removes the assigned customer from the LE Queue
			   }
		   //Customers in ME Queue are assigned second
		   else if(!MEQueue.isEmpty() == true) {
			   customer = MEQueue.getFirst(); // Gets the first customer in ME Queue
			   officer.assignCustomer(customer);
			   customer = MEQueue.remove(); 
			   }
		   //Customers in SE Queue are assigned last
		   else if(!SEQueue.isEmpty() == true) {
			   customer = SEQueue.getFirst(); // Gets the first customer in SE Queue
			   officer.assignCustomer(customer);
			   customer = SEQueue.remove(); 
			   }
		   return customer; // Returns the customer handled
		   }
	   }
	   else {
	   //if(customer == null) or No customer in any of the 3 queues
		   return null;
		   }
	   
	   return null;
	   }

   //***************************************************************
   //check if officer with the officerID has a customer, and release
   //that customer if they do. Then add that customer to checkout queue
   //and return the Customer object; otherwise return null
   Customer releaseCustomerFromOfficer(int officerID)
   {
	   LoanOfficer officer = null;
	   // Use a for-loop to iterate through the officer list to check whether officers have a customer assigned or not
	   for(int i = 0; i < officerList.length; i++) {
		   
		   if(officerList[i].getID() == officerID) { //To check if the Officer ID is valid
			   officer = officerList[i]; // The officer with valid id is assigned to the variable officer
			   
			   if(officer.hasCustomer() == true) { // The officer has a customer assigned
				   Customer customer = officer.handleCustomer(); //Handles current customer
				   checkoutQueue.add(customer); //Adds the customer to the checkout Queue
				   return customer; //Returns the released customer
				   }
		   }
	   }
	   return null;
   }

   //**************************************************************
   //remove the first Customer from the checkoutQueue and return it;
   //otherwise the method return null.
   public Customer checkoutCustomer()
   {   
      if(!checkoutQueue.isEmpty() == true) { // The checkout queue is not empty
    	  Customer nextCustomer = checkoutQueue.removeFirst(); // Removes the next customer from the queue
    	  return nextCustomer;
      }
      else {
    	  return null;
      }
   }

   //************************************************
   //The printQueue prints out the content
   //of the queues and the array of loan officers
   public void printQueues()
   {
      System.out.print("\nLarge Enterprise Queue:\n" + LEQueue.toString() + "\n");
      System.out.print("\nMedium Enterprise Queue:\n" + MEQueue.toString() + "\n");
      System.out.print("\nSmall Enterprise Queue:\n" + SEQueue.toString() + "\n\n");
      for (int i = 0; i < officerList.length; i++)
      {
         System.out.print(officerList[i].toString());
      }
      System.out.print("\nCheckout Customer Queue:\n" + checkoutQueue.toString() + "\n\n");
   }
}
   