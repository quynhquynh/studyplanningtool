package studyplanningtool;

public class Course {

	private int id;
	private String course;
	private String semester;
	private boolean status;
	
	//Constructor for Course
	public Course(int id, String course, String semester, boolean status) {
		this.id = id;
		this.course = course;
		this.semester = semester;
		this.status = status;
	}

	/**********
	 * getCourse
	 * Parameters: 
	 * Returns: course name as string
	 */
	public String getCourse() {
		return this.course;
	}
	
	/**********
	 * getSemester
	 * Parameters: -
	 * Returns: semester as string
	 */

	public String getSemester() {
		return this.semester;
	}

	/**********
	 * getStatus
	 * Parameters: -
	 * Returns: status as boolean
	 */
	public boolean getStatus() {
		return this.status;
	}
	
	/**********
	 * getStatusString
	 * Parameters: -
	 * Returns: status as string
	 */
	public String getStatusString() {
		return this.status ? "Completed" : "Uncompleted";
	}
	
	
	
	/**********
	 * getID
	 * Parameters: -
	 * Returns: ID as integer
	 */
	public int getID() {
		return this.id;
	}
}
