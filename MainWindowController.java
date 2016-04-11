package main;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
//импорт библиотеки JavaFX для создания оконной формы
public class MainWindowController {
    private static final String WARNING_TITLE = "Некорректный формат поля";
    private static final String WARNING_PRICE_CONTENT = "Цена должна иметь числовое значение.";
    private static final String WARNING_QUANTITY_CONTENT = "Количество должно быть целым числом.";
    private static final String WARNING_EMPTY_FIELD = "Все поля должны быть заполнены.";
    // Определение строк для сообщений ошибок
    @FXML
    private TableView<Item> table;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField totalTextField;
    @FXML
    private Button addEditButton;
    @FXML
    private Button cleanButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    private int selectedIndexForEdit;
    // Определение имен для элементов формы
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    {
        alert.setTitle(WARNING_TITLE);
        alert.setHeaderText(null);
    }
    // Вывод предупреждения
    @FXML
    private void addEditButtonAction(ActionEvent event) {
        if (addEditButton.getText().equals("Добавить")) {
            addItem();
        } else {
            editItem();
        }
    }
    //Добавление и Редактирование соответствующей записи по нажати кнопки
    @FXML
    private void deleteButtonAction(ActionEvent event) {
        table.getItems().remove(getSelectedIndex());
        calcTotal();
    }
    //Удаление элемента по нажатию кнопки "Удалить"
    @FXML
    private void cleanButtonAction(ActionEvent event) {
        table.getItems().clear();
        calcTotal();
    }
    //Очистка таблицы по нажатию кнопки "Очистить"
    @FXML
    private void editButtonAction(ActionEvent event) {
        ObservableList<Item> data = table.getItems();
        selectedIndexForEdit = getSelectedIndex();
        nameTextField.setText(data.get(selectedIndexForEdit).getName());
        quantityTextField.setText(data.get(selectedIndexForEdit).getQuantity());
        priceTextField.setText(data.get(selectedIndexForEdit).getPrice());
        addEditButton.setText("OK");
        disableComponents(true);
    }
    //Запуск процедуры редактирования записи по нажатию кнопки "Изменить"
    private void addItem() {
        if (isFieldsEmpty() || !isFieldsCorrect()) {
            return;
        }
        ObservableList<Item> data = table.getItems();
        data.add(new Item(nameTextField.getText(), quantityTextField.getText(), priceTextField.getText()));
        cleanFields();
        calcTotal();
    }
    //Добавление записи в таблицу. Данные берутся из соответствующих текстовых полей
    private void editItem() {
        if (isFieldsEmpty() || !isFieldsCorrect()) {
            return;
        }
        ObservableList<Item> data = table.getItems();
        data.get(selectedIndexForEdit).setName(nameTextField.getText());
        data.get(selectedIndexForEdit).setQuantity(quantityTextField.getText());
        data.get(selectedIndexForEdit).setPrice(priceTextField.getText());
        cleanFields();
        addEditButton.setText("Добавить");
        disableComponents(false);
        refreshTable();
        calcTotal();
    }
    //Редактирование  записи. Данные берутся из соответствующих полей.
    private boolean isFieldsCorrect() {
        try {
            Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            alert.setContentText(WARNING_QUANTITY_CONTENT);
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException e) {
            alert.setContentText(WARNING_PRICE_CONTENT);
            alert.showAndWait();
            return false;
        }
        return true;
    }
    // Проверка корректности записей "Количество" и "Цена"
    private void cleanFields() {
        nameTextField.setText("");
        priceTextField.setText("");
        quantityTextField.setText("");
    }
    //Операция очистки текстовых полей
    private boolean isFieldsEmpty() {
        if (nameTextField.getText().trim().equals("") || priceTextField.getText().trim().equals("")
                || quantityTextField.getText().trim().equals("")) {
            alert.setContentText(WARNING_EMPTY_FIELD);
            alert.showAndWait();
            return true;
        }
        return false;
    }
   // Проверка наличия данных в текстовых полях. Если ничего нет - вывод ошибки
    private int getSelectedIndex() {
        return table.getSelectionModel().getSelectedIndices().get(0);
    }
    // Функция возвращает позицию выбранной записи в таблице
    private void refreshTable() {
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
    }
    //Обновление таблицы
    private void disableComponents(boolean state) {
        deleteButton.setDisable(state);
        editButton.setDisable(state);
        cleanButton.setDisable(state);
        table.setDisable(state);
    }
    //Управление элементами формы.
    void calcTotal() {
        ObservableList<Item> data = table.getItems();
        double result = 0;
        for (Item one : data) {
            result += Double.parseDouble(one.getPrice()) * Integer.parseInt(one.getQuantity());
        }
        totalTextField.setText(Double.toString(result));
    }
    //Основная функция программы - подсчет общей стоимости элементов таблицы.
}
