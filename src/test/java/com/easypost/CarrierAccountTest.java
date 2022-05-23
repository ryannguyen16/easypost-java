package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CarrierAccount;
import com.easypost.model.CarrierType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarrierAccountTest {

    private static TestUtils.VCR _vcr;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        _vcr = new TestUtils.VCR("address", TestUtils.ApiKey.PRODUCTION);
    }

    private static CarrierAccount createBasicCarrierAccount() throws EasyPostException {
        return CarrierAccount.create(Fixture.basicCarrierAccount());
    }

    /**
     * Test creating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(1)
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        CarrierAccount carrierAccount = CarrierAccount.create(Fixture.basicCarrierAccount());

        assertTrue(carrierAccount instanceof CarrierAccount);
        assertTrue(carrierAccount.getId().startsWith("ca_"));

        carrierAccount.delete(); // Delete the carrier account once it's done being tested.
    }

    /**
     * Test retrieving a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(2)
    public void testRetrieve() throws EasyPostException {
        _vcr.setUpTest("retrieve");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        CarrierAccount retrieveCarrierAccount = CarrierAccount.retrieve(carrierAccount.getId());

        assertTrue(retrieveCarrierAccount instanceof CarrierAccount);
        assertTrue(retrieveCarrierAccount.getId().startsWith("ca_"));
        assertThat(carrierAccount).usingRecursiveComparison().isEqualTo(retrieveCarrierAccount);
    }

    /**
     * Test retrieving all carrier accounts.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(3)
    public void testAll() throws EasyPostException {
        _vcr.setUpTest("all");

        List<CarrierAccount> carrierAccounts = CarrierAccount.all(null);

        assertTrue(carrierAccounts.stream().allMatch(carrier -> carrier instanceof CarrierAccount));
    }

    /**
     * Test updating a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(4)
    public void testUpdate() throws EasyPostException {
        _vcr.setUpTest("update");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        String testDescription = "My custom description";
        Map<String, Object> updateParams = new HashMap<>();

        updateParams.put("description", testDescription);

        CarrierAccount updatedCarrierAccount = carrierAccount.update(updateParams);

        assertTrue(updatedCarrierAccount instanceof CarrierAccount);
        assertTrue(updatedCarrierAccount.getId().startsWith("ca_"));
        assertEquals(testDescription, updatedCarrierAccount.getDescription());
    }

    /**
     * Test deleting a carrier account.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Order(5)
    public void testDelete() throws EasyPostException {
        _vcr.setUpTest("delete");

        CarrierAccount carrierAccount = createBasicCarrierAccount();

        carrierAccount.delete();

        assertTrue(carrierAccount instanceof CarrierAccount);
    }

    /**
     * Test retrieving the carrier account types available.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testTypes() throws EasyPostException {
        _vcr.setUpTest("types");

        List<CarrierType> types = CarrierType.all();

        assertTrue(types instanceof List);
    }
}
