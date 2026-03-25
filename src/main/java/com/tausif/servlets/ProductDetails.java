package com.tausif.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tausif.HbUtility;
import com.tausif.entity.Product;
import com.tausif.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductDetails() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		if(httpSession == null) {
			response.sendRedirect("session_error.html");
			
		}else {
			User user = (User)httpSession.getAttribute("User");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<body>");
			out.print("<h1>Product App</h1>");
			out.print("<hr>");
			out.print("Welcome: <b> "+user.getName()+"</b>");
			out.print("&nbsp;&nbsp;<a href='UserHome'>Home</a>");
			out.print("&nbsp;&nbsp;<a href='AllProduct'>All Product</a>");
			out.print("&nbsp;&nbsp;<a href='Logout'></a>");
			out.print("<hr>");
			String name = request.getParameter("name");
			Session session = HbUtility.sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.get(Product.class, name);
			
			if(product == null) {
				out.print("<p>Product Not Available!>");
			}else {
				out.print("<div style='background-color:yellow; padding:15px; margin:10px; width:250px'>");
				if(product.getImage() != null) {
					out.print("<img src='GetImage?name="+product.getName()+"' >");
					
				}else {
					out.print("<img src='product.png'>");
					
				}
				out.print("<p>Name: "+product.getName()+" </p>");
				out.print("<p>Company Name: "+product.getcName()+" </p>");
				out.print("<p>Price: "+product.getPrice()+" </p>");
				out.print("<p>Description: "+product.getDescription()+" </p>");
				if(product.getContent() != null) {
					out.print("<a href= 'DownloadPdf?name="+product.getName()+"'>Download Book</a>");
					out.print("<a href= 'ViewPdf?name="+product.getName()+"' target= '_blank'>View Book</a>");
				}
				out.print("<hr>");
				out.print("<a href='DeleteProduct?name="+product.getName()+"' >Delete Product</a>");
				out.print("</div>");
			}
			out.print("</body>");
			out.print("</html>");
			out.close();
			
			
		}
	}

}
