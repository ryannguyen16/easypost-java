package com.easypost.beta;

import com.easypost.Fixture;
import com.easypost.TestUtils;
import com.easypost.exception.EasyPostException;
import com.easypost.model.beta.EndShipper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class EndShipperTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("end_shipper", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test creating an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        EndShipper endShipper = createEndShipper();

        assertInstanceOf(EndShipper.class, endShipper);
        assertTrue(endShipper.getId().startsWith("es_"));
        assertEquals("388 TOWNSEND ST APT 20", endShipper.getStreet1());
    }

    /**
     * Create an EndShipper.
     *
     * @return EndShipper object.
     */
    private static EndShipper createEndShipper() throws EasyPostException {
        return EndShipper.create(Fixture.endShipperAddress());
    }

    /**
     * Test retrieving an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        EndShipper endShipper = createEndShipper();

        EndShipper retrievedEndShipper = EndShipper.retrieve(endShipper.getId());

        assertInstanceOf(EndShipper.class, retrievedEndShipper);
        assertEquals(endShipper.getStreet1(), retrievedEndShipper.getStreet1());
    }

    /**
     * Test retrieving all EndShipper objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());

        List<EndShipper> endShippers = EndShipper.all(params);

        assertTrue(endShippers.size() <= Fixture.pageSize());
        assertTrue(endShippers.stream().allMatch(endShipper -> endShipper instanceof EndShipper));
    }

    /**
     * Test updating an EndShipper object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        EndShipper endShipper = createEndShipper();

        String testName = "NEW NAME"; // all caps since server-side verification will auto-capitalize it in response
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("name", testName);
        updateParams.put("company", "EasyPost");
        updateParams.put("street1", "388 Townsend St");
        updateParams.put("street2", "Apt 20");
        updateParams.put("city", "San Francisco");
        updateParams.put("state", "CA");
        updateParams.put("zip", "94107");
        updateParams.put("country", "US");
        updateParams.put("phone", "9999999999");
        updateParams.put("email", "test@example.com");

        EndShipper updatedEndShipper = endShipper.update(updateParams);

        assertInstanceOf(EndShipper.class, updatedEndShipper);
        assertTrue(updatedEndShipper.getId().startsWith("es_"));
        assertEquals(testName, updatedEndShipper.getName());
    }
}
