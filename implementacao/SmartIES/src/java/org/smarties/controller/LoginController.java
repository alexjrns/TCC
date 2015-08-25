/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smarties.controller;

import org.smarties.model.util.Format;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.Conexao;
import model.dao.UsuarioDAO;
import model.entidade.Usuario;
import org.smarties.model.util.Utilitario;

/**
 *
 * @author hadoop
 */
public class LoginController extends HttpServlet {

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
        String acao = Format.getString(request.getParameter("act"));
        if(acao.equals("logout")){
            HttpSession sessao = request.getSession(false);

            if (sessao != null) {
                sessao.invalidate();
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuarioLogado") != null) {
                request.getRequestDispatcher("view/pages/index.jsp").forward(request, response);            
            } else{
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
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
        String login = Format.getString(request.getParameter("login"));
        String senha = Utilitario.MD5(Format.getString(request.getParameter("senha")));
        Boolean lembrarUsuario = Format.getBoolean(request.getParameter("lembrar"));

        HttpSession sessao = request.getSession();

        EntityManagerFactory factory = (EntityManagerFactory) sessao.getAttribute("manager");
        if(factory == null){
            factory = Conexao.createEntityManagerFactory();
            sessao.setAttribute("manager", factory);
        }

        UsuarioDAO usuDAO = new UsuarioDAO((EntityManagerFactory) sessao.getAttribute("manager"));
        Usuario usr = usuDAO.logar(login, senha);
        System.out.println("usuario: " + login);
        System.out.println("senha: " + senha);
        if (usr != null) {
            if(!lembrarUsuario)
                sessao.setMaxInactiveInterval(30 * 60);
            usr.setUltimologin(Calendar.getInstance());
            try {
                usuDAO.salvarLogin(usr);
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            sessao.setAttribute("usuarioLogado", usr);
            //int qtd = (int) new NotificacaoDAO((EntityManagerFactory) sessao.getAttribute("manager")).getQuantidadeNaoLida((Usuario) sessao.getAttribute("usuarioLogado"));
            //sessao.setAttribute("quantidadeNotificacoes", qtd);

            String pagDestino = (String) sessao.getAttribute("destino");
            if (pagDestino != null) {
                request.getRequestDispatcher(pagDestino).forward(request, response);
            } else {
                request.getRequestDispatcher("view/pages/index.jsp").forward(request, response);   
            }
        } else {
           response.setContentType("text/html;charset=UTF-8");
            String info = ("<p><div class=\"msg_erro alert alert-danger fade in\" role=\"alert\">"
                    + "<button class=\"close\" data-dismiss=\"alert\" type=\"button\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Fechar</span></button>"
                    + "<h4>Usu&aacute;rio e/ou senha incorretos!</h4>"
                    + "<p>Verifique o usu&aacute;rio, a senha e digite os dados novamente.</p>"
                    + "</div></p>");
            request.setAttribute("inf", info);
            request.setAttribute("login", login);
            //request.getRequestDispatcher("login.jsp").forward(request, response);
            request.getRequestDispatcher("view/pages/index.jsp").forward(request, response);   
        }
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
