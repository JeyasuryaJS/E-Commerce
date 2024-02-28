package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ProductDAO;
import dto.ProductDTO;
@WebServlet("/loggg")
@MultipartConfig(maxFileSize = 1024*1024*10)
public class SaveProduct extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String brand = req.getParameter("brand");
		double price = Double.parseDouble(req.getParameter("price"));
		double discount = Double.parseDouble(req.getParameter("discount"));
		Part image = req.getPart("image");
		
		ProductDTO p = new ProductDTO();
		
		p.setId(id);
		p.setName(name);
		p.setBrand(brand);
		p.setPrice(price);
		p.setDiscount(discount);
        p.setImage(image.getInputStream().readAllBytes()); 
		
		ProductDAO dao = new ProductDAO();
		
		try {
			int res = dao.saveProduct(p);
			if(res==1)
			{
				req.setAttribute("products", dao.getAll());
				req.setAttribute("message1", "product added succes");
				RequestDispatcher disp = req.getRequestDispatcher("Home.jsp");
				disp.include(req, resp);
			}
			else
			{
				req.setAttribute("message1", "product save failed");
				RequestDispatcher disp = req.getRequestDispatcher("AddProduct.jsp");
				disp.include(req, resp);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
