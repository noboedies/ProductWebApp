package com.tausif.servlets;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.tausif.HbUtility;
import com.tausif.entity.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class SearchProduct
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchProduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		Session ses = HbUtility.sessionFactory.openSession();
		Product p = ses.get(Product.class, name);
		List<Product> products = ses.createQuery("select p from Product p where p.name like :name", Product.class)
				.setParameter("name","%"+name+"%").list();
		
		if(products.isEmpty()) {
			response.sendRedirect("notFound.html");
		}else {
			request.setAttribute("products", products);
			RequestDispatcher rd = request.getRequestDispatcher("PrintProduct");
			rd.forward(request, response);
		}
		
	}

}
