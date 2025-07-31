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
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {
  private static JmDNS jmdns;
  private static ServiceRegistration theRegister;  
  
  
  private ServiceRegistration() throws UnknownHostException, IOException {
        InetAddress addr = InetAddress.getByName("10.3.228.23");
        jmdns = JmDNS.create(addr);
//        jmdns = JmDNS.create(InetAddress.getLocalHost());
        System.out.println("Register: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
    }
  
  
  public static ServiceRegistration getInstance() throws IOException {
        if (theRegister == null) {
            theRegister = new ServiceRegistration();
        }
        return theRegister;

    }
  
  public void registerService(String type, String name, int port, String text) throws IOException {

       
        ServiceInfo serviceInfo = ServiceInfo.create(type, name, port, text);
       
        jmdns.registerService(serviceInfo);
        System.out.println("Registered Service " + serviceInfo.toString());
    }
  
}
