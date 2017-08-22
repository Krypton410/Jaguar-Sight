
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
import org.opencv.videoio.VideoCapture;
import static org.opencv.imgcodecs.Imgcodecs.imencode; 
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxwell
 */
public class Prime extends javax.swing.JFrame {

    /**
     * Creates new form Prime
     */
    
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
    String name = null, criminalRecord = null, age = null;
    int id = 0;
    PreparedStatement prepared;
    class DaemonThread implements Runnable{
       protected volatile boolean runnable = false;
    @Override
    public void run(){
       Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
   
//            CascadeClassifier faceDetector = new CascadeClassifier(Prime.class.getResource("haarcascade_eye.xml").getPath().substring(1));
    CascadeClassifier faceDetector = new CascadeClassifier(Prime.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    CascadeClassifier eyeDetector = new CascadeClassifier(Prime.class.getResource("haarcascade_eye.xml").getPath().substring(1));
//    CascadeClassifier eyeDetector = new CascadeClassifier(Prime.class.getResource("haarcascade_smile.xml").getPath().substring(1));
    MatOfRect faceDetections = new MatOfRect();
    MatOfRect eyeDetection = new MatOfRect();
        synchronized(this){
    
    while(runnable){
    
    if(webSource.grab()){
    try{
        
        webSource.retrieve(frame);
        Graphics g = jPanel1.getGraphics();
        faceDetector.detectMultiScale(frame, faceDetections);
             g.setFont(g.getFont().deriveFont(40f));
            g.setColor(Color.RED);
        for (Rect rect : faceDetections.toArray()){
            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));

            
            Imgproc.putText(frame, "Name: "+name, new Point (rect.x, rect.y + 200), Core.FONT_HERSHEY_SIMPLEX
                    , 1, new Scalar(0,255,0),1);
                        Imgproc.putText(frame, "Age: "+age, new Point (rect.x, rect.y + 225), Core.FONT_HERSHEY_SIMPLEX
                    , 1, new Scalar(0,255,0),1);
            Imgproc.putText(frame, "Criminal Record: " + criminalRecord, new Point (rect.x, rect.y + 250), Core.FONT_HERSHEY_SIMPLEX
                    , 1, new Scalar(0,255,0),1);

        }
//            eyeDetector.detectMultiScale(frame, eyeDetection);
//                for (Rect rect : eyeDetection.toArray()){
//            Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0));
//
//
//        }
        
        
    
        
        Imgcodecs.imencode(".bmp", frame, mem);
        Image im = ImageIO.read(new ByteArrayInputStream (mem.toArray()));
        BufferedImage buff = (BufferedImage) im;
     
        if(g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150,  0, 0, buff.getWidth(), buff.getHeight(), null)){
            
        if(runnable == false){
        System.out.print("Paused...");
        this.wait();
        }
        
        
        
        }
        
    }
    catch(Exception e){System.out.print("Error");}
    }
    }
    
    }
    }
    
    }
    
    public Prime() throws SQLException{
        initComponents();
        setTitle("O");
        setSize(727, 699);
        setResizable(false);
        Connection conn = null;
        java.sql.Statement st = null;
        ResultSet rs = null;
       
            
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Persons","root", "root");
            st= conn.createStatement();
            

            
            rs = st.executeQuery("SELECT * FROM ROOT.PERSONS");
            while(rs.next()){
            
            id = rs.getInt("ID");
            name = rs.getString("Name");
            criminalRecord = rs.getString("CRIMINAL_RECORD");
            age = rs.getString("AGE");
            
            System.out.print(String.valueOf(id));
            
            System.out.print(name);
            
            }
            rs.close();
            st.close();
            conn.close();
            
            
        
       
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.out.println(Prime.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
System.out.println(Prime.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Open Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        jButton2.setText("Start");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pause");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shootImage(){
                VideoCapture camera = new VideoCapture(0);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VideoCap.class.getName()).log(Level.SEVERE, null, ex);
        }
    if(!camera.isOpened()){
    
    System.out.println("error");}
    else{
            Mat frame = new Mat();
            while(true){
            if(camera.read(frame)){
                System.out.println("Frame Obtained");
                System.out.println("Width : " + frame.width() + "\nHeight: " + frame.height());
                Imgcodecs.imwrite("dj.jpg", frame);
                System.out.print("Done");
                break;
            
            }}}
   camera.release();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

         shootImage();


        IplImage image = cvLoadImage("C:\\Users\\Maxwell\\Desktop\\WPG\\ObjectTracking\\dj.jpg");
        final CanvasFrame canvas = new CanvasFrame("0");
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        
        canvas.showImage(converter.convert(image));
        
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        webSource = new VideoCapture(0);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VideoCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        myThread = new DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
        jButton2.setEnabled(false);
        jButton3.setEnabled(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        myThread.runnable = false;
        jButton2.setEnabled(true);
        jButton3.setEnabled(false);
        webSource.release();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Prime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
           
            public void run() {
                try {
                    new Prime().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Prime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
