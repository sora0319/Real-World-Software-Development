package com.iteratrlearning.shu_book.chapter_05;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InspectorTest {

    @Test
    public void inspectOneConditionEvaluatesTrue() {

    	// 규칙을 확인할 수 있는 대상
        final Facts facts = new Facts();
        facts.setFact("jobTitle", "CEO");
        
        // 조건부 액션
        final ConditionalAction conditionalAction = new JobTitleCondition();
        // 실제 WorkFlow에서 하지 않고, 가상의 테스트 환경(Inspector)에서 검증함
        final Inspector inspector = new Inspector(conditionalAction);

        // when
        //가상의 테스트환경에서 검사 실시 및 검사 결과 반환
        final List<Diagnosis> diagnosisList = inspector.inspect(facts);

        // then
        assertEquals(1, diagnosisList.size());
        assertEquals(true, diagnosisList.get(0).isPositive());


    }

    /*
     * 현재 구현된 부분이 ISP 위배가 된 상태
     * SRP 를 확장해서 생각하면 이해에 도움이 됨
     * interface 도 단일 책임 원칙이 좋을 수도 있다
     */
    private static class JobTitleCondition implements ConditionalAction {

    	/* 직책의 조건에 따른 실행 또는 실시
    	 	JobTitleCondition 가 perform() 을 의존하고 있는 상태가 됨
    	 	지금은 필요 없지만 ConditionalAction 의 매개변수 수정이 발생한다면
    	 	여기도 또 수정해야됨. 수정해도 필요가 없는데...
    		=> 코드 레벨로 서로 의존되고 있는 상태임 => 분리 할 수 밖에 없음
    		처음부터 분리했어야 되는데 라고 생각됨
    	*/
        @Override
        public void perform(Facts facts) {

        }

        // 조건부 액션에 대한 실시 가능 여부 평가
        // 어떤 고객이 CEO, 이면 영업팀으로 메일 발송 => rule
        // 업무 관련 프로그래밍 => 도메인 주도 프로그래밍 => DDD
        @Override
        public boolean evaluate(Facts facts) {
        	// 실시 가능한 조건인지 평가
        	// true
            return "CEO".equals(facts.getFact("jobTitle"));
        }
    }
}
