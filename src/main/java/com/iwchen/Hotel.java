package com.iwchen;

import java.util.Map;
import java.util.HashMap;

public class Hotel {

  private Map<Integer, Room> roomListing;

  public Hotel() {
    roomListing = new HashMap<>();
  }

  public Map<Integer, Room> getRoomListing() {
    return roomListing;
  }

  public void setRoomListing(Map<Integer, Room> rooms) {
    this.roomListing = rooms;
  }

}
