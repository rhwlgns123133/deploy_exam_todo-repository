package com.example.springtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import sun.jvm.hotspot.utilities.UnsupportedPlatformException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    private String link;//페이지 번호 나열

    private String[] types;//제목, 작성자 (배열)
    private String keyword;//검색어
    private boolean finished;//완료여부
    private LocalDate from;//기간 검색(시작날짜)
    private LocalDate to;//기간 검색(끝나는 날짜)

    public static Object List;
    @Builder.Default
    @Min(value=1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value=10)
    @Max(value=100)
    @Positive
    private int size = 10;

    public int getSkip(){
        return (page -1) * size;
    }

    public String getLink(){
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);
        link = builder.toString();

        if(this.finished){
            builder.append("&finished=on");
        }

        if(this.types != null && this.types.length>0){
            for(int i=0; i< this.types.length; i++){
                builder.append("&types=" + types[i]);
            }
        }

        if(this.keyword != null){
            try{
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }

        if(this.from != null){
            builder.append("&from=" + from.toString());
        }

        if(this.to != null){
            builder.append("&to=" + to.toString());
        }
        return builder.toString();
    }

    public boolean checkType(String type){
        if(this.types == null || this.types.length == 0){
            return false;
        }
        return Arrays.asList(this.types).contains(type);
    }
}
