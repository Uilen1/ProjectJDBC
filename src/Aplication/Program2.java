package Aplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		/*System.out.println("===Test 1: department insert ===");
		Department department = new Department(null, "Limpeza");
		departmentDao.insert(department);
		System.out.println("Insert completed!" + department.getId());*/
		
		/*System.out.println("\n===Test 2: department update ===");
		Department department = new Department(5, "Limpeza");
		departmentDao.update(department);
		System.out.println("Update Complete!");*/
		
		System.out.println("\n===Test 3: department findById ===");
		System.out.print("Type a ID of the department: ");
		int id = sc.nextInt();
		Department department = departmentDao.findById(id);
		System.out.println(department);
		
		System.out.println("\n===Test 4: department findAll ===");
		List<Department> list = new ArrayList<Department>();
		list = departmentDao.findAll();
		for(Department obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("\n===Test 5: department deleteById ===");
		System.out.print("Enter with the Id to be deleted: ");
		id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete Completed!");
		
		sc.close();
	}

}
