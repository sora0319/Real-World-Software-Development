WorkFlow 에 Business Logic 추가 RPA(지나 가는 이야기...)

중요한 주제 및 키워드

객체 생성 모델링, 사용자 친화적 API 모델링, Builder 디자인 패턴,
SOLID OCP , TDD, 클래스 설계

지역 변수 형식 추론, 폴스루 (fall-through)

5장 이후 => 디자인 패턴, 리팩터링, TDD, DDD, SW 아키텍쳐 => 시스템 아키텍쳐 (AWS)  

##### 실행 단위

0. 배경 지식 
	- 규칙(Rule) : 회사의 비즈니스 상의 규칙  
		- 어떤 제품에 관심이 쏠리고, 어떤 조건을 만족하면 =>특별 할인 실시 (판촉 행사)  
		- 회사지출이 평소보다 높다면 =>알람 실행 (경영부서에 지출 확인)  
		- 어떤 고객이 CEO 이면, 영업부서로 메일 발송 (잠재 우량 고객 정보 공유)   
</br>
	- 규칙의 구성 요소 => Domain class 후보군  
  		- 팩트(Fact) : 규칙을 확인할 수 있는 대상 예) 어떤 제품, 지출, 어떤 고객  
  		- 조건(Condition) : 액션이 발생되는 시점 예) 팩트의 상태  
  		- 액션(Action) : 조건에 따른 수행해야 할 동작 예) 할인, 알람, 메일 발송  
</br>	
	따라서 Rule = Fact + Condition + Action 의 조합임
	  


1. WorkFlow 에 Business Logic 추가 변경 기능  
	1.1 2번에서 개선된 내용</br>
	2 번에 쓸데없이 의존되는 코드가 없어진 상태임
	모든 곳에 srp 를 적용하려고 노력 했음
</br>
	그래서 2번에 인터페이스에 사용된 두 가지 책임을 1번 각 클래스에서 하나의 책임이 되도록 개선함
</br></br>
	1.2 중요한 내용 </br>
	객체 생성 모델링, 사용자 친화적 API 모델링, Builder 디자인 패턴,  
	SOLID OCP , TDD, 클래스 설계
	</br></br>
	1.3 지역 변수 형식 추론 </br>
	경우에 따라서 명싲거 형식 추론 보다 var 를 사용하는 쪽이 가독성에 더 도움이 될 수도 있음
</br>
	하지만 var를 사용했는데 명싲거 형식 추론 보다 가독성이 낮아진다면 명시적 형식 추론을 사용하면 안됨
</br> </br>
	Main  
	BusinessRuleEngine  
	Rule  
	Facts  
	Condition  
	Action  
</br>
2. Business Logic 을 WorkFlow 에 실제 실행하지 않고, Business Logic 에 대한 검사 기능  
  	SOLID 의 ISP(인터페이스 분리 원칙) 에 대한 설명  
  	- 실제 코드는 ISP를 위반한 코드  
  	- ISP 위반을 개선 => 1번  
  	개선을 하다 보니, 자연스럽게 </br>
  		객체 생성 모델링, 사용자 친화적 API 모델링, Builder 디자인 패턴,  
		SOLID OCP , TDD, 클래스 설계
	와 함께 고려하게 됨  
</br>
	InspectorTest  
	JobTitleConditon  
	Inspector  
	Diagnosis  
</br>	
3. fall-through 방지 switch 문 </br>
	- break 없어도 됨
	- 열거자의 요소가 모두 사용되었는지 여부를 확인
</br> </br>
	SwitchExpressions  
	Stage  