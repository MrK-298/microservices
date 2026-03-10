package microproject.user.mapper;


import microproject.user.domain.Permission;
import microproject.user.dto.request.PermissionRequest;
import microproject.user.dto.response.detail.PermissionResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdate(PermissionRequest request, @MappingTarget Permission permission);
}
