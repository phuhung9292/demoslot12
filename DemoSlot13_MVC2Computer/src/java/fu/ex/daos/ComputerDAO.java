/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.dtos.ComputerDTO;
import fu.ex.dtos.RoomDTO;
import fu.ex.ultis.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ComputerDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ComputerDAO() {

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

    public ArrayList<ComputerDTO> getAllComputer() throws SQLException {
        ArrayList<ComputerDTO> list = null;
        try {
            String sql = "SELECT ComputerID,CPU,HardDisk,RAM,VGA,Monitor,RoomID FROM ComputerTBL";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String id = rs.getString("ComputerID");
                String cpu = rs.getString("CPU");
                String hardDisk = rs.getString("HardDisk");
                String ram = rs.getString("RAM");
                String vga = rs.getString("VGA");
                String monitor = rs.getString("Monitor");
                String roomId = rs.getString("RoomID");
                roomDAO rdao = new roomDAO();
                RoomDTO rdto = rdao.getRoomByID(roomId);
                list.add(new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, rdto));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean delete(String id) throws SQLException {
        boolean check = false;
        try {
            String sql = "DELETE FROM computerTBL WHERE ComputerID=?";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insert(ComputerDTO computer) throws SQLException {
        boolean check = false;
        try {
            String sql = "INSERT INTO ComputerTBL(ComputerID,CPU,HardDisk,RAM,VGA,Monitor,RoomID) VALUES(?,?,?,?,?,?,?)";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, computer.getId());
            preStm.setString(2, computer.getCpu());
            preStm.setString(3, computer.getHardDisk());
            preStm.setString(4, computer.getRam());
            preStm.setString(5, computer.getVga());
            preStm.setString(6, computer.getMonitor());
            preStm.setString(7, computer.getRoom().getId());
            check = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return check;
    }

    public ComputerDTO getComputerByID(String id) throws SQLException {
        ComputerDTO cdto = null;
        try {
            String sql = "ComputerID,CPU,HardDisk,RAM,VGA,Monitor,RoomID FROM ComputerTBL WHERE ComputerID=? ";
            conn = DBContext.connectDatabase();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String cid = rs.getString("ComputerID");
                String cpu = rs.getString("CPU");
                String hardDisk = rs.getString("HardDisk");
                String ram = rs.getString("RAM");
                String vga = rs.getString("VGA");
                String monitor = rs.getString("Monitor");
                String roomId = rs.getString("RoomID");
                roomDAO rdao = new roomDAO();
                RoomDTO rdto = rdao.getRoomByID(roomId);
                cdto = new ComputerDTO(cid, cpu, hardDisk, ram, vga, monitor, rdto);
            }
        } finally {
            closeConnection();
        }
        return cdto;
    }
}
