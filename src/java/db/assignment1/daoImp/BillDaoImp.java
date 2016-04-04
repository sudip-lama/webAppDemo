/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.BillDao;
import db.assignment1.entity.Bill;
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
public class BillDaoImp implements BillDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createBillRecord(Bill bill) {
        
        String sql = "INSERT INTO BILL_DETAIL ( BILL_NUMBER ,"
                + " BILL_DATE, TOTAL , PREPARED_BY ) "
                + "VALUES ( ? , ? , ? , ?) ";
        Object [] params=new Object[]{bill.getBillNumber(), bill.getBillDate(), bill.getTotal(), bill.getPreparedBy()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.DOUBLE, Types.VARCHAR};
          return jdbcTemplate.update(sql, params, types); }

    @Override
    public List<Bill> getAllBillAvailable() {
        List<Bill> bills = new ArrayList<>();
                String sql = "SELECT * FROM BILL_DETAIL";
                
                bills=jdbcTemplate.query(sql,
                        new RowMapper<Bill>() {
                        
                         @Override
           public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
               Bill bill=new Bill( rs.getInt("BILL_id"),
                       rs.getString("BILL_NUMBER"),
                       rs.getDate("BILL_DATE"),
                       rs.getDouble("TOTAL"),
                       rs.getString("PREPARED_BY"));
               return bill;
           }
       });
               return bills;
    }
    

    @Override
    public Bill getBillById(Integer billId) {
        Bill bill = null;
                String sql = "SELECT * FROM BILL_DETAIL WHERE BILL_id = ? ";
                
                bill=jdbcTemplate.queryForObject(sql,new Object[]{billId},
                        new RowMapper<Bill>() {
                        
           @Override
           public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
               Bill bill=new Bill(  rs.getInt("BILL_id"),
                       rs.getString("BILL_NUMBER"),
                       rs.getDate("BILL_DATE"),
                       rs.getDouble("TOTAL"),
                       rs.getString("PREPARED_BY"));
               return bill;
           }
       });
               return bill; 
    
                        }

    @Override
    public int update(Bill bill) {
        String sql = "UPDATE  BILL_DETAIL "
                + " SET BILL_NUMBER = ? , "
                + " BILL_DATE = ? , "
                + " TOTAL = ? , "
                + "PREPARED_BY"
                + " WHERE ROOM_id = ? ";
        Object [] params=new Object[]{bill.getBillNumber(), bill.getBillDate(), bill.getTotal(), bill.getPreparedBy(), bill.getId()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.DOUBLE,Types.VARCHAR,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }
}
    
