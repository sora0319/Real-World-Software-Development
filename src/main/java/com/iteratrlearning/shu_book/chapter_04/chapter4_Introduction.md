### 문서 관리 시스템의 프로그램 구조
1. 문서 관리 시스템  
	- DocumentManagementSystemTest  
	- 의사가 사용하는 프로그램  
	- Xray 사진, 치료 비용 청구서, 리포트(환자 상담 기록) , 편지, email  
  
2. File Importer : LSP(후행조건), 약한 결합  
	- Importer Interface  
	- 구현체 : ImageImporter, InvoiceImporter, LetterImporter, ReportImporter  
    
3. 임포트 후 파싱 완료된 파일 정보 : SRP(단일 책임 원칙), 응집도 높아짐  
	- Document  
	- TextFile  
  
4. Import 된 File 의 속성 정보 : 재활용, 가독성, 유지보수성  
	- 쉽게 조회할 수 있는 구조의 메타 데이터로 활용하기 위함  
	- attributes  
  	
5. 검색 : SRP(단일 책임 원칙), 함수형 인터페이스 구현 Predicate<Document>, 캡슐화
	- Query 
	- 캡슐화 : 사용자 API 접근성을 향상시킴, 복잡도를 낮춤  
  
6. 예외  
	- UnkwonFileTypeException  
  		
  
### 단위 테스트 구조
1. 단위 테스트 클래스
	- DocumentManagementSystemTest => 리팩토링 대상, SOLID 적용  

2. 단위 테스트용 메타 데이터  
3. Test Case  
	2.1 Import 관련  
		- shouldImportFile : 대상 파일 임포트 -> 파싱 및 저장 -> Document -> 검증  
		- shouldImportLetterAttributes : 편지 임포트 -> Document -> 검증  
		- shouldImportReportAttributes  
		- shouldImportImageAttributes  
		- shouldImportInvoiceAttributes  
  
2.2 검색 관련  
	- shouldBeAbleToSearchFilesByAttributes  
	
2.3 파일 관련 => 예상된 Exception 발생을 검증
	- shouldNotImportMissingFile
	- shouldNotImportUnknownFile  

  
4. 재사용성 고려  
	3.1 각 단위테스트에서 반복 사용되는 검증 부분  
	- assertIsReport  
	- assertAttributeEquals  
	= assertTypeIs  
  
  	3.2  
  	- onlyDocument  
  	
	
	
	


