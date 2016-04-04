/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.entity.Room;
import db.assignment1.service.DoctorService;
import db.assignment1.service.RoomService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@RequestScoped
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    private Room  room;
    private String message;
    private boolean edit=false;
    private DataModel<Room> roomList;

    
    public String  saveOrEdit()
    {

        if(!edit)
        {
           if(roomService.createRoomRecord(room))
           {
               message=room.getRoomNumber()+"  Record Created";
               room=null;
           }
           else
           {
               message="Error creating Room Record";
           }
           
        }
        else
        {
            if(roomService.update(room))
            {
                message=room.getRoomNumber()+" Room Record Updated";
               room=null;
            }
            else
            {
            
               message="Error updating Room Record";
            }
            
            
        }
        edit=false;
        roomList=null;
        return null;
    }
    
     public String getRoomForEdit() {
        try {
            room= (Room) roomList.getRowData();
            roomList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Room couldn't be retrieved.";
        }
        return null;
        }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getRoom() {
        if(room==null)
        {
            room=new Room();
        }
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    
    
     

    public DataModel<Room> getRoomList() {
        if(roomList==null)
        {
            roomList=new ListDataModel<>(roomService.getAllRoomAvailable());
        }
        return roomList;
        //return roomList;
    }

    public void setRoomList(DataModel<Room> roomList) {
        this.roomList = roomList;
    }
    
    
    
    
    
}
