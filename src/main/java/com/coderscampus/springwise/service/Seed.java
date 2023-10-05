package com.coderscampus.springwise.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Seed {

	public Object getRandomElement(Object[] set) {
        if (set.length == 0) {
            throw new IllegalArgumentException("The set is empty.");
        }

        int randomIndex = new Random().nextInt(set.length);
        

        return set[randomIndex];
    } 
}
