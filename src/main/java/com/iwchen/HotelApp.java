package com.iwchen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;

public class HotelApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Create Hotel Object and populate with Rooms
        Hotel hyattRegency = new Hotel();
        Map rooms = hyattRegency.getRoomListing();
        createRooms(100, rooms);

        //Create main BorderPane layout
        BorderPane mainBorder = new BorderPane();
        //Create main GridPane as layout
        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.TOP_CENTER);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25, 25, 25, 25));
        mainBorder.setCenter(mainGrid);

        //Create search BorderPane
        BorderPane searchBorder = new BorderPane();
        //Create search GridPane
        GridPane searchGrid = new GridPane();
        searchGrid.setAlignment(Pos.TOP_CENTER);
        searchGrid.setHgap(10);
        searchGrid.setVgap(10);
        searchGrid.setPadding(new Insets(25, 25, 25, 25));
        searchBorder.setCenter(searchGrid);

        //Create Lookup BorderPane
        BorderPane lookupBorder = new BorderPane();
        //Create Lookup GridPane
        GridPane lookupGrid = new GridPane();
        lookupGrid.setAlignment(Pos.TOP_CENTER);
        lookupGrid.setHgap(10);
        lookupGrid.setVgap(10);
        lookupGrid.setPadding(new Insets(25, 25, 25, 25));
        lookupBorder.setCenter(lookupGrid);

        //Create new scene(default) and show primary stage
        Scene mainScene = new Scene(mainBorder, 1024, 576);
        Scene searchScene = new Scene(searchBorder, 1024, 576);
        Scene lookupScene = new Scene(lookupBorder, 1024, 576);
        Map<String, Scene> sceneMap = new HashMap<>();
        sceneMap.put("main", mainScene);
        sceneMap.put("search", searchScene);
        sceneMap.put("lookup", lookupScene);

        //Add Title to scenes
        addTitle(mainBorder);
        addTitle(searchBorder);
        addTitle(lookupBorder);

        //Add Side Menu to scenes
        addSideMenu(mainBorder, primaryStage, sceneMap);
        addSideMenu(searchBorder, primaryStage, sceneMap);
        addSideMenu(lookupBorder, primaryStage, sceneMap);

        //Get all rooms of hotel
        addFooter(mainBorder, hyattRegency);
        addFooter(searchBorder, hyattRegency);
        addFooter(lookupBorder, hyattRegency);

        //Book room functionality
        addBookRoom(mainGrid, hyattRegency);

        //Search Rooms by type Functionality
        addFindRoom(searchGrid, hyattRegency);

        //Lookup Room Functionality
        addLookupRoom(lookupGrid, hyattRegency);

        primaryStage.setTitle("Hotel System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void addTitle(BorderPane border) {
        Text scenetitle = new Text("Hyatt Regency");
        scenetitle.setFont(Font.font("Hack", FontWeight.BOLD, 28));
        scenetitle.setStyle("-fx-padding: 100px;");
        border.setTop(scenetitle);
        border.setAlignment(scenetitle, Pos.CENTER);
    }

    private void addSideMenu(BorderPane border, Stage stage, Map map) {
        VBox menuBar = new VBox();
        menuBar.setFillWidth(true);
        menuBar.setSpacing(10);

        Hyperlink toMainScene = new Hyperlink("Home");
        toMainScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene((Scene)map.get("main"));
            }
        });
        Hyperlink toSearchScene = new Hyperlink("Find Rooms");
        toSearchScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene((Scene)map.get("search"));
            }
        });
        Hyperlink toLookupScene = new Hyperlink("Lookup Room");
        toLookupScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene((Scene)map.get("lookup"));
            }
        });

        menuBar.getChildren().addAll(toMainScene, toSearchScene,toLookupScene);
        border.setLeft(menuBar);
    }

    /**
     * Reserve a room with party size and type of room
     * @param grid - GridPane to attach to
     * @param hotel - Hotel to reserve from
     */
    private void addBookRoom(GridPane grid, Hotel hotel) {
        Text bookRoom = new Text("Book Room");
        bookRoom.setFont(Font.font("Hack", FontWeight.SEMI_BOLD, 14));
        grid.add(bookRoom, 0, 1, 2, 1);

        Label partySize = new Label("Number of People:");
        Label roomType = new Label("Type of Room:");
        grid.add(partySize, 0, 2);
        grid.add(roomType, 0, 3);

        TextField partySizeTextField = new TextField();
        ComboBox roomTypeOptions = new ComboBox();
        roomTypeOptions.getItems().addAll(Room.RoomType.values());
        roomTypeOptions.setValue(Room.RoomType.SINGLE);
        grid.add(partySizeTextField, 1, 2);
        grid.add(roomTypeOptions, 1, 3);

        Button submitBook = new Button("Submit");
        submitBook.setAlignment(Pos.CENTER_RIGHT);
        submitBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hotel.bookRoom(Integer.parseInt(partySizeTextField.getText()),null,null, (Room.RoomType)roomTypeOptions.getValue());
                partySizeTextField.clear();
            }
        });
        grid.add(submitBook, 1, 4);
        GridPane.setHalignment(submitBook, HPos.RIGHT);
    }

    /**
     * Search for rooms by Type
     * @param grid - GridPane to attach to
     * @param hotel - Hotel to search for rooms
     */
    private void addFindRoom(GridPane grid, Hotel hotel) {
        Text findRooms = new Text("Find Room");
        findRooms.setFont(Font.font("Hack", FontWeight.SEMI_BOLD, 14));
        grid.add(findRooms, 0, 1, 2, 1);

        ComboBox roomTypeOptionsBox = new ComboBox();
        roomTypeOptionsBox.getItems().addAll(Room.RoomType.values());
        roomTypeOptionsBox.setValue(Room.RoomType.SINGLE);
        ComboBox availBox = new ComboBox();
        availBox.getItems().addAll(true, false);
        availBox.setValue(true);

        Button submitSearch = new Button("Search");
        submitSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Room> rooms = hotel.getRoom((Room.RoomType)roomTypeOptionsBox.getValue(),(boolean)availBox.getValue());
                System.out.print(rooms);
            }
        });

        grid.add(roomTypeOptionsBox, 1, 2);
        grid.add(availBox, 1, 3);
        grid.add(submitSearch, 1,   4);
        GridPane.setFillWidth(availBox,true);
        GridPane.setHalignment(submitSearch, HPos.RIGHT);

        Label availText = new Label("Availability");
        Label roomType2 = new Label("Room Type");
        grid.add(roomType2, 0, 2);
        grid.add(availText, 0, 3);
    }

    private void addLookupRoom(GridPane grid, Hotel hotel) {
        Text findRooms = new Text("Lookup Room");
        findRooms.setFont(Font.font("Hack", FontWeight.SEMI_BOLD, 14));
        grid.add(findRooms, 0, 1, 2, 1);

        TextField roomNumberField = new TextField();
        grid.add(roomNumberField, 1, 2);

        Button submitLookup = new Button("Search");
        submitLookup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room r = hotel.getRoom(Integer.parseInt(roomNumberField.getText()));
                System.out.println(r);
            }
        });
        grid.add(submitLookup, 1, 3);
        GridPane.setHalignment(submitLookup, HPos.RIGHT);

        Label roomNumberLabel = new Label("Room Number");
        grid.add(roomNumberLabel, 0, 2);
    }

    /**
     * Adds button to retrieve all rooms in the hotel
     * @param border - BorderPane to attach to
     * @param hotel - Hotel to get rooms from
     */
    private void addFooter(BorderPane border, Hotel hotel){
        Button getRoomBtn = new Button("Get All Rooms");
        HBox hbBtn = new HBox(10);
        hbBtn.setPadding(new Insets(15, 12, 15, 12));
        getRoomBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                printRooms(hotel);
            }
        });
        hbBtn.getChildren().add(getRoomBtn);
        border.setBottom(hbBtn);
    }

    private static void printRooms (Hotel hotel) {
        Map<Integer, Room> rooms = hotel.getRoomListing();
        System.out.println(rooms.values());
    }

    private static void createRooms(int numRooms, Map rooms){
        List<Room.RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(Room.RoomType.values()));
        int SIZE = VALUES.size();
        Random RANDOM = new Random();

        Set<Integer> uniqueNumbers = new HashSet<>();
        while(uniqueNumbers.size() < numRooms){
            Room.RoomType roomType = VALUES.get(RANDOM.nextInt(SIZE));
            // System.out.println(randRoom);
            int roomNumber = RANDOM.nextInt(2000);
            if(uniqueNumbers.add(roomNumber)) {
                Room newRoom = new Room(roomType, roomNumber);
                rooms.put(roomNumber, newRoom);
                System.out.println(String.format("Room Created - %d: %s", roomNumber, roomType));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
