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
import java.time.LocalTime;
import java.util.Random;
import java.util.logging.Level;



public class YourHealthServer extends YourHealthImplBase{
     private static final Logger logger = Logger.getLogger(YourHealthServer.class.getName());
    
    public static void main(String[] args) {
        YourHealthServer yourHealthserver = new YourHealthServer (); 
        
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        int port = 50053;
        logger.setLevel(Level.SEVERE);
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(yourHealthserver)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("#####Server started, listening on" + port);
            
            ServiceRegistration esr = ServiceRegistration.getInstance();
            esr.registerService( "_grpc._tcp.local.", "YourHealthServer", port,"text here if you like");
            server.awaitTermination();

        } catch (IOException e) {
          
            e.printStackTrace();

        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }
        
    }
    public StreamObserver<ListOfMedicalTest> MedicalAdvice(StreamObserver<AvailableAppointmentDate> responseObserver) {
    return new StreamObserver<ListOfMedicalTest>() {
        
        
        
        
        @Override
        public void onNext(ListOfMedicalTest req) {
            System.out.println(LocalTime.now().toString()+ ": received messages: "+ req.getAids()+ req.getTuberculosis()+ req.getMalaria() + req.getWaterBoneDisease());
            String bookedDate; 
            bookedDate = LocalTime.now().plusHours(new Random().nextInt(10)+1).toString();
            AvailableAppointmentDate reply = AvailableAppointmentDate.newBuilder().setBookedDate(bookedDate).build();    
       

				System.out.println("Server booked date: "+ bookedDate);
            
            responseObserver.onNext(reply);
            responseObserver.onCompleted();   
        }

        @Override
        public void onError(Throwable thrwbl) {
            
        }

        @Override
        public void onCompleted() {
            
         System.out.printf(LocalTime.now().toString() + ": Message stream complete \n");
        }
    };
    }
   }
    

