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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleTable extends javax.swing.JPanel
{
    JTextField txtPalabraClave;
    TableModel miModelo;
    JTable miTabla;

    public PnlVehicleTable()
    {
        miModelo = new ModeloTabla();
        setBounds(200, 300, 1200, 400);
        miTabla = new JTable(miModelo);
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
        
        //creo un panel superior para un text field
        JPanel pnlSuperior = new JPanel();
        
        pnlSuperior.setLayout(new GridBagLayout());
        //Establesco los Constraints del GridBagLayout pnlSuperior
        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.fill = GridBagConstraints.BOTH;
        gridBadConstraints.weightx = 0.1;
        gridBadConstraints.insets = new Insets(1, 1, 1, 1);
        
        txtPalabraClave = new JTextField();
        txtPalabraClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPalabraClaveKeyReleased(evt);
            }
        });
        
        //agrego el JTextField y el GridBadConstraints al panel superior
        pnlSuperior.add(txtPalabraClave, gridBadConstraints);
        add(pnlSuperior, BorderLayout.NORTH);
        
    }
    
    private void filter(String busquedad)
    {
        TableRowSorter<ModeloTabla> tr = new TableRowSorter<ModeloTabla>((ModeloTabla) miModelo);
        miTabla.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(busquedad));
    }
    
    private void txtPalabraClaveKeyReleased(KeyEvent event)
    {
        String query = txtPalabraClave.getText();
        filter(query);
    }
    
    public JTextField getTxtPalabraClave()
    {
        return txtPalabraClave;
    }

    public void setTxtPalabraClave(JTextField txtPalabraClave)
    {
        this.txtPalabraClave = txtPalabraClave;
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
