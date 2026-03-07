package com.tausif.servlets;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tausif.HbUtility;
import com.tausif.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddProduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String cName = request.getParameter("cName");
		int price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		
		Session ses = HbUtility.sessionFactory.openSession();
		Transaction transaction = ses.beginTransaction();
		
		Product product = ses.get(Product.class, name);
		
		if(product == null) {
			Product p = new Product();
			p.setName(name);
			p.setcName(cName);
			p.setPrice(price);
		    p.setDescription(description);
		    ses.persist(p);
		    transaction.commit();
		    ses.close();
		    response.sendRedirect("success.html");
		    
			
		} else {
			response.sendRedirect("Exist.html");
		}
		
		
	}

}
