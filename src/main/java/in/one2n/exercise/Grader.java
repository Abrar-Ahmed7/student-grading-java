package in.one2n.exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grader {

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here
    	
    	List<Student> studentsList = new ArrayList<Student>();
    	List<Student> studentsListTemp = new ArrayList<Student>();
        try{

          File inputF = new File(filepath);
          InputStream inputFS = new FileInputStream(inputF);
          BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

          studentsListTemp = br
        		  .lines()
        		  .skip(1)
        		  .map(line ->{
						String[] attribute = line.split(",");
						String firstName=attribute[0];
			  			String lastName=attribute[1];
			  			String university=attribute[2];
			  			Double score1=Double.parseDouble(attribute[3]);
			  			Double score2=Double.parseDouble(attribute[4]);
			  			Double score3=Double.parseDouble(attribute[5]);
			  			Double score4=Double.parseDouble(attribute[6]);
						Student student = new Student(firstName,lastName,university,score1,score2,score3,score4);
			  			return student;
			})
//        		  .filter(list->"Boston University".equals(list.getUniversity()))
        		  .collect(Collectors.toList());
          br.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    	for (Student student : studentsListTemp) {
    		studentsList.add(student);
			
		}
        
    	return studentsList;
    }

    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here
		List<Student> l_Students = new ArrayList<Student>();
		List<Double> finalScores = new ArrayList<Double>();
		List<Grade> grades = new ArrayList<Grade>();
		for (int i=0;i<students.size();i++) {
			l_Students.add(students.get(i));
    		}
			for (Student student : l_Students) {
//				finalScore.add((Double.parseDouble(student.getFinalScore().toString()));
				finalScores.add(student.getFinalScore());
				grades.add(student.getGrade());
			}
    					
//			System.out.println(grades);
//			System.out.println(finalScores);        
		return l_Students;
    }

    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here
    	List<Student> l_Students = new ArrayList<Student>();
		
		Double top_score=0.0;
		for(int i=0;i<gradedStudents.size();i++) {
			l_Students.add(gradedStudents.get(i));		
		}
		int j = 0;
		Double[] scores = new Double[gradedStudents.size()];
		for (Student student : l_Students) {
		
			scores[j]= student.getFinalScore();
//			System.out.println(top_score);
			j++;		
		}
		
		for(int k =0;k<scores.length;k++) {
			if(scores[k]>top_score) {
				top_score=scores[k];
			}
		}
		int l;
		for(l=0;l<scores.length;l++) {
			if(scores[l]==top_score) {
				break;
			}
		}
//		System.out.println(l);
		Student nah = new Student(l_Students.get(l).getFirstname(),l_Students.get(l).getLastname(),l_Students.get(l).getUniversity());
		
        return nah;
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here
    	String uni = "";
    	for (Student student : gradedStudents) {
    		if(!uni.contains(student.getUniversity())) {
    			uni=uni+student.getUniversity()+"-";
    		}    		
    	}
    	
    	String[] individualUni = uni.split("-");
    	Map<String, Double> uniTopperMap = new HashMap<>();
    	//    	System.out.println(individualUni.length);
    	for (String string : individualUni) {
			uniTopperMap.put(string, 0.0);
		}
    	Map<String,Student> overallGroups = new HashMap<>();
    	for (Student student : gradedStudents) {
    		for (String string : individualUni) {
    			if(string.equals(student.getUniversity())) {
    				if(student.getFinalScore()>(uniTopperMap.get(string))) {
    					uniTopperMap.replace(string,student.getFinalScore());
    					break;
    				}
    			}
    		}
    		
			
		}
    	
    	
    	for (Map.Entry<String, Double> set : uniTopperMap.entrySet()) {
    		for (Student student: gradedStudents) {
    			Double finalScoreFromStudent = student.getFinalScore();
    			Double finalScoreFromMap =set.getValue();
    			if(finalScoreFromStudent.equals(finalScoreFromMap)) {
    			overallGroups.put(set.getKey(),new Student(student.getFirstname(),student.getLastname(),student.getUniversity()));
    			break;
    			
    		}
    		
    	}
    	}
    	
//    	for(Map.Entry<String, Student> set :overallGroups.entrySet()) {
//    		System.out.println(set.getKey() + " = "
//                    + set.getValue());
//    	}
        return overallGroups;
    }
}
