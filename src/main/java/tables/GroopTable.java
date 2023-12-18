package tables;

import db.IDBConnector;
import db.MySqlConnector;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


public class GroopTable extends AbsTable {
    private final static String TABLE_NAME = "Groop";

    public static Map<String, String> userColumns = new LinkedHashMap<>(){{
        put("id","varchar(10)");
        put("name","varchar(10)");
        put("id_curator","varchar(10)");
    }};


    public GroopTable(IDBConnector connector){
        super(TABLE_NAME,userColumns,connector);

    }


    @Override
    public String toString() {
        return ("-----------------Успешно создана таблица--------------- " + TABLE_NAME);
    }

    public void insert (Groop groop){
        String sqlRequest_s2 = String.format("INSERT INTO groop (id,name,id_curator) VALUES ('%s','%s','%s')",
                groop.getId(), groop.getName(), groop.getId_curator());
        System.out.println(sqlRequest_s2);
        //db = new MySqlConnector();
        db.executeRequest(sqlRequest_s2);
        //db.close();
    }


}
