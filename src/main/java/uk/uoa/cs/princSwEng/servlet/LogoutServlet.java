package uk.uoa.cs.princSwEng.servlet;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import uk.uoa.cs.princSwEng.resource.Message;
import uk.uoa.cs.princSwEng.resource.Global;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.net.URISyntaxException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.CreateResearcherDatabase;
import uk.uoa.cs.princSwEng.database.SearchResearcherDatabase;


public final class LogoutServlet extends AbstractDatabaseServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        try {
            HttpSession session = req.getSession(true);
            session.invalidate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(true);
            session.invalidate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
    }

}