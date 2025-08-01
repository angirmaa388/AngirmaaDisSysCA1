/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dissysca1;

/**
 *
 * @author angirmaa
 */


import generated.healthwellbeing.Advices;
import generated.healthwellbeing.HealthWellBeingGrpc.HealthWellBeingImplBase;
import java.io.IOException;
import java.util.logging.Logger;
import  generated.healthwellbeing.Results;
import generated.healthwellbeing.BodyScanner;
import generated.healthwellbeing.Requests;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.time.LocalTime;
import java.util.logging.Level;

public class HealthWellBeingServer extends HealthWellBeingImplBase {
    private static final Logger logger = Logger.getLogger(HealthWellBeingServer.class.getName());

    public static void main(String[] args) {

        HealthWellBeingServer healthWellBeingServer = new HealthWellBeingServer();
        
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        int port = 50051;
        logger.setLevel(Level.SEVERE);
        
         try {
            Server server = ServerBuilder.forPort(port)
                    .addService(healthWellBeingServer)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("#####Server started, listening on" + port);
    
        ServiceRegistration esr = ServiceRegistration.getInstance();
            esr.registerService( "_grpc._tcp.local.", "HealthWellBeingServer", port,"text here if you like");
            server.awaitTermination();

        } catch (IOException e) {
          
            e.printStackTrace();

        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }
         
         
    }
    @Override
    public void monitorTheBody(BodyScanner request, StreamObserver<Results> responseObserver){
        System.out.println("receiving body scanner request");
        Results reply1 = Results.newBuilder().setResultmessages("Pregnancy duration: 2 months pregnancy" + request.getBodyscanner()).build();
        Results reply2 = Results.newBuilder().setResultmessages("Adviced daily meal: Breakfast, Lunch, Snack, Dinner " + request.getBodyscanner()).build();
        Results reply3 = Results.newBuilder().setResultmessages("Needed nutrients:Iron, Calcium, Folic Acide, Protein, Omega3" + request.getBodyscanner()).build();
        responseObserver.onNext(reply1);
        responseObserver.onNext(reply2);
        responseObserver.onNext(reply3);
        responseObserver.onCompleted();
    } 
     public StreamObserver<Requests> adviceNewBorn(StreamObserver<Advices> responseObserver) {
    return new StreamObserver<Requests>() {
        @Override
        public void onNext(Requests req) {
            System.out.println("received messages:");
            Advices adviceReply1 = Advices.newBuilder().setNewborncare(" fed the new born every 2–3 hours, and always place the baby on their back when baby sleep." + req.getNewborncare()).build();
            Advices adviceReply2 = Advices.newBuilder().setVaccine(" 1. Hepatitis B, 2. BCG (Bacillus Calmette–Guérin)" + req.getVaccine()).build();
            Advices adviceReply3 = Advices.newBuilder().setTreatments(" 1. Vitamin K Injection, 2. Eye Ointment, 3.Phototherapy (optional)" + req.getTreatments()).build();
            
            
          responseObserver.onNext(adviceReply1); 
          responseObserver.onNext(adviceReply2);
          responseObserver.onNext(adviceReply3);
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