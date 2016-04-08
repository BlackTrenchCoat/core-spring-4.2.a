package accounts.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import rewards.internal.account.Account;
import rewards.internal.account.Beneficiary;

import common.money.Percentage;

public class AccountClientTests {
	
	/**
	 * server URL ending with the servlet mapping on which the application can be reached.
	 */
	private static final String BASE_URL = "http://localhost:8080";
	
	private RestTemplate restTemplate = new RestTemplate();
	private Random random = new Random();
	
	@Test
	public void listAccounts() {
		//	TODO 03: Remove the @Ignore on this test method.
		//	Use the restTemplate to retrieve an array containing all Account instances.
		//	Run the test and ensure that it passes.
		Account[] accounts = restTemplate.getForObject(BASE_URL + "/accounts", Account[].class);
		
		assertNotNull(accounts);
		assertTrue(accounts.length >= 21);
		assertEquals("Keith and Keri Donald", accounts[0].getName());
		assertEquals(2, accounts[0].getBeneficiaries().size());
		assertEquals(Percentage.valueOf("50%"), accounts[0].getBeneficiary("Annabelle").getAllocationPercentage());
	}
	
	@Test
	public void getAccount() {
		//	TODO 05: Remove the @Ignore on this test method.
		//	Use the restTemplate to retrieve the Account with id 0 using a URI template
		//	Run the test and ensure that it passes.
		Account account = restTemplate.getForObject(BASE_URL + "/accounts/{id}", Account.class, 0);
		
		assertNotNull(account);
		assertEquals("Keith and Keri Donald", account.getName());
		assertEquals(2, account.getBeneficiaries().size());
		assertEquals(Percentage.valueOf("50%"), account.getBeneficiary("Annabelle").getAllocationPercentage());
	}
	
	@Test
	public void createAccount() {
		// use a unique number to avoid conflicts
		String number = String.format("12345%4d", random.nextInt(10000));
		Account account = new Account(number, "John Doe");
		account.addBeneficiary("Jane Doe");
		
		//	TODO 08: Remove the @Ignore on this test method.
		//	Create a new Account by POSTing to the right URL and store its location in a variable
		URI accountLocation = restTemplate.postForLocation(BASE_URL + "/accounts/", account);

		//	TODO 09: Retrieve the Account you just created from the location that was returned.
		//	Run this test.  Whether it passes or not, proceed with the next step.
		Account retrievedAccount = restTemplate.getForObject(accountLocation.toString(), Account.class, account.getNumber());
		
		assertEquals(account.getNumber(), retrievedAccount.getNumber());
		
		Beneficiary accountBeneficiary = account.getBeneficiaries().iterator().next();
		Beneficiary retrievedAccountBeneficiary = retrievedAccount.getBeneficiaries().iterator().next();
		
		assertEquals(accountBeneficiary.getName(), retrievedAccountBeneficiary.getName());
		assertNotNull(retrievedAccount.getEntityId());
	}
	
	@Test
	public void addAndDeleteBeneficiary() {
		// perform both add and delete to avoid issues with side effects
		
		// TODO 14: Remove the @Ignore on this test method.
		//	Create a new Beneficiary called "David" for the account with id 1 
		//	(POST the String "David" to the "/accounts/{accountId}/beneficiaries" URL).
		// 	Store the returned location URI in a variable.
		URI beneficiaryLocation = restTemplate.postForLocation(BASE_URL + "/accounts/{accountId}/beneficiaries" , "David");
		
		// TODO 15: Retrieve the Beneficiary you just created from the location that was returned
		Beneficiary newBeneficiary = restTemplate.getForObject(beneficiaryLocation, Beneficiary.class);
		
		assertNotNull(newBeneficiary);
		assertEquals("David", newBeneficiary.getName());
		
		// TODO 16: Delete the new Beneficiary
		
		try {
			//	TODO 17: Try to retrieve the new Beneficiary again.  
			//	You should get 404 Not Found.  If not, it is likely your delete was not successful. 
			
			fail("Should have received 404 Not Found after deleting beneficiary");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

	//	TODO 10: (OPTIONAL)	Monitor test execution using the TCP/IP monitor.
	//	In STS press Ctrl+3 and type 'tcp' in the popup,  then press Enter to open the TCP/IP Monitor View.
	//	Click the small arrow pointing downwards and choose "properties".
	//	Choose "Add..." to add a new monitor.  Set local monitoring port = 8081, host = "localhost", 
	//	port = 8080.  Use "Start" to launch the monitor.
	//	Above, adjust BASE_URL's port number to 8081 so all requests pass through the monitor.
	//	Re-run these tests and examine the results in the TCP/IP Monitor View.
	//	If your createAccount test method didn't work yet, then use the monitor to debug it.
	
	
}
