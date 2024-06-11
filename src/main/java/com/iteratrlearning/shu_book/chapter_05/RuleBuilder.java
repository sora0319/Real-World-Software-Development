package com.iteratrlearning.shu_book.chapter_05;

public class RuleBuilder {
    private Condition condition;

    // private 생성자
    /* Business Rule 용으로 객체를 생성시켜야 함으로 
     * 외부에서 생성자를 사용할 수 없도록 해서, 
     * 내부에서 일정 형태의 rule 이 되도록 해야함
    */
    private RuleBuilder(Condition condition) {
        this.condition = condition;
    }

    // static 메소드
    // 반환형은 ruleBuilder 자기 자신 타입임
    // OCP 가 적용됨
    // 생성자 호출 
    public static RuleBuilder when(Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(Action action) {
        return new Rule(condition, action);
    }
}