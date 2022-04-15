package com.zzami.alarm.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.NewsInfoDTO;
import com.zzami.alarm.api.dto.NewsSourceDTO;
import com.zzami.alarm.api.service.NewsService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/o")
@Api(tags = "40.뉴스관리")
public class NewsController {

    @Autowired
    NewsService newsService;

    /**
     * 
     * @author : bong 
     * @date : 2022.04.16 
     * @description : 뉴스 소스 등록  
     *
     */
    @ApiOperation(value = "뉴스 소스 등록[어드민]") 
    @ApiImplicitParams({
        @ApiImplicitParam(name = "newsSourceId", required = false,  paramType = "form", value = "뉴스아이디"),
        @ApiImplicitParam(name = "newsSourceNm", required = true,  paramType = "form", value = "뉴스이름"),
        @ApiImplicitParam(name = "newsSourceUrl", required = true, paramType = "form", value = "뉴스RSSURL"),
        @ApiImplicitParam(name = "categoryCd", required = true, paramType = "form", value = "뉴스카테고리"),   
    })
    @PostMapping(value="/news_source", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
    public ResponseEntity<ApiResponseBody<Void>> saveNewsSource(
            NewsSourceDTO newsSourceDto) {

        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);
        
        try {
            newsService.save(newsSourceDto); 

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody); 
    }
    
    /**
     * 
     * @author : bong 
     * @date : 2022.04.16 
     * @description : 단일 뉴스 등록  
     *
     */
    @ApiOperation(value = "뉴스 등록[FORM]") 
    @ApiImplicitParams({
        @ApiImplicitParam(name = "newsId", required = false,  paramType = "form", value = "뉴스아이디"), 
        @ApiImplicitParam(name = "newsSourceId", required = true,  paramType = "form", value = "뉴스소스아이디"), 
    })
    @PostMapping(value="/news_form", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
    public ResponseEntity<ApiResponseBody<Void>> saveNewsReportForm(
             NewsInfoDTO newsReportDto) {

        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);
        
        try {
            newsService.save(newsReportDto); 

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody); 
    }
    
    /**
     * 
     * @author : bong 
     * @date : 2022.04.16 
     * @description : 뉴스등록 외부에서의 뉴스 목록등록 
     *
     */
    @ApiOperation(value = "뉴스 등록[JSON]")  
    @PostMapping(value="/news_json" )
    public ResponseEntity<ApiResponseBody<Void>> saveNewsReportJson(
             @RequestBody List<NewsInfoDTO> newsList) {

        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);
        
        try {
            newsService.save(newsList);
        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody); 
    }
    
    /**
     * 
     * @author : bong 
     * @date : 2022.04.16 
     * @description : 뉴스 상세 조회 
     *
     */
    @ApiOperation(value = "뉴스 조회")  
    @GetMapping(value="/news/{newsId}" )
    public ResponseEntity<ApiResponseBody<NewsInfoDTO>> getNews(
             @PathVariable Long newsId ) {

        ApiResponseBody<NewsInfoDTO> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);
        
        try {
            responseBody.setResult(newsService.getNewsById(newsId));
        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody); 
    }
    
    

     
}
