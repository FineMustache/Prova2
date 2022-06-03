package models;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
  
public class CellRender extends DefaultTableCellRenderer 
{
    public CellRender() 
    {
        super();
        setOpaque(true);
    } 
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) 
    { 
        if(Boolean.parseBoolean((String)table.getValueAt(row, 4)))
        {
            setForeground(Color.black);        
            setBackground(Color.green);            
        }    
        else
        {    
            setBackground(Color.white);    
            setForeground(Color.black);    
        } 
        setText(value !=null ? value.toString() : "");
        return this;
    }
}