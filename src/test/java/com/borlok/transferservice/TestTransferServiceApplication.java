package com.borlok.transferservice;

import org.springframework.boot.SpringApplication;

public class TestTransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(TransferServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
