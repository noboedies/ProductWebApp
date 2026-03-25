package com.tausif.servlets;

import java.io.IOException;

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
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig
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
		
		HttpSession httpSession = request.getSession(false);
		if(httpSession == null) {
			response.sendRedirect("session_error.html");
		}else {
			User user =(User)httpSession.getAttribute("User");
			Part p1 = request.getPart("cImage");
			Part p2 = request.getPart("ctn");
			
			byte[] image = p1.getInputStream().readAllBytes();
			byte[] content = p2.getInputStream().readAllBytes();
			
			if(image.length > 2*1000*1000 && content.length > 10*1000*1000) {
				response.sendRedirect("fileSizeExceed.html");
			} else if(image.length > 2*1000*1000) {
				response.sendRedirect("imageSizeExceed.html");
			}else if(content.length > 10*1000*1000) {
				response.sendRedirect("contentSizeExceed.html");
			} else {
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
				    p.setImage(image);
				    p.setContent(content);
				    p.setUser(user);
				    ses.persist(p);
				    transaction.commit();
				    ses.close();
				    httpSession.setAttribute("msg", "Product Added successfully!");
				} else {
					httpSession.setAttribute("msg", "Product Already Exist");
				}
				response.sendRedirect("UserHome");
			}
		}
		
			
	}

}
