package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 =  new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("Toyota", 1);
      Car car2 = new Car("BMW", 2);
      Car car3 = new Car("Honda", 4);
      Car car4 = new Car("Corolla", 3);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);
      car4.setUser(user4);

      List <Car> list_of_cars = new ArrayList<>();

      list_of_cars.add(car1);
      list_of_cars.add(car2);
      list_of_cars.add(car3);
      list_of_cars.add(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);
      userService.addCar(car4);
      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println(user.toString());
      }

      for (Car i : list_of_cars) {
         System.out.println(i.getUser());
      }
      User user = userService.getUser("BMW", 2);
      System.out.println(user.toString());
      context.close();
   }
}