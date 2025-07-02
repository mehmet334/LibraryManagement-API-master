package patika.libraryManagementSpring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patika.libraryManagementSpring.dto.request.PublisherRequestDto;
import patika.libraryManagementSpring.dto.response.PublisherResponseDto;
import patika.libraryManagementSpring.service.Interface.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
@Validated
public class PublisherController {
    private final PublisherService service;

    @PostMapping
    public ResponseEntity<PublisherResponseDto> create(@RequestBody @Valid PublisherRequestDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PublisherResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> update(@PathVariable Long id,
                                                       @RequestBody @Valid PublisherRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
