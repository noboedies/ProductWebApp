package com.tausif.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tausif.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class PrintProduct
 */
@WebServlet("/PrintProduct")
public class PrintProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PrintProduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> products= (List)request.getAttribute("products");

		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>ProductWebApp</h1>");
		out.print("<hr>");
		out.print("<section style='display:flex;flex-wrap:wrap;'>");
		for(Product p:products) {
			out.print("<div style='background-color:yellow; padding:15px; margin:10px; width:250px'>");
			out.print("<p>Name: "+p.getName()+" </p>");
			out.print("<p>Company Name: "+p.getcName()+" </p>");
			out.print("<p>Price: "+p.getPrice()+" </p>");
			out.print("<p>Description: "+p.getDescription()+" </p>");
			out.print("</div>");
		}
		out.print("</section>");

		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
