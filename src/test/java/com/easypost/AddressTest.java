package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.AddressCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {
    private static TestUtils.VCR _vcr;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException{
        _vcr = new TestUtils.VCR("address", TestUtils.ApiKey.TEST);
    }

    public static Address createBasicAddress() throws EasyPostException {
        return Address.create(Fixture.basicAddress());
    }

    /**
     * Test creating an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        _vcr.setUpTest("create");

        Address address = createBasicAddress();

        assertTrue(address instanceof Address);
        assertEquals("388 Townsend St", address.getStreet1());
        assertTrue(address.getId().startsWith("adr_"));
    }

    /**
     * Test creating an address with verify_strict param.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerifyStrict() throws EasyPostException {
        Map<String, Object> addressData = Fixture.basicAddress();
        List<Boolean> verificationList = new ArrayList<>();
        
        verificationList.add(true);
        addressData.put("verify_strict", verificationList);

        Address address = Address.create(addressData);

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", address.getStreet1());
    }

    /**
     * Test retrieving an address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException{
        Address address = createBasicAddress();
        Address retrievedAddress = Address.retrieve(address.getId());

        assertTrue(retrievedAddress instanceof Address);
        assertTrue(retrievedAddress.getId().startsWith("adr_"));
        assertEquals(address.getId(), retrievedAddress.getId());

    }

    /**
     * Test retrieving all addresses.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException{
        _vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();

        params.put("page_size", Fixture.pageSize());

        AddressCollection addresses = Address.all(params);

        List<Address> addressesList = addresses.getAddresses();

        assertTrue(addressesList.size() <= Fixture.pageSize());
        assertNotNull(addresses.getHasMore());
        assertTrue(addressesList.stream().allMatch(address -> address instanceof Address));
    }

    /**
     * Test creating a verified address.
     * We purposefully pass in slightly incorrect data to get the corrected address back once verified.
     *
     *  @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateVerify() throws EasyPostException {
        List<Boolean> verificationList = new ArrayList<>();
        verificationList.add(true);

        Map<String, Object> addressData = Fixture.incorrectAddressToVerify();
        addressData.put("verify", verificationList);

        Address address = Address.create(addressData);

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST FL 5", address.getStreet1());
        assertEquals("Invalid secondary information(Apt/Suite#)", address.getVerifications().getZip4().getErrors().get(0).getMessage());
    }

    /**
     * Test creating a verified address.
     * We purposefully pass in slightly incorrect data to get the corrected address back once verified.
     *
     *  @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndVerify() throws EasyPostException {
        Address address = Address.createAndVerify(Fixture.incorrectAddressToVerify());

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("417 MONTGOMERY ST FL 5", address.getStreet1());
    }

    /**
     * Test we can verify an already created address.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testVerify() throws EasyPostException {
        Address address = createBasicAddress();
        address.verify();

        assertTrue(address instanceof Address);
        assertTrue(address.getId().startsWith("adr_"));
        assertEquals("388 TOWNSEND ST APT 20", address.getStreet1());
    }
}
