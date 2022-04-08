package com.zzami.alarm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzami.alarm.api.dto.AddressInfoDTO;
import com.zzami.alarm.api.service.AddressService;
import com.zzami.alarm.core.dto.result.ApiResponseBody;
import com.zzami.alarm.core.dto.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/o")
@Api(tags = "10.주소관리")
public class AddressController {

    @Autowired
    AddressService addressService;

    @ApiOperation(value = "주소 조회")
    @GetMapping("/address/{addCd}")
    public ResponseEntity<ApiResponseBody<AddressInfoDTO>> getAddr(
            @PathVariable String addCd) {

        ApiResponseBody<AddressInfoDTO> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);
        
        try { 
            AddressInfoDTO weather = addressService.findAddressInfoByAddcd(addCd);
            responseBody.setResult(weather);

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }

    @ApiOperation(value = "주소 저장")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "addCd", required = true,  paramType = "form", value = "주소코드"),
        @ApiImplicitParam(name = "addNm", required = true, paramType = "form", value = "주소명"),
        @ApiImplicitParam(name = "lat", required = true, paramType = "form", value = "위도"),
        @ApiImplicitParam(name = "lon", required = true, paramType = "form", value = "경도"),
        @ApiImplicitParam(name = "nx", required = true, paramType = "form", value = "nx"),
        @ApiImplicitParam(name = "ny", required = true, paramType = "form", value = "ny"),
        @ApiImplicitParam(name = "stationNm", required = true, paramType = "form", value = "관측소명")
    })
    @PostMapping(value="/address", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ApiResponseBody<Void>> saveAddr(AddressInfoDTO addr) {
        
        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);

        try {
            addressService.saveAddress(addr);

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);

    }
    
    @ApiOperation(value = "주소 삭제")
    @DeleteMapping(value="/address/{addressId}")
    public ResponseEntity<ApiResponseBody<Void>> removeAddress( @PathVariable Long  addressId) {
        ApiResponseBody<Void> responseBody = new ApiResponseBody<>();
        responseBody.setResponse(ResultStatus.OK);

        try { 
            addressService.removeAddress(addressId);

        } catch (Exception ex) {
            throw ex;
        }

        return ResponseEntity.ok().body(responseBody);
    }

}
