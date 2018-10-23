package com.ais.mobile.jhlee.aisdiary.app.contactais.domain;

import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Campus;
import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Transport;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ContactAisDataSource {

    private static ContactAisDataSource INSTANCE;

    private CampusDao campusDao = new CampusDao();
    private TransportDao transportDao = new TransportDao();

    private ContactAisDataSource() { }

    public static ContactAisDataSource instance() {
        if (INSTANCE == null) {
            synchronized (ContactAisDataSource.class) {
                INSTANCE = new ContactAisDataSource();
            }
        }
        return INSTANCE;
    }

    public final void createTables(SQLiteDatabase database) {
        database.execSQL(CampusDao.SQL_CREATE);
        database.execSQL(TransportDao.SQL_CREATE);
    }

    public final void dropTables(SQLiteDatabase database) {
        database.execSQL(CampusDao.SQL_DROP);
        database.execSQL(TransportDao.SQL_DROP);
    }

    public List<Campus> getCampusList() {
        return campusDao.getList(Database.instance().getReadableDatabase());
    }

    public List<Transport> getTransportList() {
        return transportDao.getList(Database.instance().getReadableDatabase());
    }

    /**
     * Generate sample data Campus, Transport
     */
    public void addSampleModels(SQLiteDatabase database) {

        Campus helens = new Campus();
        helens.setName("St Helens Campus");
        helens.setAddress("28a Linwood Avenue, Mount Albert, Auckland 1025 New Zealand");
        helens.setPhone("(64 9) 815 1717");
        helens.setFax("(64 9) 815 1802");
        helens.setLatitude(-36.8735583);
        helens.setLongitude(174.72083739999994);
        campusDao.add(database, helens);

        Campus asquith = new Campus();
        asquith.setName("Asquith Campus");
        asquith.setAddress("120 Asquith Avenue Mt Albert Auckland 1025 New Zealand");
        asquith.setPhone("(64 9) 845 5606");
        asquith.setFax("(64 9) 845 5609");
        asquith.setLatitude(-36.8740637);
        asquith.setLongitude(174.7245438);
        campusDao.add(database, asquith);

        Transport tp1 = new Transport();
        tp1.setType("Bus");
        tp1.setTitle("To City (Downtown)");
        tp1.setAddress("Great North Rd near Western Springs Park, Auckland 1022, New Zealand");
        tp1.setLatitude(-36.86809);
        tp1.setLongitude(174.72487999999998);
        transportDao.add(database, tp1);

        Transport tp2 = new Transport();
        tp2.setType("Bus");
        tp2.setTitle("To West Auckland");
        tp2.setAddress("Great North Rd near St Lukes Rd, Auckland 1022, New Zealand");
        tp2.setLatitude(-36.86822);
        tp2.setLongitude(174.72482000000002);
        transportDao.add(database, tp2);

        Transport tp3 = new Transport();
        tp3.setType("Bus");
        tp3.setTitle("To City (Midtown)");
        tp3.setAddress("Opp 734 New North Rd, Auckland 1025, New Zealand");
        tp3.setLatitude(-36.8796);
        tp3.setLongitude(174.72636);
        transportDao.add(database, tp3);

        Transport tp4 = new Transport();
        tp4.setType("Bus");
        tp4.setTitle("To West Auckland");
        tp4.setAddress("756 New North Rd, Auckland 1025, New Zealand");
        tp4.setLatitude(-36.880007);
        tp4.setLongitude(174.725978);
        transportDao.add(database, tp4);

        Transport tp5 = new Transport();
        tp5.setType("Bus");
        tp5.setTitle("To City (Midtown)");
        tp5.setAddress("717 New North Rd, Auckland 1022, New Zealand");
        tp5.setLatitude(-36.878026486285975);
        tp5.setLongitude(174.72870022058487);
        transportDao.add(database, tp5);

        Transport tp6 = new Transport();
        tp6.setType("Bus");
        tp6.setTitle("To West Auckland");
        tp6.setAddress("700 New North Rd, Auckland 1022, New Zealand");
        tp6.setLatitude(-36.877866);
        tp6.setLongitude(174.729148);
        transportDao.add(database, tp6);

        Transport tp7 = new Transport();
        tp7.setType("Train");
        tp7.setTitle("To City (Downtown)");
        tp7.setAddress("1025, Baldwin Ave 9 Baldwin Ave, Mount Albert, Auckland 1025, New Zealand");
        tp7.setLatitude(-36.877698);
        tp7.setLongitude(174.720463);
        transportDao.add(database, tp7);

        Transport tp8 = new Transport();
        tp8.setType("Shuttle Service");
        tp8.setTitle("To AIS (Mornings - Monday to Friday)");
        tp8.setAddress("8 Customs Street, Auckland, 1010, New Zealand");
        tp8.setLatitude(-36.8447);
        tp8.setLongitude(174.76637);
        transportDao.add(database, tp8);
    }
}
