/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.RoomDao;
import db.assignment1.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maha
 */
@Repository
public class RoomDaoImp implements RoomDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createRoomRecord(Room room) {
        
        String sql = "INSERT INTO ROOM_DETAIL ( ROOM_NUMBER ,"
                + " ROOM_TYPE, ROOM_PRICE , ROOM_AVAILABLE ) "
                + "VALUES ( ? , ? , ? , 0) ";
        Object [] params=new Object[]{room.getRoomNumber(),room.getRoomType(),
            room.getPrice()};
        int [] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.DOUBLE};
          return jdbcTemplate.update(sql, params, types); }

    @Override
    public List<Room> getAllRoomAvailable() {
        List<Room> rooms = new ArrayList<>();
                String sql = "SELECT * FROM ROOM_DETAIL WHERE ROOM_AVAILABLE = 0";
                
                rooms=jdbcTemplate.query(sql,
                        new RowMapper<Room>() {
                        
                         @Override
           public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
               Room room=new Room( rs.getInt("ROOM_id"),
                       rs.getString("ROOM_NUMBER"),
                       rs.getString("ROOM_TYPE"),
                       rs.getDouble("ROOM_PRICE"));
               return room;
           }
       });
               return rooms;
                        }

    @Override
    public Room getRoomById(Integer roomId) {
         List<Room> roomList = new ArrayList<>();
       // Room room = null;
                String sql = "SELECT * FROM ROOM_DETAIL WHERE ROOM_id = ? ";
                
                roomList=jdbcTemplate.query(sql,new Object[]{roomId},
                        new RowMapper<Room>() {
                        
           @Override
           public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
               Room room=new Room(  rs.getInt("ROOM_id"),
                       rs.getString("ROOM_NUMBER"),
                       rs.getString("ROOM_TYPE"),
                       rs.getDouble("ROOM_PRICE"));
               return room;
           }
       });
               if(roomList.isEmpty())
                     return null;
                 else
                     return roomList.get(0);
    
        }

    @Override
    public int update(Room room) {
        String sql = "UPDATE  ROOM_DETAIL "
               + " SET ROOM_NUMBER = ? , "
                + " ROOM_TYPE = ? , "
                + " ROOM_PRICE = ? "
                + " WHERE ROOM_id = ? ";
        Object [] params=new Object[]{room.getRoomNumber(),room.getRoomType(),
            room.getPrice(),room.getId()};
        int [] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.DOUBLE,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }

    @Override
    
    public int payRoom(Integer roomId) {
        String sql = "UPDATE  ROOM_DETAIL "
               + " SET ROOM_AVAILABLE = 0 "
                + " WHERE ROOM_id = ? ";
        Object [] params=new Object[]{roomId};
        int [] types=new int[]{Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }

    @Override
    public int reserveRoomById(Integer roomId) {
        String sql = "UPDATE  ROOM_DETAIL "
               + " SET ROOM_AVAILABLE = 1 "
                + " WHERE ROOM_id = ? ";
        Object [] params=new Object[]{roomId};
        int [] types=new int[]{Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    }
}
