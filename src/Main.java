import java.util.ArrayList;
import java.util.List;
class Database{
    private static Database instance;
    private Database(){
        System.out.println("Подключение к базе данных установлено");
    }
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    public void executeQuery(String query) {
        System.out.println("Выполняется запрос: " + query);
    }
}
class Logger{
    private static Logger instance;
    private List<String> logs = new ArrayList<>();
    private Logger(){}
    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }
    public void addMessage(String message){
        logs.add(message);
        System.out.println("Сообщение добавлено");
    }
    public void getLogs(){
        System.out.println("Логи системы:");
        for(String log : logs){
            System.out.println(log);
        }
    }
}
enum Status{
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}
class Order{
    String name;
    int price;
    int quantity;
    Status status;
    public Order(String name, int price, int quantity, Status status){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
    public void changeStatus(Status status){
        if (status == Status.CANCELLED || this.status == Status.DELIVERED) {
            System.out.println("Нельзя отменить доставленный заказ");
            return;
        }
        else {
            this.status = status;
            System.out.println("Статус изменён");
        }
    }
    @Override
    public String toString() {
        return "Заказ: " + "название - " + name + ", цена - " + price + "количество - " + quantity + ", status - " + status + '}';
    }
}
enum Season {
    WINTER, SPRING, SUMMER, AUTUMN
}
public class Main {
    public static void main(String[] args) {
        //1
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        database1.executeQuery("select * from student");
        if (database1 == database2) {
            System.out.println("Обе переменные ссылаются на один и тот же экземпляр");
        } else {
            System.out.println("Объекты разные");
        }
        //2
        Logger logger = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        logger.addMessage("hi");
        logger2.addMessage("hello");
        logger.getLogs();
        //3
        Order glass = new Order("Glass", 10, 10, Status.NEW);
        glass.changeStatus(Status.DELIVERED);
        glass.changeStatus(Status.CANCELLED);
        System.out.println(glass.toString());
        //4
        for (Season season : Season.values()) {
            System.out.println(season + " - " + getSeasonName(season));
        }
    }
    public static String getSeasonName(Season season) {
        return switch (season) {
            case WINTER -> "Зима";
            case SPRING -> "Весна";
            case SUMMER -> "Лето";
            case AUTUMN -> "Осень";
        };
    }
}