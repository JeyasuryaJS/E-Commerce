package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SellerDAO;
import dto.SellerDTO;
@WebServlet("/ser1")
public class SingUp extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		long contact = Long.parseLong(req.getParameter("contact"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		SellerDTO seller = new SellerDTO();
		
		seller.setId(id);
		seller.setName(name);
		seller.setContact(contact);
		seller.setEmail(email);
		seller.setPassword(password);
		
		
		SellerDAO dao = new SellerDAO();
		
		try {
			int res = dao.saveSeller(seller);
			if(res==1)
			{
				req.setAttribute("message", "Signup Success");
				RequestDispatcher disp = req.getRequestDispatcher("Index.jsp");
				disp.include(req, resp);
			}
			else
			{
				req.setAttribute("message", "Signup Failed");
				RequestDispatcher disp = req.getRequestDispatcher("Index.jsp");
				disp.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
