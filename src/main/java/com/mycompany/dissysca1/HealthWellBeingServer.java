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
// I imported the gRPC main clasess 
public class HealthWellBeingServer extends HealthWellBeingImplBase {
    
    /*This extend HealthWellBeingImplBase which means I can use rpc methods*/
    
    private static final Logger logger = Logger.getLogger(HealthWellBeingServer.class.getName());
/*here I declared logger*/
    public static void main(String[] args) {
/*declaring my mental server*/
        HealthWellBeingServer healthWellBeingServer = new HealthWellBeingServer();
        
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        int port = 50051; /*here It will work on 50051 port*/
        logger.setLevel(Level.SEVERE);
        
         try {
            Server server = ServerBuilder.forPort(port)
                    .addService(healthWellBeingServer)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("#####Server started, listening on" + port);
    /*this creating gRPC in this port and adding healthWellBeingServer and server will start*/
            /*it will print out the message, when server start successfully*/
        ServiceRegistration esr = ServiceRegistration.getInstance();
            esr.registerService( "_grpc._tcp.local.", "HealthWellBeingServer", port,"text here if you like");
            server.awaitTermination();
/*declaring the the gRPC service HealthWellBeingServer in that port using jmDNS */
        } catch (IOException e) {
          
            e.printStackTrace();

        } catch (InterruptedException e) {
           
            e.printStackTrace();
        }/*when the server starts, if there is any errorr IOException or InterruptedException, it will tell */

         
         
    }
    @Override
    public void monitorTheBody(BodyScanner request, StreamObserver<Results> responseObserver){
        /*here server starts working garthering the client response and ready to send back multinple response*/
        System.out.println("receiving body scanner request");
        /*server showing that it received client request*/
        Results reply1 = Results.newBuilder().setResultmessages("Pregnancy duration: 2 months pregnancy" + request.getBodyscanner()).build();
        Results reply2 = Results.newBuilder().setResultmessages("Adviced daily meal: Breakfast, Lunch, Snack, Dinner " + request.getBodyscanner()).build();
        Results reply3 = Results.newBuilder().setResultmessages("Needed nutrients:Iron, Calcium, Folic Acide, Protein, Omega3" + request.getBodyscanner()).build();
        responseObserver.onNext(reply1);
        responseObserver.onNext(reply2);
        responseObserver.onNext(reply3);
        responseObserver.onCompleted();
        /*here server sends back multible response using onNext*/
        /*this finished sending the response messages onCompleted()*/
    } 
     public StreamObserver<Requests> adviceNewBorn(StreamObserver<Advices> responseObserver) {
         /*here server is ready to receive multible request */
         /*server will receive multiple requests then sends back many response messages*/
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
          /*here server sends back multible response using onNext for the every request*/
        }

        @Override
        public void onError(Throwable thrwbl) {
            thrwbl.printStackTrace();
        }

        @Override
        public void onCompleted() {
            System.out.printf(LocalTime.now().toString() + ": Message stream complete \n");
            responseObserver.onCompleted(); 
             /*this finishing the response messages*/
        }
    };
    }
}