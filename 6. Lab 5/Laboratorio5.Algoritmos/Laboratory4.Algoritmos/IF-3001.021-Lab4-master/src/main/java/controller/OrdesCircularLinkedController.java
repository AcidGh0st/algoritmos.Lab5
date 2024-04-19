package controller;

import domain.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class OrdesCircularLinkedController
{
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> idTable;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> dateTable;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> productTable;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> quantityTable;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> unitPriceTable;
    @javafx.fxml.FXML
    private TableColumn <List<String>, String> totalPriceTable1;
    @javafx.fxml.FXML
    private Button addButton;
    @javafx.fxml.FXML
    private Button sortIDButton;
    @javafx.fxml.FXML
    private Button sortName;
    @javafx.fxml.FXML
    private Button containsButton;
    @javafx.fxml.FXML
    private Button sizeButton;
    @javafx.fxml.FXML
    private Button getPrevTable;
    @javafx.fxml.FXML
    private Button getNextButton;
    @javafx.fxml.FXML
    private Button removeButton;
    @javafx.fxml.FXML
    private Button removeFirtsButton;
    @javafx.fxml.FXML
    private Button clearButton;
    @javafx.fxml.FXML
    private TableView tableView;
    @javafx.fxml.FXML
    private BorderPane bp;

    private CircularDoublyLinkedList orderList;
    private DoublyLinkedList productList;
    Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos las listas globales
        this.orderList = util.Utility.getOrderList();
        this.productList = util.Utility.getProductList();
        this.alert = util.UtilityFX.alert("Order..", "");
        this.idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(0))); //defino variable data
        this.dateTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(1)));
        this.productTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(2)));
        this.quantityTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(3)));
        this.unitPriceTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(4)));
        this.totalPriceTable1.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(5)));

        //Cargamos los datos en el tableView

            if(orderList!=null && !orderList.isEmpty())
                this.tableView.setItems(getData());

    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();

        if (orderList != null && !orderList.isEmpty()) {
            try {

                for (int i = 1; i <= orderList.size(); i++) {
                    Order order = (Order) orderList.getNode(i).data;
                    double unitPrice = getProduct(order.getProductId()).getPrice();
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(order.getId()));//envuelvo con un string valor entero
                    arrayList.add(String.valueOf(order.getOrderDate()));
                    arrayList.add(getProduct(order.getProductId()).getName());
                    arrayList.add(String.valueOf(order.getQuantity()));
                    arrayList.add("$" + unitPrice);
                    arrayList.add("$" + unitPrice * order.getQuantity());

                    //agregamos al arrayList
                    data.add(arrayList);
                }

            } catch (ListException ex) {
                alert.setContentText("There was an error in the process");
            }
        }

        return data;
    }

    private Product getProduct(int productId) {

        if (productList.isEmpty()) return null;

        try {
            for (int i = 1; i < productList.size() ; i++) {
                Product product = (Product) productList.getNode(i).data;
                if (product.getId() == productId){
                    return product;
                }
            }
        }catch (ListException ex){
            alert.setContentText(ex.getMessage());
        }
        return null;
    }

    @javafx.fxml.FXML
    public void addAction(ActionEvent actionEvent) {
        util.UtilityFX.loadPage("ucr.lab", "AddOders.fxml", bp);
    }

    @javafx.fxml.FXML
    public void sortyNameAction(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void containsAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sizeAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getPrevAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getNextAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeFirtsAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearAction(ActionEvent actionEvent) {
    }
}