package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.MaterialAssignment;
import br.com.tatamedobem.dto.MaterialAssignmentRequest;
import br.com.tatamedobem.dto.MaterialAssignmentResponse;
import br.com.tatamedobem.repository.MaterialAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialAssignmentService {

    private final MaterialAssignmentRepository materialAssignmentRepository;
    private final StudentService studentService;
    private final InventoryService inventoryService;

    @Transactional(readOnly = true)
    public List<MaterialAssignmentResponse> findAll() {
        return materialAssignmentRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<MaterialAssignmentResponse> findResizeAlerts() {
        return materialAssignmentRepository.findByRequiresResizeTrue().stream()
                .map(this::toResponse)
                .toList();
    }

    public MaterialAssignmentResponse create(MaterialAssignmentRequest request) {
        MaterialAssignment assignment = new MaterialAssignment();
        assignment.setStudent(studentService.getEntity(request.studentId()));
        assignment.setInventoryItem(inventoryService.getEntity(request.inventoryItemId()));
        assignment.setAssignedAt(request.assignedAt());
        assignment.setAssignedSize(request.assignedSize());
        assignment.setNotes(request.notes());
        assignment.setRequiresResize(Boolean.TRUE.equals(request.requiresResize()));
        return toResponse(materialAssignmentRepository.save(assignment));
    }

    private MaterialAssignmentResponse toResponse(MaterialAssignment assignment) {
        return new MaterialAssignmentResponse(
                assignment.getId(),
                assignment.getStudent().getId(),
                assignment.getStudent().getFullName(),
                assignment.getInventoryItem().getId(),
                assignment.getInventoryItem().getItemName(),
                assignment.getAssignedAt(),
                assignment.getAssignedSize(),
                assignment.getNotes(),
                assignment.getRequiresResize()
        );
    }
}
