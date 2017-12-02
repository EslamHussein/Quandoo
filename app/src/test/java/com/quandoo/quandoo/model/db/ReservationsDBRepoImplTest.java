package com.quandoo.quandoo.model.db;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.dao.CustomerDao;
import com.quandoo.quandoo.model.db.dao.TableDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;



public class ReservationsDBRepoImplTest extends BaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    private CustomerDao customerDao;
    private TableDao tableDao;
    private ReservationDataBase db;
    private List<Customer> customers;
    private List<Table> tables;

    private ReservationsDBRepo reservationsDBRepo;


    @Before
    public void setUp() throws Exception {

        Context context = mock(Context.class);
        db = Room.inMemoryDatabaseBuilder(context, ReservationDataBase.class)
                .allowMainThreadQueries().build();
        customerDao = db.customerDao();
        tableDao = db.tableDao();

        customers = new ArrayList<>();
        customers.add(new Customer(1, "Eslam", "Hussein"));
        customers.add(new Customer(2, "Eslam2", "Hussein2"));


        tables = new ArrayList<>();
        tables.add(new Table(1, false));
        tables.add(new Table(2, true));

        reservationsDBRepo = spy(new ReservationsDBRepoImpl(db));
        initMocks(this);


    }

    @Test
    public void shouldCreateDatabase() {
        assertNotNull(db);
    }

    @Test
    public void getCustomersTest() throws Exception {
        customerDao.insertAll(customers);
        TestObserver<List<Customer>> testObserver = new TestObserver<>();
        reservationsDBRepo.getCustomers().subscribe(testObserver);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        customerDao.deleteAll();

    }

    @Test
    public void getAllTablesTest() {
        tableDao.insertAll(tables);
        List<Table> actualTables = reservationsDBRepo.getTables();
        assertEquals(tables.size(), actualTables.size());
        tableDao.deleteAll();

    }

    @Test
    public void addCustomersTest() {
        reservationsDBRepo.addCustomers(customers);
        List<Customer> customersActual = customerDao.getAll();
        assertEquals(customers.size(), customersActual.size());
        assertEquals(customers.get(0).getId(), customersActual.get(0).getId());
        assertEquals(customers.get(0).getCustomerFirstName(), customersActual.get(0).getCustomerFirstName());
        assertEquals(customers.get(0).getCustomerLastName(), customersActual.get(0).getCustomerLastName());
        customerDao.deleteAll();

    }

    @Test
    public void addTablesTest() {
        List<Boolean> booleanTablesList = new ArrayList<>();
        booleanTablesList.add(true);
        booleanTablesList.add(false);
        booleanTablesList.add(true);
        booleanTablesList.add(true);
        reservationsDBRepo.addTables(booleanTablesList);

        List<Table> tablesDB = tableDao.getAll();

        assertEquals(booleanTablesList.size(), tablesDB.size());
        tableDao.insertAll(tables);

    }

    @Test
    public void updateTableTest() {
        List<Boolean> booleanTablesList = new ArrayList<>();
        booleanTablesList.add(true);
        reservationsDBRepo.addTables(booleanTablesList);
        List<Table> tablesDB = reservationsDBRepo.getTables();

        TestObserver<Boolean> testObserver = new TestObserver<>();

        assertEquals(tablesDB.size(), 1);
        reservationsDBRepo.updateTable(tablesDB.get(0)).subscribe(testObserver);

        testObserver.assertComplete();
        testObserver.assertNoErrors();
        tableDao.deleteAll();
    }

    @Test
    public void clearRealmDataBaseReservationTest(){

        List<Boolean> booleanTablesList = new ArrayList<>();
        booleanTablesList.add(true);
        reservationsDBRepo.addTables(booleanTablesList);
        List<Table> tablesDB = reservationsDBRepo.getTables();
        assertEquals(tablesDB.size(), 1);
        tableDao.deleteAll();
        List<Table> tablesDBAfterDelete = reservationsDBRepo.getTables();
        assertEquals(tablesDBAfterDelete.size(), 0);

    }
    @After
    public void tearDown() throws Exception {
        db.close();
    }
}