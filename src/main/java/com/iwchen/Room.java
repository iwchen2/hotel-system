package com.iwchen;

import java.util.*;

public class Room {

public enum RoomType{

  PENTHOUSE_SUITE,
  MASTER_SUITE,
  MINI_SUITE,
  DOUBLE_DOUBLE,
  TWIN,
  KING,
  QUEEN,
  DOUBLE,
  SINGLE
}

  RoomType roomType;
  double price;
  boolean isReserved;
  Date startDate;
  Date endDate;
  int roomNumber;
  int occupancy;

  public Room(RoomType roomType){
    this.roomType = roomType;

    switch (roomType) {
      case PENTHOUSE_SUITE:
        this.price = 499.99;
        break;

      case MASTER_SUITE:
        this.price = 399.99;
        break;

      case MINI_SUITE:
        this.price = 299.99;
        break;

      case DOUBLE_DOUBLE:
        this.price = 169.99;
        break;

      case TWIN:
        this.price = 99.99;
        break;

      case KING:
        this.price = 129.99;
        break;

      case QUEEN:
        this.price = 109.99;
        break;

      case DOUBLE:
        this.price = 79.99;
        break;

      case SINGLE:
        this.price = 49.99;
        break;
    }

    this.isReserved = false;
    this.startDate = null;
    this.endDate = null;
    /**
    *TODO
    *roomNumber
    *occupancy
    */

  }
}
