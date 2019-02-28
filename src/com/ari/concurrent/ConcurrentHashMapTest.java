package com.ari.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Address> hm = new ConcurrentHashMap<>();

        for(int i = 0;i< 10000;i++)
        {
            hm.put("Name"+i, new Address("Name"+i, i+" downy lane", Integer.toString(i)));
        }

        String results = hm.search(5, (name, address) ->{
            if (String.valueOf(name).contains(String.valueOf("Name5000"))) {
                return new String(name + ":" + address.toString());
            }
            return null;

        });

        System.out.println(results);




    }



}
