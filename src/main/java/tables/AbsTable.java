package tables;

import db.IDBConnector;
import db.MySqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class AbsTable {
    private String tableName;
    private Map<String, String> columns;
    IDBConnector db;

    public AbsTable(String tableName, Map<String, String> columns,IDBConnector connector) {
        this.tableName = tableName;
        this.columns = columns;
        this.db = connector;


    }

    // Создание таблицы
    public void create() {
        this.columns = columns;
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        //db = new MySqlConnector();
        db.executeRequest(sqlRequest);
        //db.close();
    }

    private String convertMapColumnsToString() {
        final String result = columns.entrySet().stream()
                .map((Map.Entry entry) -> String.format("%s %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        return result;
    }


    // Выборка данных из таблицы
    public String selectAll() {
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        return select(sqlRequest);
    }

    //"Задание№5/Информация о всех студентах включая название группы и имя куратора"
    public String selectStudentTable() {
        final String sqlRequest = String.format("SELECT s2.id, s2.fio, s2.sex,s2.id_group,g2.name,c2.fio " +
                "FROM student s2 join groop g2 on s2.id_group = g2.id join curator c2 on g2.id_curator = c2.id");
        return select(sqlRequest);
    }

    //"Задание№6/Вывести на экран количество студентов"
    public String selectCountStudent() {
        final String sqlRequest = String.format("SELECT count(id) FROM student s2");
        return select(sqlRequest);
    }

    // "Задание№7/Вывести студенток"
    public String selectStudentWoman() {
        final String sqlRequest = String.format("SELECT * FROM student s2 WHERE sex in (\"Ж\")");
        return select(sqlRequest);
    }

    // "Задание№8/Обновить данные по группе сменив куратора

    public void updateCurator(){
        String StringsqlRequest_s1 = ("UPDATE curator SET fio = (case when id = 1 then 'Один' " +
                "when id = 2 then 'Два' " +
                "when id = 3 then 'три' " +
                "when id = 4 then 'четыре' " +
                "end ) " +
                "where id  in ('1','2','3','4') ");

        db = new MySqlConnector();
        db.executeRequest(StringsqlRequest_s1);
    }

    //"Задание№9/Вывести список групп с их кураторами"
    public String selectStudentCurator() {
        final String sqlRequest = String.format("SELECT g2.id,g2.name,g2.id_curator,c2.fio " +
                "FROM groop g2 JOIN curator c2 on g2.id_curator = c2.id ");
        return select(sqlRequest);
    }


    // "Задание№10/Вывести на экран студентов из определенной группы(поиск по имени группы)"
    public String selectStudentGroop() {
        final String sqlRequest = String.format("SELECT s.fio,g.name  FROM student s " +
                "JOIN groop g on s.id_group = g.id WHERE g.name in (\"математика\",\"русский\",\"физика\") ");
        return select(sqlRequest);
    }

    // Метод на проверку
    private String select(String sqlRequest) {
        //db = new MySqlConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        // колличество колонок в результирующем запросе
        try {
            int columnCount = rs.getMetaData().getColumnCount();
            // перебор строк с данными
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //db.close();
        }
        return sqlRequest;
    }


}
