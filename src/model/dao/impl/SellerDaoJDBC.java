package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	} 
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, Department.Name as DepName " +
					"FROM seller INNER JOIN department " +
					"ON seller.DepartmentId = department.Id " +
					"WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Department dep = instatiateDepartment(rs);
				Seller objSeller = instatiateSeller(rs, dep);
				
				return objSeller;
				
			}
			
			return null;
			
		} 
		catch (SQLException e) {
			throw new DbException("Error " + e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
		
	}
	
	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, Department.Name as DepName " +
					"FROM seller INNER JOIN department " +
					"ON seller.DepartmentId = department.Id " +
					"WHERE department.Id = ? " +
					"ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					
					dep = instatiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
					
				}
				
				Seller objSeller = instatiateSeller(rs, dep);
				
				list.add(objSeller);
				
			}
			
			return list;
			
		} 
		catch (SQLException e) {
			throw new DbException("Error " + e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
	}
	

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller objSeller = new Seller();
		objSeller.setId(rs.getInt("Id"));
		objSeller.setName(rs.getString("Name"));
		objSeller.setEmail(rs.getString("Email"));
		objSeller.setBaseSalary(rs.getDouble("BaseSalary"));
		objSeller.setBirthDate(rs.getDate("BirthDate"));
		objSeller.setDepartment(dep);
		return objSeller;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("DepName"));
			return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
