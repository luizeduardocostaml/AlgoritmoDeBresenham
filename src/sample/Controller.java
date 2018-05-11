package sample;


import Model.Ponto;
import javafx.fxml.FXML;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import javax.swing.*;

public class Controller {
    @FXML Button btnClose;
    @FXML TextField txtX;
    @FXML TextField txtY;
    @FXML TextField txtX1;
    @FXML TextField txtY1;
    @FXML Button btnCalcular;
    @FXML GridPane gridTela;
    @FXML GridPane gridX;
    @FXML GridPane gridY;
    @FXML Label lblRelatorio;

    private String relatorio;

    public void initialize(){
        btnClose.setOnAction(OnActionBtnClose);
        btnCalcular.setOnAction(OnActionBtnCalcular);
        gridXY();
        limpaGrid();
    }

    private EventHandler<ActionEvent> OnActionBtnClose = (ActionEvent event) ->{
        System.exit(0);
    };

    private EventHandler<ActionEvent> OnActionBtnCalcular = (ActionEvent event) ->{
        executarCalculo();
        lblRelatorio.setText(relatorio);
    };

    private void gridXY(){
        int X = -10;
        for(int i=0; i<20; i++){
            Label label = new Label();
            label.setStyle("-fx-background-color: #FFFFFF;");
            label.setText(String.format("%d",X));
            label.setAlignment(Pos.CENTER);
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setColumnIndex(label,i);
            GridPane.setRowIndex(label,0);
            gridX.getChildren().add(label);
            X++;
        }
        int Y = 9;
        for(int i=0; i< 20; i++){
            Label label = new Label();
            label.setText(String.format("%d",Y));
            label.setAlignment(Pos.CENTER);
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane.setColumnIndex(label,0);
            GridPane.setRowIndex(label,i);
            gridY.getChildren().add(label);
            Y--;
        }
    }

    private void limpaGrid(){
        for(int i =0; i< 20; i++){
            for(int j =0; j< 20; j++){
                Label label = new Label();
                label.setStyle("-fx-background-color: #FFFFFF;-fx-border-width: 1;\n" +
                        "-fx-border-color: black;");
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setColumnIndex(label,i);
                GridPane.setRowIndex(label,j);
                gridTela.getChildren().add(label);
            }
        }
    }

    private boolean verificaPonto(String ponto, String ponto1, String ponto2, String ponto3){
        int x = Integer.parseInt(ponto);
        int y = Integer.parseInt(ponto1);
        int x1 = Integer.parseInt(ponto2);
        int y1 = Integer.parseInt(ponto3);
        if(x >= -10 && x <= 9 && x1 >= -10 && x1 <= 9 && y >= -10 && y <= 9 && y1 >= -10 && y1 <= 9) return true;
        else{
            JOptionPane.showMessageDialog(null, "Coloque um valores válidos");
            return false;
        }
    }

    private void executarCalculo(){
        limpaGrid();
        try {
            if (verificaPonto(txtX.getText(), txtY.getText(), txtX1.getText(), txtY1.getText())) {
                Ponto pInicio = new Ponto();
                pInicio.setX(Integer.parseInt(txtX.getText()));
                pInicio.setY(Integer.parseInt(txtY.getText()));
                Ponto pFim = new Ponto();
                pFim.setX(Integer.parseInt(txtX1.getText()));
                pFim.setY(Integer.parseInt(txtY1.getText()));
                relatorio = "";
                Bresenham(pInicio, pFim);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Digite um valores válidos.");
        }
    }

    private void pintaPonto(Ponto ponto){
        Label label = new Label();
        label.setStyle("-fx-background-color: #000000;");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setColumnIndex(label,ponto.tX());
        GridPane.setRowIndex(label,ponto.tY());
        gridTela.getChildren().add(label);
    }

    private void Bresenham(Ponto pInic, Ponto pFim){
        int x = pInic.getX(), y = pInic.getY();
        int x2 = pFim.getX(), y2 = pFim.getY();
        int w = x2-x;
        int h = y2-y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if(w<0) dx1 = -1; else if(w>0) dx1 = 1;
        if(h<0) dy1 = -1; else if(h>0) dy1 = 1;
        if(w<0) dx2 = -1; else if(w>0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if(!(longest > shortest)){
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if(h<0) dy2 = -1; else if(h>0) dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for(int i=0;i<= longest; i++){
            relatorio = String.format("%s(%d, %d)\n", relatorio, x, y);
            Ponto ponto = new Ponto(x,y);
            pintaPonto(ponto);
            numerator += shortest;
            if(!(numerator<longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            }
            else{
                x += dx2;
                y += dy2;
            }
        }
    }
}
