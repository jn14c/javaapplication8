package javaapplication8;

import java.awt.Dimension;
import javaapplication8.AddComponents;
import javax.swing.JFrame;



/**
 *
 * @author Joel
 */
public class widget {
    
    final private static Dimension d = new Dimension();
     
    public static void main(String[] args){
        // TODO code application logic here
      d.height = 650;
      d.width = 575;
      AddComponents addComponents = new AddComponents(); 
      addComponents.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );    
      
      addComponents.setSize( 575, 650 ); // set frame size
      addComponents.setVisible( true ); // display frame
      addComponents.setMinimumSize(d);
      addComponents.setOpacity(1);
      
    }
     
    
}