/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 *
 * @author Joel
 */
public class AddComponents extends JFrame  {
    private Toolkit tool = Toolkit.getDefaultToolkit();
    private JScrollPane panel;
    private JPanel inner, nest;
    private JButton refresher = new JButton("Refresh");
    private JLabel[] plabels;
    private JTextArea[] labels;
    private BorderLayout bord = new BorderLayout();
    private GridBagLayout grid = new GridBagLayout();
    private Image img, imgT;
    private GridBagConstraints c = new GridBagConstraints();
    private File f;
    private File[] files;
    
    public AddComponents(){
        super("x-fer");
        setLayout(bord);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        inner = new JPanel();
        inner.setLayout(grid);
        inner.setSize(500, 500);

        add(refresher, bord.SOUTH);
        
        addPix();
        panel = new JScrollPane(inner);
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(300, 500));
        refresher.addActionListener(getButtonAction());
        add(panel, bord.CENTER);
           
    }
        private ActionListener getButtonAction(){
             ActionListener action;
        action = (ActionEvent e) -> {
            
            try{
                inner.removeAll();
                //inner.setOpaque(false);
                
            panel.remove(inner);
            
            //bord.removeLayoutComponent(panel);
            //bord.removeLayoutComponent(bord.getLayoutComponent(bord.CENTER));
            }catch(NullPointerException n){
                
            }
            Thread t = new Thread(() -> {addPix();});
            t.start();
            inner.repaint();
            panel.repaint();
            super.repaint();
        };
        return action;
        }
        //reads file directory and chooses pictures. Then 
        //adds labels with pictures to a a grid then a gridbag to a border layout
    final void addPix(){

        f = new File(".");
        files = f.listFiles();
        plabels = new JLabel[files.length];
        String fileDescription;
        int fileIndex = 0;
        int ro=0, col=0;
        String ext = "";
        
        for  (File file : files){
                if(file.isFile()){
                   ImageIcon ico = new ImageIcon(file.getAbsolutePath());
                   img = ico.getImage();
                   imgT = img.getScaledInstance(120,100 , java.awt.Image.SCALE_SMOOTH);
                   ico = new ImageIcon(imgT);
                   int ind = file.getAbsolutePath().lastIndexOf('.');
                   plabels[fileIndex] = new JLabel(ico);
                   plabels[fileIndex].setPreferredSize(new Dimension(130,110));
                   
                   if(ro%4 == 0){
                       ro = 0;
                       col++;
                   }

                   if(ind > 0){
                       ext = file.getAbsolutePath().substring(ind+1);
                   }
                   
                   if(ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")){
                       fileDescription = "<html>" + "File name: " + file.getName() + "<br>" + "Size: " + file.length()/1024 + " KB" + "</html>";
                       plabels[fileIndex ].setToolTipText(fileDescription);
                       
                       c.gridx = ro;
                       c.gridy = col;
                       grid.setConstraints(plabels[fileIndex], c);
                       inner.add(plabels[fileIndex]);
                       inner.repaint();

                       ro++;
                       
                   }
                   
                }
            try{
                
            panel.repaint();
            
            }catch(NullPointerException n){
                
            }
        }
             
           //add(panel, bord.CENTER);

           
    }

}
              