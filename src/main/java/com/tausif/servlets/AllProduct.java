package com.tausif.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class AllProduct
 */
@WebServlet("/AllProduct")
public class AllProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AllProduct() {
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
			out.print("<h1>ProductWebApp</h1>");
			out.print("<hr>");
			out.print("Welcome: <b> "+user.getName()+"</b>");
			out.print("&nbsp;&nbsp;<a href='UserHome'>Home</a>");
			out.print("&nbsp;&nbsp;<a href='AllProduct'>All Product</a>");
			out.print("&nbsp;&nbsp;<a href='Logout'></a>");
			out.print("<hr>");
			
			String msg = (String)httpSession.getAttribute("msg");
			if(msg != null) {
				out.print("<p>"+msg+"</p>");
				httpSession.setAttribute("msg", null);
			}
			
			Session session = HbUtility.sessionFactory.openSession();
			
			List<Product> products = session.createQuery("select p from Product p where p.user = :user",Product.class)
					.setParameter("user", user).list();
			if(products.isEmpty()) {
				out.print("<p>No Book Found!</p>");
			}else {
				out.print("<section style='display:flex;flex-wrap:wrap;'>");
				for(Product p : products) {
					out.print("<div style='background-color:yellow; padding:15px; margin:10px; width:250px'>");
					if(p.getImage() != null) {
						out.print("<img src='GetImage?name="+p.getName()+"' height='100px' />");
					}else {
						out.print("<img src='product.png' height='100px' />");
						
					}
					out.print("<p>Name: "+p.getName()+"</p>");
					out.print("<p>Price: "+p.getPrice()+"</p>");
					out.print("<form action='ProductDetails' method='post'>");
					out.print("<input type='hidden' name='name' value='"+p.getName()+"'>");
					out.print("<button>More Details</button>");
					out.print("</form>");
					out.print("</div>");
				}
				out.print("</section>");
				
			}
			out.print("");
			out.print("</body>");
			out.print("</html>");
			out.close();
		}
	}

}
