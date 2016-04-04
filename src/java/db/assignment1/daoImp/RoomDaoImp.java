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
        Room room = null;
                String sql = "SELECT * FROM ROOM_DETAIL WHERE ROOM_id = ? "
                        + " AND ROOM_AVAILABLE = 0";
                
                room=jdbcTemplate.queryForObject(sql,new Object[]{roomId},
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
               return room; 
    
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
}
