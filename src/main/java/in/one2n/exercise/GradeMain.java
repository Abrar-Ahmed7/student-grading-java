package in.one2n.exercise;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GradeMain {
 public static void main(String[] args) {
	 Grader grader = new Grader();
//	 System.out.println("Hiii1");
	 String filepath = Paths.get("src", "test", "resources", "grades.csv").toString();

     List<Student> students = grader.parseCSV(filepath);
//     System.out.println(students);
     System.out.println("*****List Of All Students\n");
     for (Student student : students) {
    	 System.out.println(student.getFirstname()+" "+student.getLastname()+" "+student.getUniversity()+" "+student.getTest1Score()+" "+student.getTest2Score()+" "+student.getTest3Score()+ " "+student.getTest4Score());
//    	 System.out.println(student.getGrade());
//    	 System.out.println(student.getUniversity());
		
	}
//	 System.out.println("Hiii22");
	 System.out.println("\n\n\n*****Final Score and Grade*****\n");
	 List<Student> gradedStudents = grader.calculateGrade(students);
	 for (Student student : gradedStudents) {
		 System.out.println(student.getFirstname()
				 			+" "+student.getLastname()
				 			+" "+student.getUniversity()
				 			+" "+student.getFinalScore()
				 			+" "+student.getGrade());
		
	}
	 
	System.out.println("\n\n\n*****Final Scores and Grade alone as arrays*****");
	List<Double> finalScores = new ArrayList<Double>();
	List<Grade> grades = new ArrayList<Grade>();
	
	
		for (Student student : students) {
//			finalScore.add((Double.parseDouble(student.getFinalScore().toString()));
			finalScores.add(student.getFinalScore());
			grades.add(student.getGrade());	
		}		
		System.out.println(grades);
		System.out.println(finalScores);
		
	
	System.out.println("\n\n\n*****Overall Topper*****");
	Student overAllTopper = grader.findOverallTopper(gradedStudents);
	System.out.println(overAllTopper.getFirstname()
						+" "+overAllTopper.getLastname()
						+" "+overAllTopper.getUniversity());
	
	System.out.println("\n\n\n*****Topper Per University*****");
	Map<String, Student> uniToppers = grader.findTopperPerUniversity(gradedStudents);
	for(Map.Entry<String, Student> set :uniToppers.entrySet()) {
		System.out.println(set.getKey() 
							+ ":" 
							+ set.getValue().getFirstname()
							+" " +set.getValue().getLastname() 
							/*+ " "+set.getValue().getUniversity()*/);
	}
	
//	System.out.println("\n\n\n*****Topper Per University Map As String*****");
//	System.out.println(grader.findTopperPerUniversity(gradedStudents).toString());
 }
}
