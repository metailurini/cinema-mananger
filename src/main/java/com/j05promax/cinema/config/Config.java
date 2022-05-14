package com.j05promax.cinema.config;

import java.sql.SQLException;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.entity.Calendar;
import com.j05promax.cinema.entity.Film;
import com.j05promax.cinema.entity.Post;
import com.j05promax.cinema.entity.Preparation;
import com.j05promax.cinema.entity.Promotion;
import com.j05promax.cinema.entity.Room;
import com.j05promax.cinema.entity.Seat;
import com.j05promax.cinema.entity.Ticket;
import com.j05promax.cinema.entity.User;
import com.j05promax.cinema.repo.AdminRepo;
import com.j05promax.cinema.repo.CalendarRepo;
import com.j05promax.cinema.repo.EventRepo;
import com.j05promax.cinema.repo.FilmRepo;
import com.j05promax.cinema.repo.HelloRepo;
import com.j05promax.cinema.repo.PostRepo;
import com.j05promax.cinema.repo.PreparationRepo;
import com.j05promax.cinema.repo.PromotionRepo;
import com.j05promax.cinema.repo.RoomRepo;
import com.j05promax.cinema.repo.SeatRepo;
import com.j05promax.cinema.repo.TicketRepo;
import com.j05promax.cinema.repo.UserRepo;
import com.j05promax.cinema.util.db.PostgreSQL;
import com.j05promax.cinema.util.log.Log;

import antlr.debug.Event;

public class Config {
    private static Config single_instance = null;

    public PostgreSQL postgreSQL;
    public HelloRepo helloRepo;
    public AdminRepo adminRepo;
    public CalendarRepo calendarRepo;
    public EventRepo eventRepo;
    public FilmRepo filmRepo;
    public PostRepo postRepo;
    public PreparationRepo preparationRepo;
    public PromotionRepo promotionRepo;
    public RoomRepo roomRepo;
    public SeatRepo seatRepo;
    public TicketRepo ticketRepo;
    public UserRepo userRepo;

    private Config() {
		postgreSQL = new PostgreSQL();

		try {
            // postgresql://dyferylqhijzep:b1f9d7c095fd085cd8d1127ca1b974547fc0f2eef510ad66a57abb18479cc672@ec2-3-228-75-39.compute-1.amazonaws.com:5432/d547mp4ioththq
            // jdbc:postgresql://localhost:5432/bob?user=postgres&password=example
			postgreSQL.Open("jdbc:postgresql://ec2-3-228-75-39.compute-1.amazonaws.com:5432/d547mp4ioththq?user=dyferylqhijzep&password=b1f9d7c095fd085cd8d1127ca1b974547fc0f2eef510ad66a57abb18479cc672");
			// postgresql://dyferylqhijzep:b1f9d7c095fd085cd8d1127ca1b974547fc0f2eef510ad66a57abb18479cc672@ec2-3-228-75-39.compute-1.amazonaws.com:5432/d547mp4ioththq
            helloRepo = new HelloRepo(postgreSQL);
            adminRepo = new AdminRepo(postgreSQL);
            calendarRepo = new CalendarRepo(postgreSQL);
            eventRepo = new EventRepo(postgreSQL);
            filmRepo = new FilmRepo(postgreSQL);
            postRepo = new PostRepo(postgreSQL);
            preparationRepo = new PreparationRepo(postgreSQL);
            promotionRepo = new PromotionRepo(postgreSQL);
            roomRepo = new RoomRepo(postgreSQL);
            seatRepo = new SeatRepo(postgreSQL);
            ticketRepo = new TicketRepo(postgreSQL);
            userRepo = new UserRepo(postgreSQL);
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