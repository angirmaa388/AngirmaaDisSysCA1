/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dissysca1;

/**
 *
 * @author angirmaa
 */
import generated.yourhealth.AvailableAppointmentDate;
import generated.yourhealth.ListOfMedicalTest;
import generated.yourhealth.YourHealthGrpc;
import generated.yourhealth.YourHealthGrpc.YourHealthBlockingStub;
import generated.yourhealth.YourHealthGrpc.YourHealthStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class YourHealthClient {
    private static  YourHealthStub asyncStub;
   
     
    private static final Logger logger = Logger.getLogger(YourHealthClient.class.getName());
     /*here I declared logger*/
    static JmDNS jmdns;
    /*using JmDNS, started looking for gRPC*/

    public static void main(String[] args) throws Exception {
        
        logger.setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        
        
        InetAddress addr = InetAddress.getByName("192.168.0.30");
        jmdns = JmDNS.create(addr); /*JmDNS using ip adress finds it */
        //        jmdns = JmDNS.create(InetAddress.getLocalHost());
        
        String serviceType = "_grpc._tcp.local.";
        jmdns.addServiceListener(serviceType, new ServiceListener() {

            @Override
            public void serviceAdded(ServiceEvent event) {
                System.out.println("[+] Service added: " + event.getName());
                /*here it's adding ther service when it's finds*/
                jmdns.requestServiceInfo(event.getType(), event.getName(), 1); // resolve ASAP
                /*if the service lost, it will show*/
            }

            @Override
            public void serviceRemoved(ServiceEvent event) {
                System.out.println("[-] Service removed: " + event.getName());
            }

            @Override
            public void serviceResolved(ServiceEvent event) {
                ServiceInfo serviceInfo = event.getInfo();
                System.out.println("##### Service resolved: " + serviceInfo.getName());
                System.out.println("    Address: " + serviceInfo.getHostAddresses()[0]);
                System.out.println("    Port: " + serviceInfo.getPort());
                
                // get the port number and host name from the ServiceInfo object and prints them 
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String serviceName = serviceInfo.getName();
                try{
                    if (serviceName.equals("YourHealthServer")){      
                        useYourHealthService(discoveredPort, discoveredHost );
                        /*here it will catch YourHealthServer and use it */
                        /*and ignores the other servers*/
                    }
                }catch (InterruptedException ex){
                    Logger.getLogger(YourHealthClient.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                    Logger.getLogger(YourHealthClient.class.getName()).log(Level.SEVERE, null, ex);
                }    /*if there is any sudden interrupt, it will print it*/
            }
        });

        System.out.println("#######Listening for gRPC services via JmDNS...");
      //  Thread.sleep(30000);
       
    }
    
    private static void useYourHealthService(int inPort, String inHost) throws InterruptedException, IOException {
        /*it will connect the host and port to the gRPC*/
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(inHost, inPort)
                .usePlaintext()
                .build();
        
        YourHealthGrpc.YourHealthStub asyncStub = YourHealthGrpc.newStub(channel);
        /*this service is client streaming so it's non blocking stub*/
        
    StreamObserver<AvailableAppointmentDate> responseObserver = new StreamObserver<AvailableAppointmentDate>() {
        /*it's ready receive the server message*/
         @Override
         public void onNext(AvailableAppointmentDate response) {
         System.out.println(LocalTime.now().toString() + ": ####response from server " + response.getBookedDate());
        }

         @Override
         public void onError(Throwable thrwbl) {
             thrwbl.printStackTrace();
         }

         @Override
         public void onCompleted() {
             System.out.println(LocalTime.now().toString() + ": stream is completed.");
             
             /*it shows stream is complited*/
             
            }
	};
 
                            
                           
	StreamObserver<ListOfMedicalTest> requestObserver = asyncStub.medicalAdvice(responseObserver);
        /*here it's observer that clients uses to sents many request*/
	
    try {
        
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setAids("Aids").build());
                    System.out.println("client called server with Aids");
                    /*shows the client called server with Aids*/
                        Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setTuberculosis("Tuberculosis").build());
                    System.out.println("client called server with Tuberculosis");
                    /*shows the client called server with Tuberculosis*/
			Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setMalaria("Malaria").build());
                    System.out.println("client called server with Malaria");
                    /*shows the client called server with Malaria*/
                        Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setWaterBoneDisease("WaterBoneDisease").build());
                    System.out.println("client called server with WaterBoneDisease"); 
                    /*shows the client called server with WaterBoneDisease*/
                     
                    requestObserver.onCompleted();
                        Thread.sleep(3000);
                /*Clients sends the all the request and notify that client finished sending the request*/
                        
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } /*if there is any sudden errorr it will show*/
        finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            jmdns.close();
        } /*closing the channel*/
        
        
    }
}

    
    
    

    
