/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleTable extends javax.swing.JPanel
{

    public PnlVehicleTable()
    {
        TableModel miModelo = new ModeloTabla();
        setBounds(200, 300, 1200, 400);
        JTable miTabla = new JTable(miModelo);
        setLayout(new BorderLayout());
        miTabla.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        
        add(new JScrollPane(miTabla), BorderLayout.CENTER);
        JButton botonImprimir = new JButton("Imprimir tabla");
        
        botonImprimir.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    miTabla.print();
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        JPanel pnlBotonInferior = new JPanel();
        pnlBotonInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBotonInferior.add(botonImprimir);
        
        add(pnlBotonInferior, BorderLayout.SOUTH);
    }
    
}

class ModeloTabla extends AbstractTableModel
{
    private JsonVehicleDaoImpl jvdao;
    List<Vehicle> vehicles;
    
    private void init() throws FileNotFoundException, IOException
    {
        jvdao = new JsonVehicleDaoImpl();
        vehicles = (List<Vehicle>) jvdao.getAll();
    }
    
    private int getVehicleNumber()
    {
        int number;
        try
        {
            init();
            number = vehicles.size();
            return number;
            
        } catch (IOException ex)
        {
            Logger.getLogger(ModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0; 
    }

    @Override
    public int getRowCount()
    {
        return getVehicleNumber();
    }

    @Override
    public int getColumnCount()
    {
        return 14;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Vehicle v = vehicles.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return v.getStockNumber();
            case 1:
                return v.getYear();
            case 2:
                return v.getMake();
            case 3:
                return v.getModel();
            case 4:
                return v.getStyle();
            case 5:
                return v.getVin();
            case 6:
                return v.getExteriorColor();
            case 7:
                return v.getInteriorColor();
            case 8:
                return v.getMiles();
            case 9:
                return v.getPrice();
            case 10:
                return v.getTransmission();
            case 11:
                return v.getEngine();
            case 12:
                return v.getImage();
            case 13:
                return v.getStatus();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int c)
    {
        switch(c)
        {
            case 0:
                return "StockNumber";
            case 1:
                return "Year";
            case 2:
                return "Make";
            case 3:
                return "Model";
            case 4:
                return "Style";
            case 5:
                return "Vin";
            case 6:
                return "Exetrior Color";
            case 7:
                return "Interior Color";
            case 8:
                return "Miles";
            case 9:
                return "Price";
            case 10:
                return "Transmission";
            case 11:
                return "Engene";
            case 12:
                return "Image";
            case 13:
                return "Status";
        }
        return "Columna " +c;
    }
}
