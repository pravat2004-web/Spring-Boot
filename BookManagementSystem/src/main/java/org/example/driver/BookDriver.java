package org.example.driver;

import org.example.dao.DbUtil;
import org.example.entity.Book;

import java.util.Scanner;

public class BookDriver {

    private static int choice = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DbUtil dbUtil = new DbUtil();

        DbUtil.connectToDb();

        do {

            System.out.println("\n1.Insert" + "\n2.Delete" + "\n3.Update" + "\n4.Display All" + "\n5.Get By Id" + "\n0.Exit");
            System.out.println("enter your choice: ");
            choice = sc.nextInt();


            switch (choice) {

                case 1:

                    Book b1 = new Book();

                    System.out.println("Enter Book Id");
                    b1.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.println("Enter Book Title");
                    b1.setTitle(sc.nextLine());

                    System.out.println("Enter Author Name");
                    b1.setAuthor(sc.nextLine());

                    if (dbUtil.insert(b1) > 0) {
                        System.out.println("Book Inserted Successfully");
                    } else {
                        System.out.println("Issue In Inserting");
                    }
                    break;

                case 2:

                    System.out.println("Enter Book Id");

                    if (dbUtil.deleteById(sc.nextInt())) {
                        System.out.println("Book Deleted");
                    } else {
                        System.out.println("Book Not Found");
                    }
                    break;

                case 3:

                    Book b2 = new Book();

                    System.out.println("Enter Book Id");
                    b2.setId(sc.nextInt());

                    sc.nextLine();

                    System.out.println("Enter New Title");
                    b2.setTitle(sc.nextLine());

                    System.out.println("Enter New Author");
                    b2.setAuthor(sc.nextLine());

                    if (dbUtil.update(b2) > 0) {
                        System.out.println("Book Updated");
                    } else {
                        System.out.println("Issue In Updating");
                    }
                    break;

                case 4:

                    dbUtil.displayAll();
                    break;

                case 5:

                    System.out.println("Enter Book Id");
                    dbUtil.getById(sc.nextInt());
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