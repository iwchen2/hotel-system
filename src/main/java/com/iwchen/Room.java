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

  final RoomType roomType;
  double price;
  boolean isReserved;
  Date startDate;
  Date endDate;
  int roomNumber;
  int occupancy;
  final int maxOccupancy;

  public Room(RoomType roomType, int roomNumber){
    this.roomType = roomType;

    switch (roomType) {
      case PENTHOUSE_SUITE:
        this.price = 499.99;
        this.maxOccupancy = 8;
        break;

      case MASTER_SUITE:
        this.price = 399.99;
        this.maxOccupancy = 5;
        break;

      case MINI_SUITE:
        this.price = 299.99;
        this.maxOccupancy = 3;
        break;

      case DOUBLE_DOUBLE:
        this.price = 169.99;
        this.maxOccupancy = 4;
        break;

      case TWIN:
        this.price = 99.99;
        this.maxOccupancy = 2;
        break;

      case KING:
        this.price = 129.99;
        this.maxOccupancy = 2;
        break;

      case QUEEN:
        this.price = 109.99;
        this.maxOccupancy = 2;
        break;

      case DOUBLE:
        this.price = 79.99;
        this.maxOccupancy = 2;
        break;

      case SINGLE:
        this.price = 49.99;
        this.maxOccupancy = 1;
        break;

      default:
        this.price = 0.0;
        this.maxOccupancy = 0;
    }

    this.isReserved = false;
    this.startDate = null;
    this.endDate = null;
    this.roomNumber = roomNumber;
    this.occupancy = 0;
  }

  public RoomType getRoomType() {
    return this.roomType;
  }

  public String toString() {
    return String.format("%d %s Availability=%b", this.roomNumber, this.roomType, !this.isReserved);
  }
}
