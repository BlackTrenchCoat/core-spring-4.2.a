package accounts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;

import rewards.internal.account.Account;
import rewards.internal.account.Beneficiary;
import accounts.internal.StubAccountManager;

/**
 * A JUnit test case testing the AccountController.
 */
public class AccountControllerTests {

	private AccountController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountController(new StubAccountManager());
	}

	@Test
	public void testHandleDetailsRequest() {
		Account account = controller.accountDetails(0);
		assertNotNull(account);
		assertEquals(Long.valueOf(0), account.getEntityId());
	}

	@Test
	public void testHandleSummaryRequest() {
		List<Account> accounts = controller.accountSummary();
		assertNotNull(accounts);
		assertEquals(1, accounts.size());
		assertEquals(Long.valueOf(0), accounts.get(0).getEntityId());
	}

	@Test
	public void testCreateAccount() {
		Account newAccount = new Account("11223344", "Test");
		StringBuffer url = new StringBuffer("http://accounts");
		HttpEntity<?> result = controller.createAccount(newAccount, url);
		assertNotNull(result);

		// See StubAccountManager.nextEntityId - initialized to 3
		assertEquals("http://accounts/3", result.getHeaders().getLocation()
				.toString());
	}

	@Test
	public void testGetBeneficiary() {
		Beneficiary beneficiary = controller.getBeneficiary(0, "Corgan");
		assertNotNull(beneficiary);
		assertEquals(Long.valueOf(1), beneficiary.getEntityId());
	}

	@Test
	public void testAddBeneficiary() {
		StringBuffer url = new StringBuffer("http://accounts/0/beneficiaries");
		HttpEntity<?> result = controller.addBeneficiary(0L, "Test2", url);
		assertNotNull(result);
		assertEquals("http://accounts/0/beneficiaries/Test2", result
				.getHeaders().getLocation().toString());
	}

	@Test
	public void testDeleteBeneficiary() {
		controller.removeBeneficiary(0L, "Corgan");
	}

	@Test
	public void testDeleteBeneficiaryFail() {
		try {
			controller.removeBeneficiary(0L, "Fred");
			Assert.fail("No such beneficiary 'Fred', "
					+ "IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			// Expected result
		}
	}

}
