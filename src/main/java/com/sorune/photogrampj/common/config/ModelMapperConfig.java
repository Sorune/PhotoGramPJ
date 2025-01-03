package com.sorune.photogrampj.common.config;

import com.sorune.photogrampj.common.enums.Roles;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        Converter<Roles,String> toStringConver = context -> context.getSource().name();

        Converter<List<Roles>,List<String>> rolesListConverter = context ->
                context.getSource().stream()
                                .map(Roles::name).collect(Collectors.toList());

        modelMapper.createTypeMap(Roles.class, String.class)
                .setConverter(toStringConver);
        return modelMapper;
    }
}
