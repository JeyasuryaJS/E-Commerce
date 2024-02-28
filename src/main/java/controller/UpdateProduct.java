package controller;

import java.io.IOException;
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
@WebServlet("/update")
@MultipartConfig(maxFileSize = 1024*1024*5)
public class UpdateProduct extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("pid"));
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
        
   		if(image.getSize()>1) {
   			p.setImage(image.getInputStream().readAllBytes());
   		}
   		else {
   		ProductDAO dao = new ProductDAO();
   		
   		try {
			ProductDTO dto = dao.findbyId(id);
			p.setImage(dto.getImage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
   		}
   		ProductDAO pdao = new ProductDAO();
   		try {
			int res = pdao.updateProduct(p);
			
			if(res==1) {
				req.setAttribute("products", pdao.getAll());
				req.setAttribute("message1", "updated success");
				RequestDispatcher dispatcher = req.getRequestDispatcher("Home.jsp");
				dispatcher.include(req, resp);
			}
			else {
				req.setAttribute("message1", "update fails");
				RequestDispatcher dispatcher = req.getRequestDispatcher("Edit.jsp");
				dispatcher.include(req, resp);
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
