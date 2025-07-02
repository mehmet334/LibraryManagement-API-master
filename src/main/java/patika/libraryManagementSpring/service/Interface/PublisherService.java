package patika.libraryManagementSpring.service.Interface;

import patika.libraryManagementSpring.dto.request.PublisherRequestDto;
import patika.libraryManagementSpring.dto.response.PublisherResponseDto;

import java.util.List;

public interface PublisherService {
    PublisherResponseDto create(PublisherRequestDto dto);
    List<PublisherResponseDto> getAll();
    PublisherResponseDto getById(Long id);
    PublisherResponseDto update(Long id, PublisherRequestDto dto);
    String delete(Long id);
}
