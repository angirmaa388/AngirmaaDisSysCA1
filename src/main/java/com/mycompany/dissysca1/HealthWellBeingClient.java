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
    
    static JmDNS jmdns;
    
    public static void main(String[] args) throws Exception {
        logger.setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        
        InetAddress addr = InetAddress.getByName("10.3.228.50");
        jmdns = JmDNS.create(addr);
//        jmdns = JmDNS.create(InetAddress.getLocalHost());
        
        String serviceType = "_grpc._tcp.local.";
        jmdns.addServiceListener(serviceType, new ServiceListener() {
            @Override
            public void serviceAdded(ServiceEvent se) {
                System.out.println("[+] Service added: " + se.getName());
                jmdns.requestServiceInfo(se.getType(), se.getName(), 1);
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
                
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String serviceName = serviceInfo.getName();
                
                try{
                    if (serviceName.equals("HealthWellBeingServer")){
                        useHealthWellBeingService(discoveredPort, discoveredHost );
                    }
                }catch (InterruptedException ex){
                    Logger.getLogger(HealthWellBeingClient.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                    Logger.getLogger(HealthWellBeingClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            
   }
        });
        System.out.println("#######Listening for gRPC services via JmDNS...");
//        Thread.sleep(30000);
        
    }
    private static void useHealthWellBeingService(int inPort, String inHost) throws InterruptedException, IOException {
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(inHost, inPort)
                .usePlaintext()
                .build();
        HealthWellBeingStub asyncStub = HealthWellBeingGrpc.newStub(channel);
        try {
            String bodyscan = "Pregnant woman";
            BodyScanner request = BodyScanner.newBuilder().setBodyscanner(bodyscan).build();
            
            StreamObserver<Results> responseObserver = new StreamObserver<Results>() {
                @Override
                public void onNext(Results value) {
                    System.out.println("client received " + value.getResultmessages());
                }

                @Override
                public void onError(Throwable t) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void onCompleted() {
                    System.out.println("client received completed ");
                }
            };
            
            asyncStub.monitorTheBody(request, responseObserver); 
            
            // the sleep here is optional - its purpose is to slow things down so we can observe what is happening
            try {
                    Thread.sleep(15000);
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
                    
        }catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
             
        } 
///
        ///
      StreamObserver<Advices> responseObserver = new StreamObserver<Advices>() {

            @Override
            
            public void onNext(Advices response) {
                System.out.println(LocalTime.now().toString() + ": response from server " + response.getNewborncare()+ response.getVaccine()+ response.getTreatments() );
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println(LocalTime.now().toString() + ": stream is completed.");
            }

        };
        
        
        StreamObserver<Requests> requestObserver = asyncStub.adviceNewBorn(responseObserver);
        try {
            requestObserver.onNext(Requests.newBuilder().setNewborncare("new born care").build());
            System.out.println("client called server with new born care");
            Thread.sleep(500);
            requestObserver.onNext(Requests.newBuilder().setVaccine("new born vaccine").build());
            System.out.println("client called server with new born vaccine");
            Thread.sleep(500);
             requestObserver.onNext(Requests.newBuilder().setTreatments("new born treatments").build());
            System.out.println("client called server with new born treatment");
            Thread.sleep(500);
            requestObserver.onCompleted();
            Thread.sleep(10000);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            jmdns.close();
        }
        
        
    }
}
