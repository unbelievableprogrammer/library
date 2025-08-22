import dao.BookDAO;
import dao.BookDAOImpl;
import model.Book;
import service.BookService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Properties props = DbConfig.load();

        try(Connection connection = DriverManager.getConnection(
                props.getProperty("DB_URL"),
                props.getProperty("DB_USER"),
                props.getProperty("DB_PASSWORD")
        )){
            BookService service = new BookService(new BookDAOImpl(connection));
            Scanner scanner = new Scanner(System.in);

            while(true){
                showMainMenu();
                System.out.println("Выберите действие: ");
                String input = scanner.nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число!");
                    continue;
                }
                switch(choice){
                    case 1:
                        System.out.println("Название: ");
                        String title = scanner.nextLine();
                        System.out.println("Автор: ");
                        String author = scanner.nextLine();
                        service.addBook(title, author);
                        break;
                    case 2:
                        service.listBooks().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Введите ID: ");
                        int id = scanner.nextInt();
                        if(service.getBook(id) != null)
                            System.out.println(service.getBook(id).toString());
                        else
                            System.out.println("Неверный ID");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Введите ID для удаления книги: ");
                        int idForDelete = scanner.nextInt();
                        service.deleteBook(idForDelete);
                        scanner.nextLine();
                        break;
                    case 5:
                        return;
                        default:
                            System.out.println("Неверный ввод!");

                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void showMainMenu() {
        System.out.println("\n1.Добавить книгу\n2.Получить список всех книг\n3.Получить информацию о книге по id\n4.Удалить книгу\n5.Выйти");
    }
}