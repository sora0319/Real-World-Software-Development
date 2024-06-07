package com.iteratrlearning.shu_book.chapter_03;


public class MainApplication {

    public static void main(final String[] args) throws Exception {

        final BankStatementAnalyzer bankStatementAnalyzer
                = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser
                = new BankStatementCSVParser();

        /*
         * 보고서를 html 포멧으로 생성
         * 향후에 excel 포맷으로 새로운 요구 사항이 발생한다면?
         * 따라서 느슨한 결합으로 구성
         */
        
        final Exporter exporter = new HtmlExporter();

        // chapter 2에선 매개변수가 두개였으나, 이제는 콘솔이 아닌 html 파일 생성
        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser, exporter);

    }
}
