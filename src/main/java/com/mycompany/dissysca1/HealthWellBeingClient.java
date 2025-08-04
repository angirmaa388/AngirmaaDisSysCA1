/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dissysca1;

/**
 *
 * @author angirmaa
 */

import static com.mycompany.dissysca1.HealthWellBeingClient.jmdns;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import  generated.healthwellbeing.HealthWellBeingGrpc.HealthWellBeingStub;
import  generated.healthwellbeing.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.net.InetAddress;
import io.grpc.stub.StreamObserver;
import java.net.UnknownHostException;
import java.time.LocalTime;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
public class HealthWellBeingClient {
    private static final Logger logger = Logger.getLogger(HealthWellBeingClient.class.getName());
    /*here I declared logger*/
    static JmDNS jmdns;
    /*using JmDNS, started looking for gRPC*/
    public static void main(String[] args) throws Exception {
        logger.setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        
        InetAddress addr = InetAddress.getByName("192.168.0.30"); 
        //note: here I used wifi IP adress as I'm using MAC 
        
        jmdns = JmDNS.create(addr); /*JmDNS using ip adress finds it */ 
//        jmdns = JmDNS.create(InetAddress.getLocalHost());
        
        String serviceType = "_grpc._tcp.local.";
        jmdns.addServiceListener(serviceType, new ServiceListener() {
            @Override
            public void serviceAdded(ServiceEvent se) {
                System.out.println("[+] Service added: " + se.getName());
                 /*here it's adding ther service when it's finds*/
                jmdns.requestServiceInfo(se.getType(), se.getName(), 1);
                // resolve ASAP
                /*if the service lost, it will show*/
            }

            @Override
            public void serviceRemoved(ServiceEvent se) {
                System.out.println("[-] Service removed: " + se.getName());
            }

            @Override
            public void serviceResolved(ServiceEvent se) {
                ServiceInfo serviceInfo = se.getInfo();
                System.out.println("##### Service resolved: " + serviceInfo.getName());
                System.out.println("    Address: " + serviceInfo.getHostAddresses()[0]);
                System.out.println("    Port: " + serviceInfo.getPort());
                /*get the port number and host name from the ServiceInfo object and prints them*/ 
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String serviceName = serviceInfo.getName();
                
                try{
                    if (serviceName.equals("HealthWellBeingServer")){
                        useHealthWellBeingService(discoveredPort, discoveredHost );
                        /*here it will catch HealthWellBeingServer and use it */
                        /*and ignores the other servers*/
                    }
                }catch (InterruptedException ex){
                    Logger.getLogger(HealthWellBeingClient.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                    Logger.getLogger(HealthWellBeingClient.class.getName()).log(Level.SEVERE, null, ex);
                }     /*if there is any sudden interrupt, it will print it*/ 
             }
        });
        
        System.out.println("#######Listening for gRPC services via JmDNS...");
//        Thread.sleep(30000);
        
    }
    private static void useHealthWellBeingService(int inPort, String inHost) throws InterruptedException, IOException {
         /*it will connect the host and port to the gRPC*/
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(inHost, inPort)
                .usePlaintext()
                .build();
        HealthWellBeingStub asyncStub = HealthWellBeingGrpc.newStub(channel);
        /*Here I declaring non blocking stub*/
        try {
            String bodyscan = "Pregnant woman";
            BodyScanner request = BodyScanner.newBuilder().setBodyscanner(bodyscan).build();
            /*it will receive the string body scanner*/
            StreamObserver<Results> responseObserver = new StreamObserver<Results>() {
                /*here observer ready to repond the client request*/
                @Override
                public void onNext(Results value) {
                    System.out.println("client received " + value.getResultmessages());
                    /*here it's showing client received the server message*/
                }

                @Override
                public void onError(Throwable t) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void onCompleted() {
                    System.out.println("client received completed ");
                    /*showig the client received message completed*/
                }
            };
            
            asyncStub.monitorTheBody(request, responseObserver); 
            
             try {
                    Thread.sleep(15000);
                    /*it will slow down the running*/
            } catch (InterruptedException e) {

                    e.printStackTrace();
                    Thread.currentThread().interrupt();
            }
                    
        }catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
             /*If there is any interrupt, it will shows the errorr*/
        } 
        
      StreamObserver<Advices> responseObserver = new StreamObserver<Advices>() {
          /*here observer ready to garther client requests*/
          /*When client sends many request this will catch them then sends back many messages*/
          

            @Override
            
            public void onNext(Advices response) {
                System.out.println(LocalTime.now().toString() + ": response from server " + response.getNewborncare()+ response.getVaccine()+ response.getTreatments() );
            } /*here showing that server received requests and sends back reponse*/

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString() + ": stream is completed.");
            } /*the stream is completed*/

        };
        
        
        StreamObserver<Requests> requestObserver = asyncStub.adviceNewBorn(responseObserver);
         /*here it's observer that clients uses to sents many request*/
        try {
            requestObserver.onNext(Requests.newBuilder().setNewborncare("new born care").build());
            System.out.println("client called server with new born care");
            /*shows the client called server with Newborncare*/
            Thread.sleep(500);
            requestObserver.onNext(Requests.newBuilder().setVaccine("new born vaccine").build());
            System.out.println("client called server with new born vaccine");
            /*shows the client called server with Vaccine*/
            Thread.sleep(500);
             requestObserver.onNext(Requests.newBuilder().setTreatments("new born treatments").build());
            System.out.println("client called server with new born treatment");
            /*shows the client called server with Treatments*/
            Thread.sleep(500);
            requestObserver.onCompleted();
            Thread.sleep(10000);
            /*Clients sends the all the request and notify that client finished sending the request*/
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }/*if there is any sudden errorr it will show*/
        finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            jmdns.close();
            /*it shows stream is complited*/
        }
        
        
    }
}
