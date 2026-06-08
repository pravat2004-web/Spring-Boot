package org.example.driver;

import org.example.dao.DbUtil;
import org.example.entity.Employee;

import java.util.Scanner;

public class EmployeeDriver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DbUtil db = new DbUtil();

        DbUtil.connectToDb();

        int choice;

        do {

            System.out.println("\n1.Insert");
            System.out.println("2.Delete");
            System.out.println("3.Update");
            System.out.println("4.Display All");
            System.out.println("5.Get By Id");
            System.out.println("0.Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    Employee e1 = new Employee();

                    System.out.print("Enter Id: ");
                    e1.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    e1.setName(sc.nextLine());

                    System.out.print("Enter Department: ");
                    e1.setDepartment(sc.nextLine());

                    if (db.insert(e1) > 0)
                        System.out.println("Inserted Successfully");

                    break;

                case 2:

                    System.out.print("Enter Id: ");

                    if (db.deleteById(sc.nextInt()))
                        System.out.println("Deleted Successfully");
                    else
                        System.out.println("Employee Not Found");

                    break;

                case 3:

                    Employee e2 = new Employee();

                    System.out.print("Enter Id: ");
                    e2.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    e2.setName(sc.nextLine());

                    System.out.print("Enter New Department: ");
                    e2.setDepartment(sc.nextLine());

                    if (db.update(e2) > 0)
                        System.out.println("Updated Successfully");

                    break;

                case 4:
                    db.displayAll();
                    break;

                case 5:

                    System.out.print("Enter Id: ");
                    db.getById(sc.nextInt());

                    break;

                case 0:
                    System.out.println("Thank You");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 0);
    }
}
