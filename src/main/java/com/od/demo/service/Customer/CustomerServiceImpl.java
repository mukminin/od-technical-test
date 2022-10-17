package com.od.demo.service.Customer;

import com.od.demo.model.Customer.*;
import com.od.demo.service.Customer.model.CustResponse;
import com.od.demo.service.Customer.model.Customer.ContactResponse;
import com.od.demo.service.Customer.model.Customer.CustomerResponse;
import com.od.demo.service.Customer.model.Customer.Role.RoleResponse;
import com.od.demo.service.Customer.model.Customer.Role.RolesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String url = "https://avocado.od-tech.my/api/customer";
    public static final String url2 = "https://avocado.od-tech.my/api/customerRole";


    private final RestTemplate restTemplate;

    public CustomerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public CustomerDto getCustomer(String idType, String idNumber) {

//        RestTemplate restTemplate = new RestTemplate();
//            Map<String,String> request = new HashMap<>();
//            request.put("idType",idType);
//            request.put("idNumber",idNumber);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("idType", idType)
                .queryParam("idNumber", idNumber);

        CustResponse response;

        //to get customer information
        response = restTemplate.getForObject(builder.toUriString(), CustResponse.class);


        //set identification object
        IdentificationDto identificationDto = new IdentificationDto();

        identificationDto.setIdType(response.getCustomer().getIdentification().getIdType());

        //masked id number
        String tempString = response.getCustomer().getIdentification().getIdNumber();
        for (int i = 0; i < response.getCustomer().getIdentification().getIdNumber().length() - 4; i++) {

            char j = response.getCustomer().getIdentification().getIdNumber().charAt(i);

            tempString = tempString.replace(j, '*');
            System.out.println(j);
        }
        System.out.println(tempString);
        identificationDto.setIdNumber(tempString);

        //set details object
        DetailsDto detailsDto = new DetailsDto();
        detailsDto.setDisplayName(response.getCustomer().getDetails().getName());
        detailsDto.setDateOfBirth(response.getCustomer().getDetails().getDateOfBirth());
        detailsDto.setEmail(response.getCustomer().getDetails().getEmail());

        //set contact object
        List<ContactDto> contactDtos = new ArrayList<>();

        response.getCustomer().getContact().forEach(contactResponse -> {
            ContactDto tempContactDto = new ContactDto();
            tempContactDto.setType(contactResponse.getType());
            tempContactDto.setValue(contactResponse.getValue());
            contactDtos.add(tempContactDto);

        });


        //trigger get role method
        List<RoleDto> roleResponse = getCustomerRole(response.getCustomer().getId());

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(response.getCustomer().getId());
        customerDto.setIdentification(identificationDto);
        customerDto.setDetails(detailsDto);
        customerDto.setContact(contactDtos);
        customerDto.setRoles(roleResponse);


        return customerDto;


    }

    public List<RoleDto> getCustomerRole(String customerId) {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url2)
                .queryParam("customerId", customerId);

        RolesResponse response;
        List<RoleDto> roleDtos = new ArrayList<RoleDto>();


        //to get customer information
        response = restTemplate.getForObject(builder.toUriString(), RolesResponse.class);

        //set role object
        response.getRoles().forEach(roleResponse -> {
            RoleDto tempRoleDto = new RoleDto();
            tempRoleDto.setCode(roleResponse.getCode());
            tempRoleDto.setDescription(roleResponse.getDescription());
            roleDtos.add(tempRoleDto);
            ;
        });

        return roleDtos;
    }

}
