package tables;

import java.util.List;

public class Curator {
    private String id;
    private String fio;

    public Curator(String id,String fio){
        this.id = id;
        this.fio = fio;
    }

    // Список колонок в таблицы curators
    public static List<Curator> curators = List.of(
            new Curator("1","Тащи"),
            new Curator("2","тарасов"),
            new Curator("3","Алексеев"),
            new Curator("4","Рыбаков")
    );

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getFio(){
        return fio;
    }

    public void setFio(String fio){
        this.fio = fio;
    }
}
