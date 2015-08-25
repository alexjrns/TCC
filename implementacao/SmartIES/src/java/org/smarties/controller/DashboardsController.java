/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smarties.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.smarties.model.util.Format;

/**
 *
 * @author hadoop
 */
public class DashboardsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String acao = Format.getString(request.getParameter("ds"));
        if(acao.equals("mapabrasil")){
            request.getRequestDispatcher("view/pages/mapabrasil.jsp").forward(request, response);
        }else if(acao.equals("setoresebarras")){
            request.getRequestDispatcher("view/pages/setoresebarras.jsp").forward(request, response);
        }else if(acao.equals("qualis")){
            request.getRequestDispatcher("view/pages/qualis.jsp").forward(request, response);
        }else if(acao.equals("quantidade")){
            request.getRequestDispatcher("view/pages/quantidade.jsp").forward(request, response);
        }else if(acao.equals("divisao")){
            request.getRequestDispatcher("view/pages/divisao.jsp").forward(request, response);
        }else if(acao.equals("quantidadeaulas")){
            request.getRequestDispatcher("view/pages/quantidade_aula.jsp").forward(request, response);
        }
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
