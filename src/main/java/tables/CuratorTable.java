package tables;

import db.IDBConnector;
import db.MySqlConnector;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CuratorTable extends AbsTable {
    private final static String TABLE_NAME = "Curator";

    public static Map<String, String> userColumns = new LinkedHashMap<>() {{
        put("id", "varchar(10)");
        put("fio", "varchar(10)");
    }};


    public CuratorTable(IDBConnector connector) {
        super(TABLE_NAME, userColumns,connector);

    }

    @Override
    public String toString() {
        return ("----------Успешно создана таблица------ " + TABLE_NAME);
    }

    public void insert(Curator curator) {
        String sqlRequest_s3 = String.format("INSERT INTO curator (id,fio) VALUES ('%s','%s')",
                curator.getId(), curator.getFio());
        System.out.println(sqlRequest_s3);
        //db = new MySqlConnector();
        db.executeRequest(sqlRequest_s3);
        //db.close();
    }

}
