package com.iwchen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;

public class HotelApp extends Application {

    private static final List<Room.RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(Room.RoomType.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    @Override
    public void start(Stage primaryStage) {

        //Create Hotel Object and populate with Rooms
        Hotel hyattRegency = new Hotel();
        Map rooms = hyattRegency.getRoomListing();
        createRooms(50, rooms);

        //Create GridPane as layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Hyatt Regency");
        scenetitle.setFont(Font.font("Hack", FontWeight.NORMAL, 16));
        grid.add(scenetitle, 0, 0, 2, 1);

        Text bookRoom = new Text("Book Room");
        bookRoom.setFont(Font.font("Hack", FontWeight.BOLD, 14));
        grid.add(bookRoom, 0, 1, 2, 1);

        Label partySize = new Label("Number of People:");
        Label roomType = new Label("Type of Room:");
        grid.add(partySize, 0, 2);
        grid.add(roomType, 0, 3);

        TextField partySizeTextField = new TextField();
        TextField roomTypeTextField = new TextField();
        grid.add(partySizeTextField, 1, 2);
        grid.add(roomTypeTextField, 1,3);

        //Get all rooms of hotel
        Button getRoomBtn = new Button("Get All Rooms");
        HBox hbBtn = new HBox(10);
        getRoomBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                printRooms(hyattRegency);
            }
        });
        hbBtn.setAlignment(Pos.CENTER_LEFT);
        hbBtn.getChildren().add(getRoomBtn);
        grid.add(hbBtn, 0, 5);


        Scene scene = new Scene(grid, 1024, 576);

        primaryStage.setTitle("Hotel System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private static void printRooms (Hotel hotel) {
        Map<Integer, Room> rooms = hotel.getRoomListing();
        System.out.println(rooms.values());
    }

    private static void createRooms(int numRooms, Map rooms){

        while(numRooms > 0){
            Room.RoomType roomType = VALUES.get(RANDOM.nextInt(SIZE));
            // System.out.println(randRoom);
            int roomNumber = RANDOM.nextInt(2000);
            Room newRoom = new Room(roomType, roomNumber);
            rooms.put(roomNumber, newRoom);
            System.out.println(String.format("Room Created - %d: %s", roomNumber, roomType));
            numRooms--;
        }
    }
}
