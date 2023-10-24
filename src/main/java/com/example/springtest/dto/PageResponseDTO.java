package com.example.springtest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Getter
@ToString
@Log4j2
public class PageResponseDTO<E> {
    private final int last;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pagRequsetDTO, List<E> dtoList, int total){
        this.page = pagRequsetDTO.getPage();
        this.size = pagRequsetDTO.getSize();
        
        this.total = total;
        this.dtoList = dtoList;
        
        this.end = (int) (Math.ceil(this.page / 10.0))*10;//현제페이지 일때 끝페이지가 몇인지 나오는 값(10개씩 나열 헸을때)

        this.start = this.end -9;//시작페이지
        
        this.last = (int)(Math.ceil((total/(double)size)));//마지막 페이지가 몇 페이지인지 나오는 값
        
        this.end = end > last ? last : end;
        
        this.prev = this.start > 1;
        
        this.next = total > this.end * this.size;
    }
    
    private int page;
    private int size;
    private int total;
    
    //시작페이지 번호
    private int start;
    
    //끝 페이지 번호
    private int end;
    
    //이전 페이지의 존제 여부
    private boolean prev;
    
    //다음 페이지의 존재 여부
    private boolean next;
    
    private List<E> dtoList;
}
