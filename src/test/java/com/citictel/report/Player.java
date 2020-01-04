package com.citictel.report;

  import java.util.Arrays;
  import java.util.HashMap;
  
  public class Player {
     private String name;
     private int age;
     private HashMap Hero = new HashMap<String,String>();
     private String[] nickNames;
     private Honor[] Honors;
     
    
     public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public HashMap getHero() {
		return Hero;
	}


	public void setHero(HashMap hero) {
		Hero = hero;
	}


	public String[] getNickNames() {
		return nickNames;
	}


	public void setNickNames(String[] nickNames) {
		this.nickNames = nickNames;
	}


	public Honor[] getHonors() {
		return Honors;
	}


	public void setHonors(Honor[] honors) {
		Honors = honors;
	}


	@Override
     public String toString() {
         return "Player [name=" + name + ", age=" + age + ", Hero=" + Hero + ", nickNames=" + Arrays.toString(nickNames)
                 + ", Honors=" + Arrays.toString(Honors) + "]";
     }
 
 
     static class Honor
     {
         int year;
         String name;
         public int getYear() {
             return year;
         }
         public void setYear(int year) {
             this.year = year;
         }
         public String getName() {
             return name;
         }
         public void setName(String name) {
             this.name = name;
         }
         @Override
         public String toString() {
             return "Honor [year=" + year + ", name=" + name + "]";
         }
     }
     
 
 }