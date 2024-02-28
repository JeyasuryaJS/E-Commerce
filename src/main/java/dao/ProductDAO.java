package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import dto.ProductDTO;
import dto.SellerDTO;

public class ProductDAO 
{
	public Connection getConnection() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	 		
	    return DriverManager.getConnection("jdbc:mysql://localhost:3306/product_details","root","root");			
	}
	
	public int saveProduct(ProductDTO product) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("insert into product values (?,?,?,?,?,?)");
		
		pst.setInt(1, product.getId());
		pst.setString(2, product.getName());
		pst.setString(3, product.getBrand());
		pst.setDouble(4, product.getPrice());
		pst.setDouble(5, product.getDiscount());
		
		Blob image = new SerialBlob(product.getImage());
		pst.setBlob(6, image);
		
		return pst.executeUpdate();
		
	}
	
	public int updateProduct(ProductDTO product) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("update product set name=? , brand=? ,price=? ,discount=? ,image=? where id=?");
		
		pst.setString(1, product.getName());
		pst.setString(2, product.getBrand());
		pst.setDouble(3, product.getPrice());
		pst.setDouble(4, product.getDiscount());
		Blob image = new SerialBlob(product.getImage());
		pst.setBlob(5,image);
		pst.setInt(6, product.getId() );
		
		return pst.executeUpdate();	
	}
	
	public int deleteProduct(int id) throws ClassNotFoundException, SQLException
	{

		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("delete from product where id=?");
		pst.setInt(1, id );
		
		int res = pst.executeUpdate();		
		return res;		
	}
	
	public ProductDTO findbyId(int id) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("select * from product where id=?");
		pst.setInt(1, id );
		
        ResultSet rs = pst.executeQuery();
  		
  		ProductDTO pro = new ProductDTO();
  		
  		rs.next();
  		pro.setId(rs.getInt(1));
  		pro.setName(rs.getString(2));
  		pro.setBrand(rs.getString(3));
  		pro.setPrice(rs.getDouble(4));
  		pro.setDiscount(rs.getDouble(5));
  		Blob imageBlob = rs.getBlob(6);
  		byte[] image = imageBlob.getBytes(1, (int) imageBlob.length());
  		pro.setImage(image);		
  		conn.close();	
		return pro;	
	}
	
	public List<ProductDTO> getAll() throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("select * from product");
		
		ResultSet rs = pst.executeQuery();
		
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		while(rs.next())
		{
			ProductDTO p = new ProductDTO();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
	  		p.setBrand(rs.getString(3));
	  		p.setPrice(rs.getDouble(4));
	  		p.setDiscount(rs.getDouble(5));
	  		Blob imageBlob = rs.getBlob(6);
	  		byte[] image = imageBlob.getBytes(1, (int) imageBlob.length());
	  		p.setImage(image);		
	  		
	  		products.add(p);
		}
		
		
		return products;
		
	}
	

}
