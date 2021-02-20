package com.eunmok.demo;


import com.eunmok.demo.business.common.dto.TestApiResponse;
import com.eunmok.demo.business.common.util.FeignAnswerResponse;
import com.eunmok.demo.business.common.util.FeignResponse;
import com.eunmok.demo.business.common.util.QnaView;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AlTest {

    @Test
    public void test123(){
        List<QnaView.SummaryMerge> first = Optional.of(answerData())
                .map(TestApiResponse::getData)
                .map(list -> list.stream().map(this::getMonthConvert).collect(toList()))
                .orElse(null);
        List<QnaView.SummaryMerge> second = Optional.of(monthData())
                .map(TestApiResponse::getData)
                .map(list -> list.stream().map(this::getMonthConvert).sorted(Comparator.comparing(QnaView.SummaryMerge::getMonth)).collect(toList()))
                .orElse(null);

        Map<String, List<QnaView.SummaryMerge>> map = groupingData(first, second);
        Map<String, List<QnaView.SummaryMerge>> result = setDefaultData(map);
        List<QnaView> list = getQnaView(result).stream().sorted(Comparator.comparing(QnaView::getSubjectName)).collect(toList());
        list.stream().forEach(qnaView -> {
            System.out.println("과목 : "+ qnaView.getSubjectName());
            qnaView.getList().stream().forEach(view -> {
                System.out.println("KEY : " + view.getColumn() + ", Value : " + view.getCount());
            });
        });
    }

    private Map<String, List<QnaView.SummaryMerge>> setDefaultData(Map<String, List<QnaView.SummaryMerge>> res){
        List<LocalDate> monthRes = Arrays.asList(LocalDate.of(2020, 11, 1)
                , LocalDate.of(2020, 12, 1)
                , LocalDate.of(2021, 1, 1)
                , LocalDate.of(2021, 2, 1));
        List<Integer> answerRes = Arrays.asList(10001, 10003, 10005, 10007);
        for(Map.Entry<String, List<QnaView.SummaryMerge>> map :  res.entrySet()){
            List<Integer> months = map.getValue().stream().map(QnaView.SummaryMerge::getMonth).collect(toList());
            for(LocalDate m : monthRes){
                if(!months.contains(m.getMonthValue())){
                    res.get(map.getKey()).add(QnaView.SummaryMerge.builder().yearMonth(m).month(m.getMonthValue()).count(0).build());
                }
            }

            List<Integer> answers = map.getValue().stream().map(QnaView.SummaryMerge::getAnswerStatus).collect(toList());
            for(int a : answerRes){
                if(!answers.contains(a)){
                    res.get(map.getKey()).add(QnaView.SummaryMerge.builder().answerStatus(a).count(0).build());
                }
            }
        }
        return res;
    }

    private Map<String, List<QnaView.SummaryMerge>> groupingData(List<QnaView.SummaryMerge> first, List<QnaView.SummaryMerge> second){
        return Stream.of(first, second)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(groupingBy(QnaView.SummaryMerge::getSubjectName));
    }

    private TestApiResponse<List<FeignAnswerResponse>> answerData(){
        List<FeignAnswerResponse> res = Arrays.asList(FeignAnswerResponse.builder().subjectName("수학").answerStatus(10001).count(13).build());
        return TestApiResponse.<List<FeignAnswerResponse>>builder().code(200).message("").data(res).build();
    }

    private TestApiResponse<List<FeignResponse>> monthData(){
        List<FeignResponse> res = Arrays.asList(FeignResponse.builder().subjectName("수학").yearMonth("202011").month(11).count(13).build()
                , FeignResponse.builder().subjectName("수학").yearMonth("202012").month(12).count(99).build()
                , FeignResponse.builder().subjectName("사회").yearMonth("202101").month(1).count(4).build());
        return TestApiResponse.<List<FeignResponse>>builder().code(200).message("").data(res).build();
    }

    private List<QnaView> getQnaView(Map<String, List<QnaView.SummaryMerge>> res){
        List<QnaView> result = new ArrayList<>();
        for(Map.Entry<String, List<QnaView.SummaryMerge>> map : res.entrySet()){
            result.add(QnaView.builder().subjectName(map.getKey()).list(this.convert(map.getValue())).build());
        }
        return result;
    }

    private List<QnaView.SummaryValue> convert(List<QnaView.SummaryMerge> res){
        return res.stream()
                .sorted(Comparator.comparing(QnaView.SummaryMerge::getYearMonth, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(QnaView.SummaryMerge::getAnswerStatus))
                .map(data -> QnaView.SummaryValue.builder()
                        .column(data.getMonth() == 0 ? String.valueOf(data.getAnswerStatus()) : String.valueOf(data.getMonth()))
                        .count(data.getCount()).build())
                .collect(toList());
    }

    private QnaView.SummaryMerge getMonthConvert(FeignAnswerResponse res){
        return QnaView.SummaryMerge.builder().subjectName(res.getSubjectName()).answerStatus(res.getAnswerStatus()).count(res.getCount()).build();
    }

    private QnaView.SummaryMerge getMonthConvert(FeignResponse res){
        return QnaView.SummaryMerge.builder().subjectName(res.getSubjectName()).yearMonth(res.getYearMonth()).month(res.getMonth()).count(res.getCount()).build();
    }
}
