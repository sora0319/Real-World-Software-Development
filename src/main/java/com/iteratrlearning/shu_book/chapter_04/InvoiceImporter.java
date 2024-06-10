package com.iteratrlearning.shu_book.chapter_04;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.iteratrlearning.shu_book.chapter_04.Attributes.*;

/**
 * InvoiceImporter 의 역할
 * 
 * 	- 파싱 대상 파일 : patient.invoice (치료 비용 청구서)
 * 	- 중요한 항목 : 환자명, 본문, 청구 비용
 *	- 중요한 항목이 key, value 형태로 구성 => map에 저장
 *	- Document로 반환 : LSP 조건 
 *	- DocumentManagementSystem.documents 에 등록 함
 *
 *
 */

class InvoiceImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final String AMOUNT_PREFIX = "Amount: ";

    // tag::importFile[]
    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        // 환자명 추출
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        // 청구 금액 추출
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        
        // 후행 조건 만족
        return new Document(attributes);
    }
    // end::importFile[]
}
