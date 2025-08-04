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

import  generated.mymental.MyMentalGrpc.MyMentalImplBase;
import  generated.mymental.Advice;
import generated.mymental.MentalIssue;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.logging.Level;
// I imported the gRPC main clasess 
public class MyMentalServer extends MyMentalImplBase{
/*This extend GreeterImplBase which means I can use rpc methods*/

    private static final Logger logger = Logger.getLogger(MyMentalServer.class.getName());
/*here I declared logger*/
    public static void main(String[] args) {

        MyMentalServer myMentalserver = new MyMentalServer();
/*declaring my mental server*/

       Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
       Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
       Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        int port = 50052; /*here It will work on 50052 port*/
       logger.setLevel(Level.SEVERE);
        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(myMentalserver)
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            System.out.println("#####Server started, listening on" + port);
            /*this creating gRPC in this port and adding myMentalserver and server will start*/
            /*it will print out the message, when server start successfully*/
    
        ServiceRegistration esr = ServiceRegistration.getInstance();
            esr.registerService( "_grpc._tcp.local.", "MyMental", port,"text here if you like");
            server.awaitTermination();
         /*declaring the the gRPC service MyMental in that port using jmDNS */
        } catch (IOException e) {
          
            e.printStackTrace();

        } catch (InterruptedException e) {
           
            e.printStackTrace();
        } /*when the server starts, if there is any errorr IOException or InterruptedException, it will tell */

}
    
    @Override
    public void mentalAdvice(MentalIssue request, StreamObserver<Advice> responseObserver) {
                /* mentalAdvice() this when cleints sends request, it sends the repose */
        System.out.println("receiving issue request"); 
        /*shows server received the cleint's request*/

        Advice reply = Advice.newBuilder().setAdvice("Advice  " + request.getMentalIssue()+ ". We will contact you to Dr. Anna Lembke. She will give more further advice").build();
        /*creating advice message*/

        responseObserver.onNext(reply);

        responseObserver.onCompleted();
        /*this finishing the Advice messages*/
    }
    
}

