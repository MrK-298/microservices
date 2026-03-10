package microproject.user.service;

import lombok.RequiredArgsConstructor;
import microproject.common.dto.request.FilterRequest;
import microproject.common.exception.AppException;
import microproject.user.domain.Permission;
import microproject.user.dto.request.PermissionRequest;
import microproject.user.dto.response.detail.PermissionResponse;
import microproject.user.mapper.PermissionMapper;
import microproject.user.repository.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;


    public PermissionResponse create(PermissionRequest request)
    {
        if(permissionRepository.existsByName(request.getName()))
            throw new AppException(HttpStatus.BAD_REQUEST, "Permission name is already existed");

        Permission entity = permissionMapper.toEntity(request);

        return permissionMapper.toResponse(permissionRepository.save(entity));
    }

    public PermissionResponse update(UUID id, PermissionRequest request)
    {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, "Permission not found"));

        if(permissionRepository.existsByNameAndIdNot(request.getName(),id))
            throw new AppException(HttpStatus.BAD_REQUEST, "Permission name is already existed");

        permissionMapper.toUpdate(request,existing);

        return permissionMapper.toResponse(permissionRepository.save(existing));
    }

    public PermissionResponse getById(UUID id)
    {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.BAD_REQUEST, "Permission not found"));

        return permissionMapper.toResponse(permissionRepository.save(existing));
    }

    public Page<PermissionResponse> getList(Pageable pageable, FilterRequest request)
    {
        if (request.getSearch() == null || request.getSearch().trim().isEmpty()) {
            return permissionRepository.findAll(pageable)
                    .map(permissionMapper::toResponse);
        }

        return permissionRepository.search(request.getSearch(),pageable)
                .map(permissionMapper::toResponse);
    }
}
