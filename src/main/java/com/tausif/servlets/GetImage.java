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
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetImage() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		Session session = HbUtility.sessionFactory.openSession();
		Product p = session.get(Product.class, name);
		byte[] image = p.getImage();
		response.getOutputStream().write(image);
	}

}
