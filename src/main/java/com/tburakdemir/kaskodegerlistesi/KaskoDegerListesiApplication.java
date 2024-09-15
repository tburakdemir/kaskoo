package com.tburakdemir.kaskodegerlistesi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class KaskoDegerListesiApplication {

	public static void main(String[] args) {
		System.out.println(Arrays.binarySearch(new int[]{2,3,12,12,23}, 14));

		SpringApplication.run(KaskoDegerListesiApplication.class, args);





	}

}
