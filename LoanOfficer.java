// Description: LoanOfficer class represents a bank loan officer
//              that accepts/handles and releases customers.

public class LoanOfficer
{
   private int officerID; 
   private Customer currentCustomer;

   //**************************************************
   //Constructor to initialize member variables
   //Initially no patient is assigned
   public LoanOfficer(int id)
   {
      this.officerID = id;
      currentCustomer = null;
   }

   //******************************************
   //an accessor method for the officer's ID
   public int getID()
   {
      return officerID;
   }

   //****************************************************************
   //method to determine if a loanOfficer currently has a customer by
   //checking the currentCustomer variable
   public boolean hasCustomer()
   {
	   //If loan officer has an assigned Customer, the method returns true; otherwise, it returns false
	   
	   if(currentCustomer != null) { // The loan officer has an assigned customer
		   return true;
	   }
	   else {
		   return false;
	   }
   }

   //************************************************************************
   //assign customer1 to currentCustomer if the officer does not have customer
   public boolean assignCustomer(Customer customer1)
   {
	   //If the loan officer doesn't have any assigned Customer, this method assigns the parameter 
	   //customer1 to be its currentCustomer and returns true, otherwise, it returns false
	   
	   if(currentCustomer == null) { // The loan officer does not have any assigned customer
		   currentCustomer = customer1; // Assigns parameter customer1 to be its currentCustomer
		   return true;
	   }
	   else {
		   return false;
	   }
   }

   //*********************************************
   public Customer handleCustomer() //This method simulates a loan officer handling a customer
   {
	   //If the loan officer has an assigned Customer, this method returns its currentCustomer and 
	   //later sets currentCustomer to null; otherwise, this method returns null
	   
	   if(currentCustomer != null) { // The loan officer has an assigned customer
		   Customer handledCustomer = currentCustomer; // Creates and sets the handled customer as current customer
		   currentCustomer = null;
		   return handledCustomer; // Returns the handled customer
	   }
	   else {
		   return null;
	   }
   }

   //********************************************
   //toString method returns a string containing
   //the information of a loanOfficer
   public String toString()
   {
      String result = "\nOfficer ID: " + officerID;

      if (currentCustomer == null)
         result += " does not have any cutomers\n";
      else
         result += " is serving customer with id " + currentCustomer.getCustID() + "\n";

      return result;
   }
}