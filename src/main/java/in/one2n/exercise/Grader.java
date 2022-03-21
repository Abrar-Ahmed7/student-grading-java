package in.one2n.exercise;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class Grader {

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here
    	
    	List<Student> studentsList = new ArrayList<Student>();
        try{
        	CSVReader reader = new CSVReaderBuilder(new FileReader(filepath))
                    .withSkipLines(1)
                    .build();


    		List<String[]> records;
			try {
				records = reader.readAll();
				Iterator<String[]> iterator = records.iterator();

	    		while (iterator.hasNext()) {
	    			String[] record = iterator.next();
	    			
	    			studentsList.add(new Student(record[0],record[1],record[2],
	    					Double.parseDouble(record[3]),
	    					Double.parseDouble(record[4]),
	    					Double.parseDouble(record[5]),
	    					Double.parseDouble(record[6])));
	    		}

			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    	return studentsList;
    }

    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here
		List<Double> finalScores = new ArrayList<Double>();
		List<Grade> grades = new ArrayList<Grade>();
//		for (int i=0;i<students.size();i++) {
//			l_Students.add(students.get(i));
//    		}
			for (Student student : students) {
//				finalScore.add((Double.parseDouble(student.getFinalScore().toString()));
				finalScores.add(student.getFinalScore());
				grades.add(student.getGrade());
			}
    					
//			System.out.println(grades);
//			System.out.println(finalScores);        
		return students;
    }

    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here
		
		Double top_score=0.0;
//		for(int i=0;i<gradedStudents.size();i++) {
//			l_Students.add(gradedStudents.get(i));		
//		}
		int j = 0;
		Double[] scores = new Double[gradedStudents.size()];
		for (Student student : gradedStudents) {
		
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
		Student overallTopper = new Student(gradedStudents.get(l).getFirstname(),gradedStudents.get(l).getLastname(),gradedStudents.get(l).getUniversity());
		
        return overallTopper;
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
