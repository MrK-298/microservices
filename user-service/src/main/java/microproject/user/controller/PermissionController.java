package microproject.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import microproject.common.dto.request.FilterRequest;
import microproject.common.dto.response.ApiMessage;
import microproject.common.dto.response.ApiResponse;
import microproject.user.dto.request.PermissionRequest;
import microproject.user.dto.response.detail.PermissionResponse;
import microproject.user.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Quản lý quyền")
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService service;

    @GetMapping
    private ApiResponse<List<PermissionResponse>> getList(FilterRequest request)
    {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());

        Page<PermissionResponse> responses = service.getList(pageable,request);

        return ApiResponse.successPage(HttpStatus.OK,responses);
    }

    @GetMapping("/id")
    private ApiResponse<PermissionResponse> getById(@PathVariable UUID id)
    {
        return ApiResponse.success(HttpStatus.OK, ApiMessage.FETCH_SUCCESS, service.getById(id));
    }

    @PostMapping
    private ApiResponse<PermissionResponse> create(@RequestBody @Valid PermissionRequest request)
    {
        return ApiResponse.success(HttpStatus.CREATED, ApiMessage.CREATE_SUCCESS, service.create(request));
    }

    @PutMapping("/id")
    private ApiResponse<PermissionResponse> update(@PathVariable UUID id,@RequestBody @Valid PermissionRequest request)
    {
        return ApiResponse.success(HttpStatus.OK, ApiMessage.UPDATE_SUCCESS, service.update(id,request));
    }

}
