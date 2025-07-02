package patika.libraryManagementSpring.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patika.libraryManagementSpring.dto.request.PublisherRequestDto;
import patika.libraryManagementSpring.dto.response.PublisherResponseDto;
import patika.libraryManagementSpring.entity.Publisher;
import patika.libraryManagementSpring.exception.ResourceNotFoundException;
import patika.libraryManagementSpring.repository.PublisherRepository;
import patika.libraryManagementSpring.service.Interface.PublisherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    private PublisherResponseDto mapToDto(Publisher p) {
        return PublisherResponseDto.builder()
                .id(p.getId())
                .name(p.getName())
                .establishmentYear(p.getEstablishmentYear())
                // adres response DTO'da istenmediği için eklemedik
                .build();
    }

    @Override
    public PublisherResponseDto create(PublisherRequestDto dto) {
        Publisher p = Publisher.builder()
                .name(dto.getName())
                .establishmentYear(dto.getEstablishmentYear())
                .address(dto.getAddress())
                .build();
        return mapToDto(publisherRepository.save(p));
    }

    @Override
    public List<PublisherResponseDto> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherResponseDto getById(Long id) {
        Publisher p = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));
        return mapToDto(p);
    }

    @Override
    public PublisherResponseDto update(Long id, PublisherRequestDto dto) {
        Publisher p = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));
        p.setName(dto.getName());
        p.setEstablishmentYear(dto.getEstablishmentYear());
        p.setAddress(dto.getAddress());
        return mapToDto(publisherRepository.save(p));
    }

    @Override
    public String delete(Long id) {
        Publisher p = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher", "id", id));
        publisherRepository.delete(p);
        return "Yayınevi başarıyla silindi.";
    }
}