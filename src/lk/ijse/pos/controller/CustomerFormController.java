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
import lk.ijse.pos.bo.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.view.tm.CustomerTM;

import java.util.ArrayList;

/**
 * @author Mohamed Aazaf <aazafmax2@gmail.com> (www.aazafbiz.web.app)
 * @since 10/9/21
 */
public class CustomerFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    boolean addNew = true;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == null) {
                addNew = true;
                return;
            }

            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));

            addNew = false;
        });

        loadAllCustomers();
    }

    public void loadAllCustomers() {
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            ArrayList<CustomerTM> customers = new ArrayList<>();

            for (CustomerDTO customerDTO : allCustomers) {
                customers.add(new CustomerTM(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getSalary()));
            }
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList(customers);
            tblCustomer.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO c1 = customerBO.searchCustomer(txtId.getText());
            if (c1 != null) {
                txtName.setText(c1.getName());
                txtAddress.setText(c1.getName());
                txtSalary.setText(String.valueOf(c1.getSalary()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (addNew) {
            boolean isSaved = customerBO.addCustomer(new CustomerDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtName.getText(),
                    Double.parseDouble(txtSalary.getText())));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Unable", ButtonType.OK).show();
            }
        } else {

            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Double.parseDouble(txtSalary.getText())));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "done", ButtonType.OK).show();
                loadAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK).show();
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws Exception {
        String cid = tblCustomer.getSelectionModel().getSelectedItem().getId();

        boolean isDeleted = customerBO.deleteCustomer(cid);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "deleted", ButtonType.OK).show();
            loadAllCustomers();
        } else {
            new Alert(Alert.AlertType.WARNING, "failed", ButtonType.OK).show();
        }
    }
}
