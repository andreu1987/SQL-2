import db.IDBConnector;
import db.MySqlConnector;
import tables.*;

import static tables.Curator.curators;
import static tables.Groop.groops;
import static tables.Student.students;

public class Main {
    //static IDBConnector db;

    public static void main(String[] args) {
        /// создание обьектов
        IDBConnector mySqlConnector = new MySqlConnector();
        StudentTable studentTable = new StudentTable(mySqlConnector);
        GroopTable groupTable = new GroopTable(mySqlConnector);
        CuratorTable curatorTable = new CuratorTable(mySqlConnector);


        // обьектам присваеваем метод создания таблиц
        studentTable.create();
        groupTable.create();
        curatorTable.create();

        System.out.println(studentTable);
            for (Student student: students) {
            studentTable.insert(student);
        }
        System.out.println("------------------Все данные из таблицы studentTable------------------");
        studentTable.selectAll();

        System.out.println(groupTable);
            for (Groop groop : groops) {
            groupTable.insert(groop);
        }
        System.out.println("-------------------Все данные из таблицы groopTable--------------------");
        groupTable.selectAll();

        System.out.println(curatorTable);
            for (Curator curator:curators) {
            curatorTable.insert(curator);
        }
        System.out.println("-------------------Все данные из таблицы curatorTable--------------------");
        curatorTable.selectAll();

        System.out.println("--Задание№5/-----Информация о всех студентах включая название группы и имя куратора------");
        studentTable.selectStudentTable();

        System.out.println("--Задание№6/-----Вывести на экран количество студентов-----------------------------------");
        studentTable.selectCountStudent();

        System.out.println("--Задание№7/-----Вывести студенток-------------------------------------------------------");
        studentTable.selectStudentWoman();

        System.out.println("--Задание№8/-----Обновить данные по группе сменив куратора-------------------------------");
        curatorTable.updateCurator();

        System.out.println("---------------------Обновленные данные по таблицы Curator-------------------------------");
        curatorTable.selectAll();

        System.out.println("--Задание№9/------Вывести список групп с их кураторами-----------------------------------");
        studentTable.selectStudentCurator();

        System.out.println("--Задание№10/--Вывести на экран студентов из определенной группы(поиск по имени группы)--");
        studentTable.selectStudentGroop();

        // Закрываем соединения
        mySqlConnector.close();
        System.out.println("------------Соединение успешно закрыто---------------------");
    }

}
