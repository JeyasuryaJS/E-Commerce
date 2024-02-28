package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
@WebServlet("/delete")
public class DeleteProduct extends HttpServlet 
{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	 int id = Integer.parseInt(req.getParameter("id"));
    	 
    	 ProductDAO dao = new ProductDAO();
    	 
    	 try {
    		   HttpSession session = req.getSession();
    		   String email = (String) session.getAttribute("email");
    		   
    		   if(email != null)
    		   {	  
			       int res = dao.deleteProduct(id);
	
				req.setAttribute("products", dao.getAll());
				req.setAttribute("message1", "Deleted Successfully");
				RequestDispatcher disp = req.getRequestDispatcher("Home.jsp");
				disp.include(req, resp);
			   }
			else
			  {
				req.setAttribute("message1", "Login Required");
				RequestDispatcher disp = req.getRequestDispatcher("Login.jsp");
			    disp.include(req, resp);
			  }
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
