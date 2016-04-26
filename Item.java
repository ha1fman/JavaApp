package main;
import javafx.beans.property.SimpleStringProperty;
//Импорт свойств таблицы из JavaFx
public class Item {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    //Свойство "Наименование"
    private final SimpleStringProperty quantity = new SimpleStringProperty("");
    //Свойство "Количество"
    private final SimpleStringProperty price = new SimpleStringProperty("");
    //Свойство "Цена"
    public Item() {
        this("", "", "");
    }
    //Создание пустого элемента
    public Item(String name, String quantity, String price) {
        setName(name);
        setQuantity(quantity);
        setPrice(price);
    }
    //Конструктор класса
    public void setName(String name) {
        this.name.set(name);
    }
    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }
    public void setPrice(String price) {
        this.price.set(price);
    }
    //Записывание данных в объект.
    public String getName() {
        return name.get();
    }
    public String getQuantity() {
        return quantity.get();
    }
    public String getPrice() {
        return price.get();
    }
    //Считывание из объекта.
}
