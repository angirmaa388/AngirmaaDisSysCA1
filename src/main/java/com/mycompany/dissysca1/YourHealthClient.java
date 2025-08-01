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
     private static  YourHealthBlockingStub syncStub;
    private static final Logger logger = Logger.getLogger(YourHealthClient.class.getName());
    
    static JmDNS jmdns;
    

    public static void main(String[] args) throws Exception {
        
        logger.setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty").setLevel(Level.SEVERE);
        Logger.getLogger("io.grpc.netty.shaded").setLevel(Level.SEVERE);
        Logger.getLogger("javax.jmdns").setLevel(Level.SEVERE);
        
        
//        jmdns = JmDNS.create(InetAddress.getLocalHost());
        InetAddress addr = InetAddress.getByName("10.3.228.23");
        jmdns = JmDNS.create(addr);
        
        String serviceType = "_grpc._tcp.local.";
        jmdns.addServiceListener(serviceType, new ServiceListener() {

            @Override
            public void serviceAdded(ServiceEvent event) {
                System.out.println("[+] Service added: " + event.getName());
                // This triggers serviceResolved if we explicitly request resolution
                jmdns.requestServiceInfo(event.getType(), event.getName(), 1); // resolve ASAP
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
                
                // get the port number and host name from the ServiceInfo object
                String discoveredHost = serviceInfo.getHostAddresses()[0];
                int discoveredPort = serviceInfo.getPort();
                String serviceName = serviceInfo.getName();
                try{
                    if (serviceName.equals("YourHealth")){
                        useYourHealthService(discoveredPort, discoveredHost );
                    }
                }catch (InterruptedException ex){
                    Logger.getLogger(YourHealthClient.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                    Logger.getLogger(YourHealthClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            
   }
        });

        System.out.println("#######Listening for gRPC services via JmDNS...");
      //  Thread.sleep(30000);
       
    }
    private static void useYourHealthService(int inPort, String inHost) throws InterruptedException, IOException {
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(inHost, inPort)
                .usePlaintext()
                .build();
        
    
    StreamObserver<AvailableAppointmentDate> responseObserver = new StreamObserver<AvailableAppointmentDate>() {
         @Override
         public void onNext(AvailableAppointmentDate response) {
         System.out.println(LocalTime.now().toString() + ": response from server " + response.getBookedDate());
        }

         @Override
         public void onError(Throwable thrwbl) {
             thrwbl.printStackTrace();
         }

         @Override
         public void onCompleted() {
             System.out.println(LocalTime.now().toString() + ": stream is completed.");
			}
	};
    
   
                            asyncStub = YourHealthGrpc.newStub(channel);
                            syncStub = YourHealthGrpc.newBlockingStub(channel);
        
	StreamObserver<ListOfMedicalTest> requestObserver = asyncStub.medicalAdvice(responseObserver);
 
    try {
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setAids("Aids").build());
                        Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setTuberculosis("Tuberculosis").build());
			Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setMalaria("Malaria").build());
			Thread.sleep(500);
                    requestObserver.onNext(ListOfMedicalTest.newBuilder().setWaterBoneDisease("WaterBoneDisease").build());
                        Thread.sleep(500);
                        
                    requestObserver.onCompleted();
                        Thread.sleep(10000);
                        
    } catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            jmdns.close();
    
            }
    }
}


    
    
    

    
