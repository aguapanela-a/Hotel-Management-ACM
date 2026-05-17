package com.acm.sgh.servicesManagement.service;

import com.acm.sgh.hotelManagement.entities.Hotel;
import com.acm.sgh.hotelManagement.repository.HotelRepository;
import com.acm.sgh.servicesManagement.dto.ServiceRequest;
import com.acm.sgh.servicesManagement.dto.ServiceResponse;
import com.acm.sgh.servicesManagement.entitie.OtherService;
import com.acm.sgh.servicesManagement.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OtherServiceService {

    private final ServicesRepository servicesRepository;
    private final HotelRepository hotelRepository;

    @Transactional
    public ServiceResponse createService(ServiceRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + request.getHotelId()));

        OtherService service = OtherService.builder()
                .serviceType(request.getServiceType())
                .serviceName(request.getServiceName())
                .description(request.getDescription())
                .price(request.getPrice())
                .hotel(hotel)
                .build();

        return toResponse(servicesRepository.save(service));
    }

    @Transactional(readOnly = true)
    public List<ServiceResponse> getAllServices() {
        return servicesRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServiceResponse> getServicesByHotel(UUID hotelId) {
        return servicesRepository.findByHotelId(hotelId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceResponse getServiceById(UUID id) {
        OtherService service = servicesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con id: " + id));
        return toResponse(service);
    }

    @Transactional
    public ServiceResponse updateService(UUID id, ServiceRequest request) {
        OtherService service = servicesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con id: " + id));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado con id: " + request.getHotelId()));

        service.setServiceType(request.getServiceType());
        service.setServiceName(request.getServiceName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setHotel(hotel);

        return toResponse(servicesRepository.save(service));
    }

    @Transactional
    public void deleteService(UUID id) {
        if (!servicesRepository.existsById(id)) {
            throw new IllegalArgumentException("Servicio no encontrado con id: " + id);
        }
        servicesRepository.deleteById(id);
    }

    private ServiceResponse toResponse(OtherService service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .serviceType(service.getServiceType())
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .hotelId(service.getHotel().getId())
                .hotelName(service.getHotel().getHotelName())
                .build();
    }
}
