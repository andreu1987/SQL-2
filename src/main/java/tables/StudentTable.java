package tables;

import db.IDBConnector;
import db.MySqlConnector;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StudentTable extends AbsTable {
    private final static String TABLE_NAME = "Student";
    private  final static Map<String, String> USER_COLUMNS = new LinkedHashMap<>(){{
        put("id","varchar(10)");
        put("fio","varchar(100)");
        put("sex","varchar(10)");
        put("id_group","varchar(10)");
    }};



    public StudentTable(IDBConnector connector){
        super(TABLE_NAME, USER_COLUMNS,connector);
    }



    @Override
    public String toString() {
        return ("-----------Успешно создана таблица-------- " + TABLE_NAME);
    }

    public void insert(Student student){
        String sqlRequest_s1 = String.format("INSERT INTO student(id,fio,sex,id_group) VALUES ('%s','%s','%s','%s')",
                student.getId(),student.getFio(),student.getSex(),student.getId_group());
        System.out.println(sqlRequest_s1);
        //db = new MySqlConnector();
        db.executeRequest(sqlRequest_s1);
        //db.close();
    }
}
