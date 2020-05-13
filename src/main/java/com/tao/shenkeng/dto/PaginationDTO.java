package com.tao.shenkeng.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Data
@Component
public class PaginationDTO {
    List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        //判断页面总数
        if(totalCount%size!=0){
            totalPage=totalCount/size+1;
        }else{
            totalPage=totalCount/size;
        }
        //防止分页栏显示方位外页数

        this.page=page;

        //遍历分页栏显示的页面
        for (int i = 0; i<3 ; i++) {
            if(this.page-i>0) {
                pages.add(0,this.page - i);
            }
            if(this.page+i+1<=totalPage){
                pages.add(this.page+i+1);
            }
        }


        //是否显示上一页
        if(this.page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否显示下一页
        if(this.page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }

        //是否显示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

        //是否显示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
