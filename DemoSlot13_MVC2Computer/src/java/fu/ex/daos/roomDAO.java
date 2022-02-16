/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dtos.RoomDTO;
import fu.ex.ultis.DBContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class roomDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public roomDAO() {

    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public RoomDTO getRoomByID(String id) throws SQLException {
        RoomDTO rdto = null;
        try {
            String sql = "SELECT RoomName,Building,FloorNumber From RoomTBL WHERE roomid=?";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("roomName");
                String building = rs.getString("building");
                int floorNumber = rs.getInt("floorNumber");
                rdto = new RoomDTO(id, name, building, floorNumber);
            }
        } finally {
            closeConnection();
        }
        return rdto;
    }

    public ArrayList<RoomDTO> getAllRoom() throws SQLException {
        ArrayList<RoomDTO> list = null;
        try {
            String sql = "SELECT RoomID,RoomName,Building,FloorNumber FROm RoomTBL";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String id = rs.getString("roomID");
                String roomName = rs.getString("RoomName");
                String building = rs.getString("building");
                int floorNumber = rs.getInt("floorNumber");
                list.add(new RoomDTO(id, roomName, building, floorNumber));

            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
