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
import dao.SellerDAO;
import dto.SellerDTO;
@WebServlet("/log")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pass  = req.getParameter("password");
		
		SellerDAO sdao = new SellerDAO();	
		
		try {
			SellerDTO seller = sdao.findByEmail(email);
			ProductDAO pdao = new ProductDAO();
			
			if(seller!=null)
				{  
					if(seller.getPassword().equals(pass)) 
						{ 
						    HttpSession session = req.getSession();	
						    session.setAttribute("email", email);
						    
							req.setAttribute("products",pdao.getAll() );
							req.setAttribute("message1" , "Login success");
							RequestDispatcher disp = req.getRequestDispatcher("Home.jsp");
							disp.include(req, resp);
						}
					else
						{
							req.setAttribute("message1", "Wrong Password");
							RequestDispatcher disp = req.getRequestDispatcher("Login.jsp");
							disp.include(req, resp);
						}
				}
			else
				{
					req.setAttribute("message1", "Wrong email");
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
