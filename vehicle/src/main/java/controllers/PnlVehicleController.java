/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import backend.pojo.VehicleSubModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
//import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import panels.PnlVehicle;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleController
{
    private PnlVehicle pnlVehicle;
    private JsonVehicleDaoImpl jvdao;
    private List<VehicleSubModel> vSubModels;
    private Gson gson;
    private DefaultComboBoxModel cmbModelMake;
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private String[] status = {"Active", "Mantainance", "Not available"};

    public PnlVehicleController(PnlVehicle pnlVehicle) throws FileNotFoundException
    {
        this.pnlVehicle = pnlVehicle;
        initComponent();
    }
    
    private void initComponent() throws FileNotFoundException
    {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();
        
        JsonReader jreader = new JsonReader(new BufferedReader(
        new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        
        vSubModels = gson.fromJson(jreader, listType);
        
        List<String> makes = vSubModels.stream().map(x -> x.getMake()).collect(Collectors.toList());
        List<String> models = vSubModels.stream().map(x -> x.getModel()).collect(Collectors.toList());
        List<String> colors = vSubModels.stream().map(x -> x.getColor()).collect(Collectors.toList());
        
        cmbModelMake = new DefaultComboBoxModel(makes.toArray());
        cmbModelModel = new DefaultComboBoxModel(models.toArray());
        cmbModelEColor = new DefaultComboBoxModel(models.toArray());
        cmbModelIColor = new DefaultComboBoxModel(models.toArray());
        cmbModelStatus = new DefaultComboBoxModel(status);
        
        pnlVehicle.getCmbMake().setModel(cmbModelMake);
        pnlVehicle.getCmbModel().setModel(cmbModelModel);
        pnlVehicle.getCmbEColor().setModel(cmbModelEColor);
        pnlVehicle.getCmbIColor().setModel(cmbModelIColor);
        pnlVehicle.getCmbStatus().setModel(cmbModelStatus);    
        
        pnlVehicle.getBtnSave().addActionListener(((e) -> {
            btnSaveActionListener(e);
        }));
    }
    
    private void btnSaveActionListener(ActionEvent e)
    {
        int stock, year;
        String make, model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission;
        
        stock = Integer.parseInt(pnlVehicle.getTxtStock().getText());
        year = Integer.parseInt(pnlVehicle.getSpnYear().getModel().getValue().toString());
        make = pnlVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlVehicle.getTxtStyle().getText();
        vin = pnlVehicle.getFmtVin().getText();
        eColor = pnlVehicle.getCmbEColor().getSelectedItem().toString();
        iColor = pnlVehicle.getCmbIColor().getSelectedItem().toString();
        miles = pnlVehicle.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlVehicle.getSpnPrice().getModel().getValue().toString());
        transmission = pnlVehicle.getRbtnAut().isSelected()?
                Vehicle.Transmission.AUTOMATIC:Vehicle.Transmission.MANUAL;
        engine = pnlVehicle.getTxtEngine().getText();
        image = pnlVehicle.getTxtImage().getText();
        status = pnlVehicle.getCmbStatus().getSelectedItem().toString();
        
        Vehicle vehicle = new Vehicle(stock,year, make, model, style, vin, iColor, iColor, miles, price, transmission, engine, image, status);
    }
}
