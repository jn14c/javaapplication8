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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.Box;
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
    private GridLayout gridd = new GridLayout(2,1);
    private Box b = Box.createVerticalBox();
    private Image img, imgT;
    private GridBagConstraints c = new GridBagConstraints();
    private File f;
    private File[] files;
    
    public AddComponents(){
        super("x-fer");
        setLayout(bord);
        
        



        add(refresher, bord.SOUTH);
        
        addPix();
        refresher.addActionListener(getButtonAction());
add(panel, bord.CENTER);
           
    }
        private ActionListener getButtonAction(){
             ActionListener action;
        action = (ActionEvent e) -> {
            //Thread t = new Thread(() -> {addPix();});
            //t.start();

             };
        return action;
        }
final void addPix(){
        c.fill = GridBagConstraints.HORIZONTAL;
        nest = new JPanel();
        nest.setLayout(gridd);
        inner = new JPanel();
        inner.setLayout(grid);
        inner.setSize(500, 500);
        f = new File(".");
        files = f.listFiles();
        labels = new JTextArea[files.length];
        plabels = new JLabel[files.length];

        int ug = 0;
        int ro=0, col=0;
        String ext = "";
        
        for  (File file : files){
                if(file.isFile()){
                   ImageIcon ico = new ImageIcon(file.getAbsolutePath());
                   img = ico.getImage();
                   imgT = img.getScaledInstance(120,100 , java.awt.Image.SCALE_SMOOTH);
                   ico = new ImageIcon(imgT);
                   int ind = file.getAbsolutePath().lastIndexOf('.');
                   plabels[ug] = new JLabel(ico);
                   plabels[ug].setPreferredSize(new Dimension(130,110));
                   ro++;
                   if(ro%4 == 0){
                       ro = 0;
                       col++;
                   }

                   labels[ug] = new JTextArea(file.getName());
                   labels[ug].setBackground(Color.white);
                   labels[ug].setOpaque(true);
                   if(ind > 0){
                       ext = file.getAbsolutePath().substring(ind+1);
                   }
                   if(ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")){
                       c.gridx = ro;
                       c.gridy = col;
                       grid.setConstraints(plabels[ug], c);
                       inner.add(plabels[ug]);
                       
                   }
                }
            
        }
                   panel = new JScrollPane(inner);
                   panel.setBackground(Color.white);
             panel.setPreferredSize(new Dimension(300, 500));
           add(panel, bord.CENTER);
         
           
    }
}
              