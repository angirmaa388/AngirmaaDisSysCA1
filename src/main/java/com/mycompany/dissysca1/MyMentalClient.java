/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dissysca1;

/**
 *
 * @author angirmaa
 */

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import  generated.mymental.MyMentalGrpc.MyMentalBlockingStub;
import  generated.mymental.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class MyMentalClient {
    private static final Logger logger = Logger.getLogger(MyMentalClient.class.getName());
    
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
                try {
                    // now that the service is resolved we can use it
                    // check that it is the specific service we want
                    if (serviceName.equals("MyMental")) {
                        useMyMentalService(discoveredPort, discoveredHost );
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyMentalClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyMentalClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        System.out.println("#######Listening for gRPC services via JmDNS...");
        //Thread.sleep(30000);
       
    }
        
    private static void useMyMentalService(int inPort, String inHost) throws InterruptedException, IOException {
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(inHost, inPort)
                .usePlaintext()
                .build();
        MyMentalBlockingStub blockingStub = MyMentalGrpc.newBlockingStub(channel);
        try {
            String issue = "Feeling unsercure in social platforms";
            MentalIssue request = MentalIssue.newBuilder().setMentalIssue(issue).build();

            Advice response = blockingStub.mentalAdvice(request);
            System.out.println("#######Giving Advice: " + response.getAdvice());
            
            
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
             
        } finally {
            //shutdown channel
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            jmdns.close();
        }
         
    }
 
}
