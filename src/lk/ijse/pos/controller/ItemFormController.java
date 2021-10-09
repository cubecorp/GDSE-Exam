/*
 *
 *  * ---------------------------------------------------------------------------------------------
 *  *  *  Copyright (c) GDSE-exam. All rights reserved.
 *  *  *  Licensed under the SriLankan Information License. See License.txt in the project root for license information.
 *  *  *--------------------------------------------------------------------------------------------
 *
 */

package lk.ijse.pos.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.view.tm.ItemTM;

import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class ItemFormController {

    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TableView<ItemTM> tblItems;
    boolean addNew = true;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItems.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null) {
                addNew = true;
                return;
            }

            txtCode.setText(newValue.getCode());
            txtDescription.setText(newValue.getDescription());
            txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));

            addNew = false;

        }));

        loadAllItems();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO item = itemBO.searchItem(txtCode.getText());
            if (item != null) {
                txtDescription.setText(item.getDescription());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        String code = tblItems.getSelectionModel().getSelectedItem().getCode();

        boolean isDeleted = itemBO.deleteItem(code);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK).show();
            loadAllItems();
        } else {
            new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK).show();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (addNew) {
            boolean isSaved = itemBO.addItem(new ItemDTO(
                    txtCode.getText(),
                    txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK).show();
                loadAllItems();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK).show();
            }

        } else {
            boolean isUpdated = itemBO.updateItem(new ItemDTO(
                    txtCode.getText(),
                    txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK).show();
                loadAllItems();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK).show();
            }
        }
    }

    public void loadAllItems() {
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            ArrayList<ItemTM> itemTMS = new ArrayList<>();

            for (ItemDTO itemDTO : allItems) {
                itemTMS.add(new ItemTM(
                        itemDTO.getCode(),
                        itemDTO.getDescription(),
                        itemDTO.getUnitPrice(),
                        itemDTO.getQtyOnHand()));
            }

            ObservableList<ItemTM> obList = FXCollections.observableArrayList(itemTMS);
            tblItems.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
