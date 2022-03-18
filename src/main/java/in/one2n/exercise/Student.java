package in.one2n.exercise;

public class Student {

    private String firstname;
    private String lastname;
    private String university;
    private Double test1Score;
    private Double test2Score;
    private Double test3Score;
    private Double test4Score;

    // computed fields
    private Double finalScore;
    private Grade grade;

    public Student(String firstname, String lastname, String university) {
        // TODO: add your implementation here
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.university=university;
    }

    public Student(String firstname, String lastname, String university, Double test1Score, Double test2Score, Double test3Score, Double test4Score) {
        // TODO: add your implementation here
    	this.firstname=firstname;
    	this.lastname=lastname;
    	this.university=university;
    	this.test1Score=test1Score;
    	this.test2Score=test2Score;
    	this.test3Score=test3Score;
    	this.test4Score=test4Score;

    }
    
    public Student getStudentDetails() {
    	return new Student(firstname, lastname, university);
    }
    
    public Student getStudentDetailsAndMarks() {
    	return new Student(firstname, lastname, university, test1Score, test2Score, test3Score, test4Score);
    }
    
    public String getFirstname() {
    	return firstname;
    }
    
    public String getLastname() {
    	return lastname;
    }
    
    public String getUniversity() {
    	return university;
    }
    
    public Double getTest1Score() {
    	return test1Score;
    }
    
    public Double getTest2Score() {
    	return test2Score;
    }
    
    public Double getTest3Score() {
    	return test3Score;
    }
    
    public Double getTest4Score() {
    	return test4Score;
    }
    public Double getFinalScore() {
        // TODO: add your implementation here
    	Double total=test1Score+test2Score+test3Score+test4Score;
    	this.finalScore=total/4;
    	
        return finalScore;
    }

    public Grade getGrade() {
        // TODO: add your implementation here
    	Enum<Grade> grade = Grade.A;
    	if(finalScore<35) {
    		grade = Grade.F;
    	}
    	if((finalScore>=35)&& (finalScore<50)) {
    		grade = Grade.C;
    	}
    	
    	if((finalScore>=50)&& (finalScore<70)) {
    		grade = Grade.B;
    	}
    	if(finalScore>=70) {
    		grade = Grade.A;
    	}
        return (Grade) grade;
    }

}

