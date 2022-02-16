/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlet;

import fu.ex.daos.ComputerDAO;
import fu.ex.dtos.ComputerDTO;
import fu.ex.dtos.ComputerErrorObject;
import fu.ex.dtos.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {

    private static final String SUCCESS = "LoadListComputerServlet";
    private static final String ERROR = "error,jsp";
    private static final String INVALID = "CreateFormServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String id = request.getParameter("txtID");
            String ram = request.getParameter("txtRAM");
            String cpu = request.getParameter("txtCPU");
            String vga = request.getParameter("txtVGA");
            String hardDisk = request.getParameter("txtHardDisk");
            String monitor = request.getParameter("txtMonitor");
            String room = request.getParameter("cboRoom");
            boolean valid = true;
            ComputerErrorObject ceo = new ComputerErrorObject();
            if (id.trim().isEmpty()) {
                ceo.setIdError("ID is empty");
                valid = false;
            }
            if (!id.trim().isEmpty() && !id.matches("[0-9]{2}")) {
                ceo.setIdError("ID must be numerical,2 digit");
                valid = false;
            }
            if (ram.trim().isEmpty()) {
                ceo.setIdError("RAM is empty");
                valid = false;
            }
            if (cpu.trim().isEmpty()) {
                ceo.setIdError("cpu is empty");
                valid = false;
            }
            if (!cpu.trim().isEmpty() && cpu.length() < 6) {
                ceo.setIdError("cpu infomation must be than 6 character");
                valid = false;
            }
            if (vga.trim().isEmpty()) {
                ceo.setIdError("VGA is empty");
                valid = false;
            }
            if (hardDisk.trim().isEmpty()) {
                ceo.setIdError("HardDisk is empty");
                valid = false;
            }
            if (monitor.trim().isEmpty()) {
                ceo.setIdError("Monitor is empty");
                valid = false;
            }
            ComputerDAO cdao = new ComputerDAO();
            if (cdao.getComputerByID(id) != null) {
                ceo.setIdError("ID is existed");
                valid = false;
            }
            RoomDTO rdto = new RoomDTO(room.split("-")[0].trim(), room.split("-")[1].trim(), "", 0);
            ComputerDTO cdto = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, rdto);
            if (valid) {
                if (cdao.insert(cdto)) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("errMessage", "insert fail");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", ceo);
                request.setAttribute("cdto", cdto);
            }
        } catch (Exception e) {
            log("ERROR at createServlet: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
