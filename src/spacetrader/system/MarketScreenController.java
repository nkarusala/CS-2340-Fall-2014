/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.system;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spacetrader.Planet;
import spacetrader.Player;
import spacetrader.commerce.Cargo;
import spacetrader.commerce.Market;
import spacetrader.commerce.TradeGood;
import spacetrader.commerce.Transaction;
import spacetrader.exceptions.DepletedInventoryException;
import spacetrader.exceptions.InsufficientFundsException;

/**
 * FXML Controller class
 *
 * @author nkaru_000
 */
public class MarketScreenController implements Initializable {
    
    @FXML private Text alertText;
    @FXML private Label planetName, planetGovt, planetLevel, planetResource;
    @FXML private Label playerFunds, moneyRemaining;
    @FXML private Label totalPurchase, totalSale;
    @FXML private GridPane buyGrid, sellGrid;
    @FXML private Tab buyTab, sellTab;
    @FXML private Label stock0, stock1, stock2, stock3, stock4,
                        stock5, stock6, stock7, stock8, stock9;
    @FXML private Label goodBuy0, goodBuy1, goodBuy2, goodBuy3, goodBuy4,
                        goodBuy5, goodBuy6, goodBuy7, goodBuy8, goodBuy9;
    @FXML private Label priceBuy0, priceBuy1, priceBuy2, priceBuy3, priceBuy4,
                        priceBuy5, priceBuy6, priceBuy7, priceBuy8, priceBuy9;
    @FXML private TextField numBuy0, numBuy1, numBuy2, numBuy3, numBuy4,
                            numBuy5, numBuy6, numBuy7, numBuy8, numBuy9;
    @FXML private Label cost0, cost1, cost2, cost3, cost4,
                        cost5, cost6, cost7, cost8, cost9;
    @FXML private Label inventory0, inventory1, inventory2, inventory3, inventory4,
                        inventory5, inventory6, inventory7, inventory8, inventory9;
    @FXML private Label goodSell0, goodSell1, goodSell2, goodSell3, goodSell4,
                        goodSell5, goodSell6, goodSell7, goodSell8, goodSell9;
    @FXML private Label priceSell0, priceSell1, priceSell2, priceSell3, priceSell4,
                        priceSell5, priceSell6, priceSell7, priceSell8, priceSell9;
    @FXML private TextField numSell0, numSell1, numSell2, numSell3, numSell4,
                            numSell5, numSell6, numSell7, numSell8, numSell9;
    @FXML private Label revenue0, revenue1, revenue2, revenue3, revenue4, 
                        revenue5, revenue6, revenue7, revenue8, revenue9;
    
    private Label[] stocks, goodBuys, priceBuys, costs,
                    inventorys, goodSells, priceSells, revenues;
    private TextField[] numBuys, numSells;
    
    private Transaction cashier;
    private List<TradeGood> buyGoods, sellGoods;
    private enum Change { INCREASE, DECREASE, CUSTOM }
    private boolean viewIsInitialized = false;
    
    private Player player;
    private Market market;
    private MainController mainControl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertText.setFill(Color.TRANSPARENT);
    }
    
    public void setMainControl(MainController mainControl) {
        this.mainControl = mainControl;
    }

    public void setUpMarketScreen(Planet planet, Player player) {
        this.player = player;
        this.market = planet.getMarket();
        cashier = new Transaction(market, player.getShip().getCargo(), player.getWallet().getCredits());
        
        stocks = new Label[] {stock0, stock1, stock2, stock3, stock4,
                              stock5, stock6, stock7, stock8, stock9};
        goodBuys = new Label[] {goodBuy0, goodBuy1, goodBuy2, goodBuy3, goodBuy4,
                                goodBuy5, goodBuy6, goodBuy7, goodBuy8, goodBuy9};
        priceBuys = new Label[] {priceBuy0, priceBuy1, priceBuy2, priceBuy3, priceBuy4,
                                 priceBuy5, priceBuy6, priceBuy7, priceBuy8, priceBuy9};
        numBuys = new TextField[] {numBuy0, numBuy1, numBuy2, numBuy3, numBuy4,
                                   numBuy5, numBuy6, numBuy7, numBuy8, numBuy9};
        costs = new Label[] {cost0, cost1, cost2, cost3, cost4,
                             cost5, cost6, cost7, cost8, cost9};
        inventorys = new Label[] {inventory0, inventory1, inventory2, inventory3, inventory4,
                                  inventory5, inventory6, inventory7, inventory8, inventory9};
        goodSells = new Label[] {goodSell0, goodSell1, goodSell2, goodSell3, goodSell4,
                                 goodSell5, goodSell6, goodSell7, goodSell8, goodSell9};
        priceSells = new Label[] {priceSell0, priceSell1, priceSell2, priceSell3, priceSell4,
                                  priceSell5, priceSell6, priceSell7, priceSell8, priceSell9};
        numSells = new TextField[] {numSell0, numSell1, numSell2, numSell3, numSell4,
                                    numSell5, numSell6, numSell7, numSell8, numSell9};
        revenues = new Label[] {revenue0, revenue1, revenue2, revenue3, revenue4, 
                                revenue5, revenue6, revenue7, revenue8, revenue9};

        buyGoods = market.getStock().getGoodList();
        sellGoods = player.getShip().getCargo().getGoodList();
        
        //Remove appropriate amount of rows from grid
        if (buyGoods.size() < 10) {
            deleteGridRows(buyGoods.size(), buyGrid);
        }
        if (sellGoods.size() < 10) {
            deleteGridRows(sellGoods.size(), sellGrid);
        }
        
        setUpPanels(player.getShip().getCargo());
        planetName.setText(planet.getName());
        planetGovt.setText(planet.getPoliticalSystem().toString());
        planetLevel.setText(planet.getLevel().toString());
        planetResource.setText(planet.getResource().toString());
        playerFunds.setText("$" + player.getWallet().getCredits());
        updateNetBalance();
        viewIsInitialized = true;
        checkForDisabling(buyGrid);
        
    }
    
    /**
     * Deletes rows of the grid so that there is only the specified number remaining
     * @param numRows how many rows not to delete
     */
    private void deleteGridRows(int numRows, GridPane grid) {
        ObservableList<Node> children = grid.getChildren();
        ArrayList<Node> toRemove = new ArrayList<>();
        for (Node node : children) {
            Integer row = GridPane.getRowIndex(node);
            if (row != null && row > numRows) {
                toRemove.add(node);
            }
        }
        children.removeAll(toRemove);
    }
    
    private void setUpPanels(Cargo cargo) {
        for (int i = 0; i < buyGoods.size(); i++) {
            TradeGood good = buyGoods.get(i);
            
            stocks[i].setText("" + market.getStock().getQuantityOfGood(good));
            goodBuys[i].setText("" + good.type());
            priceBuys[i].setText("$" + market.getBuyPrices().get(good));
        }
        
        for (int i = 0; i < sellGoods.size(); i++) {
            TradeGood good = sellGoods.get(i);
            
            inventorys[i].setText("" + cargo.getQuantityOfGood(good));
            goodSells[i].setText("" + good.type());
            priceSells[i].setText("$" + market.getSellPrices().get(good));
        }
    }
    
    @FXML protected void increaseBuyQuantity(ActionEvent event) {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        if (row != null) {
            modifyBuyQuantity(row - 1, Change.INCREASE);
        }     
    }
    
    @FXML protected void decreaseBuyQuantity(ActionEvent event) {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        if (row != null) {
            modifyBuyQuantity(row - 1, Change.DECREASE);
        }  
    }
    
    @FXML protected void increaseSellQuantity(ActionEvent event) {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        if (row != null) {
            modifySellQuantity(row - 1, Change.INCREASE);
        }     
    }
    
    @FXML protected void decreaseSellQuantity(ActionEvent event) {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        if (row != null) {
            modifySellQuantity(row - 1, Change.DECREASE);
        }  
    }
    
    private void modifyBuyQuantity(int index, Change type) {
        TradeGood good = buyGoods.get(index);
        int oldQuantity = cashier.getBuyQuantityOfGood(good);
        try {
            int newQuantity;
            if (type == Change.INCREASE) {
                newQuantity = oldQuantity + 1;
            } else if (type == Change.DECREASE) {
                newQuantity = oldQuantity - 1;
            } else {
                newQuantity = Integer.parseInt(numBuys[index].getText());
            }
            cashier.changeBuyQuantity(good, newQuantity);
            updateBuyText(index);
            updateNetBalance();
            checkForDisabling(buyGrid);
        } catch (NumberFormatException e) {
            displayAlert("Please input a number!");
        } catch (DepletedInventoryException e) {
            displayAlert("We are out of that item! Please try another selection.");
        } catch (InsufficientFundsException e) {
            displayAlert("You do not have enough money to buy this item!");
        } catch (IllegalArgumentException e) {
            displayAlert("Quantities must be positive!");
        }
    }
    
    private void modifySellQuantity(int index, Change type) {
        TradeGood good = sellGoods.get(index);
        int oldQuantity = cashier.getSellQuantityOfGood(good);
        try {
            int newQuantity;
            if (type == Change.INCREASE) {
                newQuantity = oldQuantity + 1;
            } else if (type == Change.DECREASE) {
                newQuantity = oldQuantity - 1;
            } else {
                newQuantity = Integer.parseInt(numSells[index].getText());
            }
            cashier.changeSellQuantity(good, newQuantity);
            updateSellText(index);
            updateNetBalance();
            checkForDisabling(sellGrid);
        } catch (NumberFormatException e) {
            displayAlert("Please input a number!");
        } catch (DepletedInventoryException e) {
            displayAlert("You are out of that item! Please try another selection.");
        } catch (IllegalArgumentException e) {
            displayAlert("Quantities must be positive!");
        }
    }
    
    private void updateBuyText(int index) {
        TradeGood good = buyGoods.get(index);
        int quantity = cashier.getBuyQuantityOfGood(good);
        int oldStock = market.getStock().getQuantityOfGood(good);
        stocks[index].setText("" + (oldStock - quantity));
        numBuys[index].setText("" + quantity);
        costs[index].setText("$" + (quantity * market.getBuyPrices().get(good)));
        totalPurchase.setText("- " + cashier.getTotalCost() + " credits");
    }
    
    private void updateSellText(int index) {
        TradeGood good = sellGoods.get(index);
        int quantity = cashier.getSellQuantityOfGood(good);
        int oldInventory = player.getShip().getCargo().getQuantityOfGood(good);
        inventorys[index].setText("" + (oldInventory - quantity));
        numSells[index].setText("" + quantity);
        revenues[index].setText("$" + (quantity * market.getBuyPrices().get(good)));
        totalSale.setText("+ " + cashier.getTotalRevenue() + " credits");
    }
    
    private void updateNetBalance() {
       moneyRemaining.setText(cashier.getRemainingBalance() + " credits"); 
    }
    
    private void displayAlert(String message) {
        alertText.setFill(Color.RED);
        alertText.setText(message);
    }
    
    /**
     * Checks to see if any buttons need to be disabled.
    */
    private void checkForDisabling(GridPane grid) {
        final int DEC_COLUMN = 4;
        final int INC_COLUMN = 5;
        ObservableList<Node> children = grid.getChildren();
        for (Node node : children) {
            Integer column = GridPane.getColumnIndex(node);
            Integer row = GridPane.getRowIndex(node);
            if (column != null && row != null) {
                if (column == DEC_COLUMN) {
                    int quantity;
                    if (grid == buyGrid) {
                        quantity = cashier.getBuyQuantityOfGood(buyGoods.get(row - 1));
                    } else {
                        quantity = cashier.getSellQuantityOfGood(sellGoods.get(row - 1));
                    }
                    if (quantity == 0) {
                        node.setDisable(true);
                    } else {
                        node.setDisable(false);
                    }  
                } else if (column == INC_COLUMN) {
                    int price, quantity;
                    if (grid == buyGrid) {
                        price = market.getBuyPrices().get(buyGoods.get(row - 1));
                        quantity = market.getStock().getQuantityOfGood(buyGoods.get(row - 1));
                    } else {
                        price = -1; //if selling, price doesn't matter
                        quantity = player.getShip().getCargo().getQuantityOfGood(sellGoods.get(row - 1));
                    }
                    if (price > cashier.getRemainingBalance() || quantity == 0) {
                        node.setDisable(true);
                    } else {
                        node.setDisable(false);
                    }
                }
            }
        }
    }
    
    @FXML protected void buyTabChanged(Event e) {
        if (viewIsInitialized) {
            checkForDisabling(buyGrid);
            checkForDisabling(sellGrid);
        }
    }
        
    @FXML protected void completeTransaction(ActionEvent event) {
        cashier.complete();
        player.getWallet().add(cashier.getTotalRevenue());
        player.getWallet().remove(cashier.getTotalCost());
        mainControl.goToMarketScreen(market.getPlanet());
    }
    
    @FXML protected void goBack(ActionEvent event) {
        mainControl.goToFirstScreen();
    }
    
    
}
    

    
