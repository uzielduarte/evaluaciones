/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import views.DlgVehicle;

/**
 *
 * @author 
 */
public class VehicleTableModel extends AbstractTableModel implements Observer{
    private List<Vehicle> data;
    private JsonVehicleDaoImpl jvdo;
    DlgVehicle dlgVehicle;
   
    private String[] columnNames = new String[]{"StockNumber", "Year", "Make", "Model", "Style",
        "Vin", "Exterior color", "Interior color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};
    
    
    public VehicleTableModel(List<Vehicle> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }   
    
    public void add(Vehicle v){
        this.data.add(v);
    }

    @Override
    public String getColumnName(int column) {
       return columnNames[column];
    }    
        
    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames == null ? 0 : columnNames.length;
    }
  
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data == null ? null : data.isEmpty() ? null : data.get(rowIndex).asArray()[columnIndex];
    }
    
       public void removeRow(int row) {
        data.remove(row);
        fireTableDataChanged();
       }
    

    @Override
    public void update(Observable o, Object arg) {
       /*if(arg.equals(true)){  //Pendiente de solucionar Update 
           System.out.println("Hi");
           fireTableDataChanged();
           arg = false;
       }*/
        
        add((Vehicle) arg);
        fireTableDataChanged();
    }
}
