package com.j05promax.cinema.service;

public class Service {
    private static Service single_instance = null;
    public AdminService Admin;

    private Service() {
        this.Admin = new AdminService();
    }

    public static Service getInstance() {
        if (single_instance == null) {
            single_instance = new Service();
        }

        return single_instance;
    }
}
