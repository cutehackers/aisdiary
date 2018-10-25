package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain;

import android.database.sqlite.SQLiteDatabase;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;
import com.ais.mobile.jhlee.aisdiary.base.Database;

import java.util.List;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class AboutAisDataSource {

    private static AboutAisDataSource INSTANCE;

    private LecturerDao lecturerDao = new LecturerDao();


    private AboutAisDataSource() { }

    public static AboutAisDataSource instance() {
        if (INSTANCE == null) {
            synchronized (AboutAisDataSource.class) {
                INSTANCE = new AboutAisDataSource();
            }
        }
        return INSTANCE;
    }

    public final void createTables(SQLiteDatabase database) {
        database.execSQL(LecturerDao.SQL_CREATE);
    }

    public final void dropTables(SQLiteDatabase database) {
        database.execSQL(LecturerDao.SQL_DROP);
    }

    public long getLecturerCount() {
        return lecturerDao.getCount(Database.instance().getReadableDatabase());
    }

    public List<Lecturer> getLecturerList() {
        return lecturerDao.getList(Database.instance().getReadableDatabase());
    }

    public List<Lecturer> getLecturerListByName(String searchQuery) {
        return lecturerDao.getListByName(Database.instance().getReadableDatabase(), searchQuery);
    }

    public long add(Lecturer lecturer) {
        return lecturerDao.add(Database.instance().getWritableDatabase(), lecturer);
    }

    public void addSampleModels(SQLiteDatabase database) {
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setName("Dr Michael Watts");
        lecturer1.setPhone("845 5606 ext 220");
        lecturer1.setMail("michaelw@ais.ac.nz");
        lecturer1.setQualifications("BSc (Hons), PhD Otago");

        Lecturer lecturer2 = new Lecturer();
        lecturer2.setName("Dr Saghir Ahmad");
        lecturer2.setPhone("845 5606 ext 237");
        lecturer2.setQualifications("PhD, MSc (Applied Mathematics), BSc");
        lecturer2.setMainTeachingField("Mathematics (discrete, engineering, finance) and programming");

        Lecturer lecturer3 = new Lecturer();
        lecturer3.setName("Dr Neda Abdelhamid");
        lecturer3.setMail("nedah@ais.ac.nz");
        lecturer3.setQualifications("PhD (Computer Science), B Engineering (Software Engineering)");
        lecturer3.setMainTeachingField("Systems analysis and design, management information systems, e-business solutions");

        Lecturer lecturer4 = new Lecturer();
        lecturer4.setName("Kourosh Ahmadi");
        lecturer4.setPhone("845 5606 ext 237");
        lecturer4.setMail("kourosha@ais.ac.nz");
        lecturer4.setQualifications("BEng(Hons), MSc (Systems Engineering), PGCE (Post Graduate in Education), PGCM (Post Graduate in Marketing) UK, MCT(Alumni), MCITP, MCTS, MCSE, MCSA, MCDST, CCAI, CCNA, CTT (Certified Technical Trainer), ITIL");
        lecturer4.setMainTeachingField("Networking, Security, Microsoft, Cisco, Hardware/Software training and ITIL");

        Lecturer lecturer5 = new Lecturer();
        lecturer5.setName("Havea Hikule'o Fonua");
        lecturer5.setPhone("845 5606 ext 214");
        lecturer5.setMail("haveaf@ais.ac.nz");
        lecturer5.setQualifications("BComSci (Bachelor of Computer Science) - Wollongongo, Australia, MDigCom (Master of Digital Communication) - Monash, Australia");
        lecturer5.setMainTeachingField("C# Programming Language (C/C++), Database Management Systems, Software Testing, Information Technology System");

        Lecturer lecturer6 = new Lecturer();
        lecturer6.setName("Rakesh Kumar");
        lecturer6.setPhone("845 5606 ext 237");
        lecturer6.setMail("rakeshk@ais.ac.nz");
        lecturer6.setQualifications("Master of Computer Applications, Post Graduate Diploma in computing");
        lecturer6.setMainTeachingField("OOP, Computer Networking, System Analysis and Design, Requirement Modelling");

        Lecturer lecturer7 = new Lecturer();
        lecturer7.setName("Shuaib Memon");
        lecturer7.setPhone("845 5606 ext 214");
        lecturer7.setMail("shuaibm@ais.ac.nz");
        lecturer7.setQualifications("MSc (Hons) Computer Science, BSc (Hons) Computer Science, Diploma in Electronics");
        lecturer7.setMainTeachingField("Programming, Web Development and Databases");

        Lecturer lecturer8 = new Lecturer();
        lecturer8.setName("Dr Amir Moravejosharieh");
        lecturer8.setMail("amirm@ais.ac.nz");
        lecturer8.setQualifications("PhD (Computer Science and Software Engineering), M Computer Science and Information Technology, B Computer Engineering Hardware");
        lecturer8.setMainTeachingField("Software design, IT administration");

        lecturerDao.add(database, lecturer1);
        lecturerDao.add(database, lecturer2);
        lecturerDao.add(database, lecturer3);
        lecturerDao.add(database, lecturer4);
        lecturerDao.add(database, lecturer5);
        lecturerDao.add(database, lecturer6);
        lecturerDao.add(database, lecturer7);
        lecturerDao.add(database, lecturer8);

    }
}
