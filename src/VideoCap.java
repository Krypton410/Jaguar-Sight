
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maxwell
 */
public class VideoCap {
    public static void main (String[] args){
    
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    
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
    
    }

