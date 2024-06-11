package com.iteratrlearning.shu_book.chapter_05;

import java.util.ArrayList;
import java.util.List;

/**
 * 회사의 WorkFlow 에 관련된 모든 Business Logic Rule을 
 * 가지고 있고, 관리, 실행을 시키고 있음
 */

public class BusinessRuleEngine {

    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(Facts facts) {
        this.facts = facts;
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void run() {
    	// 매개변수 : Consumer<? super Rule> => 매개변수를 사용하고 아무것도 생성하지 않는 것
        this.rules.forEach(rule -> rule.perform(facts));
    }
}
