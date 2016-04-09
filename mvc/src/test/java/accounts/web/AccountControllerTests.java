package accounts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import rewards.internal.account.Account;
import accounts.internal.StubAccountManager;

/**
 * A JUnit test case testing the AccountController. The AccountController
 * has two handler methods, therefore, two tests.
 */
public class AccountControllerTests {

	private AccountController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountController(new StubAccountManager());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleListRequest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.accountList(model);

		List<Account> accounts = (List<Account>) model.get("accounts");
		assertNotNull(accounts);
		assertEquals(1, accounts.size());
		assertEquals(Long.valueOf(0), accounts.get(0).getEntityId());
		assertEquals("accountList", viewName);
	}

	//	TODO-03: Add a test for the accountDetails() method of AccountController.
	//	Use the test method above for hints.  Supply 0 for the ID value to retrieve.
	//	Create assertions for model contents and view name.
	//	When complete run the test.  It should pass.
	
	@Test
	public void testHandleDetailsRequest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.accountDetails(new Long(0), model);
		
		Account account = (Account)model.get("account");
		assertEquals(account.getNumber(),"123456789");
		assertEquals(account.getName(), "Keith and Keri Donald");
		assertEquals("accountDetails", viewName);
	}

}
