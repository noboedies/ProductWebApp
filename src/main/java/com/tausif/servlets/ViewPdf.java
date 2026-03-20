package com.tausif.servlets;

import java.io.IOException;

import org.hibernate.Session;

import com.tausif.HbUtility;
import com.tausif.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ViewPdf
 */
@WebServlet("/ViewPdf")
public class ViewPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ViewPdf() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		System.out.println("Name received = " + name);
		Session session = HbUtility.sessionFactory.openSession();
		Product p = session.get(Product.class, name);
		byte[] content = p.getContent();
		response.getOutputStream().write(content);
	}

}
