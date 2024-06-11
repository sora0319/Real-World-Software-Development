package com.iteratrlearning.shu_book.chapter_05;

/**
 * 실제 WorkFlow 에 Business Logic 을 태우는 것
 */

public class Main {

    public static void main(final String...args) {

        var env = new Facts();
        env.setFact("name", "Bob"); // 지역 변수 형식 추론 => 컴파일러가 자동으로 타입 반환
        env.setFact("jobTitle", "CEO");
        
        // Rule(Condition, Action)
        
        
        // BusinessRule 관리 및 실행하는 클래스
        final var businessRuleEngine = new BusinessRuleEngine(env);
        
        // 엔진은 있는데 필요한 rule 은.....?
        
        //RuleBuilder
        /*
         * 	ruleBuilder.when().then() => 사용자 친화적 API( 복잡도 낮음)
         *	1. 메소드 체이닝 => rule 인스턴스가 생성됨
         *		ruleBuilder 를 사용하서 rule 인스턴스 생성
         *	2. ruleBuilder 만 이용하면, 다양한 Business Logic rule 을 만드는 것을 공통화 할 수 있음
         *		=> SRP, OCP
         *	3. Business Domain 을 적용
         *		어떤 고객이 CEO 이면, 영업팀으로 메일 발송 (기본 설계) 
         */
        
        // 어떤 고객이 CEO 이면, 영업팀으로 메일 발송(rule) 을 만들고 싶음
        // => ruleSendEmailToSalesWhenCEO => 변수명 자체가 도메인 룰 => DDD
        // builder pattern 사용 => SRP (rule 만 생성 책임)
        final Rule ruleSendEmailToSalesWhenCEO =
                RuleBuilder
                  .when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                  .then(facts -> {
            var name = facts.getFact("name");
            System.out.println("Relevant customer!!!: " + name);
        });

        // 생성된 rule 은 전용 엔진에 등록해서 사용
        businessRuleEngine.addRule(ruleSendEmailToSalesWhenCEO);
        // 생성된 rule 사용
        businessRuleEngine.run();

    }
}
