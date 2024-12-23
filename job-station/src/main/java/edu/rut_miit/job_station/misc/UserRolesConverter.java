package edu.rut_miit.job_station.misc;

import edu.rut_miit.job_station.entities.UserRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRolesConverter implements AttributeConverter<UserRoles, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRoles role) {
        if (role == null) {
            return null;
        }

        return role.value();
    }

    @Override
    public UserRoles convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return UserRoles.of(code);
    }
}
