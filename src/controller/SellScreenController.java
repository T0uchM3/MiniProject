/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.MenDAO;
import DOA.WomenDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXRadioButton;
import enity.Men;
import enity.Women;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class SellScreenController implements Initializable {

    private final FirstModel fm;
    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane sellBorderPane;
    @FXML
    public JFXDrawer optionDrawer;
    boolean dropDownMenu = false;
    @FXML
    JFXButton sellBtn;
    @FXML
    Pane dropDownBg;
    ObservableList<Men> shoesList = FXCollections.observableArrayList();
    ObservableList<Women> shoesList2 = FXCollections.observableArrayList();
    ObservableList<Men> shoesList3 = FXCollections.observableArrayList();
    ObservableList<Women> shoesList4 = FXCollections.observableArrayList();
    ObservableList<Men> shoesList5 = FXCollections.observableArrayList();
    ObservableList<Women> shoesList6 = FXCollections.observableArrayList();

    ObservableList<Object> test = FXCollections.observableArrayList();
    @FXML
    TableView sellTable;
    @FXML
    TableColumn<String, String> nameCol;
    @FXML
    TableColumn<String, Integer> qteCol;
    @FXML
    TableColumn<String, Float> priceCol;
    @FXML
    JFXRadioButton menRb, womenRb, kidsRb, boysRb, girlsRb, summerRb, winterRb, seasonalRb, darkRb, brightRb, unusualRb, unspecifiedRb;
    @FXML
    ToggleGroup tg1 = new ToggleGroup();
    ToggleGroup tg2 = new ToggleGroup();
    ToggleGroup tg3 = new ToggleGroup();
    ToggleGroup tg4 = new ToggleGroup();

    public SellScreenController(FirstModel fm) {
        this.fm = fm;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sellBorderPane.requestFocus();
        sellBtn.setFocusTraversable(true);
        sellBtn.requestFocus();
//        menRb.setFocusTraversable(false);
        // TODO
        menRb.setToggleGroup(tg1);
        womenRb.setToggleGroup(tg1);
        kidsRb.setToggleGroup(tg1);

        boysRb.setToggleGroup(tg2);
        girlsRb.setToggleGroup(tg2);

        summerRb.setToggleGroup(tg3);
        winterRb.setToggleGroup(tg3);
        seasonalRb.setToggleGroup(tg3);

        darkRb.setToggleGroup(tg4);
        brightRb.setToggleGroup(tg4);
        unspecifiedRb.setToggleGroup(tg4);
        unusualRb.setToggleGroup(tg4);

        shoesList2.addAll(WomenDAO.listWomen());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
    }

    @FXML
    public void openOption(ActionEvent event) {
        //because of some code limitation, i can't move a node forward by one step, so i had to switsh between different nodes and bring each of them forward/backward alone

        if (dropDownMenu == false) {
            sellBorderPane.toBack();
            dropDownMenu = true;
        } else {
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev -> {
                dropDownBg.toBack();
            }));
            timeline2.play();
            dropDownMenu = false;
        }
        if (optionDrawer.isHidden()) {
            optionDrawer.open();
        } else {
            optionDrawer.close();
        }
    }

    @FXML
    public void sendBackOptions(MouseEvent event) {
        handleHiding();
    }

    public void handleHiding() {
        optionDrawer.open();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev -> {
            dropDownBg.toBack();

        }));
        timeline2.play();
        dropDownMenu = false;

    }

    @FXML
    public void filterSelection(ActionEvent event) {
        JFXRadioButton clone = (JFXRadioButton) event.getSource();

        if (menRb.isSelected()) {
            shoesList.clear();
            shoesList.addAll(MenDAO.listMen());
            unselectRb(clone.getText());
            sellTable.setItems(shoesList);

            if (summerRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Summer")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList3);
                //*************summer start********
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //************summer end************
            }
            if (winterRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Winter")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList3);

                //***************inside Winter******************
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //************end winter****************

            }
            if (seasonalRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Seasonal")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems((shoesList3));

                //***********Seasonal start*************
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //*************seasonal end*********************************
            }
        }
        if (womenRb.isSelected()) {
            shoesList2.clear();
            shoesList2.addAll(WomenDAO.listWomen());
            unselectRb(clone.getText());
            sellTable.setItems(shoesList2);

            if (summerRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Summer")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);

                //***********Summer start*********
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }

                //*******Summer End*************************
            }
            if (winterRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Winter")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);
                //***********winter start********************
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                //************Winter end*****************

            }
            if (seasonalRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Seasonal")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);

                //****************Seasonal start********
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                //***************Seasonal End************

            }
        }
    }

    public void unselectRb(String text) {
        if (text.equals("Men") || text.equals("Women")) {
            boysRb.setSelected(false);
            girlsRb.setSelected(false);
            summerRb.setSelected(false);
            winterRb.setSelected(false);
            seasonalRb.setSelected(false);
            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
        if (text.equals("Boys") || text.equals("Girls")) {
            summerRb.setSelected(false);
            winterRb.setSelected(false);
            seasonalRb.setSelected(false);
            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
        if (text.equals("Summer") || text.equals("Winter") || text.equals("Seasonal")) {
            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
    }

}
