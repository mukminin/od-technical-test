package com.od.demo.service.Customer;

import com.od.demo.mapper.Customer.*;
import com.od.demo.model.Customer.*;
import com.od.demo.service.Customer.model.CustResponse;
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
    private final DetailsMapper detailsMapper;
    private final CustomerMapper customerMapper;
    private final RoleMapper roleMapper;
    private final IdentificationMapper identificationMapper;
    private final ContactMapper contactMapper;

    public CustomerServiceImpl(RestTemplateBuilder restTemplateBuilder, DetailsMapper detailsMapper, CustomerMapper customerMapper, RoleMapper roleMapper, IdentificationMapper identificationMapper, ContactMapper contactMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.detailsMapper = detailsMapper;
        this.customerMapper = customerMapper;
        this.roleMapper = roleMapper;
        this.identificationMapper = identificationMapper;
        this.contactMapper = contactMapper;
    }

    @Override
    public CustomerDto getCustomer(String idType, String idNumber) {

//        RestTemplate restTemplate = new RestTemplate();
//            Map<String,String> request = new HashMap<>();
//            request.put("idType",idType);
//            request.put("idNumber",idNumber);

//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity requestEntity = new HttpEntity<>(headers);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("idType", idType)
                .queryParam("idNumber", idNumber);

        CustResponse response;

        //to get customer information
        response = restTemplate.getForObject(builder.toUriString(), CustResponse.class);


        //masked id number
        String tempString = response.getCustomer().getIdentification().getIdNumber();
       //todo change using enhance for loop
        for (int i = 0; i < response.getCustomer().getIdentification().getIdNumber().length() - 4; i++) {

            char j = response.getCustomer().getIdentification().getIdNumber().charAt(i);

            tempString = tempString.replace(j, '*');
            System.out.println(j);
        }
        IdentificationDto identificationDto = new IdentificationDto();
        identificationDto.setIdType(response.getCustomer().getIdentification().getIdType());
        identificationDto.setIdNumber(tempString);

        //trigger get role method
        List<RoleDto> roleResponse = getCustomerRole(response.getCustomer().getId());

        List<ContactDto> contactDtos = new ArrayList<>();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(response.getCustomer().getId());
        customerDto.setIdentification(identificationDto);
        customerDto.setDetails(detailsMapper.toDto(response.getCustomer().getDetails()));
        customerDto.setContact(response.getCustomer().getContact().stream().map(contactMapper::toDto).collect(Collectors.toList()));
        customerDto.setRoles(roleResponse);




        return   customerDto;



    }

    public List<RoleDto> getCustomerRole(String customerId) {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url2)
                .queryParam("customerId", customerId);

        RolesResponse response;
//        List<RoleDto> roleDtos = new ArrayList<RoleDto>();


        //to get customer information
        response = restTemplate.getForObject(builder.toUriString(), RolesResponse.class);

        //set role object
//        response.getRoles().forEach(roleResponse -> {
//            RoleDto tempRoleDto = new RoleDto();
//            tempRoleDto.setCode(roleResponse.getCode());
//            tempRoleDto.setDescription(roleResponse.getDescription());
//            roleDtos.add(tempRoleDto);
//            ;
//        });
        List<RoleDto> roleDtos = response.getRoles().stream().map(roleMapper::toDto).collect(Collectors.toList());
        return roleDtos;
    }

}
