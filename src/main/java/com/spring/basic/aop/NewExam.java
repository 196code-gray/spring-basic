package com.spring.basic.aop;

public class NewExam implements Exam{

    private int kor;
    private int eng;
    private int math;
    private int com;

    public NewExam() {
    }

    public NewExam(int kor, int eng, int math, int com) {
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.com = com;
    }

    @Override
    public float avg() {
        float result = total() / 4.0f;
        return result;
    }

    @Override
    public int total() {

        int result = kor + eng + math + com;

        return result;
    }
}
