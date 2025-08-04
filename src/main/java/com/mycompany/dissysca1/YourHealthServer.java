/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dissysca1;

/**
 *
 * @author angirmaa
 */

import java.io.IOException;
import java.util.logging.Logger;
import  generated.yourhealth.YourHealthGrpc.YourHealthImplBase;
import  generated.yourhealth.AvailableAppointmentDate;
import generated.yourhealth.ListOfMedicalTest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
// I imported the gRPC main clasess 


public class YourHealthServer extends YourHealthImplBase{
/*This extend YourHealthImplBase which means I can use rpc methods*/
     private static final Logger logger = Logger.getLogger(YourHealthServer.class.getName());
    /*here I declared logger*/
    public static void main(String[] args) {
        YourHealthServer yourHealthserver = new YourHealthServer (); 
        /*declaring your health server*/

        
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        int port = 50053; /*here It will work on 50053 port*/
        logger.setLevel(Level.SEVERE);
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(yourHealthserver)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("#####Server started, listening on" + port);
             /*this creating gRPC in this port and adding yourHealthserver and server will start*/
            /*it will print out the message, when server start successfully*/
            ServiceRegistration esr = ServiceRegistration.getInstance();
            esr.registerService( "_grpc._tcp.local.", "YourHealthServer", port,"text here if you like");
            server.awaitTermination();
            /*declaring the the gRPC service MyMental in that port using jmDNS */
        } catch (IOException e) {
          
            e.printStackTrace();

        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }/*when the server starts, if there is any errorr IOException or InterruptedException, it will tell */
        
    }
    public StreamObserver<ListOfMedicalTest> MedicalAdvice(StreamObserver<AvailableAppointmentDate> responseObserver) {
    /* Here server is ready to get the clients requests */
   
        return new StreamObserver<ListOfMedicalTest>() {
        ArrayList<String> list = new ArrayList<>();
    /*creating the array list that stores the client requests*/
                    
        @Override
        public void onNext(ListOfMedicalTest req) {
            System.out.println(LocalTime.now().toString()+ ": received messages: " + req.getTests()+ req.getAids()+ req.getTuberculosis()+ req.getMalaria() + req.getWaterBoneDisease());
              list.add(req.getAids());
              list.add(req.getTuberculosis());
              list.add(req.getMalaria());
              list.add(req.getWaterBoneDisease());
              /*here I'm adding all the requests to the array list*/
              /*when server finished garthering the clients responses */
              /*now it's ready send back response*/
        }

        @Override
        public void onError(Throwable thrwbl) {
            thrwbl.printStackTrace();
        }

        @Override
        public void onCompleted() {
            System.out.printf(LocalTime.now().toString() + ": Message stream complete \n");
            /*it will show that server received the requests*/
            String idate; /*declared here idate variable to store the random date*/
            idate = LocalDate.now().plusDays(new Random().nextInt(10)+1).toString();
            AvailableAppointmentDate reply = AvailableAppointmentDate.newBuilder().setBookedDate(idate).build();    
            /*here implimenting the server reponse */

				System.out.println("Server booked date: "+ idate);
            
            responseObserver.onNext(reply);
            responseObserver.onCompleted(); 
            /*this finishing the response messages*/
  
        }
    };
    }
   }
    

