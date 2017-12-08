package studyplanningtool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries {

	//This Class manages database operations associated to the Course Class
		private static final String URL = "jdbc:mysql://eu-cdbr-azure-west-b.cloudapp.net:3306/le_quynh";
		private static final String USERNAME = "bf402aae0ad158";
		private static final String PASSWORD = "68f9b788";

		//Private variables to store connection and prepared statements
		private Connection connection = null;
		private PreparedStatement selectAllCourses = null;
		private PreparedStatement searchCourses = null;
		private PreparedStatement updateCourseStatus = null;
		private PreparedStatement addCourse = null;
		private PreparedStatement updateCourse = null;
		

		
		//Constructor for CourseQueries
		public CourseQueries() {
			try
			{
	
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Starts a connection to the database

				
				selectAllCourses = connection.prepareStatement("SELECT * FROM courses ORDER BY semester"); // Prepare the select query that gets all courses from the database

				searchCourses = connection.prepareStatement("SELECT * FROM courses WHERE course LIKE ? OR semester LIKE ? OR status LIKE ? ORDER BY semester");// Prepare the select query that gets a particular course with the matched search string
				
				updateCourseStatus = connection.prepareStatement("UPDATE courses SET status = ? WHERE id = ?");// Prepare the select query that updates course's status

				addCourse = connection.prepareStatement("INSERT INTO courses VALUES (?,?,?,?)");// Prepare the select query that allow to add new course
				
				updateCourse = connection.prepareStatement("UPDATE courses SET id=? AND course = ? AND semester = ? WHERE id=? ");// Prepare the select query to update a course's details

			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
				System.exit(1);
			}
		}
		
		/*
		 * This method will execute the select query that gets all courses from the database. 
		 * It returns an ArrayList containing Course objects initialized with Course data from each row in the courses table
		 */
		public  ArrayList<Course> getCourses(String criteria)
		{
			ArrayList<Course> results = null;
			ResultSet resultSet = null;
			
			try
			{
				if (criteria.length() == 0) {
					// execute the select all course query, resultSet contains the rows returned by the query		
					 
					resultSet = selectAllCourses.executeQuery(); 
				} else {
					// Setting the values as criteria for the question marks '?' in the prepared statement 
					searchCourses.setString(1, "%" + criteria + "%");
					searchCourses.setString(2, "%" + criteria + "%");
					searchCourses.setString(3, "%" + criteria + "%");
					
				
					resultSet = searchCourses.executeQuery();// execute the search course query
				}
				results = new ArrayList<Course>();
			
				while(resultSet.next()) // for each row returned by the select query...
				{
					// Initialize a new Course object with the row's data. Add the Course object to the results ArrayList
					
					results.add(new Course(
						resultSet.getInt("id"),// get the value associated to the id column
						resultSet.getString("course"), // get the value associated to the course column
						resultSet.getString("semester"), // get the value associated to the semester column
						resultSet.getBoolean("status"))); // get the value associated to the status column
				}
			} // end try
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			finally
			{
				try
				{
					resultSet.close();
				}
				catch (SQLException sqlException)
				{
					sqlException.printStackTrace();
				}
			} // end finally
			
			return results;
		} // end method getCourses
	
		//Method to update course's status
		protected void updateCourseStatus(int id, boolean status) {
			try {
				// Setting the values for the question marks '?' in the prepared statement
				updateCourseStatus.setBoolean(1, status);
				updateCourseStatus.setInt(2, id);
				
				updateCourseStatus.executeUpdate();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		
		// method to add new course
		public void addCourse (int id, String course, Object semester, boolean status) 
		{
			try
			{
				// Setting the values for the question marks '?' in the prepared statement
				addCourse.setInt(1, id);
				addCourse.setString(2, course);
				addCourse.setString(3, (String) semester);
				addCourse.setBoolean(4, status);
				
				//execute the add course query
				addCourse.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}	
		}
		
		
		//method to update a selected course
		public void updateCourse (int id, String course, Object semester, String selectedRow) 
		{
			try
			{
				// Setting the values for the question marks '?' in the prepared statement
				addCourse.setInt(1, id);
				addCourse.setString(2, course);
				addCourse.setString(3, (String) semester);
				addCourse.setString(4, selectedRow);
				
				
				
				updateCourse.executeUpdate(); 
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}	
		}

		
}
