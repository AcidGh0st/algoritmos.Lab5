package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class AddOdersController
{
    @javafx.fxml.FXML
    private TextField IDtestField;
    @javafx.fxml.FXML
    private TextField quantityTestField;
    @javafx.fxml.FXML
    private TextField UPriceText;
    @javafx.fxml.FXML
    private TextField totalPriceText;
    @javafx.fxml.FXML
    private ChoiceBox choiceBox;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private Button addButton;
    @javafx.fxml.FXML
    private Button cleanButton;
    @javafx.fxml.FXML
    private Button closeButton;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField idTextField;
    Alert alert;

    private CircularDoublyLinkedList orderList;
    private DoublyLinkedList productList;
    @javafx.fxml.FXML
    public void initialize() {
        //cargamos las listas globales
        this.orderList = util.Utility.getOrderList();
        this.productList = util.Utility.getProductList();
        this.alert = util.UtilityFX.alert("Order..", "Add Order...");
        //seteo el id con el valor maximo encontrado en la lista +1
        this.idTextField.setText(String.valueOf(getMaxId()));

        this.choiceBox.setItems(getProductList());
    }

    private ObservableList<List<String>>  getProductList() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();

        if (productList != null && !productList.isEmpty()) {
            try {
                for (int i = 1; i <= productList.size(); i++) {
                    Product product = (Product) productList.getNode(i).data;
                    List<String> arrayList = new ArrayList<>();
                    arrayList.add(String.valueOf(product.getId()));//envuelvo con un string valor entero
                    arrayList.add(String.valueOf(product.getName()));
                    arrayList.add(String.valueOf(product.getName()));
                    arrayList.add(String.valueOf(product.getCurrentStock()));
                    arrayList.add(String.valueOf(product.getPrice()));
                    data.add(arrayList);
                }

            } catch (ListException ex) {
                alert.setContentText("There was an error in the process");
            }
        }
        return data;
    }


    private int getMaxId(){
        int maxId =0;
        if(orderList.isEmpty())
            return 1;

        try{


            for (int i = 0; i <= orderList.size() ; i++) {
                Order order = (Order) orderList.getNode(i).data;
                if (maxId<order.getId()){
                    maxId = order.getId();
                }
            }
        }catch (ListException ex){
            alert.setContentText(ex.getMessage());
        }
        return maxId+1;//+1 para la siguiente orden
    }

    @javafx.fxml.FXML
    public void addAction(ActionEvent actionEvent) {
        util.UtilityFX.loadPage("ucr.lab.HelloApplication", "AddOdersController.fxml", bp);
    }

    @javafx.fxml.FXML
    public void cleanAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void quantityOnKyPressed(Event event) {
    }

    @javafx.fxml.FXML
    public void quantityOnKeyReleased(Event event) {
    }
}