package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.SellerDTO;

public class SellerDAO 
{
     public int saveSeller(SellerDTO seller) throws ClassNotFoundException, SQLException
     {
    	 
    	 Class.forName("com.mysql.cj.jdbc.Driver");
 		
 		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seller_details","root","root");
 		
 		PreparedStatement pst = con.prepareStatement("insert into seller values (?,?,?,?,?) ");
 		pst.setInt(1, seller.getId());
 		pst.setString(2, seller.getName());
 		pst.setLong(3, seller.getContact());
 		pst.setString(4, seller.getEmail());
 		pst.setString(5, seller.getPassword());
 		
 		int res = pst.executeUpdate();
 		con.close();
 		
 		return res;
     }
     
     
     public SellerDTO  findByEmail(String email) throws ClassNotFoundException, SQLException
     {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
  		
  		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seller_details","root","root");
  		
  		PreparedStatement pst = con.prepareStatement("select * from seller where email = ? ");
  		
  		pst.setString(1,email);
  		
  		ResultSet rs = pst.executeQuery();		
  		
  		if(rs.next())
  		{
  		SellerDTO seller = new SellerDTO();
  		seller.setId(rs.getInt(1));
  		seller.setName(rs.getString(2));
  		seller.setContact(rs.getLong(3));
  		seller.setEmail(rs.getString(4));
  		seller.setPassword(rs.getString(5));
  		
  		con.close();

    	 
		return seller;
  		}
  		return null;
     }

}
