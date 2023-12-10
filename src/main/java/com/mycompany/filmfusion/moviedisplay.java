/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.filmfusion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import mongodb.main;
import mongodb.main.Movie;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
//
/**
 *
 * @author hp
 */
public class moviedisplay extends javax.swing.JFrame {

    /**
     * Creates new form moviedisplay
     */
    
     private final main mainInstance;
    //private main.Movie movieInstance;
    
    public moviedisplay() {
        initComponents();
        mainInstance = main.getInstance();
        mainInstance.start();
        //movieInstance = mainInstance.new Movie();
        setPanelSize();
        displayMovieTitles();
    }
    
      private void setPanelSize() {
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));
    }
    private void displayMovieTitles() {
    List<main.Movie> allMovies = mainInstance.getAllMoviesFromDb();

    System.out.println("Number of movies retrieved: " + allMovies.size());

    // Remove existing components from the jPanel1
    jPanel1.removeAll();

    // Set layout for jPanel1
    GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanelLayout);

    // Create a DefaultListModel to hold movie titles
    DefaultListModel<String> listModel = new DefaultListModel<>();

    // Add movie titles to the list model
    for (main.Movie movie : allMovies) {
        listModel.addElement(movie.getTitle());
    }

    // Create a JList with the DefaultListModel
    JList<String> movieList = new JList<>(listModel);

    // Set the JList properties
    movieList.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
    movieList.setForeground(new java.awt.Color(255, 255, 0)); // Yellow color
    movieList.setBackground(new java.awt.Color(0, 0, 0));
    movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Optional: Allows only one item to be selected

    // Set GroupLayout for dynamically added JList with additional space
    GroupLayout.SequentialGroup hGroup = jPanelLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED) // Add an extra gap
            .addComponent(movieList);
           
    
    GroupLayout.SequentialGroup vGroup = jPanelLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED) // Add an extra gap
            .addComponent(movieList);
            

    jPanelLayout.setHorizontalGroup(hGroup);
    jPanelLayout.setVerticalGroup(vGroup);

    // Add MouseListener to JList for handling clicks
    movieList.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
                int index = movieList.locationToIndex(evt.getPoint());
                if (index != -1) {
                    // Display popup menu
                    showPopupMenu(movieList, evt.getX(), evt.getY());
                }
            }
        }
    });

    // Refresh the jPanel1 to reflect the changes
    jPanel1.revalidate();
    jPanel1.repaint();
}
 
// Method to display the popup menu
private void showPopupMenu(JList<String> list, int x, int y) {
    JPopupMenu popupMenu = new JPopupMenu();

    JMenuItem addToSLItem = new JMenuItem("ADD TO SL");
    JMenuItem addToWLItem = new JMenuItem("ADD TO WL");
    JMenuItem playItem = new JMenuItem("PLAY");

    addToSLItem.addActionListener(e -> {
       String userlogger = mainInstance.getsingleUserFromDb();
        String selectedMovie = list.getSelectedValue();
        mainInstance.addtosaved(userlogger, selectedMovie);
        JOptionPane.showMessageDialog(null, "Added to SL");
         SavedMovies sd =  new SavedMovies();
         sd.setVisible(true);
         sd.pack();
         sd.setLocationRelativeTo(null); // centre
         this.dispose(); 
    });

    addToWLItem.addActionListener(e -> {
         String userl= mainInstance.getsingleUserFromDb();
        String selectedMovieW = list.getSelectedValue();
        mainInstance.addtowish(userl, selectedMovieW);
        JOptionPane.showMessageDialog(null, "Added to WL");
    });

    playItem.addActionListener(e -> {
        // Handle "PLAY" action
        // You can implement the logic to play the movie here
        // For example, open movieplayy.java
        movieplayy playFrame = new movieplayy();
        playFrame.setVisible(true);
        this.dispose();
    });

    popupMenu.add(addToSLItem);
    popupMenu.add(addToWLItem);
    popupMenu.add(playItem);

    popupMenu.show(list, x, y);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));

        jButton1.setForeground(new java.awt.Color(255, 153, 0));
        jButton1.setText("jButton1");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton2.setText("jButton2");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("FILM FUSION");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        jButton6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 153, 0));
        jButton6.setText("GO BACK");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(116, 116, 116))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      Dashboard dashb =  new Dashboard();
      dashb.setVisible(true);
      dashb.pack();
      dashb.setLocationRelativeTo(null); // centre
      this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new moviedisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
