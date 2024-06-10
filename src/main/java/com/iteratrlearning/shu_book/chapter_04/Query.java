package com.iteratrlearning.shu_book.chapter_04;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toMap;

/*
 *  public class 가 아니라 default class 임
 *  
 *  자기 package 안에서만 Query를 볼 수 있음
 *  다른 package 에서는 사용할 일이 없다고 생각해서 범위 설정을 이렇게 함
 *  
 *  따라서, 다른 package에서 불필요한 class 가 보이지 않게 되어 복잡도가 낮아짐 => 캡슐화
 */

class Query implements Predicate<Document> {
    private final Map<String, String> clauses;

    // query 입력 값 : "patient:Joe,body:Diet Coke"
    // 결과 값 : Map = {patient, Joe}, {body, Diet Coke}
    static Query parse(final String query) {
    	/*
    	 * 사용자의 API 접근성을 향상 시킴
    	 * 1. 생성자를 private
    	 * 2. 조회 메소드에서 생성자 호출
    	 * 
    	 */
        return new Query(Arrays.stream(query.split(","))
              .map(str -> str.split(":"))
              .collect(toMap(x -> x[0], x -> x[1])));
    }

    private Query(final Map<String, String> clauses) {
        this.clauses = clauses;
    }

    @Override
    public boolean test(final Document document) {
        return clauses.entrySet()
                      .stream()
                      .allMatch(entry -> {
                          final String documentValue = document.getAttribute(entry.getKey());
                          final String queryValue = entry.getValue();
                          return documentValue != null && documentValue.contains(queryValue);
                      });
    }
}
