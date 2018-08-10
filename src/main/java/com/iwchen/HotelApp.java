package com.iwchen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;

public class HotelApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Create Hotel Object and populate with Rooms
        Hotel peninsula = new Hotel();
        Map rooms = peninsula.getRoomListing();
        createRooms(100, rooms);

        //Create main BorderPane layout
        BorderPane mainBorder = new BorderPane();
        //Create main GridPane as layout
        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.TOP_CENTER);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25, 25, 25, 25));
//        mainGrid.setStyle("-fx-background-color: #94b7ef;");
        mainBorder.setCenter(mainGrid);

        //Create search BorderPane
        BorderPane searchBorder = new BorderPane();
        //Create search GridPane
        GridPane searchGrid = new GridPane();
        searchGrid.setAlignment(Pos.TOP_CENTER);
        searchGrid.setHgap(10);
        searchGrid.setVgap(10);
        searchGrid.setPadding(new Insets(25, 25, 25, 25));
//        searchGrid.setStyle("-fx-background-color: #94b7ef;");
        searchBorder.setCenter(searchGrid);

        //Create Lookup BorderPane
        BorderPane lookupBorder = new BorderPane();
        //Create Lookup GridPane
        GridPane lookupGrid = new GridPane();
        lookupGrid.setAlignment(Pos.TOP_CENTER);
        lookupGrid.setHgap(10);
        lookupGrid.setVgap(10);
        lookupGrid.setPadding(new Insets(25, 25, 25, 25));
//        lookupGrid.setStyle("-fx-background-color: #94b7ef;");
        lookupBorder.setCenter(lookupGrid);

        //Create checkout BorderPane
        BorderPane checkoutBorder = new BorderPane();
        //Create checkout GridPane
        GridPane checkoutGrid = new GridPane();
        checkoutGrid.setAlignment(Pos.TOP_CENTER);
        checkoutGrid.setHgap(10);
        checkoutGrid.setVgap(10);
        checkoutGrid.setPadding(new Insets(25));
        checkoutBorder.setCenter(checkoutGrid);

        //Create main scene
        Scene mainScene = new Scene(mainBorder, 1024, 576);

        //Map of BorderPanes for easy lookup and reference
        Map<String, BorderPane> borderMap = new HashMap<>();
        borderMap.put("main", mainBorder);
        borderMap.put("search", searchBorder);
        borderMap.put("lookup", lookupBorder);
        borderMap.put("checkout", checkoutBorder);

        //Add Title to scenes
        addTitle(mainBorder);
        addTitle(searchBorder);
        addTitle(lookupBorder);
        addTitle(checkoutBorder);

        //Add Side Menu to scenes
        addSideMenu(mainBorder, mainScene, borderMap);
        addSideMenu(searchBorder, mainScene, borderMap);
        addSideMenu(lookupBorder, mainScene, borderMap);
        addSideMenu(checkoutBorder, mainScene, borderMap);

        //Add Search Bar
        addRightBar(mainBorder,peninsula);
        addRightBar(searchBorder,peninsula);
        addRightBar(lookupBorder,peninsula);
        addRightBar(checkoutBorder,peninsula);

        //Add Footer Menu
        addFooter(mainBorder, peninsula);
        addFooter(searchBorder, peninsula);
        addFooter(lookupBorder, peninsula);
        addFooter(checkoutBorder, peninsula);

        //Book room functionality
        addBookRoom(mainGrid, peninsula);

        //Search Rooms by type Functionality
        addFindRoom(searchGrid, peninsula);

        //Lookup Room Functionality
        addLookupRoom(lookupGrid, peninsula);

        //Checkout Functionality
        addCheckoutRoom(checkoutGrid, peninsula);

        primaryStage.setTitle("Hotel System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Add Title to each BorderPane
     * @param border - BorderPane to add title to
     */
    private void addTitle(BorderPane border) {
        Text scenetitle = new Text("The Peninsula Chicago");
        scenetitle.setFont(Font.font("Hack", FontWeight.BOLD, 28));
        border.setTop(scenetitle);
        BorderPane.setMargin(scenetitle, new Insets(15, 0, 15, 0));
        border.setAlignment(scenetitle, Pos.CENTER);
    }

    /**
     * Add a sidebar menu to each BorderPane
     * @param border - BorderPane to add menu to
     * @param scene - primary scene to switch sub-BorderPanes off of
     * @param map - Map of BorderPanes to switch to
     */
    private void addSideMenu(BorderPane border, Scene scene, Map map) {
        VBox menuBar = new VBox();
        menuBar.setFillWidth(true);
        menuBar.setSpacing(5);
        menuBar.setPadding(new Insets(10));

        Hyperlink toMainScene = new Hyperlink("Home");
        toMainScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot((Parent) map.get("main"));
            }
        });
        Hyperlink toSearchScene = new Hyperlink("Find Rooms");
        toSearchScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot((Parent) map.get("search"));
            }
        });
        Hyperlink toLookupScene = new Hyperlink("Lookup Room");
        toLookupScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot((Parent) map.get("lookup"));
            }
        });
        Hyperlink toCheckoutScene = new Hyperlink("CheckOut");
        toCheckoutScene.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.setRoot((Parent)map.get("checkout"));
            }
        });

        menuBar.getChildren().addAll(toMainScene, toSearchScene, toLookupScene, toCheckoutScene);
        border.setLeft(menuBar);
    }

    private void addRightBar(BorderPane border, Hotel hotel) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        HBox textAndSearch = new HBox();
        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");
        roomNumberField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    Room r = hotel.getRoom(Integer.parseInt(roomNumberField.getText()));
                    System.out.println(r);
                }
            }
        });
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Room r = hotel.getRoom(Integer.parseInt(roomNumberField.getText()));
                System.out.println(r);
            }
        });

        Button getRoomBtn = new Button("List All Rooms");
        getRoomBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printRooms(hotel);
            }
        });
        textAndSearch.getChildren().addAll(roomNumberField, searchButton);
        vbox.getChildren().addAll(textAndSearch, getRoomBtn);
        border.setRight(vbox);
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
     * Checkout a room
     * @param grid - GridPane to add content to
     * @param hotel - Hotel to reference for checkout
     */
    private void addCheckoutRoom(GridPane grid, Hotel hotel) {
        Text checkout = new Text("Checkout");
        checkout.setFont(Font.font("Hack", FontWeight.SEMI_BOLD, 14));
        grid.add(checkout, 0 , 1, 2, 1);

        Label roomNumberLabel = new Label("Room Number");
        grid.add(roomNumberLabel, 0, 2);

        TextField roomNumberTextField = new TextField();
        grid.add(roomNumberTextField, 1, 2);

        Button confirm = new Button("Confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hotel.checkoutRoom(Integer.parseInt(roomNumberTextField.getText()));
                System.out.println(String.format("Successfully checked out %s", roomNumberTextField.getText()));
            }
        });
        grid.add(confirm, 1, 3);
        GridPane.setHalignment(confirm, HPos.RIGHT);
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

        Label availText = new Label("Availability");
        Label roomType2 = new Label("Room Type");
        grid.add(roomType2, 0, 2);
        grid.add(availText, 0, 3);

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
    }

    /**
     * Check status of room
     * @param grid - GridPane to add lookup to
     * @param hotel - Hotel to reference lookup
     */
    private void addLookupRoom(GridPane grid, Hotel hotel) {
        Text findRooms = new Text("Lookup Room");
        findRooms.setFont(Font.font("Hack", FontWeight.SEMI_BOLD, 14));
        grid.add(findRooms, 0, 1, 2, 1);

        Label roomNumberLabel = new Label("Room Number");
        grid.add(roomNumberLabel, 0, 2);

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
    }

    /**
     * Adds button to retrieve all rooms in the hotel
     * @param border - BorderPane to attach to
     * @param hotel - Hotel to get rooms from
     */
    private void addFooter(BorderPane border, Hotel hotel){
        HBox hbBtn = new HBox(10);
        hbBtn.setPadding(new Insets(15, 12, 15, 12));

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(1);
            }
        });
        hbBtn.getChildren().addAll(exitBtn);
        hbBtn.setAlignment(Pos.CENTER_RIGHT);
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
