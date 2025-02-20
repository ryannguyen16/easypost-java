package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Batch;
import com.easypost.model.BatchCollection;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BatchTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("batch", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Batch batch = createBasicBatch();

        assertInstanceOf(Batch.class, batch);
        assertTrue(batch.getId().startsWith("batch_"));
        assertNotNull(batch.getShipments());
    }

    /**
     * Create a batch.
     *
     * @return Batch object.
     */
    private static Batch createBasicBatch() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        List<Object> shipments = new ArrayList<>();
        shipments.add(Fixture.basicShipment());
        params.put("shipments", shipments);

        return Batch.create(params);
    }

    /**
     * Test retrieving a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Batch batch = createBasicBatch();

        Batch retrievedBatch = Batch.retrieve(batch.getId());

        assertInstanceOf(Batch.class, batch);
        // Must compare IDs since elements of batch (i.e. status) may be different
        assertEquals(batch.getId(), retrievedBatch.getId());
    }

    /**
     * Test retrieving all batches.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());

        BatchCollection batches = Batch.all(params);

        List<Batch> batchesList = batches.getBatches();

        assertTrue(batchesList.size() <= Fixture.pageSize());
        assertNotNull(batches.getHasMore());
        assertTrue(batchesList.stream().allMatch(batch -> batch instanceof Batch));
    }

    /**
     * Test creating and buying a Batch in a single call.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndBuy() throws EasyPostException {
        vcr.setUpTest("create_and_buy");

        Map<String, Object> params = new HashMap<>();

        List<Object> shipmentData = new ArrayList<>();
        shipmentData.add(Fixture.oneCallBuyShipment());

        params.put("shipments", shipmentData);

        Batch batch = Batch.createAndBuy(params);

        assertInstanceOf(Batch.class, batch);
        assertTrue(batch.getId().startsWith("batch_"));
        assertEquals(1, batch.getNumShipments().intValue());
    }

    /**
     * Test buying a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException {
        vcr.setUpTest("buy");

        Batch batch = createOneCallBuyBatch();

        batch = batch.buy();

        assertInstanceOf(Batch.class, batch);
        assertEquals(1, batch.getNumShipments().intValue());
    }

    private static Batch createOneCallBuyBatch() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        List<Object> shipments = new ArrayList<>();
        shipments.add(Fixture.oneCallBuyShipment());
        params.put("shipments", shipments);

        return Batch.create(params);
    }

    /**
     * Test creating a scanform for a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateScanForm() throws EasyPostException, InterruptedException {
        vcr.setUpTest("create_scanform");

        Batch batch = createOneCallBuyBatch();
        batch = batch.buy();

        if (vcr.isRecording()) {
            Thread.sleep(10000); // Wait enough time for processing
        }

        Batch batchWithScanForm = batch.createScanForm();

        // We can't assert anything meaningful here
        // because the scanform gets queued for generation and may not be immediately available
        assertInstanceOf(Batch.class, batchWithScanForm);
    }

    /**
     * Test adding and removing a shipment from a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAddRemoveShipment() throws EasyPostException {
        vcr.setUpTest("add_remove_shipment");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        Batch batch = Batch.create();

        List<Shipment> shipmentData = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        shipmentData.add(shipment);
        params.put("shipments", shipmentData);

        Batch batchWithAddedShipment = batch.addShipments(params);

        assertEquals(1, batchWithAddedShipment.getNumShipments().intValue());

        Batch batchWithoutShipment = batch.removeShipments(params);

        assertEquals(0, batchWithoutShipment.getNumShipments().intValue());
    }

    /**
     * Test generating a label for a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLabel() throws EasyPostException, InterruptedException {
        vcr.setUpTest("label");

        Batch batch = createOneCallBuyBatch();

        batch = batch.buy();

        if (vcr.isRecording()) {
            Thread.sleep(10000); // Wait enough time for processing
        }

        Map<String, Object> params = new HashMap<>();
        params.put("file_format", "ZPL");

        Batch batchWithLabel = batch.label(params);

        // We can't assert anything meaningful here
        // because the label gets queued for generation and may not be immediately available
        assertInstanceOf(Batch.class, batchWithLabel);
    }
}
