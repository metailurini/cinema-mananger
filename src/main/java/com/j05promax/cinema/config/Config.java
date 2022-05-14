package com.j05promax.cinema.config;

import java.sql.SQLException;

import com.j05promax.cinema.repo.HelloRepo;
import com.j05promax.cinema.util.db.PostgreSQL;
import com.j05promax.cinema.util.log.Log;

public class Config {
    private static Config single_instance = null;

    public PostgreSQL postgreSQL;
    public HelloRepo helloRepo;

    private Config() {
		postgreSQL = new PostgreSQL();

		try {
            // postgresql://dyferylqhijzep:b1f9d7c095fd085cd8d1127ca1b974547fc0f2eef510ad66a57abb18479cc672@ec2-3-228-75-39.compute-1.amazonaws.com:5432/d547mp4ioththq
            // jdbc:postgresql://localhost:5432/bob?user=postgres&password=example
			postgreSQL.Open("jdbc:postgresql://ec2-3-228-75-39.compute-1.amazonaws.com:5432/d547mp4ioththq?user=dyferylqhijzep&password=b1f9d7c095fd085cd8d1127ca1b974547fc0f2eef510ad66a57abb18479cc672");
			helloRepo = new HelloRepo(postgreSQL);
		} catch (SQLException e) {
            new Log(e).Show();
		}
    }

    public static Config getInstance() {
        if (single_instance == null) {
            single_instance = new Config();
        }

        return single_instance;
    }
}